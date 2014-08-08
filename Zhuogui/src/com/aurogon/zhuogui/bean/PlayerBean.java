package com.aurogon.zhuogui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aurogon.zhuogui.util.Constant;
import com.aurogon.zhuogui.util.SerializObj;

public class PlayerBean implements Serializable{

	private static final long serialVersionUID = 8026571339912449504L;

	private String name;
	
	private String ip;
	
	private int port;
	
	private int index;
	
	private WordBean word;
	
	private String msg;
	
	private String role;
	
	private boolean isGameOver;
	
	private boolean isHoster;
	
	private int messageType;
	
	private boolean isMsgToAll;
	
	private String room;
	
	private String gameInfo;
	
	private String ticketName;
	
	private String deadName;
	
	public String getDeadName() {
		return deadName;
	}

	public void setDeadName(String deadName) {
		this.deadName = deadName;
	}

	private List<TicketBean> ticketBeanList = new ArrayList<TicketBean>();
	
	public List<TicketBean> getTicketBeanList() {
		return ticketBeanList;
	}

	public void setTicketBeanList(List<TicketBean> ticketBeanList) {
		this.ticketBeanList = ticketBeanList;
	}

	public void addTicketBean(TicketBean bean) {
		if (bean == null)
			return;
		for (TicketBean ticket : ticketBeanList) {
			if (ticket.getName().equals(bean.getName())) {
				ticket.addCount();
			}
		}
		ticketBeanList.add(bean);
	}
	
	public String getAllTicket() {
		String s="";
		for (TicketBean ticket : ticketBeanList) {
			s += ticket.getFrom() + " 投给了 " + ticket.getName() + "\n";
		}
		return s;
	}
	
	public String[] getMaxTicketNames() {
		String[] names = new String[allPlayerList.size()];
		int maxCount = 0;
		int pp=0;
		for (TicketBean ticket : ticketBeanList) {
			if (ticket.getCount() > maxCount) {
				names = new String[allPlayerList.size()];
				pp = 0;
				names[pp++] = ticket.getName();
				maxCount = ticket.getCount();
			} else if (ticket.getCount() == maxCount) {
				names[pp++] = ticket.getName();
			}
		}
		return names;
	}
	
	public String getMaxTicketNameString() {
		String [] names = getMaxTicketNames();
		String res = "";
		for (int i = 0; i < names.length; i++) {
			if (names[i]!= null) {
				if (i>0)
					res += ",";
				res += names[i];
			}
		}
		return res;
	}
	
	public String getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(String gameInfo) {
		this.gameInfo = gameInfo;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	private ArrayList<PlayerBean> allPlayerList= new ArrayList<PlayerBean>();
	

	public boolean isMsgToAll() {
		return isMsgToAll;
	}

	public void setMsgToAll(boolean isToAll) {
		this.isMsgToAll = isToAll;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<PlayerBean> getAllPlayerList() {
		return allPlayerList;
	}

	public void setAllPlayerList(ArrayList<PlayerBean> allPlayerList) {
		this.allPlayerList = allPlayerList;
	}
	
	public List<String> getAvailablePlayerNameList(String name) {
		List <String> list = new ArrayList<String>();
		for (PlayerBean bean : allPlayerList) {
			if (!bean.isGameOver && !bean.getName().equals(name)) {
				list.add(bean.getName());
			}
		}
		return list;
	}

	public boolean isHoster() {
		return isHoster;
	}

	public void setHoster(boolean isHoster) {
		this.isHoster = isHoster;
	}

	public WordBean getWord() {
		return word;
	}
	
	public String getMyWord() {
		if (Constant.PEOPLE_TYPE.GHOST.equals(role) && word != null)
			return word.getHint();
		else if (Constant.PEOPLE_TYPE.PEOPLE.equals(role) && word != null)
			return word.getPeopleWord();
		else if (Constant.PEOPLE_TYPE.IDIOT.equals(role) && word != null)
			return word.getIdiotWord();
		return null;
	}
	
	public String getMyWord(PlayerBean bean) {
		ArrayList<PlayerBean> list = this.allPlayerList;
		String s = null;
		if (list != null) {
			for (PlayerBean playerBean : list) {
				if (playerBean.getIp()!=null && playerBean.getIp().equals(bean.getIp())){
					bean = playerBean;
					s = bean.getMyWord();
					break;
				}
			}
			if (Constant.PEOPLE_TYPE.GHOST.equals(bean.getRole())){
				s += "\n战友：";
				boolean first=false;
				for (PlayerBean playerBean : list) {
					if (Constant.PEOPLE_TYPE.GHOST.equals(playerBean.getRole()) && !playerBean.getIp().equals(bean.getIp())) {
						if (first)
							s+=",";
						s += playerBean.getName();
						first = true;
					}
				}
			}
		}
		return s;
	}

	public void setWord(WordBean word) {
		this.word = word;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String aboutMe() {
		if (name != null && ip != null)
			return this.name + ":" + this.ip;
		return null;
	}
	
	public byte[] toByte() {
		byte[] s = null;
		try {
//			s = SerializObj.writeObjToZipByte(this);
			s = SerializObj.writeObjToGZipByte(this);
//			s = SerializObj.writeObjToByte(this);
			System.out.println("-------tobytes " + s.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public String toString() {
		return new String(this.toByte());
	}
	
	public static PlayerBean convertToObject(byte[] s) {
		PlayerBean bean = null;
		try {
			bean = (PlayerBean) SerializObj.readGZipByteToObj(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
}


