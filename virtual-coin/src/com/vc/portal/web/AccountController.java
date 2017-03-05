package com.vc.portal.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vc.core.service.UserService;

@Controller
@RequestMapping(value = "/account")
public class AccountController
{
  @Autowired
  private UserService UserSrv;

  @RequestMapping("")
  public String transMain(HttpServletRequest request)
  {
	
		return "accountMain"; 
  }
 

}