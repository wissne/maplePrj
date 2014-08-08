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
//���彻̸�ĶԻ���
public class ChatFrame extends JDialog
{
	//������Ϣ��
	JTextArea msgArea = new JTextArea(12 , 45);
	//����������
	JTextField chatField = new JTextField(30);
	//����������Ϣ�İ�ť
	JButton sendBn = new JButton("����");
	//�ý�̸���ڶ�Ӧ���û�
	UserInfo user;
	//�����������ڳ�ʼ����̸�Ի���Ľ���
	public ChatFrame(LanChat parent , UserInfo user)
	{
		super(parent , "��" + user.getName() + "������" , false);
		this.user = user;
		msgArea.setEditable(false);
		add(new JScrollPane(msgArea));
		JPanel buttom = new JPanel();
		buttom.add(new JLabel("������Ϣ��"));
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
			/*�������б��У����������SocketAddress��null*/
			//��������������˷�����Ϣ
			if (dest == null)
			{
				LoginFrame.comUtil.broadCast(chatField.getText());
				msgArea.setText("���Դ��˵��"
					+ chatField.getText() + "\n" + msgArea.getText());
			}
			//��˽�˷�����Ϣ
			else
			{
				//��ȡ������Ϣ��Ŀ��
				dest = new InetSocketAddress(dest.getHostName(),
					dest.getPort() + 1);
				LoginFrame.comUtil.sendSingle(chatField.getText(), dest);
				msgArea.setText("����" + user.getName() +  "˵��"
					+ chatField.getText() + "\n" + msgArea.getText());

			}
			chatField.setText("");
		}
	}
	//�������������������Ϣ�ķ���
	public void addString(String msg)
	{
		msgArea.setText(msg + "\n" + msgArea.getText());
	}
}
