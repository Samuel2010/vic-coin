var changeType = "01";//01-主题讲座，02-好书推荐

$(function(){
	var listFlag = $("#listFlag").val();
	if(listFlag != undefined && listFlag == "1"){
		 $("#pageNo").val("1");
		 query('01');//第一次加载  
		 $(window).scroll(loadDataPage);
	}
	
    var $radios = $('[name="scrollPic"]');
    if($radios!=undefined){
        $radios.on('change',function() {
            $("#fileType").val($radios.filter(':checked').val());
        });
    }
});


function query(type)
{
    $.ajax({
        type:'get',  
        url : "queryLectures.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            secId : $("#secId").val(),
            secType : changeType
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
                initSecData(data);
                if(data.result.length==0)
                {
                     $("#pageNo").val(parseInt($("#pageNo").val())-1);
                     return "";
                }
                initDataList(data, type);
            }  
        },  
        error : function(){
            loading=true;  
            $("#pageNo").val(parseInt($("#pageNo").val())-1);     
            showMsg("查询数据出错啦，请刷新再试");  
        }  
    });  
}  

function initDataList(obj, type){
    var content="";
    var newDate = new Date();
    var data = obj.result;
    if(changeType =="01"){
    	for(var i=0;i<data.length;i++)
        {
            newDate.setTime(data[i].psCreateTime);
            content=content
            +'<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left"' 
            +' onclick="jump(\'lectureDetail\',\'secId='+$("#secId").val()+'&psId='+data[i].psId+'&changeType=01\')">'
            +'<div class="am-u-sm-4 am-list-thumb" style="max-height:100px;">';
            //console.log(data[i].filePath==null);
            if(data[i].filePath==null || data[i].filePath==""){
            	content +='    <img src="images/2.0/wkt-'+data[i].psSecId+'.jpg"/>';
            }else{
            	content +='    <img src="upload/'+data[i].filePath+'" alt="'+data[i].psTitle+'"/>';
            }
            content += '  </div>';

            content +=' <div class=" am-u-sm-8 am-list-main">'
            +'   <div><span>课程：'+data[i].psTitle+'</span></div>'
            +'   <div>发布时间：'+newDate.format('yyyy-MM-dd')+'</div>'
            +'   <div>内容摘要：</div>'
            +'     <div class="am-list-item-text am-hg-book">'+data[i].psContent+'</div>'
            +'  </div>'
            +'  </li>';
        }
    }else if(changeType =="02"){
    	for(var i=0;i<data.length;i++)
        {
            newDate.setTime(data[i].psCreateTime);
            content=content
            +'<li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-left"' 
            +'>'
            +'<div class="am-u-sm-4 am-list-thumb" style="max-height:200px;">'
            +'    <img  onclick="jump(\'lectureDetail\',\'secId='+$("#secId").val()+'&psId='+data[i].psId+'&changeType=02\')" src="upload/'+data[i].filePath+'" alt="'+data[i].psTitle+'"/>'
            +'  </div>'

            +' <div class=" am-u-sm-8 am-list-main" >'
            +'<div onclick="jump(\'lectureDetail\',\'secId='+$("#secId").val()+'&psId='+data[i].psId+'&changeType=02\')">'
            +'   <div>书名：'+data[i].psTitle+'</div>'
            +'   <div>作者：'+data[i].psField2+'</div>'
            +'   <div>发布时间：'+newDate.format('yyyy-MM-dd')+'</div>'
            +'   <div>内容摘要：</div>'
            +'     <div class="am-list-item-text am-hg-book">'+data[i].psContent+'</div>'
            +'</div>'
            +'	 <div class="arrow-down" onclick="showPopMsg(\'内容摘要\',\''+escape(data[i].psContent)+'\')"></div>'
            +'  </div>'
            +'  </li>';
        }
    }
	if (type == "00") {
		if (changeType == "01") {
			$("#wrapper").append(content);
		} else {
			$("#recommendBooks").append(content);
		}
	} else {
		if (changeType == "01") {
			$("#wrapper").html(content);
		} else {
			$("#recommendBooks").html(content);
		}
    }
    
    
}

