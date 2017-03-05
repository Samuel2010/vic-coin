package com.vc.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vc.core.service.UserService;
import com.vc.core.util.NetworkUtil;
import com.vc.core.util.WebUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter
{
  private static Logger logger = Logger.getLogger(LoginInterceptor.class);
  @Autowired
  private UserService serSrv;

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    NetworkUtil.getIpAddress(request);
    if(request.getSession() != null && request.getSession().getAttribute(WebUtil.LOGIN_USER_SESSION) != null)
    {
      return super.preHandle(request, response, handler);
    }else{
    	/*StringBuffer uri = request.getRequestURL();
        String queryStr = request.getQueryString();
        uri.append("?");
        if (queryStr != null && queryStr.length() > 0)
        {
          uri.append(queryStr).append("&");
        }*/
        response.sendRedirect("/login");
    }
    return super.preHandle(request, response, handler);
  }

}