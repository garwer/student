package course.entity;

public class Integral {
	private int integralId;
	private String type;
	private String date;
	private String courseId;
	private String userId;
	public int getIntegralId() {
		return integralId;
	}
	public void setIntegralId(int integralId) {
		this.integralId = integralId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
