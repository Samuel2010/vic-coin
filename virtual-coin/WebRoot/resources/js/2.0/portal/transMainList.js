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
        "info": false,
        lengthChange:false,
        "jQueryUI": true,
        "dom": '<"H"fr <"toolbar">> t <"F"ip >',//规则定义参考http://datatables.club/reference/option/dom.html
		"ajax": {
            "url": "trans/getTransListData",
            "type": "POST"
        },
        "columns": [
            { "data": "id" },
            { "data": "transUnitMoney" },
            { "data": "transType" },
            { "data": "transNum" },
            { "data": "transMoney" },
            { "data": "transSts" },
            { "data": "transTime" }
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
 
    $('#button').click( function () {
        table.row('.selected').remove().draw( false );
    } );

});


/*常量*/
var CONSTANT = {
        DATA_TABLES : {
            DEFAULT_OPTION : { //DataTables初始化选项
                language: {
                    "sProcessing":   "处理中...",
                    "sLengthMenu":   "每页 _MENU_ 项",
                    "sZeroRecords":  "没有匹配结果",
                    "sInfo":         "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
                    "sInfoEmpty":    "当前显示第 0 至 0 项，共 0 项",
                    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                    "sInfoPostFix":  "",
                    "sSearch":       "搜索:",
                    "sUrl":          "",
                    "sEmptyTable":     "表中数据为空",
                    "sLoadingRecords": "载入中...",
                    "sInfoThousands":  ",",
                    "oPaginate": {
                        "sFirst":    "首页",
                        "sPrevious": "上页",
                        "sNext":     "下页",
                        "sLast":     "末页",
                        "sJump":     "跳转"
                    },
                    "oAria": {
                        "sSortAscending":  ": 以升序排列此列",
                        "sSortDescending": ": 以降序排列此列"
                    }
                },
                autoWidth: false,   //禁用自动调整列宽
                stripeClasses: ["odd", "even"],//为奇偶行加上样式，兼容不支持CSS伪类的场合
                order: [],          //取消默认排序查询,否则复选框一列会出现小箭头
                processing: false,  //隐藏加载提示,自行处理
                serverSide: true,   //启用服务器端分页
                searching: false    //禁用原生搜索
            },
            COLUMN: {
                CHECKBOX: { //复选框单元格
                    className: "td-checkbox",
                    orderable: false,
                    width: "30px",
                    data: null,
                    render: function (data, type, row, meta) {
                        return '<input type="checkbox" class="iCheck">';
                    }
                }
            },
            RENDER: {   //常用render可以抽取出来，如日期时间、头像等
                ELLIPSIS: function (data, type, row, meta) {
                    data = data||"";
                    return '<span title="' + data + '">' + data + '</span>';
                }
            }
        }
};