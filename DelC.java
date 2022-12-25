import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DelC extends JPanel implements ActionListener {
    JTextField �κ�1,�κ�,����;
    JButton ��ѯ,ɾ��;
    String saveString=null;
  public DelC() {
        �κ�1=new JTextField(10);
        �κ�=new JTextField(10);
        ����=new JTextField(10);
        ��ѯ=new JButton("��ѯ");
        ɾ��=new JButton("ɾ��");
        Box box1=Box.createHorizontalBox();
        Box box2=Box.createHorizontalBox();
        Box box3=Box.createHorizontalBox();
        Box box4=Box.createHorizontalBox();
        Box box5=Box.createHorizontalBox();
        box1.add(new JLabel("�κ�:",JLabel.CENTER));
        box1.add(�κ�);
        box2.add(new JLabel("����:",JLabel.CENTER));
        box2.add(����);

        box4.add(ɾ��);
        box5.add(new JLabel("�κ�:",JLabel.CENTER));
        box5.add(�κ�1);
        box5.add(��ѯ);
        Box boxH=Box.createVerticalBox();//��������
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(box5);
        boxH.add(Box.createVerticalGlue());
        ɾ��.addActionListener(this);
        ��ѯ.addActionListener(this);
        JPanel p=new JPanel();
        JPanel p1=new JPanel();
        p.add(boxH);
        p1.add(box5);
        setLayout(new BorderLayout());
        JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,p1,p);//��ֱ�ָ�
        add(splitV,BorderLayout.CENTER);
        validate();
}
    @Override
    public void actionPerformed(ActionEvent e) {
        String sql1="select * from course where Cno='"+�κ�1.getText()+"'";
        if (e.getSource() == ��ѯ) {
            if (�κ�1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "�κŲ���Ϊ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
                }
            else
            {
                try{
                Connection con=Tools.CONN();
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(sql1);
                if(rs.next()){
                    �κ�.setText(rs.getString("Cno").trim());
                    ����.setText(rs.getString("Cname").trim());
                    saveString=�κ�1.getText().trim();
                }
                else{
                    JOptionPane.showMessageDialog(this, "û�иÿγ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
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
        else if(e.getSource()==ɾ��){
            String sql="delete from course where Cno='"+saveString+"'";
            if(saveString==null){
                JOptionPane.showMessageDialog(this, "���Ȳ�ѯ", "��ʾ", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try{
                Connection con=Tools.CONN();
                Statement stmt=con.createStatement();// ����Statement����
                stmt.executeUpdate(sql);//ִ��sql���
                saveString=null;//���
                JOptionPane.showMessageDialog(this, "ɾ���ɹ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
                �κ�.setText("");
                ����.setText("");
                stmt.close();//�ر�Statement
                con.close();//�ر�Connection
                }
                catch(SQLException e1){
                    System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
                }
            }
        }

    }
}