$(function(){
	query('01');//第一次加载
});



/**
 * 新增建议
 */
function saveAdvices(){
	var $form = $('#advicesForm');
	var formData = $form.serialize();
	var psContent = $("#psContent").val();
	var psSecId = $("#psSecId").val();
	var psTitle = $("#psTitle").val();
	
	var rs = $form.validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的标题和建议！");
		return;
	}
	saveLoading("saveBtn");
    $.ajax({
        type:'post',
        url : "my/addAdvices.do",
        async:false,
        data :formData,
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")
            {
                showMsg("操作成功！","refresh");
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
 * 选择反馈建议，加载信息
 */
function changeAdvices(obj){
	changeClass(obj,'am-g','');
	var psId = $(obj).attr("value");
	$("#changeId").val(psId);
	$.ajax({
        type:'post',
        url : "getPost.do",
        async:false,
        data : {
            psId : psId
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data!=null)
            {
                $("#psTitleView").val(data.wxPosts.psTitle);
                $("#psContentView").val(data.wxPosts.psContent);
                console.log(data.wxUser.userName+","+data.wxPosts.psUserName);
                if(data.wxUser != null){
                    $("#psUserView").val(data.wxUser.userName + " " + data.wxUser.unitName);
                }else{
                    $("#psUserView").val(data.wxPosts.psUserName);
                }
            }
        },
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");
        }
    });
}

/**
 * 删除建议
 */
function deleteAdvices(){
	var changeId  = $("#changeId").val();
    if(changeId == null || changeId ==""){
        showMsg("请选择需要操作的记录！");
        return;
    }
    
    $.ajax({
        type:'post',  
        url : "deletePost.do", 
        async:false,  
        data : {
        	psId : changeId
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data == true)  
            {
            	$('#changeId').val(""); 
            	showMsg("操作成功！","refresh");
            	clear(changeId);
            }else{
                showMsg("操作失败！");
            }
        },  
        error : function(){
            showMsg("操作失败！");
        }
    });
}

function changeClass(obj,classIn,classOut){
	$("."+classIn).each(function(){
		$(this).attr("class",classOut);
	});
	$(obj).attr('class',classIn);
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
            secId : $("#psSecId").val(),
            parentId : $("#psParentId").val()
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
                    	  newDate.setTime(data.result[i].psCreateTime);
                    	  content=content
                          +'<li class="am-g am-list-item-dated" id="ps_'+data.result[i].psId+'"><a onclick="changeAdvices(this)" value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a>'
                          + '<span class="am-list-date">'+newDate.format('yyyy-MM-dd')+'</span></li>';
                     
                    }
                    $("#wrapper").append(content);
                }else{
                    for(var i=0;i<data.result.length;i++)  
                    {
                    	 newDate.setTime(data.result[i].psCreateTime);
                    	  content=content
                          +'<li class="am-g am-list-item-dated"   id="ps_'+data.result[i].psId+'"><a onclick="changeAdvices(this)" value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a>'
                          + '<span class="am-list-date">'+newDate.format('yyyy-MM-dd')+'</span></li>';
                      
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
    $("#ps_"+psId).remove();
    $('#psUserView').val("");
    $('#psTitleView').val("");
    $('#psContentView').val(""); 
    $("#changeId").val("");
}
