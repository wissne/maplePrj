package com.aurogon.zhuogui.bean;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.aurogon.zhuogui.util.Constant;


public class HostBean implements Serializable {
	private int totalPlayer = 4;
	private int totalGhost = 1;
	private int totalPeople = 2;
	private int totalIdiot = 1;
	private int totalReady = 0;
	private boolean isAllPlayerReady = false;
	private boolean isGameOver = false;
	private WordBean word;
	private boolean isHoster;
	
	private String IP;
	private ArrayList<PlayerBean> allPlayerList = null;
	
	// 使用常量作为本程序的多点广播IP地址
	private static final String BROADCAST_IP = "239.0.0.";
	// 使用常量作为本程序的多点广播目的的端口
	public static final int BROADCAST_PORT = 30000;
	// 定义每个数据报的最大大小为4K
	private static final int DATA_LEN = 99999;
	// 定义本程序的MulticastSocket实例
	private MulticastSocket socket = null;
	private InetAddress broadcastAddress = null;
	// 定义接收网络数据的字节数组
	byte[] inBuff = new byte[DATA_LEN];
	// 以指定字节数组创建准备接受数据的DatagramPacket对象
	private DatagramPacket inPacket = null;
	// 定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket = null;
	private String room;
	
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public WordBean getWord() {
		return word;
	}

	public void setWord(WordBean word) {
		this.word = word;
	}

	public ArrayList<PlayerBean> getAllPlayerList() {
		return allPlayerList;
	}

	public void setAllPlayerList(ArrayList<PlayerBean> allPlayerList) {
		this.allPlayerList = allPlayerList;
	}
	
	public PlayerBean getPlayerAt(int i) {
		PlayerBean bean = null;
		if (null != allPlayerList && allPlayerList.size() > i)
			bean = allPlayerList.get(i);
		return bean;
	}
	
	public boolean isHoster() {
		return isHoster;
	}

	public void setHoster(boolean isHoster) {
		this.isHoster = isHoster;
	}
	
	public int addPlayer(PlayerBean bean) {
		if (null != allPlayerList && bean != null) {
			for (int i = 0; i < allPlayerList.size(); i++) {
				PlayerBean obj = allPlayerList.get(i);
				if (bean.getIp() != null && obj.getIp() != null && bean.getIp().equals(obj.getIp())) {
					obj.setName(bean.getName());
					obj.setHoster(bean.isHoster());
					return Constant.USER_OPRATION_RESULT.USER_RE_CONN;
				} else {
					if (bean.getName() != null && obj.getName() != null && 
							bean.getName().equals(obj.getName()))
						return Constant.USER_OPRATION_RESULT.USER_NAME_EXISTS;
				}
			}
			if (allPlayerList.size() >= totalPlayer)
				return Constant.USER_OPRATION_RESULT.ROOM_IS_FULL;
			allPlayerList.add(bean);
			return Constant.USER_OPRATION_RESULT.USER_ADD_SUCCESS;
		}
		return Constant.USER_OPRATION_RESULT.USER_IS_NULL;
	}
	
	public int removePlayer(PlayerBean bean) {
		if (null != allPlayerList && bean != null) {
			for (int i = 0; i < allPlayerList.size(); i++) {
				PlayerBean obj = allPlayerList.get(i);
				if (bean.getIp() != null && obj.getIp() != null && 
						bean.getIp().equals(obj.getIp())) {
					allPlayerList.remove(i);
					return Constant.USER_OPRATION_RESULT.USER_DEL_SUCCESS;
				}
			}
			return Constant.USER_OPRATION_RESULT.USER_NOT_FOUND;
		}
		return Constant.USER_OPRATION_RESULT.USER_NOT_EXISTS;
	}
	
	public PlayerBean getObjAt(int i) {
		PlayerBean bean = null;
		if (null != allPlayerList && allPlayerList.size() > i)
			bean = allPlayerList.get(i);
		return bean;
	}
	
