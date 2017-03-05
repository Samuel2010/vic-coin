package com.vc.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tw.ei.baseclass.base.BaseIbatis3Dao;
import com.tw.ei.baseclass.page.Page;
import com.vc.core.model.WxPosts;
import com.vc.core.util.WeiXinUtil;

@Repository
public class WxPostsDao extends BaseIbatis3Dao<WxPosts, String>
{
  @Override
  public String getIbatisMapperNamesapce()
  {
    return "WxPosts";
  }

  /**
   * @return 帖子点击量
   */
  public int clicks(String psId)
  {
    this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".clicks", psId);

    return (Integer) this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".getClicks", psId);
  }

  /**
   * @return 帖子回复量
   */
  public int replys(String psId)
  {
    this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".replys", psId);
    return (Integer) this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".getReply", psId);
  }

  public int noteCount(String psUserId)
  {
    return (Integer) this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".noteCount", psUserId);
  }

  public Page<WxPosts> getSubPosts(WxPosts wxPosts)
  {
    Page<WxPosts> resultList = new Page<WxPosts>();

    if (wxPosts != null && !wxPosts.getPsParentId().equals(""))
    {
      try
      {
        wxPosts.setSortColumns("PS_IS_TOP DESC,PS_CREATE_TIME ASC");

        resultList = this.pageQuery(getIbatisMapperNamesapce() + ".findSubPostPage", wxPosts);

      } catch (Exception e)
      {
        logger.error("方法 getSubPosts，获取当前主帖的所有回帖信息发生异常[主帖id: " + wxPosts.getPsParentId() + "]!", e);
      }
    }

    return resultList;
  }

  public int getPostCount(WxPosts wxPost)
  {
    return (Integer) this.getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".getPostCount", wxPost);
  }
  
  /**
   * 获取用户所有回复未读的帖子
   * @param userId
   * @return
   */
  public Page<WxPosts> getReplyMsgPosts(String userId)
  {
    Page<WxPosts> resultList = new Page<WxPosts>();

    if (userId != null && !userId.equals(""))
    {
      try
      {
        WxPosts queryObj = new WxPosts();
        queryObj.setPsUserId(userId);
        queryObj.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS);
        queryObj.setPsStatus(1);
        queryObj.setSortColumns("PS_CREATE_TIME ASC");

        resultList = this.pageQuery(getIbatisMapperNamesapce() + ".findReplyMsgPostPage", queryObj);

      } catch (Exception e)
      {
        logger.error("方法 getReplyMsgPosts，获取用户未读帖子发生异常[用户id: " + userId + "]!", e);
      }
    }

    return resultList;
  }
  
  public Page<WxPosts> getLectureByPages(WxPosts queryObj)
  {
    Page<WxPosts> resultList = new Page<WxPosts>();

    if (queryObj != null)
    {
      try
      {
        queryObj.setSortColumns("PS_CREATE_TIME DESC");

        resultList = this.pageQuery(getIbatisMapperNamesapce() + ".findLecturePage", queryObj);

      } catch (Exception e)
      {
        logger.error("方法 getLectureByPages，获取幸福微课堂列表数据出错!", e);
      }
    }

    return resultList;
  }
  
	public Page<WxPosts> getSysEventByPages(WxPosts queryObj) {
		Page<WxPosts> resultList = new Page<WxPosts>();
		try {
			resultList = this.pageQuery(getIbatisMapperNamesapce()+ ".findSysEventPage", queryObj);

		} catch (Exception e) {
			logger.error("方法 getSysEventByPages，获取平台推广活动列表数据出错!", e);
		}

		return resultList;
	}

	
	 public List<WxPosts> getAllParentPosts(WxPosts wxPosts)
	  {
	    List<WxPosts> resultList = new ArrayList<WxPosts>();

	    if (wxPosts != null )
	    {
	      try
	      {
	        wxPosts.setSortColumns("PS_IS_TOP DESC,PS_CREATE_TIME ASC");

	        resultList = this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".findAllParentPost", wxPosts);

	      } catch (Exception e)
	      {
	        logger.error("方法 getAllParentPosts,获取子贴父级数据异常，[帖子id: " + wxPosts.getPsParentId() + "]!", e);
	      }
	    }

	    return resultList;
	  }
	 
	 public List<WxPosts> getAllSubPosts(WxPosts wxPosts)
	  {
	    List<WxPosts> resultList = new ArrayList<WxPosts>();

	    if (wxPosts != null )
	    {
	      try
	      {
	        wxPosts.setSortColumns("PS_IS_TOP DESC,PS_CREATE_TIME ASC");

	        resultList = this.getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".findAllSubPost", wxPosts);

	      } catch (Exception e)
	      {
	        logger.error("方法 getAllSubPosts,获取子贴回复数据异常，[帖子id: " + wxPosts.getPsParentId() + "]!", e);
	      }
	    }

	    return resultList;
	  }

	public List<WxPosts> queryCalcPostsReport(WxPosts wxPosts) {
		List<WxPosts> resultList = new ArrayList<WxPosts>();

		resultList = this.getSqlSessionTemplate().selectList(
				getIbatisMapperNamesapce() + ".queryCalcPostsReport", wxPosts);

		return resultList;
	}
	
	public List<WxPosts> queryDyejPostsReport(WxPosts wxPosts) {
		List<WxPosts> resultList = new ArrayList<WxPosts>();
		
		resultList = this.getSqlSessionTemplate().selectList(
				getIbatisMapperNamesapce() + ".queryDyejPostsReport", wxPosts);
		
		return resultList;
	}
	
	 public Page<WxPosts> getAtnPostsPage(WxPosts wxPosts)
	  {
		 Page<WxPosts> resultList = new Page<WxPosts>();

	    if (wxPosts != null )
	    {
	      try
	      {
	        resultList = this.pageQuery(getIbatisMapperNamesapce() + ".findAtnPostsPage", wxPosts);
	      } catch (Exception e)
	      {
	        logger.error("方法 getAtnPostsPage,查询帖子列表异常", e);
	      }
	    }

	    return resultList;
	  }

	 
}
