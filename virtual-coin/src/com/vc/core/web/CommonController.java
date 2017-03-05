package com.vc.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vc.core.service.WxPostsService;

@Controller
@RequestMapping("common")
public class CommonController
{
  @Autowired
  private WxPostsService wxPostsService;

  @RequestMapping("ViewClick")
  public String clicks(HttpServletRequest request, @RequestParam String psId)
  {
    int clicks = 0;
    synchronized (this)
    {
      clicks = wxPostsService.clicks(psId);
    }
    return "document.write('" + clicks + "')";
  }

  @RequestMapping("{reqname}")
  public String front(HttpServletRequest request, @PathVariable String reqname)
  {

    return "common/" + reqname;
  }

}
