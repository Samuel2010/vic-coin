package com.vc.portal.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vc.core.model.User;
import com.vc.core.service.UserService;
import com.vc.core.util.WebUtil;

@Controller
@RequestMapping(value = "/index")
public class IndexController
{
  @Autowired
  private UserService UserSrv;

  @RequestMapping(value ={ "/", "" })
  public String index(HttpServletRequest request)
  {
	
	  User user = (User) request.getSession().getAttribute(WebUtil.LOGIN_USER_SESSION);
	  if(user != null){
		  return "index";
	  }else{
		return "login"; 
	  }
  }
 

}