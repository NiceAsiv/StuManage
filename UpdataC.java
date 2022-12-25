import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

//底层逻辑都是用同样的模板处理不一样的东西
public class UpdataC extends JPanel implements ActionListener { //这里之前写成jframe了,结果捏,不能把jfame装进另外一个Jframe
    JMenuBar mb = new JMenuBar();
    String save = null;
    JTextField 课号1, 课号, 课名;//课号1是 查询的输入框
    JButton 修改, 查询;

    public UpdataC() {
        课号1 = new JTextField(10);
        课号 = new JTextField(10);
        课名 = new JTextField(10);
        修改 = new JButton("修改");
        查询 = new JButton("查询");
        Box box1 = Box.createHorizontalBox();//横向排列
        Box box2 = Box.createHorizontalBox();//
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        box1.add(new JLabel("课号:", JLabel.CENTER));
        box1.add(课号);
        box2.add(new JLabel("课名:", JLabel.CENTER));
        box2.add(课名);
        box3.add(修改);
        box5.add(new JLabel("课号:", JLabel.CENTER));
        box5.add(课号1);
        box5.add(查询);
        修改.addActionListener(this);
        查询.addActionListener(this);
        Box boxH = Box.createVerticalBox();//横向排列
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.add(boxH);
        p1.add(box5);
        setLayout(new BorderLayout());
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p);//分割窗口
        add(jsp, BorderLayout.CENTER);
        // setTitle("修改课程");
        // setSize(300, 300);
        // setVisible(true);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        @Override
        public void actionPerformed (ActionEvent e){
            ResultSet rs, rs1 = null;
            Connection con = null;
            String sql = null, sql1 = null;
            if (e.getSource() == 查询) {
                if (课号1.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "课号不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    sql1 = "select * from course where Cno='" + 课号1.getText() + "'";

                    System.out.println(sql1);
                    try {
                        con = Tools.CONN();
                        Statement stmt = con.createStatement();
                        rs = stmt.executeQuery(sql1);
                        if (rs.next()) {
                            课号.setText(rs.getString("Cno").trim());
                            课名.setText(rs.getString("Cname").trim());
                            save = 课号1.getText().trim();
                        } else {
                            JOptionPane.showMessageDialog(this, "没有该课程", "提示", JOptionPane.WARNING_MESSAGE);
                        }

                        stmt.close();
                        rs.close();
                        con.close();
                    } catch (SQLException e1) {
                        System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                    }
                }
            }
                    if (e.getSource() == 修改) {
                        if (save == null) {
                            JOptionPane.showMessageDialog(this, "请先查询", "提示", JOptionPane.WARNING_MESSAGE);
                        } else {
                            sql= "update course set Cno='" + 课号.getText() + "',Cname='" + 课名.getText() + "' where Cno='" + save + "'";
                            System.out.println(sql);
                            try {
                                con = Tools.CONN();
                                Statement stmt = con.createStatement();// 创建Statement对象
                                stmt.executeUpdate(sql);//执行sql语句
                                save = null;//清空
                                JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.WARNING_MESSAGE);
                                课号.setText("");
                                课名.setText("");
                                stmt.close();//关闭Statement
                                con.close();//关闭Connection
                            } catch (SQLException e1) {
                                System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                            }
                        }

                    }
                }
            }