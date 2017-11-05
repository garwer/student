package course.util;
//遇到过的问题 Open the Java build path property page of project 'student'
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
		private File initFile=null;//未处理前文件
		private File txtFile=null;
		private static String swfTools;//swftools安装路径
		private static String swfOutPath="D:/";//swf存放路径 路径类似 /home/weblogic/up/swfFile
		private static String defaultPath;//附件存放路径
		
		public Converter() {
			getDefaultPath();// defaultPath swfOutPath、swfTools赋值			
			CreateFile(swfOutPath);//创建存放swf文件的文件夹		
		}
		

		/**
		 * file 上传的附件路径 如"/form/201407/1405494758028_-1145448556.zip"
		 * @param fileString
		 */
		public void setFileToSwf(String fileString) {
			ini(fileString);
		}		
		
		/**
		 * 文件转换方法
		 * 
		 * @param fileString
		 * @throws Exception 
		 */
		private void ini(String fileString){		
			String fileType = fileString.substring(fileString.lastIndexOf(".")+1);//文件类型、后缀
			String fileName = fileString.substring(fileString.lastIndexOf("/") + 1,fileString.lastIndexOf("."));//文件名		
			String filePath=fileString.substring(0,fileString.lastIndexOf("."));//文件路径
			String swfFileName = swfOutPath+"/" +fileName; //路径+文件名		//文件名1487755963893_1043966930
			String temp=swfFileName+"_temp";//作为中转文件用,后删除
			System.out.println("defaultPath+fileString" + defaultPath+fileString);
			System.out.println("文件不需要加默认路径");
			initFile = new File(fileString); //此时fileString即为完整路径
			if (fileType.equalsIgnoreCase("doc")||fileType.equalsIgnoreCase("xls")||fileType.equalsIgnoreCase("docx")||fileType.equalsIgnoreCase("xlsx")
					||fileType.equalsIgnoreCase("ppt")||fileType.equalsIgnoreCase("pptx")){
				docFile=initFile;
				pdfFile = new File(swfFileName + ".pdf");
				swfFile = new File(swfFileName + ".swf");
				doc2pdf();
						
			}else if(fileType.equalsIgnoreCase("txt")){// 用于处理TXT文档转化为PDF格式乱码,
				//File destDir=new File(temp);
				//if (!destDir.exists()) {//文件夹路径不存在先创建路径，否复制txt会出错
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
		 * 转为PDF
		 */
		public void doc2pdf(){
			if (!swfFile.exists()) {
				if (docFile.exists()) {
					if (!pdfFile.exists()) {
						OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
						try {
							connection.connect();						
							//DocumentConverter converter = new OpenOfficeDocumentConverter(connection);//部分linux 转换不了文件,改成以下这句  udp by zhangye 
							DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
							converter.convert(docFile, pdfFile);
							connection.disconnect();						
							System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath());												
						} catch (java.net.ConnectException e) {
							e.printStackTrace();
							System.out.println("****swf转换器异常，openoffice服务未启动！****");
						} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
							e.printStackTrace();
							System.out.println("****swf转换器异常，读取转换文件失败****");
							System.out.println("****pdf转换失败，PDF输出：" + pdfFile.getPath());
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
						System.out.println("****已经转换为pdf，不需要再进行转化****");
					}
					pdf2swf(false);//调用转换成pdf
				} else {
					System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");
				}
			}		
		}
		
		/**
		 * pdf转换成 swf
		 * pdfIsSource false 删除生成的pdf true保留pdf
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
						if (pdfFile.exists()&&!pdfIsSource) {//pdf是转换出来的中间文件 则删除 是源文件则保留
							pdfFile.delete();
						}
						
						if (txtFile!=null&&txtFile.exists()) {
							txtFile.delete();
						}
						
						System.out.println("****swf转换成功，文件输出："+ swfFile.getPath() + "****");
					} catch (IOException e) {
						e.printStackTrace();
					} 
				} else {
					System.out.println("****pdf不存在,无法转换****");
				}
			} else {
				System.out.println("****swf已经存在不需要转换****");
			}
		}
		
		/**
		 * pdf转换成 swf转换流
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
		 * 返回文件路径 
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
	     * 判断文件编码
	     * @param fileName
	     * @return
	     * @throws Exception
	     */
	    public  String codeString(File file){
	    	int p;
	    	String code = null;  
	        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数  
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
	     * 读取txt文件，以utf-8写入新文件，转pdf不会乱码
	     * @param srcFile
	     * @param destFile
	     */
	    public void copyFile(File srcFile, File destFile) {
	    	BufferedReader buf = null;    	
	    	
	    	try {
	    		String charSet=codeString(srcFile);
	    		buf = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile),charSet));//以原编码读入流
	     
	    		// 新建文件输出流并对它进行缓冲
	    		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(destFile),"UTF-8");
	    		BufferedWriter bfw = new BufferedWriter(out);
	    		
	    		String str = null;
	    		String lineSeparator = System.getProperty("line.separator");
	    		while((str = buf.readLine()) != null){
	    			bfw.write(str + lineSeparator);
	    		}	
	    		bfw.flush();//刷新
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
		 * 判断文件夹是否存在，不存在则创建
		 */
		private static void CreateFile(String path) {
			File file = new File(path);
			// 如果文件夹不存在则创建
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		
		/**
		 * 获取附件默认地址和swf文件存放路径、swfTools安装路径
		 */

	private void getDefaultPath(){
		try {
			defaultPath = "D:\\up";
			swfTools="D:/Program Files/SWFTools/pdf2swf.exe";
			//当类型为3时(在线查看文档未完整)
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
		 * 遍历解压后的文件夹并把相对应的文件转换为swf
		 * @param path
		 */
		public void traverseFolder(String path) {
			File file = new File(path);
			if (file.exists()) {
				File[] files = file.listFiles();
				if (files.length == 0) {
					System.out.println("文件夹是空的!");
					return;
				} else {
					for (File file2 : files) {
						if (file2.isDirectory()) {
							traverseFolder(file2.getAbsolutePath());
						} else {
							String fileName=file2.getAbsolutePath().replace("\\", "/");
							String newFileName=fileName.substring(0,fileName.lastIndexOf("."));//文件路径
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
							//图片类保留
							if(fileType.equalsIgnoreCase("png")||fileType.equalsIgnoreCase("gif")||fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg")||fileType.equalsIgnoreCase("bmp")){
								if (file2.exists()){
									file2.renameTo(new File(newFileName+"."+fileType));//重命名
								}		
							}else{
								if (file2.exists()) {//其他类型 转换完 文件删除
									file2.delete();
								}
							}								
						}
					}
				}
			} else {
				System.out.println("要解压的文件不存在!");
			}
		}		
	}

