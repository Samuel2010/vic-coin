$(document).ready(function() {
	var table = $('#example').DataTable( {
		"responsive": true,
		"language": tabLanguage,
        autoWidth: false,   //禁用自动调整列宽
        stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
        order: [],          //取消默认排序查询,否则复选框一列会出现小箭头
        processing: true,  //隐藏加载提示,自行处理
        serverSide: true,   //启用服务器端分页
        searching: false,    //禁用原生搜索
        "info": true,
        lengthChange:false,
        "jQueryUI": true,
        "dom": '<"H"fr <"toolbar">> t <"F"ip >',//规则定义参考http://datatables.club/reference/option/dom.html
		"ajax": {
            "url": "trans/getTransListData",
            "type": "POST",
            "data":{
            	transType:function(){
            		return $("#transType").val();
            	},
            	transSts:function(){
            		return $("#transSts").val();
            	}
            }
        },
        "columns": [
            { "data": "transId" },
            { "data": "transUnitMoney" },
            { "data": "transType" },
            { "data": "transNum" },
            { "data": "transMoney" },
            { "data": "transSts" },
            { "data": "transTimeString" }
        ],
        "columnDefs": [
               	{
               	    "render": function(data, type, row) {
               	    	if(data == 1){
               	    		return '买入';
               	    	}else{
               	    		return '卖出';
               	    	}
               	        
               	    },
               	    "targets": 2
               	},{
               	    "render": function(data, type, row) {
           	    	if(data == 1){
           	    		return '交易中';
           	    	}else{
           	    		return '已成交';
           	    	}
           	        
           	    },
           	    "targets": 5
             	},{
                    "visible": false,
                    "targets": [0]//设置第一列隐藏
                }
          ]
    } );
	
	$('#example tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            var selectRow = table.row($(this)).data();
            if(selectRow != undefined){
            	console.log(selectRow.tid);//tid为字段名
            }
            
        }
    } );
	
	$("div.toolbar").html('<b style="color:red">自定义文字、图片等等</b>');//<button class="am-btn am-btn-secondary" type="button">新增</button>
	
	/**
	 var column = table.column(0);
	 column.visible(false); //动态隐藏列
	 */
 
    /*$('#button').click( function () {
        table.row('.selected').remove().draw( false );
    } );*/
	
	$('#btnQry').click( function () {
		table.draw();
	});

});
