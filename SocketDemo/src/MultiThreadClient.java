import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadClient {
	
	public static void main(String[] args) {
		int numTasks = 1;

		String [] userList = {"111", "222", "333", "444", "555", "666", "777", "888", "999", "000"};
		ExecutorService exec = Executors.newCachedThreadPool();

//		for (int i = 0; i < numTasks; i++) {
		
			int i = (int) (Math.random() * 10 % 10);
			String userName = userList[i];
			exec.execute(createTask(userName));
//		}

	}

	// 定义一个简单的任务
	private static Runnable createTask(final String player) {
		return new Runnable() {
			private PrintWriter getWriter(Socket socket) throws IOException {
				OutputStream socketOut = socket.getOutputStream();
				return new PrintWriter(socketOut, true);
			}

			private BufferedReader getReader(Socket socket) throws IOException {
				InputStream socketIn = socket.getInputStream();
				return new BufferedReader(new InputStreamReader(socketIn));
			}
			
			private Socket socket = null;
			private int port = 8821;

			public void run() {
				try {
					socket = new Socket("localhost", port);
					final String SEND_STR = "S:";
					final String REC_STR = "R:";
					String msg = player;
					System.out.println(SEND_STR + msg);
					// 发送准备命令
					PrintWriter outWriter = getWriter(socket);
					outWriter.println(msg);

					// 接收服务器的消息
					BufferedReader inReader = getReader(socket);
					while ((msg = inReader.readLine()) != null) {
						msg = inReader.readLine();
						System.out.println(REC_STR + msg);
					}
					
				} catch (IOException e) {
//					e.printStackTrace();
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

		};
	}
}
