import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	
	private int numOfServer=0;
	
	public Server(int num) {
		numOfServer = num;
	}

	public int getNumOfServer() {
		return numOfServer;
	}
	
	public void startThread() {

		try {
			//����һ���ɻ����̳߳�
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			//����һ���������˵�Socket
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(9999);
			//�ȴ��ͻ��˵�����
			Socket socket = null;
			System.out.println("*********************");
			
			while(true) {		
				for (int i = 0; i < numOfServer; i++) {				
					socket = serverSocket.accept();
					cachedThreadPool.execute(new ServerThread(socket));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
