import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class getgrade extends JPanel implements ActionListener {
    JTextField 学号,姓名,课号,课名,成绩;
    JButton 查询;
    public getgrade() {
        学号 = new JTextField(10);
        姓名 = new JTextField(10);
        课号 = new JTextField(10);
        课名 = new JTextField(10);
        成绩 = new JTextField(10);
        查询 = new JButton("查询");
        查询.addActionListener(this); // 添加监听器
        Box box1 = Box.createHorizontalBox();// 横放box
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        box1.add(new JLabel("学号:", JLabel.CENTER));
        box1.add(学号);
        box1.add(new JLabel("姓名:", JLabel.CENTER));
        box1.add(姓名);
        box2.add(new JLabel("课号:", JLabel.CENTER));
        box2.add(课号);
        box2.add(new JLabel("课名:", JLabel.CENTER));
        box2.add(课名);
        box3.add(new JLabel("成绩:", JLabel.CENTER));
        box3.add(成绩);
        box4.add(查询);
        Box boxH = Box.createVerticalBox();// 竖放box
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        JPanel messJPanel = new JPanel();
        messJPanel.add(boxH);
        setLayout(new BorderLayout());
        add(messJPanel, BorderLayout.CENTER);
        validate();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == 查询) {
            if (学号.getText().equals("") || 姓名.getText().equals("") || 课号.getText().equals("") || 课名.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "学号或姓名或课程或课名不能为空");
            } else {
                String sql = "select * from course,sc,student where sc.Sno=student.Sno and course.Cno=sc.Cno and student.Sno='" + 学号.getText() + "' and course.Cno='" + 课号.getText() + "'";
              System.out.println(sql);
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        姓名.setText(rs.getString("Sname").trim());
                        课名.setText(rs.getString("Cname").trim());
                        成绩.setText(rs.getString("grade").trim());
                        学号.setText(rs.getString("Sno").trim());
                        课号.setText(rs.getString("Cno").trim());
                    } else {
                        JOptionPane.showMessageDialog(this, "没有该学生或该课程");
                    }


                } catch (SQLException e1) {
                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                }

            }
        }
    }

}
