
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
        url : "my/getAttention.do",
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
    for(var i=0;i<data.length;i++)  
    {
      newDate.setTime(data[i].psCreateTime);
      content=content  
      +'<li class="am-g am-list-item-dated">'
      +'<a class="am-list-item-hd " onclick="jump(\'my/myDetailPage\',\'secId='+data[i].psSecId+'&psId='+data[i].psId+'\')">'+data[i].psTitle+'</a>';
      if(data[i].psUserId==$("#psUserId").val()){
          content=content +'<span class="am-badge am-badge-danger am-radius am-list-username">本人帖</span>';
      }
      content=content
      +'  <footer class="am-comment-footer">'
      +'  <div class="am-comment-actions">'
      +'  <span>发布时间：'+newDate.format('yyyy-MM-dd')+'</span>'
      +'  <span class="am-fr">发布状态：'+getStatus(data[i].psStatus)+'</span>'
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

function getStatus(psStatus){
    if(psStatus == 1){
        psStatus = "审核中";
    }else if(psStatus == 2){
        psStatus = "审核通过";
    }else if(psStatus == -1){
        psStatus = "审核不通过";
    }else{
        psStatus = "待审核";
    }
    
    return psStatus;
}