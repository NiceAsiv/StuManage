import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
public class getStu extends JFrame {
   Vector rowData = null;// ����һ��Vector����
   Vector columnNames = null;// ����
   JTable table=null; // ����һ��JTable����
   String sql=null;// ����һ��String����
    JScrollPane jsp=null;
    PreparedStatement pstmt=null;// ����һ��PreparedStatement����
    ResultSet rs=null;
    public getStu(String sql1) {
        super("����ѧ����Ϣ");// ���ô��ڱ���
        columnNames = new Vector();// ����һ��Vector����
        columnNames.add("ѧ��");// ��Vector�������������
        columnNames.add("����");// ��Vector�������������
        columnNames.add("ϵ��");// ��Vector�������������
        columnNames.add("�Ա�");// ��Vector�������������
        rowData = new Vector();// ����һ��Vector����
        sql = sql1;// ��sql��丳ֵ��sql����
        try {
            Connection con = Tools.CONN();// ����Tools���е�CONN����
            pstmt = con.prepareStatement(sql);// ����һ��PreparedStatement����
            rs = pstmt.executeQuery();// ����һ��ResultSet����
            while (rs.next()) {
                Vector row = new Vector();// ����һ��Vector����
                row.add(rs.getString("Sno"));// ��Vector�������������
                row.add(rs.getString("Sname"));// ��Vector�������������
                row.add(rs.getString("Sdept"));// ��Vector�������������
                row.add(rs.getString("Ssex"));// ��Vector�������������
                rowData.add(row);// ��Vector�������������
    }
    table = new JTable(rowData, columnNames);// ����һ��JTable����
    jsp = new JScrollPane(table);// ����һ��JScrollPane����
    table.setEnabled(false);// ����JTable��񲻿ɱ༭
    this.add(jsp);// ��JScrollPane������ӵ�������
    this.setSize(400, 300);// ���ô��ڴ�С
    this.setVisible(true);// ���ô��ڿɼ�
}catch (SQLException e1) {
    System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
    }
}
}