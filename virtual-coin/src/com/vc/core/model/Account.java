package com.vc.core.model;

import java.util.Date;

public class Account {
    private Integer acctId;

    private Integer userId;

    private Double acctMoney;

    private Double blockMoney;

    private Integer acctSts;

    private Date acctCreateTime;

    private Date acctModifyTime;

    public Integer getAcctId() {
        return acctId;
    }

    public void setAcctId(Integer acctId) {
        this.acctId = acctId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAcctMoney() {
        return acctMoney;
    }

    public void setAcctMoney(Double acctMoney) {
        this.acctMoney = acctMoney;
    }

    public Double getBlockMoney() {
        return blockMoney;
    }

    public void setBlockMoney(Double blockMoney) {
        this.blockMoney = blockMoney;
    }

    public Integer getAcctSts() {
        return acctSts;
    }

    public void setAcctSts(Integer acctSts) {
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
}