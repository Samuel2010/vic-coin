//var pageCount = 1;
var editFlag = 0;
var listFlag = 0;//是否从列表进入，区分修改和查看
$(function(){
	editFlag = $("#editFlag").val();
	listFlag = $("#listFlag").val();
	if(editFlag != null && (editFlag=="1" || editFlag =="2")){
		query('01');//第一次加载
		$(window).scroll(loadDataPage);
	}
	$("#shareEventObj").removeClass("am-active");
	$("#readEventObj").removeClass("am-active");
	if(editFlag == 1){
		$("#shareEventObj").addClass("am-active");
	}else if(editFlag == 2){
		$("#readEventObj").addClass("am-active");
	}
	 
});


//切换样式
function changgeClass(obj,classIn,classOut){
	$("."+classIn).each(function(){
		$(this).attr("class",classOut);
	})
	$(obj).attr('class',classIn);
}

/**
 * 发布活动
 */
function saveEvent(){
	var $form = $('#noticeForm');
	var formData = $form.serialize();
	var nContent = $("#psContent").val();
	var nSecId = $("#psSecId").val();
	var nTitle = $("#psTitle").val();
	
	var rs = $form.validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的标题和内容！");
		return;
	}
 
	saveLoading("saveBtn");
    $.ajax({
        type:'post',
        url : "addNote.do",
        async:false,
        data :formData,
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")
            {
            	showMsg("操作成功！","","sysEvent.do?secId="+nSecId);
                //location.href=history.go(-1);
            }else{
            	showMsg("操作失败！错误信息[" + data.errorMsg + "]");
            }
            saveLoadComplete("saveBtn");
        },  
        error : function(){
            showMsg("操作失败！");
            saveLoadComplete("saveBtn");
        }
    });
}

/**
 * 选择活动，加载信息
 * @param nId
 */
function changeEvent(psId){
	if(listFlag !=1){
		$.ajax({
	        type:'get',  
	        url : "getPost.do", 
	        async:false,  
	        data : {
	            psId : psId
	        },
	        dataType:'json', 
	        cache : false,  
	        success : function(data) {
	            loading=true;
	            if(data!=null)  
	            {
	            	$("#changenId").val(psId);
	            	$("#psTitle").val(data.wxPosts.psTitle);
	            	$("#psContent").val(data.wxPosts.psContent);
	            	$("#psField4").val(data.wxPosts.psField4);
	            }
	        },  
	        error : function(){
	            loading=true;
	            showMsg("查询数据出错啦，请刷新再试");
	        }
	    });
	}else{
		
	}
	 
}

/**
 * 删除活动
 */
function deleteEvent(){
	var changenId  = $("#changenId").val();
	var secId =  $("#secId").val();
    if(changenId == null || changenId ==""){
        showMsg("请选择需要操作的记录！");
        return;
    }
    
    $.ajax({
        type:'post',  
        url : "deletePost.do", 
        async:false,  
        data : {
        	psId : changenId
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data == true)  
            {
            	$('#changenId').val(""); 
            	showMsg("操作成功！","","sysEventEdit.do?secId="+secId);
            }else{
                showMsg("操作失败！");
            }
        },  
        error : function(){
            showMsg("操作失败！");
        }
    });
}

/**
 * 更新活动
 */
function updateEvent(){
	var changenId  = $("#changenId").val();
	var secId =  $("#secId").val();
	var psField4 = $("#psField4").val();
	var psContent = $("#psContent").val();
	var psTitle = $("#psTitle").val();
    if(changenId == null || changenId ==""){
        showMsg("请选择需要操作的记录！");
        return;
    }
    
    saveLoading("saveBtn");
    $.ajax({
        type:'post',  
        url : "updateEvent.do", 
        async:false,  
        data : {
        	psId : changenId,
        	psField4 : psField4,
        	psContent : psContent,
        	psTitle : psTitle
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == 1)  
            {
            	$('#changenId').val(""); 
            	showMsg("操作成功！","","sysEventEdit.do?secId="+secId);
                
            }else{
                showMsg("操作失败！");
            }
            saveLoadComplete("saveBtn");
        },  
        error : function(){
            showMsg("操作失败！");
            saveLoadComplete("saveBtn");
        }
    });
}
function query(type)  
{
    $.ajax({
        type:'get',  
        url : "queryPosts.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            secId : $("#secId").val(),
            psUserId : $("#psUserId").val(),
            parentId : $("#parentId").val(),
            sysEventFlag : 1
        },
        dataType:'json', 
        cache : false,  
        success : function(data) {
            loading=true;
            if(data==null)  
            {
                $("#pageNo").val(parseInt($("#pageNo").val())-1); 
            } else {     
                var content="";
                pageCount = data.pageCount;
                refreshPageBtn(pageCount);
                if(type=="00")
                {
                    if(data.result.length==0)  
                    {
                         $("#pageNo").val(parseInt($("#pageNo").val())-1);
                         return "";
                    }
                    content = initListContent(data,content);
                    $("#wrapper").append(content);
                }else{
                	content = initListContent(data,content);
                    $("#wrapper").html(content);
                }
            }
        },  
        error : function(){
            loading=true;
            $("#pageNo").val(parseInt($("#pageNo").val())-1); 
            showMsg("查询数据出错啦，请刷新再试");
        }
    });
}

