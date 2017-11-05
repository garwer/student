package course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import course.entity.User;
import course.entity.UserInfo;
//dao�����ڳ�������ݿ�Ĳ��� Ϊģ�Ͳ�

public interface UserDao {
	//����ѧ��
	public UserInfo findByUserId(@Param("id")String id,@Param("type")String type);
	public List<Map<String, Object>> findUserById(@Param("id")String id);
	public User findByTel(String tel);
}
