package com.taokoo.model;
/**
 * 用户表
 * @author Taokoo
 *
 */
public class SgdUser {
	
	private String id;
	
	private String userName;//账号
	
	private String passWord;//密码
	
	private String stuName;//学生姓名
	
	private String stuMail;//邮箱
	
	private String stuPhone;//手机
	
	private String stuAddress;//楼栋

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuMail() {
		return stuMail;
	}

	public void setStuMail(String stuMail) {
		this.stuMail = stuMail;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public String getStuAddress() {
		return stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
}
