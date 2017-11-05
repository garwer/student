package course.dao.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import course.dao.CourseDao;
import course.dao.UserDao;
import course.entity.Comment;
import course.entity.Integral;
import course.entity.User;
import course.entity.UserInfo;
import course.util.SpringTest;

public class test extends SpringTest{
/*	@Test
	//检测用户登录
	public void test1(){
		UserDao userDao = getAc().getBean("userDao",UserDao.class);
		User user =  userDao.findByTel("18326109580"); 
		System.out.println(user.getUser_name()+"的电话号码为:"+user.getUser_tel());
	}
	@Test
	public void test2(){
		UserDao userDao = getAc().getBean("userDao",UserDao.class);
		System.out.println(1);
		UserInfo userinfo =  userDao.findByUserId("·","student"); 
		System.out.println(1);
		System.out.println(userinfo.getUser_name()+"的密码为:"+userinfo.getUser_password());
	}*/
	
	@Test
	public void test3(){
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		System.out.println(1);
		Map<String, Object> map = dao.findContentById("111");
		System.out.println(1);
		System.out.println(map);
	}
	
	@Test
	public void test4(){
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		System.out.println(1);
		List<Map<String, Object>> list = dao.findCourseByPro("1");
		System.out.println(1);
		System.out.println(list);
	}

	@Test
	public void test5(){
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		System.out.println(1);
		int isCollect = dao.isCollect("151", "1");
		System.out.println(isCollect);
	}

	@Test
	public void test6(){
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		List<Map<String, Object>> list = dao.getComment("151","course");
		System.out.println(list);
	}
	
	@Test
	public void test7(){
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		Comment comment = new Comment();
		comment.setCommentContent("评论一下");
		comment.setCourseId("151");
		comment.setType("course");
		comment.setUserId("1");
		dao.saveComment(comment);
	}
	
	@Test
	public void test8(){
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		Integral integral = new Integral();
		integral.setType("comment");
		integral.setCourseId("183");
		integral.setUserId("1");
		dao.saveIntegral(integral);
	}
	
	@Test
	public void test9(){
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "1");
		map.put("start", 1);
		map.put("size", 3);
		List<Map<String, Object>> list = dao.getMyCollect(map);
		System.out.println(list);
	}
	
	@Test
	public void test10() {
		CourseDao dao = getAc().getBean("courseDao",CourseDao.class);
		int count = dao.getCollectCount("1");
		System.out.println(count);
	}
}
