package com.vc.core.model;

import java.util.Date;

public class TransInfo {
    private Integer transId;

    private Integer transType;

    private Integer transMoneyId;

    private Double transUnitMoney;

    private Integer transEntrustId;

    private Integer transNum;

    private Double transMoney;

    private Date transTime;

    private Integer transSts;

    private Date transCompTime;

    private String transDesc;

    private String transBuyUser;

    private String transSellUser;

    private Double transBuyHandMoney;

    private Double transSellHandMoney;

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Integer getTransMoneyId() {
        return transMoneyId;
    }

    public void setTransMoneyId(Integer transMoneyId) {
        this.transMoneyId = transMoneyId;
    }

    public Double getTransUnitMoney() {
        return transUnitMoney;
    }

    public void setTransUnitMoney(Double transUnitMoney) {
        this.transUnitMoney = transUnitMoney;
    }

    public Integer getTransEntrustId() {
        return transEntrustId;
    }

    public void setTransEntrustId(Integer transEntrustId) {
        this.transEntrustId = transEntrustId;
    }

    public Integer getTransNum() {
        return transNum;
    }

    public void setTransNum(Integer transNum) {
        this.transNum = transNum;
    }

    public Double getTransMoney() {
        return transMoney;
    }

    public void setTransMoney(Double transMoney) {
        this.transMoney = transMoney;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public Integer getTransSts() {
        return transSts;
    }

    public void setTransSts(Integer transSts) {
        this.transSts = transSts;
    }

    public Date getTransCompTime() {
        return transCompTime;
    }

    public void setTransCompTime(Date transCompTime) {
        this.transCompTime = transCompTime;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc == null ? null : transDesc.trim();
    }

    public String getTransBuyUser() {
        return transBuyUser;
    }

    public void setTransBuyUser(String transBuyUser) {
        this.transBuyUser = transBuyUser == null ? null : transBuyUser.trim();
    }

    public String getTransSellUser() {
        return transSellUser;
    }

    public void setTransSellUser(String transSellUser) {
        this.transSellUser = transSellUser == null ? null : transSellUser.trim();
    }

    public Double getTransBuyHandMoney() {
        return transBuyHandMoney;
    }

    public void setTransBuyHandMoney(Double transBuyHandMoney) {
        this.transBuyHandMoney = transBuyHandMoney;
    }

    public Double getTransSellHandMoney() {
        return transSellHandMoney;
    }

    public void setTransSellHandMoney(Double transSellHandMoney) {
        this.transSellHandMoney = transSellHandMoney;
    }
}