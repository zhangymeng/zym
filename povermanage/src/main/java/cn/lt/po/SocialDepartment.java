package cn.lt.po;

public class SocialDepartment {
	private Integer id;
	private Integer sId;
	private Integer dId;
	private Integer num;
	private Integer remainingNum;
	private Social social;
	private String title;
	private Integer theYear;
	
	private String department;
	
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
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
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
	public Social getSocial() {
		return social;
	}
	public void setSocial(Social social) {
		this.social = social;
	}
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
	
	
}
