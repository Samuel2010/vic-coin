package com.vc.core.model;

import java.util.Date;

public class EntrustInfo {
    private Integer entrustId;

    private Integer entrustMoneyId;

    private Integer entrustType;

    private Double entrustUnitMoney;

    private Integer entrustNum;

    private Double entrustMoney;

    private Date entrustTime;

    private Double entrustCompMoney;

    private Integer entrustSts;

    private Date entrustLastTime;

    private String entrustDesc;

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getEntrustMoneyId() {
        return entrustMoneyId;
    }

    public void setEntrustMoneyId(Integer entrustMoneyId) {
        this.entrustMoneyId = entrustMoneyId;
    }

    public Integer getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(Integer entrustType) {
        this.entrustType = entrustType;
    }

    public Double getEntrustUnitMoney() {
        return entrustUnitMoney;
    }

    public void setEntrustUnitMoney(Double entrustUnitMoney) {
        this.entrustUnitMoney = entrustUnitMoney;
    }

    public Integer getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(Integer entrustNum) {
        this.entrustNum = entrustNum;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Double getEntrustCompMoney() {
        return entrustCompMoney;
    }

    public void setEntrustCompMoney(Double entrustCompMoney) {
        this.entrustCompMoney = entrustCompMoney;
    }

    public Integer getEntrustSts() {
        return entrustSts;
    }

    public void setEntrustSts(Integer entrustSts) {
        this.entrustSts = entrustSts;
    }

    public Date getEntrustLastTime() {
        return entrustLastTime;
    }

    public void setEntrustLastTime(Date entrustLastTime) {
        this.entrustLastTime = entrustLastTime;
    }

    public String getEntrustDesc() {
        return entrustDesc;
    }

    public void setEntrustDesc(String entrustDesc) {
        this.entrustDesc = entrustDesc == null ? null : entrustDesc.trim();
    }
}