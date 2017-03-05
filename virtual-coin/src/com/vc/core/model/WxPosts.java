package com.vc.core.model;

import java.util.Date;
import java.util.List;

import com.tw.ei.baseclass.base.BaseEntity;
import com.tw.ei.baseclass.util.DateConvertUtils;

public class WxPosts extends BaseEntity implements java.io.Serializable,Comparable
{
  private static final long serialVersionUID = 5454155097193832342L;

  private Integer id;
  private String psId;
  private Integer psSecId;
  private String psTitle;
  private String psUserId;
  private String psUserName;
  private String psContent;
  private Integer psStatus;
  private Integer psCheckStatus;
  private Integer psPostType;
  private Integer psViews;
  private Integer psReply;
  private String psParentId;
  private Integer psIsTop;//普通模块：是否置顶，0-否，1-是；党员E家：标识是否“常见问题”
  private Integer psIsDoing;//普通模块：是否完成，0-未完成，1-已完成；对于党员E家模块，此字段标识是否已有管理员，0-未回复，1-已回复
  private Date psCreateTime;
  private Date psModifyTime;
  private String psField1;//图标
  private String psField2;//好书推荐-作者
  private String psField3;//管理员回复标志；子贴：标识是否管理员回复，后续查看帖子时取历史用户权限，不取实时
  private String psField4;//好书推广 保存url
  private String psField5;//保存点赞数
  private String psField6;//保存审核意见
  private String psField7;
  private String psField8;
  private String psField9;
  private String psField10;
  private String psCreateTimeBegin;
  private String psCreateTimeEnd;
  private String psModifyTimeBegin;
  private String psModifyTimeEnd;
  private Integer atnStatus;
  private String atnUserId;
  private Integer atnThumbsStatus;
  private String userAvatar;
  private String psIsAttention;
  private Integer msgCount;
  private String fileId;
  private String fileName;
  private String filePath;
  private Integer fileType;
  private Integer fileIsPic;
  private Integer fileStatus;
  private String filePsId;
  private Integer fileOrder;
  private Date fileCreateTime;
  private Integer sortType;//排序方式
  private String logId;//积分记录ID，平台推广活动处使用
  private String logUserId;
  
  
  private List<WxPosts> subPostList;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public void setPsId(String psId)
  {
    this.psId = psId;
  }

  public String getPsId()
  {
    return psId;
  }

  public Integer getPsSecId()
  {
    return psSecId;
  }

  public void setPsSecId(Integer psSecId)
  {
    this.psSecId = psSecId;
  }

  public String getPsTitle()
  {
    return psTitle;
  }

  public void setPsTitle(String psTitle)
  {
    this.psTitle = psTitle;
  }

  public String getPsUserId()
  {
    return psUserId;
  }

  public void setPsUserId(String psUserId)
  {
    this.psUserId = psUserId;
  }

  public String getPsUserName()
  {
    return psUserName;
  }

  public void setPsUserName(String psUserName)
  {
    this.psUserName = psUserName;
  }

  public String getPsContent()
  {
    return psContent;
  }

  public void setPsContent(String psContent)
  {
    this.psContent = psContent;
  }

  public Integer getPsStatus()
  {
    return psStatus;
  }

  public void setPsStatus(Integer psStatus)
  {
    this.psStatus = psStatus;
  }

  public Integer getPsCheckStatus()
  {
    return psCheckStatus;
  }

  public void setPsCheckStatus(Integer psCheckStatus)
  {
    this.psCheckStatus = psCheckStatus;
  }

  public Integer getPsPostType()
  {
    return psPostType;
  }

  public void setPsPostType(Integer psPostType)
  {
    this.psPostType = psPostType;
  }

  public Integer getPsViews()
  {
    return psViews;
  }

  public void setPsViews(Integer psViews)
  {
    this.psViews = psViews;
  }

  public Integer getPsReply()
  {
    return psReply;
  }

  public void setPsReply(Integer psReply)
  {
    this.psReply = psReply;
  }

  public String getPsParentId()
  {
    return psParentId;
  }

  public void setPsParentId(String psParentId)
  {
    this.psParentId = psParentId;
  }

  public Integer getPsIsTop()
  {
    return psIsTop;
  }

  public void setPsIsTop(Integer psIsTop)
  {
    this.psIsTop = psIsTop;
  }

  public Integer getPsIsDoing()
  {
    return psIsDoing;
  }

  public void setPsIsDoing(Integer psIsDoing)
  {
    this.psIsDoing = psIsDoing;
  }

  public Date getPsCreateTime()
  {
    return psCreateTime;
  }

  public void setPsCreateTime(Date psCreateTime)
  {
    this.psCreateTime = psCreateTime;
  }

  public Date getPsModifyTime()
  {
    return psModifyTime;
  }

  public void setPsModifyTime(Date psModifyTime)
  {
    this.psModifyTime = psModifyTime;
  }

  public String getPsField1()
  {
    return psField1;
  }

  public void setPsField1(String psField1)
  {
    this.psField1 = psField1;
  }

  public String getPsField2()
  {
    return psField2;
  }

  public void setPsField2(String psField2)
  {
    this.psField2 = psField2;
  }

  public String getPsField3()
  {
    return psField3;
  }

  public void setPsField3(String psField3)
  {
    this.psField3 = psField3;
  }

  public String getPsField4()
  {
    return psField4;
  }

  public void setPsField4(String psField4)
  {
    this.psField4 = psField4;
  }

  public String getPsField5()
  {
    return psField5;
  }

  public void setPsField5(String psField5)
  {
    this.psField5 = psField5;
  }

  public String getPsField6()
  {
    return psField6;
  }

