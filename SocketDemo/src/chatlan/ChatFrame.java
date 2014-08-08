package chatlan;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.InetSocketAddress;

/**
 * Description:
 * <br/>Copyright (C), 2008-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
//定义交谈的对话框
public class ChatFrame extends JDialog
{
	//聊天信息区
	JTextArea msgArea = new JTextArea(12 , 45);
	//聊天输入区
	JTextField chatField = new JTextField(30);
	//发送聊天信息的按钮
	JButton sendBn = new JButton("发送");
	//该交谈窗口对应的用户
	UserInfo user;
	//构造器，用于初始化交谈对话框的界面
	public ChatFrame(LanChat parent , UserInfo user)
	{
		super(parent , "和" + user.getName() + "聊天中" , false);
		this.user = user;
		msgArea.setEditable(false);
		add(new JScrollPane(msgArea));
		JPanel buttom = new JPanel();
		buttom.add(new JLabel("输入信息："));
		buttom.add(chatField);
		buttom.add(sendBn);
		add(buttom , BorderLayout.SOUTH);
		MyActionListener listener = new MyActionListener();
		chatField.addActionListener(listener);
		sendBn.addActionListener(listener);
		pack();
	}

	class MyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			InetSocketAddress dest = (InetSocketAddress)user.getAddress();
			/*在聊友列表中，所有人项的SocketAddress是null*/
			//这表明是向所有人发送消息
			if (dest == null)
			{
				LoginFrame.comUtil.broadCast(chatField.getText());
				msgArea.setText("您对大家说："
					+ chatField.getText() + "\n" + msgArea.getText());
			}
			//向私人发送信息
			else
			{
				//获取发送消息的目的
				dest = new InetSocketAddress(dest.getHostName(),
					dest.getPort() + 1);
				LoginFrame.comUtil.sendSingle(chatField.getText(), dest);
				msgArea.setText("您对" + user.getName() +  "说："
					+ chatField.getText() + "\n" + msgArea.getText());

			}
			chatField.setText("");
		}
	}
	//定义向聊天区域添加消息的方法
	public void addString(String msg)
	{
		msgArea.setText(msg + "\n" + msgArea.getText());
	}
}
