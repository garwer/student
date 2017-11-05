package course.dao.test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.xml.crypto.Data;

import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


import course.util.ConnectDB;
import course.util.JSONResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class testaaa {


	public static void main(String[] args) throws SQLException, IOException { 
		Date date = new Date();
		Calendar   calendar   =   new   GregorianCalendar(); 
	    calendar.setTime(date); 
	    calendar.add(calendar.DATE,-16);
	    date=calendar.getTime();
	   // System.out.println(calendar);
	    System.out.println(date);
		//System.out.println(date.getTime());
	/*	PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		String result = "";
		Connection conn = null;
		JSONArray result3JsonArray = null;
		conn = ConnectDB.getConn();
		String sql = "SELECT C.POOL_ID,C.JNDI_NAME,C.SYSTEM_ID, T.TREE_LABEL AS NAME, D.USER_NAME, D.PASSWORD,D.JDBC_URL FROM JDBC_GATHER_CONNECTION_POOLS C, JDBC_GATHER_DS D,BASIC_TREE_NODE T WHERE C.POOL_ID = D.POOL_ID AND  T.TREE_ID = C.SYSTEM_ID ORDER BY C.POOL_ID DESC";
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();		
		while (rs.next()) {
			String aString = rs.getString("PASSWORD");
			System.out.println(aString);
		}
		result3JsonArray = JSONResult.toJSON(rs);
		System.out.println(result3JsonArray);

		System.out.println(result3JsonArray.getJSONObject(2).get("PASSWORD").toString().substring(16));
		//在这边改变值
		System.out.println(result3JsonArray.getJSONObject(0).get("PASSWORD").toString());
		for (int i = 0; i < result3JsonArray.size(); i++) {
			result3JsonArray.getJSONObject(i).put("PASSWORD", result3JsonArray.getJSONObject(i).get("PASSWORD").toString().substring(16));
		}
		String base = getBase64("aaa");
		System.out.println("base==================:" + base);
		System.out.println(getFromBase64("YWFh"));
		//AESSecurity.decrypt(Passwd,"ffitsm0123456789");
		//result3JsonArray.getJSONObject(0).put("PASSWORD", AESSecurity.decrypt(result3JsonArray.getJSONObject(0).get("PASSWORD").toString()),"ffitsm0123456789");

		System.out.println(result3JsonArray);
		//0C92B2D9D2436A8F3  0C92B2D9D2436A8F3  0C92B2D9D2436A8F3 u00027EF0AE5FC74010C92B2D9D2436A8F3 u00027EF0AE5FC74010C92B2D9D2436A8F3
		ConnectDB.closeDB(conn,ps,rs);*/		
		String a = "{590000022063=-1.0, 590000021883=-1.0, 590000022002=-1.0, 590000021577=-1.0, 590000022082=-1.0, 590000022083=-1.0, 590000022080=-1.0, 590000022081=-1.0, 590000022050=-1.0, 590000022077=-1.0, 590000022076=-1.0, 590000022404=-1.0, 590000022079=-1.0, 590000022078=-1.0, 590000022072=-1.0, 590000022403=-1.0, 590000022068=-1.0, 590000022065=-1.0, 590000022064=-1.0, 590000022067=-1.0, 590000022066=-1.0}";
		JSONObject jsonObject = JSONObject.fromObject(a);
		System.out.println(jsonObject);
	    Double aDouble  = 12.1;
	    
	    Double bDouble = 0.02;
	    //BigDecimal  b  =  new   BigDecimal(aDouble);  
	    aDouble = new  BigDecimal(bDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    System.out.println(aDouble);
	    
	/*    System.out.println(aDouble);
	    Object aObject = 1;
	    Object bObject = 1.02;
	    System.out.println(Integer.valueOf(aObject.toString()));
	    System.out.println(aDouble > bDouble);
	    System.out.println((Double.valueOf(aObject.toString())) < Double.valueOf(bObject.toString()));
	    Double kpiValue = 0.85;
	    System.out.println(kpiValue.toString());
	    Double ab = 0.7080;
	    System.out.println(ab.toString());
	    System.out.println();
	    System.out.println(Double.valueOf(null));*/
	    double aDouble2 = 80.0;
	    int i = (int)aDouble2; 
	    System.out.println(String.valueOf(i));
	 } 
	
	//加密
    public static String getBase64(String str) {  
        byte[] b=null;  
        String s=null;  
        try {  
            b = str.getBytes("gbk");  
       } catch (UnsupportedEncodingException e) {  
           e.printStackTrace();  
        }  
        if(b!=null){  
           s=new BASE64Encoder().encode(b);  
       }  
        return s;  
    } 
    
    public static String getFromBase64(String s) {    
        byte[] b = null;    
        String result = null;    
       if (s != null) {    
            BASE64Decoder decoder = new BASE64Decoder();    
            try {    
                b = decoder.decodeBuffer(s);    
               result = new String(b, "gbk");    
            } catch (Exception e) {    
                e.printStackTrace();    
            }    
       }    
        return result;    
    }   


}


