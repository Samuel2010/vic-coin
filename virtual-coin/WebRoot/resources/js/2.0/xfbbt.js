var replyManage ="0";
var sortType = 0;
function autoScroll(obj){  
	$(obj).find("ul").animate({  
		marginTop : "-25px"  
	},500,function(){  
		$(this).css({marginTop : "0px"}).find("li:first").appendTo(this);  
	});
}  
$(function(){
	//回帖管理入口，区分回帖置顶，扣分功能
	var replyManageFlag = $("#replyManage").val();
	if(replyManageFlag != null && replyManageFlag != undefined){
		replyManage = replyManageFlag;
	}
	$("#pageNo").val("1");
    query('01');//第一次加载  
    setInterval('autoScroll("#noticePanel")',3000);
    
    $(window).scroll(loadDataPage);
});

function searchTieZi(){
    $("#pageNo").val("1");
    loadingEnd = false;//恢复滚动记录，common.js
    query("01");
}

function reSearchBySort(type){
    $("#pageNo").val("1");
    loadingEnd = false;//恢复滚动记录，common.js
    sortType = type;
    query("01");
}

function query(type)
{
    var searchValue = "";
    if($("#searchValue").val()!= '本版搜索'){
        searchValue = $("#searchValue").val();
    }
    //alert(type);
    
    var newDate = new Date();
    $.ajax({
        type:'post', 
        url : "queryPosts.do?time="+newDate.getTime(), 
        async:false,
        data : {
            pageNo : $("#pageNo").val(),
            secId : $("#secId").val(),
            psParentId : "-1",
            psCheckStatus : 2,
            sortType : sortType,
            searchValue : searchValue
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data){
            if(data==null)
            {
                $("#pageNo").val(parseInt($("#pageNo").val())-1);
            } else { 
                pageCount = data.pageCount;
                refreshPageBtn(pageCount);
                if(data.result.length==0)
                {
                     $("#pageNo").val(parseInt($("#pageNo").val())-1);
                     return "";
                }
                initDataListNew(data.result, type);
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
      +'<div class="am-comment-main" onclick="jump(\'detailPage\',\'secId='+$("#secId").val()+'&psId='+data[i].psId+'&replyManage='+replyManage+'\')">'
      +'  <header class="am-comment-hd">'
      +'    <div class="am-comment-meta">'
      +'      <a href="#link-to-user" class="am-comment-author">'+data[i].psUserName+'</a>'
      +'      发布于 <time datetime="'+newDate.timeago()+'" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>'
      +'    </div>';
      if(data[i].psIsTop==1){
          content=content
          +'<span class="am-badge am-ding am-badge-danger">顶</span>';
      }
      content=content
      +'  </header>'
      +'  <div class="am-comment-bd">';
      
      if(data[i].psField1 != null && data[i].psField1 != ""){
          content=content +'<img src="images/2.0/emoji/'+data[i].psField1+'" width="16" height="16">';
      }
      
      content=content
      +'    '+data[i].psTitle+''
      +'  </div>'
      +'  <footer class="am-comment-footer">'
      +'  <div class="am-comment-actions">'
      +'  <span><i class="am-icon-thumbs-up">'+data[i].psViews+'</i></span>'
      +'  <span><i class="am-icon-commenting">'+data[i].psReply+'</i></span>'
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
}

function initDataListNew(data, type){
	var content="";
	var newDate = new Date();
	for(var i=0;i<data.length;i++)
	{
		
		 newDate.setTime(data[i].psCreateTime);
		 content += '<li>'; 
		/* if(i == 0){
			 content +='style="border-top-style:none;">';
		 }else{
			 content +='>';
		 }*/
		  content+='<span name="my-popover" id="my-popover-'+data[i].psUserId+'-'+data[i].psId+'" onclick="getUserInfo(\''+data[i].psUserId+'\',\''+data[i].psId+'\')" isload="false">';
	      if(data[i].userAvatar!=null){
	          content += '  <img src="'+data[i].userAvatar+'" class="am-comment-avatar" width="48" height="48"/>';
	      }else{
	          content +='  <img src="images/2.0/titleMan.png" class="am-comment-avatar" width="48" height="48"/>';
	      }
	      content += '</span>'
		 +'<span class="am-padding-left-sm am-text-truncate am-list-main-show" onclick="jump(\'detailPage\',\'secId='+$("#secId").val()+'&psId='+data[i].psId+'&replyManage='+replyManage+'\')">'+data[i].psTitle+'</span>';
		 if(data[i].psIsTop==1){
	          content += '<span class="am-badge am-badge-danger am-list-icon">顶</span>';
	      }
		 content +='  <footer class="am-comment-footer am-padding-top-xs">'
		 +'  <div class="am-comment-actions  am-padding-left-lg">'
		 +'   <time datetime="'+newDate.timeago()+'" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>'
		 +'  </span>'
		 +'  <span class="am-fr "><span><i class="am-icon-eye">'+data[i].psViews+'</i></span>'
		 +'  <span><i class="am-icon-commenting">'+data[i].psReply+'</i></span></span>'
		 +'  </div>'
		 +'  </footer>'
	     +'</li>';
		 
	}
	
	if(type=="00")
	{
		$("#wrapper").append(content);
	}else{
		$("#wrapper").html(content);
	}
}

function replaceTitle(content){
	var len = getByteLen(content);
	var reg = /[\u4e00-\u9fa5]/g;
	console.log(content+"----"+len);
	if(len/2 >=15){
		return content.substr(0,16)+"...";
	}else{
		return content;
	}
}

function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
         var a = val.charAt(i);
         if (a.match(/[^\x00-\xff]/ig) != null) 
        {
            len += 2;
        }
        else
        {
            len += 1;
        }
    }
    return len;
}
