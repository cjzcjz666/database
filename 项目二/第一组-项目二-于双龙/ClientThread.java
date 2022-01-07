import java.util.concurrent.ConcurrentHashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;


class ClientThread implements Runnable{
	
	static int count =0;
	private final CountDownLatch doneSignal;		//实现并发
	
	public ClientThread(CountDownLatch doneSignal) {
		super();
		this.doneSignal = doneSignal;
	}
	
	public void run() {
		
		try {
			final ConcurrentHashMap<Integer, Thread> records = new ConcurrentHashMap<Integer, Thread>();
			//创建客户端socket,指定服务器地址和端口
			int index = getIndex();
			Socket socket=new Socket("localhost", 9999);
			//获取输出流，向服务器端发送消息
			OutputStream os=socket.getOutputStream(); //字节输出流
			PrintWriter pw=new PrintWriter(os);		//将字节流包装成打印流
			//随机生成26个大写字母
			int a=(int)Math.round(Math.random()*26);
    		int j=(int)'A'+a;
    		char ch=(char)j;
    		pw.println(ch);
    		pw.println(index);
			pw.flush(); //刷新缓存
			socket.shutdownOutput();
			//获取输入流，并读取服务器端的响应信息
			InputStream is=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String info=null;			
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {              
			e.printStackTrace();
		}
		
		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int getIndex() {
        return ++count;
    }
	
}