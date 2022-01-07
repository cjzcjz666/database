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
    private JLabel client_number = new JLabel("   ��   ��   ��   ����  ");
    private JTextField number1 = new JTextField();
    private JLabel thread_number = new JLabel("�̳߳����߳�������");
    private JTextField number2 = new JTextField();
    private JButton ok = new JButton("ȷ��");
    private JButton cancel = new JButton("ȡ��");
    
    public ClientUI() {
        //���ô����λ�ü���С
        jFrame.setBounds(600, 300, 360, 240);
        //����һ���൱�������Ķ���
        container.setLayout(new BorderLayout());//���ֹ�����
        //���ð������Ͻ�X�ź�ر�
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʼ��--��������������ؼ�
        init();
        //���ô���ɼ�
        jFrame.setVisible(true);
    }
    
    public void init() {
    	
        /*���ⲿ��--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("HomeWork2"));
        container.add(titlePanel, "North");
        
        /*���벿��--Center*/
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
        
        /*��ť����--South*/
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


