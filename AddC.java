import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddC extends JPanel implements ActionListener {
    JTextField �κ�, ����;
    JTextField ѧ��;
    JButton ���;
    public AddC()
    {
        setLayout(new FlowLayout());
        �κ� = new JTextField(12);
        ���� = new JTextField(12);
        ѧ�� = new JTextField(12);
        ��� = new JButton("���");
        ���.addActionListener(this);
        Box box1=Box.createHorizontalBox();
        Box box2=Box.createHorizontalBox();
        Box box3=Box.createHorizontalBox();
        Box box4=Box.createHorizontalBox();
        box1.add(new JLabel("�κ�:"));
        box1.add(�κ�);
        box2.add(new JLabel("����:"));
        box2.add(����);
        box3.add(new JLabel("ѧ��:"));
        box3.add(ѧ��);
        box4.add(���);//��ӵ�������
        Box boxH=Box.createVerticalBox();//������ֱ����
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
        if(e.getSource() == ���)
        {
           
            if(�κ�.getText().equals("") || ����.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "�κŻ��������Ϊ��");
            }
            String sql1="select * from course where Cno='"+�κ�.getText()+"'";
            String sql = "insert into course values('" + �κ�.getText() + "','" + ����.getText()+"','"+ѧ��.getText()+"')";
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
                    JOptionPane.showMessageDialog(this, "�ÿκ��Դ��ڣ��޷����");
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "��ӳɹ�");
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