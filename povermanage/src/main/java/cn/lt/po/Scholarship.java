package cn.lt.po;

public class Scholarship {
	private Integer id;
	private String title;
	private Integer theYear;
	private Integer isDel;
	private Integer num;
	private Integer remainingNum;
	
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
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
