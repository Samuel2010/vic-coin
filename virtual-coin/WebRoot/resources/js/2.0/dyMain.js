$(document).ready(function() { 
	$("#psTitle").autocomplete("queryAllPosts.do",{
		minChars: 0,
		max: 10,
		autoFill: false,
		mustMatch: false,
		matchContains: true,
		scrollHeight: 220,
		//dataType:'json',
		parse : function(data) {
			if(data != null){
				var parsed = [];
				var rows = eval("("+data+")");
				var result =  rows.result;
				for (var i=0; i < result.length; i++) {
					parsed[parsed.length] = {
						data: result[i],
						value: result[i].psTitle,
						result: result[i].psTitle
					};
				}
				return parsed;
			}
			
		},
		extraParams:{searchValue:
			function(){return $("#psTitle").val();},secId : '36'},
		formatItem: function(row, i, total) {
			var psIsDoing = row.psIsDoing;
			//console.log(row);
			console.log(row.psTitle);
			if(psIsDoing =="1"){
				return row.psTitle+"<strong style='color:#0CBA3A'>[已有正式答复]<strong>";
			}else{
				return row.psTitle;
			}
			
		},
		formatMatch: function(row, i, total) {
			//alert(row.psTitle);
			//console.log(row.psTitle);
			return row.psTitle;
		}/*,
		formatResult: function(row) {
			return row.psTitle;
		}*/
	});
 
	function findValueCallback(event, data, formatted) {
		jump("cktwPage","secId="+$("#secId").val()+"&psId="+data.psId);
		//alert("<strong>"+(!data ? "No match!" : "Selected: " + formatted)+"</strong>");
	}
	$("#psTitle").result(findValueCallback);
	//$("#getvalue").click(function() {$("#website").search()});
});

function saveNote(action){
    var formData = new FormData($("#addNoteForm")[0]);
    saveLoading("saveBtn");
    $.ajax({
        type:'post',  
        url : action, 
        data: formData,  
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        dataType:'json',
        success : function(data) {
            if(data.status == "1")  
            {
               showMsg("问题发布成功。", 'refresh');
            }else{
               showMsg("操作失败！错误信息[" + data.errorMsg + "]");
            }
            saveLoadComplete("saveBtn");
        },  
        error : function(e){
            showMsg("操作失败！"+e);
            saveLoadComplete("saveBtn");
        }
    });
}