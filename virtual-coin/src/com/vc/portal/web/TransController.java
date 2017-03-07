package com.vc.portal.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vc.core.service.UserService;

@Controller
@RequestMapping(value = "/trans")
public class TransController
{
  @Autowired
  private UserService UserSrv;

  @RequestMapping("")
  public String transMain(HttpServletRequest request)
  {
	
		return "transMain"; 
  }
 
  @RequestMapping("transMainList")
  public String toTransMainList(HttpServletRequest request)
  {
	
		return "portal/transListMain"; 
  }

	@RequestMapping("getTransListData")
	@ResponseBody
	public Object getTransListData(HttpServletRequest request) {

		Map map = new HashMap();
		map.put("draw", 1);
		map.put("recordsTotal", 3);
		map.put("recordsFiltered", 3);

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("name", "Angelica");
		dataMap.put("extn", "Ramos");
		dataMap.put("position", "Chief Executive Officer (CEO)");
		dataMap.put("office", "London");
		dataMap.put("start_date", "9th Oct 09");
		dataMap.put("salary", "$1,200,000");
		List list = new ArrayList();
		list.add(dataMap);
		list.add(dataMap);
		list.add(dataMap);
		
		map.put("data", list);
		
		return map;
	}
}