function initListContent(data,content){
	 var newDate = new Date();
	 var shareScore = $("#shareScore").val();
	 var readScore = $("#readScore").val();
	 for(var i=0;i<data.result.length;i++)  
     {
       newDate.setTime(data.result[i].psCreateTime);
       var logId = data.result[i].logId;
 	   content += "<li class=\"am-g am-list-item-desced\"";
       if(i == 0){
     	  content += " style=\"border-top: none;\">";
       }else{
     	  content += "\">";
       }
       if(listFlag != undefined && listFlag == 1){
    	   content += '<div class="am-u-sm-9 am-list-main"><a onclick="jumpToPage(\''+data.result[i].psField4+'\',\''+data.result[i].psId+'\')" class=\"am-list-item-hd \">'+data.result[i].psTitle+'</a>';
    	  
    	   content += "<div class=\"am-list-item-text\">"+newDate.format('yyyy-MM-dd hh:mm:ss')+"</div></div>" ;
    	   if(logId != null && logId !=""){
    		   if(editFlag == "1"){
            	   content += ' <div class="am-u-sm-3 am-list-thumb am-div-bg-yel am-padding-right-0"><span class="">已分享(积分+'+shareScore+')</span></div>';
               }else{
            	   content += ' <div class="am-u-sm-3 am-list-thumb am-div-bg-yel"><span class="">已阅（积分+'+readScore+'）</span></div>';
               }
    	   }else{
    		   if(editFlag == "1"){
            	   content += ' <div class="am-u-sm-3 am-list-thumb am-div-bg-yel"><span class="">分享积分+'+shareScore+'</span></div>';
               }else{
            	   content += ' <div class="am-u-sm-3 am-list-thumb am-div-bg-yel"><span class="">阅读积分+'+readScore+'</span></div>';
               }
    	   }
           
       
       }else{
    	   content+= "<a onclick=\"changeEvent(\'"+data.result[i].psId+"\')\" class=\"am-list-item-hd \">"+data.result[i].psTitle+"</a>"
    	   +"<div class=\"am-list-item-text\">"+newDate.format('yyyy-MM-dd hh:mm:ss')+"</div>" ;
       }
       content += '</li>';
      
     }
	 return content;
}

function clear(nId){
    $("#n_"+psId).remove();
    $('#nTitle').val("");
    $('#nContent').val(""); 
    $("#changenId").val("");
}

function changeProgess(value,type){
	editFlag = type;
	$("#editFlag").val(type);
	$('#secId').val(value); 
	$('#pageNo').val("1"); 
	$("#wrapper").html("");
    query("01");
}

function jumpToPage(url,psId){
	//updateScoreByEvent
	var secId = $('#secId').val();
	if(editFlag == 1){
		jump("sysEventDetail","psId="+psId+"&secId="+secId);
	}else{
		updateScoreByEvent(url,psId,2);
	}
}

function updateScoreByEvent(url,psId,browseType){
	 $.ajax({
	        type:'post',  
	        url : "updateScoreByEvent.do", 
	        async:false,  
	        data : {
	        	psId : psId,
	        	browseType : browseType
	        },
	        dataType:'json', 
	        cache : false,
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        success : function(data) {
	        	console.log(data);
	            if(data == true)  
	            {
	            	console.log("更新积分成功！");
	            }else{
	            	console.log("更新积分失败！");
	            }
	            window.location.href = url;
	        },  
	        error : function(e){
	        	console.log(e);
	            showMsg("出错啦！");
	        }
	    });
}
