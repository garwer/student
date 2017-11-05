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
		//1������һ��DiskFileItemFactory����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2������һ���ļ��ϴ�������
        ServletFileUpload upload = new ServletFileUpload(factory);
        //����ϴ��ļ�������������
        upload.setHeaderEncoding("utf-8"); 
		factory.setSizeThreshold(1024 * 5000);//�����ڴ���ٽ�ֵΪ500K
		File linshi = new File("E:\\up");//������500K��ʱ�򣬴浽һ����ʱ�ļ�����
		factory.setRepository(linshi);
		upload.setSizeMax(1024 * 1024 * 5);//�����ϴ����ļ��ܵĴ�С���ܳ���5M
		try {
			// 1. �õ� FileItem �ļ��� items
			@SuppressWarnings("unchecked")
			List<FileItem> /* FileItem */items = upload.parseRequest(request);
			// 2. ���� items:
			for (FileItem item : items) {
				// ����һ��һ��ı���, ��ӡ��Ϣ
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					System.out.println(name + ": " + value);									
				}
				// �����ļ�������ļ����浽 e:\\files Ŀ¼��.
				else {
					String fileName = item.getName();//��߻�ȡ��Ϊ��ϸ·��
					long sizeInBytes = item.getSize();
					System.out.println("=================:" + fileName);
					System.out.println("�ļ���СΪ--------------:" + sizeInBytes);
					InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					System.out.println("fileName��ֵΪ��" + fileName);
					File tempFile =new File(fileName.trim()); 
					fileName = tempFile.getName(); //����ȡ�ļ���
					String path = "D:\\image\\user\\";
					File filePath = new File(path);
					if (!filePath.exists()) {
						filePath.mkdir(); //���������·�� �򴴽�
					}
					Random random = new Random();
				    //String raString = String.valueOf(random.nextInt(300));
					//String fileUploadPath = "D:\\image\\user\\" + fileName ;//�ļ������ϴ���λ��
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
					//�ü�ͼƬ����  
					/**
					 * ͼƬ�ü�
					 * @param srcImageFile ͼƬ�ü���ַ
					 * @param result ͼƬ����ļ���
					 * @param destWidth ͼƬ�ü����
					 * @param destHeight ͼƬ�ü��߶�
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
			        ImageReader reader = (ImageReader)iterator.next();//��ȡͼƬ�ߴ�
			        InputStream img = new FileInputStream(fileUploadPath);  
			        ImageInputStream iis = ImageIO.createImageInputStream(img);   
			        reader.setInput(iis, true);   
			        ImageReadParam param = reader.getDefaultReadParam();   
			        Rectangle rectangle = new Rectangle(x, y, width, height);//*ָ����ȡ��Χ*/    
			        param.setSourceRegion(rectangle);   
			        BufferedImage bi = reader.read(0,param); 
			      
			        String photoAdr = "D:\\image\\user\\" + fileName;
			        String finalAdr = "/user/" + fileName;
			        System.out.println(finalAdr);
			        ImageIO.write(bi, "JPEG", new File("D:\\image\\user\\" + fileName));
					out.close();
					in.close();
					//������
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
