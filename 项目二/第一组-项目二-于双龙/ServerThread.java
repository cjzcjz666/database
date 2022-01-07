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

	//线程执行操作，响应客户端请求
	public void run() {
		//输入
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		//输出
		OutputStream os = null;
		//打印
		PrintWriter pw = null;

		try {

			//获取输入流，并读取客户端信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info=null;
			
			while(true) {
				String instr1  = br.readLine(); //按行读取数据
				String instr2  = br.readLine(); //按行读取数据
                if(instr1 == null){
                    break; //当客户端关闭时跳出循环
                }
                System.out.println("Client "+instr2+" for "+instr1);//打印获得的数据
                Thread.sleep(5);
			}

			socket.shutdownInput();//关闭输入流
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
