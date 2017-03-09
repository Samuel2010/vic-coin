package com.vc.core.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.tw.ei.baseclass.page.Page;
import com.vc.core.exception.BusinessException;
import com.vc.core.exception.ParameterException;
import com.vc.core.model.WxUser;
import com.vc.core.model.WxUserRight;
import com.vc.core.util.WeiXinUtil;

@Controller
public abstract class BaseController
{
  private static Logger logger = Logger.getLogger(BaseController.class);

  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected HttpSession session;

  public String userId;

  /**
   * form表单提交 Date类型数据绑定 <功能详细描述>
   * 
   * @param binder
   * @see [类、类#方法、类#成员]
   */
  @InitBinder
  public void initBinder(WebDataBinder binder)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }

  /**
   * 获取分页页码
   * @param request
   * @return
   */
  public int getPageNumber(HttpServletRequest request) {
		//数据起始位置
	    Integer start = Integer.parseInt(request.getParameter("start"));
	    //数据长度
	    Integer length = Integer.parseInt(request.getParameter("length"));
	    //请求次数
	    String draw = request.getParameter("draw");
	    
	    System.out.println("start:"+start+",length:"+length+",draw:"+draw);
	    
		return start/length+1;
	}
  
  /**
   * 根据返回结果初始化分页数据
   * @param page
   * @param request
   * @return
   */
	public Map<String,Object> initPageData(Page page,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("draw", request.getParameter("draw"));
		map.put("recordsTotal", page.getTotalCount());
		map.put("recordsFiltered", page.getTotalCount());
		map.put("data", page.getResult());
		return map;
	}
  
  
  public String getUser(HttpServletRequest request)
  {
    // ApiConfigKit.setThreadLocalApiConfig(getApiConfig());

    WxUser wxUser = (WxUser) request.getSession().getAttribute(WeiXinUtil.WEIXIN_QY_USER);
    if (wxUser != null)
    {
      userId = wxUser.getUserId();
    }
    logger.info("userId>>>" + userId);
    return userId;
  }

  public boolean checkRole(HttpServletRequest request, Integer secId)
  {
    List<WxUserRight> userRights = (List<WxUserRight>) request.getSession().getAttribute(WeiXinUtil.WEIXIN_QY_USER_RIGHTS);
    if (userRights != null)
    {
      for (WxUserRight userRight : userRights)
      {
        if (userRight.getRightSysManager().equals(1))
        {
          return true;
        }
        if (userRight.getRightSecId().equals(secId) && userRight.getRightSecManager().equals(1))
        {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean checkSysManageRole(HttpServletRequest request, Integer secId)
  {
    List<WxUserRight> userRights = (List<WxUserRight>) request.getSession().getAttribute(WeiXinUtil.WEIXIN_QY_USER_RIGHTS);
    if (userRights != null)
    {
      for (WxUserRight userRight : userRights)
      {
        if (userRight.getRightSysManager().equals(1))
        {
          return true;
        }
      }
    }
    return false;
  }
  

	/**
	 * 处理内容中包含的网址，加上<a>标签
	 * @param content
	 * @return
	 */
	protected String replaceUrl(String content){
		String regex = "(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s]*";
	    Pattern pattern=Pattern.compile(regex);
	    Matcher m=pattern.matcher(content); 
		while (m.find()) {
			String url = m.group();
			String srcUrl = url;
			if(url.indexOf("http://") <0){
				srcUrl = "http://"+url;
			}
			
			content = content.replace(url, "<a href=\""+srcUrl+"\">"+url+"</a>");
		}
		return content;
	}
	
	/**
	 * 替换换行符
	 * @param content
	 * @return
	 */
	protected String replaceLine(String content){
		if(content.indexOf("\r\n") != -1){
	    	content = content.replaceAll("\r\n", "<br>");
	    }else if(content.indexOf("\n") != -1){
	    	content = content.replaceAll("\n", "<br>");
	    }
		return content;
	}

  /** 基于@ExceptionHandler异常处理 */
  @ExceptionHandler
  public String exp(HttpServletRequest request, Exception ex)
  {
    request.setAttribute("ex", ex);
    logger.error("业务异常：",ex);
    // 根据不同错误转向不同页面
    if (ex instanceof BusinessException)
    {
      return "error-business";
    } else if (ex instanceof ParameterException)
    {
      return "error-parameter";
    } else
    {
      return "error";
    }
  }

  public static String create_timestamp()
  {
    return Long.toString(System.currentTimeMillis() / 1000);
  }

  public static String create_nonce_str()
  {
    return UUID.randomUUID().toString();
  }
}
