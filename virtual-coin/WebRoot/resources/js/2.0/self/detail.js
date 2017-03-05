var isNew = true;
var jsonStr = {};
//var pageCount = 1;

$(function(){
    var title = $("#psTitle").val();
	if(title!=null&&title!=''&&title!='不超过20个字'&&title!=undefined){
		//$('#psTitle').attr("disabled", true);
		$('#psTitle').attr("readonly", true);
		isNew = false;
	}
	query('01');//第一次加载 
});

function focusTitle(){
    if(isNew){
        $("#psTitle").val('');
    }
}

function query(type)  
{
    //alert(type);
    $.ajax({
        type:'get',  
        url : "querySubPosts.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            psId : $("#psId").val()
        },
        dataType:'json', 
        cache : false,  
        success : function(data) {
            if(data==null)  
            {
                $("#pageNo").val(parseInt($("#pageNo").val())-1);   
            } else {
                pageCount = data.pageCount;
                
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
      content=content
      +'<li class="am-comment">'
      +'<article class="am-comment">'
      +'<span name="my-popover" id="my-popover-'+data[i].psUserId+'-'+data[i].psId+'" onclick="getUserInfo(\''+data[i].psUserId+'\',\''+data[i].psId+'\')" isload="false">';
      if(data[i].userAvatar!=null){
          content=content+'  <img src="'+data[i].userAvatar+'" class="am-comment-avatar" width="48" height="48"/>';
      }else{
          content=content+'  <img src="images/2.0/titleMan.png" class="am-comment-avatar" width="48" height="48"/>';
      }
      content=content
      +'</span>'
      +'<div class="am-comment-main">'
      +'  <header class="am-comment-hd">'
      +'    <div class="am-comment-meta">'
      +'      <a  class="am-comment-author">'+data[i].psUserName+'</a>'
      +'      发布于 <time datetime="'+newDate.timeago()+'" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>'
      +'    </div>';
      //content=content +'<span class="am-badge am-ding am-badge-success">';
//      if(i==0 && $("#pageNo").val()=="1"){
//          content=content+'楼主';
//      }else{
      //content=content+((parseInt($("#pageNo").val())-1)*parseInt($("#pageSize").val())+i+2) + '楼';
//      }
      //content=content+'</span>';
      content=content
      +'  </header>'
      +'  <div class="am-comment-bd">'
      if(data[i].psField1 != null && data[i].psField1 != ""){
          content=content +'<img src="images/2.0/emoji/'+data[i].psField1+'" width="16" height="16">';
      }
      content=content
      +'    '+data[i].psContent+'';
      var picContent = "";
      var picFiles = data[i].picFileList;
      if(picFiles != null && picFiles.length >0){
    	  for(var k=0;k<picFiles.length;k++)  {
    		  picContent += '<img class="am-img-reply" src="upload/'+picFiles[k].filePath+'">';
          }
      }
      content=content +picContent
      +'  </div>'
      +'  <footer class="am-comment-footer">'
      +'  <div class="am-comment-actions">'
      //+'  <a><i class="am-icon-thumbs-up">积分：'+data[i].psViews+'</i></a>'
      //+'  <a><i class="am-icon-reply">'+data[i].psReply+'</i></a>'
      +'  </div>'
      +'  </footer>'
      +'</div>'
      +'</article>'
      +'</li>';
    }
    
    if(type=="00")
    {
        $("#wrapper").append(content);
    }else{
        $("#wrapper").html(content);
    }
}

/**
 * 关注
 * @param psId
 */
function attention(psId, obj){
    var atnStatus = 0;
    if($(obj).text() == "关注"){
        atnStatus = 1;
    }
    $.ajax({
        type:'post',
        url : "attention.do",
        async:false,
        data : {
            psId : psId,
            atnStatus : atnStatus
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data!=null)
            {
                showMsg("操作成功!");
                if($(obj).text() == "关注"){
                    $(obj).text("取消关注");
                }else{
                    $(obj).text("关注");
                }
            }
        },
        error : function(){
            showMsg("操作失败，请刷新再试");
        }
    });
}
