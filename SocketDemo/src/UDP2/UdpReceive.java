package UDP2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceive {

	public static void main(String[] args) throws Exception {
		receive();
	}

	public static void receive() throws Exception {
		// 1.����udpsocket�������˵�
		DatagramSocket ds = new DatagramSocket(10000);

		// 2.�������ݰ������ڴ洢����
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		// 3.ͨ�������receive�������յ������ݴ������ݰ���
		ds.receive(dp);

		// 4.ͨ�����ݰ��ķ�����ȡ���е�����
		String ip = dp.getAddress().getHostAddress();

		String data = new String(dp.getData(), 0, dp.getLength());

		int port = dp.getPort();

		System.out.println(ip + "::" + data + "::" + port);

		// 5.�ر���Դ
		ds.close();
	}
}