<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head2.0.ftl">
  <title></title>
</head>
<body>
<header data-am-widget="header"
          class="am-header am-header-default">
      <div class="am-header-left am-header-nav">
          <a href="javascript:jump('account');" class="">

              <img class="am-header-icon-custom" src="data:image/svg+xml;charset&#x3D;utf-8,&lt;svg xmlns&#x3D;&quot;http://www.w3.org/2000/svg&quot; viewBox&#x3D;&quot;0 0 12 20&quot;&gt;&lt;path d&#x3D;&quot;M10,0l2,2l-8,8l8,8l-2,2L0,10L10,0z&quot; fill&#x3D;&quot;%23fff&quot;/&gt;&lt;/svg&gt;" alt=""/>
          </a>
      </div>

      <h1 class="am-header-title">
        	  成交记录查询
      </h1>

      <div class="am-header-right am-header-nav">
          <a class="">

              <img class="am-header-icon-custom" src="data:image/svg+xml;charset&#x3D;utf-8,&lt;svg xmlns&#x3D;&quot;http://www.w3.org/2000/svg&quot; viewBox&#x3D;&quot;0 0 42 26&quot; fill&#x3D;&quot;%23fff&quot;&gt;&lt;rect width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;1&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;rect y&#x3D;&quot;11&quot; width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;12&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;rect y&#x3D;&quot;22&quot; width&#x3D;&quot;4&quot; height&#x3D;&quot;4&quot;/&gt;&lt;rect x&#x3D;&quot;8&quot; y&#x3D;&quot;23&quot; width&#x3D;&quot;34&quot; height&#x3D;&quot;2&quot;/&gt;&lt;/svg&gt;" alt=""/>
          </a>
      </div>
  </header>
	<table id="example" class="display nowrap" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>id</th>
	                <th>单价</th>
	                <th>类型</th>
	                <th>数量</th>
	                <th>总额</th>
	                <th>状态</th>
	                <th>时间</th>
	            </tr>
	        </thead>
	 
	        <tfoot>
	            <tr>
	               <th>id</th>
	                <th>单价</th>
	                <th>类型</th>
	                <th>数量</th>
	                <th>总额</th>
	                <th>状态</th>
	                <th>时间</th>
	            </tr>
	        </tfoot>
	    </table>
   

  <#include "/inc/message.ftl">
  <#include "/inc/menu.ftl">
  <script type="text/javascript" src="<@s.url '/js/2.0/portal/transMainList.js?time=${.now}'/>"></script>
</body>
</html>
