import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.*;

class Tools {
    public static Connection CONN() {
        String driverName = "com.mysql.cj.jdbc.Driver"; // ����JDBC����
        String dbURL = "jdbc:mysql://localhost:3306/course_pick_info"; // ���ӷ����������ݿ�test���˴�course_pick_info�����ݿ���
        String userName = "asiv"; // �û���asiv
        String userPwd = "123456"; // ����
        Connection dbConn = null;// ����һ��Connection����
        try {
            Class.forName(driverName);// ע������
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);// ��ȡ���ݿ�����Ӷ���
            System.out.println("Connection Successful!");
            // ������ӳɹ� ����̨���Connection Successful!
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}
public class User extends JFrame {
    private JLabel user, password;
    private JTextField k1;// �û��������
    private JPasswordField k2;// ���������
    private JButton login, exit;// ��¼��ť��ע�ᰴť

    // ��¼���ڹ��캯��
    public User() {
        super("ϵͳ��¼");  //�� �ô��ڱ���
        Container c = getContentPane();
        c.setLayout(new FlowLayout()); // ���ô��ڲ���Ϊ��ʽ����
        user = new JLabel("�û�����");
        user.setFont(new Font("Serif", Font.PLAIN, 20));
        password = new JLabel("��¼���룺");
        password.setFont(new Font("Serif", Font.PLAIN, 20));
        k1 = new JTextField(12);// �û��������
        k2 = new JPasswordField(10);// ���������
        login = new JButton("��¼");
        exit = new JButton("�˳�");
        LoginListener l = new LoginListener();// ������¼������
        ExitListener e = new ExitListener();// �����˳�������
        login.addActionListener(l);// Ϊ��¼��ť��Ӽ�����
        exit.addActionListener(e);// Ϊ�˳���ť��Ӽ�����
        c.add(user);
        c.add(k1);
        c.add(password);
        c.add(k2);
        c.add(login);
        c.add(exit);
        setBounds(600, 300, 250, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private class LoginListener implements ActionListener {// ��¼������

        public void actionPerformed(ActionEvent e) {
            if (k1.getText().equals("") || k2.getPassword().equals("")) {
                JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ�");
            } else {
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    String sql = "select * from admin where Username = '" + k1.getText() + "'and password = '"+ new String(k2.getPassword()) + "'";
                   // System.out.println(sql);
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "��¼�ɹ���");
                        new menu();// ��ת���˵�����
                        dispose();// �رմ���
                    } else {
                        JOptionPane.showMessageDialog(null, "�û������������");
                    }
                    rs.close();
                    stmt.close();
                    con.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(User.this, "SQL Exception occur.Message is:" + ex.getMessage());
                }

        }
    }
    }

    private class ExitListener implements ActionListener {// �˳�������
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}// end of class
