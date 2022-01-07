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
	private final CountDownLatch doneSignal;		//ʵ�ֲ���
	
	public ClientThread(CountDownLatch doneSignal) {
		super();
		this.doneSignal = doneSignal;
	}
	
	public void run() {
		
		try {
			final ConcurrentHashMap<Integer, Thread> records = new ConcurrentHashMap<Integer, Thread>();
			//�����ͻ���socket,ָ����������ַ�Ͷ˿�
			int index = getIndex();
			Socket socket=new Socket("localhost", 9999);
			//��ȡ���������������˷�����Ϣ
			OutputStream os=socket.getOutputStream(); //�ֽ������
			PrintWriter pw=new PrintWriter(os);		//���ֽ�����װ�ɴ�ӡ��
			//�������26����д��ĸ
			int a=(int)Math.round(Math.random()*26);
    		int j=(int)'A'+a;
    		char ch=(char)j;
    		pw.println(ch);
    		pw.println(index);
			pw.flush(); //ˢ�»���
			socket.shutdownOutput();
			//��ȡ������������ȡ�������˵���Ӧ��Ϣ
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