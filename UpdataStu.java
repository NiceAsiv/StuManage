import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import com.mysql.cj.xdevapi.Result;

public class UpdataStu extends JPanel implements ActionListener {
    String save = null;
    JTextField 学号1, 学号, 姓名, 系别, 性别;
    JButton 修改, 查询;

    public UpdataStu() {
        学号1 = new JTextField(10);
        学号 = new JTextField(10);
        姓名 = new JTextField(10);
        系别 = new JTextField(10);
        性别 = new JTextField(10);
        修改 = new JButton("修改");
        查询 = new JButton("查找");
        Box box1 = Box.createHorizontalBox();//横向排列
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        Box box6 = Box.createHorizontalBox();
        box1.add(new JLabel("学号"));
        box1.add(学号);
        box2.add(new JLabel("姓名"));
        box2.add(姓名);
        box3.add(new JLabel("系别"));
        box3.add(系别);
        box4.add(new JLabel("性别:"));
        box4.add(性别);
        box5.add(修改);
        box6.add(new JLabel("学号:"));
        box6.add(学号1);
        box6.add(查询);
        修改.addActionListener(this);
        查询.addActionListener(this);
        Box boxH = Box.createVerticalBox();//横向排列
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(box5);
        boxH.add(Box.createVerticalGlue());
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.add(boxH);
        p1.add(box6);
        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p);
        add(splitPane, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sql1 = "select * from student where Sno='" + 学号1.getText() + "'";
        String sql = null;
        ResultSet rs = null, rs1 = null;
        if (e.getSource() == 查询) {
            if (学号1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "请填写查询的学号！");
            } else {
                try {
                    Connection conn = Tools.CONN();
                    Statement stmt = conn.createStatement();
                    rs1 = stmt.executeQuery(sql1);
                    if (rs1.next()) {
                        学号.setText(rs1.getString("Sno"));
                        姓名.setText(rs1.getString("Sname"));
                        系别.setText(rs1.getString("Sdept"));
                        性别.setText(rs1.getString("Ssex"));
                        save = 学号1.getText();
                    } else {
                        JOptionPane.showMessageDialog(this, "没有查询到该学号的学生信息！");
                    }
                    stmt.close();
                    rs1.close();
                } catch (SQLException e1) {
                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                }
            }
        } else if (e.getSource() == 修改) {
            if (save == null) {
                JOptionPane.showMessageDialog(this, "请先查询要修改的学生信息！");
            } else {
                if (学号.getText().equals("") || 姓名.getText().equals("") || 系别.getText().equals("") || 性别.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "请填写完整的信息！");
                } else {
                    if (save.trim().equals(学号.getText().trim())) {
                        sql = "update student set Sno='" + 学号.getText() + "',Sname='" + 姓名.getText() + "',Sdept='" + 系别.getText() + "',Ssex='" + 性别.getText() + "' where Sno='" + save + "'";
                        System.out.println(sql);
                        try {
                            Connection conn = Tools.CONN();
                            Statement stmt = conn.createStatement();
                            stmt.executeUpdate(sql);
                            JOptionPane.showMessageDialog(this, "修改成功！");
                            save = null;
                            conn.close();
                            stmt.close();
                        } catch (SQLException e1) {
                            System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                        }
                    } else {
                        sql1 = "select * from student where Sno='" + 学号.getText() + "'";
                        try {
                            Connection conn = Tools.CONN();
                            Statement stmt = conn.createStatement();
                            rs = stmt.executeQuery(sql1);
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(this, "要修改的学号与另外一位同学重复！");
                            } else {
                                sql = "update student set Sno='" + 学号.getText() + "',Sname='" + 姓名.getText() + "',Sdept='" + 系别.getText() + "',Ssex='" + 性别.getText() + "' where Sno='" + save + "'";
                                try {
                                    Connection conn1 = Tools.CONN();
                                    Statement stmt1 = conn1.createStatement();
                                    stmt1.executeUpdate(sql);
                                    JOptionPane.showMessageDialog(this, "修改成功！");
                                    save = null;
                                    conn1.close();
                                    stmt1.close();
                                } catch (SQLException e1) {
                                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                                }

                            }
                        } catch (SQLException e1) {
                            System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                        }
                    }
                }
            }
        }
    }
}

