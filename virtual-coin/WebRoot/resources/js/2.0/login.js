$(function(){
	$("#loginBtn").click(function(){
	    var $form = $('#loginForm');
		var formData = $form.serialize();
		var loginId = $("#loginId").val();
		var loginPwd = $("#loginPwd").val();
		
		/*var rs = $form.validator('isFormValid');
		console.log(rs);
		if(!rs){
			showMsg("请输入用户名和密码");
			return;
		}*/
		console.log($form.validator('isFormValid'));
	 
		saveLoading("loginBtn");
	    $.ajax({
	        type:'post',
	        url : "login/doLogin",
	        async:false,
	        data :formData,
	        dataType:'json',
	        cache : false,
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        success : function(data) {
	    		if(data.flag == "false"){
	    			showMsg(data.msg);
	    		}else{
	    			window.location.href="index";
	    		}
	            saveLoadComplete("loginBtn");
	        },  
	        error : function(e){
	            showMsg("请求出错，"+e.responseText);
	            saveLoadComplete("loginBtn");
	        }
	    });
	})
	 
});