  public void setPsField6(String psField6)
  {
    this.psField6 = psField6;
  }

  public String getPsField7()
  {
    return psField7;
  }

  public void setPsField7(String psField7)
  {
    this.psField7 = psField7;
  }

  public String getPsField8()
  {
    return psField8;
  }

  public void setPsField8(String psField8)
  {
    this.psField8 = psField8;
  }

  public String getPsField9()
  {
    return psField9;
  }

  public void setPsField9(String psField9)
  {
    this.psField9 = psField9;
  }

  public String getPsField10()
  {
    return psField10;
  }

  public void setPsField10(String psField10)
  {
    this.psField10 = psField10;
  }

  public String getPsCreateTimeBegin()
  {
    return psCreateTimeBegin;
  }

  public void setPsCreateTimeBegin(String psCreateTimeBegin)
  {
    this.psCreateTimeBegin = psCreateTimeBegin;
  }

  public String getPsCreateTimeEnd()
  {
    return psCreateTimeEnd;
  }

  public void setPsCreateTimeEnd(String psCreateTimeEnd)
  {
    this.psCreateTimeEnd = psCreateTimeEnd;
  }

  public String getPsModifyTimeBegin()
  {
    return psModifyTimeBegin;
  }

  public void setPsModifyTimeBegin(String psModifyTimeBegin)
  {
    this.psModifyTimeBegin = psModifyTimeBegin;
  }

  public String getPsModifyTimeEnd()
  {
    return psModifyTimeEnd;
  }

  public void setPsModifyTimeEnd(String psModifyTimeEnd)
  {
    this.psModifyTimeEnd = psModifyTimeEnd;
  }

  public String getPsCreateTimeString()
  {
    return DateConvertUtils.format(getPsCreateTime(), DATE_TIME_FORMAT);
  }

  public void setPsCreateTimeString(String value)
  {
    setPsCreateTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT, Date.class));
  }

  public String getPsModifyTimeString()
  {
    return DateConvertUtils.format(getPsModifyTime(), DATE_TIME_FORMAT);
  }

  public void setPsModifyTimeString(String value)
  {
    setPsModifyTime(DateConvertUtils.parse(value, DATE_TIME_FORMAT, Date.class));
  }

  public Integer getAtnStatus()
  {
    return atnStatus;
  }

  public void setAtnStatus(Integer atnStatus)
  {
    this.atnStatus = atnStatus;
  }

  public String getAtnUserId()
  {
    return atnUserId;
  }

  public void setAtnUserId(String atnUserId)
  {
    this.atnUserId = atnUserId;
  }

  public String getUserAvatar()
  {
    return userAvatar;
  }

  public void setUserAvatar(String userAvatar)
  {
    this.userAvatar = userAvatar;
  }

  public String getPsIsAttention()
  {
    return psIsAttention;
  }

  public void setPsIsAttention(String psIsAttention)
  {
    this.psIsAttention = psIsAttention;
  }
  

	public Integer getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}
	
	

    public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getFileIsPic() {
		return fileIsPic;
	}

	public void setFileIsPic(Integer fileIsPic) {
		this.fileIsPic = fileIsPic;
	}

	public Integer getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(Integer fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getFilePsId() {
		return filePsId;
	}

	public void setFilePsId(String filePsId) {
		this.filePsId = filePsId;
	}

	public Integer getFileOrder() {
		return fileOrder;
	}

	public void setFileOrder(Integer fileOrder) {
		this.fileOrder = fileOrder;
	}

	public Date getFileCreateTime() {
		return fileCreateTime;
	}

	public void setFileCreateTime(Date fileCreateTime) {
		this.fileCreateTime = fileCreateTime;
	}
	


	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}
	
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public List<WxPosts> getSubPostList() {
		return subPostList;
	}

	public void setSubPostList(List<WxPosts> subPostList) {
		this.subPostList = subPostList;
	}

	public Integer getAtnThumbsStatus() {
		return atnThumbsStatus;
	}

	public void setAtnThumbsStatus(Integer atnThumbsStatus) {
		this.atnThumbsStatus = atnThumbsStatus;
	}
	
	

public String getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(String logUserId) {
		this.logUserId = logUserId;
	}

@Override
  public String toString()
  {
    return "WxPosts [psCheckStatus=" + psCheckStatus + ", psContent=" + psContent + ", psCreateTime=" + psCreateTime + ", psCreateTimeBegin=" + psCreateTimeBegin
        + ", psCreateTimeEnd=" + psCreateTimeEnd + ", psField1=" + psField1 + ", psField2=" + psField2 + ", psField3=" + psField3 + ", psField4=" + psField4 + ", psField5="
        + psField5 + ", psField6=" + psField6 + ", psField7=" + psField7 + ", psField8=" + psField8 + ", psField9=" + psField9 + ", psField10=" + psField10 + ", psId=" + psId
        + ", psIsTop=" + psIsTop + ", psModifyTime=" + psModifyTime + ", psModifyTimeBegin=" + psModifyTimeBegin + ", psModifyTimeEnd=" + psModifyTimeEnd + ", psParentId="
        + psParentId + ", psPostType=" + psPostType + ", psSecId=" + psSecId + ", psStatus=" + psStatus + ", psTitle=" + psTitle + ", psUserId=" + psUserId + ", psUserName="
        + psUserName + ", psViews=" + psViews + ", psIsDoing=" + psIsDoing + ", psReply=" + psReply + ",atnStatus=" + atnStatus + ",atnUserId=" + atnUserId + ",userAvatar="
        + userAvatar + "]";
  }

	@Override
	public int compareTo(Object o) {
		WxPosts post = (WxPosts)o;
		return this.psCreateTime.compareTo(post.getPsCreateTime());
	}
}
