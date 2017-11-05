package course.util;
//������������ Open the Java build path property page of project 'student'
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;

public class Converter {
		private File pdfFile=null;
		private File swfFile=null;
		private File docFile=null;
		private File initFile=null;//δ����ǰ�ļ�
		private File txtFile=null;
		private static String swfTools;//swftools��װ·��
		private static String swfOutPath="D:/";//swf���·�� ·������ /home/weblogic/up/swfFile
		private static String defaultPath;//�������·��
		
		public Converter() {
			getDefaultPath();// defaultPath swfOutPath��swfTools��ֵ			
			CreateFile(swfOutPath);//�������swf�ļ����ļ���		
		}
		

		/**
		 * file �ϴ��ĸ���·�� ��"/form/201407/1405494758028_-1145448556.zip"
		 * @param fileString
		 */
		public void setFileToSwf(String fileString) {
			ini(fileString);
		}		
		
		/**
		 * �ļ�ת������
		 * 
		 * @param fileString
		 * @throws Exception 
		 */
		private void ini(String fileString){		
			String fileType = fileString.substring(fileString.lastIndexOf(".")+1);//�ļ����͡���׺
			String fileName = fileString.substring(fileString.lastIndexOf("/") + 1,fileString.lastIndexOf("."));//�ļ���		
			String filePath=fileString.substring(0,fileString.lastIndexOf("."));//�ļ�·��
			String swfFileName = swfOutPath+"/" +fileName; //·��+�ļ���		//�ļ���1487755963893_1043966930
			String temp=swfFileName+"_temp";//��Ϊ��ת�ļ���,��ɾ��
			System.out.println("defaultPath+fileString" + defaultPath+fileString);
			System.out.println("�ļ�����Ҫ��Ĭ��·��");
			initFile = new File(fileString); //��ʱfileString��Ϊ����·��
			if (fileType.equalsIgnoreCase("doc")||fileType.equalsIgnoreCase("xls")||fileType.equalsIgnoreCase("docx")||fileType.equalsIgnoreCase("xlsx")
					||fileType.equalsIgnoreCase("ppt")||fileType.equalsIgnoreCase("pptx")){
				docFile=initFile;
				pdfFile = new File(swfFileName + ".pdf");
				swfFile = new File(swfFileName + ".swf");
				doc2pdf();
						
			}else if(fileType.equalsIgnoreCase("txt")){// ���ڴ���TXT�ĵ�ת��ΪPDF��ʽ����,
				//File destDir=new File(temp);
				//if (!destDir.exists()) {//�ļ���·���������ȴ���·��������txt�����
				//	destDir.mkdirs();
				//}
				swfFile = new File(swfFileName + ".swf");
				if (!swfFile.exists()) {
					txtFile = new File(temp + ".txt");		    
					copyFile(initFile,txtFile);
					docFile=txtFile;
					pdfFile = new File(swfFileName + ".pdf");				
					doc2pdf();
				}			
				
			}else if(fileType.equalsIgnoreCase("pdf")){
				pdfFile = new File(defaultPath+filePath + ".pdf");
				swfFile = new File(swfFileName + ".swf");
				pdf2swf(true);
				
			}
		}	
		
