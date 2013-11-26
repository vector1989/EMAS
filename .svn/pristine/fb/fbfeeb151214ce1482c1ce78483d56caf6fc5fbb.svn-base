$(document).ready(function(){
	navTag("广告管理&gt;广告编辑");
	adv.load();
});
var adv={
	load : function(page){
		$("#AllCheck").attr("checked",false);

		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		
		_waiting._show();
		$.ajax({
			url:"query",
			type:"post",
			data:{"page":page,"limit":limit,"fchecked":$("#fchecked").val(),"fbranchid":$("#fbranchid").val()},
			dataType:"json",
			success:function(data){
				adv.bindGrid(data,limit);
				_waiting._hide();
			},
			error:function(data){
				_waiting._hide();
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
		});
	},
	bindGrid : function(V,limit){
		var D = eval(V);
		var html = '';
		if(D.total > 0){
			$.each(D.source,function(i,obj){// onclick="base.selectTd(\''+i+'\');"
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.id+'</td><td>'+obj.fpositionid+'</td><td>'+obj.ftype+'</td><td>'+(obj.fdefinition=="HD"?"高清":"标清")+'</td><td>'+(obj.fchecked==1?'<font color="green">已通过<blue>':obj.fchecked==0?'<font color="blue">未审核<blue>':'<font color="red">未通过</font>')+'</td><td>'+obj.queryKeyWord+'</td><td>'+obj.fcreatetime+'</td><td>'+obj.temp+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='9' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"adv.load",limit:limit,count:D.total});
	},
	edit : function(D,opt){
		var advs = D.adv;
		if(!advs){
			advs={"fpositionid":"","fdefinition":'','id':''};
		}
		var selected = "selected='selected'";
		var option = "";
		$.each(D.advClass,function(i,a){
			option += "<option class='"+a.id+"' value='"+a.fpositionid+"' "+(a.fpositionid == advs.fpositionid?selected:'')+">"+a.ftype+"</option>";
		});
		var branchsHtml = '';
		var fbranchid = $("#fbranchid").val();
		$.each(D.branchs,function(i,b){
			branchsHtml += '<option value="'+b.id+'" '+(fbranchid==b.id?selected : "")+'>'+b.fname+'</option>';
		});
		var html = '<form id="form1"><label for="fbranchid">分公司：</label><select name="fbranchid" id="fbranchid" onchange="adv.loadAdv(this.value);">'+branchsHtml+'</select>';
		html += '<br/><br/><label for="fpositionid">广告位：</label><select name="fpositionid" id="fpositionid">'+option+'</select>';
		html += '<br/><br/><label for="fdefinition">解析度：</label><select name="fdefinition" id="fdefinition"><option value="HD" '+(advs.fdefinition=="HD"?selected:"")+'>高清&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;</option><option value="SD" '+(advs.fdefinition=="SD"?selected:"")+'>标清&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;</option></select>';
		html += '<input id="id" name="id" value="'+advs.id+'" type="hidden" /></form>';
		if(!opt) uri = "insert";
		else uri = "update";
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 350,
			height : 250,
			title : '新增广告位',
			body : '<div id="txt_advRelease_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:20px;margin-left:20px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					if(!opt) uri = "insert";
					else uri = "update";
					adv.ajaxSubmit("form1",uri);
					kdialog.remove();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
	},
	ajaxSubmit:function(form,uri){
		var postObj = $("#fpositionid").find("option:selected");
		var ftype = postObj.text();
		var fadvclassid = postObj.attr("class");

		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:uri,
			data : {"ftype":ftype,"fadvclassid":fadvclassid},
			type:'post',
			dataType:'json',
			success:function(data){
				_waiting._hide();
				adv.load();
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
		$.ajax({
			url:"selectByKey",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				_waiting._hide();
				adv.edit(data,opt);
			},
			error:function(data){
				_waiting._hide();
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
		});
	},
	deleteSource:function(){
		var id = base.selectValue();
		if(!id){
			$.jBox.tip("请选择要删除的数据","warning",{"top":"40%","timeout":1000});
			return false;
		}
		var submit = function(){
			_waiting._show();

			$.ajax({
				url:"delete",
				type:"post",
				data:{"id":id},
				success:function(data){
					_waiting._hide();
					adv.load();
					$.jBox.tip(data,"success",{"timeout":1000});
				},
				error:function(data){
					_waiting._hide();
					$.jBox.tip(data,"error",{"timeout":1000});
				}
			});
		};
		$.jBox.confirm("确定要删除数据吗？", "提示", submit);
	},
	checked:function(check){
		var id = base.selectValue();
		if(!id){
//			alert("请选择要审核的数据");
			$.jBox.tip("请选择要审核的数据","warning",{"top":"40%","timeout":1000});
			return false;
		}
		_waiting._show();

		
		$.post("checked",{"ids":id,"fchecked":check},function(data,status){
			if(status){
				_waiting._hide();

				$.jBox.tip(data+'条数据审核成功',"success",{"timeout":1000});
				adv.load();
			}else{
				_waiting._hide();
				$.jBox.tip("数据加载失败","error",{"timeout":1000});

			}
		},"text");
	},
	loadAdv : function(branchid){

		$.post("loadBranchAdvclass",{"branchid":branchid},function(data,status){
			$.jBox.closeTip();
			if(status){
				var html = '';
				$.each(data,function(i,a){
					html += "<option class='"+a.id+"' value='"+a.fpositionid+"'>"+a.ftype+"</option>";
				});
				$("#fpositionid").html(html);
			}else{

				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
		},"json");
	}
};