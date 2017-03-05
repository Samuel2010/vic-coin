package com.vc.core.web.front;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vc.core.model.WxPosts;
import com.vc.core.service.WxPostsService;

@Controller
@RequestMapping("{temp}")
public class PageController
{
  @Autowired
  private WxPostsService wxPostsService;

  @RequestMapping("page/{channelid}_{current}")
  public String page(HttpServletRequest request, @PathVariable String temp, @PathVariable String channelid, @PathVariable String current)
  {
    request.setAttribute("channelid", channelid);
    request.setAttribute("temp", temp);
    request.setAttribute("current", current);
    if (temp.equals("school"))
    {
      request.setAttribute("school_state", "current");
      return "school";
    } else
    {
      request.setAttribute("news_state", "current");
      return "newlist";
    }
  }

  @RequestMapping("{contentId}")
  public String content(HttpServletRequest request, HttpServletResponse response, @PathVariable String psId, @PathVariable String temp)
  {
    request.setAttribute(temp, "active");
    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
    if (null != wxPosts)
    {
      request.setAttribute("wxPosts", wxPosts);
      request.setAttribute("news_state", "current");
      return "content";
    } else
    {
      try
      {
        response.sendError(404);
      } catch (IOException e)
      {
        e.printStackTrace();
      }
      return "404";
    }
  }

}
