import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddC extends JPanel implements ActionListener {
    JTextField 课号, 课名;
    JTextField 学分;
    JButton 添加;
    public AddC()
    {
        setLayout(new FlowLayout());
        课号 = new JTextField(12);
        课名 = new JTextField(12);
        学分 = new JTextField(12);
        添加 = new JButton("添加");
        添加.addActionListener(this);
        Box box1=Box.createHorizontalBox();
        Box box2=Box.createHorizontalBox();
        Box box3=Box.createHorizontalBox();
        Box box4=Box.createHorizontalBox();
        box1.add(new JLabel("课号:"));
        box1.add(课号);
        box2.add(new JLabel("课名:"));
        box2.add(课名);
        box3.add(new JLabel("学分:"));
        box3.add(学分);
        box4.add(添加);//添加到窗口中
        Box boxH=Box.createVerticalBox();//创建垂直盒子
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
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == 添加)
        {
           
            if(课号.getText().equals("") || 课名.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "课号或课名不能为空");
            }
            String sql1="select * from course where Cno='"+课号.getText()+"'";
            String sql = "insert into course values('" + 课号.getText() + "','" + 课名.getText()+"','"+学分.getText()+"')";
            ResultSet rs=null;
            ResultSet rs1=null;
//            System.out.println(sql1);
//            System.out.println(sql);
            try
            {
                Connection con =Tools.CONN();
                Statement stmt = con.createStatement();
                rs=stmt.executeQuery(sql1);
                if(rs.next())
                {
                    JOptionPane.showMessageDialog(this, "该课号以存在，无法添加");
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "添加成功");
                    stmt.executeUpdate(sql);
                }
                rs.close();
                stmt.close();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this, "SQL Exception occur.Message is:"+ex.getMessage());
            }

        }
    }
}