
$(function(){
    query('01');//第一次加载  
    $(window).scroll(loadDataPage);
});

function searchTieZi(){
    $("#pageNo").val("1");
    query("01");
}

function query(type)
{
    $.ajax({
        type:'get',  
        url : "my/getMyNotice.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val()
        },
        dataType:'json', 
        cache : false,  
        success : function(data) {
            loading=true;  
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
            loading=true;  
            $("#pageNo").val(parseInt($("#pageNo").val())-1);     
            showMsg("查询数据出错啦，请刷新再试");  
        }  
    });  
}  

function updateUserMsg(psId){
    $.ajax({
        type:'post',
        url : "my/updateUserMsg.do",
        async:false,
        data : {
            psId : psId
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data)
            {
            	//showMsg("更新成功！");
            }else{
                //showMsg("操作失败！");
            }
        },  
        error : function(){
            //showMsg("操作失败！");
        }
    });
}

function initDataList(data, type){
    var content="";
    var newDate = new Date();
    var openStyle;
    for(var i=0;i<data.length;i++)
    {
        openStyle = "myDetailPage";
        if(data[i].psStatus == "1" && data[i].psCheckStatus == "1"){
            openStyle = "myNotePage";
        }
        newDate.setTime(data[i].psCreateTime);
        content=content
        +'<li class="am-g am-list-item-dated" onclick="updateUserMsg(\''+data[i].psId+'\');jump(\'my/'+openStyle+'\',\'secId='+data[i].psSecId+'&psId='+data[i].psId+'\')">'
        +'<a class="am-list-item-hd ">'+data[i].psTitle+'</a>'
        +'<span class="am-badge am-badge-danger am-radius am-round am-list-username">'+data[i].msgCount+'</span>';
        content=content
        +'  <footer class="am-comment-footer">'
        +'  <div class="am-comment-actions">'
        +'  <span>发布时间：'+newDate.format('yyyy-MM-dd')+'</span>'
        +'  <span class="am-fr">发布状态：'+getPsCheckStatus(data[i].psCheckStatus)+'</span>'
        +'  </div>'
        +'  <div class="am-comment-actions">'
        +'  <span>是否置顶：';
        if(data[i].psIsTop==1){
          content=content+"是"+'</span>';
        }else{
          content=content+"否"+'</span>';
        }
        content=content
        +'  <span class="am-fr">需求进展：';
        if(data[i].psIsDoing==1){
          content=content+"已完成"+'</span>';
        }else{
          content=content+"进行中"+'</span>';
        }
        content=content
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
