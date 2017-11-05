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
	 * ����ת�������ͷ���ת�����·��
	 * @param kId
	 * @param attachId
	 * @param requestType
	 * @return
	 */
	public String converAction() { //kId������չ UPD BY LINJW
		Converter converter = new Converter();
		String retsString="";
		String documentPath = "D:\\up/document/1487755963893_1043966930.doc";  //��д·��
		retsString = converAction(converter,documentPath);
		return retsString;
	}
	
	
	
	/**
	 * ת���ļ�������swf�ļ���·��
	 * @param converter
	 * @param attachPath//Ҫת���ĸ���·��
	 * @return
	 */
	private String converAction(Converter converter,String attachPath){
		//StringBuffer xmlReturn = new StringBuffer("<root><error_code>0</error_code>");
		String flag = "";
		if (!attachPath.equals("")) {
			converter.setFileToSwf(attachPath);//����ת������   ����߻�ȡ·��ֵ
			System.out.println("converter��ֵΪ" + converter.getswfPath());
			String flieUrl="";
			String fileType = attachPath.substring(attachPath.lastIndexOf(".")+1,attachPath.length());
			//String zipFileName=attachPath.substring(attachPath.lastIndexOf("/"),attachPath.lastIndexOf("."));//ȥ·����ȥ��׺ȡ�ļ���
			
			if(fileType.equalsIgnoreCase("doc")||fileType.equalsIgnoreCase("xls")||fileType.equalsIgnoreCase("docx")||
				fileType.equalsIgnoreCase("xlsx")||fileType.equalsIgnoreCase("pdf")||fileType.equalsIgnoreCase("txt")
				||fileType.equalsIgnoreCase("ppt")||fileType.equalsIgnoreCase("pptx")){	//�ĵ���
				System.out.println("attachPath:" + attachPath);
				String path=converter.getswfPath();		
				System.out.println("path" + path);
				if (!path.equals("")) {
					flieUrl=retultPach+converter.getswfPath();//��ȡת����·��
					System.out.println("ת�����·��Ϊ" + flieUrl);   //swf�ļ�·����ַ
				}				
/*				
				xmlReturn.append("<rowSet>");
				xmlReturn.append("<FILE_URL>").append(XML.Encode(flieUrl)).append("</FILE_URL>");
				xmlReturn.append("<FILE_TYPE>").append("1").append("</FILE_TYPE>");
				xmlReturn.append("</rowSet>");*/
			}/*else if(fileType.equalsIgnoreCase("zip")){
								
				attachPath=defaultPath+attachPath;//zip�ļ�����·��
				ZipRarUtil zipRarUtil=new ZipRarUtil();				
				Map<String,String> map=zipRarUtil.readZipFileName(attachPath);//����zip�ļ�				
				xmlReturn.append(readMap(map,zipFileName));
				
			}else if(fileType.equalsIgnoreCase("rar")){
				
				attachPath=defaultPath+attachPath;//rar�ļ�����·��
				ZipRarUtil zipRarUtil=new ZipRarUtil();				
				Map<String,String> map=zipRarUtil.readRarFileName(attachPath);//����rar�ļ�				
				xmlReturn.append(readMap(map,zipFileName));

			}*/else if(
				fileType.equalsIgnoreCase("png")||fileType.equalsIgnoreCase("gif")||fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg")||fileType.equalsIgnoreCase("bmp")){//ͼƬ��ֱ�ӷ���
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
		System.out.println("flag=="+ flag);  //�ڴ˴���ӡ
		return flag;		
	}
			
	/**
	 * ��ʼ��·��
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
	 * ��ȡ�����õ�ѹ���ļ���ƴ��·��
	 * @param map
	 * @param zipFileName
	 * @return
	 */
/*	public String readMap(Map<String,String> map,String zipFileName){
		String fileName="";
		String fileType="";
		String fileUrl="";
		String fileTypeflag="";//1doc�� 2ͼƬ3����
		//StringBuffer xmlReturn = new StringBuffer("");
		String flag = "";
		for(Map.Entry<String, String> entry:map.entrySet()){
			fileName=entry.getValue();// ��ȡ���ļ�������
			fileType=fileName.substring(fileName.lastIndexOf(".")+1);
			
			if(fileType.equalsIgnoreCase("doc")||fileType.equalsIgnoreCase("xls")||fileType.equalsIgnoreCase("docx")||
			   fileType.equalsIgnoreCase("xlsx")||fileType.equalsIgnoreCase("pdf")||fileType.equalsIgnoreCase("txt")
			   ||fileType.equalsIgnoreCase("ppt")||fileType.equalsIgnoreCase("pptx")){	
				
				fileTypeflag="1";
				fileUrl=retultPach+swfOutPath+zipFileName+"/"+entry.getKey()+".swf";
				
			}else if(fileType.equalsIgnoreCase("png")||fileType.equalsIgnoreCase("gif")||fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg")||fileType.equalsIgnoreCase("bmp")) {
				fileTypeflag="2";
				fileUrl=retultPach+swfOutPath+zipFileName+"/"+entry.getKey()+"."+fileType;
			}else {//�������Ͳ�Ԥ��
				fileTypeflag="3";
				fileUrl="";				
			}
			xmlReturn.append("<rowSet>");
			xmlReturn.append("<FILE_TYPE>").append(fileTypeflag).append("</FILE_TYPE>");		
			xmlReturn.append("<FILE_NAME>").append(XML.Encode(fileName)).append("</FILE_NAME>");
			xmlReturn.append("<FILE_URL>").append(XML.Encode(fileUrl)).append("</FILE_URL>");
			xmlReturn.append("</rowSet>");
			flag = fileName;
			System.err.println("flag��ֵΪ" + flag);
		  } 	
		return flag;
	}*/
}
