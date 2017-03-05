var nowChoice ;
//var pageCount = 1;

$(function(){
    query('01');//第一次加载
});

function changgeVerify(obj){
	changgeClass(obj,'am-g','');
	var title = $(obj).text();
	$('#verifyTitle').val(title);
	$("#verifyImage").empty();
	var psId = $(obj).attr("value");
	initContent(psId);
	nowChoice = obj;
	var passType = $("#passType1");
	if(passType != undefined){
		$("#passType1").prop("checked",true);
		$("#passTypeVal").val("2");
		$("#applyAdvice").val("");
		$("#applyAdviceDiv").removeClass("am-block");
		$("#applyAdviceDiv").addClass("am-hide");
	}
	
}

function changgeProgress(value){
    $("#psIsDoing").val(value);
    query('01');
    if(value == '0'){
        $("#isDone").css('display','');
    }else{
        $("#isDone").css('display','none');
    }
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
                var content = "";
                if(data.files.length > 0){
                    for(var i=0;i<data.files.length;i++)
                    {
                      content=content
//                      +'<li><img class="am-thumbnail" src="upload/'+data.files[i].filePath+'" /></li>';
                      +'<img src="upload/'+data.files[i].filePath+'" data-rel="upload/'+data.files[i].filePath+'" />';
                    }
                    $("#verifyImage").append(content);
                }
                if(data.wxUser != null){
                    $("#userInfo").html(data.wxUser.userName + " " + data.wxUser.unitName);
                }else{
                    $("#userInfo").html(data.wxPosts.psUserName);
                }
                
                if($("#btnIsTop") != null){
                    if(data.wxPosts.psIsTop == "0"){
                        $("#btnIsTop").text("置顶");
                    }else{
                        $("#btnIsTop").text("取消置顶");
                    }
                }
            }
        },
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");
        }
    });
}


