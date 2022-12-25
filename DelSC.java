
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DelSC extends JPanel implements ActionListener{
	String saveC=null;
	String saveS=null;
	JTextField 课号1,学号1,学号,成绩,课号;
	JButton 删除,查询;
    DelSC()
    {
     课号1=new JTextField(10);
        学号1=new JTextField(10);
        学号=new JTextField(10);
        课号1=new JTextField(10);
        课号=new JTextField(10);
        成绩=new JTextField(10);
        删除=new JButton("删除");
        查询=new JButton("查询");
        Box box1=Box.createHorizontalBox();
        Box box2=Box.createHorizontalBox();
        Box box3=Box.createHorizontalBox();
        Box box4=Box.createHorizontalBox();
        Box box5=Box.createHorizontalBox();
        box1.add(new JLabel("课号:",JLabel.CENTER));
        box1.add(课号);
        box2.add(new JLabel("学号:",JLabel.CENTER));
        box2.add(学号);
        box4.add(删除);
        box3.add(new JLabel("课号:",JLabel.CENTER));
        box3.add(课号1);
        box5.add(new JLabel("学号:",JLabel.CENTER));
        box5.add(学号1);//用来查询的学号
        box5.add(查询);
        Box boxH=Box.createVerticalBox();//横向排列
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        删除.addActionListener(this);
        查询.addActionListener(this);
        JPanel p=new JPanel();
        JPanel p1=new JPanel();
        p.add(boxH);
        p1.add(box3);
        p1.add(box5);
        setLayout(new BorderLayout());
        JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,p1,p);
        add(jsp,BorderLayout.CENTER);
        validate();
    }
    public void actionPerformed(ActionEvent e)
    {  
        String sql1="select * from sc where Cno='"+课号1.getText()+"' and Sno='"+学号1.getText()+"'";
        ResultSet rs1=null;
        ResultSet rs=null;
        if(e.getSource()==查询)
        {
            if(课号1.getText().equals("")||学号1.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"请输入课号和学号","提示",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                try{

                    Connection con =Tools.CONN();
                    Statement stmt = con.createStatement();
                    rs=stmt.executeQuery(sql1);
                    if(rs.next())
                    {
                        学号.setText(rs.getString("Sno").trim());
                        课号.setText(rs.getString("Cno").trim());
                        成绩.setText(rs.getString("grade").trim());
                        saveC=课号1.getText().trim();
                        saveS=学号1.getText().trim();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"没有这个课号的学生","提示",JOptionPane.WARNING_MESSAGE);
                    }
                    con.close();
                    stmt.close();
                    rs.close();
                    
                }catch(SQLException e1){
                    System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
                }
                
            }

        }
        else if(e.getSource()==删除)
        {
            String sql="delete from sc where Cno='"+saveC+"' and Sno='"+saveS+"'";
            if(saveC==null||saveS==null)
            {
                JOptionPane.showMessageDialog(this,"请先查询","提示",JOptionPane.WARNING_MESSAGE);
            }
            else {
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.WARNING_MESSAGE);
                    con.close();
                    stmt.close();
                } catch (SQLException e1) {
                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                }
            }
        }
    }

}
