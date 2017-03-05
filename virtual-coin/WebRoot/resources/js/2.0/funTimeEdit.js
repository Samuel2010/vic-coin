var nowChoice ;
var delOtherFileId = '';
var verfDelFlag = false;

$(function(){
    query('01');//第一次加载
});

function changgeVerify(obj){
	changgeClass(obj,'am-g','');
	var title = $(obj).text();
	var psId = $(obj).attr("value");
	$('#verifyTitle').val(title);
	$('#delFiles').val(""); 
	$('#psId').val(psId);
	$('#file-list').html("");
	clearFileInfo();
	initContent(psId);
	nowChoice = obj;
}

function changgeProgress(value){
    query('01');
    var psId = $(nowChoice).attr("value");
    clear(psId);
}

function initContent(psId){
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
                $("#verifyContent").val(data.wxPosts.psContent);
                if(data.files.length > 0){
                	var content ="";
                    for(var i=0;i<data.files.length;i++)
                    {
                    	  content += 
                          '<div class="file_bar" data-old="true" id="delBtn_'+data.files[i].fileId+'" >'
                          +'<div style="padding: 5px;">'
                          +'<span class="file_del" onclick="delOldFiles(\''+data.files[i].fileId+'\');verfDelInfo();" data-index=""'
                          +'title="删除"></span>'
                          +'</div>'
                          +'</div>'
              	          +'<img src="upload/'+data.files[i].filePath+'"  id="ptFile_'+data.files[i].fileId+'"  class="am-img-reply" />';
                    }
                    $("#file-list").html(content);
                    
                }
                if(data.wxUser != null){
                    $("#userInfo").html(data.wxUser.userName + " " + (data.wxUser.unitName != null?data.wxUser.unitName:""));
                }else{
                    $("#userInfo").html(data.wxPosts.psUserName);
                }
            }
        },
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");
        }
    });
}




//切换样式
function changgeClass(obj,classIn,classOut){
	$("."+classIn).each(function(){
		$(this).attr("class",classOut);
	})
	$(obj).attr('class',classIn);
}


function changeFunTime(){
    if(nowChoice == null){
        showMsg("请选择需要操作的记录！");
        return;
    }
    clearInputFile("doc-form-file");
    var formData= new FormData($("#funTimeForm")[0]);
    var rs = $("#funTimeForm").validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的内容！");
		return;
	}
    try{
    	 funUploadFiles(formData);
	}catch(e){
		console.log(e);
	}
	
	saveLoading("saveBtn");
    $.ajax({
        type:'post',  
        url : 'updateFunTime.do', 
        data: formData,  
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:'json',
        success : function(data) {
            if(data.status == "1")  
            {
                showMsg("更新成功。", 'refresh');
                
            }else{
                showMsg("操作失败！错误信息[" + data.errorMsg + "]");
            }
            saveLoadComplete("saveBtn");
        },  
        error : function(e){
            showMsg("操作失败！"+e);
            saveLoadComplete("saveBtn");
        }
    });
}


function deleteFunTime(){
    if(nowChoice == null){
        showMsg("请选择需要操作的记录！");
        return;
    }

    var psId = $(nowChoice).attr("value");
    
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
                clear(psId);
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

function query(type)  
{
    //alert(type);
    $.ajax({
        type:'get',  
        url : "queryPosts.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            secId : $("#psSecId").val(),
            psCheckStatus : $("#psCheckStatus").val()
            //psUserId : $("#psUserId").val(),
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
                      +'<li class="am-g am-list-item-dated" id="ps'+data.result[i].psId+'"><a onclick="changgeVerify(this)" author="'+data.result[i].psField2+'" value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a></li>';
                    }
                    $("#wrapper").append(content);
                }else{
                    for(var i=0;i<data.result.length;i++)  
                    {
                      newDate.setTime(data.result[i].psCreateTime);
                      content=content
                      +'<li class="am-g am-list-item-dated" id="ps'+data.result[i].psId+'"><a onclick="changgeVerify(this)" author="'+data.result[i].psField2+'"  value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a></li>';
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

function clear(psId){
    $("#ps"+psId).remove();
    nowChoice = null;
    verfDelFlag = false;
    $('#verifyContent').val("");
    $('#verifyTitle').val(""); 
    $('#delFiles').val(""); 
    $("#userInfo").empty();
}

//更新数据时，因为有两个文件表单，在校验时需要记录一下图片是否删除
function verfDelInfo(){
	verfDelFlag = true;
}


