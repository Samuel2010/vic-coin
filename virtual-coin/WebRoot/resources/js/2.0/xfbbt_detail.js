var isNew = true;
var jsonStr = {};
var isDyEj = 0;
var replyManage = "0";
//var pageCount = 1;

$(function(){
    var title = $("#psTitle").val();
	if(title!=null&&title!=''&&title!='不超过20个字'&&title!=undefined){
		//$('#psTitle').attr("disabled", true);  
		$('#psTitle').attr("readonly", true);
		isNew = false;
	}
	//党员E家入口，区分管理员和志愿者标签
	var dyej = $("#isDyEj").val();
	if(dyej!=null && dyej!='' && dyej!=undefined){
		isDyEj = 1;
	}
	
	//回帖管理入口，区分回帖置顶，扣分功能
	var replyManageFlag = $("#replyManage").val();
	if(replyManageFlag!=null && replyManageFlag!='' && replyManageFlag!=undefined){
		replyManage = replyManageFlag;
	}
	$("#pageNo").val('1');
	query('01');//第一次加载 
	$(window).scroll(loadDataPage);
});

function focusTitle(){
    if(isNew){
        $("#psTitle").val('');
    }
}

function query(type)  
{
    //alert(type);  
    $.ajax({
        type:'get',
        url : "querySubPosts.do", 
        async:false,
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            psId : $("#psId").val()
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data==null)  
            {
                $("#pageNo").val(parseInt($("#pageNo").val())-1);     
            } else {
			    pageCount = data.pageCount;
			    refreshPageBtn(pageCount);
                if(data.result.length==0)
                {
                     $("#pageNo").val("1");
                     return "";
                }
                initDataList(data.result, type);
            }
        },
        error : function(){
            $("#pageNo").val(parseInt($("#pageNo").val())-1);
            showMsg("查询数据出错啦，请刷新再试");
        }
    });  
}

