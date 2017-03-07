<#import "/inc/spring.ftl" as s />
<!doctype html>
<html class="no-js">
<head>
  <#include "/inc/head.ftl">
  <title></title>
</head>
<body>
    <script type="text/javascript" src="<@s.url '/js/2.0/echarts-all.js'/>"></script>
    <form name="addNoteForm" action="addNote.do" method="POST" class="am-form" data-am-validator>
<!--<div data-am-widget="titlebar" class=" am-titlebar-multi" >
    <h2 class="am-titlebar-title am-margin-bottom-0">
        U币交易
    </h2>
  
</div>-->
<div class="am-panel am-panel-secondary am-margin-bottom-xs">
	<div id="transPanel" style="height:250px;">
  </div>
</div>
  <div data-am-widget="tabs" class="am-tabs am-tabs-d2" >
      <ul class="am-tabs-nav am-cf">
          <li class="am-active"><a href="[data-tab-panel-0]">默认</a></li>
          <li class=""><a href="[data-tab-panel-1]">买入</a></li>
          <li class=""><a href="[data-tab-panel-2]">卖出</a></li>
      </ul>
      <div class="am-tabs-bd">
          <div data-tab-panel-0 class="am-tab-panel am-active">
          <table class="am-table ">
		    <thead>
		        <tr>
		            <th>买/卖</th>
		            <th>价格</th>
		            <th>数量</th>
		            <th>总额</th>
		        </tr>
		    </thead>
		    <tbody class="am-text-danger">
		        <tr>
		            <td>买8</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买7</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买6</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买5</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买4</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买3</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买2</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买1</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		    </tbody>
		</table>
          </div>
          <div data-tab-panel-1 class="am-tab-panel ">
            <table class="am-table ">
            <thead>
		        <tr>
		            <th>买/卖</th>
		            <th>价格</th>
		            <th>数量</th>
		            <th>总额</th>
		        </tr>
		    </thead>
           <tbody class="am-text-danger">
		        <tr>
		            <td>买8</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买7</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买6</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买5</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买4</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买3</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买2</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>买1</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		    </tbody>
		</table>
          </div>
          <div data-tab-panel-2 class="am-tab-panel ">
          <table class="am-table ">
            <thead>
		        <tr>
		            <th>买/卖</th>
		            <th>价格</th>
		            <th>数量</th>
		            <th>总额</th>
		        </tr>
		    </thead>
           <tbody class="am-text-success">
		        <tr>
		            <td>卖8</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>卖7</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>卖6</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>卖5</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>卖4</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>卖3</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>卖2</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		        <tr>
		            <td>卖1</td>
		            <td>1.258</td>
		            <td>3512</td>
		            <td>4123.25</td>
		        </tr>
		    </tbody>
		</table>
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
  <script>
        var myChart = echarts.init(document.getElementById('transPanel'));
        var option = {
    title : {
        text: 'U币指数'
    },
    tooltip : {
        trigger: 'axis',
        formatter: function (params) {
            var res = params[0].seriesName + ' ' + params[0].name;
            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
            return res;
        }
    },
    //legend: {
    //    data:['U币指数']
    //},
    toolbox: {
        show : true,
        feature : {
            mark : {show: false},
            dataZoom : {show: true},
            dataView : {show: false, readOnly: false},
            magicType: {show: false, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    dataZoom : {
        show : true,
        realtime: true,
        start : 50,
        end : 100
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : true,
            axisTick: {onGap:false},
            splitLine: {show:false},
            data : [
                "2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
                "2013/1/31", "2013/2/1", "2013/2/4", "2013/2/5", "2013/2/6", 
                "2013/2/7", "2013/2/8", "2013/2/18", "2013/2/19", "2013/2/20", 
                "2013/2/21", "2013/2/22", "2013/2/25", "2013/2/26", "2013/2/27"
                
            ]
        }
    ],
    yAxis : [
        {
            type : 'value',
            scale:true,
            boundaryGap: [0.01, 0.01]
        }
    ],
    series : [
        {
            name:'U币指数',
            type:'k',
            data:[ // 开盘，收盘，最低，最高
                [2320.26,2302.6,2287.3,2362.94],
                [2300,2291.3,2288.26,2308.38],
                [2295.35,2346.5,2295.35,2346.92],
                [2347.22,2358.98,2337.35,2363.8],
                [2360.75,2382.48,2347.89,2383.76],
                [2383.43,2385.42,2371.23,2391.82],
                [2377.41,2419.02,2369.57,2421.15],
                [2425.92,2428.15,2417.58,2440.38],
                [2411,2433.13,2403.3,2437.42],
                [2432.68,2434.48,2427.7,2441.73],
                [2430.69,2418.53,2394.22,2433.89],
                [2416.62,2432.4,2414.4,2443.03],
                [2441.91,2421.56,2415.43,2444.8],
                [2420.26,2382.91,2373.53,2427.07],
                [2383.49,2397.18,2370.61,2397.94],
                [2378.82,2325.95,2309.17,2378.82],
                [2322.94,2314.16,2308.76,2330.88],
                [2320.62,2325.82,2315.01,2338.78],
                [2313.74,2293.34,2289.89,2340.71],
                [2297.77,2313.22,2292.03,2324.63]
                
            ]
        }
    ]
};
                    
			                    
        myChart.setOption(option);
   </script>
</body>
</html>
