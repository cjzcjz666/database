package Server;

import Server.ThreadPool.ThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.ListIterator;

public class ServerMgr {

    // 服务器的任务列表
    public static LinkedList<Task> taskList = new LinkedList<>();

    // taskList 列表的迭代器
    public ListIterator<Task> taskIterator = ServerMgr.taskList.listIterator();

    // 线程池对象
    public static ThreadPool threadPool;

    /**
     * 请求接收器
     * 将所有客户端的请求接收并解析，生成对应的 Task 对象，储存在 taskList 列表中
     *
     * @throws IOException ServerSocket 套接字IO异常
     */
    static void receiveRequest() throws IOException {
        ServerSocket ss = new ServerSocket(44944);
        System.out.println("The server has been started...");
        System.out.println("Waiting for information from the client...\n");

        int counter = 0;

        /* 接收并处理客户端所有请求 */
        while (true) {
            Socket sk = ss.accept();
            InputStreamReader isr = new InputStreamReader(sk.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();

            /* 接收到客户端的请求，该其创建一个任务对象，并将任务对象添加到列表 */
            if (!line.equals("END")) {
                Task task = new Task(line, counter);
                taskList.add(task);
                counter++;
            }

            /* 接收到结束信号 END ，打印提示信息，跳出循环 */
            else {
                System.out.println("All requests received...\n");
                break;
            }
        }
    }
}
