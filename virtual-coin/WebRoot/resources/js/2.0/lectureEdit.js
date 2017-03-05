var nowChoice ;
var delOtherFileId = '';
var verfDelFlag = false;
var pageSourceType = '0';//来源页面，0-主题讲座，1-好书推荐

$(function(){
	$("[data-labelauty]").labelauty();
	pageSourceType = $("#pageSourceType").val();
    query('01');//第一次加载
    $('#other-form-file').on('change', function() {
    		 var fileNames = '';
    	     $.each(this.files, function() {
    	         fileNames += '<span class="am-badge">' + this.name + '</span> ';
    	         var delFiles = $("#delFiles").val();
		    	 if(delFiles ==""){
		    	 	 $("#delFiles").val(delOtherFileId);
		    	 }else if(delFiles.indexOf(delOtherFileId)<0){
		    	     $("#delFiles").val(delFiles+","+delOtherFileId);
		    	 }
    	     });
    	     $('#other-file-list').html(fileNames);
       
      });
    var $radios = $('[name="scrollPic"]');
    if($radios!=undefined){
        $radios.on('change',function() {
            $("#fileType").val($radios.filter(':checked').val());
        });
    }
});

function changgeVerify(obj){
	changgeClass(obj,'am-g','');
	var title = $(obj).text();
	var psId = $(obj).attr("value");
	$('#verifyTitle').val(title);
	$('#delFiles').val(""); 
	$('#psId').val(psId);
	if(pageSourceType =="1"){
		$('#psField2').val($(obj).attr("author"));
	}
	clearFileInfo();
	$('#file-list').html("");
	initContent(psId);
	nowChoice = obj;
}

function changgeProgress(value){
    query('01');
    var psId = $(nowChoice).attr("value");
    clear(psId);
}

function initContent(psId){
	$.ajax({
        type:'post',
        url : "getPost.do",
        async:false,
        data : {
            psId : psId
        },
        dataType:'json',
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data!=null)
            {
                $("#verifyContent").val(data.wxPosts.psContent);
                var isScrollPic = false;
                if(data.files.length > 0){
                	var fileContent = "";
                	var otherFileContent= "";
                    for(var i=0;i<data.files.length;i++)
                    {
                      if(data.files[i].fileIsPic ==1){
                    	  fileContent += 
                          '<div class="file_bar" data-old="true" id="delBtn_'+data.files[i].fileId+'" >'
                          +'<div style="padding: 5px;">'
                          +'<span class="file_del" onclick="delOldFiles(\''+data.files[i].fileId+'\');verfDelInfo();" data-index=""'
                          +'title="删除"></span>'
                          +'</div>'
                          +'</div>'
              	          +'<a style="height:100px;width:30px;" class="imgBox" id="ptFile_'+data.files[i].fileId+'">'
              	          +'<img src="upload/'+data.files[i].filePath+'"  id="'+data.files[i].fileId+'" value="'+data.files[i].fileName+'" class="am-img-reply" /></a>';
                    	 
                    	  var fileType = data.files[i].fileType;
                    	  
                    	  if(fileType == "2"){
                    		  //$("#scrollPic1").prop("checked",true);
                    		  isScrollPic = true;
                    		  //$("#changeFileTypeId").val(''+data.files[i].fileId);
                    	  }else{
                    		 // $("#scrollPic2").prop("checked",true);
                    	  }
                    	 
                    	 
                      }else if(data.files[i].fileIsPic ==2){
                    	  otherFileContent +='<span class="am-badge"><a href="upload/'+data.files[i].filePath+'">'+data.files[i].fileName+'</a></span>';
                    	  delOtherFileId = ''+data.files[i].fileId;
                    	 
                      }
                     
                    }
                    
                    $("#file-list").html(fileContent);
                    $('#other-file-list').html(otherFileContent);
                    console.log(isScrollPic);
                      
                }
                if(isScrollPic){
            		$("#scrollPic1").prop("checked",true);
            		$("#fileType").val("2");
            	}else{
            		$("#scrollPic2").prop("checked",true);
            		$("#fileType").val("1");
            	}
                if(data.wxUser != null){
                    $("#userInfo").html(data.wxUser.userName + " " + (data.wxUser.unitName != null?data.wxUser.unitName:""));
                }else{
                    $("#userInfo").html(data.wxPosts.psUserName);
                }
            }
        },
        error : function(){
            showMsg("查询数据出错啦，请刷新再试");
        }
    });
}




