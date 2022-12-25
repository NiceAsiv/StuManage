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
        super("查询课程信息");
        columnNames = new Vector();
        columnNames.add("课号");
        columnNames.add("课名");
        columnNames.add("学分");
        rowData = new Vector();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);// 设置窗口可见
        sql = sql1;// 获取查询语句
        try {

            Connection con = Tools.CONN();// 调用Tools类中的CONN方法
            pstmt = con.prepareStatement(sql);// 创建一个PreparedStatement对象
            rs = pstmt.executeQuery();// 创建一个ResultSet对象
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
