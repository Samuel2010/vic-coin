//var pageCount = 1;
var editFlag = 0;
var listFlag = 0;//是否从列表进入，区分平台公告修改和查看
$(function(){
	editFlag = $("#editFlag").val();
	listFlag = $("#listFlag").val();
	if(editFlag != null && (editFlag=="1" || editFlag =="2")){
		query('01');//第一次加载
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
 * 发布公告
 */
function saveNotice(){
	var $form = $('#noticeForm');
	var formData = $form.serialize();
	var nContent = $("#nContent").val();
	var nSecId = $("#nSecId").val();
	var nTitle = $("#nTitle").val();
	
	var rs = $form.validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的公告标题和内容！");
		return;
	}
 
	saveLoading("saveBtn");
    $.ajax({
        type:'post',
        url : "saveNotice.do",
        async:false,
        data :formData,
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")
            {
                if(editFlag == "0" || editFlag == "1"){
                	 showMsg("操作成功！","","bkgly.do?secId="+nSecId);
            	}else{
            		showMsg("操作成功！","","sysNotice.do?secId="+nSecId);
            	}
                //location.href=history.go(-1);
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

/**
 * 选择公告，加载信息
 * @param nId
 */
function changeNotice(nId){
	if(listFlag !=1){
		$.ajax({
	        type:'get',  
	        url : "queryNotice.do", 
	        async:false,  
	        data : {
	            nId : nId
	        },
	        dataType:'json', 
	        cache : false,  
	        success : function(data) {
	            loading=true;
	            if(data!=null)  
	            {
	            	$("#changenId").val(nId);
	            	$("#nTitle").val(data.wxNotice.nTitle);
	            	$("#nContent").val(data.wxNotice.nContent);
	            }
	        },  
	        error : function(){
	            loading=true;
	            showMsg("查询数据出错啦，请刷新再试");
	        }
	    });
	}else{
		var secId = $("#secId").val();
		jump("queryNoticeDetail.do","nId="+nId+"&secId="+secId);
	}
	 
}

/**
 * 删除公告
 */
function deleteNotice(){
	var changenId  = $("#changenId").val();
	var secId =  $("#secId").val();
    if(changenId == null || changenId ==""){
        showMsg("请选择需要操作的公告！");
        return;
    }
    
    $.ajax({
        type:'post',  
        url : "deleteNotice.do", 
        async:false,  
        data : {
        	nId : changenId
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data == true)  
            {
            	$('#changenId').val(""); 
            	if(editFlag == "1"){
            		showMsg("操作成功！","","noticeEdit.do?secId="+secId);
            	}else{
            		showMsg("操作成功！","","sysNoticeEdit.do?secId="+secId);
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

/**
 * 更新公告
 */
function updateNotice(){
	var changenId  = $("#changenId").val();
	var secId =  $("#secId").val();
	var nTitle = $("#nTitle").val();
	var nContent = $("#nContent").val();
    if(changenId == null || changenId ==""){
        showMsg("请选择需要操作的公告！");
        return;
    }
    saveLoading("saveBtn");
    $.ajax({
        type:'post',  
        url : "updateNotice.do", 
        async:false,  
        data : {
        	nId : changenId,
        	nTitle : nTitle,
        	nContent : nContent
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == 1)  
            {
            	$('#changenId').val(""); 
            	if(editFlag == "1"){
            		showMsg("操作成功！","","noticeEdit.do?secId="+secId);
            	}else{
            		showMsg("操作成功！","","sysNoticeEdit.do?secId="+secId);
            	}
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
        url : "queryNoticePage.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            secId : $("#secId").val(),
            psUserId : $("#psUserId").val(),
            parentId : $("#parentId").val()
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
                var newDate = new Date();
                if(type=="00")
                {
                    if(data.result.length==0)  
                    {
                         $("#pageNo").val(parseInt($("#pageNo").val())-1);
                         return "";
                    }
                    for(var i=0;i<data.result.length;i++)  
                    {
                      newDate.setTime(data.result[i].nCreateTime);
                      if(editFlag == 1){
                    	  content=content
                          +'<li class="am-g am-list-item-dated" id="n_'+data.result[i].nId+'"><a onclick="changeNotice(\''+data.result[i].nId+'\')" >'+data.result[i].nTitle+'</a></li>';
                      }else if(editFlag == 2){
                    	  content += "<li class=\"am-g am-list-item-desced\"";
                          if(i == 0){
                        	  content += " style=\"border-top: none;\">";
                          }else{
                        	  content += "\">";
                          }
                          content += "<a onclick=\"changeNotice(\'"+data.result[i].nId+"\')\" class=\"am-list-item-hd \">"+data.result[i].nTitle+"</a>"
                          +"<div class=\"am-list-item-text\">"+newDate.format('yyyy-MM-dd hh:mi:ss')+"</div></li>" ;
                      }
                     
                    }
                    $("#wrapper").append(content);
                }else{
                    for(var i=0;i<data.result.length;i++)  
                    {
                      newDate.setTime(data.result[i].nCreateTime);
                      if(editFlag == 1){
                    	  content=content
                          +'<li class="am-g am-list-item-dated" id="n_'+data.result[i].nId+'"><a onclick="changeNotice(\''+data.result[i].nId+'\')" >'+data.result[i].nTitle+'</a></li>';
                      }else if(editFlag == 2){
                    	  content += "<li class=\"am-g am-list-item-desced\"";
                          if(i == 0){
                        	  content += " style=\"border-top: none;\">";
                          }else{
                        	  content += "\">";
                          }
                          content += "<a onclick=\"changeNotice(\'"+data.result[i].nId+"\')\" class=\"am-list-item-hd \">"+data.result[i].nTitle+"</a>"
                          +"<div class=\"am-list-item-text\">"+newDate.format('yyyy-MM-dd hh:mm:ss')+"</div></li>" ;
                      }
                      
                    }
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

function clear(nId){
    $("#n_"+psId).remove();
    $('#nTitle').val("");
    $('#nContent').val(""); 
    $("#changenId").val("");
}

/**
 * 显示审核管理细则
 */
function showNoticeGLGZ(){
    showMsg('<span>1、遵守中华人民共和国宪法、法律和行政法规的规定，不得利用本论坛进行任何违法犯罪行为；<br/>'
            + '2、遵守论坛规则，遵从论坛管理人员的版务管理；<br/>'
            + '3、礼貌、和谐、有序地与他人交流；<br/>'
            + '4、禁止恶意诋毁和攻击论坛、论坛管理人员的正常工作；<br/>'
            + '5、不得利用论坛资源进行任何包括但不限于宣传网络赚钱、变相传销等涉及经济内容的信息；<br/>'
            + '6、不得在未经许可的情况下将论坛资源用于商业用途；<br/>'
            + '7、不得在未经许可的情况下在论坛中发布商业广告及类似信息；</span>');
}