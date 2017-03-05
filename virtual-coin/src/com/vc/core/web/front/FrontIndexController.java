package com.vc.core.web.front;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vc.core.web.BaseController;

@Controller
@RequestMapping
public class FrontIndexController extends BaseController
{
  /*private static Logger logger = Logger.getLogger(FrontIndexController.class);
  @Autowired
  private WxOptLogService wxOptLogService;

  @Autowired
  private WxSectionService wxSectionService;

  @Autowired
  private WxFileService wxFileService;

  @Autowired
  private WxPostsService wxPostsService;

  @Autowired
  private WxNoticeService wxNoticeService;

  @Autowired
  private WxUserAttentionService wxUserAttentionService;

  @Autowired
  private WxUserService wxUserService;
  
  @Autowired
  private WxScoreRuleService wxScoreRuleService;
  
  @Autowired
  private WxScoreLogService wxScoreLogService;

  @RequestMapping("index")
  public String index(HttpServletRequest request)
  {
    request.setAttribute("index", "current");
    return "index";
  }

  @RequestMapping("regedit")
  public String regedit(HttpServletRequest request)
  {
    request.setAttribute("regedit", "regedit");
    return "regedit";
  }
  
  @RequestMapping("nouser")
  public String nouser(HttpServletRequest request)
  {
    return "nouser";
  }

  *//**
   * 根据请求链接重定向页面
   * 
   * @param request
   * 
   * @param reqname
   *          请求链接
   * @param secId
   *          板块Id
   * @param wxPosts
   *          帖子
   * @param model
   *          返回模型
   * @return
   *//*
  @RequestMapping("{reqname}")
  public String front(HttpServletRequest request, @PathVariable String reqname, String secId, WxPosts wxPosts, Model model)
  {
    logger.info("WEIXIN_QY_USER>>>" + getUser(request));

    Integer secid = 1;
    if (!(secId == null || secId.trim().length() == 0))
    {
      secid = Integer.parseInt(secId);

    }
    List<WxNotice> noticeList = wxNoticeService.getWxNoticeListBySecId(secid);
    if (noticeList != null)
    {
      model.addAttribute("wxNoticeList", noticeList);
    }

    WxSection section = wxSectionService.getWxSection(secid);
    model.addAttribute("section", section);
    
    if(reqname != null && (reqname.equals("xfbbt") || reqname.equals("bkgly"))){
    	 boolean isManageAuth = checkRole(request, secid);
    	 request.setAttribute("isBkManageAuth", isManageAuth);
    }
   

    if (section != null)
    {
      List<WxSection> list = wxSectionService.getSubSectionList(section.getSecId());

      if (list != null && list.size() > 0)
      {
        model.addAttribute("list", list);
        model.addAttribute("secId", list.get(0).getSecId());

        for (WxSection wxSection : list) {
        	if(wxSection.getSecEn() !=null && wxSection.getSecEn().equals(reqname)){
        		 model.addAttribute("secId", wxSection.getSecId());
        	}else if(reqname.equals("bkgly") && wxSection.getSecEn().equals("xfwkt")){
        		model.addAttribute("lectureSecId", wxSection.getSecId());
        	}
        	
		}
        if (reqname != null && reqname.equals("bkgly"))
        {
          if (!checkRole(request, secid))
          {
            return "noright";
          }
          WxPosts wxPost = new WxPosts();
          wxPost.setPsSecId(list.get(0).getSecId());
          wxPost.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_DAI_SHEN_HE);
          wxPost.setPsStatus(WeiXinUtil.PS_STATUS_OK);
          wxPost.setPsParentId("-1");
          int verifyCount = wxPostsService.getPostCount(wxPost);
          model.addAttribute("verifyCount", verifyCount);
        }
        if (reqname != null && reqname.equals("dyejAdmin"))
        {
          WxPosts wxPost = new WxPosts();
          wxPost.setPsSecId(WeiXinUtil.PS_DYEJ_SEC_ID);
          wxPost.setPsStatus(WeiXinUtil.PS_STATUS_OK);
          wxPost.setPsParentId("-1");
          wxPost.setPsIsDoing(0);
          wxPost.setPsIsTop(0);
          int replyCount = wxPostsService.getPostCount(wxPost);
          model.addAttribute("replyCount", replyCount);
        }
      }
    }

    if (reqname != null && reqname.equals("sysNotice"))
    {
      model.addAttribute("isNoticeAuth", checkRole(request, secid));
    }

    return reqname;
  }

  *//**
   * 查询帖子列表
   * 
   * @param secId
   *          板块Id
   * @param pageNo
   *          页码
   * @param psPostType
   *          帖子类型
   * @param searchValue
   *          查找内容
   * @param model
   *          返回模型
   * @return
   * @throws Exception
   *//*
  @RequestMapping(value =
    { "/queryPosts" })
  @ResponseBody
  public Object queryPosts(HttpServletRequest request,Integer secId, Integer pageNo, String psParentId, Integer psPostType, Integer psStatus, Integer psCheckStatus, String searchValue, String psUserId,
      Integer psIsDoing,Integer psIsTop,Integer sortType, Integer sysEventFlag,Integer qryFileMode,Model model) throws Exception
  {
    logger.info("pageNo=" + pageNo);
    if (pageNo == null)
    {
      pageNo = 1;
    }

    WxPosts wxPosts = new WxPosts();
    wxPosts.setPsSecId(secId);
    if (searchValue != null)
    {
      wxPosts.setPsTitle(searchValue);
    }

    if (psPostType != null)
    {
      wxPosts.setPsPostType(psPostType);
    }
    if (psStatus != null)
    {
      wxPosts.setPsStatus(psStatus);
    } else
    {
      wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
    }
    if (psCheckStatus != null)
    {
      wxPosts.setPsCheckStatus(psCheckStatus);
    } else
    {
      wxPosts.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_DAI_SHEN_HE);
    }

    if (psParentId != null)
    {
      wxPosts.setPsParentId(psParentId);
    }

    if (psUserId != null)
    {
      wxPosts.setPsUserId(psUserId);
    }
    if (psIsDoing != null)
    {
      wxPosts.setPsIsDoing(psIsDoing);
    }
    if (psIsTop != null)
    {
    	wxPosts.setPsIsTop(psIsTop);
    }
    //排序类型，1-最热、2-最新，0,null-默认 按置顶+最新排列
    if(sortType != null){
    	wxPosts.setSortType(sortType);
    }

    wxPosts.setPageNumber(pageNo);
    model.addAttribute("wxPosts", wxPosts);
    Page<WxPosts> data;
    if(sysEventFlag != null){
    	wxPosts.setLogUserId(getUser(request));
    	data = wxPostsService.querySysEventWxPosts(wxPosts);
    }else{
    	wxPosts.setAtnUserId(getUser(request));
    	data = wxPostsService.getAtnPostsPage(wxPosts);
    }
    

    List<WxPosts> result = data.getResult();
    
    
    //是否查询所属文件，主要用于图文混排模式
    if(qryFileMode != null && qryFileMode == 1){
    	String psIds = "";
    	for (WxPosts obj : result) {
    		psIds+=obj.getPsId()+",";
    	}
    	if(psIds.length()>0){
    		psIds =  psIds.substring(0,psIds.length()-1);
    	}
    	WxFile qryFile = new WxFile();
    	qryFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
    	//qryFile.setFileIsPic(WeiXinUtil.FILE_IS_PIC);
    	qryFile.setFilePsId(psIds);
        List<WxFile> fileList = wxFileService.getFilesByPsIds(qryFile);
    	
    	for (WxPosts obj : result) {
    		List<WxFile> picFileList = new ArrayList<WxFile>();
    		for (WxFile wxFile : fileList) {
				if(obj.getPsId().equals(wxFile.getFilePsId())){
					picFileList.add(wxFile);
				}
    		}
    		obj.setPicFileList(picFileList);
    	}
    }
    
    int pageCount = data.getLastPageNumber();
    int pageSize = data.getPageSize();
    
    

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("pageCount", pageCount);
    map.put("pageSize", pageSize);
    return map;
  }

  *//**
   * 新建帖子页面
   * 
   * @param request
   *          request请求
   * @param secId
   *          板块ID
   * @param psId
   *          帖子ID
   * @return
   *//*
  @RequestMapping("newNote")
  public String newNote(HttpServletRequest request, Integer secId, String psId,Integer replyType,String topPsId)
  {
    WxSection section = wxSectionService.getWxSection(secId);
    request.setAttribute("section", section);

    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
    request.setAttribute("wxPosts", wxPosts);
    
    //initJssdkData(request);//初始化jssdk参数
    
    if(replyType != null){
    	request.setAttribute("replyType", replyType);
    }else{
    	if(section.getSecEn().equals("dyej")){
        	request.setAttribute("replyType", 3);
        	request.setAttribute("hideMode", "true");
        }else{
        	request.setAttribute("replyType", 0);
        }
    }
    //子贴回复时，需要更新最顶级帖子的回复数，传递参数，更新时使用
    if(topPsId !=null && !topPsId.equals("")){
    	request.setAttribute("topPsId", topPsId);
    }else{
    	request.setAttribute("topPsId", "-1");
    }

    return "newNote";
  }

  *//**
   * 新增帖子
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
  @RequestMapping("addNote")
  @ResponseBody
  public Object addNote(HttpServletRequest request, WxPosts wxPosts, Integer secId,String topPsId, @RequestParam(value = "files", required = false) CommonsMultipartFile[] files, Model model)
  {
    String result="";
	wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
	wxPosts.setPsViews(0);
	// 判断是否为主贴，如果是回复帖子，则不需要审核即可显示。
	if (wxPosts.getPsParentId().equals("-1"))
	{
	  wxPosts.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_DAI_SHEN_HE);
	} else
	{
	  wxPosts.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS);
	}
	//党员E家-常见问题维护，此字段根据页面传值设置，标志是否常见问题
	if(wxPosts.getPsSecId()==null || wxPosts.getPsSecId() != WeiXinUtil.PS_DYEJ_SEC_ID || wxPosts.getPsIsTop() == null){
		wxPosts.setPsIsTop(0);
	}
	if (wxPosts.getPsSecId()!=null && wxPosts.getPsSecId() == WeiXinUtil.PS_DYEJ_SEC_ID && !wxPosts.getPsParentId().equals("-1")){
		WxPosts updatePosts = wxPostsService.getWxPosts(wxPosts.getPsParentId());
		if(checkRole(request, secId)){
			wxPosts.setPsField3("1");//标识子贴是否管理员回复，后续查看帖子时取历史用户权限，不取实时
			updatePosts.setPsIsDoing(1);//标识主贴是否已有管理员回复，方便后面统计,0-未回复，1-已回复
		}else{
			wxPosts.setPsField3("0");
			updatePosts.setPsIsDoing(0);
		}
		wxPostsService.updateWxPosts(updatePosts);
	}
	result = wxPostsService.addWxPosts(wxPosts);
	JSONObject object = JSON.parseObject(result);
	String psId = null;
	if (object != null)
	{
	  psId = object.getString("psId");
	  WxOptLog optLog = new WxOptLog();
	  optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD);
	  optLog.setLogPsId(psId);
	  optLog.setLogUserId(getUser(request));
	  optLog.setLogRemark("新增帖子，帖子ID：" + psId);
	  wxOptLogService.addLog(optLog);
	}
	
	dealUploadFiles(request, psId,1,"");//批量处理上传文件。

	model.addAttribute("secId", secId);
	if (secId != null && (secId == 36|| secId == 39 || secId == 40))
	{
	  return result;
	}
	if (!wxPosts.getPsParentId().equals("-1"))
	{
	  synchronized (this)
	  {
	    wxPostsService.replys(wxPosts.getPsParentId());
	    String socreObjId = wxPosts.getPsParentId();
	    //回复子贴时，更新下顶级主帖的回复数
	    if(topPsId != null && !topPsId.equals("-1")){
	    	 wxPostsService.replys(topPsId);
	    	 socreObjId = topPsId;
	    }
	    //回帖后更新用户积分
	    updateUserScore(request, WeiXinUtil.SC_CODE_POST_REPLY,socreObjId);
	  }
	} else
	{
	  WxUserAttention userAttention = new WxUserAttention();
	  userAttention.setAtnPsId(psId);
	  userAttention.setAtnUserId(getUser(request));
	  userAttention.setAtnStatus(Integer.parseInt(wxPosts.getPsIsAttention()));
	  wxUserAttentionService.addAttention(userAttention);
	}
    
    return result;
  }
  
	private void dealUploadFiles(HttpServletRequest request, String objId,int uploadFileType,String otherFileName) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			boolean isUploadScrollPic = false;
			boolean firstPicFlag = false; //标识第一张图片，首页展示时使用
			while (iter.hasNext()) {
				
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				for (MultipartFile multipartFile : files) {
					// 取得上传文件
					MultipartFile file = multipartFile;
					if (file != null) {
						String inputFileName = multipartFile.getName();
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
						if(uploadFileType == 2){
							if(otherFileName != null && otherFileName.equals(inputFileName)){
								wxFile.setFileType(WeiXinUtil.FILE_TYPE_NORMAL);
								wxFile.setFileIsPic(WeiXinUtil.FILE_IS_NOT_PIC);
							}else{
								String type = (String) multiRequest.getParameter("fileType");
								String changeFileName = multiRequest.getParameter("changeFileName");
								//微课堂只处理第一张图片的滚动方式。
								if(changeFileName != null && !changeFileName.equals("") && fileName.equals(changeFileName) && !isUploadScrollPic){
									wxFile.setFileType(WeiXinUtil.FILE_TYPE_GUNDONG_PIC);
									isUploadScrollPic = true;
								}else{
									wxFile.setFileType(WeiXinUtil.FILE_TYPE_NORMAL);
								}
								if(!firstPicFlag){
									wxFile.setFileField2("1");
									firstPicFlag = true;
								}
								wxFile.setFileIsPic(WeiXinUtil.FILE_IS_PIC);
								
							}
						}
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
  @RequestMapping("detailPage")
  public String detailPage(HttpServletRequest request, Integer secId, String psId ,Integer replyManage)
  {
    WxSection section = wxSectionService.getWxSection(secId);
    request.setAttribute("section", section);
    
    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
   
	wxPosts.setPsContent(replaceLine(replaceUrl(wxPosts.getPsContent())));
    request.setAttribute("wxPosts", wxPosts);
    
    if(replyManage != null){
    	request.setAttribute("replyManage", replyManage);
    }else{
    	request.setAttribute("replyManage", 0);
    }
    
    WxFile wxFile = new WxFile();
    wxFile.setFilePsId(psId);
    wxFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
    List<WxFile> files = wxFileService.getFileList(wxFile);
    request.setAttribute("files", files);

    WxOptLog optLog = new WxOptLog();
    optLog.setLogOpt(WeiXinUtil.LOG_OPT_VIEW);
    optLog.setLogPsId(psId);
    optLog.setLogUserId(getUser(request));
    optLog.setLogRemark("查看帖子，帖子ID：" + psId);
    wxOptLogService.addLog(optLog);
    
    if (wxPosts.getPsParentId().equals("-1"))
    {
      synchronized (this)
      {
        wxPostsService.clicks(psId);
      }
    }
    
    
    request.setAttribute("isAtnPost", wxUserAttentionService.isAtnPost(getUser(request), psId));

    return "detailPage";
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
  @RequestMapping("cktwPage")
  public String cktwPage(HttpServletRequest request, Integer secId, String psId,Integer channel)
  {
	//0-查看提问页面，1-管理员回复
	if(channel != null){
		request.setAttribute("channel", channel);
	}else{
		request.setAttribute("channel", 0);
	}
    WxSection section = wxSectionService.getWxSection(secId);
    request.setAttribute("section", section);

    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
    wxPosts.setPsContent(replaceLine(replaceUrl(wxPosts.getPsContent())));
    request.setAttribute("wxPosts", wxPosts);

    return "cktwPage";
  }

  *//**
   * 查询帖子回复列表
   * 
   * @param request
   *          request请求
   * @param psId
   *          帖子ID
   * @return
   *//*
  @RequestMapping(value =
    { "/querySubPosts" })
  @ResponseBody
  public Object querySubPosts(HttpServletRequest request, Integer pageNo,String psId)
  {
	  
	WxPosts qryCond = new WxPosts();
	qryCond.setPsParentId(psId);
	qryCond.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS);
	qryCond.setPsStatus(WeiXinUtil.PS_STATUS_OK);
	qryCond.setPageNumber(pageNo);
	qryCond.setPsField5(psId);
	qryCond.setAtnUserId(getUser(request));
    Page<WxPosts> data = wxPostsService.getSubPosts(qryCond);

    List<WxPosts> result = data.getResult();
    int pageCount = data.getLastPageNumber();
    int pageSize = data.getPageSize();
    
    List<WxPosts> childPostList = new ArrayList<WxPosts>();
    childPostList.addAll(result);
    
    for (WxPosts wxPosts : childPostList) {
    	
	}
    
    List<WxPosts> subPostList = new ArrayList<WxPosts>();
    for (WxPosts wxPosts : result) {
    	WxFile wxFile = new WxFile();
	    wxFile.setFilePsId(wxPosts.getPsId());
	    wxFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
	    wxFile.setFileIsPic(WeiXinUtil.FILE_IS_PIC);
	    List<WxFile> files = wxFileService.getFileList(wxFile);
	    wxPosts.setPicFileList(files);
	    wxPosts.setPsContent(replaceLine(replaceUrl(wxPosts.getPsContent())));
	    
	    qryCond = new WxPosts();
		qryCond.setPsId(wxPosts.getPsId());
		qryCond.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS);
		qryCond.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		qryCond.setPsField5(wxPosts.getPsId());
		qryCond.setPsField6(psId);
		qryCond.setAtnUserId(getUser(request));
		
		subPostList = wxPostsService.getAllParentPosts(qryCond);
		if(subPostList != null && subPostList.size() > 0){
			wxPosts.setSubPostList(subPostList);
		}
	    List<WxPosts> childPostList = new ArrayList<WxPosts>();
	    getParentPosts(result,childPostList, wxPosts.getPsParentId());
	    if(childPostList != null && childPostList.size() > 0){
			wxPosts.setSubPostList(childPostList);
		}
		
	}
   

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("pageCount", pageCount);
    map.put("pageSize", pageSize);
    return map;
  }
  
  private static void getParentPosts(List<WxPosts> postList,List<WxPosts> retList,String pid){
	  for (WxPosts wxPosts : postList) {
		  if(pid.equals(wxPosts.getPsId()) && !retList.contains(wxPosts)){
			  retList.add(wxPosts);
			  getParentPosts(postList,retList, wxPosts.getPsParentId());
		  }
		  if(!wxPostsIn.getPsParentId().equals("-1")){
			  getParentPosts(postList, wxPostsIn);
		  }
		 
	  }
  }

  *//**
   * 查询帖子
   * 
   * @param request
   *          request请求
   * @param psId
   *          帖子ID
   * @return
   *//*
  @RequestMapping(value =
    { "/getPost" })
  @ResponseBody
  public Object getPost(HttpServletRequest request, String psId)
  {
    Map<String, Object> map = new HashMap<String, Object>();
    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
    map.put("wxPosts", wxPosts);
    WxFile wxFile = new WxFile();
    wxFile.setFilePsId(psId);
    wxFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
    List<WxFile> files = wxFileService.getFileList(wxFile);
    map.put("files", files);
    WxUser wxUser = wxUserService.getWxUser(wxPosts.getPsUserId());
    map.put("wxUser", wxUser);
    return map;
  }

  *//**
   * 修改帖子
   * 
   * @param request
   *          request请求
   * @param psId
   *          帖子ID
   * @param checkStatus
   *          审核状态：0为待审核，1为审核中，2为审核通过，-1为审核不通过，默认为0
   * @param psTitle
   *          帖子标题
   * @param psIsTop
   *          是否置顶：0为未置顶，1为置顶，默认为0
   * @param psIsDoing
   *          是否完成：0为未完成，1为已完成，默认为0
   * @return
   *//*
  @RequestMapping(value =
    { "/updatePost" })
  @ResponseBody
  public String updatePost(HttpServletRequest request, String psId, Integer checkStatus, String psTitle,
		  String psContent,Integer psIsTop, Integer psIsDoing,Integer reqType,String applyAdvice)
  {
    WxPosts wxPosts = wxPostsService.getWxPosts(psId);

    if (psIsTop != null)
    {
      if (psIsTop == 1)
      {
        WxOptLog optLog = new WxOptLog();
        optLog.setLogOpt(WeiXinUtil.LOG_OPT_ZHIDING);
        optLog.setLogPsId(psId);
        optLog.setLogUserId(getUser(request));
        optLog.setLogRemark("帖子置顶，帖子ID：" + psId);
        wxOptLogService.addLog(optLog);
        
        //reqType: 1-回帖，0-主帖
      	if(reqType != null && reqType == 1){
      	    //回帖置顶后更新用户积分
          	updateUserScore(request, WeiXinUtil.SC_CODE_POST_REPLY_TOP,wxPosts.getPsId());
      	}else{
          	//主帖置顶后更新用户积分
          	updateUserScore(request, WeiXinUtil.SC_CODE_POST_TOP,wxPosts.getPsId());
      	}
      } else
      {
        WxOptLog optLog = new WxOptLog();
        optLog.setLogOpt(WeiXinUtil.LOG_OPT_CANCEL_ZHIDING);
        optLog.setLogPsId(psId);
        optLog.setLogUserId(getUser(request));
        optLog.setLogRemark("帖子取消置顶，帖子ID：" + psId);
        wxOptLogService.addLog(optLog);
      }
      wxPosts.setPsIsTop(psIsTop);
    }
    if (psTitle != null && !psTitle.equals(""))
    {
    	wxPosts.setPsTitle(psTitle);
    }
    if (psContent != null && !psContent.equals(""))
    {
    	wxPosts.setPsContent(psContent);
    }
    if (applyAdvice != null && !applyAdvice.equals(""))
    {
    	wxPosts.setPsField6(applyAdvice);//保存审核意见
    }
    if (checkStatus != null)
    {
      wxPosts.setPsCheckStatus(checkStatus);
      WxOptLog optLog = new WxOptLog();
      optLog.setLogOpt(WeiXinUtil.LOG_OPT_UPDATE);
      optLog.setLogPsId(psId);
      optLog.setLogUserId(getUser(request));
      optLog.setLogRemark("修改帖子审核状态：" + checkStatus);
      wxOptLogService.addLog(optLog);
      
      //审核通过后更新用户积分
      if(checkStatus == WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS ){
    	  updateUserScore(request, WeiXinUtil.SC_CODE_POST_PASS,wxPosts.getPsId());
      }
      
    }
    if (psIsDoing != null)
    {
      wxPosts.setPsIsDoing(psIsDoing);
      WxOptLog optLog = new WxOptLog();
      optLog.setLogOpt(WeiXinUtil.LOG_OPT_UPDATE);
      optLog.setLogPsId(psId);
      optLog.setLogUserId(getUser(request));
      optLog.setLogRemark("修改帖子完成状态：" + psIsDoing);
      wxOptLogService.addLog(optLog);
    }
    

    return wxPostsService.updateWxPosts(wxPosts);
  }

  *//**
   * 删除帖子
   * 
   * @param request
   *          request请求
   * @param psId
   *          帖子ID
   * @return
   *//*
  @RequestMapping(value =
    { "/deletePost" })
  @ResponseBody
  public boolean deletePost(HttpServletRequest request, String psId)
  {
    boolean result = wxPostsService.deleteWxPosts(psId);
    WxOptLog optLog = new WxOptLog();
    optLog.setLogOpt(WeiXinUtil.LOG_OPT_DELETE);
    optLog.setLogPsId(psId);
    optLog.setLogUserId(getUser(request));
    optLog.setLogRemark("删除帖子，帖子ID：" + psId);
    wxOptLogService.addLog(optLog);
    return result;
  }

  *//**
   * 关注帖子
   * 
   * @param request
   * @param psId
   * @return
   *//*
  @RequestMapping(value =
    { "/attention" })
  @ResponseBody
  public String attention(HttpServletRequest request, String psId, Integer atnStatus)
  {
    WxUserAttention userAttention = new WxUserAttention();
    userAttention.setAtnPsId(psId);
    userAttention.setAtnUserId(getUser(request));
    userAttention.setAtnStatus(atnStatus);
    String result = wxUserAttentionService.addAttention(userAttention);

    WxOptLog optLog = new WxOptLog();
    optLog.setLogOpt(WeiXinUtil.LOG_OPT_ATTENTION);
    optLog.setLogPsId(psId);
    optLog.setLogUserId(getUser(request));
    optLog.setLogRemark("关注帖子，帖子ID：" + psId + ",关注状态：" + atnStatus);
    wxOptLogService.addLog(optLog);

    return result;
  }
  
  *//**
   * 点赞/取消点赞
   * 
   * @param request
   * @param psId
   * @return
   *//*
  @RequestMapping(value =
    { "/changeThumbs" })
  @ResponseBody
  public String changeThumbs(HttpServletRequest request, String psId,Integer atnThumbsStatus)
  {
	WxUserAttention qryCond = new WxUserAttention();
	qryCond.setAtnPsId(psId);
	qryCond.setAtnUserId(getUser(request));
	String result ="";
	String tumbsCount = "0";
	WxPosts wxPosts = wxPostsService.getWxPosts(psId);
	List<WxUserAttention> attenList = wxUserAttentionService.getUserAttentionList(qryCond);
	if(attenList != null && attenList.size()>0){
		WxUserAttention userAttention = attenList.get(0);
		if(atnThumbsStatus == WeiXinUtil.PS_ATN_THUMBS_YES && userAttention.getAtnThumbsStatus() == WeiXinUtil.PS_ATN_THUMBS_NO){
	    	synchronized (this){
	    		int thumbsCount = 0;
	    		if(wxPosts.getPsField5() != null){
	    			thumbsCount = Integer.parseInt(wxPosts.getPsField5())+1;
	    			wxPosts.setPsField5(String.valueOf(thumbsCount));
	    		}else{
	    			wxPosts.setPsField5("1");
	    		}
	    		wxPostsService.updateWxPosts(wxPosts);
	    	}
		}else if(atnThumbsStatus == WeiXinUtil.PS_ATN_THUMBS_NO && userAttention.getAtnThumbsStatus() == WeiXinUtil.PS_ATN_THUMBS_YES ){
			synchronized (this){
	    		int thumbsCount = 0;
	    		if(wxPosts.getPsField5() != null){
	    			thumbsCount = Integer.parseInt(wxPosts.getPsField5())-1;
	    			wxPosts.setPsField5(String.valueOf(thumbsCount));
	    		}else{
	    			wxPosts.setPsField5("0");
	    		}
	    		wxPostsService.updateWxPosts(wxPosts);
	    	}
		}
		userAttention.setAtnThumbsStatus(atnThumbsStatus);
		result = wxUserAttentionService.updateAttention(userAttention);
		
	}else{
		  WxUserAttention userAttention = new WxUserAttention();
		  userAttention.setAtnPsId(psId);
		  userAttention.setAtnUserId(getUser(request));
		  userAttention.setAtnStatus(WeiXinUtil.PS_ATN_NO);
		  userAttention.setAtnThumbsStatus(WeiXinUtil.PS_ATN_THUMBS_YES);
		  result = wxUserAttentionService.addAttention(userAttention);
		  synchronized (this){
	    		int thumbsCount = 0;
	    		if(wxPosts.getPsField5() != null){
	    			thumbsCount = Integer.parseInt(wxPosts.getPsField5())+1;
	    			wxPosts.setPsField5(String.valueOf(thumbsCount));
	    		}else{
	    			wxPosts.setPsField5("1");
	    		}
	    		wxPostsService.updateWxPosts(wxPosts);
	    	}
	}
	tumbsCount = wxPosts.getPsField5();
    return tumbsCount;
  }

  *//**
   * 发布公告
   * 
   * @param request
   * @param wxNotice
   * @param secId
   * @param files
   *          文件，暂时保留
   * @param model
   * @return
   *//*
  @RequestMapping(value =
    { "/saveNotice" })
  @ResponseBody
  public Object saveNotice(HttpServletRequest request, WxNotice wxNotice, Integer secId, @RequestParam(value = "files", required = false) CommonsMultipartFile[] files, Model model)
  {
    wxNotice.setnStatus(WeiXinUtil.PS_STATUS_OK);
    wxNotice.setnIsTop(0);
    wxNotice.setnViews(0);
    wxNotice.setnPostType(0);
    wxNotice.setnCheckStatus(WeiXinUtil.PS_CHECK_STATUS_DAI_SHEN_HE);
    String result = wxNoticeService.addWxNotice(wxNotice);
    JSONObject object = JSON.parseObject(result);
    String nId = null;
    if (object != null)
    {
      nId = object.getString("nId");
      WxOptLog optLog = new WxOptLog();
      optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD);
      optLog.setLogPsId(nId);
      optLog.setLogUserId(getUser(request));
      optLog.setLogRemark("新增版块公告，公告ID：" + nId);
      wxOptLogService.addLog(optLog);
    }
    model.addAttribute("secId", secId);
    return result;
  }

  *//**
   * 查询具体公告内容，点击超链接时使用
   * 
   * @param request
   * @param wxNotice
   * @param secId
   * @param model
   * @return
   *//*
  @RequestMapping(value =
    { "/queryNoticeDetail" })
  public String queryNoticeDetail(HttpServletRequest request, WxNotice wxNotice, String nId, Integer secId, Model model)
  {
    WxNotice notice = wxNoticeService.getWxNotice(nId);
    notice.setnContent(replaceLine(replaceUrl(notice.getnContent())));
    model.addAttribute("wxNotice", notice);

    WxSection section = wxSectionService.getWxSection(secId);
    model.addAttribute("section", section);

    if (secId == 36)
    {
      return "sysNoticeDetail";
    } else
    {
      return "noticeDetail";
    }

  }

  *//**
   * 查询公告列表（分页）
   * 
   * @param request
   * @param pageNo
   * @param secId
   * @param model
   * @return
   *//*
  @RequestMapping(value =
    { "/queryNoticePage" })
  @ResponseBody
  public Object queryNoticePage(HttpServletRequest request, Integer pageNo, Integer secId, String parentId, Model model)
  {
    if (pageNo == null)
    {
      pageNo = 1;
    }

    WxSection section = wxSectionService.getWxSection(secId);
    model.addAttribute("section", section);

    WxNotice wxNotice = new WxNotice();
    wxNotice.setPageNumber(pageNo);
    wxNotice.setnSecId(secId);
    wxNotice.setnStatus(1);
    wxNotice.setnParentId(parentId);

    Page<WxNotice> data = wxNoticeService.queryPageWxNotice(wxNotice);

    List<WxNotice> result = data.getResult();
    int pageCount = data.getLastPageNumber();
    int pageSize = data.getPageSize();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", result);
    map.put("pageCount", pageCount);
    map.put("pageSize", pageSize);
    return map;
  }

  *//**
   * 查询具体公告信息，异步查询
   * 
   * @param request
   * @param nId
   * @return
   *//*
  @RequestMapping(value =
    { "/queryNotice" })
  @ResponseBody
  public Object queryNotice(HttpServletRequest request, String nId)
  {
    Map<String, Object> map = new HashMap<String, Object>();
    WxNotice notice = wxNoticeService.getWxNotice(nId);

    map.put("wxNotice", notice);
    return map;
  }

  *//**
   * 删除公告，保留数据，数据库nStatus设置为0
   * 
   * @param request
   * @param nId
   * @return
   *//*
  @RequestMapping(value =
    { "/deleteNotice" })
  @ResponseBody
  public Object deleteNotice(HttpServletRequest request, String nId)
  {
    wxNoticeService.deleteWxNotice(nId);
    WxOptLog optLog = new WxOptLog();
    optLog.setLogOpt(WeiXinUtil.LOG_OPT_DELETE);
    optLog.setLogPsId(nId);
    optLog.setLogUserId(getUser(request));
    optLog.setLogRemark("删除公告，公告ID：" + nId);
    wxOptLogService.addLog(optLog);
    return "true";
  }

  @RequestMapping(value =
    { "/updateNotice" })
  @ResponseBody
  public String updateNotice(HttpServletRequest request, String nId, String nContent, String nTitle)
  {
    WxNotice wxNotice = wxNoticeService.getWxNotice(nId);
    if (nContent != null && nTitle != null)
    {
      wxNotice.setnContent(nContent);
      wxNotice.setnTitle(nTitle);
      WxOptLog optLog = new WxOptLog();
      optLog.setLogOpt(WeiXinUtil.LOG_OPT_UPDATE);
      optLog.setLogPsId(nId);
      optLog.setLogUserId(getUser(request));
      optLog.setLogRemark("更新公告,公告ID：" + nId);
      wxOptLogService.addLog(optLog);
    }
    return wxNoticeService.updateWxNotice(wxNotice);
  }

  @RequestMapping(value =
    { "/userInfo" })
  public String userInfo(HttpServletRequest request, String psUserId, Model model)
  {
    WxUser wxUser = wxUserService.getWxUser(psUserId);
    model.addAttribute("wxUser", wxUser);
    return "userInfo";
  }

  @RequestMapping(value =
    { "/getUserInfo" })
  @ResponseBody
  public Object getUserInfo(HttpServletRequest request, String userId)
  {
    Map<String, Object> map = new HashMap<String, Object>();
    WxUser wxUser = wxUserService.getWxUser(userId);
    map.put("wxUser", wxUser);
    return map;
  }
    
    *//**
     * 微课堂主页
     * @param request
     * @param secId
     * @param model
     * @return
     *//*
	@RequestMapping("xfwkt")
	public String xfwkt(HttpServletRequest request, Integer secId, Model model) {

		WxSection section = wxSectionService.getWxSection(secId);
		model.addAttribute("section", section);
		boolean isManageAuth = checkRole(request, secId);
		request.setAttribute("isBkManageAuth", isManageAuth);
		List<WxSection> list = wxSectionService.getSubSectionList(secId);

		int tempSecId = 0;
		for (WxSection wxSection : list) {
			if (wxSection.getSecEn() != null
					&& wxSection.getSecEn().equals("xfwkt")) {
				model.addAttribute("secId", wxSection.getSecId());
				tempSecId = wxSection.getSecId();
			}

		}

		List<WxSection> sublist = wxSectionService.getSubSectionList(tempSecId);
		int subSecId = 0;
		for (WxSection wxSection : sublist) {
			if (wxSection != null && wxSection.getSecEn().equals("ztjz")) {
				subSecId = wxSection.getSecId();
			}
		}

		WxPosts wxPosts = new WxPosts();
		wxPosts.setPsSecId(subSecId);
		wxPosts.setFileIsPic(1);
		wxPosts.setFileStatus(1);
		wxPosts.setFileType(2);
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);

		wxPosts.setPageNumber(0);
		wxPosts.setPageSize(5);
		Page<WxPosts> data = wxPostsService.getLectureByPages(wxPosts);
		model.addAttribute("picShowList", data.getResult());
		return "xfwkt";
	}

	*//**
	 * 新增讲座页面
	 * 
	 * @param request
	 * @param secId
	 * @return
	 *//*
	@RequestMapping("lectureAdd")
	public String lectureAdd(HttpServletRequest request, Integer secId) {
		WxSection section = wxSectionService.getWxSection(secId);
		request.setAttribute("topSection", section);
		List<WxSection> list = wxSectionService.getSubSectionList(section
				.getSecId());
		for (WxSection wxSection : list) {
			if (wxSection.getSecEn().equals("ztjz")) {
				request.setAttribute("section", wxSection);
			}
		}

		return "lectureAdd";
	}
	
	*//**
	 * 新增推荐页面
	 * 
	 * @param request
	 * @param secId
	 * @return
	 *//*
	@RequestMapping("recommendBookAdd")
	public String recommendBookAdd(HttpServletRequest request, Integer secId) {
		WxSection section = wxSectionService.getWxSection(secId);
		request.setAttribute("topSection", section);
		List<WxSection> list = wxSectionService.getSubSectionList(section
				.getSecId());
		for (WxSection wxSection : list) {
			if (wxSection.getSecEn().equals("hstj")) {
				request.setAttribute("section", wxSection);
			}
		}

		return "recommendBookAdd";
	}

	*//**
	 * 维护讲座页面
	 * 
	 * @param request
	 * @param secId
	 * @return
	 *//*
	@RequestMapping("lectureEdit")
	public String lectureEdit(HttpServletRequest request, Integer secId) {
		WxSection section = wxSectionService.getWxSection(secId);
		request.setAttribute("topSection", section);
		List<WxSection> list = wxSectionService.getSubSectionList(section
				.getSecId());
		for (WxSection wxSection : list) {
			if (wxSection.getSecEn().equals("ztjz")) {
				request.setAttribute("section", wxSection);
			}
		}

		return "lectureEdit";
	}
	*//**
	 * 维护好书推荐页面
	 * 
	 * @param request
	 * @param secId
	 * @return
	 *//*
	@RequestMapping("recommendBookEdit")
	public String recommendBookEdit(HttpServletRequest request, Integer secId) {
		WxSection section = wxSectionService.getWxSection(secId);
		request.setAttribute("topSection", section);
		List<WxSection> list = wxSectionService.getSubSectionList(section
				.getSecId());
		for (WxSection wxSection : list) {
			if (wxSection.getSecEn().equals("hstj")) {
				request.setAttribute("section", wxSection);
			}
		}
		
		return "recommendBookEdit";
	}
  
	*//**
	 * 新增主题讲座、好书推荐
	 * 
	 * @param request
	 * @param wxPosts
	 * @param secId
	 * @param files
	 * @param model
	 * @return
	 *//*
	@RequestMapping("addLecture")
	@ResponseBody
	public Object addLecture(HttpServletRequest request, WxPosts wxPosts,
			Integer secId, Model model) {
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		wxPosts.setPsIsTop(0);
		wxPosts.setPsViews(0);
		wxPosts.setPsParentId("-1");
		wxPosts.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS);
		String result = "";
		try {
			result = wxPostsService.addWxPosts(wxPosts);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject object = JSON.parseObject(result);
		String psId = null;
		if (object != null) {
			psId = object.getString("psId");
			WxOptLog optLog = new WxOptLog();
			optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD);
			optLog.setLogPsId(psId);
			optLog.setLogUserId(getUser(request));
			optLog.setLogRemark("新增主题讲座/好书推荐，ID：" + psId);
			wxOptLogService.addLog(optLog);
		}

		dealUploadFiles(request, psId, 2, "otherFiles");

		model.addAttribute("secId", secId);
		return result;
	}
  
	*//**
	 * 维护主题讲座、好书推荐
	 * 
	 * @param request
	 * @param wxPosts
	 * @param secId
	 * @param model
	 * @return
	 *//*
	@RequestMapping("updateLecture")
	@ResponseBody
	public Object updateLecture(HttpServletRequest request, WxPosts wxPosts,
			String delFiles, Integer secId, String changeFileTypeId, Model model) {
		WxPosts updatePost = wxPostsService.getWxPosts(wxPosts.getPsId());
		String result = "";
		try {
			updatePost.setPsTitle(wxPosts.getPsTitle());
			updatePost.setPsContent(wxPosts.getPsContent());
			if(wxPosts.getPsField2() != null){
				updatePost.setPsField2(wxPosts.getPsField2());
			}
			result = wxPostsService.updateWxPosts(updatePost);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject object = JSON.parseObject(result);
		String psId = null;
		if (object != null) {
			psId = object.getString("psId");
			WxOptLog optLog = new WxOptLog();
			optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD);
			optLog.setLogPsId(psId);
			optLog.setLogUserId(getUser(request));
			optLog.setLogRemark("更新主题讲座、好书推荐，ID：" + psId);
			wxOptLogService.addLog(optLog);
		}

		// 处理新上传的文件
		dealUploadFiles(request, wxPosts.getPsId(), 2, "otherFiles");

		// 处理需要删除的文件
		if (delFiles != null && !delFiles.equals("")) {
			String[] fileId = delFiles.split(",");
			if (fileId != null && fileId.length > 0) {
				for (String obj : fileId) {
					wxFileService.deleteFile(obj);
				}
			}

		}

		// 兼容页面只更新“是否滚动图片”选项的情况
		WxFile qryFile = new WxFile();
		qryFile.setFilePsId(wxPosts.getPsId());
		qryFile.setFileIsPic(WeiXinUtil.FILE_IS_PIC);
		qryFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
		List<WxFile> updateFileList = wxFileService.getFileList(qryFile);
		
		//fileFiled2批量更新为0
		WxFile updateFile = new WxFile();
		updateFile.setFileField2("0");//列表显示图片，0-不显示，1-显示
		updateFile.setFilePsId(wxPosts.getPsId());
		if(wxPosts.getFileType() == WeiXinUtil.FILE_TYPE_NORMAL){
			updateFile.setFileType(WeiXinUtil.FILE_TYPE_NORMAL);
		}
		wxFileService.updateFileStatus(updateFile);
		
		//fileFiled2第一条更新为1
		if(updateFileList != null && updateFileList.size() > 0){
			updateFile = new WxFile();
			updateFile = updateFileList.get(0);
			if(wxPosts.getFileType() == WeiXinUtil.FILE_TYPE_NORMAL){
				updateFile.setFileType(WeiXinUtil.FILE_TYPE_NORMAL);
			}
			updateFile.setFileField2("1");
			wxFileService.updateFileStatus(updateFile);
		}
		
		
		//维护时，触发更新
		if(changeFileTypeId != null && !changeFileTypeId.equals("")){
			updateFile = wxFileService.getFileById(changeFileTypeId);
			updateFile.setFileType(wxPosts.getFileType());
			wxFileService.updateFile(updateFile);
		}

		model.addAttribute("secId", secId);
		return result;
	}
  
  
	@RequestMapping(value = { "/queryLectures" })
	@ResponseBody
	public Object queryLectures(Integer secId, Integer pageNo,String secType, Model model)
			throws Exception {
		logger.info("pageNo=" + pageNo);
		if (pageNo == null) {
			pageNo = 1;
		}

		List<WxSection> list = wxSectionService.getSubSectionList(secId);
		int subSecId = 0; 
		for (WxSection wxSection : list) {
			if(wxSection != null ){
				if(secType != null && secType.equals("01") && wxSection.getSecEn().equals("ztjz")){
					subSecId = wxSection.getSecId();
				}else if(secType != null && secType.equals("02") && wxSection.getSecEn().equals("hstj")){
					subSecId = wxSection.getSecId();
				}
				
			}
		}
		
		
		WxPosts wxPosts = new WxPosts();
		wxPosts.setPsSecId(subSecId);
		wxPosts.setFileIsPic(WeiXinUtil.FILE_IS_PIC);
		wxPosts.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
		if(secType != null && secType.equals("01")){
			wxPosts.setPsField10("1");
		}
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);

		wxPosts.setPageNumber(pageNo);
		
		Page<WxPosts> data = wxPostsService.queryPageWxPosts(wxPosts);
		List<WxPosts> result = data.getResult();
		String psIds = "";
		for (WxPosts obj : result) {
			psIds+=obj.getPsId()+",";
		}
		if(psIds.length() >0){
			psIds =  psIds.substring(0,psIds.length()-1);
		}
		WxFile qryFile = new WxFile();
		qryFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
		qryFile.setFileIsPic(WeiXinUtil.FILE_IS_PIC);
		qryFile.setFilePsId(psIds);
		List<WxFile> fileList = wxFileService.getFilesByPsIds(qryFile);
		
		for (WxPosts obj : result) {
			String filePath = "";
			for (WxFile wxFile : fileList) {
				if(secType.equals("01")){
					if(obj.getPsId().equals(wxFile.getFilePsId()) && wxFile.getFileField2().equals("1")){
						filePath = wxFile.getFilePath();
						break;
					}
				}else{
					if(obj.getPsId().equals(wxFile.getFilePsId())){
						filePath = wxFile.getFilePath();
						break;
					}
				}
				
			}
			obj.setFilePath(filePath);
		}
		
		model.addAttribute("wxPosts", wxPosts);
		
		int pageCount = data.getLastPageNumber();
		int pageSize = data.getPageSize();
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("pageCount", pageCount);
		map.put("pageSize", pageSize);
		map.put("secList", list);
		return map;
	}
	
	
	*//**
	 * 查看主题讲座、好书推荐
	 * 
	 * @param request
	 * @param secId
	 * @return
	 *//*
	@RequestMapping("lectureDetail")
	public String lectureDetail(HttpServletRequest request, Integer secId,String psId, Model model,String changeType) {
		WxSection section = wxSectionService.getWxSection(secId);
		request.setAttribute("topSection", section);
		List<WxSection> list = wxSectionService.getSubSectionList(section
				.getSecId());
		for (WxSection wxSection : list) {
			if (wxSection.getSecEn().equals("ztjz")) {
				request.setAttribute("section", wxSection);
			}
		}
		
	    WxPosts wxPosts = wxPostsService.getWxPosts(psId);
	    wxPosts.setPsContent(replaceLine(replaceUrl(wxPosts.getPsContent())));
	    model.addAttribute("wxPosts", wxPosts);
	    WxFile wxFile = new WxFile();
	    wxFile.setFilePsId(psId);
	    wxFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
	    List<WxFile> files = wxFileService.getFileList(wxFile);
	    model.addAttribute("files", files);
	    WxUser wxUser = wxUserService.getWxUser(wxPosts.getPsUserId());
	    model.addAttribute("wxUser", wxUser);
	    model.addAttribute("changeType",changeType);
	    
		// 用户首次浏览 更新用户积分
		updateUserScore(request, WeiXinUtil.SC_CODE_POST_BROWSE,wxPosts.getPsId());
		synchronized (this)
	    {
	      wxPostsService.clicks(psId);
	    }

		WxOptLog optLog = new WxOptLog();
	    optLog.setLogOpt(9);
	    optLog.setLogPsId(psId);
	    optLog.setLogUserId(getUser(request));
	    optLog.setLogRemark("浏览微课堂，ID：" + psId);
	    wxOptLogService.addLog(optLog);
	    
		return "lectureDetail";
	}
	
	@RequestMapping("downloadFile")
    public ResponseEntity<byte[]>  downloadFile(HttpServletRequest request,HttpServletResponse response,String fileName,String fileRealName) throws Exception {
    	ResponseEntity <byte[]> respEnt = null;
        try {
			HttpHeaders headers = new HttpHeaders();
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			File file = new File(realPath + File.separator + "resources/upload"
					+ File.separator + fileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", new String(
					fileRealName.getBytes("ISO8859-1"), "utf-8"));
			respEnt = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respEnt;
    }

	*//**
	 * 党员E家，查看管理员未回复列表
	 * @param model
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = { "/queryDyejReplys" })
	@ResponseBody
	public Object queryDyejReplys(Integer pageNo) throws Exception {
		WxPosts wxPosts = new WxPosts();
		wxPosts.setPsSecId(WeiXinUtil.PS_DYEJ_SEC_ID);
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		wxPosts.setPsIsDoing(0);
		wxPosts.setPsIsTop(0);
		wxPosts.setPsParentId("-1");
		
		if (pageNo == null) {
			pageNo = 1;
		}
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
	
	*//**
	 *党员E家， 更新常见提问
	 * @param request
	 * @param wxPosts
	 * @return
	 *//*
	@RequestMapping("updateQuest")
	@ResponseBody
	public Object updateQuest(HttpServletRequest request, WxPosts wxPosts) {
		WxPosts updatePost = wxPostsService.getWxPosts(wxPosts.getPsId());
		String result = "";
		try {
			updatePost.setPsTitle(wxPosts.getPsTitle());
			updatePost.setPsContent(wxPosts.getPsContent());
			if(wxPosts.getPsField2() != null){
				updatePost.setPsField2(wxPosts.getPsField2());
			}
			result = wxPostsService.updateWxPosts(updatePost);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JSONObject object = JSON.parseObject(result);
		String psId = null;
		if (object != null) {
			psId = object.getString("psId");
			WxOptLog optLog = new WxOptLog();
			optLog.setLogOpt(WeiXinUtil.LOG_OPT_ADD);
			optLog.setLogPsId(psId);
			optLog.setLogUserId(getUser(request));
			optLog.setLogRemark("更新党员E家常见提问，ID：" + psId);
			wxOptLogService.addLog(optLog);
		}

		return result;
	}
	
	*//**
	 * 党员E家，问题维护
	 * 
	 * @param request
	 *            request请求
	 * @param secId
	 *            板块ID
	 * @return
	 *//*
	@RequestMapping("dyejEdit")
	public String dyejEdit(HttpServletRequest request,Integer channel) {
		// 0-常见提问维护，1-其它提问维护
		if (channel != null) {
			request.setAttribute("channel", channel);
		} else {
			request.setAttribute("channel", 0);
		}
		WxSection section = wxSectionService.getWxSection(WeiXinUtil.PS_DYEJ_SEC_ID);
		request.setAttribute("section", section);

		return "dyejEdit";
	}
    
	*//**
	 * 根据规则编码计算用户积分
	 * @param request
	 * @param ruleCode
	 *//*
	private void updateUserScore(HttpServletRequest request,String ruleCode,String objId){
		String userId = getUser(request);
		WxUser wxUser = new WxUser();
		wxUser = wxUserService.getWxUser(userId);
		
		WxScoreRule wxScoreRule = new WxScoreRule();
		wxScoreRule.setRuleCode(ruleCode);
	    List<WxScoreRule> wxScoreRuleList = wxScoreRuleService.getWxScoreRuleList(wxScoreRule);
	    
	    WxScoreRule rule = new WxScoreRule();
	    if(wxScoreRuleList != null && wxScoreRuleList.size()>0){
	    	rule = wxScoreRuleList.get(0);
	    }else{
	    	return;
	    }
	    if(rule.getRuleScoreType() == 0){
	    	wxUser.setUserJiFen(wxUser.getUserJiFen()+rule.getRuleScore());
	    }else if(rule.getRuleScoreType() == 1){
	    	wxUser.setUserJiFen(wxUser.getUserJiFen()-rule.getRuleScore());
	    }
	    int limit = rule.getRuleLimit();
	    if(limit > 0 && !objId.equals("")){
	    	WxScoreLog qryLog = new WxScoreLog();
	    	qryLog.setLogUserId(wxUser.getUserId());
    		qryLog.setLogObjId(objId);
    		qryLog.setLogRuleId(ruleCode);
    		List<WxScoreLog> qryList = wxScoreLogService.getWxScoreLogList(qryLog);
	    	//先判断同一对象，是否已经赠送积分，是则返回
	    	if(qryList != null && qryList.size()>=1){
	    		return;
	    	}
	    	//该对象未触发积分记录，则根据ruleCode+ruleLimit+ruleLimitType判断该用户当月积分记录
	    	if(rule.getRuleLimitType() ==3){
	    		 String beginDate = getFormatDate(3)+" 00:00:00";
	    		 String endDate = getFormatDate(4)+" 23:59:59";
	    		 qryLog.setLogCreateTimeBegin(beginDate);
	    		 qryLog.setLogCreateTimeEnd(endDate);
	    		 qryLog.setLogObjId("");//清空objId
	    		 qryList = wxScoreLogService.getWxScoreLogList(qryLog);
		    	 //根据配置的次数限制 校验下是否符合规则
		    	 if(qryList != null && qryList.size()>=limit){
		    	 	return;
		    	 }
	    	}
	    	//该对象未触发积分记录，则根据ruleCode+ruleLimit+ruleLimitType判断该用户当日积分记录
	    	if(rule.getRuleLimitType() ==2){
	    		 String currDate = getFormatDate(5);
	    		 String beginDate = currDate+" 00:00:00";
	    		 String endDate = currDate+" 23:59:59";
	    		 qryLog.setLogCreateTimeBegin(beginDate);
	    		 qryLog.setLogCreateTimeEnd(endDate);
	    		 qryLog.setLogObjId("");//清空objId
	    		 qryList = wxScoreLogService.getWxScoreLogList(qryLog);
		    	 //根据配置的次数限制 校验下是否符合规则
		    	 if(qryList != null && qryList.size()>=limit){
		    	 	return;
		    	 }
	    	}
	    	
	    	
	    }
	    
	    synchronized (this){
	    	wxUserService.updateWxUser(wxUser);
	    }
	    
	    WxScoreLog log = new WxScoreLog();
	    log.setLogUserId(getUser(request));
	    log.setLogRuleId(ruleCode);
	    if(objId != null && !objId.equals("")){
	    	 log.setLogObjId(objId);
	    }
	    log.setLogOpt(rule.getRuleScoreType());
	    log.setLogScore(rule.getRuleScore());
		wxScoreLogService.addLog(log);
		
	}
	
	*//**
	 * 更新积分
	 * @param request
	 * @param psId
	 * @param ruleCode
	 * @return
	 *//*
	@RequestMapping(value = { "/changeUserScore" })
	@ResponseBody
	public Object changeUserScore(HttpServletRequest request, String psId,
			String ruleCode) {
		boolean flag = true;
		try {
			updateUserScore(request, ruleCode, psId);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
	}
	
	
	@RequestMapping(value = { "/queryAllPosts" },produces="text/html;charset=UTF-8")
	@ResponseBody
	public String queryAllPosts(Integer secId, String psParentId,
			Integer psPostType, Integer psStatus, Integer psCheckStatus,
			String searchValue, Model model) throws Exception {

		WxPosts wxPosts = new WxPosts();
		wxPosts.setPsSecId(secId);
		if (searchValue != null) {
			wxPosts.setPsTitle(searchValue);
		}

		if (psPostType != null) {
			wxPosts.setPsPostType(psPostType);
		}
		if (psStatus != null) {
			wxPosts.setPsStatus(psStatus);
		} else {
			wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		}
		if (psCheckStatus != null) {
			wxPosts.setPsCheckStatus(psCheckStatus);
		} else {
			wxPosts.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_DAI_SHEN_HE);
		}

		if (psParentId != null) {
			wxPosts.setPsParentId(psParentId);
		}

		model.addAttribute("wxPosts", wxPosts);
		List<WxPosts> result = wxPostsService.getWxPostsList(wxPosts);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		String data = JSON.toJSONString(map);
		return data;
	}
	
	private void initJssdkData(HttpServletRequest request){
    	
	}
	
	*//**
	 * 活动推广主页
	 * @param request
	 * @return
	 *//*
	@RequestMapping("sysEvent")
	public String sysEvent(HttpServletRequest request){
		WxScoreRule wxScoreRule = new WxScoreRule();
		wxScoreRule.setRuleCode(WeiXinUtil.SC_CODE_EVENT_SHARE);
	    List<WxScoreRule> wxScoreRuleList = wxScoreRuleService.getWxScoreRuleList(wxScoreRule);
	    WxScoreRule rule = new WxScoreRule();
	    if(wxScoreRuleList != null && wxScoreRuleList.size()>0){
	    	rule = wxScoreRuleList.get(0);
	    }
	    request.setAttribute("shareScore", rule.getRuleScore());
	    
	    wxScoreRule = new WxScoreRule();
		wxScoreRule.setRuleCode(WeiXinUtil.SC_CODE_EVENT_READ);
	    List<WxScoreRule> wxScoreRuleReadList = wxScoreRuleService.getWxScoreRuleList(wxScoreRule);
	    rule = new WxScoreRule();
	    if(wxScoreRuleReadList != null && wxScoreRuleReadList.size()>0){
	    	rule = wxScoreRuleReadList.get(0);
	    }
	    request.setAttribute("readScore", rule.getRuleScore());
	    
	    request.setAttribute("isEventAuth", checkRole(request, 39));
	    return "sysEvent";

	  }
	*//**
	 * 更新推广活动
	 * @param request
	 * @param psId
	 * @param psContent
	 * @param psField4
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = { "/updateEvent" })
	@ResponseBody
	public String updateEvent(HttpServletRequest request, String psId,
			String psContent, String psField4,String psTitle) throws Exception{
		WxPosts wxPosts = wxPostsService.getWxPosts(psId);
		if (psContent != null && psField4 != null) {
			wxPosts.setPsContent(psContent);
			wxPosts.setPsTitle(psTitle);
			wxPosts.setPsField4(psField4);
			WxOptLog optLog = new WxOptLog();
			optLog.setLogOpt(WeiXinUtil.LOG_OPT_UPDATE);
			optLog.setLogPsId(psId);
			optLog.setLogUserId(getUser(request));
			optLog.setLogRemark("更新推广活动,活动ID：" + psId);
			wxOptLogService.addLog(optLog);
		}
		return wxPostsService.updateWxPosts(wxPosts);
	}
	
	*//**
	 * 分享、阅读后更新用户积分
	 * @param request
	 * @param psId
	 * @param browseType
	 * @return
	 *//*
	@RequestMapping(value = { "/updateScoreByEvent" })
	@ResponseBody
	public String updateScoreByEvent(HttpServletRequest request, String psId,Integer browseType){
		if(browseType == 1){
			updateUserScore(request, WeiXinUtil.SC_CODE_EVENT_SHARE, psId);
		}else if(browseType == 2){
			updateUserScore(request, WeiXinUtil.SC_CODE_EVENT_READ, psId);
		}
		return "true";
	}
	
	*//**
	 * 活动分享主页
	 * @param request
	 * @param psId
	 * @param secId
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = { "/sysEventDetail" })
	public String sysEventDetail(HttpServletRequest request, String psId,
			Integer secId, Model model) {
		initJssdkData(request);
		WxPosts posts = wxPostsService.getWxPosts(psId);
		String url = posts.getPsField4();
		if(url != null && url.indexOf("http://") <0){
			posts.setPsField4("http://"+url);
		}
		model.addAttribute("wxPosts", posts);
		

		WxSection section = wxSectionService.getWxSection(secId);
		model.addAttribute("section", section);

		return "sysEventDetail";

	}

	*//**
	 * 轻松一刻首页
	 * 
	 * @param request
	 * @param channel
	 * @return
	 *//*
	@RequestMapping("funTime")
	public String funTime(HttpServletRequest request, Integer secId) {
		WxSection section = wxSectionService.getWxSection(secId);
		request.setAttribute("section", section);
		request.setAttribute("secId", secId);
		boolean isManageAuth = checkRole(request, secId);
		request.setAttribute("isBkManageAuth", isManageAuth);
		return "funTime";
	}
	
	*//**
	 * 保存轻松一刻
	 * @param request
	 * @param wxPosts
	 * @param secId
	 * @param files
	 * @param model
	 * @return
	 *//*
	@RequestMapping("saveFunTime")
	@ResponseBody
	public Object saveFunTime(
			HttpServletRequest request,
			WxPosts wxPosts,
			Integer secId,
			@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
			Model model) {
		String result = "";
		wxPosts.setPsStatus(WeiXinUtil.PS_STATUS_OK);
		wxPosts.setPsViews(0);
		wxPosts.setPsIsTop(0);
		wxPosts.setPsCheckStatus(WeiXinUtil.PS_CHECK_STATUS_SHEN_HE_PASS);
		result = wxPostsService.addWxPosts(wxPosts);
		String psId = null;
		String status = "";
		JSONObject object = JSON.parseObject(result);
		if (object != null) {
			psId = object.getString("psId");
			status = object.getString("status");
		 }

		dealUploadFiles(request, psId, 1, "");// 批量处理上传文件。

		model.addAttribute("secId", secId);
		if (status.equals("1")) {
			//更新用户积分
			updateUserScore(request, WeiXinUtil.SC_CODE_POST_FUNTIME,psId);
		}

		return result;
	}
	
	*//**
	 * 维护轻松一刻
	 * @param request
	 * @param wxPosts
	 * @param delFiles
	 * @param secId
	 * @param model
	 * @return
	 *//*
	@RequestMapping("updateFunTime")
	@ResponseBody
	public Object updateFunTime(HttpServletRequest request, WxPosts wxPosts,
			String delFiles, Integer secId, Model model) {
		WxPosts updatePost = wxPostsService.getWxPosts(wxPosts.getPsId());
		String result = "";
		try {
			updatePost.setPsTitle(wxPosts.getPsTitle());
			updatePost.setPsContent(wxPosts.getPsContent());
			result = wxPostsService.updateWxPosts(updatePost);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 处理新上传的文件
		dealUploadFiles(request, wxPosts.getPsId(), 1, "");

		// 处理需要删除的文件
		if (delFiles != null && !delFiles.equals("")) {
			String[] fileId = delFiles.split(",");
			if (fileId != null && fileId.length > 0) {
				for (String obj : fileId) {
					wxFileService.deleteFile(obj);
				}
			}

		}
		model.addAttribute("secId", secId);
		return result;
	}
	
	*//**
	 * 发布轻松一刻主页
	 * @param request
	 * @param secId
	 * @param psId
	 * @param replyType
	 * @param topPsId
	 * @return
	 *//*
	@RequestMapping("funTimeAdd")
	public String funTimeAdd(HttpServletRequest request, Integer secId,
			String psId, Integer replyType, String topPsId) {
		WxSection section = wxSectionService.getWxSection(secId);
		request.setAttribute("section", section);

		WxPosts wxPosts = wxPostsService.getWxPosts(psId);
		request.setAttribute("wxPosts", wxPosts);

		request.setAttribute("replyType", 0);
		request.setAttribute("topPsId", "-1");

		return "funTimeAdd";
	}
	
	*//**
	 * 浏览轻松一刻详细页面
	 * @param request
	 * @param psId
	 * @param secId
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = { "/funTimeDetail" })
	public String funTimeDetail(HttpServletRequest request, String psId,
			Integer secId, Model model) {
		initJssdkData(request);
		WxPosts posts = wxPostsService.getWxPosts(psId);
		posts.setPsContent(replaceLine(replaceUrl(posts.getPsContent())));
		String url = posts.getPsField4();
		if(url != null && url.indexOf("http://") <0){
			posts.setPsField4("http://"+url);
		}
		model.addAttribute("wxPosts", posts);

		WxSection section = wxSectionService.getWxSection(secId);
		model.addAttribute("section", section);
		
		WxFile wxFile = new WxFile();
	    wxFile.setFilePsId(psId);
	    wxFile.setFileStatus(WeiXinUtil.FILE_STATUS_OK);
	    List<WxFile> files = wxFileService.getFileList(wxFile);
	    request.setAttribute("files", files);
	    
	    synchronized (this)
	      {
	        wxPostsService.clicks(psId);
	      }

		return "funTimeDetail";

	}*/
	
	
	private static String getFormatDate(int type) {
		String formatDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (type == 1) {
			// 获取上月的第一天
			Calendar cal_1 = Calendar.getInstance();// 获取当前日期
			cal_1.add(Calendar.MONTH, -1);
			cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			formatDate = format.format(cal_1.getTime());
		} else if (type == 2) {
			// 获取上月的最后一天
			Calendar cale = Calendar.getInstance();
			cale.set(Calendar.DAY_OF_MONTH, 0);
			formatDate = format.format(cale.getTime());
		} else if (type == 3) {
			// 获取当前月第一天：
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
			formatDate = format.format(c.getTime());
		} else if (type == 4) {
			// 获取当前月最后一天
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH,
					ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			formatDate = format.format(ca.getTime());
		}else if (type == 5) {
			// 获取当天日期
			Calendar ca = Calendar.getInstance();
			formatDate = format.format(ca.getTime());
		}
		return formatDate;
	}
}
