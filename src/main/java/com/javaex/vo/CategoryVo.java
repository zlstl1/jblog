package com.javaex.vo;

public class CategoryVo {

	private int no;
	private int cateNo;
	private String id;
	private String cateName;
	private String description;
	private int postTime;
	private String regDate;
	
	public CategoryVo() {
	}

	public CategoryVo(int no, int cateNo, String id, String cateName, String description, int postTime,
			String regDate) {
		super();
		this.no = no;
		this.cateNo = cateNo;
		this.id = id;
		this.cateName = cateName;
		this.description = description;
		this.postTime = postTime;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPostTime() {
		return postTime;
	}

	public void setPostTime(int postTime) {
		this.postTime = postTime;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", cateNo=" + cateNo + ", id=" + id + ", cateName=" + cateName
				+ ", description=" + description + ", postTime=" + postTime + ", regDate=" + regDate + "]";
	}

	
	
}
