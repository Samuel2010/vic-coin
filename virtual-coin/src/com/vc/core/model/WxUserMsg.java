package com.vc.core.model;

import java.util.Date;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class WxUserMsg  extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 543211282736102342L;
	
	private String msgId;
	private String msgUserId;
	private String msgContent;
	private int msgStatus;
	private Date msgCreateTime;
	private Date msgModifyTime;
	private String msgCreateTimeBegin;
	private String msgCreateTimeEnd;
	private String msgModifyTimeBegin;
	private String msgMoidfyTimeEnd;
	private String msgParentPsId;
    private String msgPsId;
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getMsgId() {
		return msgId;
	}

	public String getMsgUserId() {
		return msgUserId;
	}

	public void setMsgUserId(String msgUserId) {
		this.msgUserId = msgUserId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public int getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(int msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Date getMsgCreateTime() {
		return msgCreateTime;
	}

	public void setMsgCreateTime(Date msgCreateTime) {
		this.msgCreateTime = msgCreateTime;
	}

	public Date getMsgModifyTime() {
		return msgModifyTime;
	}

	public void setMsgModifyTime(Date msgModifyTime) {
		this.msgModifyTime = msgModifyTime;
	}

	public String getMsgCreateTimeBegin() {
		return msgCreateTimeBegin;
	}

	public void setMsgCreateTimeBegin(String msgCreateTimeBegin) {
		this.msgCreateTimeBegin = msgCreateTimeBegin;
	}

	public String getMsgCreateTimeEnd() {
		return msgCreateTimeEnd;
	}

	public void setMsgCreateTimeEnd(String msgCreateTimeEnd) {
		this.msgCreateTimeEnd = msgCreateTimeEnd;
	}

	public String getMsgModifyTimeBegin() {
		return msgModifyTimeBegin;
	}

	public void setMsgModifyTimeBegin(String msgModifyTimeBegin) {
		this.msgModifyTimeBegin = msgModifyTimeBegin;
	}

	public String getMsgMoidfyTimeEnd() {
		return msgMoidfyTimeEnd;
	}

	public void setMsgMoidfyTimeEnd(String msgMoidfyTimeEnd) {
		this.msgMoidfyTimeEnd = msgMoidfyTimeEnd;
	}
	
	public String getMsgCreateTimeString() {
		return DateConvertUtils.format(getMsgCreateTime(), DATE_TIME_FORMAT);
	}
	
	public void setMsgCreateTimeString(String value) {
		setMsgCreateTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,Date.class));
	}
	
	public String getMsgModifyTimeString(){
		return DateConvertUtils.format(getMsgModifyTime(), DATE_TIME_FORMAT);
	}
	
	public void setMsgModifyTimeString(String value) {
		setMsgModifyTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT,Date.class));
	}

	public String getMsgParentPsId() {
		return msgParentPsId;
	}

	public void setMsgParentPsId(String msgParentPsId) {
		this.msgParentPsId = msgParentPsId;
	}

	public String getMsgPsId() {
		return msgPsId;
	}

	public void setMsgPsId(String msgPsId) {
		this.msgPsId = msgPsId;
	}
	
}
