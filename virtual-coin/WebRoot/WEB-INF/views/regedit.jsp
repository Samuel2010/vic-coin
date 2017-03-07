<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head.ftl">
  <title>用户绑定</title>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，UI 暂不支持。 请升级浏览器以获得更好的体验！</p>
<![endif]-->

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <hr/>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
          <form class="am-form am-form-horizontal">
            <div class="am-form-group">
              <label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>
              <div class="am-u-sm-9">
                <input type="text" id="user-name" placeholder="输入您的姓名">
                <small>您的姓名</small>
              </div>
            </div>

            <div class="am-form-group">
              <label for="user-email" class="am-u-sm-3 am-form-label">OA账号</label>
              <div class="am-u-sm-9">
                <input type="text" id="user-email" placeholder="输入您的OA账号 ">
                <small>公司邮箱地址前缀，如zhangsan</small>
              </div>
            </div>

            <div class="am-form-group">
              <label for="user-phone" class="am-u-sm-3 am-form-label">手机号码</label>
              <div class="am-u-sm-9">
                <input type="number" pattern="[0-9]*" id="user-phone" placeholder="输入您的手机号码">
              </div>
            </div>

            <div class="am-form-group">
              <div class="am-u-sm-9 am-u-sm-push-3">
                <button type="button" class="am-btn am-btn-primary">绑定账号</button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>

  </div>
  <!-- content end -->


</body>
</html>
