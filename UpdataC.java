import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

//�ײ��߼�������ͬ����ģ�崦��һ���Ķ���
public class UpdataC extends JPanel implements ActionListener { //����֮ǰд��jframe��,�����,���ܰ�jfameװ������һ��Jframe
    JMenuBar mb = new JMenuBar();
    String save = null;
    JTextField �κ�1, �κ�, ����;//�κ�1�� ��ѯ�������
    JButton �޸�, ��ѯ;

    public UpdataC() {
        �κ�1 = new JTextField(10);
        �κ� = new JTextField(10);
        ���� = new JTextField(10);
        �޸� = new JButton("�޸�");
        ��ѯ = new JButton("��ѯ");
        Box box1 = Box.createHorizontalBox();//��������
        Box box2 = Box.createHorizontalBox();//
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        box1.add(new JLabel("�κ�:", JLabel.CENTER));
        box1.add(�κ�);
        box2.add(new JLabel("����:", JLabel.CENTER));
        box2.add(����);
        box3.add(�޸�);
        box5.add(new JLabel("�κ�:", JLabel.CENTER));
        box5.add(�κ�1);
        box5.add(��ѯ);
        �޸�.addActionListener(this);
        ��ѯ.addActionListener(this);
        Box boxH = Box.createVerticalBox();//��������
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(Box.createVerticalGlue());
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        p.add(boxH);
        p1.add(box5);
        setLayout(new BorderLayout());
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p);//�ָ��
        add(jsp, BorderLayout.CENTER);
        // setTitle("�޸Ŀγ�");
        // setSize(300, 300);
        // setVisible(true);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        @Override
        public void actionPerformed (ActionEvent e){
            ResultSet rs, rs1 = null;
            Connection con = null;
            String sql = null, sql1 = null;
            if (e.getSource() == ��ѯ) {
                if (�κ�1.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "�κŲ���Ϊ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
                } else {
                    sql1 = "select * from course where Cno='" + �κ�1.getText() + "'";

                    System.out.println(sql1);
                    try {
                        con = Tools.CONN();
                        Statement stmt = con.createStatement();
                        rs = stmt.executeQuery(sql1);
                        if (rs.next()) {
                            �κ�.setText(rs.getString("Cno").trim());
                            ����.setText(rs.getString("Cname").trim());
                            save = �κ�1.getText().trim();
                        } else {
                            JOptionPane.showMessageDialog(this, "û�иÿγ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
                        }

                        stmt.close();
                        rs.close();
                        con.close();
                    } catch (SQLException e1) {
                        System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                    }
                }
            }
                    if (e.getSource() == �޸�) {
                        if (save == null) {
                            JOptionPane.showMessageDialog(this, "���Ȳ�ѯ", "��ʾ", JOptionPane.WARNING_MESSAGE);
                        } else {
                            sql= "update course set Cno='" + �κ�.getText() + "',Cname='" + ����.getText() + "' where Cno='" + save + "'";
                            System.out.println(sql);
                            try {
                                con = Tools.CONN();
                                Statement stmt = con.createStatement();// ����Statement����
                                stmt.executeUpdate(sql);//ִ��sql���
                                save = null;//���
                                JOptionPane.showMessageDialog(this, "�޸ĳɹ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
                                �κ�.setText("");
                                ����.setText("");
                                stmt.close();//�ر�Statement
                                con.close();//�ر�Connection
                            } catch (SQLException e1) {
                                System.out.print("SQL Exception occur.Message is:" + e1.getMessage());
                            }
                        }

                    }
                }
            }