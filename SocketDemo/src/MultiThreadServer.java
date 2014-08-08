import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
	private int port = 8821;
	private ServerSocket serverSocket;
	private ExecutorService executorService;// 线程池
	private final int POOL_SIZE = 10;// 单个CPU线程池大小
	public static int MAX = 4;
	public static int NUM_GHOST = 1;
	public static int NUM_PEOPLE = 2;
	public static int NUM_IDIOT = 1;
	public static int CUR = 0;
	public static String [][] ALL_WORDS = {{"黑色","白色"}, {"大的", "小的"}, {"长的","短的"}};
	public static int indWord = (int) (Math.floor(Math.random() * MultiThreadServer.ALL_WORDS.length) % MultiThreadServer.ALL_WORDS.length);
	public static String [] PEOPLE = new String [NUM_PEOPLE];;
	public static String [] IDIOT = new String [NUM_IDIOT];;
	public static String [] GHOST = new String [NUM_GHOST];
	public static HashMap<String, String> curPlayer = null;
	public static ArrayList<String> allPlayer = new ArrayList<String>();
	public static boolean isSet = false;

	public MultiThreadServer() throws IOException {
		serverSocket = new ServerSocket(port);
		// Runtime的availableProcessor()方法返回当前系统的CPU数目.
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
				.availableProcessors() * POOL_SIZE);
		System.out.println("Server is running...");
	}

	public void service() {
		while (true) {
			Socket socket = null;
			try {
				// 接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
				socket = serverSocket.accept();
				executorService.execute(new Handler(MAX, socket));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		new MultiThreadServer().service();
	}

}

class Handler implements Runnable {
	private Socket socket;
	private final int MAX;

	public Handler(int max, Socket socket) {
		this.socket = socket;
		this.MAX = max;
	}

	private PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}

	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}

	public String getReady(String msg) {
		return "" + msg;
	}

	public void run() {
		try {
			System.out.println("New connection accepted " + /*
															 * socket.getInetAddress
															 * ()+
															 */":"
					+ socket.getPort());
			BufferedReader inReader = getReader(socket);
			PrintWriter outWriter = getWriter(socket);
			final String FGF = ":";
			final String SEND_STR = "S:";
			final String REC_STR = "R:";
			String msg = inReader.readLine();
			String player = msg;
//			while ((msg = inReader.readLine()) != null) {
				System.out.println(REC_STR + msg);
				msg = socket.getPort() + FGF + "Wait for others"; // Send message
				System.out.println(SEND_STR + msg);
				outWriter.println(getReady(msg));
				MultiThreadServer.CUR++;
				MultiThreadServer.allPlayer.add(player);
				//Waiting for others
				while (MultiThreadServer.CUR < MultiThreadServer.MAX){
					Thread.sleep(100);
				}
				System.out.println("All Players are ready, game begin...");
						
				boolean isSet = false;
				String word = "";
				
				// Set Ghost
				synchronized (MultiThreadServer.GHOST) {
					if (!isSet) {
						int left = MultiThreadServer.MAX - MultiThreadServer.NUM_IDIOT - MultiThreadServer.NUM_PEOPLE;
						boolean con = Math.floor(Math.random() * left  % left) < MultiThreadServer.NUM_GHOST && MultiThreadServer.NUM_GHOST > 0;
						if (con) {
							MultiThreadServer.GHOST[MultiThreadServer.NUM_GHOST - 1] = player;
							MultiThreadServer.NUM_GHOST--;
							isSet = true;
							word = "N/A";
						}			
					}
				}
				// Set idiot
				synchronized (MultiThreadServer.IDIOT) {
					if (!isSet) {
						int left = MultiThreadServer.MAX - MultiThreadServer.NUM_IDIOT - MultiThreadServer.NUM_PEOPLE;
						boolean con = Math.floor(Math.random() * left  % left) < MultiThreadServer.NUM_IDIOT && MultiThreadServer.NUM_IDIOT > 0;
						if (con) {
							MultiThreadServer.IDIOT[MultiThreadServer.NUM_IDIOT - 1] = player;
							MultiThreadServer.NUM_IDIOT--;
							isSet = true;
							word = MultiThreadServer.ALL_WORDS[MultiThreadServer.indWord][1];
						}			
					}
				}
				// Set people
				synchronized (MultiThreadServer.PEOPLE) {
					if (!isSet) {
						word = MultiThreadServer.ALL_WORDS[MultiThreadServer.indWord][0];
						MultiThreadServer.PEOPLE[MultiThreadServer.NUM_PEOPLE - 1] = player;
						MultiThreadServer.NUM_PEOPLE--;
					}
				}
//				System.out.println(MultiThreadServer.indWord);				
				msg = "Your word is: " + word;
				outWriter.println(msg);
//				if (msg.equals("bye"))
//					break;
//			}
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
