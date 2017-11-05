package course.entity;
public class User{
	private int user_id;
	private String user_tel;
	private String user_password;
	private String user_name;
	private String user_birth;
	private String user_sex;
	private String user_work;
	private int meal_id;
	private int user_bill;


	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String userBirth) {
		user_birth = userBirth;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String userSex) {
		user_sex = userSex;
	}
	public String getUser_work() {
		return user_work;
	}
	public void setUser_work(String userWork) {
		user_work = userWork;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int userId) {
		user_id = userId;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String userTel) {
		user_tel = userTel;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String userPassword) {
		user_password = userPassword;
	}
	public void setMeal_id(int meal_id) {
		this.meal_id = meal_id;
	}
	public int getMeal_id() {
		return meal_id;
	}
	public void setUser_bill(int user_bill) {
		this.user_bill = user_bill;
	}
	public int getUser_bill() {
		return user_bill;
	}

}
