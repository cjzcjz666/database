import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
 

public class ServerThread extends Thread{

	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket=socket;
	}

	//�߳�ִ�в�������Ӧ�ͻ�������
	public void run() {
		//����
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		//���
		OutputStream os = null;
		//��ӡ
		PrintWriter pw = null;

		try {

			//��ȡ������������ȡ�ͻ�����Ϣ
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info=null;
			
			while(true) {
				String instr1  = br.readLine(); //���ж�ȡ����
				String instr2  = br.readLine(); //���ж�ȡ����
                if(instr1 == null){
                    break; //���ͻ��˹ر�ʱ����ѭ��
                }
                System.out.println("Client "+instr2+" for "+instr1);//��ӡ��õ�����
                Thread.sleep(5);
			}

			socket.shutdownInput();//�ر�������
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pw!=null)
					pw.close();
				if(os!=null)
					os.close();
				if(br!=null)
					br.close();
				if(isr!=null)
					isr.close();
				if(is!=null)
					is.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
