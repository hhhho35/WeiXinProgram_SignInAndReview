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
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        /*获取当前日期和时间*/
        Calendar time = Calendar.getInstance();
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DATE);
        int hour = time.get(Calendar.HOUR);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        //获取微信小程序get的参数值并打印
        String addr = request.getParameter("address");
        //String ID = request.getParameter("ID");
        String Name = request.getParameter("Name");
        System.out.println(Name + "于" + year + "年" + month + "月" + day + "日" + hour + ":" +
        minute + ":" + second + "在" + addr + "签到");

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
			
			//执行添加语句，返回结果集
			//注意字符型变量要用引号包裹起来
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
				//关闭数据库
				statement.close();
				conn.close();
				Date date = new Date();
				System.out.println("Database Closed！！！"+ date.toString());
								
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		}
  }
    
}
