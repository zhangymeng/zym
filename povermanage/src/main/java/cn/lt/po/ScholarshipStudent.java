package cn.lt.po;

public class ScholarshipStudent {
	private Integer id;
	private Integer sId;
	private Integer studentId;
	private Integer sdId;
	private Integer adminId;
	private Student student;
	private Scholarship scholarship;
	private UserInfo userInfo;
	
	private String scholarshipTitle;
	private Integer theYear;
	private Integer gradeNo;
	private String stuName;
	private String stuNo;
	private String phone;
	private String department;
	private String professional;
	private String adminStr;
	
	public Scholarship getScholarship() {
		return scholarship;
	}
	public void setScholarship(Scholarship scholarship) {
		this.scholarship = scholarship;
	}
	public String getScholarshipTitle() {
		return scholarshipTitle;
	}
	public void setScholarshipTitle(String scholarshipTitle) {
		this.scholarshipTitle = scholarshipTitle;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getSdId() {
		return sdId;
	}
	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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
}
