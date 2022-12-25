import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class AddStu extends JPanel implements ActionListener {
    JTextField 学号, 姓名, 系别,性别;
    JButton 添加;

    public AddStu() {
        setLayout(new FlowLayout());
        学号 = new JTextField(12);
        姓名 = new JTextField(12);
        系别 = new JTextField(12);
        性别 = new JTextField(12);
        添加 = new JButton("添加");
        添加.addActionListener(this);
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        box1.add(new JLabel("学号:"));
        box1.add(学号);
        box2.add(new JLabel("姓名:"));
        box2.add(姓名);
        box3.add(new JLabel("系别:"));
        box3.add(系别);
        box4.add(new JLabel("性别:"));
        box4.add(性别);
        box5.add(添加);// 添加到窗口中
        Box boxH = Box.createVerticalBox();// 创建垂直盒子
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(box5);
        boxH.add(Box.createVerticalGlue());
        JPanel messJPanel = new JPanel();
        messJPanel.add(boxH);
        setLayout(new BorderLayout());
        add(messJPanel, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == 添加) {
            if (学号.getText().equals("") || 姓名.getText().equals("") || 系别.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "学号或姓名或系别不能为空");
            }
            else {
                String sql1 = "select * from student where Sno='" + 学号.getText() + "'";
                String sql = "insert into student values('" + 学号.getText() + "','" + 姓名.getText() + "','" + 系别.getText() +
                        "','" + 性别.getText() + "')";
                ResultSet rs = null;
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery(sql1);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "该学号已存在，无法添加");
                    } else {
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(this, "添加成功");
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("SQL Exception occur.Message is:" + ex.getMessage());
                }
            }
        }
    }
}
