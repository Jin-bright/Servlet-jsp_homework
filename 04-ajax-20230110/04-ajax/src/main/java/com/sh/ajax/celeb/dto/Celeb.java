package com.sh.ajax.celeb.dto;

public class Celeb {
	private int no;
	private String name;
	private String profile;
	private CelebType type;
	//기본
	public Celeb() {
		super();
	}
	//파라미터
	public Celeb(int no, String name, String profile, CelebType type) {
		super();
		this.no = no;
		this.name = name;
		this.profile = profile;
		this.type = type;
	}
	//getset
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public CelebType getType() {
		return type;
	}
	public void setType(CelebType type) {
		this.type = type;
	}
	//
	@Override
	public String toString() {
		return "Celeb [no=" + no + ", name=" + name + ", profile=" + profile + ", type=" + type + "]";
	}
	
	
	
	
}
