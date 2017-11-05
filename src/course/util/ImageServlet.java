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

	// 图片的宽度
	private int width = 120;
	
	// 图片的高度
	private int height = 40;
	
	// 验证码字符个数
	private int codeCount = 4;
	
	// 验证码干扰线数
	private int lineCount = 50;
	
	private Random random = new Random();

	public void doGet(HttpServletRequest request, HttpServletResponse response)

	throws ServletException, IOException {

		this.doPost(request, response);

	}
	
	/** 获取随机数 */
	private int getRandomNumber(int number) {
		return random.nextInt(number);
	}
	
	/** 获取随机颜色 */
	private Color getRandomColor() {
		int r = getRandomNumber(255);
		int g = getRandomNumber(255);
		int b = getRandomNumber(255);
		return new Color(r, g, b);
	}

	// 生成数字和字母的验证码

	public void doPost(HttpServletRequest request, HttpServletResponse response)

                     throws ServletException, IOException {

              BufferedImage img = new BufferedImage(68, 22,

                            BufferedImage.TYPE_INT_RGB);

              // 得到该图片的绘图对象

              Graphics g = img.getGraphics();

              Random r = new Random();

             // Color c = new Color(200, 150, 255);

             // g.setColor(c);

              // 填充整个图片的颜色
              // 将图像填充为白色
      		  g.setColor(Color.WHITE);
      		  
      		  g.fillRect(0, 0, 68, 22);

              // 向图片中输出数字和字母

              StringBuffer sb = new StringBuffer();

              char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

              int index, len = ch.length;
              
              
           // 绘制干扰线
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

                     g.setColor(getRandomColor());//生成随机颜色
                     
                     g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的字体和大小                  

                     g.drawString("" + ch[index], (i * 15) + 3, 18);//写什么数字，在图片的什么位置画
                     
                     sb.append(ch[index]);
                     
              }

              request.getSession().setAttribute("piccode", sb.toString());
              System.out.println("验证码:"+sb.toString());
              //禁止图像缓存
              response.setHeader("Cache-Control", "no-cache");
              response.setHeader("Pragma", "no-cache");
              response.setDateHeader("Expores",0);
              ImageIO.write(img, "JPG", response.getOutputStream());
       }
	
}
