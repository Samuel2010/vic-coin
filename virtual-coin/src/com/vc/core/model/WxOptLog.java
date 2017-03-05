package com.vc.core.model;

import java.util.Date;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class WxOptLog extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454158383342342L;
	
	private String logId;
	private String logUserId;
	private String logPsId;
	private int logOpt;
	private String logRemark;
	private Date logCreateTime;
	private Date logModifyTime;
	private String logCreateTimeBegin;
	private String logCreateTimeEnd;
	private String logModifyTimeBegin;
	private String logModifyTimeEnd;
	
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	public String getLogId() {
		return logId;
	}

	public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogPsId() {
		return logPsId;
	}

	public void setLogPsId(String logPsId) {
		this.logPsId = logPsId;
	}

	public int getLogOpt() {
		return logOpt;
	}

	public void setLogOpt(int logOpt) {
		this.logOpt = logOpt;
	}

	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}

	public Date getLogCreateTime() {
		return logCreateTime;
	}

	public void setLogCreateTime(Date logCreateTime) {
		this.logCreateTime = logCreateTime;
	}

	public Date getLogModifyTime() {
		return logModifyTime;
	}

	public void setLogModifyTime(Date logModifyTime) {
		this.logModifyTime = logModifyTime;
	}

	public String getLogCreateTimeBegin() {
		return logCreateTimeBegin;
	}

	public void setLogCreateTimeBegin(String logCreateTimeBegin) {
		this.logCreateTimeBegin = logCreateTimeBegin;
	}

	public String getLogCreateTimeEnd() {
		return logCreateTimeEnd;
	}

	public void setLogCreateTimeEnd(String logCreateTimeEnd) {
		this.logCreateTimeEnd = logCreateTimeEnd;
	}

	public String getLogModifyTimeBegin() {
		return logModifyTimeBegin;
	}

	public void setLogModifyTimeBegin(String logModifyTimeBegin) {
		this.logModifyTimeBegin = logModifyTimeBegin;
	}

	public String getLogModifyTimeEnd() {
		return logModifyTimeEnd;
	}

	public void setLogModifyTimeEnd(String logModifyTimeEnd) {
		this.logModifyTimeEnd = logModifyTimeEnd;
	}
	
	public String getLogCreateTimeString() {
		return DateConvertUtils.format(getLogCreateTime(), DATE_TIME_FORMAT);
	}
	
	public void setLogCreateTimeString(String value) {
		setLogCreateTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,Date.class));
	}
	
	public String getLogModifyTimeString(){
		return DateConvertUtils.format(getLogModifyTime(), DATE_TIME_FORMAT);
	}
	
	public void setLogModifyTimeString(String value) {
		setLogModifyTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,Date.class));
	}

	@Override
	public String toString() {
		return "WxOptLog [logCreateTime=" + logCreateTime
				+ ", logCreateTimeBegin=" + logCreateTimeBegin
				+ ", logCreateTimeEnd=" + logCreateTimeEnd + ", logId=" + logId
				+ ", logModifyTime=" + logModifyTime + ", logModifyTimeBegin="
				+ logModifyTimeBegin + ", logModifyTimeEnd=" + logModifyTimeEnd
				+ ", logOpt=" + logOpt + ", logPsId=" + logPsId
				+ ", logRemark=" + logRemark + ", logUserId=" + logUserId + "]";
	}
}
