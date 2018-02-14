package cn.lt.po;

public class AidStudent {
	private Integer id;
	private Integer aidId;
	private Integer studentId;
	private Integer adId;
	private Integer adminId;
	private Student student;
	private Aid aid;
	private UserInfo userInfo;
	
	private String aidTitle;
	private Integer theYear;
	private Integer gradeNo;
	private String stuName;
	private String stuNo;
	private String phone;
	private String department;
	private String professional;
	private String adminStr;
	
	public Integer getAidId() {
		return aidId;
	}
	public void setAidId(Integer aidId) {
		this.aidId = aidId;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Aid getAid() {
		return aid;
	}
	public void setAid(Aid aid) {
		this.aid = aid;
	}
	public String getAidTitle() {
		return aidTitle;
	}
	public void setAidTitle(String aidTitle) {
		this.aidTitle = aidTitle;
	}
	public Integer getTheYear() {
		return theYear;
	}
	public void setTheYear(Integer theYear) {
		this.theYear = theYear;
	}
	public Integer getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getAdminStr() {
		return adminStr;
	}
	public void setAdminStr(String adminStr) {
		this.adminStr = adminStr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
}
