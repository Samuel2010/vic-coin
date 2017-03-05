var sumScore = 0;
$(function(){
    query('01');
    $('#qryYear').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryYear').datepicker('close');
    });
    $('#qryMon').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryMon').datepicker('close');
    });
    $('#qryTopYear').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryTopYear').datepicker('close');
    	//console.log(event.date.format('MM'));
    	queryScoreTop(1,event.date.format('yyyy'),0);
    });
    $('#qryTopMon').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryTopMon').datepicker('close');
    	//console.log(event.date.format('MM'));
    	queryScoreTop(1,event.date.format('MM'),1);
    });
    $('#qryTopQuaYear').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryTopQuaYear').datepicker('close');
    	//console.log(event.date.format('yyyy'));
    	queryScoreTop(2,event.date.format('yyyy'),0);
    });
    $('#qryTopAllYear').datepicker().
    on('changeDate.datepicker.amui', function(event) {
    	$('#qryTopAllYear').datepicker('close');
    	//console.log(event.date.format('yyyy'));
    	queryScoreTop(3,event.date.format('yyyy'),0);
    });
    
    $('#qryQua').on('change', function() {
    	var qryDate = $("#qryTopQuaYear").val();
    	queryScoreTop(2,qryDate,0);
    });
});

function queryScoreTop(type,qryDate,otherData){
	var qryYear,qryMon,qryQua ="";
	var showPanel ="qryTopMonData";
	if(type == 1){
		if(otherData == 0){
			qryYear = qryDate;
			qryMon = $("#qryTopMon").val();
		}else{
			qryYear = $("#qryTopYear").val();
			qryMon = qryDate;
		}
	}else if(type == 2){
		qryYear = qryDate;//$("#qryTopQuaYear").val();
		qryQua = $("#qryQua").val();
		showPanel ="qryTopQuaData";
	}else if(type == 3){
		qryYear = qryDate;//$("#qryTopAllYear").val();
		showPanel ="qryTopAllData";
	}
	console.log(type+","+qryYear+","+qryMon+","+qryQua);
	 $.ajax({
	        type:'get',  
	        url : "my/queryScoreTop.do", 
	        async:false,  
	        data : {
	            qryYear : qryYear,
	            qryMon : qryMon,
	            qryQua : qryQua,
	            qryType : type
	        },
	        dataType:'json', 
	        cache : false,  
	        success : function(data) {
                $("#"+showPanel).html(data.result);
	        },  
	        error : function(){
	            loading=true;  
	            showMsg("查询数据出错啦，请稍后再试");  
	        }  
	    });  
	
}

function doQry(){
    $("#pageNo").val("1");
    query("01");
}

function query(type)
{
    $.ajax({
        type:'get',  
        url : "my/getScoreLogs.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            qryYear : $("#qryYear").val(),
            qryMon : $("#qryMon").val(),
            qryDay : $("#qryDay").val(),
            logOpt : $("#logOpt").val()
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
                     $("#wrapper").html("未查询到结果");
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
    var score = 0;
    for(var i=0;i<data.length;i++)
    {
        newDate.setTime(data[i].logCreateTime);
        content = content
        +'<tr>' 
        +'<td>'+newDate.format('yyyy-MM-dd hh:mm:ss')+'</td>'   
        +'<td>'+data[i].ruleName+'</td>'    
        +'<td>';
       if(data[i].logOpt == "1"){
        	content += '-'+data[i].logScore;
        	score = score-data[i].logScore;
        }else{
        	content += '+'+data[i].logScore;
        	score = score+data[i].logScore;
        }
        //content += data[i].logScore;
    	//score = score+data[i].logScore;
        content +='</td>'   
        +' </tr>' ;
       
    }
    if(type=="00")
    {
    	sumScore += score;
        $("#wrapper").append(content);
    }else{
    	sumScore = score;
        $("#wrapper").html(content);
    }
    
    var sumInfo =
        '<tr id="sumTr" class="am-warning">' 
        +'<td colspan="2">小计</td>'    
        +'<td>'+sumScore+'</td>'   
        +' </tr>' ;
    $("#sumTr").remove();
    $("#wrapper").append(sumInfo);
    $("#sumTr").replaceWith(sumInfo);
}

function doClear(){
     /* $("#qryMon").datepicker('setValue', "1");
      $("#qryDay").datepicker('setValue', "2");*/
	  $("#qryMon").attr("placeholder","月");
	  $("#qryDay").attr("placeholder","日");
      $("#qryMon").removeAttr("value");
      $("#qryDay").removeAttr("value");
}