		/**
		 * תΪPDF
		 */
		public void doc2pdf(){
			if (!swfFile.exists()) {
				if (docFile.exists()) {
					if (!pdfFile.exists()) {
						OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
						try {
							connection.connect();						
							//DocumentConverter converter = new OpenOfficeDocumentConverter(connection);//����linux ת�������ļ�,�ĳ��������  udp by zhangye 
							DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
							converter.convert(docFile, pdfFile);
							connection.disconnect();						
							System.out.println("****pdfת���ɹ���PDF�����" + pdfFile.getPath());												
						} catch (java.net.ConnectException e) {
							e.printStackTrace();
							System.out.println("****swfת�����쳣��openoffice����δ������****");
						} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
							e.printStackTrace();
							System.out.println("****swfת�����쳣����ȡת���ļ�ʧ��****");
							System.out.println("****pdfת��ʧ�ܣ�PDF�����" + pdfFile.getPath());
						} catch (Exception e) {
							e.printStackTrace();
						}finally { 
				            try{ 
				            	if(connection != null){
				            		connection.disconnect();
				            		connection = null;
				            	}
				            }catch(Exception e){} 
				        } 
					} else {
						System.out.println("****�Ѿ�ת��Ϊpdf������Ҫ�ٽ���ת��****");
					}
					pdf2swf(false);//����ת����pdf
				} else {
					System.out.println("****swfת�����쳣����Ҫת�����ĵ������ڣ��޷�ת��****");
				}
			}		
		}
		
		/**
		 * pdfת���� swf
		 * pdfIsSource false ɾ�����ɵ�pdf true����pdf
		 */
		public void pdf2swf(boolean pdfIsSource){
			Runtime r = Runtime.getRuntime();
			if (!swfFile.exists()) {
				if (pdfFile.exists()) {
					try {
						Process p = r.exec(this.swfTools+" "+ pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9");
						loadStream(p.getInputStream());
						loadStream(p.getErrorStream());
						loadStream(p.getInputStream());
						if (pdfFile.exists()&&!pdfIsSource) {//pdf��ת���������м��ļ� ��ɾ�� ��Դ�ļ�����
							pdfFile.delete();
						}
						
						if (txtFile!=null&&txtFile.exists()) {
							txtFile.delete();
						}
						
						System.out.println("****swfת���ɹ����ļ������"+ swfFile.getPath() + "****");
					} catch (IOException e) {
						e.printStackTrace();
					} 
				} else {
					System.out.println("****pdf������,�޷�ת��****");
				}
			} else {
				System.out.println("****swf�Ѿ����ڲ���Ҫת��****");
			}
		}
		
		/**
		 * pdfת���� swfת����
		 * @param in
		 * @return
		 * @throws IOException
		 */
		private String loadStream(InputStream in) throws IOException {
			int ptr = 0;
			in = new BufferedInputStream(in);
			StringBuffer buffer = new StringBuffer();
			while ((ptr = in.read()) != -1) {
				buffer.append((char) ptr);
			}
			return buffer.toString();
		}

		/**
		 * �����ļ�·�� 
		 */
		public String getswfPath() {
			if (swfFile!=null&&swfFile.exists()) {
				String tempString = swfFile.getPath();
				tempString = tempString.replaceAll("\\\\", "/");
				return tempString;
			} else {
				return "";
			}
		}	
	        
	    /**
	     * �ж��ļ�����
	     * @param fileName
	     * @return
	     * @throws Exception
	     */
	    public  String codeString(File file){
	    	int p;
	    	String code = null;  
	        //���е� 0xefbb��0xfffe��0xfeff��0x5c75��Щ��������ļ���ǰ�������ֽڵ�16������  
	       if (file.exists()) {
	    	   BufferedInputStream bin;
				try {
					bin = new BufferedInputStream( new FileInputStream(file));
					 try {
						p = (bin.read() << 8) + bin.read();
						  switch (p) {  
			               case 0xefbb:  
			                   code = "UTF-8";break;  
			              case 0xfffe:  
			                  code = "Unicode";break;  
			               case 0xfeff:  
			                   code = "UTF-16BE"; break;  
			               case 0x5c75:  
			                   code = "ANSI|ASCII" ; break ;  
			               default:  
			                   code = "GBK";  
						  }
					} catch (IOException e) {
						e.printStackTrace();
					}  
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}                     
	           return code; 
	       }else{
	    	   return null;
	       }          
	   }
	      
	         
	    /**
	     * ��ȡtxt�ļ�����utf-8д�����ļ���תpdf��������
	     * @param srcFile
	     * @param destFile
	     */
	    public void copyFile(File srcFile, File destFile) {
	    	BufferedReader buf = null;    	
	    	
	    	try {
	    		String charSet=codeString(srcFile);
	    		buf = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile),charSet));//��ԭ���������
	     
	    		// �½��ļ���������������л���
	    		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
	    		BufferedWriter bfw = new BufferedWriter(out);
	    		
	    		String str = null;
	    		String lineSeparator = System.getProperty("line.separator");
	    		while((str = buf.readLine()) != null){
	    			bfw.write(str + lineSeparator);
	    		}	
	    		bfw.flush();//ˢ��
	    		bfw.close();
	    	} catch (Exception e) {
	    		throw new RuntimeException(e);
	    	} finally {    		
	    		try {
	    			if (buf != null) {
	    				buf.close();
	    			}
	    		} catch (Exception e) {
	    			throw new RuntimeException(e);
	    		} 
	    	}
	    } 
	    
	    /**
		 * �ж��ļ����Ƿ���ڣ��������򴴽�
		 */
		private static void CreateFile(String path) {
			File file = new File(path);
			// ����ļ��в������򴴽�
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		
		/**
		 * ��ȡ����Ĭ�ϵ�ַ��swf�ļ����·����swfTools��װ·��
		 */

	private void getDefaultPath(){
		try {
			defaultPath = "D:\\up";
			swfTools="D:/Program Files/SWFTools/pdf2swf.exe";
			//������Ϊ3ʱ(���߲鿴�ĵ�δ����)
			if(!defaultPath.equals("")){
				swfOutPath=defaultPath+"/swfFile/";
			}else {
				swfOutPath=swfOutPath+"/swfFile/";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 	
    }
		
		/**
		 * ������ѹ����ļ��в������Ӧ���ļ�ת��Ϊswf
		 * @param path
		 */
		public void traverseFolder(String path) {
			File file = new File(path);
			if (file.exists()) {
				File[] files = file.listFiles();
				if (files.length == 0) {
					System.out.println("�ļ����ǿյ�!");
					return;
				} else {
					for (File file2 : files) {
						if (file2.isDirectory()) {
							traverseFolder(file2.getAbsolutePath());
						} else {
							String fileName=file2.getAbsolutePath().replace("\\", "/");
							String newFileName=fileName.substring(0,fileName.lastIndexOf("."));//�ļ�·��
							String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
							
							docFile=null;
							pdfFile=null;
							swfFile=null;
							txtFile=null;
							
							if(fileType.equalsIgnoreCase("doc")||fileType.equalsIgnoreCase("xls")||fileType.equalsIgnoreCase("docx")||fileType.equalsIgnoreCase("xlsx")
									||fileType.equalsIgnoreCase("ppt")||fileType.equalsIgnoreCase("pptx")){
								
								docFile=new File(fileName);							
								pdfFile = new File(newFileName + ".pdf");
								swfFile = new File(newFileName + ".swf");
								doc2pdf();
								
								if (docFile.exists()) {
									docFile.delete();
								}							
							}else if(fileType.equalsIgnoreCase("pdf")) {
								pdfFile = new File(fileName);
								swfFile = new File(newFileName + ".swf");
								pdf2swf(false);
							}else if(fileType.equalsIgnoreCase("txt")) {
								docFile=new File(fileName);
								txtFile = new File(newFileName+"_temp" + ".txt");		    
								copyFile(docFile,txtFile);
								docFile=txtFile;
								pdfFile = new File(newFileName + ".pdf");
								swfFile = new File(newFileName + ".swf");
								doc2pdf();
								if (docFile.exists()) {
									docFile.delete();
								}
							}
							//ͼƬ�ౣ��
							if(fileType.equalsIgnoreCase("png")||fileType.equalsIgnoreCase("gif")||fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg")||fileType.equalsIgnoreCase("bmp")){
								if (file2.exists()){
									file2.renameTo(new File(newFileName+"."+fileType));//������
								}		
							}else{
								if (file2.exists()) {//�������� ת���� �ļ�ɾ��
									file2.delete();
								}
							}								
						}
					}
				}
			} else {
				System.out.println("Ҫ��ѹ���ļ�������!");
			}
		}		
	}

