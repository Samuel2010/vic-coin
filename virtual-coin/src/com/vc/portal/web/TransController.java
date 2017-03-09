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

import com.tw.ei.baseclass.page.Page;
import com.vc.core.model.TransInfo;
import com.vc.core.service.TransInfoService;
import com.vc.core.service.UserService;
import com.vc.core.web.BaseController;

@Controller
@RequestMapping(value = "/trans")
public class TransController extends BaseController{
	@Autowired
	private UserService userSrv;

	@Autowired
	private TransInfoService transSrv;

	@RequestMapping("")
	public String transMain(HttpServletRequest request) {

		return "transMain";
	}

	@RequestMapping("transMainList")
	public String toTransMainList(HttpServletRequest request) {

		return "portal/transListMain";
	}


	@RequestMapping("getTransListData")
	@ResponseBody
	public Object getTransListData(HttpServletRequest request,Integer transType,Integer transSts) {
		TransInfo qryCond = new TransInfo();
		qryCond.setPageNumber(getPageNumber(request));
		if(transType != -1){
			qryCond.setTransType(transType);
		}
		if(transSts != -1){
			qryCond.setTransSts(transSts);
		}
		Page<TransInfo> data = transSrv.queryTransInfoByPage(qryCond);
	   
		/*Map map = new HashMap();
		map.put("draw", 1);
		map.put("recordsTotal", 3);
		map.put("recordsFiltered", 3);

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("id", "1");
		dataMap.put("transUnitMoney", "1000.00");
		dataMap.put("transType", "2");
		dataMap.put("transNum", "999");
		dataMap.put("transMoney", "88888.32");
		dataMap.put("transSts", "2");
		dataMap.put("transTime", "2017-03-02 11:11:23");
		List list = new ArrayList();
		list.add(dataMap);
		list.add(dataMap);
		list.add(dataMap);

		map.put("data", list);*/
		Map<String,Object> dataMap = initPageData(data, request);
		return dataMap;
	}
}