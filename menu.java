import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class menu extends JFrame implements ActionListener {

    JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("ѧ������");
    JMenuItem mi1 = new JMenuItem("���ѧ��");
    JMenuItem mi2 = new JMenuItem("ɾ��ѧ��");
    JMenuItem mi11 = new JMenuItem("�޸�ѧ��");
    JMenu m2 = new JMenu("�γ̹���");
    JMenuItem mi3 = new JMenuItem("��ӿγ�");
    JMenuItem mi4 = new JMenuItem("ɾ���γ�");
    JMenuItem mi12 = new JMenuItem("�޸Ŀγ�");
    JMenu m3 = new JMenu("ѡ�ι���");
    JMenuItem mi5 = new JMenuItem("ѡ��");
    JMenuItem mi6 = new JMenuItem("�˿�");
    JMenu m4 = new JMenu("�ɼ�����");
    JMenuItem mi7 = new JMenuItem("��ѯ�ɼ�");
    JMenuItem mi8 = new JMenuItem("�޸ĳɼ�");
    JMenu m5 = new JMenu("��ѯ����");
    JMenuItem mi9 = new JMenuItem("���ݿ��ѯ");
    JMenuItem mi10 = new JMenuItem("ϵͳ�˳�");
    Font t = new Font("sans-serif", Font.BOLD, 12);
    JPanel p = null;
    CardLayout cl = null;

    public menu() {
        this.setTitle("ѧ��ѡ�ι���ϵͳ");
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
        JLabel l = new JLabel("��ӭʹ��ѡ��ϵͳpowered by Asiv",JLabel.CENTER);
        // JLabel l1=new JLabel("powered by Asiv",JLabel.CENTER);
        l.setFont(new Font("sans-serif", Font.BOLD, 16));
        l.setHorizontalTextPosition(SwingConstants.CENTER);
        // l1.setHorizontalTextPosition(SwingConstants.CENTER);
        // ����¼�
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
         UpdataSC updataSC = new UpdataSC(); // �޸ĳɼ�
         getgrade Getgrade = new getgrade();
        Search search=new Search();//mi9
        //
        p.add(l, "��ӭ����");//add (string,component)Ҫ��JPanel����ʵ��CardLayout���֣���Ҫ�������ӵ�JPanel���棬Ȼ������ڷ�����ActionLisetener����ʵ�ֶ�������������л�
        // p.add(l1,"��ӭ����");
        p.add(addStu, "���ѧ������");
        p.add(addC, "��ӿγ̽���");
        p.add(addSC, "���ѡ�ν���");
        p.add(delStu, "ɾ��ѧ������");
        p.add(delC, "ɾ���γ̽���");
        p.add(delSC, "ɾ��ѡ�ν���");
        p.add(updataC, "�޸Ŀγ̽���");
        p.add(updataStu, "�޸�ѧ������");
        p.add(updataSC, "�޸ĳɼ�����");//mi8
        p.add(search,"ѧ�����ݿ��ѯ����");
        p.add(Getgrade,"�ɼ���ѯ����");//mi7
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
            cl.show(p, "���ѧ������");
        }
        if (e.getSource() == mi2) {
            cl.show(p, "ɾ��ѧ������");
        }
        if (e.getSource() == mi3) {
            cl.show(p, "��ӿγ̽���");
        }
        if (e.getSource() == mi4) {
            cl.show(p, "ɾ���γ̽���");
        }
        if (e.getSource() == mi12) {
            cl.show(p, "�޸Ŀγ̽���");
        }
        if (e.getSource() == mi5)
        {
            cl.show(p, "���ѡ�ν���");
        }
        if (e.getSource() == mi6) {
            cl.show(p, "ɾ��ѡ�ν���");
        }
        if (e.getSource() == mi7) {
            cl.show(p, "�ɼ���ѯ����");
        }
        if (e.getSource() == mi8) {
            cl.show(p, "�޸ĳɼ�����");
        }
        if (e.getSource() == mi10) {
            System.exit(0);
        }
        if (e.getSource() == mi9) {
          cl.show(p,"ѧ�����ݿ��ѯ����");
        }
        if (e.getSource() == mi11) {
            cl.show(p, "�޸�ѧ������");
        }
    }
}
