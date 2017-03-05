$(function() {
	var appId = $("#appId").val();
	var timestamp = $("#timestamp").val();
	var nonceStr = $("#nonceStr").val();
	var signature = $("#signature").val();
	var shareUrl = $("#shareUrl").val();
	var shareTitle = $("#shareTitle").val();
	var psContent = $("#psContent").text();
	console.log(psContent);
	wx.config({
		debug : false,
		appId : appId,
		timestamp : timestamp,
		nonceStr : nonceStr,
		signature : signature,
		jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
				'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo',
				'chooseImage', 'previewImage', 'uploadImage', 'downloadImage',
				'hideOptionMenu', 'showOptionMenu', 'hideMenuItems',
				'showMenuItems', 'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem', 'closeWindow' ]
	});

	wx.ready(function() {
		wx.hideAllNonBaseMenuItem();
		wx.showMenuItems({
			menuList : [ 'menuItem:share:appMessage', // 发送给朋友
			'menuItem:share:timeline', // 分享到朋友圈
			'menuItem:share:qq', // 分享到QQ
			'menuItem:share:QZone', // 分享到QQ空间
			'menuItem:share:weiboApp' // 分享到Weibo
			],
			success : function(res) {

			},
			fail : function(res) {
				showMsg("页面初始化出错:"+JSON.stringify(res));
			}
		});

		wx.onMenuShareAppMessage({
			title : shareTitle,
			desc : psContent,
			link : shareUrl,
			//imgUrl : '',
			trigger : function(res) {
				//showMsg('用户点击发送给朋友');
			},
			success : function(res) {
				updateScoreByEvent();
			},
			cancel : function(res) {
				//showMsg('已取消');
			},
			fail : function(res) {
				showMsg("分享失败:"+JSON.stringify(res));
			}
		});
		//showMsg('已注册获取“发送给朋友”状态事件');

		// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareTimeline({
			title : shareTitle,
			desc : psContent,
			link : shareUrl,
			//imgUrl : '',
			trigger : function(res) {
				//showMsg('用户点击分享到朋友圈');
			},
			success : function(res) {
				//showMsg('已分享');
				updateScoreByEvent();
			},
			cancel : function(res) {
				//showMsg('已取消');
			},
			fail : function(res) {
				showMsg("分享失败:"+JSON.stringify(res));
			}
		});
		//showMsg('已注册获取“分享到朋友圈”状态事件');

		// 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareQQ({
			title : shareTitle,
			desc : psContent,
			link : shareUrl,
			//imgUrl : '',
			trigger : function(res) {
				//showMsg('用户点击分享到QQ');
			},
			/*complete : function(res) {
				showMsg(JSON.stringify(res));
			},*/
			success : function(res) {
				updateScoreByEvent();
			},
			cancel : function(res) {
				//showMsg('已取消');
			},
			fail : function(res) {
				showMsg("分享失败:"+JSON.stringify(res));
			}
		});
		//showMsg('已注册获取“分享到 QQ”状态事件');
		wx.onMenuShareQZone({
			title : shareTitle,
			desc : psContent,
			link : shareUrl,
			//imgUrl : '',
			trigger : function(res) {
				//showMsg('用户点击分享到QQ');
			},
			complete : function(res) {
				//showMsg(JSON.stringify(res));
			},
			success : function(res) {
				//showMsg('已分享');
				updateScoreByEvent();
			},
			cancel : function(res) {
				//showMsg('已取消');
			},
			fail : function(res) {
				showMsg("分享失败:"+JSON.stringify(res));
			}
		});

		// 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareWeibo({
			title : shareTitle,
			desc : '',
			link : shareUrl,
			imgUrl : '',
			trigger : function(res) {
				//showMsg('用户点击分享到微博');
			},
			complete : function(res) {
				//showMsg(JSON.stringify(res));
			},
			success : function(res) {
				updateScoreByEvent();
				//showMsg('已分享');
			},
			cancel : function(res) {
				//showMsg('已取消');
			},
			fail : function(res) {
				showMsg("分享失败:"+JSON.stringify(res));
			}
		});
		//showMsg('已注册获取“分享到微博”状态事件');

	});
});

function updateScoreByEvent() {
	var psId = $("#psId").val();
	$.ajax({
		type : 'post',
		url : "updateScoreByEvent.do",
		async : false,
		data : {
			psId : psId,
			browseType : 1
		},
		dataType : 'json',
		cache : false,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			console.log(data);
			if (data == true) {
				showMsg("分享成功，积分已赠送，多次分享只计算一次积分。");
				console.log("更新积分成功！");
			} else {
				console.log("更新积分失败！");
			}
		},
		error : function(e) {
			console.log(e);
			showMsg("出错啦！");
		}
	});
}