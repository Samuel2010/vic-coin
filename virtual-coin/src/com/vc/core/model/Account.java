package com.vc.core.model;

import java.util.Date;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class Account extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 5244712331334711769L;
	private String acctId;
	private String userId;
	private double acctMoney;
	private double blockMoney;
	private int acctSts;
	private Date acctCreateTime;
	private Date acctModifyTime;
	private String acctCreateTimeBegin;
	private String acctCreateTimeEnd;
	private String acctModifyTimeBegin;
	private String acctModifyTimeEnd;

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getAcctMoney() {
		return acctMoney;
	}

	public void setAcctMoney(double acctMoney) {
		this.acctMoney = acctMoney;
	}

	public double getBlockMoney() {
		return blockMoney;
	}

	public void setBlockMoney(double blockMoney) {
		this.blockMoney = blockMoney;
	}

	public int getAcctSts() {
		return acctSts;
	}

	public void setAcctSts(int acctSts) {
		this.acctSts = acctSts;
	}

	public Date getAcctCreateTime() {
		return acctCreateTime;
	}

	public void setAcctCreateTime(Date acctCreateTime) {
		this.acctCreateTime = acctCreateTime;
	}

	public Date getAcctModifyTime() {
		return acctModifyTime;
	}

	public void setAcctModifyTime(Date acctModifyTime) {
		this.acctModifyTime = acctModifyTime;
	}

	public String getAcctCreateTimeBegin() {
		return acctCreateTimeBegin;
	}

	public void setAcctCreateTimeBegin(String acctCreateTimeBegin) {
		this.acctCreateTimeBegin = acctCreateTimeBegin;
	}

	public String getAcctCreateTimeEnd() {
		return acctCreateTimeEnd;
	}

	public void setAcctCreateTimeEnd(String acctCreateTimeEnd) {
		this.acctCreateTimeEnd = acctCreateTimeEnd;
	}

	public String getAcctModifyTimeBegin() {
		return acctModifyTimeBegin;
	}

	public void setAcctModifyTimeBegin(String acctModifyTimeBegin) {
		this.acctModifyTimeBegin = acctModifyTimeBegin;
	}

	public String getAcctModifyTimeEnd() {
		return acctModifyTimeEnd;
	}

	public void setAcctModifyTimeEnd(String acctModifyTimeEnd) {
		this.acctModifyTimeEnd = acctModifyTimeEnd;
	}

	public String getAcctCreateTimeString() {
		return DateConvertUtils.format(getAcctCreateTime(), DATE_TIME_FORMAT);
	}

	public void setAcctCreateTimeString(String value) {
		setAcctCreateTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,
				Date.class));
	}

	public String getAcctModifyTimeString() {
		return DateConvertUtils.format(getAcctModifyTime(), DATE_TIME_FORMAT);
	}

	public void setAcctModifyTimeString(String value) {
		setAcctModifyTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,
				Date.class));
	}

	@Override
	public String toString() {
		return "User [acctId=" + acctId + ", userId=" + userId + ", acctMoney="
				+ acctMoney + ", blockMoney=" + blockMoney + ", acctSts="
				+ acctSts + ", acctCreateTime=" + acctCreateTime
				+ ", acctModifyTime=" + acctModifyTime
				+ ", acctCreateTimeBegin=" + acctCreateTimeBegin
				+ ", acctCreateTimeEnd=" + acctCreateTimeEnd
				+ ", acctModifyTimeBegin=" + acctModifyTimeBegin
				+ ", acctModifyTimeEnd=" + acctModifyTimeEnd + "]";
	}
}
