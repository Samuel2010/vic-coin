package com.vc.core.model;

import java.util.Date;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class User extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 6869763197566766910L;
	private String userId;
	private String userName;
	private String userPhone;
	private String userLoginId;
	private String userLoginPwd;
	private String userTransPwd;
	private String userCard;
	private int userType;
	private int userLvl;
	private int userSts;
	private String userFid;
	private Date userCreateTime;
	private Date userModifyTime;
	private String userCreateTimeBegin;
	private String userCreateTimeEnd;
	private String userModifyTimeBegin;
	private String userModifyTimeEnd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserLoginPwd() {
		return userLoginPwd;
	}

	public void setUserLoginPwd(String userLoginPwd) {
		this.userLoginPwd = userLoginPwd;
	}

	public String getUserTransPwd() {
		return userTransPwd;
	}

	public void setUserTransPwd(String userTransPwd) {
		this.userTransPwd = userTransPwd;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getUserLvl() {
		return userLvl;
	}

	public void setUserLvl(int userLvl) {
		this.userLvl = userLvl;
	}

	public int getUserSts() {
		return userSts;
	}

	public void setUserSts(int userSts) {
		this.userSts = userSts;
	}

	public String getUserFid() {
		return userFid;
	}

	public void setUserFid(String userFid) {
		this.userFid = userFid;
	}

	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	public Date getUserCreateTime() {
		return userCreateTime;
	}

	public void setUserModifyTime(Date userModifyTime) {
		this.userModifyTime = userModifyTime;
	}

	public Date getUserModifyTime() {
		return userModifyTime;
	}

	public void setUserCreateTimeBegin(String userCreateTimeBegin) {
		this.userCreateTimeBegin = userCreateTimeBegin;
	}

	public String getUserCreateTimeBegin() {
		return userCreateTimeBegin;
	}

	public void setUserCreateTimeEnd(String userCreateTimeEnd) {
		this.userCreateTimeEnd = userCreateTimeEnd;
	}

	public String getUserCreateTimeEnd() {
		return userCreateTimeEnd;
	}

	public void setUserModifyTimeBegin(String userModifyTimeBegin) {
		this.userModifyTimeBegin = userModifyTimeBegin;
	}

	public String getUserModifyTimeBegin() {
		return userModifyTimeBegin;
	}

	public void setUserModifyTimeEnd(String userModifyTimeEnd) {
		this.userModifyTimeEnd = userModifyTimeEnd;
	}

	public String getUserModifyTimeEnd() {
		return userModifyTimeEnd;
	}

	public String getUserCreateTimeString() {
		return DateConvertUtils.format(getUserCreateTime(), DATE_TIME_FORMAT);
	}

	public void setUserCreateTimeString(String value) {
		setUserCreateTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,
				Date.class));
	}

	public String getUserModifyTimeString() {
		return DateConvertUtils.format(getUserModifyTime(), DATE_TIME_FORMAT);
	}

	public void setUserModifyTimeString(String value) {
		setUserModifyTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,
				Date.class));
	}

	@Override
	public String toString() {
		return "User [userCreateTime=" + userCreateTime
				+ ", userCreateTimeBegin=" + userCreateTimeBegin
				+ ", userCreateTimeEnd=" + userCreateTimeEnd + ", userId="
				+ userId + ", userName=" + userName + ", userModifyTime="
				+ userModifyTime + ", userModifyTimeBegin="
				+ userModifyTimeBegin + ", userModifyTimeEnd="
				+ userModifyTimeEnd + ", userName=" + userName + ", userPhone="
				+ userPhone + ", userLoginId=" + userLoginId
				+ ", userCard=" + userCard + ", userType=" + userType
				+ ", userLvl=" + userLvl + ", userSts=" + userSts
				+ ", userFid=" + userFid +  "]";
	}
}
