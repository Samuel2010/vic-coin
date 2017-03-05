<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head2.0.ftl">
  <title></title>
</head>
<body>
<form name="addNoteForm" action="addNote.do" method="POST" class="am-form" data-am-validator>
<fieldset>

<!-- Slider start -->
<!--<div data-am-widget="slider" class="am-slider am-slider-a1"  data-am-slider='{"directionNav":false}' id="scrollPicList">
  <ul class="am-slides">
    <li>
      <a onclick=""><img src="images/" style="height:200px;" ></a>
    </li>
  </ul>
</div>
--><!-- Slider end -->
  <div data-am-widget="slider" class="am-slider am-slider-c2" data-am-slider='{"directionNav":false}' >
  <ul class="am-slides">
      <li>
        	<img src="http://s.amazeui.org/media/i/demos/bing-1.jpg">
          <div class="am-slider-desc">远方 有一个地方 那里种有我们的梦想</div>
         
      </li>
      <li>
        	<img src="http://s.amazeui.org/media/i/demos/bing-2.jpg">
          <div class="am-slider-desc">某天 也许会相遇 相遇在这个好地方</div>
         
      </li>
      <li>
        	<img src="http://s.amazeui.org/media/i/demos/bing-3.jpg">
          <div class="am-slider-desc">不要太担心 只因为我相信 终会走过这条遥远的道路</div>
         
      </li>
      <li>
        	<img src="http://s.amazeui.org/media/i/demos/bing-4.jpg">
          <div class="am-slider-desc">OH PARA PARADISE 是否那么重要 你是否那么地遥远</div>
         
      </li>
  </ul>
</div>

<!--<div data-am-widget="list_news" class="am-list-news am-list-news-default" >
  <div class="am-list-news-bd">
     <ul class="am-list" id="wrapper"></ul>
     <ul class="am-list am-margin-top-0" id="recommendBooks"></ul>
  </div>
  </div>
  
--></fieldset>
</form>
  <#include "/inc/message.ftl">
  <#include "/inc/menu.ftl">
  
</body>
</html>
