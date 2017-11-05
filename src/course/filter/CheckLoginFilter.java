package course.filter;

     
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;        
import javax.servlet.http.HttpServletResponse;        
import javax.servlet.http.HttpSession;               

import org.apache.commons.lang.StringUtils;

import java.io.IOException;        
import java.util.regex.Matcher;
import java.util.regex.Pattern;
       
/**   
* 用于检测用户是否登陆的过滤器，如果未登录，则重定向到指的登录页面    
* 配置参数   
* checkSessionKey 需检查的在 Session 中保存的关键字    
* redirectURL 如果用户未登录，则重定向到指定的页面，URL不包括 ContextPath    
* notCheckURLList 不做检查的URL列表，以分号分开，并且 URL 中不包括 ContextPath   
*/       
public class CheckLoginFilter implements Filter {  
		private FilterConfig filterConfig;
		public void init(FilterConfig filterConfig) throws ServletException {
			this.filterConfig = filterConfig;
		}
		
		public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {			
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res = (HttpServletResponse)response;
			//取得session. 如果没有session则自动会创建一个, 我们用false表示没有取得到session则设置为session为空.
			HttpSession session = req.getSession(false);
			//登陆页面
			String loginPage = filterConfig.getInitParameter("login");
			//基于http协议的servlet			
			//如果没有登录.
		/*	String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/",1),req.getRequestURI().length());
			System.out.println(requestURI);*/
			//对登陆页面不进行过滤
			String reqUrl = req.getRequestURI();
			StringBuffer reqUrL = req.getRequestURL();
			System.out.println("请求地址" + reqUrl);
			if (reqUrl.contains(loginPage)) {
				 chain.doFilter(request, response);
		         return;
			}
			
			//不过滤css js等文件
			String excludeSuffix = this.filterConfig.getInitParameter("exclude");
			if(!StringUtils.isEmpty(excludeSuffix)){
				Matcher m = Pattern.compile(excludeSuffix).matcher(reqUrl);
				if(m.find()){
					chain.doFilter(request, response);
					return;
				}
			}
			
			System.out.println(reqUrL);

			String YZM = this.filterConfig.getInitParameter("YZM");
			if (reqUrl.contains(YZM)||reqUrl.contains("/checkLogin.do")) {//验证码和登陆请求放行
				chain.doFilter(request, response);
				return;
			}
			if (session == null ||session.getAttribute("userInfo") == null) {
				res.sendRedirect(req.getContextPath() + "/course/login.jsp");
				return;
			} else {
				 System.out.println(reqUrL);
				 session.setAttribute("adr", reqUrL);
				 chain.doFilter(request, response);
				 return;
			}
		}
  
		
		public void destroy() {
		
		}  
}  