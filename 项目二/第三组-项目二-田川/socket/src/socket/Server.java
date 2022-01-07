
package socket;

 

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.ServerSocket;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.AbstractDocument.BranchElement;

 

/*

 * ����TCPЭ���Socketͨ�ţ�ʵ���û���¼

 * ��������

 */

public class Server {

	public static void main(String[] args) {

		try {

			
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			
			//1.����һ���������˵�Socket����ServerSocket��ָ���󶨵Ķ˿ڣ��������˶˿�

			ServerSocket serverSocket=new ServerSocket(8888);

			//2.����accept������ʼ�������ȴ��ͻ��˵�����

			Socket socket=null;

			int count=0;

			System.out.println("***�����������������ȴ��ͻ��˵�����***");

			while(true) {

				
				for (int i = 0; i < 10; i++) 
				{	
					
					socket=serverSocket.accept();
					cachedThreadPool.execute(new ServerThread(socket));
				}
				

				//ͳ�ƿͻ��˵�����

				//count++;

			
			}

			

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
