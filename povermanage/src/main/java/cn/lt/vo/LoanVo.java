package cn.lt.vo;

public class LoanVo {
	private Integer id;
	
	private String title;
	private Integer theYear;
	private Integer isDel;
	private Integer num;
	private Integer remainingNum;
	private Integer ldId;
	
	private Integer loanId;
	private Integer dId;
	private Integer editNum;
	
	private Integer studentId;
	private Integer adminId;
	private String stuNo;
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public Integer getdId() {
		return dId;
	}
	public void setdId(Integer dId) {
		this.dId = dId;
	}
	public Integer getRemainingNum() {
		return remainingNum;
	}
	public void setRemainingNum(Integer remainingNum) {
		this.remainingNum = remainingNum;
	}
	public Integer getTheYear() {
		return theYear;
	}
	public void setTheYear(Integer theYear) {
		this.theYear = theYear;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
