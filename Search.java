import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Search extends JPanel implements ActionListener {//以任意条件模糊查找相关的表
    JTextField  学号,姓名,系别,性别;
    JButton 查询学生;
    JTextField 课号,课名;
    JButton 查询课程;
    JTextField 课号1,学号1,成绩;
    JButton 查询选课;
    public Search() {
        学号 = new JTextField(10);
        姓名 = new JTextField(10);
        系别 = new JTextField(10);
        性别 = new JTextField(10);
        查询学生 = new JButton("查询学生信息");
        查询学生.addActionListener(this); // 添加监听器
        课号 = new JTextField(10);
        课名 = new JTextField(10);
        查询课程 = new JButton("查询课程信息");
        查询课程.addActionListener(this); // 添加监听器
        课号1 = new JTextField(10);
        学号1 = new JTextField(10);
        成绩 = new JTextField(10);
        查询选课 = new JButton("查询选课信息");
        查询选课.addActionListener(this); // 添加监听器
        Box box1 = Box.createHorizontalBox();// 横放box
        Box box2 = Box.createHorizontalBox();
        Box box3 = Box.createHorizontalBox();
        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createHorizontalBox();
        Box box6 = Box.createHorizontalBox();
        box1.add(new JLabel("学号:", JLabel.CENTER));
        box1.add(学号);
        box1.add(new JLabel("姓名:", JLabel.CENTER));
        box1.add(姓名);
        box1.add(new JLabel("系别:", JLabel.CENTER));
        box1.add(系别);
        box1.add(new JLabel("性别:", JLabel.CENTER));
        box1.add(性别);
        box2.add(查询学生);
        box3.add(new JLabel("课号:", JLabel.CENTER));
        box3.add(课号);
        box3.add(new JLabel("课名:", JLabel.CENTER));
        box3.add(课名);
        box4.add(查询课程);
        box5.add(new JLabel("课号:", JLabel.CENTER));
        box5.add(课号1);
        box5.add(new JLabel("学号:", JLabel.CENTER));
        box5.add(学号1);
        box5.add(new JLabel("成绩:", JLabel.CENTER));
        box5.add(成绩);
        box6.add(查询选课);
        Box boxH1 = Box.createVerticalBox();// 竖放box
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
            getStu getstu=null; //实例化类
            if (e.getSource() == 查询学生) {
                if(学号.getText().equals("")&&姓名.getText().equals("")&&系别.getText().equals("")&&性别.getText().equals(""))
                {
                    sql="select * from student";
                }
                else
                {
                    sql="select * from student where Sno like '%"+学号.getText()+"%' or Sname like '%"+姓名.getText()+"%' and Sdept like '%"+系别.getText()+"%' and Ssex like '%"+性别.getText()+"%'";
                }
                getstu=new getStu(sql);
            }
            else if (e.getSource() == 查询课程) {
                if (课号.getText().equals("") && 课名.getText().equals("")) {
                    sql = "select * from course";
                } else {
                    if (课号.getText().equals("")) {
                        sql = "select * from course where Cname like'%" + 课名.getText() + "%'";
                    } else {
                        if (系别.getText().equals("")) {
                            sql = "select * from course where Cno like'%" + 课号.getText() + "%'";
                        } else {
                            sql = "select * from course where  Cno like'%" + 课号.getText() + "%' and Cname like'%" + 姓名.getText() + "%'";
                        }
                    }
                    getc = new getC(sql);
                }
            }
            else if(e.getSource() == 查询选课)
            {
                if(课号1.getText().equals("")&&学号1.getText().equals("")&&成绩.getText().equals("")){
                    sql="select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade from sc,course,student where course.Cno=SC.Cno and student.Sno=sc.Sno";
                }
                else{
                    if(课号1.getText().equals("")){
                        sql="select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade  from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Sname like'%"+学号1.getText()+"%'";
                    }
                    else{
                        if(学号1.getText().equals("")){
                            sql="select select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade  from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Cname like'%"+课号1.getText()+"%'";
                        }
                        else{
                            if(成绩.getText().equals("")){
                                sql="select select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Sname like'%"+学号1.getText()+"%' and Cname like'%"+课号1.getText()+"%'";
                            }
                            else{
                                sql="select select sc.Cno,Cname,Ccredit,sc.Sno,Sname,grade from sc,course,student where course.Cno=sc.Cno and student.Sno=sc.Sno and Sname like'%"+学号1.getText()+"%' and Cname like'%"+课号1.getText()+"%' and course like'%"+成绩.getText()+"%'";
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
