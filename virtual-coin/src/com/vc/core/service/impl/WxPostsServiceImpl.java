package com.vc.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.ei.baseclass.page.Page;
import com.vc.core.dao.WxPostsDao;
import com.vc.core.dao.WxUserMsgDao;
import com.vc.core.model.WxPosts;
import com.vc.core.model.WxUserMsg;
import com.vc.core.service.WxPostsService;
import com.vc.core.util.WeiXinUtil;

@Service
public class WxPostsServiceImpl implements WxPostsService
{
  private static Logger logger = Logger.getLogger(WxPostsServiceImpl.class);

  @Autowired
  private WxPostsDao postsDao;
  
  @Autowired
  private WxUserMsgDao userMsgDao;

  @Override
  public String addWxPosts(WxPosts wxPosts)
  {
    String result = "";
    String errorMsg = "";

    if (wxPosts != null)
    {
      String psId = UUID.randomUUID().toString();
      wxPosts.setPsId(psId);

      int psSecId = wxPosts.getPsSecId();
      String psTitle = wxPosts.getPsTitle();
      String psUserId = wxPosts.getPsUserId();
      String psUserName = wxPosts.getPsUserName();
      String psContent = wxPosts.getPsContent();

      if (psSecId <= 0)
      {
        errorMsg += "psSecId 为空，";
      }
      if (psTitle == null || psTitle.length() == 0)
      {
        errorMsg += "psTitle 为空，";
      }
      if (psUserId == null || psUserId.length() == 0)
      {
        errorMsg += "psUserId 为空，";
      }
      if (psUserName == null || psUserName.length() == 0)
      {
        errorMsg += "psUserName 为空，";
      }
      if (psContent == null || psContent.length() == 0)
      {
        errorMsg += "psContent 为空，";
      }

      if (errorMsg.length() == 0)
      {
        try
        {
          String psParentId = wxPosts.getPsParentId();

          if (psParentId != null && psParentId.length() > 0 && !psParentId.equals("-1"))
          {
            WxPosts parentObj = postsDao.getById(psParentId);

            if (parentObj == null || parentObj.getPsId() == null || parentObj.getPsId().length() == 0)
            {
              errorMsg = "执行论坛帖子新增失败，信息错误，当前帖子的父帖在数据库中不存在，psParentId: " + psParentId;
              logger.error("方法 addWxPosts，执行论坛帖子新增失败，信息错误，当前帖子的父帖在数据库中不存在，当前对象信息: " + wxPosts.toString());
            }
            WxUserMsg userMsg = new WxUserMsg();
            String msgId = UUID.randomUUID().toString();
            userMsg.setMsgId(msgId);
            userMsg.setMsgContent("["+psUserName+"]回复了您的帖子！");
            userMsg.setMsgParentPsId(psParentId);
            userMsg.setMsgPsId(psId);
            userMsg.setMsgStatus(0);
            userMsg.setMsgUserId(parentObj.getPsUserId());
            userMsg.setMsgCreateTime(new Date());
            userMsg.setMsgModifyTime(new Date());
            userMsgDao.save(userMsg);
          }

          if (errorMsg.length() == 0)
          {
            if (wxPosts.getPsStatus() <= 0)
            {
              wxPosts.setPsStatus(1);
            }
            if (wxPosts.getPsCheckStatus() <= 0)
            {
              wxPosts.setPsCheckStatus(0);
            }
            if (wxPosts.getPsPostType() <= 0)
            {
              wxPosts.setPsPostType(0);
            }
            if (wxPosts.getPsIsTop() <= 0)
            {
              wxPosts.setPsIsTop(0);
            }
            wxPosts.setPsCreateTime(new Date());
            wxPosts.setPsModifyTime(new Date());

            boolean flag = postsDao.save(wxPosts);
            if (!flag)
            {
              errorMsg = "执行论坛帖子新增操作失败了。";
              logger.error("方法 addWxPosts，" + errorMsg + "当前帖子信息: " + wxPosts.toString());
            }
          }
        } catch (Exception e)
        {
          logger.error("方法 addWxPosts，执行论坛帖子新增时发生异常，当前帖子信息: " + wxPosts.toString(), e);
          errorMsg = "执行论坛帖子新增操作发生异常";
        }
      }
    } else
    {
      logger.error("方法 addWxPosts，参数 wxPosts 对象为空。");
      errorMsg = "执行论坛帖子新增失败，WxPosts对象为空!";
    }

    if (errorMsg.length() > 0)
    {
      if (errorMsg.endsWith("，"))
      {
        errorMsg = errorMsg.substring(0, errorMsg.lastIndexOf("，"));
      }
      result = "{\"status\":\"0\",\"errorMsg\":\"" + errorMsg + "\"}";
    } else
    {
      result = "{\"status\":\"1\",\"psId\":\"" + wxPosts.getPsId() + "\"}";
    }

    return result;
  }

