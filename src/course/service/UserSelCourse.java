package course.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import course.dao.CourseDao;
import course.dao.UserDao;
import course.entity.Collect;
import course.entity.Comment;
import course.entity.CourseTree;
import course.entity.Integral;
import course.entity.UserInfo;

@SuppressWarnings(value = "all")
@Service("UserSelCourse")
@Transactional
public class UserSelCourse {
	@Autowired
	private CourseDao courseDao;
	private UserDao userDao;
	public List<Map<String, Object>> getCourse() {
		List<Map<String, Object>> list = courseDao.findAllCourse();
		return list;
	}
	
	public Map findContentById(String id) {
		Map<String,Object> map = courseDao.findContentById(id);
		return map;
	}
	
	public List<Map<String, Object>> findCourseByPro(String id) {
		List<Map<String, Object>> list = courseDao.findCourseByPro(id);
		return list;
	}
	public List<Map<String, Object>> findHotCourse(String id) {
		List<Map<String, Object>> list = courseDao.findHotCourse(id);
		return list;
	}
	public List<Map<String, Object>> findScoreOrder(String id) {
		List<Map<String, Object>> list = courseDao.findScoreOrder(id);
		return list;
	}
	public String isCollect(String courseId,String userId) {
		int isCollect = courseDao.isCollect(courseId, userId);
		if (isCollect == 0) {
			return "false";
		}else {
			return "true";
		}
	}
	public List<Map<String, Object>> getComment(String courseId,String type) {
		List<Map<String, Object>> list = courseDao.getComment(courseId,type);
		return list;
	}
	public void saveComment(Comment comment) {
		courseDao.saveComment(comment);
	}
	public void delComment(String commentId) {
		courseDao.delComment(commentId);
	}
	public void saveIntegral(Integral integral) {
		courseDao.saveIntegral(integral);
	}
	public void saveCollect(Collect collect) {
		courseDao.saveCollect(collect);
	}
	public int getCollectCount(String id) {
		return courseDao.getCollectCount(id);
	}
	public void cancelCollect(String courseId) {
		courseDao.cancelCollect(courseId);
	}
	public List<Map<String, Object>> getMyCollect(Map<String, Object> map) {
		List<Map<String, Object>> list = courseDao.getMyCollect(map);
		return list;
	}
	public List<Map<String, Object>> getMyLabel(String id) {
		List<Map<String, Object>> list = courseDao.getMyLabel(id);
		return list;
	}
	public List<Map<String, Object>> getIntegral(Map<String, Object> map) {
		List<Map<String, Object>> list = courseDao.getIntegral(map);
		return list;
	}
	public int getIntegralCount(String id) {
		return courseDao.getIntegralCount(id);
	}
	public Map getScoreDetail(String id) {
		Map<String,Object> map = courseDao.getScoreDetail(id);
		return map;
	}
	public String isChoose(String id) {
		String isChoose = courseDao.isChoose(id);
		return isChoose;
	}
	public int courseChoose(String id) {
		int isChoose = courseDao.courseChoose(id);
		return isChoose;
	}
	public void sureChoose(String id,String userId) {
		courseDao.sureChoose(id,userId);
	}
	
	public Map getCenterInfo(String id) {
		Map<String,Object> map = courseDao.getCenterInfo(id);
		return map;
	}
	
	public void cancleCourse(String id) {
		courseDao.cancleCourse(id);
	}
	public List<Map<String, Object>> findAllStudent(Map<String, Object> map) {
		List<Map<String, Object>> list = courseDao.findAllStudent(map);
		return list;
	}
	public int allStudentNum(String id) {
		int allStudentNum = courseDao.allStudentNum(id);
		return allStudentNum;
	}
	public List<Map<String, Object>> findAllCourses(Map<String, Object> map) {
		List<Map<String, Object>> list = courseDao.findAllCourses(map);
		return list;
	}
	public int allCourseNum(String id) {
		int allStudentNum = courseDao.allCourseNum(id);
		return allStudentNum;
	}
	public List<Map<String, Object>> getAllTeacher(String id) {
		List<Map<String, Object>> list = courseDao.getAllTeacher(id);
		return list;
	}
	public void insertCourse(CourseTree courseTree) {
		courseDao.insertCourse(courseTree);
	}
	public void insertStudent(UserInfo userInfo) {
		courseDao.insertStudent(userInfo);
	}
	public List<Map<String, Object>> findAllMyStudent(Map<String, Object> map) {
		List<Map<String, Object>> list = courseDao.findAllMyStudent(map);
		return list;
	}
	public int allMyStudentNum(String id) {
		int allMyStudentNum = courseDao.allMyStudentNum(id);
		return allMyStudentNum;
	}
	public List<Map<String, Object>> findAllMyCourse(Map<String, Object> map) {
		List<Map<String, Object>> list = courseDao.findAllMyCourse(map);
		return list;
	}
	public int allMyCourseNum(String id) {
		int allMyCourseNum = courseDao.allMyCourseNum(id);
		return allMyCourseNum;
	}
}