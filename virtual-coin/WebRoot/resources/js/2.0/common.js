var isNew = true;
var $modal = $('#msg-modal');
var $popModal = $('#msg-popup');
var $popEmoji = $('#popEmoji');
var pageCount = 1;
var loadingEnd = false;

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


//切换样式
function changgeClass(obj,classIn,classOut){
  $("."+classIn).each(function(){
      $(this).attr("class",classOut);
  });
  $(obj).attr('class',classIn);
}

/**
 * 显示发帖须知提示
 */
function showFtxz(){
var content = "";
content +=
    '  <p>“幸福帮帮团”是通过员工间的线上互动，推动内部资源流转，第一时间为员工提供线上援助和需求解决方案的内部交流平台。为维护平台规范、高效运转， 体现对员工的帮助价值，员工发帖请遵守以下规定：</p>'
    + '  <p>1、遵守社会公德和国家有关法律法规，文明使用网络语言；</p>'
    + '  <p>2、符合湖南移动企业文化和有关规章制度要求，弘扬正能量；</p>'
    + '  <p>3、不得发布与职业发展、情绪压力、亲子教育、婚姻家庭、医疗健康、法律咨询、综合服务（家政、旅游等）这些主题无关的帖子。</p>'
    + '  <p>4、请选择对应模块发帖，根据帖子实际情况发到最符合的模块，不发表与模块主题不符的帖子；</p>'
    + '  <p>5、发帖要尽量将自己的意思表达清楚，包括求助的问题和要求，联系方式等，帖子正文内容最多不超过300字；帖子标题应尽量与帖子内容相符，标题不超过20字； </p>'
    + '  <p>6、不允许在短时间内多次发布雷同或相似度高的帖子；</p>'
    + '  <p>7、欢迎回帖，回帖内容要具有针对性，内容清楚、言之有物，能够使发帖者得到有用的信息和有效帮助。 不允许在他人帖子里发表与主帖无关的内容，或是发表仅仅是“支持”、“顶”、“同意”等太过简单的回复。</p>'
    + '  <p>8、对自己的言论承担责任。员工的帖子一经发表，就无法由本人修改或删除，除非有正当理由，群主一般不受理发帖者删除和修改帖子的请求。</p>'
    + '  <p>9、对不符合发帖规定的，群主有权删除其全部或部分言论，并视情况给与适当的扣除积分处理。</p>'
    + '  <p>10、对员工发起的帖子，管理员一般在4-8小时内进行发布处理。对于特别紧急的帖子，可及时与管理员联系，第一时间进行发布。</p>';

showPopMsg('“幸福帮帮团”发帖管理规则', content);
}

/**
 * 显示积分规则,待改成从数据库读取
 */
function showScoreRule(){
var content = "";
content +=
    '  <p>为充分发挥“湖南移动幸福同行”微信企业号的作用，增进员工之间的交流和互动，调动广大员工参与H-EAP员工幸福成长计划的积极性和自觉性，特制定此积分规则。</p>'
	+ '  <p>一、	积分的获取</p>'
    + '  <p>1、成功关注“湖南移动幸福同行”微信企业号获积分10分。</p>'
    + '  <p>2、在“职业发展、情绪压力、亲子教育、婚姻家庭、医疗健康、法律咨询、综合服务”等模块的“幸福帮帮团”发帖一次并被群主审核通过，获积分10分，被置顶的另获积分5分；回帖一次并被群主审核通过，获积分10分，同一人在同一帖子回帖多次最多计算一次积分，被置顶的另获积分5分。</p>'
    + '  <p>3、在“职业发展、情绪压力、亲子教育、婚姻家庭、医疗健康、法律咨询、综合服务”等模块的“幸福微课堂”点击浏览信息一次获积分5分（每条信息仅首次浏览计分）。</p>'
    + '  <p>4、参与专业测评获积分10分，参与趣味测评获积分5分。。</p>'
    + '  <p>5、通过“一键呼”咨询一次获积分10分。 </p>'
    + '  <p>6、进入“轻松一刻”发布或共享幽默笑话、段子等信息并被审核通过获积分5分（每天最多不超过3次）。</p>'
    + '  <p>7、反馈平台存在的问题或提出改进的建议，每次获积分5分。</p>'
    + '  <p>8、担任职业发展、情绪压力、亲子教育、婚姻家庭、医疗健康、法律咨询、综合服务等模块的群主每月获积分200分。</p>'
    + '  <p>9、积分月排行前10名的，获赠积分20分；积分月排行前11—50名的，获赠积分10分。每季度发布积分排名前20名的榜单，年终进行最后排名，对积分最高的前20名进行奖励。</p>'
    + '  <p>10、每月参与平台推广活动：在“活动中心”—“平台推广活动”——“分享赚”中分享转发一次获取积分100分（同一内容转发多次只计算一次积分），在“学习赚”中点击浏览一次获取积分20分（同一内容浏览多次只计算一次积分）。</p>'
    + '  <p>二、积分的扣除</p>'
    + '  <p>1、违反国家法律法规以及公司相关规定，或与企业文化相悖的帖子，一次扣除积分20分。</p>'
    + '  <p>2、使用低俗、不文明的语言，一次扣除积分5分。。</p>'
    + '  <p>3、发布与职业发展、情绪压力、亲子教育、婚姻家庭、医疗健康、法律咨询、综合服务等主题无关的帖子，一次扣除积分5分。</p>'
    + '  <p>4、未按模块主题对应发帖的，一次扣除积分2分。</p>'
    + '  <p>5、回帖不具有针对性，一次扣除积分2分。</p>'
    + '  <p>6、参与兑换流量、话费和其它礼品的，扣除相应的兑换积分。</p>'
    + '  <p>三、积分的使用</p>'
    + '  <p>积分可用来兑换流量或其它礼品（目前只支持兑换流量），还可以赢取幸运转盘抽奖的机会。</p>'
    + '  <p>1、按1个积分兑换5M流量。如某员工有100分积分，则该员工可兑换500M流量。</p>'
    + '  <p>2、积分累计达到一定额度时，可以参与幸运转盘抽奖活动（通过平台公告另行通知）。</p>'
    + '  <p>3、每个员工每月积分兑换最多不超过5G流量。</p>'
    + '  <p>4、兑换的流量可通过“湖南移动和你在一起”公众号“流量银行”提取和使用（可转赠）。</p>'
    + '  <p>5、上一年度的积分可以滚入下一年度计算。</p>'
    + '  <p>四、积分的查询</p>' 
    + '  <p>在“个人中心”的“我的积分”栏目可以查询员工本人积分的详细信息，包括积分记录、积分余额、积分排行榜、积分兑换等。</p>';

showPopMsg('“湖南移动幸福同行”积分规则(试行)', content);
}

