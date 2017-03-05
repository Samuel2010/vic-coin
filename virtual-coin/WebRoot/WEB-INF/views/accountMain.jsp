<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head2.0.ftl">
  <title></title>
</head>
<body>
 <form name="" action="" method="POST" class="am-form" data-am-validator>
  <header data-am-widget="header"
          class="am-header am-header-default">
      <!-- 
      <div class="am-header-left am-header-nav">
          <a href="" class="">
                <i class="am-header-icon am-icon-home"></i>
          </a>
      </div>
 	 -->
      <h1 class="am-header-title">
          <a href="#title-link" class="">
            财务中心
          </a>
      </h1>

      <div class="am-header-right am-header-nav">
          <a href="#right-link" class="">

                <i class="am-header-icon am-icon-bars"></i>
          </a>
      </div>
  </header>

      <nav data-am-widget="menu" class="am-menu  am-menu-slide1"  
     data-am-menu-collapse
    > 
    <a href="javascript: void(0)" class="am-menu-toggle">
          <i class=""></i>
    </a>


      <ul class="am-menu-nav am-avg-sm-4 am-collapse">
          <li class="">
            <a href="##" class="" >财务中心</a>
          </li>
          <li class="">
            <a href="##" class="" >人民币充值</a>
          </li>
          <li class="">
            <a href="##" class="" >人名币提现</a>
          </li>
          <li class="">
            <a href="##" class="" >转入虚拟币</a>
          </li>
          <li class="">
            <a href="##" class="" >转出虚拟币</a>
          </li>
          <li class="">
            <a href="##" class="" >委托管理</a>
          </li>
          <li class="">
            <a href="##" class="" >成交查询</a>
          </li>
      </ul>

  </nav>
       
      </form>
  <#include "/inc/message.ftl">
  <#include "/inc/menu.ftl">
</body>
</html>
