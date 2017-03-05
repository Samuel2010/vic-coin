var replyManage ="0";
var sortType = 0;
$(function(){
	var replyManageFlag = $("#replyManage").val();
	if(replyManageFlag != null && replyManageFlag != undefined){
		replyManage = replyManageFlag;
	}
	$("#pageNo").val("1");
    query('01');//第一次加载  
    
    $(window).scroll(loadDataPage);
});

function searchTieZi(){
    $("#pageNo").val("1");
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
            qryFileMode : 1,
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
		 content += '<section class="am-panel am-panel-default am-margin-sm">';
		 content+=' <header class="am-panel-hd"><h3 class="am-panel-title" style="text-align: left">'+data[i].psTitle+'</h3></header>';
			    
		 content +=' <main class="am-panel-bd"><p class="paragraph-default-p am-padding-left-sm am-margin-bottom-0 am-margin-top-0">'+data[i].psContent+'</p>';
		 var picFileList = data[i].picFileList;
		 if(picFileList != null && picFileList.length>0){
			 for(var k=0;k<picFileList.length;k++){
				 content+='<div class="am-paragraph-default"><img src="upload/'+picFileList[k].filePath+'" ></div>';
			 }
		 }
		 content +='  <footer class="am-comment-footer am-padding-top-xs">'
		 +'  <div class="am-comment-actions ">'
		 +'   <time datetime="'+newDate.timeago()+'" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>'
		 +'  </span>';
		 content += '<span class="am-fr ">';
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
         content += '</span></i></span>';
         //content +='  <span class="am-fr "><span><i class="am-icon-eye">'+data[i].psViews+'</i></span>'
         content +='  </div>'
		 +'  </footer> '
	     +'</main></section>';
		 
	}
	
	if(type=="00")
	{
		$("#wrapper").append(content);
	}else{
		$("#wrapper").html(content);
	}
}

/**
 * 提交帖子
 * @param action 提交的action
 */
function saveFunTime(action){
    clearInputFile("doc-form-file");
    var formData= new FormData($("#addNoteForm")[0]);
    try{
    	 funUploadFiles(formData);
	}catch(e){
		console.log(e);
	}
   
	saveLoading("saveBtn");
    $.ajax({
        type:'post',  
        url : action, 
        data: formData,  
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:'json',
        success : function(data) {
            if(data.status == "1")  
            {
               showMsg("发布成功。", 'goback');
                
            }else{
                showMsg("操作失败！错误信息[" + data.errorMsg + "]");
                //重新绑定事件。
                $('#doc-form-file').on('change', function() {
                    var fileNames = '';
                    initFiles(this.files);
                  });
            }
            saveLoadComplete("saveBtn");
        },  
        error : function(e){
            showMsg("操作失败！"+e);
            saveLoadComplete("saveBtn");
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

