import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class getgrade extends JPanel implements ActionListener {
    JTextField ѧ��,����,�κ�,����,�ɼ�;
    JButton ��ѯ;
    public getgrade() {
        ѧ�� = new JTextField(10);
        ���� = new JTextField(10);
        �κ� = new JTextField(10);
        ���� = new JTextField(10);
        �ɼ� = new JTextField(10);
        ��ѯ = new JButton("��ѯ");
        ��ѯ.addActionListener(this); // ��Ӽ�����
        Box box1 = Box.createHorizontalBox();// ���box
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        box1.add(new JLabel("ѧ��:", JLabel.CENTER));
        box1.add(ѧ��);
        box1.add(new JLabel("����:", JLabel.CENTER));
        box1.add(����);
        box2.add(new JLabel("�κ�:", JLabel.CENTER));
        box2.add(�κ�);
        box2.add(new JLabel("����:", JLabel.CENTER));
        box2.add(����);
        box3.add(new JLabel("�ɼ�:", JLabel.CENTER));
        box3.add(�ɼ�);
        box4.add(��ѯ);
        Box boxH = Box.createVerticalBox();// ����box
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ��ѯ) {
            if (ѧ��.getText().equals("") || ����.getText().equals("") || �κ�.getText().equals("") || ����.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "ѧ�Ż�������γ̻��������Ϊ��");
            } else {
                String sql = "select * from course,sc,student where sc.Sno=student.Sno and course.Cno=sc.Cno and student.Sno='" + ѧ��.getText() + "' and course.Cno='" + �κ�.getText() + "'";
              System.out.println(sql);
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        ����.setText(rs.getString("Sname").trim());
                        ����.setText(rs.getString("Cname").trim());
                        �ɼ�.setText(rs.getString("grade").trim());
                        ѧ��.setText(rs.getString("Sno").trim());
                        �κ�.setText(rs.getString("Cno").trim());
                    } else {
                        JOptionPane.showMessageDialog(this, "û�и�ѧ����ÿγ�");
                    }


                } catch (SQLException e1) {
                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                }

            }
        }
    }

}
