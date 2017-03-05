package com.vc.core.model;

import java.util.Date;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class WxUserRight extends BaseEntity implements java.io.Serializable
{
  private static final long serialVersionUID = 545492827276102342L;

  private String rightId;
  private String rightUserId;
  private Integer rightSecId;
  private Integer rightSysManager;
  private Integer rightSecManager;
  private Date rightCreateTime;
  private Date rightModifyTime;
  private String rightCreateTimeBegin;
  private String rightCreateTimeEnd;
  private String rightModifyTimeBegin;
  private String rightModifyTimeEnd;

  public void setRightId(String rightId)
  {
    this.rightId = rightId;
  }

  public String getRightId()
  {
    return rightId;
  }

  public void setRightUserId(String rightUserId)
  {
    this.rightUserId = rightUserId;
  }

  public String getRightUserId()
  {
    return rightUserId;
  }

  public void setRightSecId(Integer rightSecId)
  {
    this.rightSecId = rightSecId;
  }

  public Integer getRightSecId()
  {
    return rightSecId;
  }

  public void setRightSysManager(Integer rightSysManager)
  {
    this.rightSysManager = rightSysManager;
  }

  public Integer getRightSysManager()
  {
    return rightSysManager;
  }

  public void setRightSecManager(Integer rightSecManager)
  {
    this.rightSecManager = rightSecManager;
  }

  public Integer getRightSecManager()
  {
    return rightSecManager;
  }

  public void setRightCreateTime(Date rightCreateTime)
  {
    this.rightCreateTime = rightCreateTime;
  }

  public Date getRightCreateTime()
  {
    return rightCreateTime;
  }

  public void setRightModifyTime(Date rightModifyTime)
  {
    this.rightModifyTime = rightModifyTime;
  }

  public Date getRightModifyTime()
  {
    return rightModifyTime;
  }

  public void setRightCreateTimeBegin(String rightCreateTimeBegin)
  {
    this.rightCreateTimeBegin = rightCreateTimeBegin;
  }

  public String getRightCreateTimeBegin()
  {
    return rightCreateTimeBegin;
  }

  public void setRightCreateTimeEnd(String rightCreateTimeEnd)
  {
    this.rightCreateTimeEnd = rightCreateTimeEnd;
  }

  public String getRightCreateTimeEnd()
  {
    return rightCreateTimeEnd;
  }

  public void setRightModifyTimeBegin(String rightModifyTimeBegin)
  {
    this.rightModifyTimeBegin = rightModifyTimeBegin;
  }

  public String getRightModifyTimeBegin()
  {
    return rightModifyTimeBegin;
  }

  public void setRightModifyTimeEnd(String rightModifyTimeEnd)
  {
    this.rightModifyTimeEnd = rightModifyTimeEnd;
  }

  public String getRightModifyTimeEnd()
  {
    return rightModifyTimeEnd;
  }

  public String getRightCreateTimeString()
  {
    return DateConvertUtils.format(getRightCreateTime(), DATE_TIME_FORMAT);
  }

  public void setRightCreateTimeString(String value)
  {
    setRightCreateTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT, Date.class));
  }

  public String getRightModifyTimeString()
  {
    return DateConvertUtils.format(getRightModifyTime(), DATE_TIME_FORMAT);
  }

  public void setRightModifyTimeString(String value)
  {
    setRightModifyTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT, Date.class));
  }

  @Override
  public String toString()
  {
    return "WxUserRight [rightCreateTime=" + rightCreateTime + ", rightCreateTimeBegin=" + rightCreateTimeBegin + ", rightCreateTimeEnd=" + rightCreateTimeEnd + ", rightId="
        + rightId + ", rightModifyTime=" + rightModifyTime + ", rightModifyTimeBegin=" + rightModifyTimeBegin + ", rightModifyTimeEnd=" + rightModifyTimeEnd + ", rightSecId="
        + rightSecId + ", rightSecManager=" + rightSecManager + ", rightSysManager=" + rightSysManager + ", rightUserId=" + rightUserId + "]";
  }
}
