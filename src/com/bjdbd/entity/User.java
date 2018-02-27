package com.bjdbd.entity;

public class User {

	private String userId;
	private String companyId;   //公司id
	private String officeId;
	private String officeName;
	private String financeId;   // 主管财务id
	private String loginName;   // 登录名
	private String password;    // 密码
	private String no;		    // 工号
	private String name;	    // 姓名
	private String email;	    // 邮箱
	private String phone;	    // 电话
	private String mobile;	    // 手机
	/**
	 * <ul>
	 * <li>1:系统用户,仅限栋邦达人员登录</li>
	 * <li>2:财务人员,仅看属于自己权限页面</li>
	 * <li>3:普通用户,仅限登录app</li>
	 * </ul>
	 */
	private String userType;    // 用户类型
	private String loginFlag;	// 是否允许登陆
	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	private String limitMoney;
	
	public String getLimitMoney() {
		return limitMoney;
	}
	public void setLimitMoney(String limitMoney) {
		this.limitMoney = limitMoney;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getFinanceId() {
		return financeId;
	}
	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	public String getOldLoginName() {
		return oldLoginName;
	}
	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public User() {
	}
	
}