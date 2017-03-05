package com.vc.core.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vc.core.model.WxPosts;
import com.vc.core.service.WxPostsService;
import com.vc.core.util.WeiXinUtil;

@Controller
@RequestMapping(value = "/wxPost")
public class WxPostsController
{
  private static Logger logger = Logger.getLogger(WxPostsController.class);

  @Autowired
  private WxPostsService wxPostsService;

  @ResponseBody
  @RequestMapping(value = "/getPosts", method =
    { RequestMethod.GET, RequestMethod.POST })
  public String getPosts(HttpServletRequest request, HttpServletResponse response)
  {
    WxPosts wxPosts = new WxPosts();

    String psSecId = request.getParameter("psSecId");
    String pageNumber = request.getParameter("pageNumber");
    String pageSize = request.getParameter("pageSize");
    if (psSecId == null || psSecId.length() == 0)
    {
      psSecId = "1";
    }
    if (pageNumber == null || pageNumber.length() == 0)
    {
      pageNumber = "0";
    }
    if (pageSize == null || pageSize.length() == 0)
    {
      pageSize = "10";
    }
    wxPosts.setPsSecId(Integer.parseInt(psSecId));
    wxPosts.setPageNumber(Integer.parseInt(pageNumber));
    wxPosts.setPageSize(Integer.parseInt(pageSize));
    wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);

    List<WxPosts> list = wxPostsService.getWxPostsList(wxPosts);

    JSONArray jsonarray = JSONArray.fromObject(list);

    logger.info("方法 getPosts, Posts：" + jsonarray.toString());

    return jsonarray.toString();
  }

  @RequestMapping(value = "/list")
  public String list(HttpServletRequest request, HttpServletResponse response)
  {
    // request.setAttribute("app", app);
    return "posts/list";
  }
}
