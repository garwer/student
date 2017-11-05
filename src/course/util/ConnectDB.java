package course.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ConnectDB {
	//IPD 127.0.0.1ָ���Ǳ��� һ������������
	//mysql
	static String url = "jdbc:mysql://localhost:3306/test";  //mysql
	//static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";  //oracle
	//static String user = "system";
	static String user = "root";
	static String password = "linjiawei";
	
/*	static String url = "jdbc:oracle:thin:@192.168.17.238:1521:devdb2";
	static String user = "bosswg_kf";
	static String password = "bosswg";*/
	//��ȡ���ݿ�����
	public static Connection getConn(){
		Connection conn = null;
		try {
			//�������� mysql���������ķ�ʽ
			//Class.forName("com.mysql.jdbc.Driver");
			//oracle���������ķ�ʽ
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("���ݿ�������...");
			//��ȡ���ݿ�����
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return conn;	
	}
	//�ر����ݿ�
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
		System.out.println("���ݿ��ѹر�");
	}
	
	//�������ݿ�����
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = ConnectDB.getConn();
		System.out.println(1);
		ConnectDB.closeDB(conn,ps,rs);
	}
}
