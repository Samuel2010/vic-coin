package com.vc.core.web.front;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tag")
public class TagController
{
  @RequestMapping("{key}")
  public String channel(HttpServletRequest request, @PathVariable String key)
  {
    request.setAttribute("key", key);
    return "serchlist";
  }
}
