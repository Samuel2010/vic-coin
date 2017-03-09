package com.vc.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

import com.tw.ei.baseclass.base.*;
import com.tw.ei.baseclass.util.*;
import com.tw.ei.baseclass.page.*;

import com.vc.core.model.*;
import com.vc.core.dao.*;
import com.vc.core.service.*;

/**
 * @author lw
 * @version 1.0
 * @since  
 */


public class TransInfo extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	//date formats
	public static final String FORMAT_TRANS_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_TRANS_COMP_TIME = DATE_TIME_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * transId       db_column: trans_id 
     */	
	
	private Integer transId;
    /**
     * transType       db_column: trans_type 
     */	
	
	private Integer transType;
    /**
     * transMoneyId       db_column: trans_money_id 
     */	
	
	private Integer transMoneyId;
    /**
     * transUnitMoney       db_column: trans_unit_money 
     */	
	
	private Double transUnitMoney;
    /**
     * transEntrustId       db_column: trans_entrust_id 
     */	
	
	private Integer transEntrustId;
    /**
     * transNum       db_column: trans_num 
     */	
	
	private Integer transNum;
    /**
     * transMoney       db_column: trans_money 
     */	
	
	private Double transMoney;
    /**
     * transTime       db_column: trans_time 
     */	
	private Date transTime;
    /**
     * transSts       db_column: trans_sts 
     */	
	
	private Integer transSts;
    /**
     * transCompTime       db_column: trans_comp_time 
     */	
	private Date transCompTime;
    /**
     * transDesc       db_column: trans_desc 
     */	
	private String transDesc;
    /**
     * transBuyUser       db_column: trans_buy_user 
     */	
	private String transBuyUser;
    /**
     * transSellUser       db_column: trans_sell_user 
     */	
	private String transSellUser;
    /**
     * transBuyHandMoney       db_column: trans_buy_hand_money 
     */	
	
	private Double transBuyHandMoney;
    /**
     * transSellHandMoney       db_column: trans_sell_hand_money 
     */	
	
	private Double transSellHandMoney;
	
	public String transTimeBegin;
	public String transTimeEnd;
	public String transCompTimeBegin;
	public String transCompTimeEnd;
	
	//columns END

	public TransInfo(){
	}

	public TransInfo(
		Integer transId
	){
		this.transId = transId;
	}

	public void setTransId(Integer value) {
		this.transId = value;
	}
	
	public Integer getTransId() {
		return this.transId;
	}
	public void setTransType(Integer value) {
		this.transType = value;
	}
	
	public Integer getTransType() {
		return this.transType;
	}
	public void setTransMoneyId(Integer value) {
		this.transMoneyId = value;
	}
	
	public Integer getTransMoneyId() {
		return this.transMoneyId;
	}
	public void setTransUnitMoney(Double value) {
		this.transUnitMoney = value;
	}
	
	public Double getTransUnitMoney() {
		return this.transUnitMoney;
	}
	public void setTransEntrustId(Integer value) {
		this.transEntrustId = value;
	}
	
	public Integer getTransEntrustId() {
		return this.transEntrustId;
	}
	public void setTransNum(Integer value) {
		this.transNum = value;
	}
	
	public Integer getTransNum() {
		return this.transNum;
	}
	public void setTransMoney(Double value) {
		this.transMoney = value;
	}
	
	public Double getTransMoney() {
		return this.transMoney;
	}
	public String getTransTimeString() {
		return DateConvertUtils.format(getTransTime(), FORMAT_TRANS_TIME);
	}
	public void setTransTimeString(String value) {
		setTransTime(DateConvertUtils.parse(value, FORMAT_TRANS_TIME,Date.class));
	}
	
	public void setTransTime(Date value) {
		this.transTime = value;
	}
	
	public Date getTransTime() {
		return this.transTime;
	}
	public void setTransSts(Integer value) {
		this.transSts = value;
	}
	
	public Integer getTransSts() {
		return this.transSts;
	}
	public String getTransCompTimeString() {
		return DateConvertUtils.format(getTransCompTime(), FORMAT_TRANS_COMP_TIME);
	}
	public void setTransCompTimeString(String value) {
		setTransCompTime(DateConvertUtils.parse(value, FORMAT_TRANS_COMP_TIME,Date.class));
	}
	
	public void setTransCompTime(Date value) {
		this.transCompTime = value;
	}
	
	public Date getTransCompTime() {
		return this.transCompTime;
	}
	public void setTransDesc(String value) {
		this.transDesc = value;
	}
	
	public String getTransDesc() {
		return this.transDesc;
	}
	public void setTransBuyUser(String value) {
		this.transBuyUser = value;
	}
	
	public String getTransBuyUser() {
		return this.transBuyUser;
	}
	public void setTransSellUser(String value) {
		this.transSellUser = value;
	}
	
	public String getTransSellUser() {
		return this.transSellUser;
	}
	public void setTransBuyHandMoney(Double value) {
		this.transBuyHandMoney = value;
	}
	
	public Double getTransBuyHandMoney() {
		return this.transBuyHandMoney;
	}
	public void setTransSellHandMoney(Double value) {
		this.transSellHandMoney = value;
	}
	
	public Double getTransSellHandMoney() {
		return this.transSellHandMoney;
	}
	

	public String getTransTimeBegin() {
		return transTimeBegin;
	}

	public void setTransTimeBegin(String transTimeBegin) {
		this.transTimeBegin = transTimeBegin;
	}

	public String getTransTimeEnd() {
		return transTimeEnd;
	}

	public void setTransTimeEnd(String transTimeEnd) {
		this.transTimeEnd = transTimeEnd;
	}

	public String getTransCompTimeBegin() {
		return transCompTimeBegin;
	}

	public void setTransCompTimeBegin(String transCompTimeBegin) {
		this.transCompTimeBegin = transCompTimeBegin;
	}

	public String getTransCompTimeEnd() {
		return transCompTimeEnd;
	}

	public void setTransCompTimeEnd(String transCompTimeEnd) {
		this.transCompTimeEnd = transCompTimeEnd;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTransId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TransInfo == false) return false;
		if(this == obj) return true;
		TransInfo other = (TransInfo)obj;
		return new EqualsBuilder()
			.append(getTransId(),other.getTransId())
			.isEquals();
	}
}

