package javasrc;

import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class Login extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /* ������Ӧͷ����ajax������� */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        /*��ȡ��ǰ���ں�ʱ��*/
        Calendar time = Calendar.getInstance();
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DATE);
        int hour = time.get(Calendar.HOUR);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
        String addr = request.getParameter("address");
        //String ID = request.getParameter("ID");
        String Name = request.getParameter("Name");
        System.out.println(Name + "��" + year + "��" + month + "��" + day + "��" + hour + ":" +
        minute + ":" + second + "��" + addr + "ǩ��");

        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException classnotfoundexception) {
			classnotfoundexception.printStackTrace();
		}
		try {
			//�������ݿ⣬�˿���3306���˺ţ�������000000����Щ�����Ը���
			String url="jdbc:mysql://localhost:3306/hello";
			String usrname="root";
			String psw="000000";
			Connection conn = DriverManager
					.getConnection(url,usrname,psw);
			Statement statement = conn.createStatement();
			System.out.println("Connect Database Ok������");
			
			//ִ�������䣬���ؽ����
			//ע���ַ��ͱ���Ҫ�����Ű�������
			String sql = "insert into t_login "+
			"(name,year,month,day,hour,minute,second,address) values (" 
					+ " ' "+Name
					+ " ', " + year
					+ ", " + month
					+ "," + day
					+ "," + hour
					+ "," + minute
					+ "," + second
					+ ", ' " + addr +" ' "

					+ " )";
			System.out.println(sql);
			try{
				statement.executeUpdate(sql);
			}catch(SQLException sqlexception){
				sqlexception.printStackTrace();
			}finally {
				//�ر����ݿ�
				statement.close();
				conn.close();
				Date date = new Date();
				System.out.println("Database Closed������"+ date.toString());
								
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		}
  }
    
}
