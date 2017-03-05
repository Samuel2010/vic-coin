package com.vc.core.model;

import com.tw.ei.baseclass.base.BaseEntity;

public class WxPostReport extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = -2441721570863629885L;
	private Integer dataCount;
	private String field1;
	private String field2;
	private String field3;
	public Integer getDataCount() {
		return dataCount;
	}
	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
}
