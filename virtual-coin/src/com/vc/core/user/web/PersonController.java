package com.vc.core.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vc.core.web.BaseController;

/**
 * 个人中心
 * 
 * @author ztf
 * 
 */
@Controller
@RequestMapping(value = "my")
public class PersonController extends BaseController
{
 /* private static Logger logger = Logger.getLogger(PersonController.class);

  @Autowired
  private WxOptLogService wxOptLogService;

  @Autowired
  private WxUserAttentionService wxUserAttentionService;

  @Autowired
  private WxSectionService wxSectionService;

  @Autowired
  private WxPostsService wxPostsService;

  @Autowired
  private WxUserMsgService wxUserMsgService;
  
  @Autowired
  private WxUserService wxUserService;

  @Autowired
  private WxFileService wxFileService;
  
  @Autowired
  private WxScoreLogService wxScoreLogService;

  private List<WxSection> secDataList;
  @RequestMapping("")
  public String index(HttpServletRequest request,Integer secId)
  {
    String userId = getUser(request);
    logger.info("userId>>>" + userId);
    Integer atnCount = wxUserAttentionService.atnCount(userId);
    Integer noteCount = wxPostsService.noteCount(userId);
    Integer msgCount = wxUserMsgService.getMyMsgCount(userId);
    boolean isSysManager = checkSysManageRole(request, secId);
    request.setAttribute("atnCount", atnCount);
    request.setAttribute("noteCount", noteCount);
    request.setAttribute("msgCount", msgCount);
    request.setAttribute("isSysManager", isSysManager);
    
    initSection(request,secId,true);
    return "myInfo";
  }
  
	public void initSection(HttpServletRequest request, Integer secId,boolean checkRole) {
		if (secId != null) {
			WxSection section = wxSectionService.getWxSection(secId);
			request.setAttribute("section", section);
		}else{
			WxSection section = wxSectionService.getWxSection(1);
			request.setAttribute("section", section);
		}
		if(checkRole){
			 boolean isManageAuth = checkRole(request, secId);
			 request.setAttribute("isBkManageAuth", isManageAuth);
		}
	}

  @RequestMapping("{reqname}")
  public String front(HttpServletRequest request, @PathVariable String reqname,Integer secId)
  {
	
    String userId = getUser(request);
    logger.info("userId>>>" + userId);
    Integer atnCount = wxUserAttentionService.atnCount(userId);
    Integer noteCount = wxPostsService.noteCount(userId);
    request.setAttribute("atnCount", atnCount);
    request.setAttribute("noteCount", noteCount);
    initSection(request,secId,false);
    return reqname;
  }

  @RequestMapping(value =
    { "/getAttention" })
  @ResponseBody
  public Object getAttention(HttpServletRequest request, Integer pageNo)
  {
    String userId = getUser(request);
    logger.info("userId>>>" + userId);

    WxUserAttention userAttention = new WxUserAttention();
    userAttention.setAtnUserId(userId);
    userAttention.setPageNumber(pageNo);
    userAttention.setPsStatus(WeiXinUtil.PS_STATUS_OK);
    userAttention.setAtnStatus(WeiXinUtil.PS_ATN_YES);

    Page<WxUserAttention> data = wxUserAttentionService.queryPageAttentionList(userAttention);

    List<WxUserAttention> result = data.getResult();
    int pageCount = data.getLastPageNumber();
    int pageSize = data.getPageSize();

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("pageCount", pageCount);
    map.put("pageSize", pageSize);
    return map;
  }

  @RequestMapping(value =
    { "/getNotice" })
  @ResponseBody
  public Object getNotice(HttpServletRequest request, Integer pageNo)
  {
    String userId = getUser(request);
    WxPosts wxPosts = new WxPosts();
    wxPosts.setPsUserId(userId);
    wxPosts.setPageNumber(pageNo);

    Page<WxPosts> data = wxPostsService.queryPageWxPosts(wxPosts);

    List<WxPosts> result = data.getResult();
    int pageCount = data.getLastPageNumber();
    int pageSize = data.getPageSize();

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("pageCount", pageCount);
    map.put("pageSize", pageSize);
    return map;
  }

  @RequestMapping(value =
    { "/getNote" })
  @ResponseBody
  public Object getNote(HttpServletRequest request, Integer pageNo)
  {
    String userId = getUser(request);
    WxPosts wxPosts = new WxPosts();
    wxPosts.setPsUserId(userId);
    wxPosts.setPsParentId("-1");
    wxPosts.setPageNumber(pageNo);

    Page<WxPosts> data = wxPostsService.queryPageWxPosts(wxPosts);

    List<WxPosts> result = data.getResult();
    int pageCount = data.getLastPageNumber();
    int pageSize = data.getPageSize();

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("pageCount", pageCount);
    map.put("pageSize", pageSize);
    return map;
  }

  @RequestMapping(value =
    { "/getMyNotice" })
  @ResponseBody
  public Object getMyNotice(HttpServletRequest request, Integer pageNo)
  {
    String userId = getUser(request);

    Page<WxPosts> data = wxPostsService.getReplyMsgPosts(userId);

    List<WxPosts> result = data.getResult();
    int pageCount = data.getLastPageNumber();
    int pageSize = data.getPageSize();

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("pageCount", pageCount);
    map.put("pageSize", pageSize);
    return map;
  }

  *//**
   * 帖子明细
   * 
   * @param request
   *          request请求
   * @param secId
   *          板块ID
   * @param psId
   *          帖子ID
   * @return
   *//*
  @RequestMapping("myDetailPage")
  public String myDetailPage(HttpServletRequest request, Integer secId, String psId)
  {
    WxSection section = wxSectionService.getWxSection(secId);
    request.setAttribute("section", section);

    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
    wxPosts.setPsContent(replaceLine(replaceUrl(wxPosts.getPsContent())));
    request.setAttribute("wxPosts", wxPosts);

    WxFile wxFile = new WxFile();
    wxFile.setFilePsId(psId);
    wxFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
    List<WxFile> files = wxFileService.getFileList(wxFile);
    request.setAttribute("files", files);
    request.setAttribute("isAtnPost", wxUserAttentionService.isAtnPost(getUser(request), psId));
    return "myDetailPage";
  }

  *//**
   * 
   * @param request
   * @param secId
   * @param psId
   * @return
   *//*
  @RequestMapping("myNotePage")
  public String myNotePage(HttpServletRequest request, Integer secId, String psId)
  {
    WxSection section = wxSectionService.getWxSection(secId);
    request.setAttribute("section", section);

    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
    request.setAttribute("wxPosts", wxPosts);

    WxFile wxFile = new WxFile();
    wxFile.setFilePsId(psId);
    wxFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
    List<WxFile> files = wxFileService.getFileList(wxFile);
    request.setAttribute("files", files);

    return "myNotePage";
  }

  *//**
   * 保存帖子
   * 
   * @param request
   *          request请求
   * @param wxPosts
   *          帖子
   * @param secId
   *          板块ID
   * @param model
   *          返回模型
   * @return
   *//*
  @RequestMapping("saveNote")
  @ResponseBody
  public Object saveNote(HttpServletRequest request, WxPosts wxPosts,String delFiles, Integer secId, @RequestParam(value = "files", required = false) CommonsMultipartFile[] files, Model model)
  {

    String result = wxPostsService.updateWxPosts(wxPosts);
    WxOptLog optLog = new WxOptLog();
    optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD);
    optLog.setLogPsId(wxPosts.getPsId());
    optLog.setLogUserId(getUser(request));
    optLog.setLogRemark("修改帖子，帖子内容：" + wxPosts.toString());
    wxOptLogService.addLog(optLog);

    dealUploadFiles(request, wxPosts.getPsId());//批量处理上传文件。
    
    if(delFiles !=null && !delFiles.equals("")){
    	String[] fileId = delFiles.split(",");
    	if(fileId != null && fileId.length>0){
    		for (String obj : fileId) {
       		    wxFileService.deleteFile(obj);
    		}
    	}
    	
    }
    
    if (files != null)
    {
      for (MultipartFile file : files)
      {
        if (!file.isEmpty())
        {
          wxFileService.deleteFileByPsId(wxPosts.getPsId());
          break;
        }
      }
      String path = request.getSession().getServletContext().getRealPath("/resources/upload/");

      String fileName = null;
      String fileType = null;
      String filePath = null;

      
      
      
      WxFile wxFile = null;
      for (MultipartFile file : files)
      {
        if (file.isEmpty())
        {
          continue;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        filePath = sdf.format(new Date()) + UUID.randomUUID().toString();
        fileName = file.getOriginalFilename();
        fileType = fileName.substring(fileName.lastIndexOf("."));
        filePath = filePath + fileType;
        logger.info("path>>>" + path);
        logger.info("filePath>>>" + filePath);
        logger.info("fileName>>>" + fileName);
        File targetFile = new File(path, filePath);
        if (!targetFile.exists())
        {
          targetFile.mkdirs();
        }
        // 保存
        try
        {
          file.transferTo(targetFile);
        } catch (Exception e)
        {
          e.printStackTrace();
        }

        wxFile = new WxFile();
        wxFile.setFileName(fileName);
        wxFile.setFilePsId(wxPosts.getPsId());
        wxFile.setFilePath(filePath);
        wxFile.setFileUploadUser(getUser(request));
        String res = wxFileService.addFile(wxFile);

        optLog = new WxOptLog();
        optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD_FILE);
        optLog.setLogPsId(wxPosts.getPsId());
        optLog.setLogUserId(getUser(request));
        optLog.setLogRemark("帖子上传文件，帖子ID：" + wxPosts.getPsId());
        wxOptLogService.addLog(optLog);

        logger.info(res);
      }
    }

    model.addAttribute("secId", secId);
    if (secId != null && secId == 36)
    {
      return "redirect:dyEj";
    }
    if (!wxPosts.getPsParentId().equals("-1"))
    {
      synchronized (this)
      {
        wxPostsService.replys(wxPosts.getPsParentId());
      }
    }
    return result;
  }

  @RequestMapping(value =
    { "/updateUserMsg" })
  @ResponseBody
  public boolean updateUserMsg(HttpServletRequest request, String psId)
  {
    wxUserMsgService.updateUserMsg(psId);
    return true;
  }
  
  private void dealUploadFiles(HttpServletRequest request, String objId) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				for (MultipartFile multipartFile : files) {
					// 取得上传文件
					MultipartFile file = multipartFile;
					if (file != null) {
						String path = request.getSession().getServletContext()
								.getRealPath("/resources/upload/");
						String fileName = null;
						String fileType = null;
						String filePath = null;
						WxFile wxFile = null;
						if (file.isEmpty()) {
							continue;
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
						filePath = sdf.format(new Date())
								+ UUID.randomUUID().toString();
						fileName = file.getOriginalFilename();
						fileType = fileName.substring(fileName.lastIndexOf("."));
						filePath = filePath + fileType;
						logger.info("path>>>" + path);
						logger.info("filePath>>>" + filePath);
						logger.info("fileName>>>" + fileName);
						File targetFile = new File(path, filePath);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						// 保存
						try {
							file.transferTo(targetFile);
						} catch (Exception e) {
							e.printStackTrace();
						}

						wxFile = new WxFile();
						wxFile.setFileName(fileName);
						wxFile.setFilePsId(objId);
						wxFile.setFilePath(filePath);
						wxFile.setFileUploadUser(getUser(request));
						String res = wxFileService.addFile(wxFile);

						WxOptLog optLog = new WxOptLog();
						optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD_FILE);
						optLog.setLogPsId(objId);
						optLog.setLogUserId(getUser(request));
						optLog.setLogRemark("帖子上传文件，帖子ID：" + objId);
						wxOptLogService.addLog(optLog);
						logger.info(res);
					}
				}
				
			}

		}
	}
  
  
  @RequestMapping("myPoints")
  public String myPoints(HttpServletRequest request,Integer secId)
  {
    String userId = getUser(request);
    WxUser user = wxUserService.getWxUser(userId);
    request.setAttribute("curUser", user);
    Date date = new Date();
    SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
    SimpleDateFormat sdfm = new SimpleDateFormat("MM");
    String defYear = sdfy.format(date);
    String defMon = sdfm.format(date);
    request.setAttribute("defYear", defYear);
    request.setAttribute("defMon", defMon);
    
    WxScoreLog wxScoreLog = new WxScoreLog();
    wxScoreLog.setLogUserId(userId);
    
    try {
		List<WxScoreLog> logList = wxScoreLogService.queryScoreTop(wxScoreLog);
	} catch (Exception e) {
		e.printStackTrace();
	}
    
	initSection(request,secId,true);
    return "myPoints";
  }
  
	@RequestMapping("getScoreLogs")
	@ResponseBody
	public Object getScoreLogs(HttpServletRequest request, Integer pageNo,String qryYear,String qryMon,String qryDay,Integer logOpt) {
		String userId = getUser(request);
		WxScoreLog wxScoreLog = new WxScoreLog();
		wxScoreLog.setLogUserId(userId);
		wxScoreLog.setPageNumber(pageNo);
		String qryDate ="";
		String beginDate = "";
		String endDate = "";
		if(qryYear != null && !qryYear.equals("")){
			qryDate += qryYear.trim();
		}
		if(qryMon != null && !qryMon.equals("")){
			qryDate += "-"+qryMon.trim();
		}
		if(qryDay != null && !qryDay.equals("")){
			qryDate += "-"+qryDay.trim();
		}
		//2016-01  2016-01-12
		if(qryDate.length()==4){
			beginDate = qryDate+"-01-01 00:00:00";
			endDate = qryDate+"-12-31 23:59:59";
		}else if(qryDate.length()==7){
			beginDate = qryDate+"-01 00:00:00";
			endDate = qryDate+"-31 23:59:59";
		}else if(qryDate.length()==10){
			beginDate = qryDate+" 00:00:00";
			endDate = qryDate+" 23:59:59";
		}
		
		wxScoreLog.setLogCreateTimeBegin(beginDate);
		wxScoreLog.setLogCreateTimeEnd(endDate);
		
		if(logOpt != null && logOpt != -1){
			wxScoreLog.setLogOpt(logOpt);
		}
		
		System.out.println(qryDate+"xxxxxxxxxxxxxxxxx");

		Page<WxScoreLog> data = wxScoreLogService.queryPageWxScoreLog(wxScoreLog);

		List<WxScoreLog> result = data.getResult();
		int pageCount = data.getLastPageNumber();
		int pageSize = data.getPageSize();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("pageCount", pageCount);
		map.put("pageSize", pageSize);
		return map;
	}
	
	@RequestMapping("queryScoreTop")
	@ResponseBody
	public Object queryScoreTop(HttpServletRequest request,Integer qryType,String qryYear,String qryMon,Integer qryQua) {
		
		String qryDate ="";
		String beginDate = "";
		String endDate = "";
		if(qryYear != null && !qryYear.equals("")){
			qryDate += qryYear.trim();
		}
		if(qryMon != null && !qryMon.equals("")){
			qryDate += "-"+qryMon.trim();
		}
		
		//2016-01  2016-01-12
		if(qryDate.length()==4){
			beginDate = qryDate+"-01-01 00:00:00";
			endDate = qryDate+"-12-31 23:59:59";
		}else if(qryDate.length()==7){
			beginDate = qryDate+"-01 00:00:00";
			endDate = qryDate+"-31 23:59:59";
		}
		
		if(qryType == 2){
			qryYear = qryYear.trim();
			if(qryQua == 1){
				beginDate = qryYear+"-01-01 00:00:00";
				endDate = qryYear+"-3-31 23:59:59";
			}else if(qryQua == 2){
				beginDate = qryYear+"-04-01 00:00:00";
				endDate = qryYear+"-06-30 23:59:59";
			}else if(qryQua == 3){
				beginDate = qryYear+"-07-01 00:00:00";
				endDate = qryYear+"-09-30 23:59:59";
			}else if(qryQua == 4){
				beginDate = qryYear+"-10-01 00:00:00";
				endDate = qryYear+"-12-31 23:59:59";
			}
		}
		
		String userId = getUser(request);
		WxScoreLog wxScoreLog = new WxScoreLog();
	    wxScoreLog.setLogUserId(userId);
		wxScoreLog.setLogCreateTimeBegin(beginDate);
		wxScoreLog.setLogCreateTimeEnd(endDate);
		
		System.out.println(beginDate+"---xxxxxxxxxxxxxxxxx---"+endDate);

		List<WxScoreLog> logList = wxScoreLogService.queryScoreTop(wxScoreLog);
		Map<String, Object> map = new HashMap<String, Object>();
        if(logList != null && logList.size() > 0){
        	map.put("result", "第"+logList.get(0).getRowNum()+"名");
        }else{
        	map.put("result", "暂无");
        }

		return map;
	}

	*//**
	 * 建议反馈页面
	 * @param request
	 * @param model
	 * @return
	 *//*
	@RequestMapping("myAdvices")
	public String myAdvices(HttpServletRequest request,Model model,Integer secId){
	   
		Integer curSecId = WeiXinUtil.PS_ADVICES_SEC_ID;
	    boolean isManageAuth = checkRole(request, curSecId);
	    request.setAttribute("isManageAuth", isManageAuth);
	    initSection(request,secId,true);
	    
	    return "myAdvices";
	}
	
	*//**
	 * 新增建议反馈
	 * 
	 * @param request
	 * @param wxPosts
	 * @param secId
	 * @return
	 *//*
	@RequestMapping("addAdvices")
	@ResponseBody
	public Object addAdvices(HttpServletRequest request, WxPosts wxPosts,
			Integer secId) {
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		wxPosts.setPsViews(0);
		wxPosts.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_DAI_SHEN_HE);
		wxPosts.setPsIsTop(0);
		String result = wxPostsService.addWxPosts(wxPosts);
		return result;
	}
	
	@RequestMapping("queryCalcPostsReport")
	@ResponseBody
	public Object queryCalcPostsReport(HttpServletRequest request, WxPosts wxPosts,
			String qryYear,String qryMon,String qryDay,Integer qryType) {
		
		String qryDate ="";
		String beginDate = "";
		String endDate = "";
		if(qryYear != null && !qryYear.equals("")){
			qryDate += qryYear.trim();
		}
		if(qryMon != null && !qryMon.equals("")){
			qryDate += "-"+qryMon.trim();
		}
		if(qryDay != null && !qryDay.equals("")){
			qryDate += "-"+qryDay.trim();
		}
		if(qryType != null ){
			if(qryType == 0){
				wxPosts.setPsParentId("-1");
			}else{
				wxPosts.setPsField10("-1");//随便传一个值，SQL执行判断psParentId!=-1的数据，即不是主贴
			}
			
		}
		
		
		//2016-01  2016-01-12
		if(qryDate.length()==4){
			beginDate = qryDate+"-01-01 00:00:00";
			endDate = qryDate+"-12-31 23:59:59";
		}else if(qryDate.length()==7){
			beginDate = qryDate+"-01 00:00:00";
			endDate = qryDate+"-31 23:59:59";
		}else if(qryDate.length()==10){
			beginDate = qryDate+" 00:00:00";
			endDate = qryDate+" 23:59:59";
		}
		wxPosts.setPsCreateTimeBegin(beginDate);
		wxPosts.setPsCreateTimeEnd(endDate);
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		
		//简化数据库SQL语句，统计逻辑在应用层实现，提升效率
		List<WxPosts> postList = wxPostsService.queryCalcPostsReport(wxPosts);
		
		int[] xfbbtSecArry ={8,12,16,20,24,28,32};//各版块幸福帮帮团secId
		int[] ztjzSecArry = {10,14,18,22,26,30,34};//各版块主题讲座secId
		int[] hstjSecArry = {11,15,19,23,27,31,35};//各版块好书推荐secId
		Integer xfbbtSum = 0;
		Integer ztjzSum = 0;
		Integer hstjSum = 0;
		List<Map<Integer,String>> resultList = new ArrayList<Map<Integer,String>>();
		for(int i =0;i<8;i++){//1-7分别对应主版块secId
			Map<Integer,String> map = new LinkedHashMap<Integer, String>();
			if(i != 7){
				map.put(1,getSecNameById(i+1).getSecName());
			}else{
				map.put(1, "合计");
				map.put(2,String.valueOf(xfbbtSum));
				map.put(3,String.valueOf(ztjzSum));
				map.put(4,String.valueOf(hstjSum));
				map.put(5, String.valueOf(xfbbtSum+ztjzSum+hstjSum));
				resultList.add(map);
				break;
			}
			
			Integer sumCount = 0;
			for (WxPosts obj : postList) {
				if(xfbbtSecArry[i] == obj.getPsSecId()){
					map.put(2, String.valueOf(obj.getPsViews()));
					sumCount += obj.getPsViews();
					xfbbtSum += obj.getPsViews();
				}
				if(ztjzSecArry[i] == obj.getPsSecId()){
					map.put(3, String.valueOf(obj.getPsViews()));
					sumCount += obj.getPsViews();
					ztjzSum += obj.getPsViews();
				}
				if(hstjSecArry[i] == obj.getPsSecId()){
					map.put(4, String.valueOf(obj.getPsViews()));
					sumCount += obj.getPsViews();
					hstjSum += obj.getPsViews();
				}
				
			}
			if(!map.containsKey(2)){
				map.put(2,"0");
			}
			if(!map.containsKey(3)){
				map.put(3,"0");
			}
			if(!map.containsKey(4)){
				map.put(4,"0");
			}
			map.put(5, String.valueOf(sumCount));
			resultList.add(map);
		}
		
		
		
		return resultList;
	}
	
	@RequestMapping("queryDyejPostsReport")
	@ResponseBody
	public Object queryDyejPostsReport(HttpServletRequest request, WxPosts wxPosts,
			String qryYear,String qryMon,String qryDay,Integer qryType) {
		
		String qryDate ="";
		String beginDate = "";
		String endDate = "";
		if(qryYear != null && !qryYear.equals("")){
			qryDate += qryYear.trim();
		}
		if(qryMon != null && !qryMon.equals("")){
			qryDate += "-"+qryMon.trim();
		}
		if(qryDay != null && !qryDay.equals("")){
			qryDate += "-"+qryDay.trim();
		}
		
		//2016-01  2016-01-12
		if(qryDate.length()==4){
			beginDate = qryDate+"-01-01 00:00:00";
			endDate = qryDate+"-12-31 23:59:59";
		}else if(qryDate.length()==7){
			beginDate = qryDate+"-01 00:00:00";
			endDate = qryDate+"-31 23:59:59";
		}else if(qryDate.length()==10){
			beginDate = qryDate+" 00:00:00";
			endDate = qryDate+" 23:59:59";
		}
		wxPosts.setPsCreateTimeBegin(beginDate);
		wxPosts.setPsCreateTimeEnd(endDate);
		wxPosts.setPsSecId(WeiXinUtil.PS_DYEJ_SEC_ID);
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		
		
		wxPosts.setPsParentId("-1");
		List<WxPosts> postList = wxPostsService.queryDyejPostsReport(wxPosts);
		Map<Integer,Integer> retMap = new LinkedHashMap<Integer,Integer>();
		int replyCount = 0;
		int noReplyCount = 0;
		for (WxPosts obj : postList) {
			if(obj.getPsIsDoing() != null && obj.getPsIsDoing() == 1){
				replyCount += obj.getPsViews();
			}else{
				noReplyCount += obj.getPsViews();
			}
				
		}
		retMap.put(1, replyCount+noReplyCount);
		retMap.put(2, replyCount);//已回复
		retMap.put(3, noReplyCount);//未回复
			
		wxPosts.setPsParentId("");
		wxPosts.setPsField10("-1");//随便传一个值，SQL执行判断psParentId!=-1的数据，即不是主贴
		postList = wxPostsService.queryDyejPostsReport(wxPosts);
		int adminCount = 0;
		int otherCount = 0;
		for (WxPosts obj : postList) {
			if(obj.getPsField3() != null && obj.getPsField3().equals("1")){
				adminCount += obj.getPsViews();
			}else{
				otherCount += obj.getPsViews();
			}
				
		}
		retMap.put(4, adminCount);//管理员回复
		retMap.put(5, otherCount);//志愿者回复
		//retMap.put(6, adminCount+otherCount);
		return retMap;
	}
	
	private void initSecData(){
		List<WxSection> secList = wxSectionService.getWxSectionList(new WxSection());
		secDataList = secList;
	}
	
	private WxSection getSecNameById(Integer secId){
		if(secDataList == null){
			initSecData();
		}
		WxSection retSec = new WxSection();
		for (WxSection sec : secDataList) {
			if(sec.getSecId() == secId){
				retSec = sec;
				break;
			}
		}
		return retSec;
		
	}
	
	@RequestMapping("queryFunPostsReport")
	@ResponseBody
	public Object queryFunPostsReport(HttpServletRequest request, WxPosts wxPosts,
			String qryYear,String qryMon,String qryDay) {
		String qryDate ="";
		String beginDate = "";
		String endDate = "";
		if(qryYear != null && !qryYear.equals("")){
			qryDate += qryYear.trim();
		}
		if(qryMon != null && !qryMon.equals("")){
			qryDate += "-"+qryMon.trim();
		}
		if(qryDay != null && !qryDay.equals("")){
			qryDate += "-"+qryDay.trim();
		}
		
		if(qryDate.length()==4){
			beginDate = qryDate+"-01-01 00:00:00";
			endDate = qryDate+"-12-31 23:59:59";
		}else if(qryDate.length()==7){
			beginDate = qryDate+"-01 00:00:00";
			endDate = qryDate+"-31 23:59:59";
		}else if(qryDate.length()==10){
			beginDate = qryDate+" 00:00:00";
			endDate = qryDate+" 23:59:59";
		}
		wxPosts.setPsParentId("-1");
		wxPosts.setPsCreateTimeBegin(beginDate);
		wxPosts.setPsCreateTimeEnd(endDate);
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		wxPosts.setPsSecId(41);
		
		List<WxPosts> postList = wxPostsService.queryCalcPostsReport(wxPosts);
		Map<Integer,Integer> map = new LinkedHashMap<Integer, Integer>();
		if(postList != null && postList.size()>0){
			for (WxPosts obj : postList) {
				map.put(1, obj.getPsViews());
			}
		}else{
			map.put(1, 0);
		}
		
		
		
		return map;
	}
	
	@RequestMapping("queryReport")
	public String queryReport(HttpServletRequest request, Integer secId) {
		String userId = getUser(request);
		WxUser user = wxUserService.getWxUser(userId);
		request.setAttribute("curUser", user);
		Date date = new Date();
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfm = new SimpleDateFormat("MM");
		SimpleDateFormat sdfd = new SimpleDateFormat("dd");
		String defYear = sdfy.format(date);
		String defMon = sdfm.format(date);
		String defDay = sdfd.format(date);
		request.setAttribute("defYear", defYear);
		request.setAttribute("defMon", defMon);
		request.setAttribute("defDay", defDay);

		WxScoreLog wxScoreLog = new WxScoreLog();
		wxScoreLog.setLogUserId(userId);

		initSection(request, secId, true);
		return "queryReport";
	}
	
	*/
	
	
	
	
	
	
	
	
	

}
