package course.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import course.dao.UserDao;
import course.entity.UserInfo;

@SuppressWarnings(value = "all")
@Service("UserLoginService")
@Transactional
public class UserLoginService {
	@Autowired
	private UserDao userDao;
	public Map<String,String> checkLogin(String id,String password,String type, String yzmSession,String yzm) {
		String flag = "";
		UserInfo userInfo = userDao.findByUserId(id,type);
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println("userInfo" + userInfo);
		if (userInfo == null ||!userInfo.getUser_password().equals(password)) {
			flag = "nullId";
			map.put("flag", flag);
			return map;
		} else if(!yzmSession.equalsIgnoreCase(yzm)) {
			flag = "errorYzm";
			map.put("flag", flag);
			return map;
		}
		else {
			flag = "ok";
			UserInfo info = userDao.findByUserId(id, type);
			info.setUser_id(id);
			System.out.print(type);
			info.setUser_type(type);
			map.put("flag", flag);
			map.put("id", info.getUser_id());
			map.put("name", info.getUser_name());
			map.put("type", info.getUser_type());
			map.put("photo", info.getPhoto_adr());
			//给session赋值 
			System.out.println("值为" + info.getUser_id()+info.getUser_name());
			return map;
		}
	}
	
	public List<Map<String, Object>> getUserInfo(String id) {
		List<Map<String, Object>> info = userDao.findUserById(id);
		return info;
	}
}