function initSecData(obj) {
	var secTitle = "";
	var secList = obj.secList;
	for ( var i = 0; i < secList.length; i++) {
		if (i == 0) {
			secTitle += ' <li ';
			if (changeType == "01") {
				secTitle += 'class="am-active"';
			}
			secTitle += '><a onclick="changeProgess(0)">' + secList[i].secName
					+ '</a></li>';
		} else {
			secTitle += ' <li ';
			if (changeType == "02") {
				secTitle += 'class="am-active"';
			}
			secTitle += '><a onclick="changeProgess(1)">' + secList[i].secName
					+ '</a></li>';
		}

	}
	$("#secTitle").html(secTitle);
}

/**
 * 提交主题讲座、好书推荐
 * 
 * @param action
 *  提交的action
 */
function submitLeture(action){
	
    var rs = $("#lectureForm").validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的内容！");
		return;
	}
	/*if(getCurFileCount()<=0){
		showMsg("请选择要上传的图片！");
		return;
	}*/
	
	$("#changeFileTypeId").val("");
	$("#changeFileName").val("");
	var fileType = $("#fileType").val();
	if(fileType == "2"){
		if(getCurFileCount()<=0){
			showMsg("已选择“滚动图片”选项，请上传！");
			return;
		}
		//$("#file-list").children().first()
		var fid = $("#file-list").children("a:first").children("img:first").attr('id');
		var fname = $("#file-list").children("a:first").children("img:first").attr('value');
		//console.log(fid);
		if(fid != undefined ){
			if(fid !="uploadImgView"){
				$("#changeFileTypeId").val(fid);
			}
			$("#changeFileName").val(fname);
			var img = document.getElementById(""+fid);
			
			if(!checkImg(img)){
				return;
			}
		}
	}
	
	
	/*if($("#other-form-file").val() ==""){
		showMsg("请选择要上传的附件！");
		return;
	}*/
	
	//处理多张图片上传
	clearInputFile("doc-form-file");
    var formData = new FormData($("#lectureForm")[0]);
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
                showMsg("新增成功。", 'goback');
                
            }else{
                showMsg("操作失败！错误信息[" + data.errorMsg + "]");
            }
            saveLoadComplete("saveBtn");
        },  
        error : function(e){
            showMsg("操作失败！"+e);
            saveLoadComplete("saveBtn");
        }
    });
}

function submitRecBook(action){
    var formData = new FormData($("#lectureForm")[0]);
    var rs = $("#lectureForm").validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的内容！");
		return;
	}
	if(getCurFileCount()<=0){
		showMsg("请选择要上传的图片！");
		return;
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
                showMsg("新增成功。", 'goback');
                
            }else{
                showMsg("操作失败！错误信息[" + data.errorMsg + "]");
            }
            clearInputFile("doc-form-file");
            saveLoadComplete("saveBtn");
        },  
        error : function(e){
            showMsg("操作失败！"+e);
            clearInputFile("doc-form-file");
            saveLoadComplete("saveBtn");
        }
    });
}

function checkImg(img) {
    var nWidth, nHeight;
    //console.log(img.naturalWidth);
    if (img.naturalWidth) {
        nWidth = img.naturalWidth;
        nHeight = img.naturalHeight;
        //console.log(nWidth/nHeight);
        if(nWidth/nHeight<0.666){
        	showMsg("当前图片宽高比例不适宜做为封面滚动图片，请修改！");
        	return false;
        }
    }
    
    return true;
}


function changeProgess(value){
    //query('01');
    if(value == '0'){
    	changeType = "01";
        $("#scrollPicList").css('display','');
        $("#wrapper").css('display','');
        $("#recommendBooks").css('display','none');
    }else{
    	changeType = "02";
        $("#wrapper").css('display','none');
        $("#scrollPicList").css('display','none');
        $("#recommendBooks").css('display','');
    }
    $("#pageNo").val("1");
    loadingEnd = false;//恢复滚动记录，common.js
    $("#wrapper").html("");
	$("#recommendBooks").html("");
    query(changeType);
}