import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DelC extends JPanel implements ActionListener {
    JTextField 课号1,课号,课名;
    JButton 查询,删除;
    String saveString=null;
  public DelC() {
        课号1=new JTextField(10);
        课号=new JTextField(10);
        课名=new JTextField(10);
        查询=new JButton("查询");
        删除=new JButton("删除");
        Box box1=Box.createHorizontalBox();
        Box box2=Box.createHorizontalBox();
        Box box3=Box.createHorizontalBox();
        Box box4=Box.createHorizontalBox();
        Box box5=Box.createHorizontalBox();
        box1.add(new JLabel("课号:",JLabel.CENTER));
        box1.add(课号);
        box2.add(new JLabel("课名:",JLabel.CENTER));
        box2.add(课名);

        box4.add(删除);
        box5.add(new JLabel("课号:",JLabel.CENTER));
        box5.add(课号1);
        box5.add(查询);
        Box boxH=Box.createVerticalBox();//横向排列
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(box5);
        boxH.add(Box.createVerticalGlue());
        删除.addActionListener(this);
        查询.addActionListener(this);
        JPanel p=new JPanel();
        JPanel p1=new JPanel();
        p.add(boxH);
        p1.add(box5);
        setLayout(new BorderLayout());
        JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,p1,p);//垂直分割
        add(splitV,BorderLayout.CENTER);
        validate();
}
    @Override
    public void actionPerformed(ActionEvent e) {
        String sql1="select * from course where Cno='"+课号1.getText()+"'";
        if (e.getSource() == 查询) {
            if (课号1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "课号不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                }
            else
            {
                try{
                Connection con=Tools.CONN();
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(sql1);
                if(rs.next()){
                    课号.setText(rs.getString("Cno").trim());
                    课名.setText(rs.getString("Cname").trim());
                    saveString=课号1.getText().trim();
                }
                else{
                    JOptionPane.showMessageDialog(this, "没有该课程", "提示", JOptionPane.WARNING_MESSAGE);
                }
            
               stmt.close();
               rs.close();
               con.close();
            }
            catch(SQLException e1){
                System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
            }
            }
        }
        else if(e.getSource()==删除){
            String sql="delete from course where Cno='"+saveString+"'";
            if(saveString==null){
                JOptionPane.showMessageDialog(this, "请先查询", "提示", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try{
                Connection con=Tools.CONN();
                Statement stmt=con.createStatement();// 创建Statement对象
                stmt.executeUpdate(sql);//执行sql语句
                saveString=null;//清空
                JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.WARNING_MESSAGE);
                课号.setText("");
                课名.setText("");
                stmt.close();//关闭Statement
                con.close();//关闭Connection
                }
                catch(SQLException e1){
                    System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
                }
            }
        }

    }
}