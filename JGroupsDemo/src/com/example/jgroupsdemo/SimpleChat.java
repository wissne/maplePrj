package com.example.jgroupsdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.jgroups.ChannelException;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import android.os.Bundle;
import android.os.Handler;

public class SimpleChat extends Thread {
	private JChannel channel;
	private List<String> state = new LinkedList<String>();
	private String userName = System.getProperty("user.name", "WhiteSock");
	
	private String props="udp.xml";

	
	private Handler handler;

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			channel = new JChannel();  
		
		channel.setReceiver(new ReceiverAdapter() {

			public void receive(Message msg) {
				System.out.println(msg.getSrc() + ": " + msg.getObject());

				android.os.Message message = new android.os.Message();
				Bundle data = new Bundle();
				data.putString("data", (String) msg.getObject());
				message.what = 1;
				message.setData(data);
				handler.sendMessage(message);

				synchronized (state) {
					state.add((String) msg.getObject());
				}
			}

			public void viewAccepted(View view) {
				System.out.println("view accepted: " + view);
			}

//			public byte[] getState() {
//				synchronized (state) {
//					try {
//						return Util.objectToByteBuffer(state);
//					} catch (Exception e) {
//						e.printStackTrace();
//						return null;
//					}
//				}
//			}
//
//			@SuppressWarnings("unchecked")
//			public void setState(byte[] new_state) {
//				try {
//					List<String> list = (List<String>) Util
//							.objectFromByteBuffer(new_state);
//					synchronized (state) {
//						state.clear();
//						state.addAll(list);
//					}
//					System.out.println("received state (" + list.size()
//							+ " messages in chat history):");
//					for (String str : list) {
//						System.out.println(str);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
		});
		channel.connect("ChatCluster");
		channel.getState(null, 10000);

		//
//		sendMessage();

		//
		} catch (ChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void stopConnect() {
		channel.close();
	}

	private void sendMessage() throws Exception {
		boolean succeed = false;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.print(">");
				System.out.flush();
				String line = br.readLine();
				if (line != null && line.equals("exit")) {
					break;
				} else {
					Message msg = new Message(null, null, "[" + userName + "]"
							+ line);
					channel.send(msg);
				}
			}
			succeed = true;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					if (succeed) {
						throw e;
					}
				}
			}
		}
	}
	
	public void sendMessage(String s) throws Exception {
		try {
				String line = s;
				Message msg = new Message(null, null, "[" + userName + "]"
						+ line);
				channel.send(msg);
		} finally {
		}
	}

	public static void main(String args[]) throws Exception {
		new SimpleChat().start();
	}

}
