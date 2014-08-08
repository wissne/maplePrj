package UDP2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceive {

	public static void main(String[] args) throws Exception {
		receive();
	}

	public static void receive() throws Exception {
		// 1.创建udpsocket，建立端点
		DatagramSocket ds = new DatagramSocket(10000);

		// 2.定义数据包，用于存储数据
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);

		// 3.通过服务的receive方法将收到的数据存入数据包中
		ds.receive(dp);

		// 4.通过数据包的方法获取其中的数据
		String ip = dp.getAddress().getHostAddress();

		String data = new String(dp.getData(), 0, dp.getLength());

		int port = dp.getPort();

		System.out.println(ip + "::" + data + "::" + port);

		// 5.关闭资源
		ds.close();
	}
}