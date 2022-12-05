import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddSC extends JPanel implements ActionListener {
    JTextField 课号, 学号, 成绩;
    JButton 录入;

    public AddSC() {
        课号 = new JTextField(10);
        学号 = new JTextField(10);
        成绩 = new JTextField(10);
        录入 = new JButton("录入");
        录入.addActionListener(this); // 添加监听器
        Box box1 = Box.createHorizontalBox();// 横放box
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        box1.add(new JLabel("课号:"));
        box1.add(课号);
        box2.add(new JLabel("学号:"));
        box2.add(学号);
        box3.add(new JLabel("成绩:"));
        box3.add(成绩);
        box4.add(录入);
        Box boxH = Box.createVerticalBox();// 竖放box
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        JPanel messPanel = new JPanel();
        messPanel.add(boxH);
        setLayout(new BorderLayout());
        add(messPanel, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == 录入) {
            if (课号.getText().equals("") || 学号.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "课号或学号或成绩不能为空！");
            } else {
                ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
                String sql1 = "select * from course where Cno='" + 课号.getText() + "'";
                String sql2 = "select * from student where Sno='" + 学号.getText() + "'";
                String sql3 = "select * from sc where Cno='" + 课号.getText() + "' and Sno='" + 学号.getText() + "'";
                String sql4 = "insert into sc values('" + 课号.getText() + "','" + 学号.getText() + "','" + 成绩.getText() + "')";
//                System.out.println(sql1);
//                System.out.println(sql2);
//                System.out.println(sql3);
//                System.out.println(sql4);
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    rs1 = stmt.executeQuery(sql1);
                    if (rs1.next()){
                        rs2=stmt.executeQuery(sql2);
                        if(rs2.next())
                        {
                            rs3=stmt.executeQuery(sql3);
                            if(rs3.next())
                            {
                                JOptionPane.showMessageDialog(this,"您已选择该课程无法继续添加");

                            }
                            else
                            {
                                stmt.executeUpdate(sql4);
                                JOptionPane.showMessageDialog(this,"添加成功");
                            }
                            rs3.close();
                        }
                        else {
                            JOptionPane.showMessageDialog(this,"该学生不存在，无法添加");
                        }
                        rs2.close();
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"该课程不存在，无法添加");
                    }
                    rs1.close();
                    stmt.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        new AddSC();
    }
}

