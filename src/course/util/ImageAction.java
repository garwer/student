package course.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ͨ�����ķ�ʽ�ӱ��ػ�ȡͼƬ
 * @author 
 * @version 1.0
 */

public class ImageAction extends HttpServlet {
	private static final long serialVersionUID = -1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		//ͨ�����ķ�ʽ ��ȡ����ͼƬ������ ��ȡͼƬ
		//���ж��Ƿ����
		//�����ݿ��ȡ  
		FileInputStream inputStream = null;
		File file = new File("D:\\temp\\" + fileName);
		if (!file.exists()) {
			System.out.println("������");
			inputStream = new FileInputStream("D:\\temp\\null.jpg");  // D:/image/123.jpg
		} else {
		    inputStream = new FileInputStream("D:\\temp\\biao.jpg");  // D:/image/123.jpg
		}
		int i = inputStream.available();
		//byte�������ڴ��ͼƬ�ֽ�����
		byte[] buff = new byte[i];
		inputStream.read(buff);
		//�ǵùر�������
		inputStream.close();
		//���÷��͵��ͻ��˵���Ӧ��������
		response.setContentType("image/*");
		OutputStream out = response.getOutputStream();
		out.write(buff);
		//�ر���Ӧ�����
		out.close();
	}
}