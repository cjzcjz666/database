package Server;

import Server.ThreadPool.AddNewTask;
import Server.ThreadPool.ExecuteCurrentTask;
import Server.ThreadPool.ThreadPool;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerTest {

    public static void main(String[] args) throws IOException {

        /* 启动服务器，接收并解析请求 */
        ServerMgr.receiveRequest();

        /* 创建线程池 */
        ServerMgr.threadPool = new ThreadPool(10);

        /* 创建并启动池中线程 */
        Thread t1 = new Thread(new AddNewTask());
        Thread t2 = new Thread(new ExecuteCurrentTask());
        t1.start();
        t2.start();

    }
}
