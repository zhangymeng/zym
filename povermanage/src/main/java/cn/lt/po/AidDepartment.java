package cn.lt.po;

public class AidDepartment {
	private Integer id;
	private Integer aidId;
	private Integer dId;
	private Integer num;
	private Integer remainingNum;
	private Aid aid;
	private String title;
	private Integer theYear;
	
	public Integer getAidId() {
		return aidId;
	}
	public void setAidId(Integer aidId) {
		this.aidId = aidId;
	}
	public Aid getAid() {
		return aid;
	}
	public void setAid(Aid aid) {
		this.aid = aid;
	}
	private String department;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getTheYear() {
		return theYear;
	}
	public void setTheYear(Integer theYear) {
		this.theYear = theYear;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getdId() {
		return dId;
	}
	public void setdId(Integer dId) {
		this.dId = dId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getRemainingNum() {
		return remainingNum;
	}
	public void setRemainingNum(Integer remainingNum) {
		this.remainingNum = remainingNum;
	}
}
