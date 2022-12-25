import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddSC extends JPanel implements ActionListener {
    JTextField �κ�, ѧ��, �ɼ�;
    JButton ¼��;

    public AddSC() {
        �κ� = new JTextField(10);
        ѧ�� = new JTextField(10);
        �ɼ� = new JTextField(10);
        ¼�� = new JButton("¼��");
        ¼��.addActionListener(this); // ��Ӽ�����
        Box box1 = Box.createHorizontalBox();// ���box
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        box1.add(new JLabel("�κ�:"));
        box1.add(�κ�);
        box2.add(new JLabel("ѧ��:"));
        box2.add(ѧ��);
        box3.add(new JLabel("�ɼ�:"));
        box3.add(�ɼ�);
        box4.add(¼��);
        Box boxH = Box.createVerticalBox();// ����box
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
        if (e.getSource() == ¼��) {
            if (�κ�.getText().equals("") || ѧ��.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "�κŻ�ѧ�Ż�ɼ�����Ϊ�գ�");
            } else {
                ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
                String sql1 = "select * from course where Cno='" + �κ�.getText() + "'";
                String sql2 = "select * from student where Sno='" + ѧ��.getText() + "'";
                String sql3 = "select * from sc where Cno='" + �κ�.getText() + "' and Sno='" + ѧ��.getText() + "'";
                String sql4 = "insert into sc values('" + �κ�.getText() + "','" + ѧ��.getText() + "','" + �ɼ�.getText() + "')";
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
                                JOptionPane.showMessageDialog(this,"����ѡ��ÿγ��޷��������");

                            }
                            else
                            {
                                stmt.executeUpdate(sql4);
                                JOptionPane.showMessageDialog(this,"��ӳɹ�");
                            }
                            rs3.close();
                        }
                        else {
                            JOptionPane.showMessageDialog(this,"��ѧ�������ڣ��޷����");
                        }
                        rs2.close();
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"�ÿγ̲����ڣ��޷����");
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