	public boolean addObjToList(ArrayList<PlayerBean> list, PlayerBean bean) {
		if (null != list && bean != null) {
			for (int i = 0; i < list.size(); i++) {
				PlayerBean obj = list.get(i);
				if (bean.getIp() != null && obj.getIp() != null && bean.getIp() == obj.getIp()) {
					if (bean.getName() != null && obj.getName() != null && 
							bean.getName().equals(obj.getName())) {
						list.remove(obj);
						list.add(bean);
					}
					return true;
				} else {
					if (bean.getName() != null && obj.getName() != null && 
							bean.getName().equals(obj.getName()))
						return false;
				}
			}
			list.add(bean);
			return true;
		}
		return false;
	}
	
	public boolean removeObjFromList(ArrayList<PlayerBean> list, PlayerBean bean) {
		if (null != list && bean != null) {
			for (int i = 0; i < list.size(); i++) {
				PlayerBean obj = list.get(i);
				if (bean.getName() != null && obj.getName() != null && 
						bean.getName().equals(obj.getName()))
					return false;
			}
			list.remove(bean);
			return true;
		}
		return false;
	}	
	
	public boolean isAllPlayerReady() {
		return isAllPlayerReady;
	}

	public void setAllPlayerReady(boolean isAllPlayerReady) {
		this.isAllPlayerReady = isAllPlayerReady;
	}

	public int getTotalPlayer() {
		return totalPlayer;
	}

	public void setTotalPlayer(int totalPlayer) {
		this.totalPlayer = totalPlayer;
	}

	public int getTotalGhost() {
		return totalGhost;
	}

	public void setTotalGhost(int totalGhost) {
		this.totalGhost = totalGhost;
	}
	
	public int getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}

	public int getTotalIdiot() {
		return totalIdiot;
	}

	public void setTotalIdiot(int totalIdiot) {
		this.totalIdiot = totalIdiot;
	}

	public int getTotalReady() {
		return totalReady;
	}

	public void setTotalReady(int totalReady) {
		this.totalReady = totalReady;
	}
	
	public int addTotalReady(PlayerBean player) {		
		int i = addPlayer(player);
		boolean b = (i == Constant.USER_OPRATION_RESULT.USER_ADD_SUCCESS);
		if (b )
			totalReady++;
		return i;
	}
	
	public int delTotalReady(PlayerBean player) {		
		int i = removePlayer(player);
		boolean b = (i == Constant.USER_OPRATION_RESULT.USER_DEL_SUCCESS);
		if (b) {
			totalReady--;
			if (totalReady < 0)
				totalReady = 0;
		}
		return i;
	}

	public HostBean(int totalPlayer, int totalGhost, int totalIdiot, WordBean wordBean, String room, String IP, boolean isHoster) throws IOException {
		this.totalPlayer = totalPlayer;
		this.totalGhost = totalGhost;
		this.totalPeople = totalPlayer - totalGhost - totalIdiot;
		this.totalIdiot = totalIdiot;
		this.word = wordBean;
		this.room = room;
		this.isHoster = isHoster;
		this.IP = IP;
		this.allPlayerList = new ArrayList<PlayerBean>();
	}
	
	public void init() throws IOException {
		socket = new MulticastSocket(BROADCAST_PORT);
		broadcastAddress = InetAddress.getByName(BROADCAST_IP + this.room);
		socket.joinGroup(broadcastAddress);
		socket.setLoopbackMode(false);
		socket.setTTL((byte) 32);
		socket.setTimeToLive(32);
		outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress,
				BROADCAST_PORT);
		inPacket = new DatagramPacket(inBuff, inBuff.length);
	}
	
	public void closeSocket() {
		try {
			socket.leaveGroup(broadcastAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.disconnect();
		socket.close();
	}
	
	

	public MulticastSocket getSocket() {
		return socket;
	}

	public void setSocket(MulticastSocket socket) {
		this.socket = socket;
	}

	public InetAddress getBroadcastAddress() {
		return broadcastAddress;
	}

	public void setBroadcastAddress(InetAddress broadcastAddress) {
		this.broadcastAddress = broadcastAddress;
	}

	public DatagramPacket getInPacket() {
		return inPacket;
	}

	public void setInPacket(DatagramPacket inPacket) {
		this.inPacket = inPacket;
	}

	public DatagramPacket getOutPacket() {
		return outPacket;
	}

	public void setOutPacket(DatagramPacket outPacket) {
		this.outPacket = outPacket;
	}

}