  @Override
  public String updateWxPosts(WxPosts wxPosts)
  {
    String result = "";
    String errorMsg = "";

    if (wxPosts != null)
    {
      String psId = wxPosts.getPsId();
      int psSecId = wxPosts.getPsSecId();
      String psTitle = wxPosts.getPsTitle();
      String psUserId = wxPosts.getPsUserId();
      String psUserName = wxPosts.getPsUserName();
      String psContent = wxPosts.getPsContent();

      if (psId == null || psId.length() == 0)
      {
        errorMsg += "psId 为空，";
      }
      if (psSecId <= 0)
      {
        errorMsg += "psSecId 为空，";
      }
      if (psTitle == null || psTitle.length() == 0)
      {
        errorMsg += "psTitle 为空，";
      }
      if (psUserId == null || psUserId.length() == 0)
      {
        errorMsg += "psUserId 为空，";
      }
      if (psUserName == null || psUserName.length() == 0)
      {
        errorMsg += "psUserName 为空，";
      }
      if (psContent == null || psContent.length() == 0)
      {
        errorMsg += "psContent 为空，";
      }

      if (errorMsg.length() == 0)
      {
        try
        {
          WxPosts existObj = postsDao.getById(psId);
          if (existObj != null && existObj.getPsId() != null && existObj.getPsId().length() > 0)
          {
            String psParentId = wxPosts.getPsParentId();

            if (psParentId != null && psParentId.length() > 0 && !psParentId.equals("-1"))
            {
              WxPosts parentObj = postsDao.getById(psParentId);

              if (parentObj == null || parentObj.getPsId() == null || parentObj.getPsId().length() == 0)
              {
                errorMsg = "执行论坛帖子修改失败，信息错误，当前帖子的父帖在数据库中不存在，psParentId: " + psParentId;
                logger.error("方法 updateWxPosts，执行论坛帖子修改失败，信息错误，当前帖子的父帖在数据库中不存在，当前对象信息: " + wxPosts.toString());
              }
            }

            if (errorMsg.length() == 0)
            {
              wxPosts.setPsModifyTime(new Date());
              boolean flag = postsDao.update(wxPosts);
              if (!flag)
              {
                errorMsg = "执行论坛帖子修改操作失败了。";
                logger.error("方法 updateWxPosts，" + errorMsg + "当前帖子信息: " + wxPosts.toString());
              }
            }
          } else
          {
            errorMsg = "执行论坛帖子修改操作失败了，当前帖子id在数据库中不存在，id: " + psId;
            logger.error("方法 updateWxPosts，" + errorMsg + "。当前帖子信息: " + wxPosts.toString());
          }
        } catch (Exception e)
        {
          logger.error("方法 updateWxPosts，执行论坛帖子修改时发生异常，当前帖子信息: " + wxPosts.toString(), e);
          errorMsg = "执行论坛帖子修改操作发生异常";
        }
      }
    } else
    {
      logger.error("方法 updateWxPosts，参数 wxPosts 对象为空。");
      errorMsg = "执行论坛帖子修改失败，WxPosts对象为空!";
    }

    if (errorMsg.length() > 0)
    {
      if (errorMsg.endsWith("，"))
      {
        errorMsg = errorMsg.substring(0, errorMsg.lastIndexOf("，"));
      }
      result = "{\"status\":\"0\",\"errorMsg\":\"" + errorMsg + "\"}";
    } else
    {
      result = "{\"status\":\"1\"}";
    }

    return result;
  }

  @Override
  public boolean deleteWxPosts(String psId)
  {
    return postsDao.deleteById(psId);
  }

  @Override
  public WxPosts getWxPosts(String psId)
  {
    return postsDao.getById(psId);
  }

  @Override
  public List<WxPosts> getWxPostsList(WxPosts wxPosts)
  {
    List<WxPosts> resultList = postsDao.findByExample(wxPosts);
    return resultList;
  }

  /**
   * 根据当前回复帖获取其主帖
   */
  @Override
  public WxPosts getParentPosts(WxPosts wxPosts)
  {
    WxPosts parentPosts = null;

    String psId = wxPosts.getPsId();
    String psParentId = wxPosts.getPsParentId();

    try
    {
      if (psParentId != null && psParentId.length() > 0)
      {
        parentPosts = postsDao.getById(psParentId);
        if (parentPosts == null || parentPosts.getPsId() == null || parentPosts.getPsId().length() == 0)
        {
          parentPosts = null;
        }
      }

      if (parentPosts == null)
      {
        WxPosts existObj = postsDao.getById(psId);
        if (existObj != null && existObj.getPsParentId() != null && existObj.getPsParentId().length() > 0)
        {
          psParentId = existObj.getPsParentId();
          parentPosts = postsDao.getById(psParentId);
          if (parentPosts == null || parentPosts.getPsId() == null || parentPosts.getPsId().length() == 0)
          {
            parentPosts = null;
          }
        }
      }
    } catch (Exception e)
    {
      logger.error("方法 getParentPosts，查询 h_posts表发生异常[psId: " + psId + ",psParentId: " + psParentId + "]!", e);
    }

    return parentPosts;
  }

