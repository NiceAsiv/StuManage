import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.sql.*;

class Tools {
    public static Connection CONN() {
        String driverName = "com.mysql.cj.jdbc.Driver"; // 加载JDBC驱动
        String dbURL = "jdbc:mysql://localhost:3306/course_pick_info"; // 连接服务器和数据库test，此处course_pick_info是数据库名
        String userName = "asiv"; // 用户名asiv
        String userPwd = "123456"; // 密码
        Connection dbConn = null;// 创建一个Connection对象
        try {
            Class.forName(driverName);// 注册驱动
            dbConn = DriverManager.getConnection(dbURL, userName, userPwd);// 获取数据库的连接对象
            System.out.println("Connection Successful!");
            // 如果连接成功 控制台输出Connection Successful!
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}
public class User extends JFrame {
    private JLabel user, password;
    private JTextField k1;// 用户名输入框
    private JPasswordField k2;// 密码输入框
    private JButton login, exit;// 登录按钮，注册按钮

    // 登录窗口构造函数
    public User() {
        super("系统登录");  //设 置窗口标题
        Container c = getContentPane();
        c.setLayout(new FlowLayout()); // 设置窗口布局为流式布局
        user = new JLabel("用户名：");
        user.setFont(new Font("Serif", Font.PLAIN, 20));
        password = new JLabel("登录密码：");
        password.setFont(new Font("Serif", Font.PLAIN, 20));
        k1 = new JTextField(12);// 用户名输入框
        k2 = new JPasswordField(10);// 密码输入框
        login = new JButton("登录");
        exit = new JButton("退出");
        LoginListener l = new LoginListener();// 创建登录监听器
        ExitListener e = new ExitListener();// 创建退出监听器
        login.addActionListener(l);// 为登录按钮添加监听器
        exit.addActionListener(e);// 为退出按钮添加监听器
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

    private class LoginListener implements ActionListener {// 登录监听器

        public void actionPerformed(ActionEvent e) {
            if (k1.getText().equals("") || k2.getPassword().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名或密码不能为空！");
            } else {
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    String sql = "select * from admin where Username = '" + k1.getText() + "'and password = '"+ new String(k2.getPassword()) + "'";
                   // System.out.println(sql);
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "登录成功！");
                        new menu();// 跳转至菜单窗口
                        dispose();// 关闭窗口
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误！");
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

    private class ExitListener implements ActionListener {// 退出监听器
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}// end of class
