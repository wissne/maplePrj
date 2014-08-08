package MultiUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Daniel
 */
public class MultiCastServer implements Runnable {

	public static void main(String[] args) throws IOException {
		Runnable r = new MultiCastServer();
		Thread th = new Thread(r);
		th.start();
		MulticastSocket socket = new MulticastSocket(6500);
	}

	@Override
	public void run() {
		try {
			MulticastSocket socket = new MulticastSocket(6500);
			// 230.0.0.1
			InetAddress group = InetAddress.getByName("224.0.0.5");
			socket.joinGroup(group);
			DatagramPacket packet;
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			String received = new String(packet.getData());
			System.out.println("Received data : " + received);
			socket.leaveGroup(group);
		} catch (IOException ex) {
			Logger.getLogger(MultiCastServer.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}