
package socket;

 

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.PrintWriter;

import java.net.Socket;

 

/*

 * �������̴߳�����

 */

 

public class ServerThread extends Thread{

	//�ͱ��߳���ص�Socket

	Socket socket = null;

	

	public ServerThread(Socket socket) {

		this.socket=socket;

	}

	//�߳�ִ�в�������Ӧ�ͻ�������

	public void run() {

		

		InputStream is = null;

		InputStreamReader isr = null;

		BufferedReader br = null;

		OutputStream os = null;

		PrintWriter pw = null;

		try {

			//3. ��ȡ������������ȡ�ͻ�����Ϣ

			is = socket.getInputStream();

			isr = new InputStreamReader(is);

			br = new BufferedReader(isr);

			String info=null;
			
			while(true) {
				String instr  = br.readLine(); //���ж�ȡ����
                String instr2  = br.readLine(); //���ж�ȡ����
                if(instr == null){
                    break; //���ͻ��˹ر�ʱ����ѭ��
                }

                System.out.println("Client "+instr2+" for "+instr);//��ӡ��õ�����

			}

			socket.shutdownInput();//�ر�������

			

			os = socket.getOutputStream();

			pw = new PrintWriter(os);

			pw.write("��ӭ�㣡");

			pw.flush();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}finally {

			try {

				if(pw!=null)

					pw.close();

				if(os!=null)

					os.close();

				//�ر���Դ

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
