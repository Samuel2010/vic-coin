package com.vc.core.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class EntrustInfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//date formats
	public static final String FORMAT_ENTRUST_TIME = DATE_FORMAT;
	public static final String FORMAT_ENTRUST_LAST_TIME = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * entrustId       db_column: entrust_id 
     */	
	
	private Integer entrustId;
    /**
     * entrustMoneyId       db_column: entrust_money_id 
     */	
	
	private Integer entrustMoneyId;
    /**
     * entrustType       db_column: entrust_type 
     */	
	
	private Integer entrustType;
    /**
     * entrustUnitMoney       db_column: entrust_unit_money 
     */	
	
	private Double entrustUnitMoney;
    /**
     * entrustNum       db_column: entrust_num 
     */	
	
	private Integer entrustNum;
    /**
     * entrustMoney       db_column: entrust_money 
     */	
	
	private Double entrustMoney;
    /**
     * entrustTime       db_column: entrust_time 
     */	
	private Date entrustTime;
    /**
     * entrustCompMoney       db_column: entrust_comp_money 
     */	
	
	private Double entrustCompMoney;
    /**
     * entrustSts       db_column: entrust_sts 
     */	
	
	private Integer entrustSts;
    /**
     * entrustLastTime       db_column: entrust_last_time 
     */	
	private Date entrustLastTime;
    /**
     * entrustDesc       db_column: entrust_desc 
     */	
	private String entrustDesc;
	//columns END

	public EntrustInfo(){
	}

	public EntrustInfo(
		Integer entrustId
	){
		this.entrustId = entrustId;
	}

	public void setEntrustId(Integer value) {
		this.entrustId = value;
	}
	
	public Integer getEntrustId() {
		return this.entrustId;
	}
	public void setEntrustMoneyId(Integer value) {
		this.entrustMoneyId = value;
	}
	
	public Integer getEntrustMoneyId() {
		return this.entrustMoneyId;
	}
	public void setEntrustType(Integer value) {
		this.entrustType = value;
	}
	
	public Integer getEntrustType() {
		return this.entrustType;
	}
	public void setEntrustUnitMoney(Double value) {
		this.entrustUnitMoney = value;
	}
	
	public Double getEntrustUnitMoney() {
		return this.entrustUnitMoney;
	}
	public void setEntrustNum(Integer value) {
		this.entrustNum = value;
	}
	
	public Integer getEntrustNum() {
		return this.entrustNum;
	}
	public void setEntrustMoney(Double value) {
		this.entrustMoney = value;
	}
	
	public Double getEntrustMoney() {
		return this.entrustMoney;
	}
	public String getEntrustTimeString() {
		return DateConvertUtils.format(getEntrustTime(), FORMAT_ENTRUST_TIME);
	}
	public void setEntrustTimeString(String value) {
		setEntrustTime(DateConvertUtils.parse(value, FORMAT_ENTRUST_TIME,Date.class));
	}
	
	public void setEntrustTime(Date value) {
		this.entrustTime = value;
	}
	
	public Date getEntrustTime() {
		return this.entrustTime;
	}
	public void setEntrustCompMoney(Double value) {
		this.entrustCompMoney = value;
	}
	
	public Double getEntrustCompMoney() {
		return this.entrustCompMoney;
	}
	public void setEntrustSts(Integer value) {
		this.entrustSts = value;
	}
	
	public Integer getEntrustSts() {
		return this.entrustSts;
	}
	public String getEntrustLastTimeString() {
		return DateConvertUtils.format(getEntrustLastTime(), FORMAT_ENTRUST_LAST_TIME);
	}
	public void setEntrustLastTimeString(String value) {
		setEntrustLastTime(DateConvertUtils.parse(value, FORMAT_ENTRUST_LAST_TIME,Date.class));
	}
	
	public void setEntrustLastTime(Date value) {
		this.entrustLastTime = value;
	}
	
	public Date getEntrustLastTime() {
		return this.entrustLastTime;
	}
	public void setEntrustDesc(String value) {
		this.entrustDesc = value;
	}
	
	public String getEntrustDesc() {
		return this.entrustDesc;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getEntrustId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof EntrustInfo == false) return false;
		if(this == obj) return true;
		EntrustInfo other = (EntrustInfo)obj;
		return new EqualsBuilder()
			.append(getEntrustId(),other.getEntrustId())
			.isEquals();
	}
}

