package course.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConnectDB {
	//IPD 127.0.0.1指的是本机 一般用来测试用
	//mysql
	static String url = "jdbc:mysql://localhost:3306/test";  //mysql
	//static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  //oracle
	//static String user = "system";
	static String user = "root";
	static String password = "linjiawei";
	
/*	static String url = "jdbc:oracle:thin:@192.168.17.238:1521:devdb2";
	static String user = "bosswg_kf";
	static String password = "bosswg";*/
	//获取数据库连接
	public static Connection getConn(){
		Connection conn = null;
		try {
			//加载驱动 mysql加载驱动的方式
			//Class.forName("com.mysql.jdbc.Driver");
			//oracle加载驱动的方式
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("数据库连接中...");
			//获取数据库连接
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return conn;	
	}
	//关闭数据库
	public static void closeDB(Connection conn, PreparedStatement ps, ResultSet rs) {
		try{
			if(null!=rs)
				rs.close();
			if(null!= ps)
				ps.close();
			if(null!=conn)
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("数据库已关闭");
	}
	
	//测试数据库连接
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = ConnectDB.getConn();
		System.out.println(1);
		ConnectDB.closeDB(conn,ps,rs);
	}
}
