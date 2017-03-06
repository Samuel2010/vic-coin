package com.vc.core.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;


public class Account extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//date formats
	public static final String FORMAT_ACCT_CREATE_TIME = DATE_FORMAT;
	public static final String FORMAT_ACCT_MODIFY_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * acctId       db_column: acct_id 
     */	
	
	private Integer acctId;
    /**
     * userId       db_column: user_id 
     */	
	
	private Integer userId;
    /**
     * acctMoney       db_column: acct_money 
     */	
	
	private Double acctMoney;
    /**
     * blockMoney       db_column: block_money 
     */	
	
	private Double blockMoney;
    /**
     * acctSts       db_column: acct_sts 
     */	
	
	private Integer acctSts;
    /**
     * acctCreateTime       db_column: acct_create_time 
     */	
	
	private Date acctCreateTime;
    /**
     * acctModifyTime       db_column: acct_modify_time 
     */	
	
	private Date acctModifyTime;
	//columns END

	public Account(){
	}

	public Account(
		Integer acctId
	){
		this.acctId = acctId;
	}

	public void setAcctId(Integer value) {
		this.acctId = value;
	}
	
	public Integer getAcctId() {
		return this.acctId;
	}
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setAcctMoney(Double value) {
		this.acctMoney = value;
	}
	
	public Double getAcctMoney() {
		return this.acctMoney;
	}
	public void setBlockMoney(Double value) {
		this.blockMoney = value;
	}
	
	public Double getBlockMoney() {
		return this.blockMoney;
	}
	public void setAcctSts(Integer value) {
		this.acctSts = value;
	}
	
	public Integer getAcctSts() {
		return this.acctSts;
	}
	public String getAcctCreateTimeString() {
		return DateConvertUtils.format(getAcctCreateTime(), FORMAT_ACCT_CREATE_TIME);
	}
	public void setAcctCreateTimeString(String value) {
		setAcctCreateTime(DateConvertUtils.parse(value, FORMAT_ACCT_CREATE_TIME,Date.class));
	}
	
	public void setAcctCreateTime(Date value) {
		this.acctCreateTime = value;
	}
	
	public Date getAcctCreateTime() {
		return this.acctCreateTime;
	}
	public String getAcctModifyTimeString() {
		return DateConvertUtils.format(getAcctModifyTime(), FORMAT_ACCT_MODIFY_TIME);
	}
	public void setAcctModifyTimeString(String value) {
		setAcctModifyTime(DateConvertUtils.parse(value, FORMAT_ACCT_MODIFY_TIME,Date.class));
	}
	
	public void setAcctModifyTime(Date value) {
		this.acctModifyTime = value;
	}
	
	public Date getAcctModifyTime() {
		return this.acctModifyTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAcctId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Account == false) return false;
		if(this == obj) return true;
		Account other = (Account)obj;
		return new EqualsBuilder()
			.append(getAcctId(),other.getAcctId())
			.isEquals();
	}
}

