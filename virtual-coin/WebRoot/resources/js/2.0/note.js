var uploadFile = [];// 需要上传的文件数组
var perUploadFile = [];// 存放永久的文件数组，方便删除使用
var lastUploadFile =[];//上次选择的文件数组
var fileNum = 0;
var fileUploadCount = 0;//用于限制上传图片数量，界面表单包含此元素“fileUploadCount”，后续再封装成单独的文件上传对象

$(function() {
	$('#doc-form-file').on('change', function() {
		initFiles(this.files);
	});
});

function initFiles(files) {
	if (files.length) {
		var fileCount = $("#fileUploadCount").val();
		if(fileCount != null && fileCount != undefined ){
			var oldData = $('[data-old="true"]');//对于维护界面，已存在的图片需要添加此属性
			fileUploadCount = fileCount;
			
			if(oldData != null && oldData != undefined && oldData.attr("data-old") == "true" ){
				fileUploadCount --;
			}
			
			if(uploadFile.length >= fileUploadCount || files.length > fileUploadCount){
				showMsg("只允许上传"+fileCount+"张图片！");
				return;
			}
		}
		
		var self = this;
		var arryFiles = [];
		for(var i = 0;i<files.length;i++){
			//TODO 可在此处过滤上传格式等。。
			arryFiles.push(files[i]);
		}
		self.lastUploadFile = this.uploadFile;
		this.uploadFile = this.uploadFile.concat(arryFiles);
		
		var tmpFiles = [];
		
		// 因为jquery的inArray方法无法对object数组进行判断是否存在于，所以只能提取名称进行判断
		var lArr = [];  // 之前文件的名称数组
		var uArr = [];  // 现在文件的名称数组
		$.each(self.lastUploadFile, function(k, v){
			lArr.push(v.name);
		});
		$.each(self.uploadFile, function(k, v){
			uArr.push(v.name);
		});
		
		$.each(uArr, function(k, v){
			// 获得当前选择的每一个文件   判断当前这一个文件是否存在于之前的文件当中
			if($.inArray(v, lArr) < 0){  // 不存在
				tmpFiles.push(self.uploadFile[k]);
			}
		});
		
		// 如果tmpFiles进行过过滤上一次选择的文件的操作，需要把过滤后的文件赋值
		this.uploadFile = tmpFiles;
			
		
		// 目前是遍历所有的文件，给每个文件增加唯一索引值
		$.each(this.uploadFile, function(k, v) {
			// 因为涉及到继续添加，所以下一次添加需要在总个数的基础上添加
			v.index = self.fileNum;
			// 添加一个之后自增
			self.fileNum++;
		});
		var selectFiles = this.uploadFile;
		// 要把全部的文件都保存下来，因为删除所使用的下标是全局的变量
		this.perUploadFile = this.perUploadFile.concat(this.uploadFile);
		// 合并下上传的文件
		this.uploadFile = this.lastUploadFile.concat(this.uploadFile);
		
		var html = '', i = 0;
		
		funDealtPreviewHtml(selectFiles,i,html);
		
	}
}

/**
 * 递归创建HTML
 * @param selectFiles
 * @param i
 * @param html
 */
function funDealtPreviewHtml(selectFiles,i,html){
	var file = selectFiles[i];
	if(file){
		var index= file.index;
		console.log(file.name);
		var reader = new FileReader();
		reader.onload = function(e) {
			html += '<div class="file_bar" id="delBtn_' + index + '">'
			+ '<div style="padding:5px;">'
			+ '<span class="file_del" onclick="delUploadFile(\''
			+ index + '\')" data-index="' + index
			+ '" title="删除"></span>' + '</div>' + '</div>';
			
			html += '<a style="height:100px;width:30px;" class="imgBox" id="ptFile_'
				+ index + '">'
				+ '<img class="am-img-reply" id="uploadImgView" src="'
				+ e.target.result + '" value="'+file.name+'">' + '</a>';
			i++;
			funDealtPreviewHtml(selectFiles,i,html);
		};
		reader.readAsDataURL(file);
	}else{
		$('#file-list').append(html);
	};
	
	
}

/**
 * 批量处理当前文件
 * @param formData
 */
function funUploadFiles(formData) {
	var self = this; // 在each中this指向没个v  所以先将this保留
	// 遍历所有文件  ，在调用单个文件上传的方法
	$.each(this.uploadFile, function(k, v) {
		self.funUploadFile(formData, v);
	});
}

function funUploadFile(formData, file) {
	formData.append("fileList", file);
}

/**
 * 删除客户端已上传的文件，与服务器无交互
 * @param delFileIndex
 * @returns {delUploadFile}
 */
function delUploadFile(delFileIndex) {
	var self = this; // 在each中this指向没个v  所以先将this保留
	var tmpFile = []; // 用来替换的文件数组
	// 合并下上传的文件
	var delFile = this.perUploadFile[delFileIndex];

	// 目前是遍历所有的文件，对比每个文件  删除
	$.each(this.uploadFile, function(k, v) {
		if (delFile != v) {
			// 如果不是删除的那个文件 就放到临时数组中
			tmpFile.push(v);
		} else {
			$("#ptFile_" + delFileIndex).remove();
			$("#delBtn_" + delFileIndex).remove();
		}
	});

	this.uploadFile = tmpFile;
}

/**
 * 根据下标删除上传文件，标记并在服务端删除
 * @param delFileIndex
 */
function delOldFiles(delFileIndex){
 	 var delFiles = $("#delFiles").val();
 	 if(delFiles ==""){
 	 	 $("#delFiles").val(delFileIndex);
 	 }else{
 	     $("#delFiles").val(delFiles+","+delFileIndex);
 	 }
 	
    $("#ptFile_" + delFileIndex).remove();
	 $("#delBtn_" + delFileIndex).remove();
 }

/**
 * 清空已上传文件
 */
function clearFileInfo(){
	uploadFile = [];
	perUploadFile = [];
	lastUploadFile =[];
	fileNum = 0;
}

/**
 * 返回当前上传文件数量
 */
function getCurFileCount(){
	return uploadFile.length;
}

