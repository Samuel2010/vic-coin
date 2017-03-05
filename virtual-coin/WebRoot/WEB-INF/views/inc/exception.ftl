<!-- lang: html -->
<!doctype html>
<#import "/inc/spring.ftl" as s />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta name="viewport" content ="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no,target-densitydpi=device-dpi">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="telephone=no" name="format-detection" />
<link rel="stylesheet" type="text/css" href="<@s.url '/css/basics.css'/>">
<link rel="stylesheet" type="text/css" href="<@s.url '/css/y_pop_block.css'/>">
<link rel="stylesheet" type="text/css" href="<@s.url '/css/common.css'/>">
<script type="text/javascript" src="<@s.url '/js/jquery/jquery.min.js'/>"></script>
<script type="text/javascript" src="<@s.url '/js/common.js'/>"></script>

<title>幸福同行</title>
</head>
<body>
<H2>出错了，您可以
<a href="javascript:showErrors();">查看详情</a>或直接<a href="javascript:reback();">返回</a>
</H2>
<div id="errors" style="display:none;">${exception}</div>
</body>
</html>
