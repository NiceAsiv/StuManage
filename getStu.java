import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
public class getStu extends JFrame {
   Vector rowData = null;// 定义一个Vector对象
   Vector columnNames = null;// 列名
   JTable table=null; // 定义一个JTable对象
   String sql=null;// 定义一个String对象
    JScrollPane jsp=null;
    PreparedStatement pstmt=null;// 定义一个PreparedStatement对象
    ResultSet rs=null;
    public getStu(String sql1) {
        super("西电学生信息");// 设置窗口标题
        columnNames = new Vector();// 创建一个Vector对象
        columnNames.add("学号");// 向Vector对象中添加数据
        columnNames.add("姓名");// 向Vector对象中添加数据
        columnNames.add("系别");// 向Vector对象中添加数据
        columnNames.add("性别");// 向Vector对象中添加数据
        rowData = new Vector();// 创建一个Vector对象
        sql = sql1;// 将sql语句赋值给sql变量
        try {
            Connection con = Tools.CONN();// 调用Tools类中的CONN方法
            pstmt = con.prepareStatement(sql);// 创建一个PreparedStatement对象
            rs = pstmt.executeQuery();// 创建一个ResultSet对象
            while (rs.next()) {
                Vector row = new Vector();// 创建一个Vector对象
                row.add(rs.getString("Sno"));// 向Vector对象中添加数据
                row.add(rs.getString("Sname"));// 向Vector对象中添加数据
                row.add(rs.getString("Sdept"));// 向Vector对象中添加数据
                row.add(rs.getString("Ssex"));// 向Vector对象中添加数据
                rowData.add(row);// 向Vector对象中添加数据
    }
    table = new JTable(rowData, columnNames);// 创建一个JTable对象
    jsp = new JScrollPane(table);// 创建一个JScrollPane对象
    table.setEnabled(false);// 设置JTable表格不可编辑
    this.add(jsp);// 将JScrollPane对象添加到窗口中
    this.setSize(400, 300);// 设置窗口大小
    this.setVisible(true);// 设置窗口可见
}catch (SQLException e1) {
    System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
    }
}
}