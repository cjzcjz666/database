import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class MySocketClient {

    private static int localHostPort = 5001;
    private static CountDownLatch latch = new CountDownLatch(10);
    public static void main(String[] args) throws IOException {
        for(int i=0;i<=10;i++) {
            new Thread(() -> {
                try {
                    latch.countDown();
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    latch.await();
                    Thread.sleep((long)(Math.random()*10000));
                    CSendMessage cSendMessage = new CSendMessage();
                    cSendMessage.run();
                    /*System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");*/

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        ServerSocket serverSocket = new ServerSocket(localHostPort);
        while (true) {
            Socket socketServer = serverSocket.accept();
            CGetMessage getMessage = new CGetMessage(socketServer);
            Thread thread = new Thread(getMessage);
            thread.start();
        }
    }
}

// 接收消息
class CGetMessage implements Runnable {
    private InputStream inputStream;
    private Socket socket;
    private byte[] buffer;

    public CGetMessage(Socket socket) {
        this.socket = socket;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String str = "";
        while (true) {
            buffer = new byte[2048];
            int n = 0;
            try {
                n = inputStream.read(buffer);
                str = new String(buffer, 0, n);
                System.out.println("服务器:" + str);
                //
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (str.equals("q")) {
                break;
            }
        }
        try {
            if (socket != null)
                socket.close();
            if (inputStream != null)
                inputStream.close();
        } catch (Exception e) {

        }
    }
}

// 发送消息
class CSendMessage implements Runnable {
    private boolean socketIsExits = false;
    private OutputStream outputStream;
    private Socket socketClient;
    private int remotePort = 5000;
    private String remoteAddress = "localhost";
    private byte[] buffer;

    public CSendMessage() throws IOException {
    }

    public void run() {
        String str = new String();
        checkSocket();
        int size = 0;
        /*int d = (int) (Math.random() * 100);
        while (d != 0) {*/
            //System.out.println("请输入任意A-Z:");
            //buffer = new byte[2048];
            //size = System.in.read(buffer);
            //str = new String(buffer, 0, size);
            char c = (char) (int) (Math.random() * 26 + 97);
            str = String.valueOf(c);

            if (socketIsExits) {
                try {
                    //System.out.println("客户端发送 socket：" + this.socketClient);
                    System.out.print("\nAccess " + str);
                    System.out.println("count: "+"for "+str);
                    outputStream.write(str.getBytes());
                    outputStream.flush();
                    Thread.sleep((long) (Math.random() * 1000));
                    /*d--;*/
                } catch (Exception e) {
                    System.out.println("客户端socket不存在。");
                    checkSocket();
                }
            } else {
                checkSocket();
            }
        }



    private void checkSocket() {
        try {
            socketClient = new Socket(remoteAddress, remotePort);
            outputStream = socketClient.getOutputStream();
            socketIsExits = true;
        } catch (Exception e) {
            socketIsExits = false;
        }
    }
}
