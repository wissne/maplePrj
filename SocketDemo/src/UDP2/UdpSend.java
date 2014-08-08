package UDP2;


import java.net.*;

public class UdpSend {
	public static void main(String[] args) throws Exception {
		send();
	}

	public static void send() throws Exception {
		// 1创建udp服务，通过DatagramSocket
		DatagramSocket ds = new DatagramSocket(8888);

		// 2确定数据，并将数据封装成包DatagramPacket(byte[] buf, int length, InetAddress
		// address, int port
		byte[] buf = "udp send".getBytes();

		DatagramPacket dp = new DatagramPacket(buf, buf.length,
				InetAddress.getByName("127.0.0.1"), 10000);

		// 3.通过socket服务，并将已有的数据包发送出去，通过send方法
		ds.send(dp);

		// 4关闭资源
		ds.close();
	}
}