var isNew = true;
var $modal = $('#msg-modal');
var $popModal = $('#msg-popup');
var $popEmoji = $('#popEmoji');
var pageCount = 1;
var loadingEnd = false;

var tabLanguage = {
    "sProcessing":   "处理中...",
    "sLengthMenu":   "每页 _MENU_ 项",
    "sZeroRecords":  "没有匹配结果",
    "sInfo":         "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
    "sInfoEmpty":    "当前显示第 0 至 0 项，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix":  "",
    "sSearch":       "搜索:",
    "sUrl":          "",
    "sEmptyTable":     "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands":  ",",
    "oPaginate": {
        "sFirst":    "首页",
        "sPrevious": "上页",
        "sNext":     "下页",
        "sLast":     "末页",
        "sJump":     "跳转"
    },
    "oAria": {
        "sSortAscending":  ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
    }
}

function jump(target, params){
    //var title = $("#title").text();
    if(params != undefined){
        params = params + '&';
    }else{
        params = "";
    }
	var jumpUrl = target + '.do?' + params;
	window.location.href = jumpUrl;
}


function showPopMsg(title, msg){
    if(title != null){
        $("#popMsgTitle").html(title);
    }else{
        $("#popMsgTitle").html("温馨提示");
    }
    $("#popMsg").html(unescape(msg));
    $popModal.modal('open');
}

function showMsg(msg,modeType,afterUrl){
	var $msgModal = $("#my-alert");
    $("#alertMsg").html(msg);
    $msgModal.modal();
    if(modeType != null && modeType != undefined){
    	if(modeType == "goback"){
    		$msgModal.on('close.modal.amui', function(){
        	    history.go(-1);
        	});
    	}else if(modeType == "refresh"){
    		$msgModal.on('close.modal.amui', function(){
    			window.location.reload();
        	});
    	}else{
    		if(afterUrl != null && afterUrl != undefined){
    			$msgModal.on('close.modal.amui', function(){
            		window.location.href=afterUrl;
            	});
            }
    	}
    }
}

function saveLoading(objId){
	$("#"+objId).button('loading');
}
function saveLoadComplete(objId){
	$("#"+objId).button('reset');
}

Date.prototype.format = function(format) {
       var date = {
              "M+": this.getMonth() + 1,
              "d+": this.getDate(),
              "h+": this.getHours(),
              "m+": this.getMinutes(),
              "s+": this.getSeconds(),
              "q+": Math.floor((this.getMonth() + 3) / 3),
              "S+": this.getMilliseconds()
       };
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
       }
       for (var k in date) {
              if (new RegExp("(" + k + ")").test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
              }
       }
       return format;
}
//console.log(newDate.format('yyyy-MM-dd h:m:s'));

Date.prototype.timeago = function() {
    var nowDate = new Date();
    var ct = (nowDate.getTime()-this.getTime())/1000;
    if (ct > 86400*6){
      return this.format('yyyy-MM-dd hh:mm');
    }else if (ct >= 86400*2 && ct <= 86400*6){
        return parseInt(ct/86400) + '天前';
    }else if (ct >= 86400){
        return '昨天';
    }else if (ct >= 3600){
        return parseInt(ct/3600) + '小时前';
    }else if (ct >= 60){
        return parseInt(ct/60) + '分钟前';
    }else if (ct > 0){
        return parseInt(ct) + '秒前';
    }else{
        return '刚刚';
    }
}

function loadData(){
    $("#pageNo").val(parseInt($("#pageNo").val())+1);
    if(pageCount >= parseInt($("#pageNo").val())){
        query("00");
    }else{
        showMsg("没有数据啦～～～");
    }
}

function loadDataPageByRange(range){
    if(($(window).scrollTop()+$(window).height()>$(document).height()-range) && !loadingEnd){
    	//$("#pageNo").val(parseInt($("#pageNo").val())+1);
    	 if(pageCount >= parseInt($("#pageNo").val())+1){
    		 $("#qryMoreBtn").addClass("am-block");
    		 $("#qryMoreBtn").removeClass("am-hide");
    		 $("#pageNo").val(parseInt($("#pageNo").val())+1)
    	     query("00");
    	}else{
    		console.log("已无更多数据");
    		loadingEnd = true;
    	}
   }
}
function loadDataPage(){
	loadDataPageByRange(30);
}


// 获取选中的值
$(function() {
  var $radios = $('[name="ftfs"]');
  if($radios!=undefined){
      $radios.on('change',function() {
          console.log('单选框当前选中的是：', $radios.filter(':checked').val());
          $("#psPostType").val($radios.filter(':checked').val());
      });
  }
  
  var sfgz = $('[name="sfgz"]');
  if(sfgz!=undefined){
      sfgz.on('change',function() {
          console.log('单选框当前选中的是：', sfgz.filter(':checked').val());
          $("#psIsAttention").val(sfgz.filter(':checked').val());
      });
  }
  
});
