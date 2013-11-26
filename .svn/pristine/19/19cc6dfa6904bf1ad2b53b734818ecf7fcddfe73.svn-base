$(document).ready(function(){
	navTag("系统管理&gt;素材信息");
	advClassConfig.load();
});
var advClassConfig={
	load : function(page){
		$("#AllCheck").attr("checked",false);

		var limit = base.getGlobalLimit();
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}

		_waiting._show();
		var d = {"page":page,"limit":limit};
		
		$.post("query",d,function(data,status){
			if(status){
				advClassConfig.bindGrid(data,limit);
			}else{
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
			_waiting._hide();
		},"json");
	},
	bindGrid : function(V,limit){
		var D = eval(V);
		var html = '';
		if(D.total > 0){
			$.each(D.source,function(i,obj){
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.advClassName+'</td><td>'+(obj.fdefinition=="HD"?"高清":"标清")+'</td><td>'+obj.fwidth+'</td><td>'+obj.fheight+'</td><td>'+obj.fsize+'</td><td>'+obj.fformat+'</td><td>'+(obj.ftime==0?"":obj.ftime)+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='9' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"advClassConfig.load",limit:limit,count:D.total});
	},
	edit : function(data,opt){
		var advs = data.config;
		if(!advs){//,'fbranchid':$("#fbranchid").val()
			advs={"fpositionid":"","fdefinition":'','id':'','fwidth':'','fheight':'','fsize':'','fformat':'',"ftime":'0.0'};
		}
		
		var selected = "selected='selected'";
		var option = "";
		$.each(data.advClass,function(i,a){
			option += "<option value='"+a.fpositionid+"' "+(a.fpositionid == advs.fpositionid?selected:'')+">"+a.ftype+"</option>";
		});
		var isNone = advs.fpositionid == 2 ? "" : "none";
		//空格
		var nbsp = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;";
		var html = '<form id="form1"><label for="fpositionid">广告位：</label><select name="fpositionid" id="fpositionid" onchange="advClassConfig.isVideo(this.value)">'+option+'</select>';
		html += '<br/><br/><label for="fdefinition">解析度：</label><select name="fdefinition" id="fdefinition"><option value="HD" '+(advs.fdefinition=="HD"?selected:"")+'>高清'+nbsp+'</option><option value="SD" '+(advs.fdefinition=="SD"?selected:"")+'>标清'+nbsp+'</option></select>';
		html += '<br/><br/><label for="fwidth">水平分辨率：</label><input name="fwidth" required="required" value="'+advs.fwidth+'" type="text" placeholder="请输入水平分辨率"/>px';
		html += '<br/><br/><label for="fheight">垂直分辨率：</label><input name="fheight" required="required" type="text" value="'+advs.fheight+'" placeholder="请输入垂直分辨率"/>px';
		html += '<br/><br/><label for="fsize">文件大小限制：</label><input name="fsize" required="required" type="text" value="'+advs.fsize+'" placeholder="请输入文件大小最大值"/>KB';
		html += '<br/><br/><label for="fformat">文件格式：</label><select name="fformat" id="fformat"><option value="jpg" '+(advs.fformat=="jpg"?selected:"")+'>图片文件JPEG</option><option value="mpg" '+(advs.fformat=="mpg"?selected:"")+'>视频文件MPEG2</option><option value="m2v" '+(advs.fformat=="m2v"?selected:"")+'>I帧</option></select>';
		html += '<div id="video" style="display:'+isNone+';"><br/><label for="ftime">视频时长：</label><input name="ftime" required="required" type="text" value="'+advs.ftime+'" placeholder="请输入时长"/>s</div>';
		html += '<input id="id" name="id" value="'+advs.id+'" type="hidden" /></form>';
		
		if(!opt) uri = "insert";
		else uri = "update";
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 450,
			height : 420,
			title : '新增广告位属性',
			body : '<div id="txt_advRelease_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin:20px;">'+html+'</div>',
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					if(!opt) uri = "insert";
					else uri = "update";
					advClassConfig.ajaxSubmit("form1",uri);
					kdialog.remove();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
	},
	ajaxSubmit:function(form,uri){

		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:uri,
			type:'post',
			dataType:'json',
			success:function(data){
				_waiting._hide();
				advClassConfig.load();
				$.jBox.tip("数据保存成功","success",{"timeout":1000});
			},
			error:function(msg){
				_waiting._hide();
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
		});
	},
	ajaxLoadById:function(opt){
		var id= base.selectFirst();
		
		_waiting._show();
		$.post("selectByKey",{"id":id},function(data,status){
			if(status){
				advClassConfig.edit(data,opt);
			}else{
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
			_waiting._hide();
		},"json");
	},
	deleteSource:function(){
		var id = base.selectValue();
		if(!id){
			$.jBox.tip("请选择要删除的数据","warning",{"top":"40%","timeout":1000});
			return false;
		}
		var submit = function(){
			_waiting._show();
			$.post("delete",{"id":id},function(data,status){
				if(status){
					advClassConfig.load();
					$.jBox.tip(data,"success",{"timeout":1000});
				}else{
					$.jBox.tip(data,"error",{"timeout":1000});
				}
				_waiting._hide();
			});
		};
		$.jBox.confirm("确定要删除数据吗？", "提示", submit);
	},
	isVideo : function(val){
		if(val == 2){
			$("#video").css("display","");
		}else{
			$("#video").css("display","none");
		}
	}
};