function initDataList(data, type){
    var content="";
    var newDate = new Date();
    for(var i=0;i<data.length;i++)  
    {
      newDate.setTime(data[i].psCreateTime);
      content=content
      +'<li class="am-comment">'
      +'<article class="am-comment">'
      +'<span name="my-popover" id="my-popover-'+data[i].psUserId+'-'+data[i].psId+'" onclick="getUserInfo(\''+data[i].psUserId+'\',\''+data[i].psId+'\')" isload="false">';
      if(data[i].userAvatar!=null){
          content=content+'  <img src="'+data[i].userAvatar+'" class="am-comment-avatar" width="48" height="48"/>';
      }else{
          content=content+'  <img src="images/2.0/titleMan.png" class="am-comment-avatar" width="48" height="48"/>';
      }
      content=content
      +'</span>'
      +'<div class="am-comment-main">'
      +'  <header class="am-comment-hd">'
      +'    <div class="am-comment-meta">'
      +'      <a class="am-comment-author">'+data[i].psUserName+'</a>'
      +'      发布于 <time datetime="'+newDate.timeago()+'" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>'
      +'    </div>';
      if(isDyEj == 1){
    	  var isAdmin = data[i].psField3;
    	  if(isAdmin =="1"){
    		  content=content+'<span class="am-badge am-ding am-badge-admin">管理员';
    	  }else{
    		  content=content+'<span class="am-badge am-ding am-badge-success">志愿者';
    	  }
    	 
      }else{
    	  if(data[i].psIsTop == "1"){
    		  content=content 
        	  +'<span class="am-badge am-ding am-badge-danger" id="postTopTips_'+data[i].psId+'">顶</span>&nbsp;';
    	  }else{
    		  content=content 
        	  +'<span class="am-badge am-ding am-badge-danger am-hide" id="postTopTips_'+data[i].psId+'">顶</span>&nbsp;';
    	  }
    	  content=content 
    	  +'<span class="am-badge am-ding am-badge-success">'
    	  +((parseInt($("#pageNo").val())-1)*parseInt($("#pageSize").val())+i+2) + '楼';
      }
     
      content=content
      +'</span>'
      +'  </header>'
      +'  <div class="am-comment-bd">';
      var subPostsList = data[i].subPostList;
      if(subPostsList != null && subPostsList.length >0){
    	  var subContent = "";
    	  var insertFlag = false;
          subContent=subContent+'<blockquote class="am-padding-top-0 am-padding-left-0 am-padding-bottom-0">';
          
    	  for(var k=0;k<subPostsList.length;k++)  {
    		  newDate.setTime(subPostsList[k].psCreateTime);
    		  var childContent = "";
    		  var hideContent = "";
    		  var subpubTime =  '<time datetime="'+newDate.timeago()+'" class="am-padding-left-xs" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>';
    		  childContent += '<header class="am-comment-hd"><div class="am-comment-meta am-fl" style="white-space:normal;">'+subPostsList[k].psUserName
    		  +subpubTime;
    		  if(subPostsList.length >1){
    			  childContent += '<span class="am-fr">'+(k+1)+'楼</span>';
    		  }
    		 
    		  childContent += '<br><br><span style="line-height:1.4">'+subPostsList[k].psContent+'<span></div></header>';
    		  
    		  
    		  if(subPostsList.length > 2 && k>=1 && k<subPostsList.length-1){
    			  hideContent += '<div class="am-hide" data-panel="moreReview_'+data[i].psId+'">'+childContent+'</div>';
    			  if(!insertFlag){
    				  subContent  +='<header class="am-comment-hd am-comment-meta" value="'+data[i].psId+'" onclick="showMoreReview(this)">还有'+(subPostsList.length-2)+'条评论</header>';
    				  insertFlag = true;
    			  }
    			  subContent  +=hideContent;
    		  }else{
    			  subContent += childContent;
    		  }
          }
          subContent +=  '</blockquote>';
          content +=subContent;
      }
     
      
      if(data[i].psField1 != null && data[i].psField1 != ""){
          content=content +'<img src="images/2.0/emoji/'+data[i].psField1+'" width="16" height="16">';
      }
      content=content
      +'    '+data[i].psContent+'';
      var picContent = "";
      var picFiles = data[i].picFileList;
      if(picFiles != null && picFiles.length >0){
    	  for(var k=0;k<picFiles.length;k++)  {
    		  picContent += '<img class="am-img-reply" src="upload/'+picFiles[k].filePath+'">';
          }
      }
      content=content +picContent
      +' <div class="am-cf am-fr">';
//      +'<button class="am-btn am-btn-primary am-btn-xs am-fr" type="button" onclick="attention(\''+data[i].psId+'\', this)">';
//      if(data[i].atnStatus == null){
//          content=content +'关注</button>';
//      }else{
//          if(data[i].atnStatus==1){
//              content=content +'取消关注</button>';
//          }else{
//              content=content +'关注</button>';
//          }
//      }
      content=content
      +'  </div>'
      
      +'  </div>';
      var secId = $("#secId").val();
      var topPsId = $("#psId").val();
      if(replyManage != "1" && isDyEj != 1){
    	  content = content +'<footer class="am-comment-footer"><div class="am-comment-actions">'
          +'<i class="am-icon-reply" onclick="jump(\'newNote\', \'secId='+secId+'&psId='+data[i].psId+'&replyType=2&topPsId='+topPsId+'\')"><span class="am-margin-left-xs">回复</span></i>';
    	  if(data[i].atnThumbsStatus == "1"){
        	  content += '<i class="am-icon-thumbs-up';
          }else{
        	  content += '<i class="am-icon-thumbs-o-up';
          }
          var thumbsNum = data[i].psField5;
          content += ' am-padding-left-xs" value="'+data[i].psId+'" onclick="changeThumbs(this)"><span class="am-margin-left-xs" id="thumbsSpan_'+data[i].psId+'">赞';
          if(thumbsNum != null && thumbsNum >0){
        	content += '（'+thumbsNum+'）';  
          }
          content += '</span></i></div></footer>';
      }
     
      
      content = content +'  <footer class="am-comment-footer">'
      +'  <div class="am-comment-actions">';
      if(replyManage == "1"){
	      if(data[i].psIsTop == "1"){
	    	  content += '  <button class="am-btn am-btn-primary am-btn-xs" type="button" id="btnIsTop_'+data[i].psId+'"><i class="am-icon-arrow-down" onclick="changeUpNote(\''+data[i].psId+'\',this);" >取消置顶</i></button>';
	      }else{
	    	  content += '  <button class="am-btn am-btn-primary am-btn-xs" type="button" id="btnIsTop_'+data[i].psId+'"><i class="am-icon-arrow-up" onclick="changeUpNote(\''+data[i].psId+'\',this);">置顶</i></button>';
	      }
	      content += '  &nbsp;<button type="button" class="am-btn am-btn-primary am-btn-xs" onclick="changeScore(\''+data[i].psId+'\',this);"><i class="am-icon-paint-brush " >扣分</i></button>';
	      content += '  &nbsp;<button type="button" class="am-btn am-btn-danger am-btn-xs" onclick="deleteNote(\''+data[i].psId+'\');"><i class="am-icon-trash " >&nbsp;删帖</i></button>';
      }
      content=content
      +'  </div>'
      +'  </footer>'
      +'</div>'
      +'</article>'
      +'</li>';
    }
    if(type=="00")
    {
        $("#wrapper").append(content);
    }else{
        $("#wrapper").html(content);
    }
    //$('.am-dropdown').dropdown();
}

function showMoreReview(obj){
	var pid = $(obj).attr("value");
	$(obj).addClass("am-hide");
	$("[data-panel='moreReview_"+pid+"']").removeClass("am-hide");
}
/**
 * 关注
 * @param psId
 */
