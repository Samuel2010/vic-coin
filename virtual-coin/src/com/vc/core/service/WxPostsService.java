package com.vc.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tw.ei.baseclass.page.Page;
import com.vc.core.model.WxPosts;

@Service
public interface WxPostsService
{
  public WxPosts getWxPosts(String psId);

  public List<WxPosts> getWxPostsList(WxPosts wxPosts);

  public String addWxPosts(WxPosts wxPosts);

  public String updateWxPosts(WxPosts wxPosts);

  public boolean deleteWxPosts(String psId);

  /**
   * 根据当前回复帖获取其主帖
   * 
   * @param wxPosts
   * @return
   */
  public WxPosts getParentPosts(WxPosts wxPosts);

  /**
   * 获取主帖的所有回复信息
   * 
   * @param psParentId
   * @return
   */
  public Page<WxPosts> getSubPosts(WxPosts wxPosts);
  
  /**
   * 获取当前帖子的所有父贴，树形遍历
   * 
   * @param psParentId
   * @return
   */
  public List<WxPosts> getAllParentPosts(WxPosts wxPosts);
  
  

  /**
   * 分页查询
   * 
   * @param wxPosts
   * @return
   */
  public Page<WxPosts> queryPageWxPosts(WxPosts wxPosts);

  /**
   * 帖子点击
   * 
   * @param psId
   * @return
   */
  public int clicks(String psId);

  /**
   * 帖子回复
   * 
   * @param psId
   * @return
   */
  public int replys(String psId);
  

  /**
   * 获取我的帖子回复（未读信息）
   * @param userId
   * @return
   */
  public Page<WxPosts> getReplyMsgPosts(String userId);

  /**
   * 用户帖子数量
   * 
   * @param psUserId
   *          用户ID
   * @return
   */
  public int noteCount(String psUserId);

  /**
   * 查询帖子数量
   * 
   * @param wxPost
   * @return
   */
  public int getPostCount(WxPosts wxPost);
  
  
  /**
   * 查询幸福微课堂数据（分页）
   * @param wxPosts
   * @return
   */
  public Page<WxPosts> getLectureByPages(WxPosts wxPosts);
  
  
  /**
   * 平台推广活动查询
   * 
   * @param wxPosts
   * @return
   */
  public Page<WxPosts> querySysEventWxPosts(WxPosts wxPosts);
  
  /**
   * 查询所有帖子列表，关联H_USER_ATTENTION表，获取是否点赞 关注数据
   * @param wxPosts
   * @return
   */
  public Page<WxPosts> getAtnPostsPage(WxPosts wxPosts);
  
  /**
   * 发帖、回帖查询统计
   * @param wxPosts
   * @return
   */
  public List<WxPosts> queryCalcPostsReport(WxPosts wxPosts);
  
  /**
   * 党员E家查询统计
   * @param wxPosts
   * @return
   */
  public List<WxPosts> queryDyejPostsReport(WxPosts wxPosts);
}
