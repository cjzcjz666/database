import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySocketServer {
    private static int localHostPort = 5000;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket s = new ServerSocket(localHostPort);
        //final int[] count = {0};
        //ExecutorService execut=Executors.newFixedThreadPool(10);
        try {
            ServerSocket server = new ServerSocket(20011);
            ExecutorService executor=Executors.newFixedThreadPool(20);
            boolean flag = true;
            while (flag) {
                System.out.println("\n准备接入客户端");
                executor.execute(new Handler(s.accept()));
                System.out.println("客户端已经连接");
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
 class Handler extends Thread {

    private Socket client = null;
    private BufferedReader input = null;
    private OutputStream out = null;
    //获取Socket的输入流，用来接收从服务端发送过来的数据
    InputStream buf =  null;

    public Handler(Socket client ) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            GetMessage getMessage = new GetMessage(client);
            getMessage.run();
            CSendMessage cSendMessage = null;
            cSendMessage = new CSendMessage();
            cSendMessage.run();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}



// 接收消息
class GetMessage implements Runnable {
    private int remotePort = 5001;
    private String remoetAddress = "localhost";
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socketGet;
    private BufferedReader br;
    private Socket socketSendMessage;
    private boolean socketIsExits = false;
    private int sum = 0;
    int count;

    private byte[] buffer;

    public GetMessage(Socket socket) {
        this.socketGet = socket;
        try {
            inputStream = socketGet.getInputStream();
            outputStream = socketGet.getOutputStream();
            //InputStream is=socket.getInputStream();
            //br=new BufferedReader(new InputStreamReader(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String str = "";
        int n = 0;
        while (true) {
            try {
                buffer = new byte[2048];
                n = inputStream.read(buffer);
                str = new String(buffer, 0, n);
                //str = br.readLine();
                System.out.print("\nAccess:" + str);
                sendMessage(str);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (str.equals("q")) {
                break;
            }
        }
        try {
            if (socketGet != null)
                socketGet.close();
            if (inputStream != null)
                inputStream.close();
        } catch (Exception e) {

        }
    }

    // 发送消息
    public void sendMessage(String str) throws IOException {
        if (socketIsExits) {
            try {
                //
                /*Thread.sleep((long) (Math.random() * 100));*/
                String input = "Client "+count+"  for " +str;
                //System.out.println("服务端发送 socket：" + this.socketSendMessage);
                outputStream.write(input.getBytes());
                System.out.println("服务器：" + input);
                outputStream.flush();
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
            socketSendMessage = new Socket(remoetAddress, remotePort);
            outputStream = socketSendMessage.getOutputStream();
            socketIsExits = true;
        } catch (Exception e) {
            socketIsExits = false;
        }
    }
}
