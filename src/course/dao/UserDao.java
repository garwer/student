package course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import course.entity.User;
import course.entity.UserInfo;
//dao层用于程序对数据库的操作 为模型层

public interface UserDao {
	//根据学号
	public UserInfo findByUserId(@Param("id")String id,@Param("type")String type);
	public List<Map<String, Object>> findUserById(@Param("id")String id);
	public User findByTel(String tel);
}