function doNote(){
    if(nowChoice == null){
        showMsg("请选择需要操作的帖子！");
        return;
    }
    var psId = $(nowChoice).attr("value");
    
    $.ajax({
        type:'post',
        url : "updatePost.do",
        async:false,
        data : {
            psId : psId,
            psIsDoing : 1
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")
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

function updateNote(){
    if(nowChoice == null){
        showMsg("请选择需要操作的帖子！");
        return;
    }
    var psId = $(nowChoice).attr("value");
    var psTitle = $("#verifyTitle").val();
    var psContent = $("#verifyContent").val();
    $.ajax({
        type:'post',
        url : "updatePost.do",
        async:false,
        data : {
            psId : psId,
            psContent : psContent,
            psTitle : psTitle
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")
            {
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

function verifyNote(){
    if(nowChoice == null){
        showMsg("请选择需要操作的帖子！");
        return;
    }
    var psId = $(nowChoice).attr("value");
    var psTitle = $("#verifyTitle").val();
    var psContent = $("#verifyContent").val();
    var checkStatus = $("#passTypeVal").val();
    var applyAdvice = $("#applyAdvice").val();
    if((checkStatus == "-1" || checkStatus == "1") && applyAdvice ==""){
    	 showMsg("请输入审核意见！");
    	 return;
    }
    
    $.ajax({
        type:'post',  
        url : "updatePost.do", 
        async:false,  
        data : {
            psId : psId,
            checkStatus : checkStatus,
            psContent : psContent,
            psTitle : psTitle,
            applyAdvice :applyAdvice
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")  
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

//切换样式
function changgeClass(obj,classIn,classOut){
	$("."+classIn).each(function(){
		$(this).attr("class",classOut);
	})
	$(obj).attr('class',classIn);
}

function changeUpNote(){
//	alert($(nowChoice).text());
    if(nowChoice == null){
        showMsg("请选择需要操作的帖子！");
        return;
    }
	var psId = $(nowChoice).attr("value");
	var nowTitle;
	var isTop = 0;
	var topLen = $("#top_"+psId).html().length;
	console.log(topLen);
	if(topLen>0){
		$("#top_"+psId).html('');
	}else{
	    isTop = 1;
		$("#top_"+psId).html('<span class="am-badge am-ding am-badge-danger am-list-icon">顶</span>');
	}
    
    $.ajax({
        type:'post',  
        url : "updatePost.do", 
        async:false,  
        data : {
            psId : psId,
            //psTitle : nowTitle,
            psIsTop : isTop,
            reqType : 0
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data.status == "1")  
            {
                //clear(psId);
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


function deleteNote(){
    if(nowChoice == null){
        showMsg("请选择需要操作的帖子！");
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
    //alert(type);
    $.ajax({
        type:'get',  
        url : "queryPosts.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            secId : $("#secId").val(),
            psCheckStatus : $("#psCheckStatus").val(),
            //psUserId : $("#psUserId").val(),
            psIsDoing : $("#psIsDoing").val(),
            psParentId : $("#psParentId").val()
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
                      +'<li class="am-g am-list-item-dated " id="ps'+data.result[i].psId+'"><a class="am-padding-right-lg" onclick="changgeVerify(this)"  value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a>';
                      content=content+'<div id="top_'+data.result[i].psId+'">';
                      if(data.result[i].psIsTop==1){
                          content=content
                          +'<div id="top_'+data.result[i].psId+'"><span class="am-badge am-ding am-badge-danger am-list-icon">顶</span>';
                      }
                      content=content+'</div></li>';
                      
                    }
                    $("#wrapper").append(content);
                }else{
                    for(var i=0;i<data.result.length;i++)  
                    {
                      newDate.setTime(data.result[i].psCreateTime);
                      content=content
                      +'<li class="am-g am-list-item-dated " id="ps'+data.result[i].psId+'"><a class="am-padding-right-lg" onclick="changgeVerify(this)" value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a>';
                      content=content+'<div id="top_'+data.result[i].psId+'">';
                      if(data.result[i].psIsTop==1){
                          content=content
                          +'<span class="am-badge am-ding am-badge-danger am-list-icon">顶</span>';
                      }
                      content=content+'</div></li>';
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
    $('#verifyContent').val("");
    $('#verifyTitle').val(""); 
    $("#verifyImage").empty();
    $("#userInfo").empty();
    var passType = $("#passType1");
    if(passType != undefined){
		$("#passType1").prop("checked",true);
		$("#passTypeVal").val("2");
		$("#applyAdvice").val("");
		$("#applyAdviceDiv").removeClass("am-block");
		$("#applyAdviceDiv").addClass("am-hide");
	}
}

/**
 * 显示审核管理细则
 */
function showNoticeGLGZ(){
	var content = "";
	content +=
		'<p>一、管理员职责</p>'
		+ '  <p>1.“幸福帮帮团”内容管理</p>'
		+ '  <p>负责栏目内容的维护、管理，管理员及时对本栏目的帖子进行审核、置顶、删除、修改等操作。一般情况下，管理员每日21：00前对本栏目当日新增内容进行1-2次集中维护、管理。</p>'
		+ '  <p>（1）审核：审核版友的发帖申请，及时发布每一个符合平台规定的帖子，确保本版讨论主题的鲜明性。</p>'
		+ '  <p>（2）删帖：删除不符合《平台发帖规则》的所有帖子，删除时应当简要说明原因并通知发帖人。一般情况下，只对不符合发帖规则、恶意发帖、大批量的发帖才予以删除。</p>'
		+ '  <p>（3）置顶：对于易引起共鸣、关注的帖子可以置顶，对于不适合置顶的帖子或置顶时间超过一周或未超期但置顶已无意义的帖子，应当及时解固。置顶帖各栏目一般不得超过六条</p>'
		+ '  <p>（4）投诉建议：有关本栏目的投诉建议之帖应当及时回复，并短信通知发帖人。</p>'
		+ '  <p>2.“幸福微课堂”内容管理</p>'
		+ '  <p>每周至少更新一次本栏目“幸福微课堂”内容，包括主题讲座和好书推荐。</p>'
		+ '  <p>3.“轻松一刻”内容管理</p>'
		+ '  <p>每名管理员每月至少在“轻松一刻”发布一条信息（转摘、原创均可），包括但不限于幽默笑话、段子、冷笑话、减压音乐、搞笑视频、趣人趣事等，要求阳光、健康、积极，杜绝情色信息。</p>'
		+ '  <p>4.版友管理</p>'
		+ '  <p>负责与本栏目版友互动及栏目信息动态监控。</p>'
		+ '  <p>（1）对版友信息发布的真实性和资源反馈的有效性进行核实。</p>'
		+ '  <p>（2）及时有效地发现用户的问题，并对问题进行快速跟进和支持。</p>'
		+ '  <p>（3）发挥好桥梁作用，做好信息发布者与资源提供者之间的沟通衔接。</p>'
		+ '  <p>（4）积极组织话题讨论，提高本栏目活跃度和关注度，形成良好的讨论氛围。</p>'
		+ '  <p>（5）对版友宽容大度，尽量不要用管理员身份发表过激言论。</p>'
		+ '  <p>二、管理员福利</p>'
		+ '  <p>1．管理员每月享有一定额度的平台积分，积分可兑换礼品，具体按平台积分管理细则实施。</p>'
		+ '  <p>2．管理员在本栏目发帖享有免审（即发即见）权。</p>'
		+ '  <p>3．结识志同道合朋友，锻炼组织、协调能力，积累论坛管理经验。</p>'
		+ '  <p>4．帮助优秀管理员提升自身影响力，在年度各类评先评优时予以优先考虑。</p>'
		+ '  <p>5.利用互联网工具每月对各栏目的活跃度等级进行统计，根据5等级进行积分。活跃等级最高的积5分，最低积1分，按积分高低评选年度十佳管理员。</p>'
		+ '  <p>三、其他事项</p>'
		+ '  <p>1．为论坛安全起见，严禁管理员将管理权限私自外借给其他普通会员，否则免去其管理员职务。</p>'
		+ '  <p>2．管理员在行使管理权力时，必须严格限定在自己的管理范围之内。管理范围之外，牵涉到本栏目或虽不牵涉本栏目但想提出自己的看法和意见，必须以短信等私下的方式提交给总管理员。</p>'
		+ '  <p>3．对于任何与本版有关但不能把握之事，应及时与总管理员沟通。</p>'
		+ '  <p>4．关于管理员请假的规范：如因个人等原因近期一周以上不超过一个月不能进行栏目管理的管理员，应提前一周向总管理员请假，休假前做好工作交接；如因个人原因需要离开平台一个月以上，可以申请辞职。</p>';

	showPopMsg('湖南移动H-EAP微信平台栏目管理规则', content);
}