function showDemo(obj){
	var content = "";
	var title = "";
	switch(obj){
	case 1 :
		title = '<p>职业发展交流互动：包括但不限于职业生涯规划、潜能开发、人格完善、职业倦怠预防和应对、晋升迁职的调试、新环境适应等方面的问题。</p>';
		break;
	case 2 :
		title = '<p>情绪压力交流互动：包括但不限于职场压力舒缓、人际关系优化、职业心理健康、工作与生活平衡、人际沟通，失眠、焦虑、抑郁等情绪疏导问题。</p>';
		break;
	case 3 :
		title = '<p>亲子教育交流互动：包括但不限于亲子关系调整、特长教育、子女参加评选活动在线投票等服务。</p>';
		break;
	case 4 :
		title = '<p>婚姻家庭交流互动：包括但不限于家政、夫妻情感咨询、家庭关系调节、未婚员工的情感咨询以及婚前指导等。</p>';
		break;
	case 5 :
		title = '<p>医疗健康交流互动：包括但不限于医院以及民间的名医指南，挂号信息，各大医院口碑相传的各所擅长的医疗优势，各类营养师、瑜伽老师、健身教练、中医健康管理专家等信息。</p>';
		break;
	case 6 :
		title = '<p>法律咨询交流互动：为员工提供便捷的法律咨询服务及法律资源，如员工遇法律纠纷，为员工提供法律支持。</p>';
		break;
	case 7 :
		title = '<p>综合服务交流互动：包括但不限于户外、旅行、美食、交通、住宿等信息。</p>';
		break;
		
	}
	content +=
		title
	    + '  <p>一、有效发帖示例</p>'
	    + '  <p><img src="images/2.0/fatie'+obj+'.png" class="am-img-demo"> </p>';
		if(obj == 5){
			content +=
				'  <p>二、有效回帖示例一</p>'
				+ '  <p> <img src="images/2.0/huitie'+obj+'.png" class="am-img-demo"></p>'
				+ '  <p>三、有效回帖示例二</p>'
				+ '  <p> <img src="images/2.0/huitie'+obj+'-1.png" class="am-img-demo"></p>'
				+ '  <p>四、完整示例</p>'
				+ '  <p><img src="images/2.0/chakan'+obj+'.png" class="am-img-demo"> </p>';
		}else{
			content +=
				'  <p>二、有效回帖示例</p>'
				+ '  <p> <img src="images/2.0/huitie'+obj+'.png" class="am-img-demo"></p>'
				+ '  <p>三、完整示例</p>'
				+ '  <p><img src="images/2.0/chakan'+obj+'.png" class="am-img-demo"> </p>';
		}
	   

	showPopMsg('发帖及回帖示例', content);
}

function showEmoji(){
    $popEmoji.modal('open');
}

