import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Search extends JPanel implements ActionListener {//����������ģ��������صı�
    JTextField  ѧ��,����,ϵ��,�Ա�;
    JButton ��ѯѧ��;
    JTextField �κ�,����;
    JButton ��ѯ�γ�;
    JTextField �κ�1,ѧ��1,�ɼ�;
    JButton ��ѯѡ��;
    public Search() {
        ѧ�� = new JTextField(10);
        ���� = new JTextField(10);
        ϵ�� = new JTextField(10);
        �Ա� = new JTextField(10);
        ��ѯѧ�� = new JButton("��ѯѧ����Ϣ");
        ��ѯѧ��.addActionListener(this); // ��Ӽ�����
        �κ� = new JTextField(10);
        ���� = new JTextField(10);
        ��ѯ�γ� = new JButton("��ѯ�γ���Ϣ");
        ��ѯ�γ�.addActionListener(this); // ��Ӽ�����
        �κ�1 = new JTextField(10);
        ѧ��1 = new JTextField(10);
        �ɼ� = new JTextField(10);
        ��ѯѡ�� = new JButton("��ѯѡ����Ϣ");
        ��ѯѡ��.addActionListener(this); // ��Ӽ�����
        Box box1 = Box.createHorizontalBox();// ���box
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        Box box6 = Box.createHorizontalBox();
        box1.add(new JLabel("ѧ��:", JLabel.CENTER));
        box1.add(ѧ��);
        box1.add(new JLabel("����:", JLabel.CENTER));
        box1.add(����);
        box1.add(new JLabel("ϵ��:", JLabel.CENTER));
        box1.add(ϵ��);
        box1.add(new JLabel("�Ա�:", JLabel.CENTER));
        box1.add(�Ա�);
        box2.add(��ѯѧ��);
        box3.add(new JLabel("�κ�:", JLabel.CENTER));
        box3.add(�κ�);
        box3.add(new JLabel("����:", JLabel.CENTER));
        box3.add(����);
        box4.add(��ѯ�γ�);
        box5.add(new JLabel("�κ�:", JLabel.CENTER));
        box5.add(�κ�1);
        box5.add(new JLabel("ѧ��:", JLabel.CENTER));
        box5.add(ѧ��1);
        box5.add(new JLabel("�ɼ�:", JLabel.CENTER));
        box5.add(�ɼ�);
        box6.add(��ѯѡ��);
        Box boxH1 = Box.createVerticalBox();// ����box
        boxH1.add(box1);
        boxH1.add(box2);
        boxH1.add(Box.createVerticalGlue());
        Box boxH2 = Box.createVerticalBox();
        boxH2.add(box3);
        boxH2.add(box4);
        boxH2.add(Box.createVerticalGlue());
        Box boxH3 = Box.createVerticalBox();
        boxH3.add(box5);
        boxH3.add(box6);
        boxH3.add(Box.createVerticalGlue());
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel.add(boxH1);
        panel1.add(boxH2);
        panel2.add(boxH3);
        setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, panel1);
        add(splitPane, BorderLayout.CENTER);
        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane, panel2);
        add(splitPane1, BorderLayout.CENTER);
        validate();
    }
        public void actionPerformed (ActionEvent e)
        {
            String sql=null;
            ResultSet rs=null;
            getSC getsc=null;
            getC getc=null;
            getStu getstu=null; //ʵ������
            if (e.getSource() == ��ѯѧ��) {
                if(ѧ��.getText().equals("")&&����.getText().equals("")&&ϵ��.getText().equals("")&&�Ա�.getText().equals(""))
                {
                    sql="select * from student";
                }
                else
                {
                    sql="select * from student where Sno like '%"+ѧ��.getText()+"%' or Sname like '%"+����.getText()+"%' and Sdept like '%"+ϵ��.getText()+"%' and Ssex like '%"+�Ա�.getText()+"%'";
                }
                getstu=new getStu(sql);
            }
            else if (e.getSource() == ��ѯ�γ�) {
                if (�κ�.getText().equals("") && ����.getText().equals("")) {
                    sql = "select * from course";
                } else {
                    if (�κ�.getText().equals("")) {
                        sql = "select * from course where Cname like'%" + ����.getText() + "%'";
                    } else {
                        if (ϵ��.getText().equals("")) {
                            sql = "select * from course where Cno like'%" + �κ�.getText() + "%'";
                        } else {
                            sql = "select * from course where  Cno like'%" + �κ�.getText() + "%' and Cname like'%" + ����.getText() + "%'";
                        }
                    }
                    getc = new getC(sql);
                }
            }
            else if(e.getSource() == ��ѯѡ��)
            {
                if(�κ�1.getText().equals("")&&ѧ��1.getText().equals("")&&�ɼ�.getText().equals("")){
                    sql="select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade from sc,course,student where course.Cno=SC.Cno and student.Sno=sc.Sno";
                }
                else{
                    if(�κ�1.getText().equals("")){
                        sql="select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade  from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Sname like'%"+ѧ��1.getText()+"%'";
                    }
                    else{
                        if(ѧ��1.getText().equals("")){
                            sql="select select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade  from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Cname like'%"+�κ�1.getText()+"%'";
                        }
                        else{
                            if(�ɼ�.getText().equals("")){
                                sql="select select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Sname like'%"+ѧ��1.getText()+"%' and Cname like'%"+�κ�1.getText()+"%'";
                            }
                            else{
                                sql="select select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Sname like'%"+ѧ��1.getText()+"%' and Cname like'%"+�κ�1.getText()+"%' and course like'%"+�ɼ�.getText()+"%'";
                            }
                        }
                    }
                }
                System.out.println(sql);
                getsc=new getSC(sql);
            }
        }

    public static void main(String[] args) {
        new Search();
    }
    }
