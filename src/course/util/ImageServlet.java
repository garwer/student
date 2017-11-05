package course.util;

import java.awt.Color;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.io.IOException;

import java.util.Random;

import javax.imageio.ImageIO;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	// ͼƬ�Ŀ��
	private int width = 120;
	
	// ͼƬ�ĸ߶�
	private int height = 40;
	
	// ��֤���ַ�����
	private int codeCount = 4;
	
	// ��֤���������
	private int lineCount = 50;
	
	private Random random = new Random();

	public void doGet(HttpServletRequest request, HttpServletResponse response)

	throws ServletException, IOException {

		this.doPost(request, response);

	}
	
	/** ��ȡ����� */
	private int getRandomNumber(int number) {
		return random.nextInt(number);
	}
	
	/** ��ȡ�����ɫ */
	private Color getRandomColor() {
		int r = getRandomNumber(255);
		int g = getRandomNumber(255);
		int b = getRandomNumber(255);
		return new Color(r, g, b);
	}

	// �������ֺ���ĸ����֤��

	public void doPost(HttpServletRequest request, HttpServletResponse response)

                     throws ServletException, IOException {

              BufferedImage img = new BufferedImage(68, 22,

                            BufferedImage.TYPE_INT_RGB);

              // �õ���ͼƬ�Ļ�ͼ����

              Graphics g = img.getGraphics();

              Random r = new Random();

             // Color c = new Color(200, 150, 255);

             // g.setColor(c);

              // �������ͼƬ����ɫ
              // ��ͼ�����Ϊ��ɫ
      		  g.setColor(Color.WHITE);
      		  
      		  g.fillRect(0, 0, 68, 22);

              // ��ͼƬ��������ֺ���ĸ

              StringBuffer sb = new StringBuffer();

              char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

              int index, len = ch.length;
              
              
           // ���Ƹ�����
      		for (int i = 0; i < lineCount; i++) {
      			int xs = getRandomNumber(width);
      			int ys = getRandomNumber(height);
      			int xe = xs + getRandomNumber(width / 8);
      			int ye = ys + getRandomNumber(height / 8);
      			g.setColor(getRandomColor());
      			//System.out.println(1);
      			g.drawLine(xs, ys, xe, ye);
      		}


              for (int i = 0; i < 4; i ++) {

                     index = r.nextInt(len);

                     g.setColor(getRandomColor());//���������ɫ
                     
                     g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// ���������ʹ�С                  

                     g.drawString("" + ch[index], (i * 15) + 3, 18);//дʲô���֣���ͼƬ��ʲôλ�û�
                     
                     sb.append(ch[index]);
                     
              }

              request.getSession().setAttribute("piccode", sb.toString());
              System.out.println("��֤��:"+sb.toString());
              //��ֹͼ�񻺴�
              response.setHeader("Cache-Control", "no-cache");
              response.setHeader("Pragma", "no-cache");
              response.setDateHeader("Expores",0);
              ImageIO.write(img, "JPG", response.getOutputStream());
       }
	
}
