package course.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import course.entity.Collect;
import course.entity.Comment;
import course.entity.CourseTree;
import course.entity.Integral;
import course.entity.UserInfo;

public interface CourseDao {
	public List<Map<String, Object>> findAllCourse();
	public Map<String, Object> findContentById(String id); 
	public List<Map<String, Object>> findCourseByPro(String id);
	public List<Map<String, Object>> findHotCourse(String id);
	public List<Map<String, Object>> findScoreOrder(String id);
	public int isCollect (@Param("courseId")String courseId,@Param("userId")String userId);
	public List<Map<String, Object>> getComment(@Param("courseId")String courseId,@Param("type")String type);
	public void saveComment(Comment comment);
	public void delComment(String commentId);
	public void saveIntegral(Integral integral);
	public void saveCollect(Collect collect);
	public void cancelCollect(String courseId);
	public List<Map<String, Object>> getMyCollect(Map<String, Object> map);
	public int getCollectCount(String id);
	public List<Map<String, Object>> getMyLabel(String id);
	public List<Map<String, Object>> getIntegral(Map<String, Object> map);
	public int getIntegralCount(String id);
	public Map<String, Object> getScoreDetail(String id); 
	public String isChoose(String id);
	public int courseChoose(String id);
	public void sureChoose(@Param("id")String id,@Param("userId")String userId);
	public Map<String, Object> getCenterInfo(String id); 
	public void cancleCourse(String id);
	public List<Map<String, Object>> findAllStudent(Map<String, Object> map);
	public int allStudentNum(String id);
	public List<Map<String, Object>> findAllCourses(Map<String, Object> map);
	public int allCourseNum(String id);
	public List<Map<String, Object>> getAllTeacher(String id);
	public void insertCourse(CourseTree courseTree);
	public void insertStudent(UserInfo userInfo);
	public List<Map<String, Object>> findAllMyStudent(Map<String, Object> map);
	public int allMyStudentNum(String id);
	public List<Map<String, Object>> findAllMyCourse(Map<String, Object> map);
	public int allMyCourseNum(String id);
}
