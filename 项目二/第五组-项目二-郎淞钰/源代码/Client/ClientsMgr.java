package Client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

class ClientsMgr {

    // 客户端的数量
    private int amount;

    // 储存客户端对象的数组
    private Client[] clients;

    // 服务器IP地址
    private String host;

    // 服务器端口号
    private int port;

    /**
     * 获取需要模拟的客户端数量，初始化各属性，调用发送请求的方法
     *
     * @param amount 客户端数量
     */
    ClientsMgr(int amount) {
        this.amount = amount;
        clients = new Client[amount];
        host = "127.0.0.1";
        port = 44944;

        /* 创建客户端对象 */
        for (int num = 0; num < amount; num++) {
            clients[num] = new Client(num);
        }

        sendRequest();
    }

    /**
     * 模拟客户端对服务器发送请求的模拟器
     * 客户端发送的字符串示例：Client No.1 requests access to X
     */
    private void sendRequest() {

        /* 每一个客户端对象发送一条请求 */
        for (int i = 0; i < amount; i++) {
            String str = "Client No." + clients[i].getClientNo()
                    + " requests access to " + clients[i].getLetter();
            try {
                Socket sk = new Socket(host, port);
                PrintStream ps = new PrintStream(sk.getOutputStream());
                ps.println(str);
                ps.close();
                sk.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* 客户端全部模拟完成，发送完成信号 END 字符串 */
        try {
            Socket sk = new Socket("127.0.0.1", 44944);
            PrintStream ps = new PrintStream(sk.getOutputStream());
            ps.println("END");
            ps.close();
            sk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
