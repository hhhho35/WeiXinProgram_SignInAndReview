package javasrc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class AdminLogin extends HttpServlet{
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
		   request.setCharacterEncoding("UTF-8");
	       response.setContentType("text/html;charset=UTF-8");
			
			request.setCharacterEncoding("UTF-8");
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
				
				
				//ִ�в�ѯ��䣬���ؽ����
				StringBuffer sql = new StringBuffer("select * from t_login ");

				System.out.println(sql);
				try{
					ResultSet rs = statement.executeQuery(sql.toString());
				    JSONArray jsonData = JSONArray.fromObject(convertList(rs));      //��ת��List��ʽ����ת��json��ʽ

		            System.out.println(jsonData.toString());

		            PrintWriter out = response.getWriter();    //��json���ݴ��ݵ�ǰ�ˣ�����ǰ����ajax����
		            out.print(jsonData);

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
	  
	  private static List convertList(ResultSet rs) throws SQLException {
	        List list = new ArrayList();
	        ResultSetMetaData md = rs.getMetaData();
	        int columnCount = md.getColumnCount();
	        while (rs.next()) {
	            Map rowData = new HashMap();
	            for (int i = 1; i <= columnCount; i++) {
	                rowData.put(md.getColumnName(i), rs.getObject(i));
	            }
	            list.add(rowData);
	        }
	        return list;
	    }
}