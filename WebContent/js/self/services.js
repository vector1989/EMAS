$(document).ready(function(){
	navTag("广告管理&gt;业务编辑");
	service.load(1);
});
var service={
	load : function(page){
		$("#AllCheck").attr("checked",false);
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		$.ajax({
			url:"query",
			type:"post",
			data:{"page":page,"limit":limit},
			dataType:"json",
			success:function(data){
				service.bindGrid(data,limit);
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
			}
		});
	},
	bindGrid : function(V,limit){
		var D = eval(V);
		var html = '';
		if(D.total > 0){
			$.each(D.source,function(i,obj){
				// title="双击查看业务时间" ondblclick="service.loadTime(\''+obj.ftimeperiodid+'\');
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'""><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.fname+'</td><td>'+obj.fserviceid+'</td><td>'+obj.temp+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='5' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"service.load",limit:limit,count:D.total});
	},
	edit : function(D,uri){
		if(!D){
			D = {"fname":"","fserviceid":"","id":"","fchannelid":""};
		}
		var kdialog = null;
		var html = '<form id="timeForm"><label for="fname">业务名称：</label><input id="fname" name="fname" value="'+D.fname+'" required="required" type="text" />';
		html += '<br/><br/><label for="fserviceid">业务号：</label><input id="fserviceid" name="fserviceid" value="'+D.fserviceid+'" required="required" type="text" />';
		html += '<br/><br/><label for="fchannelid">选择频道：</label><select name="fchannelid" id="dialog_fchannelid"></select>';
		html += '<input id="id" name="id" value="'+D.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 350,
			height : 250,
			title : '频道编辑',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					if(!uri)
						uri = "insert";
					service.ajaxSubmit("timeForm",uri);
					kdialog.remove();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
		this.ajaxLoadChannels(D.ftimeperiodid);
	},
	ajaxSubmit:function(form,uri){
		$("#"+form).ajaxSubmit({
			url:uri,
			type:'post',
			dataType:'json',
			success:function(data){
				service.load();
				$.jBox.tip('数据保存成功','success');
			},
			error:function(msg){
				$.jBox.tip('数据保存失败','error'); 
			}
		});
	},
	ajaxLoadById:function(){
		var id= base.selectFirst();
		$.ajax({
			url:"selectByKey",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				service.edit(data,"update");
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
			}
		});
	},
	deleteSource:function(){
		var id = base.selectValue();
		if(!id){
			alert("请选择要删除的数据");
			return false;
		}
		$.ajax({
			url:"delete",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				$.jBox.tip('数据删除成功','success');
				service.load();
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
			}
		});
	},
	/*loadTime:function(id){
		$.ajax({
			url:"../time/selectByKey",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(D){
				var kd = null;
				var html = '<form id="timeForm"><label for="fstarttime">开始时间：</label><input id="fstarttime" name="fstarttime" value="'+D.fstarttime+'" required="required" type="text" class="Wdate" onClick="WdatePicker({dateFmt:\'H:mm\'})" placeholder="请输入开始时间"/>';
				html += '<br/><br/><label for="fendtime">结束时间：</label><input id="fendtime" name="fendtime" value="'+D.fendtime+'" required="required" type="text" class="Wdate" onClick="WdatePicker({dateFmt:\'H:mm\'})" placeholder="请输入结束时间"/>';
				kd = KindEditor.dialog({
					width : 350,
					height : 200,
					title : '业务时间',
					body : '<div id="tips_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
					shadowMode : true,
					closeBtn : {name : '关闭',click : function(e) {kd.remove();}},
					yesBtn : {name : '关闭',click : function(e) {kd.remove();}}
				});
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
			}
		});
	},*/
	ajaxLoadChannels:function(fchannelid){
		var selected = "selected='selected'";
		$.ajax({
			url:"../channels/selectAll",
			type:"post",
			dataType:"json",
			success:function(data){
				$.each(data,function(i,obj){
					$("#dialog_fchannelid").append("<option value='"+obj.id+"' "+(obj.id == fchannelid ? selected : '')+">"+obj.fname+"</option>");
				});
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
			}
		});
	}
};