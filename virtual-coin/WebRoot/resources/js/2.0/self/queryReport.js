var sumScore = 0;
$(function(){
    query();
    $('#qryYear').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryYear').datepicker('close');
    });
    $('#qryMon').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryMon').datepicker('close');
    });
    $('#dyEjQryYear').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#dyEjQryYear').datepicker('close');
    });
    $('#dyEjQryMon').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#dyEjQryMon').datepicker('close');
    });
    $('#funQryYear').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#funQryYear').datepicker('close');
    });
    $('#funQryMon').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#funQryMon').datepicker('close');
    });
});

function doQry(){
    query();
}

function query()
{
    $.ajax({
        type:'get',  
        url : "my/queryCalcPostsReport.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            qryYear : $("#qryYear").val(),
            qryMon : $("#qryMon").val(),
            qryDay : $("#qryDay").val(),
            qryType : $("#qryType").val()
        },
        dataType:'json', 
        cache : false,  
        success : function(data) {
            if(data!=null)
            {
               initDataList(data);
            }  
        },  
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");  
        }  
    });  
}  


function initDataList(data, type){
    var content="";
    for(var i=0;i<data.length;i++)
    {
    	var calcMap = data[i];
    	content+='<tr>';
    	for(key in calcMap){
    		content+='<td>';
    		content+=calcMap[key];
    		content+='</td>';
    	}
    	content+='</tr>';
    }
    $("#wrapper").html(content);
    $("#wrapper").children("tr:last").addClass("am-warning");
}

function doClear(){
     /* $("#qryMon").datepicker('setValue', "1");
      $("#qryDay").datepicker('setValue', "2");*/
	  $("#qryMon").attr("placeholder","月");
	  $("#qryDay").attr("placeholder","日");
      $("#qryMon").removeAttr("value");
      $("#qryDay").removeAttr("value");
}


function doDyEjQry()
{
    $.ajax({
        type:'get',  
        url : "my/queryDyejPostsReport.do", 
        async:false,  
        data : {
            qryYear : $("#dyEjQryYear").val(),
            qryMon : $("#dyEjQryMon").val(),
            qryDay : $("#dyEjQryDay").val()
        },
        dataType:'json', 
        cache : false,  
        success : function(data) {
            if(data!=null)
            {
            	var calcMap = data;
            	var content='<tr>';
            	for(key in calcMap){
            		content+='<td>';
            		content+=calcMap[key];
            		content+='</td>';
            	}
            	content+='</tr>';
                $("#dyEjWrapper").html(content);
            }  
        },  
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");  
        }  
    });  
}  



function doDyEjClear(){
	  $("#dyEjQryMon").attr("placeholder","月");
	  $("#dyEjQryDay").attr("placeholder","日");
      $("#dyEjQryMon").removeAttr("value");
      $("#dyEjQryDay").removeAttr("value");
}

function doFunQry()
{
    $.ajax({
        type:'get',  
        url : "my/queryFunPostsReport.do", 
        async:false,  
        data : {
            qryYear : $("#funQryYear").val(),
            qryMon : $("#funQryMon").val(),
            qryDay : $("#funQryDay").val()
        },
        dataType:'json', 
        cache : false,  
        success : function(data) {
            if(data!=null)
            {
            	var calcMap = data;
            	var content='<tr>';
            	for(key in calcMap){
            		content+='<td>';
            		content+=calcMap[key];
            		content+='</td>';
            	}
            	content+='</tr>';
                $("#funWrapper").html(content);
            }
        },  
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");  
        }  
    });  
}  



function doFunQryClear(){
	  $("#funQryMon").attr("placeholder","月");
	  $("#funQryDay").attr("placeholder","日");
      $("#funQryMon").removeAttr("value");
      $("#funQryDay").removeAttr("value");
}
