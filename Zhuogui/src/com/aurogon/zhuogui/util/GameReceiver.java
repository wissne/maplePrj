package com.aurogon.zhuogui.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.aurogon.zhuogui.bean.HostBean;
import com.aurogon.zhuogui.bean.PlayerBean;

public class GameReceiver implements Runnable {
	
	private HostBean hostServer;
	private MulticastSocket socket = null;
	private Handler handler = null;
	
	public MulticastSocket getSocket() {
		return socket;
	}

	public void setSocket(MulticastSocket socket) {
		this.socket = socket;
	}

	private InetAddress broadcastAddress = null;
	private DatagramPacket inPacket = null;

	public GameReceiver(HostBean hostServer, Handler handler) throws IOException {
		this.hostServer = hostServer;
		this.socket = hostServer.getSocket();
		this.inPacket = hostServer.getInPacket();
		this.handler = handler;
	}
	
	public void receiveMsg() {
		new Thread(this).start();
	}
	
	public void run() {
		PlayerBean player = new PlayerBean();
		try {
			while(true) {
				socket.receive(inPacket);
				player = PlayerBean.convertToObject(inPacket.getData());
				
				if (hostServer.isHoster()) {
					if (Constant.MESSAGE_TYPE.PLAYER_IS_READY  == player.getMessageType() ) {
						player.setIndex(hostServer.getTotalReady());
						player.setMsgToAll(true);
						int res = hostServer.addTotalReady(player);
						if (res == Constant.USER_OPRATION_RESULT.USER_NAME_EXISTS) {
							player.setMsg("�Ѿ���������[" + player.getName() +"]����ң�������������¼�����Ϸ");
							player.setMsgToAll(false);
						} else if (res == Constant.USER_OPRATION_RESULT.USER_RE_CONN) {
							player.setMsg("���[" + player.aboutMe() +"]���¼�������Ϸ");
						} else if (res == Constant.USER_OPRATION_RESULT.ROOM_IS_FULL) {
							player.setMsg("����������������ķ���");
							player.setMsgToAll(false);
						}
						if (res == Constant.USER_OPRATION_RESULT.USER_ADD_SUCCESS)
							player.setMsg("���[" + player.aboutMe() +"]��������Ϸ");
						Message msg = new Message();
						Bundle data = new Bundle();
						player.setAllPlayerList(hostServer.getAllPlayerList());
						player.setGameInfo("����" + hostServer.getRoom()+"-" + hostServer.getTotalReady() + "/" + hostServer.getTotalPlayer() + "-" + hostServer.getTotalPeople() + "��-" + hostServer.getTotalGhost() + "��-" + hostServer.getTotalIdiot() + "��");
						data.putSerializable(Constant.HOST_DATA.DATA, player);
						msg.what = Constant.MESSAGE_TYPE.PLAYER_IS_READY;
						msg.setData(data);
						handler.sendMessage(msg);
					} else if (Constant.MESSAGE_TYPE.PLAYER_HAS_LEFT  == player.getMessageType() ) {
						player.setMsgToAll(true);
						int res = hostServer.delTotalReady(player);
						if (res == Constant.USER_OPRATION_RESULT.USER_NOT_FOUND) {
							player.setMsg("���[" + player.getName() +"]���ڵ�ǰ��Ϸ��");
							player.setMsgToAll(false);
						}						
						if (res == Constant.USER_OPRATION_RESULT.USER_DEL_SUCCESS)
							player.setMsg("���[" + player.aboutMe() +"]�뿪����Ϸ");
						Message msg = new Message();
						Bundle data = new Bundle();
						player.setAllPlayerList(hostServer.getAllPlayerList());
						player.setGameInfo("����" + hostServer.getRoom()+"-" + hostServer.getTotalReady() + "/" + hostServer.getTotalPlayer() + "-" + hostServer.getTotalPeople() + "��-" + hostServer.getTotalGhost() + "��-" + hostServer.getTotalIdiot() + "��");
						data.putSerializable(Constant.HOST_DATA.DATA, player);
						msg.what = Constant.MESSAGE_TYPE.PLAYER_HAS_LEFT;
						msg.setData(data);
						handler.sendMessage(msg);
					} else if (Constant.MESSAGE_TYPE.HOST_HAS_LEFT == player.getMessageType() ) {
						player.setMsgToAll(true);
						player.setMsg("����[" + player.aboutMe() +"]�Ͽ������ӣ������¿�ʼ��Ϸ");
						Message msg = new Message();
						Bundle data = new Bundle();
						player.setAllPlayerList(null);
						player.setGameInfo("�������˳�");
						data.putSerializable(Constant.HOST_DATA.DATA, player);
						msg.what = Constant.MESSAGE_TYPE.HOST_HAS_LEFT;
						msg.setData(data);
						handler.sendMessage(msg);
					} else if (Constant.MESSAGE_TYPE.SEND_MY_TICKET == player.getMessageType() ) {
						player.setMsgToAll(false);
						Message msg = new Message();
						Bundle data = new Bundle();
						data.putSerializable(Constant.HOST_DATA.DATA, player);
						msg.what = Constant.MESSAGE_TYPE.SEND_MY_TICKET;
						msg.setData(data);
						handler.sendMessage(msg);
					}
				} 
				// ����Handler
				if (Constant.MESSAGE_TYPE.GET_YOUR_WORD == player.getMessageType()) {
					// �������
					Message msg = new Message();
					Bundle data = new Bundle();
					data.putSerializable(Constant.HOST_DATA.DATA, player);
					msg.what = Constant.MESSAGE_TYPE.GET_YOUR_WORD;
					msg.setData(data);
					handler.sendMessage(msg);
					
				} else if (Constant.MESSAGE_TYPE.SEND_YOUR_TICKEDT == player.getMessageType()) {
					// ��ʼͶƱ
					Message msg = new Message();
					Bundle data = new Bundle();
					player.setMsgToAll(true);
					data.putSerializable(Constant.HOST_DATA.DATA, player);
					msg.what = Constant.MESSAGE_TYPE.SEND_YOUR_TICKEDT;
					msg.setData(data);
					handler.sendMessage(msg);	
				} else if (Constant.MESSAGE_TYPE.PLAYER_GAME_OVER == player.getMessageType()) {
					// ͶƱ����
					Message msg = new Message();
					Bundle data = new Bundle();
					player.setMsgToAll(true);
					data.putSerializable(Constant.HOST_DATA.DATA, player);
					msg.what = Constant.MESSAGE_TYPE.PLAYER_GAME_OVER;
					msg.setData(data);
					handler.sendMessage(msg);	
				} else if (Constant.MESSAGE_TYPE.GAME_OVER == player.getMessageType()) {
					// ͶƱ����
					Message msg = new Message();
					Bundle data = new Bundle();
					player.setMsgToAll(true);
					data.putSerializable(Constant.HOST_DATA.DATA, player);
					msg.what = Constant.MESSAGE_TYPE.PLAYER_GAME_OVER;
					msg.setData(data);
					handler.sendMessage(msg);	
				} else if (Constant.MESSAGE_TYPE.SHOW_MESSAGE == player.getMessageType() ) {
					// ����������Ϣ
					if (player.isMsgToAll()) {
						Message msg = new Message();
						Bundle data = new Bundle();
						data.putSerializable(Constant.HOST_DATA.DATA, player);
						msg.what = Constant.MESSAGE_TYPE.SHOW_MESSAGE;
						msg.setData(data);
						handler.sendMessage(msg);
					} else {
						if (hostServer.getIP().equals(player.getIp())) {
							// ���������ҵ���Ϣ
							Message msg = new Message();
							Bundle data = new Bundle();
							data.putSerializable(Constant.HOST_DATA.DATA, player);
							msg.what = Constant.MESSAGE_TYPE.SHOW_MESSAGE;
							msg.setData(data);
							handler.sendMessage(msg);
						}
					}
					if (hostServer.isGameOver())
						hostServer.closeSocket();
				}
			}			
		} catch (IOException e) {
			if (socket != null) {
				try {
					socket.leaveGroup(broadcastAddress);
				} catch (IOException e2) {
				}
				socket.close();
			}
			e.printStackTrace();
		} finally {
		}
	}
}