function attention(psId, obj){
    var atnStatus = 0;
    if($(obj).text() == "关注"){
        atnStatus = 1;
    }
    $.ajax({
        type:'post',
        url : "attention.do",
        async:false,
        data : {
            psId : psId,
            atnStatus : atnStatus
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data!=null)
            {
                showMsg("操作成功!");
                if($(obj).text() == "关注"){
                    $(obj).text("取消关注");
                }else{
                    $(obj).text("关注");
                }
            }
        },
        error : function(){
            showMsg("操作失败，请刷新再试");
        }
    });
}

/**
 * 点赞
 * @param psId
 * @param obj
 */
function changeThumbs(obj){
	//am-icon-thumbs-o-up am-padding-left-xs
    var atnStatus = 0;
    var isThumbs = false;
    var psId = $(obj).attr("value");
    isThumbs = $(obj).hasClass("am-icon-thumbs-o-up");
    
    if(isThumbs){
    	atnThumbsStatus = 1;
    }else{
    	atnThumbsStatus = 0;
    }
    $.ajax({
        type:'post',
        url : "changeThumbs.do",
        async:false,
        data : {
            psId :  psId,
            atnThumbsStatus : atnThumbsStatus
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data!=null)
            {
                //showMsg("操作成功!");
                if(isThumbs){
                	$(obj).removeClass("am-icon-thumbs-o-up");
                	$(obj).addClass("am-icon-thumbs-up");
                }else{
                	$(obj).removeClass("am-icon-thumbs-up");
                	$(obj).addClass("am-icon-thumbs-o-up");
                }
                
                if(parseInt(data)>0){
                	$("#thumbsSpan_"+psId).html("赞（"+data+"）");
                }else{
                	$("#thumbsSpan_"+psId).html("赞");
                }
            }
        },
        error : function(){
            showMsg("操作失败，请刷新再试");
        }
    });
}

function changeUpNote(psId,obj){
	var choiceTitle = $(obj).text();
	var nowTitle,postTopTips;
	var isTop = 0;
	if($(obj).text() == "置顶"){
		isTop = 1;
		nowTitle = '<i class="am-icon-arrow-down" onclick="changeUpNote(\''+psId+'\',this);" >取消置顶</i>';
	}else{
		nowTitle = '<i class="am-icon-arrow-up" onclick="changeUpNote(\''+psId+'\',this);" >置顶</i>';
	}
    
    $.ajax({
        type:'post',  
        url : "updatePost.do", 
        async:false,  
        data : {
            psId : psId,
            psIsTop : isTop,
            reqType : 1
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")  
            {
            	showMsg("操作成功！");
            	$("#btnIsTop_"+psId).html(nowTitle);
            	if(isTop ==1){
            		$("#postTopTips_"+psId).attr("class","am-badge am-ding am-badge-danger am-show");
            	}else{
            		$("#postTopTips_"+psId).attr("class","am-badge am-ding am-badge-danger am-hide");
            	}
            }else{
                showMsg("操作失败！");
            }
        },  
        error : function(){
            showMsg("操作失败！");
        }
    });
}

function changeScore(psId){
	var content = 
		'<li class="am-modal-actions-header">积分扣除</li>'
        +'<li><a onclick="updateUserScore(\'015\',\''+psId+'\')">低俗不文明语言</a></li>'
        +'<li><a onclick="updateUserScore(\'016\',\''+psId+'\')">帖子与主题无关</a></li>'
        +'<li><a onclick="updateUserScore(\'017\',\''+psId+'\')">未对应模块发贴</a></li>'
        +'<li><a onclick="updateUserScore(\'018\',\''+psId+'\')">回贴无针对性</a></li>'
        +'<li><a onclick="updateUserScore(\'021\',\''+psId+'\')">违反法律或公司规定</a></li>';
	$("#scoreContent").html(content);
	$('#scoreSelecter').modal();
}

function updateUserScore(ruleCode,psId){
	 $.ajax({
	        type:'post',  
	        url : "changeUserScore.do", 
	        async:false,  
	        data : {
	            psId : psId,
	            ruleCode : ruleCode
	        },
	        dataType:'json', 
	        cache : false,
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        success : function(data) {
	        	$('#scoreSelecter').modal('close');
	            if(data)  
	            {
	            	showMsg("积分扣除成功！");
	            }else{
	                showMsg("积分扣除失败！");
	            }
	        },  
	        error : function(){
	        	$('#scoreSelecter').modal('close');
	            showMsg("积分扣除失败！");
	        }
	    });
}

function deleteNote(psId){
    
    $.ajax({
        type:'post',  
        url : "deletePost.do", 
        async:false,  
        data : {
            psId : psId
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data == true)  
            {
                showMsg("操作成功！","refresh");
            }else{
                showMsg("操作失败！");
            }
        },  
        error : function(){
            showMsg("操作失败！");
        }
    });
}
