
$(function(){
    query('01');//第一次加载  
    $(window).scroll(loadDataPageByRange(10));
});

function searchTieZi(){
    $("#pageNo").val("1");
    query("01");
}

function query(type)
{
    $.ajax({
        type:'get',  
        url : "queryDyejReplys.do", 
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
        newDate.setTime(data[i].psCreateTime);
        content=content
        +'<li class="am-g am-list-item-dated" onclick="jump(\'cktwPage\',\'secId=36&channel=1&psId='+data[i].psId+'\')">'
        +'<a class="am-list-item-hd ">'+data[i].psTitle+'</a>'+
        '<span class="am-list-date">'+newDate.format('yyyy-MM-dd')+'</span>';
        +'</li>';
    }
    if(type=="00")
    {
        $("#wrapper").append(content);
    }else{
        $("#wrapper").html(content);
    }
}
