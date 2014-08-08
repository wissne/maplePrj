package UDP;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class netchat {
	JFrame jf = new JFrame("我的网络聊天程序");
	List l = new List(6);
	JPanel jp = new JPanel();
	TextField tip = new TextField(15);
	TextField tdata = new TextField(15);
	DatagramSocket ds;

	// DatagramPacket dp;
	public netchat() {
		tip.setText("127.0.0.1");
		try {
			ds = new DatagramSocket(3000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new Runnable() {
			DatagramPacket dp;

			public void run() {
				byte[] buf = new byte[1024];
				dp = new DatagramPacket(buf, 1024);
				//
				// byte[] buf=new byte[1024];
				// dp=new DatagramPacket(buf,1024);
				while (true) {
					try {
						ds.receive(dp);
						// l.add(dp.getAddress().getHostName()+"say:"+new
						// String(buf,0,dp.getLength()), -1);
						l.add(dp.getAddress().getHostAddress() + " say:"
								+ new String(buf, 0, +dp.getLength()), -1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new netchat().init();
	}

	private void init() {
		jf.add(l);
		jp.add(tip, BorderLayout.WEST);
		jp.add(tdata, BorderLayout.EAST);
		jf.add(jp, BorderLayout.SOUTH);
		jf.setSize(300, 300);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// byte[] buf;
				// buf=e.getActionCommand().getBytes();
				// DatagramPacket dp;
				// try
				// {
				// dp=new
				// DatagramPacket(buf,buf.length,InetAddress.getByName(tip.getText()),3000);
				// ds.send(dp);
				//
				byte[] buf;
				buf = e.getActionCommand().getBytes();
				DatagramPacket dp;
				try {
					dp = new DatagramPacket(buf, buf.length, InetAddress
							.getByName(tip.getText()), 3000);
					ds.send(dp);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				((TextField) e.getSource()).setText("");
			}
		});
	}
}
