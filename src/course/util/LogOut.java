package course.util;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 学生退出的Servlet
 * 
 * @author 
 * @version 1.0
 */
public class LogOut extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
	    System.out.println("进入到bbbbbbbbbbb");
		HttpSession session = request.getSession(false);//防止创建Session         
		//String user_login_url = (String)session.getAttribute("userInfo");
       if (session == null) {
   		response.sendRedirect(request.getContextPath() + "/course/login.jsp");
       }
       System.out.println("enenenenenenen");
       session.removeAttribute("userInfo"); 
       request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/course/login.jsp");
  /*    if (user_login_url == null) {     	
             response.sendRedirect("http://"+LoadProperties.getCASSERVER()+"/cas/logout");
        }
        else*/
   
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}