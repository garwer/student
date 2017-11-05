package course.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import course.dao.CourseDao;
import course.entity.UserInfo;
import course.service.UserSelCourse;


public class fileUploadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private UserSelCourse userSelCourse;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
		//1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("utf-8"); 
		factory.setSizeThreshold(1024 * 5000);//设置内存的临界值为500K
		File linshi = new File("E:\\up");//当超过500K的时候，存到一个临时文件夹中
		factory.setRepository(linshi);
		upload.setSizeMax(1024 * 1024 * 5);//设置上传的文件总的大小不能超过5M
		try {
			// 1. 得到 FileItem 的集合 items
			@SuppressWarnings("unchecked")
			List<FileItem> /* FileItem */items = upload.parseRequest(request);
			// 2. 遍历 items:
			for (FileItem item : items) {
				// 若是一个一般的表单域, 打印信息
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					System.out.println(name + ": " + value);									
				}
				// 若是文件域则把文件保存到 e:\\files 目录下.
				else {
					String fileName = item.getName();//这边获取的为详细路径
					long sizeInBytes = item.getSize();
					System.out.println("=================:" + fileName);
					System.out.println("文件大小为--------------:" + sizeInBytes);
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					System.out.println("fileName的值为：" + fileName);
					File tempFile =new File(fileName.trim()); 
					fileName = tempFile.getName(); //仅获取文件名
					String path = "D:\\image\\user\\";
					File filePath = new File(path);
					if (!filePath.exists()) {
						filePath.mkdir(); //如果不存在路径 则创建
					}
					Random random = new Random();
				    //String raString = String.valueOf(random.nextInt(300));
					//String fileUploadPath = "D:\\image\\user\\" + fileName ;//文件最终上传的位置
					String fileUploadPath = "D:\\image\\user\\" + fileName ;
					System.out.println("=================:" + fileUploadPath);
					System.out.println(fileName);
					OutputStream out = new FileOutputStream(fileUploadPath);
					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					String jsonStr = request.getParameter("jsonStr");
					JSONObject jsonObject = JSONObject.fromObject(jsonStr);
					String xString = ((Object)(jsonObject.get("x"))).toString();
					System.out.println(xString);
					String yString = ((Object)(jsonObject.get("y"))).toString(); 
					System.out.println(yString);
					String hString = ((Object)(jsonObject.get("height"))).toString();
					System.out.println(hString);
					String wString = ((Object)(jsonObject.get("width"))).toString(); 
					System.out.println(wString);
					int x = Double.valueOf(xString).intValue();
					System.out.println(x);
					int y = Double.valueOf(yString).intValue();
					System.out.println(y);
					int height = Double.valueOf(hString).intValue();
					int width = Double.valueOf(wString).intValue();
					System.out.println(jsonObject);
					//裁剪图片操作  
					/**
					 * 图片裁剪
					 * @param srcImageFile 图片裁剪地址
					 * @param result 图片输出文件夹
					 * @param destWidth 图片裁剪宽度
					 * @param destHeight 图片裁剪高度
					 */
					String type = "";
					String prefix = fileName.substring(fileName.lastIndexOf(".")+1);     
					if ("png".equalsIgnoreCase(prefix)) {
						type="PNG";
					} else if ("jpg".equalsIgnoreCase(prefix)) {
						type="JPEG";
					} else if ("gif".equalsIgnoreCase(prefix)) {
						type = "GIF";
					}
			        Iterator iterator = ImageIO.getImageReadersByFormatName(type);//PNG,BMP   
			        ImageReader reader = (ImageReader)iterator.next();//获取图片尺寸
			        InputStream img = new FileInputStream(fileUploadPath);  
			        ImageInputStream iis = ImageIO.createImageInputStream(img);   
			        reader.setInput(iis, true);   
			        ImageReadParam param = reader.getDefaultReadParam();   
			        Rectangle rectangle = new Rectangle(x, y, width, height);//*指定截取范围*/    
			        param.setSourceRegion(rectangle);   
			        BufferedImage bi = reader.read(0,param); 
			      
			        String photoAdr = "D:\\image\\user\\" + fileName;
			        String finalAdr = "/user/" + fileName;
			        System.out.println(finalAdr);
			        ImageIO.write(bi, "JPEG", new File("D:\\image\\user\\" + fileName));
					out.close();
					in.close();
					//入库操作
					UserInfo userInfo = new UserInfo();
					userInfo.setUser_id(jsonObject.get("user_id").toString());
					userInfo.setUser_password(jsonObject.get("user_pwd").toString());
					userInfo.setUser_name(jsonObject.get("user_name").toString());
					userInfo.setUser_birth(jsonObject.get("user_date").toString());
					userInfo.setUser_sex(jsonObject.get("user_sex").toString());
					userInfo.setPhoto_adr(finalAdr);
					System.out.println(userSelCourse);
					//dao.insertStudent(userInfo);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}
