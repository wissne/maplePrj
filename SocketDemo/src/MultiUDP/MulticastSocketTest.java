package MultiUDP;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

//�ø���ʵ��Runnable�ӿڣ������ʵ������Ϊ�̵߳�target
public class MulticastSocketTest implements Runnable {
	// ʹ�ó�����Ϊ������Ķ��㲥IP��ַ
	private static final String BROADCAST_IP = "230.0.0.1";
	// ʹ�ó�����Ϊ������Ķ��㲥Ŀ�ĵĶ˿�
	public static final int BROADCAST_PORT = 30000;
	// ����ÿ�����ݱ�������СΪ4K
	private static final int DATA_LEN = 4096;
	// ���屾�����MulticastSocketʵ��
	private MulticastSocket socket = null;
	private InetAddress broadcastAddress = null;
	private Scanner scan = null;
	// ��������������ݵ��ֽ�����
	byte[] inBuff = new byte[DATA_LEN];
	// ��ָ���ֽ����鴴��׼���������ݵ�DatagramPacket����
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// ����һ�����ڷ��͵�DatagramPacket����
	private DatagramPacket outPacket = null;

	public void init() throws Exception {
		try {
			// �������ڷ��͡��������ݵ�MulticastSocket����
			// ��Ϊ��MulticastSocket������Ҫ���գ�������ָ���˿�
			socket = new MulticastSocket(BROADCAST_PORT);
			broadcastAddress = InetAddress.getByName(BROADCAST_IP);
			// ����socket����ָ���Ķ��㲥��ַ
			socket.joinGroup(broadcastAddress);
			// ���ñ�MulticastSocket���͵����ݱ������͵�����
			socket.setLoopbackMode(false);
			// ��ʼ�������õ�DatagramSocket��������һ������Ϊ0���ֽ�����
			outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress,
					BROADCAST_PORT);
			// �����Ա�ʵ����run()������Ϊ�߳�����߳�
			new Thread(this).start();
			// ��������������
			scan = new Scanner(System.in);
			// ���϶�ȡ��������
			while (scan.hasNextLine()) {
				// �����������һ���ַ���ת���ֽ�����
				byte[] buff = scan.nextLine().getBytes();
				// ���÷����õ�DatagramPacket����ֽ�����
				String s = new String(buff);
				Player player= new Player();
				player.setName("maple");
				player.setMsg(s);
				s = SerializObj.writeObjToString(player);
				outPacket.setData(SerializObj.writeObjToByte(player));
				
				// �������ݱ�
				socket.send(outPacket);
			}
		} finally {
			socket.close();
		}
	}

	public void run() {
		try {
			while (true) {
				// ��ȡSocket�е����ݣ����������ݷ���inPacket����װ���ֽ������
				socket.receive(inPacket);
				// ��ӡ�����socket�ж�ȡ������
				String str = new String(inBuff, 0, inPacket.getLength());
				Player player = (Player) SerializObj.readByteToObj(inPacket.getData());
				System.out.println("������Ϣ��"
						+ player.getName() + ":" + player.getMsg());
				
				if (!player.getMsg().equals("test")) {
					String s = "test";
					player= new Player();
					player.setName("maple");
					player.setMsg(s);
					s = SerializObj.writeObjToString(player);
					outPacket.setData(SerializObj.writeObjToByte(player));
					
					// �������ݱ�
					socket.send(outPacket);
				}
			}
		}
		// ��׽�쳣
		catch (IOException ex) {
			ex.printStackTrace();
			try {
				if (socket != null) {
					// �ø�Socket�뿪�ö��IP�㲥��ַ
					socket.leaveGroup(broadcastAddress);
					// �رո�Socket����
					socket.close();
				}
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new MulticastSocketTest().init();
	}
	
}
