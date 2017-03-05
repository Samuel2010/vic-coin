
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
        url : "my/getNote.do", 
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
        +'<li id="'+'list_'+data[i].psId+'" class="am-g am-list-item-dated" >'
        +'<a class="am-list-item-hd " onclick="jump(\'my/'+openStyle+'\',\'secId='+data[i].psSecId+'&psId='+data[i].psId+'\')">'+data[i].psTitle+'</a>';
        
        if((data[i].psCheckStatus == '1' || data[i].psCheckStatus == '-1') && data[i].psStatus!='0'){
        	content = content  +'  <span class="am-badge am-badge-danger am-radius am-list-username" onclick="deleteNote(\''+data[i].psId+'\')">删除</span>';
        }
        
        content=content
        +'  <footer class="am-comment-footer">'
        +'  <div class="am-comment-actions">'
        +'  <span>发布时间：'+newDate.format('yyyy-MM-dd')+'</span>';
        if(data[i].psStatus=='0'){
            content=content +'<span class="am-fr">帖子状态：<span class="am-text-danger">已删除</span></span>';
        }else{
        	content=content
        	+'  <span class="am-fr">发布状态：'+getPsCheckStatus(data[i].psCheckStatus)+'</span>'
            +'  </div>';
        	/*var tips = getPsCheckStatusExtend(data[i].psCheckStatus);*/
        	var tips ='';
    	    if(data[i].psCheckStatus == 1 || data[i].psCheckStatus == -1){
    	    	if(data[i].psField6 !=null){
    	    		tips = '<span class="am-text-danger">'+data[i].psField6+'</span>';
    	    	}else{
    	    		tips = getPsCheckStatusExtend(data[i].psCheckStatus);
    	    	}
    	    }
        	if(tips != ''){
        		content += '<div class="am-comment-actions"><span class="am-fl">审核意见：</span>'+tips+'</div>';
        	}
        }
       
        
        if(!(data[i].psCheckStatus == '1' || data[i].psCheckStatus == '-1') && data[i].psStatus != '0'){
            content=content
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
            content=content +'  </div>';
          
        }
       
        content=content
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
