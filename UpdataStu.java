import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


public class UpdataStu extends JPanel implements ActionListener {
    String save = null;
    JTextField ѧ��1, ѧ��, ����, ϵ��, �Ա�;
    JButton �޸�, ��ѯ;

    public UpdataStu() {
        ѧ��1 = new JTextField(10);
        ѧ�� = new JTextField(10);
        ���� = new JTextField(10);
        ϵ�� = new JTextField(10);
        �Ա� = new JTextField(10);
        �޸� = new JButton("�޸�");
        ��ѯ = new JButton("����");
        Box box1 = Box.createHorizontalBox();//��������
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        Box box6 = Box.createHorizontalBox();
        box1.add(new JLabel("ѧ��"));
        box1.add(ѧ��);
        box2.add(new JLabel("����"));
        box2.add(����);
        box3.add(new JLabel("ϵ��"));
        box3.add(ϵ��);
        box4.add(new JLabel("�Ա�:"));
        box4.add(�Ա�);
        box5.add(�޸�);
        box6.add(new JLabel("ѧ��:"));
        box6.add(ѧ��1);
        box6.add(��ѯ);
        �޸�.addActionListener(this);
        ��ѯ.addActionListener(this);
        Box boxH = Box.createVerticalBox();//��������
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(box5);
        boxH.add(Box.createVerticalGlue());
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.add(boxH);
        p1.add(box6);
        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p);
        add(splitPane, BorderLayout.CENTER);
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sql1 = "select * from student where Sno='" + ѧ��1.getText() + "'";
        String sql = null;
        ResultSet rs = null, rs1 = null;
        if (e.getSource() == ��ѯ) {
            if (ѧ��1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "����д��ѯ��ѧ�ţ�");
            } else {
                try {
                    Connection conn = Tools.CONN();
                    Statement stmt = conn.createStatement();
                    rs1 = stmt.executeQuery(sql1);
                    if (rs1.next()) {
                        ѧ��.setText(rs1.getString("Sno"));
                        ����.setText(rs1.getString("Sname"));
                        ϵ��.setText(rs1.getString("Sdept"));
                        �Ա�.setText(rs1.getString("Ssex"));
                        save = ѧ��1.getText();
                    } else {
                        JOptionPane.showMessageDialog(this, "û�в�ѯ����ѧ�ŵ�ѧ����Ϣ��");
                    }
                    stmt.close();
                    rs1.close();
                } catch (SQLException e1) {
                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                }
            }
        } else if (e.getSource() == �޸�) {
            if (save == null) {
                JOptionPane.showMessageDialog(this, "���Ȳ�ѯҪ�޸ĵ�ѧ����Ϣ��");
            } else {
                if (ѧ��.getText().equals("") || ����.getText().equals("") || ϵ��.getText().equals("") || �Ա�.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "����д��������Ϣ��");
                } else {
                    if (save.trim().equals(ѧ��.getText().trim())) {
                        sql = "update student set Sno='" + ѧ��.getText() + "',Sname='" + ����.getText() + "',Sdept='" + ϵ��.getText() + "',Ssex='" + �Ա�.getText() + "' where Sno='" + save + "'";
                        System.out.println(sql);
                        try {
                            Connection conn = Tools.CONN();
                            Statement stmt = conn.createStatement();
                            stmt.executeUpdate(sql);
                            JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
                            save = null;
                            conn.close();
                            stmt.close();
                        } catch (SQLException e1) {
                            System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                        }
                    } else {
                        sql1 = "select * from student where Sno='" + ѧ��.getText() + "'";
                        try {
                            Connection conn = Tools.CONN();
                            Statement stmt = conn.createStatement();
                            rs = stmt.executeQuery(sql1);
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(this, "Ҫ�޸ĵ�ѧ��������һλͬѧ�ظ���");
                            } else {
                                sql = "update student set Sno='" + ѧ��.getText() + "',Sname='" + ����.getText() + "',Sdept='" + ϵ��.getText() + "',Ssex='" + �Ա�.getText() + "' where Sno='" + save + "'";
                                try {
                                    Connection conn1 = Tools.CONN();
                                    Statement stmt1 = conn1.createStatement();
                                    stmt1.executeUpdate(sql);
                                    JOptionPane.showMessageDialog(this, "�޸ĳɹ���");
                                    save = null;
                                    conn1.close();
                                    stmt1.close();
                                } catch (SQLException e1) {
                                    System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                                }

                            }
                        } catch (SQLException e1) {
                            System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                        }
                    }
                }
            }
        }
    }
}

