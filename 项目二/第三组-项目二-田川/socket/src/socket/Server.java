
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

 * 基于TCP协议的Socket通信，实现用户登录

 * 服务器端

 */

public class Server {

	public static void main(String[] args) {

		try {

			
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			
			//1.创建一个服务器端的Socket，即ServerSocket，指定绑定的端口，并监听此端口

			ServerSocket serverSocket=new ServerSocket(8888);

			//2.调用accept方法开始监听，等待客户端的连接

			Socket socket=null;

			int count=0;

			System.out.println("***服务器即将启动，等待客户端的连接***");

			while(true) {

				
				for (int i = 0; i < 10; i++) 
				{	
					
					socket=serverSocket.accept();
					cachedThreadPool.execute(new ServerThread(socket));
				}
				

				//统计客户端的数量

				//count++;

			
			}

			

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
