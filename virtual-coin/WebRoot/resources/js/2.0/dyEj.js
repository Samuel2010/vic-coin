
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
    var searchValue = "";
    if($("#searchValue").val() != '本版搜索'){
        searchValue = $("#searchValue").val();
    }
    var psPostType = $("#psPostType").val();
    if(psPostType == "-1"){
    	psPostType = "";
    }
    //alert(psPostType);
    
    $.ajax({  
        type:'get',  
        url : "queryPosts.do", 
        async:false,  
        data : {  
            pageNo : $("#pageNo").val(),
            secId : '36',
            searchValue : searchValue,
            psPostType : psPostType
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
                      +'<li >'
                      +'<a class="am-list-item-hd  am-list-main-show" style="padding-right:80px;"  onclick="jump(\'cktwPage\',\'secId='+$("#secId").val()+'&psId='+data.result[i].psId+'\')">'+data.result[i].psTitle+'</a>';
                      if(data.result[i].psIsTop == "1"){
                    	  content+='<span class="am-badge am-badge-danger am-radius am-badge-daily" style="">常见提问</span>';
                      }else if(data.result[i].psIsDoing=="1"){
                    	  content+='<span class="am-badge am-badge-danger am-radius am-badge-reply" style="">已回复</span>';
                      }
                      content +='  <footer class="am-comment-footer ">'
                 		 +'  <div class="am-comment-actions ">'
                 		+data.result[i].psUserName
                 		 +'   发布于  <time datetime="'+newDate.timeago()+'" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>'
                 		 +'  </div>'
                 		 +'  </footer>'
                      +'</li>';
                    }  
                    $("#wrapper").append(content);  
                }else{  
                    for(var i=0;i<data.result.length;i++)  
                    {  
                      newDate.setTime(data.result[i].psCreateTime);
                      content=content  
                      +'<li>'
                      +'<a class="am-list-item-hd  am-list-main-show" style="padding-right:80px;" onclick="jump(\'cktwPage\',\'secId='+$("#secId").val()+'&psId='+data.result[i].psId+'\')">'+data.result[i].psTitle+'</a>';
                      if(data.result[i].psIsTop == "1"){
                    	  content+='<span class="am-badge am-badge-danger am-radius am-badge-daily" style="">常见提问</span>';
                      }else if(data.result[i].psIsDoing=="1"){
                    	  content+='<span class="am-badge am-badge-danger am-radius am-badge-reply" style="">已回复</span>';
                      }
                      content +='  <footer class="am-comment-footer">'
                  		 +'  <div class="am-comment-actions ">'
                  		 +data.result[i].psUserName
                  		 +'   发布于<time datetime="'+newDate.timeago()+'" title="'+newDate.timeago()+'">'+newDate.timeago()+'</time>'
                  		 +'  </div>'
                  		 +'  </footer>'
                      +'</li>';
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
