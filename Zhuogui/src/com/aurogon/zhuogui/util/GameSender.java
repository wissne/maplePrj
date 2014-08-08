package com.aurogon.zhuogui.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.aurogon.zhuogui.bean.HostBean;
import com.aurogon.zhuogui.bean.PlayerBean;

public class GameSender implements Runnable {
	
	private HostBean hostServer;
	private MulticastSocket socket = null;
	private DatagramPacket outPacket = null;
	
	private byte[] msg;
	
	public GameSender(HostBean hostServer) throws IOException {
		this.hostServer = hostServer;
		socket = hostServer.getSocket();
		outPacket = hostServer.getOutPacket();
	}
	
	public void sendMsg(PlayerBean player) {
		this.msg = player.toByte();
		new Thread(this).start();
	}
	
	public void run() {
		try {
			outPacket.setData(msg);
			socket.send(outPacket);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
}