	/**
	 * 获取主帖的所有回复信息
	 */
	@Override
	public Page<WxPosts> getSubPosts(WxPosts wxPosts) {
		//先根据分页查询固定数据
		Page<WxPosts> subPosts = postsDao.getSubPosts(wxPosts);
		//再查询当前页的引用回复数据
		if (subPosts.getResult() != null && subPosts.getResult().size() > 0) {
			String psId= "";
			for (WxPosts obj : subPosts.getResult()) {
				psId+=obj.getPsId()+",";
			}
			psId = psId.substring(0, psId.length()-1);
			WxPosts qryCond = new WxPosts();
			qryCond.setPsId(psId);
			qryCond.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS);
			qryCond.setPsStatus(WeiXinUtil.PS_STATUS_OK);
			qryCond.setPsField5(wxPosts.getPsParentId());
			qryCond.setAtnUserId(wxPosts.getAtnUserId());
			
			List<WxPosts> allSubList = postsDao.getAllParentPosts(qryCond);
			for (WxPosts obj : subPosts.getResult()) {
				 List<WxPosts> childPostList = new ArrayList<WxPosts>();
				 getParentPosts(allSubList,childPostList, obj.getPsParentId());
				 if(childPostList != null && childPostList.size() > 0){
					 Collections.sort(childPostList);
					 obj.setSubPostList(childPostList);
				 }
			}
			
		}
		
		return subPosts;
	}
	
	private static void getParentPosts(List<WxPosts> postList,
			List<WxPosts> retList, String pid) {
		for (WxPosts wxPosts : postList) {
			if (pid.equals(wxPosts.getPsId()) && !retList.contains(wxPosts)) {
				retList.add(wxPosts);
				getParentPosts(postList, retList, wxPosts.getPsParentId());
			}
		}
	}

  @Override
  /**
   * 翻页查询
   * 如果是论坛列表展示，可设置 sortColumns 为 PS_IS_TOP desc,PS_CREATE_TIME DESC
   */
  public Page<WxPosts> queryPageWxPosts(WxPosts wxPosts)
  {
	Integer sortType = wxPosts.getSortType();
	String orderDesc = "PS_IS_TOP DESC, PS_CREATE_TIME DESC";
	if(sortType != null){
		if(sortType == 1){//最热
			orderDesc = "PS_VIEWS DESC, PS_REPLY DESC";
		}else if (sortType == 2){//最新
			orderDesc = "PS_CREATE_TIME DESC";
		}else{
			orderDesc = "PS_IS_TOP DESC, PS_CREATE_TIME DESC";
		}
	}
    wxPosts.setSortColumns(orderDesc);
    return postsDao.pageQuery(wxPosts);
  }
  
  /**
   * 获取我的帖子回复（未读信息）
   */
  @Override
  public Page<WxPosts> getReplyMsgPosts(String userId)
  {
    return postsDao.getReplyMsgPosts(userId);
  }

  public int clicks(String psId)
  {
    return postsDao.clicks(psId);
  }

  public int replys(String psId)
  {
    return postsDao.replys(psId);
  }

  public int noteCount(String psUserId)
  {
    return postsDao.noteCount(psUserId);
  }

  public int getPostCount(WxPosts wxPost)
  {
    return postsDao.getPostCount(wxPost);
  }

	@Override
	public Page<WxPosts> getLectureByPages(WxPosts wxPosts) {
		return postsDao.getLectureByPages(wxPosts);
	}

	@Override
	public Page<WxPosts> querySysEventWxPosts(WxPosts wxPosts) {
		String orderDesc = "PS_IS_TOP DESC, PS_CREATE_TIME DESC";
		wxPosts.setSortColumns(orderDesc);
		return postsDao.getSysEventByPages(wxPosts);
	}
	
	  /**
	   * 获取主帖的所有回复信息
	   */
	  @Override
	  public List<WxPosts> getAllParentPosts(WxPosts wxPosts)
	  {
	    return postsDao.getAllParentPosts(wxPosts);
	  }
	  
	  /**
	   * 查询所有帖子列表，关联H_USER_ATTENTION表，获取是否点赞 关注数据
	   * @param wxPosts
	   * @return
	   */
	  public Page<WxPosts> getAtnPostsPage(WxPosts wxPosts)
	  {
		Integer sortType = wxPosts.getSortType();
		String orderDesc = "PS_IS_TOP DESC, PS_CREATE_TIME DESC";
		if(sortType != null){
			if(sortType == 1){//最热
				orderDesc = "PS_VIEWS DESC, PS_REPLY DESC";
			}else if (sortType == 2){//最新
				orderDesc = "PS_CREATE_TIME DESC";
			}else if(sortType == 3){//轻松一刻，最热按点赞排序
				orderDesc = "PS_FIELD5 DESC,PS_CREATE_TIME DESC";
			}else{
				orderDesc = "PS_IS_TOP DESC, PS_CREATE_TIME DESC";
			}
		}
	    wxPosts.setSortColumns(orderDesc);
	    return postsDao.getAtnPostsPage(wxPosts);
	  }

	@Override
	public List<WxPosts> queryCalcPostsReport(WxPosts wxPosts) {
		return postsDao.queryCalcPostsReport(wxPosts);
	}
	
	@Override
	public List<WxPosts> queryDyejPostsReport(WxPosts wxPosts) {
		return postsDao.queryDyejPostsReport(wxPosts);
	}
}
