/**
 * source共用
 */
var source={
	createTree : function(){
		var basePath = _waiting.getRootPath();
		var rv = $("#freleaseversionid").find("option:selected");
		
		this.isFinishEdit(rv.attr("label"));
		
		_waiting._show();
		$.ajax({
			url:basePath+"/main/time/query?"+$("#queryForm").serialize(),
			type:"post",
			data:{"id":rv.val()},//"fbranchid":$("#fbranchid").val(),"fdefinition":$("#fdefinition").val(),
			dataType:"json",
			success:function(data){
				$.fn.zTree.init($("#tree"), setting, data);
				$("#tree_1_span").click();
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败',"error"); 
				_waiting._hide();
			}
		});
	},
	queryByExample : function(page,node){
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		if(!node)
			node = base.getSelectedTreeNode();
		if(!node){
			return false;
		}
		var params = {};
		if(!node.root){
			params.fadvid = node.advid;
			if(!node.isParent){
				params.ftimeperiodid = node.timeperiodid;
			}
		}
		var rvid = node.rvid;	

		if(!rvid){
			rvid = $("#freleaseversionid").val();
		}
		params.page = page;
		params.limit = limit;
		params.freleaseversionid = rvid;
//		params.rvid = rvid;
		

		var basePath = _waiting.getRootPath();
		_waiting._show();
		$.ajax({
			url:basePath+"/main/source/queryByAdvIdAndTimepreiodId?"+$("#queryForm").serialize(),
			type:"post",
			data:params,
			dataType:"json",
			success:function(data){
				source.bindGridAdvTimeperiodResource(data,limit);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败',"error"); 
				_waiting._hide();
			}
		});
	},
	bindGridAdvTimeperiodResource : function(data,limit){
		var height = $("#data").css("height");
		var html = "";
		var iw = 180, ih = 120;
		if(data.total > 0){
			$.each(data.source,function(i,s){
				var definition = s.fdefinition == "HD" ? "高清" : "标清";
				var cname = s.fname;
				//流程
				var nodestatus = '[<a href="../nodeStatus/queryContractAdvResourceStatus?id='+s.carid+'" target="_black">流程</a>]';
				var cgroups = "";
				if(s.ffontcolor){
					cgroups += "[<span class='cgroups'>"+s.ffontcolor+"</span>]";
				}
				cname = cname.length > 9 ? cname.substring(0,9):cname;
				html += '<div class="img_div" id="tr'+i+'" title="素材名称：'+s.fname+'" style="width:220px;" onclick="check.divCk('+i+',\'img_div\')">';
				var p = base.replaceEndwidth(s.fpath);
				html += '<div class="imgDiv">'+preview.playHtml(p,s.fname,s.fwidth,s.fheight,iw,ih)+'</div>';
				html += '<div class="caption"><input type="radio" name="checkbox" id="checkbox'+i+'" isSource="1" onchange="check.divCk('+i+',\'img_div\')" value="'+s.id+'" vsid="'+s.vsid+'" fchannelsid="'+(s.fexpand==undefined?"":s.fexpand)+'">';
				if(s.tid)
					html += ('时间段：'+s.fstarttime+'-'+s.fendtime);
				html += nodestatus;
				html +='<br/>素材名称：'+cname+'</br>广告位：'+s.ftype+'[<font color="green" >'+definition+'</font>]';
				html += cgroups +'</div></div>';
			});
			html += '<div class="clear"></div>';
		}else{
			var status = $("#fstatus").find("option:selected").text();
			html = "暂无您查询<font color='red'>"+status+"</font>版本的广告记录";
		}
		$("#dataGrid").html(html);
		
		$("#data").css("height",height);
		
		$("#pager").pager({ pagenumber: data.page, pagecount: data.totalPage, buttonClickCallback: this.queryByExample, limitFun:"source.queryByExample",limit:limit,count:data.total});
	},
	loadBranchRelease : function(){
		var s = $("#fstatus").val();
		if(s != 2){
			$("#tools").css("display","");
			if(s == 0)
				$("#versiondesc").css("display","none");
		}else{
			$("#tools").css("display","none");
			$("#versiondesc").css("display","");
		}
		var basePath = _waiting.getRootPath();
		$.ajax({
			url:basePath+"/main/source/loadReleaseVersion",
			type:"post",
			data:{"fbranchid":$("#fbranchid").val(),"fdefinition":$("#fdefinition").val(),"fstatus":s},
			dataType:"json",
			success:function(data){
				var rvHtml = "";
				$.each(data,function(i,d){
					var o = d.fdesc +"[sep]"+ d.fversion +"[sep]"+ d.fcreatetime +"[sep]" + d.fstatus;
					rvHtml += "<option value='"+d.id+"' label='"+d.fisfinishededit+"' desc='"+o+"'>"+d.fversion+"</option>";
				});
				$("#freleaseversionid").html(rvHtml);
				source.createTree();
			},
			error:function(data){
				$.jBox.tip('数据加载失败',"error"); 
			}
		});
	},
	showVersionDesc : function(){
		var desc = $('#freleaseversionid').find("option:selected").attr("desc");
		if(desc){
			var sep = desc.split("[sep]");
			var status = sep[3];
			var s = "";
			if(status == 1){
				s = "发布成功";
			}else{
				s = "审核未通过";
			}
			var html = "<div style='padding:5px;'><br><b>版本号：</b>"+sep[1] + "<br><br><b>状态：</b>" + s + "<br><br><b>版本创建时间：</b>"+sep[2] + "<br><br><b>版本描述：</b>"+sep[0] + "<br><br></div>";
			$.jBox(html,{"title":"版本描述信息","width":700});
		}else{
			var status = $("#fstatus").find("option:selected").text();
			$.jBox.tip("暂无"+status+"的版本信息","info");
		}
	},
	gotoStatus : function(){
		var rvid = $("#freleaseversionid").val();
		if(rvid){
			var url = '../nodeStatus/queryContractAdvResourceStatus?type=2&rvid='+rvid+'&bid='+$("#fbranchid").val();
			window.open(url,featurse="_blank");
		}else{
			var status = $("#fstatus").find("option:selected").text();
			$.jBox.tip("暂无"+status+"的版本信息","info");
		}
	},
	selectChannels : function(){
		var id = base.selectFirst();
		var fchannelsid = $("input[name='checkbox']:checked").attr("fchannelsid");
		if(id){
			if(fchannelsid){
				var basePath = _waiting.getRootPath();
				$.post(basePath+"/main/source/loadChannels",{"fchannelsid":fchannelsid},function(data,status){
					if(status){
						var html = "<div><div><b>频道总数：</b>"+fchannelsid.split(',').length+"</div>";
						$.each(data,function(i,c){
							html += "<div style='float : left;width:120px;'><input type='hidden' tsid='"+c.ftsid+"' onid='"+c.fonid+"' name='cid' value='"+c.id+"'/>"+c.fname+"</div>";
						});
						html += "<div style='clear:both;'></div></div>";
						var kdialog=null;
						kdialog = KindEditor.dialog({
							width : 530,
							height : 400,
							title : '查看频道信息',
							body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:1px;margin-left:30px;">'+html+'</div>',
							shadowMode : true,
							closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
							noBtn : {name : '关闭',click : function(e) {kdialog.remove();}}
						});
					}else{
						$.jBox.tip('数据加载出错',"error");
					}
				},"json");
			}else{
				$.jBox.tip('该广告无频道相关信息',"info");
			}
		}else{
			$.jBox.tip('请选择广告信息',"info");
		}
	},
	isFinishEdit : function(label){
		var status = $("#fstatus").val();
		if(status != "2" && (label == "1" || label == "4" || label == "5")){
			$("#releaseA").css("display","");
		}else{
			$("#releaseA").css("display","none");
		}
		if(label == "0"){
			$("#edit").css("display","");
			$("#rvNode").css("display","none");
		}else{
			$("#edit").css("display","none");
			$("#rvNode").css("display","");
		}
	}
};