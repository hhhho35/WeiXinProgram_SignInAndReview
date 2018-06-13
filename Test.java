package javasrc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


public class Test extends HttpServlet{
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
	           throws ServletException, IOException {
		   request.setCharacterEncoding("UTF-8");
	       response.setContentType("text/html;charset=UTF-8");
	       //System.out.println("55");

			String username0,password0;
			
			username0=request.getParameter("username");
			password0=request.getParameter("password");
			System.out.println(username0);
			System.out.println(password0);

			boolean success;
			success = false;
			
			request.setCharacterEncoding("UTF-8");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException classnotfoundexception) {
				classnotfoundexception.printStackTrace();
			}
			try {
				//链接数据库，端口是3306，账号，密码是000000，这些都可以更改
				String url="jdbc:mysql://localhost:3306/hello";
				String usrname="root";
				String psw="000000";
				Connection conn = DriverManager
						.getConnection(url,usrname,psw);
				Statement statement = conn.createStatement();
				System.out.println("Connect Database Ok！！！");
				
				//执行查询语句，返回结果集
				String mysqlStatement="select * from t_usr_psw";
				ResultSet rs = statement
						.executeQuery(mysqlStatement);
				while (rs.next()) {
					if(username0.equals(rs.getString("name"))) {
						if(password0.equals(rs.getString("password"))) {							
							success = true;
							System.out.println(success);
						}
						else continue;
					}
				}
				
				statement.close();
				conn.close();
				System.out.println("Database Closed！！！");
				
				//向前端返回json，是否执行成功
				String isSuccess = (success==false) ? "0" : "1";
				//Map param = new HashMap();
				JSONObject json = new JSONObject();
		        PrintWriter write = null;
		        
		        try {
					json.put("success", isSuccess);
					write = response.getWriter(); 
			        write.print(json); 
			        write.flush();
			        write.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException sqlexception) {
				sqlexception.printStackTrace();
			}
	  }
}
