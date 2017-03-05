package com.vc.core.model;

import java.util.Date;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class WxUser extends BaseEntity implements java.io.Serializable
{
  private static final long serialVersionUID = 5454155097114635342L;

  private String userId;
  private String userOpenId;
  private String userWxId;
  private String userName;
  private String userPhone;
  private String userPassword;
  private int userStatus;
  private int userJiFen;
  private String userSecret;
  private String userAccessToken;
  private Date userCreateTime;
  private Date userModifyTime;
  private String userCreateTimeBegin;
  private String userCreateTimeEnd;
  private String userModifyTimeBegin;
  private String userModifyTimeEnd;
  private String userAvatar;
  private String deptName;
  private String unitName;

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getUserId()
  {
    return userId;
  }

  public String getUserOpenId()
  {
    return userOpenId;
  }

  public void setUserOpenId(String userOpenId)
  {
    this.userOpenId = userOpenId;
  }

  public String getUserWxId()
  {
    return userWxId;
  }

  public void setUserWxId(String userWxId)
  {
    this.userWxId = userWxId;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserPhone(String userPhone)
  {
    this.userPhone = userPhone;
  }

  public String getUserPhone()
  {
    return userPhone;
  }

  public String getUserPassword()
  {
    return userPassword;
  }

  public void setUserPassword(String userPassword)
  {
    this.userPassword = userPassword;
  }

  public void setUserStatus(int userStatus)
  {
    this.userStatus = userStatus;
  }

  public int getUserStatus()
  {
    return userStatus;
  }

  public void setUserJiFen(int userJiFen)
  {
    this.userJiFen = userJiFen;
  }

  public int getUserJiFen()
  {
    return userJiFen;
  }

  public void setUserSecret(String userSecret)
  {
    this.userSecret = userSecret;
  }

  public String getUserSecret()
  {
    return userSecret;
  }

  public void setUserAccessToken(String userAccessToken)
  {
    this.userAccessToken = userAccessToken;
  }

  public String getUserAccessToken()
  {
    return userAccessToken;
  }

  public void setUserCreateTime(Date userCreateTime)
  {
    this.userCreateTime = userCreateTime;
  }

  public Date getUserCreateTime()
  {
    return userCreateTime;
  }

  public void setUserModifyTime(Date userModifyTime)
  {
    this.userModifyTime = userModifyTime;
  }

  public Date getUserModifyTime()
  {
    return userModifyTime;
  }

  public void setUserCreateTimeBegin(String userCreateTimeBegin)
  {
    this.userCreateTimeBegin = userCreateTimeBegin;
  }

  public String getUserCreateTimeBegin()
  {
    return userCreateTimeBegin;
  }

  public void setUserCreateTimeEnd(String userCreateTimeEnd)
  {
    this.userCreateTimeEnd = userCreateTimeEnd;
  }

  public String getUserCreateTimeEnd()
  {
    return userCreateTimeEnd;
  }

  public void setUserModifyTimeBegin(String userModifyTimeBegin)
  {
    this.userModifyTimeBegin = userModifyTimeBegin;
  }

  public String getUserModifyTimeBegin()
  {
    return userModifyTimeBegin;
  }

  public void setUserModifyTimeEnd(String userModifyTimeEnd)
  {
    this.userModifyTimeEnd = userModifyTimeEnd;
  }

  public String getUserModifyTimeEnd()
  {
    return userModifyTimeEnd;
  }

  public String getUserCreateTimeString()
  {
    return DateConvertUtils.format(getUserCreateTime(), DATE_TIME_FORMAT);
  }

  public void setUserCreateTimeString(String value)
  {
    setUserCreateTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT, Date.class));
  }

  public String getUserModifyTimeString()
  {
    return DateConvertUtils.format(getUserModifyTime(), DATE_TIME_FORMAT);
  }

  public void setUserModifyTimeString(String value)
  {
    setUserModifyTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT, Date.class));
  }

  public String getUserAvatar()
  {
    return userAvatar;
  }

  public void setUserAvatar(String userAvatar)
  {
    this.userAvatar = userAvatar;
  }

  public String getDeptName()
  {
    return deptName;
  }

  public void setDeptName(String deptName)
  {
    this.deptName = deptName;
  }

  public String getUnitName()
  {
    return unitName;
  }

  public void setUnitName(String unitName)
  {
    this.unitName = unitName;
  }

  @Override
  public String toString()
  {
    return "WxUser [userAccessToken=" + userAccessToken + ", userCreateTime=" + userCreateTime + ", userCreateTimeBegin=" + userCreateTimeBegin + ", userCreateTimeEnd="
        + userCreateTimeEnd + ", userId=" + userId + ", userJiFen=" + userJiFen + ", userModifyTime=" + userModifyTime + ", userModifyTimeBegin=" + userModifyTimeBegin
        + ", userModifyTimeEnd=" + userModifyTimeEnd + ", userName=" + userName + ", userPhone=" + userPhone + ", userPassword=" + userPassword + ", userSecret=" + userSecret
        + ", userStatus=" + userStatus + ", userOpenId=" + userOpenId + ", userWxId=" + userWxId + ", userAvatar=" + userAvatar + ", deptName=" + deptName + ", unitName="
        + unitName + "]";
  }
}
