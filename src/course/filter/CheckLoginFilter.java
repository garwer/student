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
* ���ڼ���û��Ƿ��½�Ĺ����������δ��¼�����ض���ָ�ĵ�¼ҳ��    
* ���ò���   
* checkSessionKey ������� Session �б���Ĺؼ���    
* redirectURL ����û�δ��¼�����ض���ָ����ҳ�棬URL������ ContextPath    
* notCheckURLList ��������URL�б��Էֺŷֿ������� URL �в����� ContextPath   
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
			//ȡ��session. ���û��session���Զ��ᴴ��һ��, ������false��ʾû��ȡ�õ�session������ΪsessionΪ��.
			HttpSession session = req.getSession(false);
			//��½ҳ��
			String loginPage = filterConfig.getInitParameter("login");
			//����httpЭ���servlet			
			//���û�е�¼.
		/*	String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/",1),req.getRequestURI().length());
			System.out.println(requestURI);*/
			//�Ե�½ҳ�治���й���
			String reqUrl = req.getRequestURI();
			StringBuffer reqUrL = req.getRequestURL();
			System.out.println("�����ַ" + reqUrl);
			if (reqUrl.contains(loginPage)) {
				 chain.doFilter(request, response);
		         return;
			}
			
			//������css js���ļ�
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
			if (reqUrl.contains(YZM)||reqUrl.contains("/checkLogin.do")) {//��֤��͵�½�������
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