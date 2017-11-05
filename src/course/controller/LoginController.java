package course.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import course.entity.UserInfo;
import course.service.UserLoginService;
import course.util.ConverterDao;
@Controller
@RequestMapping("/checkLogin.do")
public class LoginController {
	@Autowired
	private UserLoginService userLoginService;
	private static final Logger log = Logger.getLogger(CourseController.class);
	@ResponseBody
	@RequestMapping(params="method=checkUser")
	public String checkUserLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		String type = request.getParameter("type");
		String yzm = request.getParameter("yzm");
		HttpSession session = request.getSession();
		//piccode
		String yzmSession = (String) session.getAttribute("piccode"); 	
		System.out.println(yzmSession);		
		HashMap<String,String> map = (HashMap<String, String>) userLoginService.checkLogin(id, passWord, type,yzmSession, yzm);
		UserInfo userInfo = new UserInfo();
		String flag = map.get("flag");
		if (flag == "ok") {
			userInfo.setUser_id(map.get("id"));
			userInfo.setUser_name(map.get("name"));
			userInfo.setUser_type(map.get("type"));
			userInfo.setPhoto_adr(map.get("photo"));
	        request.getSession().setAttribute("userInfo", userInfo);
		}
		List<Map<String, Object>> studentInfo = userLoginService.getUserInfo(id);
		System.out.println(studentInfo);
		System.out.println(id);
		System.out.println(type);
/*		UserInfo info = userDao.findByUserId(id, type);
		System.out.println("值为aaaaaaa" + info.getUser_id()+info.getUser_name());*/
		log.info("flag的值为:" + flag);
		System.out.println("flag的值为:" + flag);
		return flag;		
	}
	
	//通过学生id获取信息
	@ResponseBody
	@RequestMapping(params="method=getUserInfo")
	public List<Map<String, Object>> getUserInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		String id = request.getParameter("id");
		List<Map<String, Object>> info = userLoginService.getUserInfo(id);
		return info;	
	}
	
	@ResponseBody
	@RequestMapping(params="method=openFile")
	public String openFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		ConverterDao converterDao= new ConverterDao();
		String isSuccess = converterDao.converAction();
		return isSuccess;	
	}
	
	
}
