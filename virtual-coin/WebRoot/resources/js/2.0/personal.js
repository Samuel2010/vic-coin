
$(function(){
    query('01');//第一次加载  
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
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
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
                      +'<li class="am-g am-list-item-dated">'
                      +'<a class="am-list-item-hd" onclick="jump(\'cktwPage\',\'secId='+$("#secId").val()+'&psId='+data.result[i].psId+'\')">'+data.result[i].psTitle+'</a>'
                      +'<span class="am-list-date">'+newDate.format('yyyy-MM-dd')+'</span>'
                      +'</li>';
                    }  
                    $("#wrapper").append(content);  
                }else{  
                    for(var i=0;i<data.result.length;i++)  
                    {  
                      newDate.setTime(data.result[i].psCreateTime);
                      content=content  
                      +'<li class="am-g am-list-item-dated">'
                      +'<a class="am-list-item-hd" onclick="jump(\'cktwPage\',\'secId='+$("#secId").val()+'&psId='+data.result[i].psId+'\')">'+data.result[i].psTitle+'</a>'
                      +'<span class="am-list-date">'+newDate.format('yyyy-MM-dd')+'</span>'
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
