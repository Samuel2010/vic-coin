<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head.ftl">
</head>
<body>
<div class="am-g myapp-login">
	<div class="myapp-login-bg">
		  <div data-am-widget="tabs"
	       class="am-tabs am-tabs-d2"
	        >
	      <ul class="am-tabs-nav am-cf">
	          <li class="am-active"><a href="[data-tab-panel-0]">SIGN IN</a></li>
	          <li class=""><a href="[data-tab-panel-1]">PHONE IN</a></li>
	         
	      </ul>
	      <div class="am-tabs-bd">
	          <div data-tab-panel-0 class="am-tab-panel am-active">
				<form  class="am-form" id="loginForm" name="loginForm" data-am-validator>
					<fieldset>
						<div class="am-form-group">
						<label for="loginId">用户名</label>
						<input type="text" id="loginId" name="loginId" minlength="3" value="admin" placeholder="User ID" class="am-form-field login-input" required/>
						</div>
						<div class="am-form-group">
						<label for="doc-vld-name">密码</label>
						<input type="password" id="loginPwd" name="loginPwd" value="123456" minlength="3" placeholder="User Password" class="am-form-field login-input" required/>
						</div>
						<div class="am-form-group myapp-login-treaty"><label class="am-form-label"></label><label class="am-checkbox-inline"> <input type="checkbox" value="" name="docVlCb" minchecked="2" maxchecked="4" required="">已同意使用条约 </label></div>
						<button class="myapp-login-button am-btn am-btn-secondary" id="loginBtn" type="button" data-am-loading="{spinner: 'circle-o-notch', loadingText: 'Login...'}" >SIGN IN</button>
					</fieldset>
					<legend>Forgot Password?</legend>
				</form>
	          </div>
	          <div data-tab-panel-1 class="am-tab-panel ">
	            <form action="" class="am-form" data-am-validator>
					<fieldset>
						<div class="am-form-group">
						<label for="doc-vld-name">手机</label>
						<input type="text" id="loginPhone" name="loginPhone" minlength="3" placeholder="Number" class="am-form-field login-input" required/>
						</div>
						<div class="am-form-group">
						<label for="doc-vld-name">验证码</label>
						<input type="password" id="loginCode" name="loginCode" minlength="3" placeholder="Code" class="am-form-field login-input" required/>
						</div>
						<div class="am-form-group myapp-login-treaty"><label class="am-form-label"></label><label class="am-checkbox-inline"> <input type="checkbox" value="" name="docVlCb" minchecked="2" maxchecked="4" required="">已同意使用条约 </label></div>
						<button class="myapp-login-button am-btn am-btn-secondary" type="submit">SIGN IN</button>
					</fieldset>
					<legend>Forgot Password?</legend>
				</form>
	          </div>
	        
	      </div>
	  </div>
	</div>
</div>
<#include "/inc/message.ftl">
<script type="text/javascript" src="<@s.url '/js/2.0/app.js?time=${.now}'/>"></script>
<script type="text/javascript" src="<@s.url '/js/2.0/login.js'/>"></script>
</body>
</html>