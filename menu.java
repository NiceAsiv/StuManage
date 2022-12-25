import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class menu extends JFrame implements ActionListener {

    JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("学生管理");
    JMenuItem mi1 = new JMenuItem("添加学生");
    JMenuItem mi2 = new JMenuItem("删除学生");
    JMenuItem mi11 = new JMenuItem("修改学生");
    JMenu m2 = new JMenu("课程管理");
    JMenuItem mi3 = new JMenuItem("添加课程");
    JMenuItem mi4 = new JMenuItem("删除课程");
    JMenuItem mi12 = new JMenuItem("修改课程");
    JMenu m3 = new JMenu("选课管理");
    JMenuItem mi5 = new JMenuItem("选课");
    JMenuItem mi6 = new JMenuItem("退课");
    JMenu m4 = new JMenu("成绩管理");
    JMenuItem mi7 = new JMenuItem("查询成绩");
    JMenuItem mi8 = new JMenuItem("修改成绩");
    JMenu m5 = new JMenu("查询管理");
    JMenuItem mi9 = new JMenuItem("数据库查询");
    JMenuItem mi10 = new JMenuItem("系统退出");
    Font t = new Font("sans-serif", Font.BOLD, 12);
    JPanel p = null;
    CardLayout cl = null;

    public menu() {
        this.setTitle("学生选课管理系统");
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi11);
        m1.setFont(t);
        m2.add(mi3);
        m2.add(mi4);
        m2.add(mi12);
        m2.setFont(t);
        m3.add(mi5);
        m3.add(mi6);
        m3.setFont(t);
        m4.add(mi7);
        m4.add(mi8);
        m4.setFont(t);
        m5.add(mi9);
        m5.add(mi10);
        m5.setFont(t);
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(m4);
        mb.add(m5);
        this.setJMenuBar(mb);
        JLabel l = new JLabel("欢迎使用选课系统powered by Asiv",JLabel.CENTER);
        // JLabel l1=new JLabel("powered by Asiv",JLabel.CENTER);
        l.setFont(new Font("sans-serif", Font.BOLD, 16));
        l.setHorizontalTextPosition(SwingConstants.CENTER);
        // l1.setHorizontalTextPosition(SwingConstants.CENTER);
        // 点击事件
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);
        mi8.addActionListener(this);
        mi9.addActionListener(this);
        mi10.addActionListener(this);
        mi11.addActionListener(this);
        mi12.addActionListener(this);
        p = new JPanel();
        cl = new CardLayout();
        p.setLayout(cl);
        AddStu addStu = new AddStu();
        AddC addC = new AddC();
        AddSC addSC = new AddSC();
        DelStu delStu = new DelStu();
        DelC delC = new DelC();
        DelSC delSC = new DelSC();
        UpdataC updataC = new UpdataC();
        UpdataStu updataStu = new UpdataStu();
         UpdataSC updataSC = new UpdataSC(); // 修改成绩
         getgrade Getgrade = new getgrade();
        Search search=new Search();//mi9
        //
        p.add(l, "欢迎界面");//add (string,component)要在JPanel里面实现CardLayout布局，需要将组件添加到JPanel里面，然后才能在方法（ActionLisetener）中实现对容器中组件的切换
        // p.add(l1,"欢迎界面");
        p.add(addStu, "添加学生界面");
        p.add(addC, "添加课程界面");
        p.add(addSC, "添加选课界面");
        p.add(delStu, "删除学生界面");
        p.add(delC, "删除课程界面");
        p.add(delSC, "删除选课界面");
        p.add(updataC, "修改课程界面");
        p.add(updataStu, "修改学生界面");
        p.add(updataSC, "修改成绩界面");//mi8
        p.add(search,"学生数据库查询界面");
        p.add(Getgrade,"成绩查询界面");//mi7
        this.add(p, BorderLayout.CENTER);
        setVisible(true);
        setBounds(400, 150, 400, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        validate();

    }
    // public static void main(String[] args) {
    // new menu();
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mi1) {
            cl.show(p, "添加学生界面");
        }
        if (e.getSource() == mi2) {
            cl.show(p, "删除学生界面");
        }
        if (e.getSource() == mi3) {
            cl.show(p, "添加课程界面");
        }
        if (e.getSource() == mi4) {
            cl.show(p, "删除课程界面");
        }
        if (e.getSource() == mi12) {
            cl.show(p, "修改课程界面");
        }
        if (e.getSource() == mi5)
        {
            cl.show(p, "添加选课界面");
        }
        if (e.getSource() == mi6) {
            cl.show(p, "删除选课界面");
        }
        if (e.getSource() == mi7) {
            cl.show(p, "成绩查询界面");
        }
        if (e.getSource() == mi8) {
            cl.show(p, "修改成绩界面");
        }
        if (e.getSource() == mi10) {
            System.exit(0);
        }
        if (e.getSource() == mi9) {
          cl.show(p,"学生数据库查询界面");
        }
        if (e.getSource() == mi11) {
            cl.show(p, "修改学生界面");
        }
    }
}
