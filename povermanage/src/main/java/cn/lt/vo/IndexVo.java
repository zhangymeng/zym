package cn.lt.vo;

import java.sql.Timestamp;

public class IndexVo {
	private Integer id;
	
	private String name;
	private String stuNo;
	private Integer sex; //0=�?1=�?
	private Integer gradeNo;
	private Integer classNo;
	private Integer dId;// student user
	private Integer pId;
	private String phone;
	
	private String username;
	private String password;
	private Timestamp createDate;
	
	private Integer loanId;
	private Integer type;
	private Integer editNum;
	
	private Integer ldId;
	private Integer studentId;
	
	private Integer sId;
	private Integer sdId;

	private Integer aidId;
	private Integer adId;
	
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

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getEditNum() {
		return editNum;
	}

	public void setEditNum(Integer editNum) {
		this.editNum = editNum;
	}

	public Integer getLdId() {
		return ldId;
	}

	public void setLdId(Integer ldId) {
		this.ldId = ldId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(Integer gradeNo) {
		this.gradeNo = gradeNo;
	}

	public Integer getClassNo() {
		return classNo;
	}

	public void setClassNo(Integer classNo) {
		this.classNo = classNo;
	}

	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
