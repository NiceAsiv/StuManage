import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;

public class DelStu extends JPanel implements ActionListener {
    JTextField ѧ��1, ѧ��, ����,ϵ��,�Ա�;
    JButton ɾ��,��ѯ;
    String save = null;
    DelStu() {
        ѧ��1 = new JTextField(10);
        ѧ�� = new JTextField(10);
        ���� = new JTextField(10);
        ϵ�� = new JTextField(10);
        �Ա� = new JTextField(10);
        ɾ�� = new JButton("ɾ��");
       ��ѯ = new JButton("����");
        Box box1 = Box.createHorizontalBox();//��������
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        Box box6 = Box.createHorizontalBox();
        box1.add(new JLabel("ѧ��:", JLabel.CENTER));
        box1.add(ѧ��);
        box2.add(new JLabel("����:", JLabel.CENTER));
        box2.add(����);
        box3.add(new JLabel("ϵ��:", JLabel.CENTER));
        box3.add(ϵ��);
        box6.add(new JLabel("�Ա�:", JLabel.CENTER));
        box6.add(�Ա�);
        box4.add(ɾ��);
        box5.add(new JLabel("ѧ��:", JLabel.CENTER));
        box5.add(ѧ��1);
        box5.add(��ѯ);
        Box boxH = Box.createVerticalBox();//��������
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box6);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        ɾ��.addActionListener(this);
       ��ѯ.addActionListener(this);
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
            if(e.getSource()==��ѯ)
            {
                if(ѧ��1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this, "ѧ�Ų���Ϊ��");
                }
                else{
                    String sql1="select * from student where Sno='"+ѧ��1.getText()+"'";
                    try{
                        Connection con=Tools.CONN();
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql1);
                        if(rs.next())
                        {
                            ѧ��.setText(rs.getString("Sno").trim());
                            ����.setText(rs.getString("Sname").trim());
                            ϵ��.setText(rs.getString("Sdept").trim());
                            �Ա�.setText(rs.getString("Ssex").trim());
                            save=ѧ��1.getText().trim();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "û���ҵ���ѧ��");
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
            else if(e.getSource()==ɾ��)
            {
                if(save==null)
                {
                    JOptionPane.showMessageDialog(this, "���Ȳ���");
                }
                else{
                    String sql2="delete from student where Sno='"+save+"'";
                    try{
                        Connection con=Tools.CONN();
                        Statement stmt=con.createStatement();
                        stmt.executeUpdate(sql2);
                        save=null;
                        JOptionPane.showMessageDialog(this, "ɾ���ɹ�");
                        ѧ��.setText("");
                        ����.setText("");
                        ϵ��.setText("");
                        �Ա�.setText("");
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