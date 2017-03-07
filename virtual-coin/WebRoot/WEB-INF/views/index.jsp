<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head.ftl">
  <title></title>
</head>
<body>
<div data-am-widget="titlebar" class=" am-titlebar-multi" >
    <h2 class="am-titlebar-title am-margin-bottom-0">
        U币交易平台
    </h2>

    <nav class="am-titlebar-nav">
        <a onclick="reSearchBySort(0)" class="">登陆</a>
        <a onclick="reSearchBySort(1)" class="">注册</a>
    </nav>
</div>
<form name="addNoteForm" action="addNote.do" method="POST" class="am-form" data-am-validator>
  <input type="hidden" name="pageNo" id="pageNo" value="1" /> 
  <input type="hidden" name="secId" id="secId" value="" /> 
  
  
  <div class="am-cf am-padding-xs">
      <div data-am-widget="slider" class="am-slider am-slider-c2" data-am-slider='{"directionNav":false}' >
  <ul class="am-slides">
      <li>
        	<img src="images/2.0/vc_index1.jpg">
          <div class="am-slider-desc">U币交易平台正式上线，开启数字货币新时代</div>
         
      </li>
      <li>
        	<img src="images/2.0/vc_index2.jpg">
          <div class="am-slider-desc">2017年与我们共创财富</div>
         
      </li>
      <li>
        	<img src="images/2.0/vc_index4.jpg">
          <div class="am-slider-desc">U币 全新理念 全新平台 引领潮流</div>
         
      </li>
  </ul>
</div>
<div class="am-padding-top-xs" style="height:130px;" >
<div class="am-panel am-panel-secondary">
<div class="am-panel-hd">U币最新交易行情</div>
  <div class="am-panel-bd">
	 <div class="am-g">
	  <div class="am-u-sm-3">
	    <!--<span class="am-show-md-down ">U币</span>
	    <span class="am-show-md-down am-text-danger">2.086</span>
	    <span class="am-show-lg-only">lg-4</span>
	  -->
	   <span class="am-show-md-down am-text-danger am-text-xl">2.086</span>
	  </div>
	  <div class="am-u-sm-3">
		<span class="am-show-md-down">买一</span>
	    <span class="am-show-md-down am-text-danger ">2.081</span>
      </div>
	 <div class="am-u-sm-3">
		<span class="am-show-md-down ">卖一</span>
	    <span class="am-show-md-down am-text-danger">2.281</span>
      </div>
	  <div class="am-u-sm-3 ">
	    <span class="am-show-md-down">涨跌</span>
	    <span class="am-show-md-down am-text-danger">+1.234%</span>
	  </div>
	</div>
  </div>
</div>
</div>
<div class="pet_content_main">
 <div data-am-widget="list_news" class="am-list-news am-list-news-default" >
  <div class="am-list-news-bd">
  <ul class="am-list">
     <!--缩略图在标题右边-->
      <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">
        <div class="pet_list_one_info">
            <div class="pet_list_one_info_l">
                <div class="pet_list_one_info_ico"><img src="images/2.0/a2.png" alt=""></div>
                <div class="pet_list_one_info_name">董事长 </div>
            </div>
            <div class="pet_list_one_info_r">
                <div class="pet_list_tag pet_list_tag_xxs">最新公告</div>
            </div>
        </div>
        <div class=" am-u-sm-8 am-list-main pet_list_one_nr">
            <h3 class="am-list-item-hd pet_list_one_bt"><a href="###" class="">U币于2017年3月2日上线交易</a></h3>
            <div class="am-list-item-text pet_list_one_text">X币网从2017年3月2日开盘以来，平台以公开、透明的方式面向广大用户，并逐渐取得了用户的信任。为了增加用户体验，我们缩短了提现时间，同时充值秒到账，而且网站服务器进行了高端升级</div>

        </div>
          <div class="am-u-sm-4 am-list-thumb">
            <a href="###" class="">
              <img src="images/2.0/q3.jpg" class="pet_list_one_img" alt=""/>
            </a>
          </div>
      </li>
      <li class="am-g am-list-item-desced am-list-item-thumbed am-list-item-thumb-right pet_list_one_block">
        <div class="pet_list_one_info">
            <div class="pet_list_one_info_l">
                <div class="pet_list_one_info_ico"><img src="images/2.0/a3.png" alt=""></div>
                <div class="pet_list_one_info_name">首席顾问</div>
            </div>
            <div class="pet_list_one_info_r">
                <div class="pet_list_tag pet_list_tag_zzs">行业咨询</div>
            </div>
        </div>
        <div class=" am-u-sm-8 am-list-main pet_list_one_nr">
            <h3 class="am-list-item-hd pet_list_one_bt"><a href="###" class="">瑞士将放宽金融法规，支持区块链创新！</a></h3>
            <div class="am-list-item-text pet_list_one_text">瑞士政府表示，金融法规应“迅速调整”，以迎合FinTech创新，“尤其是在区块链领域。”瑞士联邦委员会在星期三发表的声明中表示，希望建立一个“动态的金融科技系统”</div>

        </div>
          <div class="am-u-sm-4 am-list-thumb">
            <a href="###" class="">
              <img src="images/2.0/q4.jpg" class="pet_list_one_img" alt="我很囧，你保重....晒晒旅行中的那些囧！"/>
            </a>
          </div>
      </li>
      </ul>
      </div>
      </div>
      </div>
  </div>

<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
	<div class="am-list-news-bd am-margin-top-sm">
    <ul class="am-list am-list-main am-list-striped" id="wrapper"></ul>
    </div>
   
</div>

</form>
  <#include "/inc/message.ftl">
  <#include "/inc/menu.ftl">
</body>
</html>
