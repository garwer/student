package course.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class ConverterDao {	
	
	private static String swfOutPath="D:";
	private static String defaultPath;
	private static String retultPach="/servlet/DisplayChart?filename=";
	
	public ConverterDao() {
		getDefaultPath();
	}
	
	
	
	/**
	 * 接收转换参数和返回转换后的路径
	 * @param kId
	 * @param attachId
	 * @param requestType
	 * @return
	 */
	public String converAction() { //kId留作扩展 UPD BY LINJW
		Converter converter = new Converter();
		String retsString="";
		String documentPath = "D:\\up/document/1487755963893_1043966930.doc";  //填写路径
		retsString = converAction(converter,documentPath);
		return retsString;
	}
	
	
	
	/**
	 * 转换文件并返回swf文件的路径
	 * @param converter
	 * @param attachPath//要转换的附件路径
	 * @return
	 */
	private String converAction(Converter converter,String attachPath){
		//StringBuffer xmlReturn = new StringBuffer("<root><error_code>0</error_code>");
		String flag = "";
		if (!attachPath.equals("")) {
			converter.setFileToSwf(attachPath);//调用转换方法   在这边获取路径值
			System.out.println("converter的值为" + converter.getswfPath());
			String flieUrl="";
			String fileType = attachPath.substring(attachPath.lastIndexOf(".")+1,attachPath.length());
			//String zipFileName=attachPath.substring(attachPath.lastIndexOf("/"),attachPath.lastIndexOf("."));//去路径、去后缀取文件名
			
			if(fileType.equalsIgnoreCase("doc")||fileType.equalsIgnoreCase("xls")||fileType.equalsIgnoreCase("docx")||
				fileType.equalsIgnoreCase("xlsx")||fileType.equalsIgnoreCase("pdf")||fileType.equalsIgnoreCase("txt")
				||fileType.equalsIgnoreCase("ppt")||fileType.equalsIgnoreCase("pptx")){	//文档类
				System.out.println("attachPath:" + attachPath);
				String path=converter.getswfPath();		
				System.out.println("path" + path);
				if (!path.equals("")) {
					flieUrl=retultPach+converter.getswfPath();//获取转换后路径
					System.out.println("转换后的路径为" + flieUrl);   //swf文件路径地址
				}				
/*				
				xmlReturn.append("<rowSet>");
				xmlReturn.append("<FILE_URL>").append(XML.Encode(flieUrl)).append("</FILE_URL>");
				xmlReturn.append("<FILE_TYPE>").append("1").append("</FILE_TYPE>");
				xmlReturn.append("</rowSet>");*/
			}/*else if(fileType.equalsIgnoreCase("zip")){
								
				attachPath=defaultPath+attachPath;//zip文件所在路径
				ZipRarUtil zipRarUtil=new ZipRarUtil();				
				Map<String,String> map=zipRarUtil.readZipFileName(attachPath);//遍历zip文件				
				xmlReturn.append(readMap(map,zipFileName));
				
			}else if(fileType.equalsIgnoreCase("rar")){
				
				attachPath=defaultPath+attachPath;//rar文件所在路径
				ZipRarUtil zipRarUtil=new ZipRarUtil();				
				Map<String,String> map=zipRarUtil.readRarFileName(attachPath);//遍历rar文件				
				xmlReturn.append(readMap(map,zipFileName));

			}*/else if(
				fileType.equalsIgnoreCase("png")||fileType.equalsIgnoreCase("gif")||fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg")||fileType.equalsIgnoreCase("bmp")){//图片类直接访问
				System.out.println("retultPach" + retultPach +"===" + "defaultPath" + defaultPath);
				flieUrl=retultPach+defaultPath+attachPath;
				System.out.println(flieUrl);
			/*	xmlReturn.append("<rowSet>");
				xmlReturn.append("<FILE_URL>").append(XML.Encode(flieUrl)).append("</FILE_URL>");
				xmlReturn.append("<FILE_TYPE>").append("2").append("</FILE_TYPE>");
				xmlReturn.append("</rowSet>");*/
				return "a";
			}							
		}
		System.out.println("flag=="+ flag);  //在此处打印
		return flag;		
	}
			
	/**
	 * 初始化路径
	 */
	public static void getDefaultPath(){
		try {
			//defaultPath=DatabaseUtil.getSysConfigVal("DEFAULT_UPLOAD_PATH");
			defaultPath = "D:\\up";
			if(!defaultPath.equals("")){
				swfOutPath=defaultPath+"swfFile/";
			}else {
				swfOutPath=swfOutPath+"swfFile/";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
		
	/**
	 * 读取遍历好的压缩文件并拼接路径
	 * @param map
	 * @param zipFileName
	 * @return
	 */
/*	public String readMap(Map<String,String> map,String zipFileName){
		String fileName="";
		String fileType="";
		String fileUrl="";
		String fileTypeflag="";//1doc类 2图片3其他
		//StringBuffer xmlReturn = new StringBuffer("");
		String flag = "";
		for(Map.Entry<String, String> entry:map.entrySet()){
			fileName=entry.getValue();// 获取子文件的名字
			fileType=fileName.substring(fileName.lastIndexOf(".")+1);
			
			if(fileType.equalsIgnoreCase("doc")||fileType.equalsIgnoreCase("xls")||fileType.equalsIgnoreCase("docx")||
			   fileType.equalsIgnoreCase("xlsx")||fileType.equalsIgnoreCase("pdf")||fileType.equalsIgnoreCase("txt")
			   ||fileType.equalsIgnoreCase("ppt")||fileType.equalsIgnoreCase("pptx")){	
				
				fileTypeflag="1";
				fileUrl=retultPach+swfOutPath+zipFileName+"/"+entry.getKey()+".swf";
				
			}else if(fileType.equalsIgnoreCase("png")||fileType.equalsIgnoreCase("gif")||fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg")||fileType.equalsIgnoreCase("bmp")) {
				fileTypeflag="2";
				fileUrl=retultPach+swfOutPath+zipFileName+"/"+entry.getKey()+"."+fileType;
			}else {//其他类型不预览
				fileTypeflag="3";
				fileUrl="";				
			}
			xmlReturn.append("<rowSet>");
			xmlReturn.append("<FILE_TYPE>").append(fileTypeflag).append("</FILE_TYPE>");		
			xmlReturn.append("<FILE_NAME>").append(XML.Encode(fileName)).append("</FILE_NAME>");
			xmlReturn.append("<FILE_URL>").append(XML.Encode(fileUrl)).append("</FILE_URL>");
			xmlReturn.append("</rowSet>");
			flag = fileName;
			System.err.println("flag的值为" + flag);
		  } 	
		return flag;
	}*/
}
