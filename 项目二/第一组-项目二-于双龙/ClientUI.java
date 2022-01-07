import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientUI {
    
    private JFrame jFrame = new JFrame("17301143");
    private Container container = jFrame.getContentPane();
    private JLabel client_number = new JLabel("   客   户   数   量：  ");
    private JTextField number1 = new JTextField();
    private JLabel thread_number = new JLabel("线程池中线程数量：");
    private JTextField number2 = new JTextField();
    private JButton ok = new JButton("确定");
    private JButton cancel = new JButton("取消");
    
    public ClientUI() {
        //设置窗体的位置及大小
        jFrame.setBounds(600, 300, 360, 240);
        //设置一层相当于桌布的东西
        container.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
    }
    
    public void init() {
    	
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("HomeWork2"));
        container.add(titlePanel, "North");
        
        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        client_number.setBounds(10, 20, 120, 20);
        thread_number.setBounds(10, 60, 120, 20);
        fieldPanel.add(client_number);
        fieldPanel.add(thread_number);
        number1.setBounds(150, 20, 120, 20);
        number2.setBounds(150, 60, 120, 20);
        fieldPanel.add(number1);
        fieldPanel.add(number2);
        container.add(fieldPanel, "Center");
        
        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(ok);
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String cl = number1.getText();
            	try {
            	int ccl = Integer.parseInt(cl);
            	Client client=new Client(ccl);
            	client.startThread();
            	}catch (NumberFormatException e1){
            		e1.printStackTrace();
            	}
            	String th = number2.getText();
            	try {
                	int tth = Integer.parseInt(th);
                	Server server = new Server(tth);
                	server.startThread ();
                	}catch (NumberFormatException e2){
                		e2.printStackTrace();
                	}
            }
        });
        buttonPanel.add(cancel);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	jFrame.dispose();
            }
        });
        container.add(buttonPanel, "South");
    }
    
    public static void main(String[] args) {
        new ClientUI();
    }
   
}


