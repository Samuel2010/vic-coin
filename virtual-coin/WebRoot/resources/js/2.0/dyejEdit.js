var nowChoice ;
var channel = "0";

$(function(){
	channel = $("#channel").val();
    query('01');//第一次加载
});

function changgeVerify(obj){
	changgeClass(obj,'am-g','');
	var title = $(obj).text();
	$('#psTitle').val(title);
	var psId = $(obj).attr("value");
	$('#psId').val(psId);
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
                $("#psContent").val(data.wxPosts.psContent);
                $('#psTitle').val(data.wxPosts.psTitle);
            }
        },
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");
        }
    });
}


function updateQuest(){
	var url = "updateQuest.do";
    if(nowChoice == null){
    	url = "addNote.do";
    }
    var formData = new FormData($("#addNoteForm")[0]);
    var rs = $("#addNoteForm").validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的内容！");
		return;
	}
	saveLoading("saveBtn");
    $.ajax({
        type:'post',  
        url : url, 
        data: formData,  
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:'json',
        success : function(data) {
            if(data.status == "1")  
            {
                showMsg("操作成功。", 'refresh');
                
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

//切换样式
function changgeClass(obj,classIn,classOut){
	$("."+classIn).each(function(){
		$(this).attr("class",classOut);
	})
	$(obj).attr('class',classIn);
}


function deleteNote(){
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
                showMsg("操作成功！");
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
	var psIsTop = "1";
	if(channel == "1"){
		psIsTop = "0";
	}
    $.ajax({
        type:'get',  
        url : "queryPosts.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            secId : $("#secId").val(),
            psIsTop : psIsTop
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
                if(data.result.length==0)  
                {
                     $("#pageNo").val(parseInt($("#pageNo").val())-1);
                     return "";
                }
                for(var i=0;i<data.result.length;i++)  
                {
                  newDate.setTime(data.result[i].psCreateTime);
                  content=content
                  +'<li class="am-g am-list-item-dated" id="ps'+data.result[i].psId+'"><a onclick="changgeVerify(this)" value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a></li>';
                }
                if(type=="00")  
                {
                    $("#wrapper").append(content);
                }else{
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
    clearInput();
}

function clearInput(){
    nowChoice = null;
    $('#psContent').val("");
    $('#psTitle').val(""); 
    $("#userInfo").empty();
    $('#psId').val("");
}
