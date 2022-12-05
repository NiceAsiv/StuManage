import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;

import com.mysql.cj.exceptions.RSAException;
public class DelStu extends JPanel implements ActionListener {
    JTextField 学号1, 学号, 姓名,系别,性别;
    JButton 删除,查询;
    String save = null;
    DelStu() {
        学号1 = new JTextField(10);
        学号 = new JTextField(10);
        姓名 = new JTextField(10);
        系别 = new JTextField(10);
        性别 = new JTextField(10);
        删除 = new JButton("删除");
       查询 = new JButton("查找");
        Box box1 = Box.createHorizontalBox();//横向排列
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        Box box6 = Box.createHorizontalBox();
        box1.add(new JLabel("学号:", JLabel.CENTER));
        box1.add(学号);
        box2.add(new JLabel("姓名:", JLabel.CENTER));
        box2.add(姓名);
        box3.add(new JLabel("系别:", JLabel.CENTER));
        box3.add(系别);
        box6.add(new JLabel("性别:", JLabel.CENTER));
        box6.add(性别);
        box4.add(删除);
        box5.add(new JLabel("学号:", JLabel.CENTER));
        box5.add(学号1);
        box5.add(查询);
        Box boxH = Box.createVerticalBox();//横向排列
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box6);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        删除.addActionListener(this);
       查询.addActionListener(this);
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.add(boxH);
        p1.add(box5);
        setLayout(new BorderLayout());
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p);
        add(jsp, BorderLayout.CENTER);
        validate();
    }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==查询)
            {
                if(学号1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this, "学号不能为空");
                }
                else{
                    String sql1="select * from student where Sno='"+学号1.getText()+"'";
                    try{
                        Connection con=Tools.CONN();
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql1);
                        if(rs.next())
                        {
                            学号.setText(rs.getString("Sno").trim());
                            姓名.setText(rs.getString("Sname").trim());
                            系别.setText(rs.getString("Sdept").trim());
                            性别.setText(rs.getString("Ssex").trim());
                            save=学号1.getText().trim();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "没有找到该学生");
                            stmt.close();
                            rs.close();
                            con.close();
                        }

                    }
                    catch(SQLException e1)
                    {
                        System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
                    }
                }
                
           
            }
            else if(e.getSource()==删除)
            {
                if(save==null)
                {
                    JOptionPane.showMessageDialog(this, "请先查找");
                }
                else{
                    String sql2="delete from student where Sno='"+save+"'";
                    try{
                        Connection con=Tools.CONN();
                        Statement stmt=con.createStatement();
                        stmt.executeUpdate(sql2);
                        save=null;
                        JOptionPane.showMessageDialog(this, "删除成功");
                        学号.setText("");
                        姓名.setText("");
                        系别.setText("");
                        性别.setText("");
                        stmt.close();
                        con.close();
                    }
                    catch(SQLException e1)
                    {
                        System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
                    }
                }
            }
        }

}