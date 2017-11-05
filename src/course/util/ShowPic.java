package course.util;

import java.io.IOException;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import java.io.*;  
import javax.servlet.*;  
import com.sun.image.codec.jpeg.*;//sun��˾���ṩ��jpgͼƬ�ļ��ı���api  
import java.awt.image.BufferedImage; 


/** 
 *  
 * ��ʾ�ļ���Servlet 
 * ����ӱ����ļ��ж�ȡͼƬ�� 
 *  
 */  
public class ShowPic extends javax.servlet.http.HttpServlet implements  
        javax.servlet.Servlet {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String GIF = "image/gif;charset=GB2312";// �趨���������  
  
    private static final String JPG = "image/jpeg;charset=GB2312";  
  
    /* 
     * (non-Java-doc) 
     *  
     * @see javax.servlet.http.HttpServlet#HttpServlet() 
     */  
    public ShowPic() {  
        super();  
    }  
  
    /* 
     * (non-Java-doc) 
     *  
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, 
     *      HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        doPost(request, response);  
    }  
  
    /* 
     * (non-Java-doc) 
     *  
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, 
     *      HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        String spec = request.getParameter("spec");// ���ͼƬ�����͵ı�־  
  
        String imagePath = "D:\\temp\\biao.jpg";  
        System.out.println(imagePath);
        response.reset();  
  
        OutputStream output = response.getOutputStream();// �õ������  
        if (imagePath.toLowerCase().endsWith(".jpg"))// ʹ�ñ��봦���ļ����������  
        {  
            response.setContentType(JPG);// �趨���������  
            // �õ�ͼƬ����ʵ·��  
  
            // �õ�ͼƬ���ļ���  
            InputStream imageIn = new FileInputStream(new File(imagePath));  
            // �õ�����ı����������ļ�������jpg��ʽ����  
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);  
            // �õ�������ͼƬ����  
            BufferedImage image = decoder.decodeAsBufferedImage();  
            // �õ�����ı�����  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);  
            encoder.encode(image);// ��ͼƬ�����������  
            imageIn.close();// �ر��ļ���  
        }  
        if (imagePath.toLowerCase().endsWith(".gif"))// ��ʹ�ñ��봦���ļ����������  
        {  
            response.setContentType(GIF);  
            ServletContext context = getServletContext();// �õ���������  
            InputStream imageIn = context.getResourceAsStream(imagePath);// �ļ���  
            BufferedInputStream bis = new BufferedInputStream(imageIn);// ���뻺����  
            BufferedOutputStream bos = new BufferedOutputStream(output);// ���������  
            byte data[] = new byte[4096];// �����ֽ���  
            System.out.println("����");
            int size = 0;  
            size = bis.read(data);  
            while (size != -1) {  
                bos.write(data, 0, size);  
                size = bis.read(data);  
            }  
            bis.close();  
            bos.flush();// ������������  
            bos.close();  
        }  
        output.close();  
    }  
}  