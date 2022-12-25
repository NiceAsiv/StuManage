import java.util.Vector;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class getC extends JFrame{
    Vector rowData = null;
    Vector columnNames = null;
    JTable table = null;
    JScrollPane jsp = null;
    String sql = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    public getC(String sql1) {
        super("��ѯ�γ���Ϣ");
        columnNames = new Vector();
        columnNames.add("�κ�");
        columnNames.add("����");
        columnNames.add("ѧ��");
        rowData = new Vector();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);// ���ô��ڿɼ�
        sql = sql1;// ��ȡ��ѯ���
        try {

            Connection con = Tools.CONN();// ����Tools���е�CONN����
            pstmt = con.prepareStatement(sql);// ����һ��PreparedStatement����
            rs = pstmt.executeQuery();// ����һ��ResultSet����
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString("Cno"));
                row.add(rs.getString("Cname"));
                row.add(rs.getString("Ccredit"));
                rowData.add(row);
            }
            table = new JTable(rowData, columnNames);
            jsp = new JScrollPane(table);
            this.add(jsp);
            this.setSize(400, 300);
            this.setVisible(true);
        } catch (SQLException e) {
            System.out.print("SQL Exception occur.Message is:"+e.getMessage());
        }
            
}

//    public static void main(String[] args) {
//        JFrame a=new getC("sdasd");
//        JFrame b=new getStu("fgdgdfg");
//    }
}
