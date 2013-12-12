$(document).ready(function(){
	broadcast.load();
});
var broadcast={
	load : function(page){
		_waiting._show();
		$.ajax({
			url:"query",
			type:"post",
			data:{"fbranchid":$("#fbranchid").val()},
			dataType:"json",
			success:function(data){
				var basePath = _waiting.getRootPath();
				if(data == null){
					$.jBox.tip("暂无分公司数据库配置信息,请与管理员联系","info");
					$("#status").text("暂无数据库配置相关信息，请与管理员联系");
					$("#pauseImg").attr("src","");
					$("#playImg").attr("src","");
				}else if(data.fplaystatus == 1){
					$("#pause").removeAttr("onclick");
					$("#pauseImg").attr("src",basePath+"/images/pause_focus.png");
					$("#play").attr("onclick","broadcast.edit(0)");
					$("#playImg").attr("src",basePath+"/images/play.png");
					$("#status").text("暂停播出");
				}else{
					$("#pause").attr("onclick","broadcast.edit(1)");
					$("#pauseImg").attr("src",basePath+"/images/pause.png");
					$("#play").removeAttr("onclick");
					$("#playImg").attr("src",basePath+"/images/play_focus.png");
					$("#status").text("正常播出");
				}
				_waiting._hide();
			},
			error:function(data){
				_waiting._hide();
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
		});
	},
	edit : function(status){
		$.jBox.confirm("确定修改播出服务器状态？", "温馨提示", 
			function (v, h, f) {
			    if (v == 'ok')
			    	broadcast.ajaxSubmit(status);
			    return true; //close
			});
	},
	ajaxSubmit:function(status){
		
		_waiting._show();
		$.ajax({
			url:"update",
			type:'post',
			data:{"fbranchid":$("#fbranchid").val(),"fplaystatus":status},
			dataType:'json',
			success:function(data){
				_waiting._hide();
				broadcast.load();
				$.jBox.tip("数据修改成功","success",{"timeout":1000});
			},
			error:function(msg){
				_waiting._hide();

				$.jBox.tip("数据修改失败","error",{"timeout":1000});
			}
		});
	}
};