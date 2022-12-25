import java.sql.*;
import javax.swing.*;
import java.util.*;

public class getSC extends JFrame{
    Vector rowData = null;
    Vector columnNames = null;
    String sql = null;
    JTable table = null;
    JScrollPane scrollPane = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    public getSC(String sql1) {
        super("��ѯ�γ���Ϣ");
        columnNames = new Vector();
        columnNames.add("�κ�");
        columnNames.add("����");
        columnNames.add("ѧ��");
        columnNames.add("ѧ��");
        columnNames.add("����");
        columnNames.add("�ɼ�");
        rowData = new Vector();
        sql = sql1;
        try {
            Connection con = Tools.CONN();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString("Cno"));
                row.add(rs.getString("Cname"));
                row.add(rs.getString("Ccredit"));
                row.add(rs.getString("Sno"));
                row.add(rs.getString("Sname"));
                row.add(rs.getString("grade"));
                rowData.add(row);
            }
            table = new JTable(rowData, columnNames);
            scrollPane = new JScrollPane(table);
            this.add(scrollPane);
            this.setSize(400, 300);
            this.setVisible(true);
        } catch (SQLException e) {
            System.out.print("SQL Exception occur.Message is:" + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//       JFrame a= new getSC("select distinct* from course,sc,student where sc.Sno=student.Sno and course.Cno=sc.Cno");
//    }

           
}
