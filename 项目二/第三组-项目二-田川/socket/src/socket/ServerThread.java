
package socket;

 

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.PrintWriter;

import java.net.Socket;

 

/*

 * 服务器线程处理类

 */

 

public class ServerThread extends Thread{

	//和本线程相关的Socket

	Socket socket = null;

	

	public ServerThread(Socket socket) {

		this.socket=socket;

	}

	//线程执行操作，响应客户端请求

	public void run() {

		

		InputStream is = null;

		InputStreamReader isr = null;

		BufferedReader br = null;

		OutputStream os = null;

		PrintWriter pw = null;

		try {

			//3. 获取输入流，并读取客户端信息

			is = socket.getInputStream();

			isr = new InputStreamReader(is);

			br = new BufferedReader(isr);

			String info=null;
			
			while(true) {
				String instr  = br.readLine(); //按行读取数据
                String instr2  = br.readLine(); //按行读取数据
                if(instr == null){
                    break; //当客户端关闭时跳出循环
                }

                System.out.println("Client "+instr2+" for "+instr);//打印获得的数据

			}

			socket.shutdownInput();//关闭输入流

			

			os = socket.getOutputStream();

			pw = new PrintWriter(os);

			pw.write("欢迎你！");

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

				//关闭资源

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
