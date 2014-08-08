package MultiUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MultiCastClient {
	public static void main(String[] args) throws Exception {
		
		DatagramSocket socket = new DatagramSocket();
        int DGRAM_LENGTH = 200;
        byte[] b = "192.168.1.100".getBytes(); //ÎÒµÄipµØÖ·
        DatagramPacket dgram;
        dgram = new DatagramPacket(b, b.length,
                InetAddress.getByName("224.0.0.5"), 6500);
        System.err.println("Sending " + b.length + " bytes to "
                + dgram.getAddress() + ':' + dgram.getPort());
            socket.send(dgram);

    }

}
