import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class AddStu extends JPanel implements ActionListener {
    JTextField ѧ��, ����, ϵ��,�Ա�;
    JButton ���;

    public AddStu() {
        setLayout(new FlowLayout());
        ѧ�� = new JTextField(12);
        ���� = new JTextField(12);
        ϵ�� = new JTextField(12);
        �Ա� = new JTextField(12);
        ��� = new JButton("���");
        ���.addActionListener(this);
        Box box1 = Box.createHorizontalBox();
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        box1.add(new JLabel("ѧ��:"));
        box1.add(ѧ��);
        box2.add(new JLabel("����:"));
        box2.add(����);
        box3.add(new JLabel("ϵ��:"));
        box3.add(ϵ��);
        box4.add(new JLabel("�Ա�:"));
        box4.add(�Ա�);
        box5.add(���);// ��ӵ�������
        Box boxH = Box.createVerticalBox();// ������ֱ����
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(box5);
        boxH.add(Box.createVerticalGlue());
        JPanel messJPanel = new JPanel();
        messJPanel.add(boxH);
        setLayout(new BorderLayout());
        add(messJPanel, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ���) {
            if (ѧ��.getText().equals("") || ����.getText().equals("") || ϵ��.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "ѧ�Ż�������ϵ����Ϊ��");
            }
            else {
                String sql1 = "select * from student where Sno='" + ѧ��.getText() + "'";
                String sql = "insert into student values('" + ѧ��.getText() + "','" + ����.getText() + "','" + ϵ��.getText() +
                        "','" + �Ա�.getText() + "')";
                ResultSet rs = null;
                try {
                    Connection con = Tools.CONN();
                    Statement stmt = con.createStatement();
                    rs = stmt.executeQuery(sql1);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "��ѧ���Ѵ��ڣ��޷����");
                    } else {
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(this, "��ӳɹ�");
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println("SQL Exception occur.Message is:" + ex.getMessage());
                }
            }
        }
    }
}
