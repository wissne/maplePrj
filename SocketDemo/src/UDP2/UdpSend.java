package UDP2;


import java.net.*;

public class UdpSend {
	public static void main(String[] args) throws Exception {
		send();
	}

	public static void send() throws Exception {
		// 1����udp����ͨ��DatagramSocket
		DatagramSocket ds = new DatagramSocket(8888);

		// 2ȷ�����ݣ��������ݷ�װ�ɰ�DatagramPacket(byte[] buf, int length, InetAddress
		// address, int port
		byte[] buf = "udp send".getBytes();

		DatagramPacket dp = new DatagramPacket(buf, buf.length,
				InetAddress.getByName("127.0.0.1"), 10000);

		// 3.ͨ��socket���񣬲������е����ݰ����ͳ�ȥ��ͨ��send����
		ds.send(dp);

		// 4�ر���Դ
		ds.close();
	}
}