function chooseEmoji(emojiId){
    $("#psField1").val(emojiId);
    $("#chooseEmojiImg").attr("src", "images/2.0/emoji/" + emojiId);
    $("#chooseEmojiImg").attr("class","am-show");
    

    $('[name="emojiRadio"]').each(function(){
        $(this).attr("checked",false);
    });
    
    $popEmoji.modal('close');
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

function focusTitle(){
    if(isNew){
        $("#psTitle").val('');
    }
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

function getPsCheckStatus(psCheckStatus){
    if(psCheckStatus == 1){
        psCheckStatus = '<span class="am-text-danger">返回修改</span>';
    }else if(psCheckStatus == 2){
        psCheckStatus = '审核通过';
    }else if(psCheckStatus == -1){
        psCheckStatus = '<span class="am-text-danger">审核未通过</span>';
    }else{
        psCheckStatus = '待审核';
    }
    return psCheckStatus;
}

function getPsCheckStatusExtend(psCheckStatus){
	var tips = '';
    if(psCheckStatus == 1){
    	tips = '<span class="am-text-danger">请参照栏目发帖及回贴实例</span>';
    }else if(psCheckStatus == -1){
    	tips = '<span class="am-text-danger">不符合发帖规则</span>';
    }
    return tips;
}

function getPsIsDoing(psIsDoing){
    if(psIsDoing == 1){
        psIsDoing = "已完成";
    }else{
        psIsDoing = "进行中";
    }
    return psIsDoing;
}

function saveLoading(objId){
	$("#"+objId).button('loading');
}
function saveLoadComplete(objId){
	$("#"+objId).button('reset');
}

/**
 * 提交帖子
 * @param action 提交的action
 */
function submitNote(action){
	
    clearInputFile("doc-form-file");
    var formData= new FormData($("#addNoteForm")[0]);
    try{
    	 funUploadFiles(formData);
	}catch(e){
		console.log(e);
	}
	saveLoading("saveBtn");
    $.ajax({
        type:'post',  
        url : action, 
        data: formData,  
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:'json',
        success : function(data) {
            if(data.status == "1")  
            {
                if($("#psParentId").val() == "-1"){
                    showMsg("发帖成功，需要管理员审核通过后才能显示。", 'goback');
                }else{
                    showMsg("回帖成功。", 'goback');
                }
                
            }else{
                showMsg("操作失败！错误信息[" + data.errorMsg + "]");
                //重新绑定事件。
                $('#doc-form-file').on('change', function() {
                    var fileNames = '';
                    initFiles(this.files);
                  });
            }
            saveLoadComplete("saveBtn");
        },  
        error : function(e){
            showMsg("操作失败！"+e);
            saveLoadComplete("saveBtn");
        }
    });
}

function clearInputFile(id){
	try{
		var obj = document.getElementById(id+'');
		obj.outerHTML=obj.outerHTML;
	}catch(e){
		console.log(e);
	}
    //obj.innerHTML = '<input id="doc-form-file" name="files" type="file" multiple>';
}

function refreshPageBtn(pageCount){
	if(pageCount >$("#pageNo").val()){
		 $("#qryMoreBtn").addClass("am-block");
		 $("#qryMoreBtn").removeClass("am-hide");
	  }else{
		 $("#qryMoreBtn").removeClass("am-block");
		 $("#qryMoreBtn").removeClass("am-hide");
		 $("#qryMoreBtn").addClass("am-hide");
	  }
}

function getUserInfo(userId, psId){
    var popid = '#my-popover-'+userId+'-'+psId;
    var _isload = $(popid).attr("isload");
    if(_isload == "true"){
        $(popid).popover($(popid));
    }else{
        $.ajax({
            type:'post',  
            url : 'getUserInfo.do', 
            data: {
                userId : userId
            },
            async: false,
            dataType:'json', 
            cache : false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success : function(data) {
                if(data.wxUser != null)  
                {
                    $(popid).attr("isload", "true");
                    var content = "";
                    if(data.wxUser.unitName!=null){
                        content = data.wxUser.userName +"，积分："+data.wxUser.userJiFen+ "，" + data.wxUser.unitName;
                    }else{
                        content = data.wxUser.userName +"，积分："+data.wxUser.userJiFen;
                    }
                    $(popid).popover({
                        theme: 'primary',
                        content: content
                    });
                    $(popid).popover('toggle');
                }
            },  
            error : function(e){
                showMsg("操作失败！"+e);
            }
        });
    }
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
  
  if($("#sPsCheckStatus")!=undefined){
      $("#sPsCheckStatus").html("发布状态："+getPsCheckStatus($("#psCheckStatus").val()));
  }
  
  if($("#sPsIsDoing")!=undefined){
      $("#sPsIsDoing").html("进展状态："+getPsIsDoing($("#psIsDoing").val()));
  }
  
  if($("#psTitle")!=undefined){
      var title = $("#psTitle").val();
      if(title!=null&&title!=''&&title!='不超过20个字'&&title!=undefined){
          $('#psTitle').attr("readonly", true);
          isNew = false;
      } 
  }
  
  var emojiRadio = $('[name="emojiRadio"]');
  if(emojiRadio!=undefined){
      emojiRadio.on('change',function() {
          console.log('单选框当前选中的是：', emojiRadio.filter(':checked').val());
          $("#psField1").val(emojiRadio.filter(':checked').val());
          $("#chooseEmojiImg").attr("src", "images/2.0/emoji/" + emojiRadio.filter(':checked').val());
          $("#chooseEmojiImg").attr("class","am-show");
      });
  }
});