//切换样式
function changgeClass(obj,classIn,classOut){
	$("."+classIn).each(function(){
		$(this).attr("class",classOut);
	})
	$(obj).attr('class',classIn);
}


function changeLecture(){
    if(nowChoice == null){
        showMsg("请选择需要操作的记录！");
        return;
    }
    
    var rs = $("#lectureForm").validator('isFormValid');
	if(!rs){
		showMsg("请输入正确格式的内容！");
		return;
	}
	if(pageSourceType =="1" && getCurFileCount()<=0 && verfDelFlag){
		showMsg("请选择要上传的图片！");
		return;
	}
	
	$("#changeFileTypeId").val("");
	$("#changeFileName").val("");
	var fileType = $("#fileType").val();
	var delFiles = $("#delFiles").val();
	if(fileType == "2"){
		if(getCurFileCount()<=0 && verfDelFlag){
			showMsg("已选择“滚动图片”选项，请上传！");
			return;
		}
		//$("#file-list").children().first()
		var fid = $("#file-list").children("a:first").children("img:first").attr('id');
		var fname = $("#file-list").children("a:first").children("img:first").attr('value');
		console.log(fid);
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
        url : 'updateLecture.do', 
        data: formData,  
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:'json',
        success : function(data) {
            if(data.status == "1")  
            {
                showMsg("更新成功。", 'refresh');
                
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


function deleteLecture(){
    if(nowChoice == null){
        showMsg("请选择需要操作的记录！");
        return;
    }

    var psId = $(nowChoice).attr("value");
    
    $.ajax({
        type:'post',  
        url : "deletePost.do", 
        async:false,  
        data : {
            psId : psId
        },
        dataType:'json', 
        cache : false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success : function(data) {
            if(data == true)  
            {
                clear(psId);
                showMsg("操作成功！","refresh");
            }else{
                showMsg("操作失败！");
            }
        },  
        error : function(){
            showMsg("操作失败！");
        }
    });
}

function query(type)  
{
    //alert(type);
    $.ajax({
        type:'get',  
        url : "queryPosts.do", 
        async:false,  
        data : {
            pageNo : $("#pageNo").val(),
            pageSize : $("#pageSize").val(),
            secId : $("#psSecId").val(),
            psCheckStatus : $("#psCheckStatus").val()
            //psUserId : $("#psUserId").val(),
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
                      +'<li class="am-g am-list-item-dated" id="ps'+data.result[i].psId+'"><a onclick="changgeVerify(this)" author="'+data.result[i].psField2+'" value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a></li>';
                    }
                    $("#wrapper").append(content);
                }else{
                    for(var i=0;i<data.result.length;i++)  
                    {
                      newDate.setTime(data.result[i].psCreateTime);
                      content=content
                      +'<li class="am-g am-list-item-dated" id="ps'+data.result[i].psId+'"><a onclick="changgeVerify(this)" author="'+data.result[i].psField2+'"  value="'+data.result[i].psId+'">'+data.result[i].psTitle+'</a></li>';
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

function clear(psId){
    $("#ps"+psId).remove();
    nowChoice = null;
    verfDelFlag = false;
    $('#verifyContent').val("");
    $('#verifyTitle').val(""); 
    $('#delFiles').val(""); 
    $("#userInfo").empty();
}

//更新数据时，因为有两个文件表单，在校验时需要记录一下图片是否删除
function verfDelInfo(){
	verfDelFlag = true;
}

function checkImg(img) {
    var nWidth, nHeight;
    //console.log(img.naturalWidth);
    if (img.naturalWidth) {
        nWidth = img.naturalWidth;
        nHeight = img.naturalHeight;
        console.log(nWidth/nHeight);
        if(nWidth/nHeight<0.666){
        	showMsg("当前图片宽高比例不适宜做为封面滚动图片，请修改！");
        	return false;
        }
    }
    
    return true;
}

