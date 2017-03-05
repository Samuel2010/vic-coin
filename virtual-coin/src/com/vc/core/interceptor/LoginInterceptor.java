package com.vc.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vc.core.model.WxUser;
import com.vc.core.model.WxUserRight;
import com.vc.core.service.WxUserRightService;
import com.vc.core.service.WxUserService;
import com.vc.core.util.AESEncoder;
import com.vc.core.util.NetworkUtil;
import com.vc.core.util.SysConfig;
import com.vc.core.util.WeiXinUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter
{
  private static Logger logger = Logger.getLogger(LoginInterceptor.class);
  @Autowired
  private WxUserService wxUserService;

  public boolean preHandle11(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    NetworkUtil.getIpAddress(request);
    if (request.getSession() != null && request.getSession().getAttribute(WeiXinUtil.WEIXIN_QY_USER) != null)
    {
      return super.preHandle(request, response, handler);
    } else
    {
      String userInfoJson = request.getParameter("userInfoJson");
      logger.info("userInfoJson>>>" + userInfoJson);
      userInfoJson = AESEncoder.decrypt(userInfoJson);
      logger.info("userInfoJson>>>" + userInfoJson);

      if (userInfoJson != null)
      {
        JSONObject object = JSON.parseObject(userInfoJson);
        String userId = object.getString("userid");
        String weixinid = object.getString("weixinid");
        String avatar = object.getString("avatar");

        if(userId != null){
        	userId = userId.trim();
        }
        WxUser wxUser = wxUserService.getWxUser(userId);
        if (wxUser != null)
        {
          logger.info("wxUser>>>" + wxUser.toString());

          WxUserRight userRight = new WxUserRight();
          userRight.setRightUserId(userId);
         // List<WxUserRight> userRights = wxUserRightService.getUserRightList(userRight);

          //request.getSession().setAttribute(WeiXinUtil.WEIXIN_QY_USER_RIGHTS, userRights);
          request.getSession().setAttribute(WeiXinUtil.WEIXIN_QY_USER, wxUser);

          /*//数据库wxId未同步、用户头像未同步、或者头像、微信ID有变更时 更新数据库用户数据
          if(wxUser.getUserWxId() == null || wxUser.getUserAvatar() == null 
        		  || !wxUser.getUserWxId().equals(weixinid) || !wxUser.getUserAvatar().equals(avatar)){*/
        	  // 设置用户的微信账号
              wxUser.setUserWxId(weixinid);
              // 增加用户的微信图标
              wxUser.setUserAvatar(avatar);
        	  wxUserService.updateWxUser(wxUser);
         // }
        } else
        {
          logger.info(">>>>>未查询到用户信息，userId:" + userId+">>>>");
          response.sendRedirect("nouser.do");
        }
      } else
      {
        // 获得请求路径的uri
        StringBuffer uri = request.getRequestURL();
        String queryStr = request.getQueryString();
        uri.append("?");
        if (queryStr != null && queryStr.length() > 0)
        {
          uri.append(queryStr).append("&");
        }
        uri.append("time=").append(System.currentTimeMillis());

        String url = SysConfig.getValue("apiUrl") + "qyoauth2?url=" + uri;
        response.sendRedirect(url);
      }
    }
    return super.preHandle(request, response, handler);
  }
  
  /**
   * 压力测试 跳过鉴权使用
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    NetworkUtil.getIpAddress(request);
    if (request.getSession() != null && request.getSession().getAttribute(WeiXinUtil.WEIXIN_QY_USER) != null)
    {
      return super.preHandle(request, response, handler);
    } else
    {
    String userInfoJson = "{\"errcode\":0,\"errmsg\":\"ok\",\"userid\":\"ljl\",\"name\":\"ljl\",\"department\":[1],\"gender\":\"1\",\"weixinid\":\"samuel201104\",\"avatar\":\"http://shp.qpic.cn/bizmp/SVhYhMxibTYVoaAL0RibLF0QPkia0XIfEERbh9ibG366xBvxDCeI1Yf30w/\",\"status\":1,\"extattr\":{\"attrs\":[]}}";

    JSONObject object = JSON.parseObject(userInfoJson);
    String userId = object.getString("userid");
    String weixinid = object.getString("weixinid");
    String avatar = object.getString("avatar");

    WxUser wxUser = wxUserService.getWxUser(userId);
    if (wxUser != null)
    {
      logger.info("wxUser>>>" + wxUser.toString());

      WxUserRight userRight = new WxUserRight();
      userRight.setRightUserId(userId);
     // List<WxUserRight> userRights = wxUserRightService.getUserRightList(userRight);

    //  request.getSession().setAttribute(WeiXinUtil.WEIXIN_QY_USER_RIGHTS, userRights);
      request.getSession().setAttribute(WeiXinUtil.WEIXIN_QY_USER, wxUser);

      // 设置用户的微信账号
      wxUser.setUserWxId(weixinid);
      // 增加用户的微信图标
      wxUser.setUserAvatar(avatar);
      if(!wxUser.getUserWxId().equals(weixinid) || !wxUser.getUserAvatar().equals(avatar)){
    	  wxUserService.updateWxUser(wxUser);
      }

    } else
    {
      response.sendRedirect("nouser");
    }
    }
    return super.preHandle(request, response, handler);
  }

}