var setting = {
	view: {
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		dblClickExpand: false,
		fontCss: getFont,
		nameIsHTML: true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback: {
		beforeClick : beforeClick,
		onClick: onClick
	}
};
function beforeClick(treeId, treeNode, clickFlag) {
	$("#showChannel").css("display","none");
	$("#updateTime").css("display","none");
	if(!treeNode.root && !treeNode.isParent){
		$("#updateTime").css("display","");
	}
	var pos = treeNode.positionid;
	if(pos == 4){
		$("#showChannel").css("display","");
	}else if(pos == 1 || pos == 2 || pos == 9 || (!treeNode.disabled && pos == 3)){
		$("#updateTime").css("display","none");
	}
	
	return true;
}
/**
 * 
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag
 */
function onClick(event, treeId, treeNode, clickFlag) {
	source.queryByExample(1,treeNode);
}
function getFont(treeId, node) {
	var font = {'font-weight':'bold',"color":"red"};
	if(node.disabled){
		font = {};
	}
	if(node.root){
		node.icon = "../../images/1_open.png";
		font = {'font-weight':'bold','color':'blue',"font-size":"20px"};
	}
	return font;
}
var sourceEdit={
	
	edit : function(D,opt,fbranchid,treeNode,vsid){
		var s = D.source;
		var playHtmlStr = "";
		if(opt == 1){
			var p = base.replaceEndwidth(s.fpath);
			playHtmlStr = preview.playHtml(p,s.fname, s.fwidth,s.fheight,300, 160);
		}else{
			s={"fpositionid":"","fdefinition":'','fadvid':treeNode.timeperiodid,'id':'',"fpath":'',"fchannelsid":"","temp":"","fversion":"1","time":"1"};
		}
		s.time = 1;
		var spanHtml = "";
		// 预览html
		var html = '<form id="form1"><style>label{width:65px;}</style>';
		html += '<table width="100%">';
		html += '<tr><td colspan="3"><label for="fversion">版本号：</label><input name="fversion" id="fversion" value="'+s.fversion+'"></td></tr>';
		html += '<tbody id="listbody" style="display:none;"><tr><td colspan="3"><label for="time">循环次数：</label><input name="time" style="width:140px;" id="time" value="'+s.time+'"></td></tr></tbody>';
		html += '<tbody id="channelbody" style="display:none;"><tr><td colspan="3"><label for="fchannelsidLevel">频道分组：</label><span id="cgroups"></span></td></tr>';
		html += '<tr><td rowspan="2"><label for="fchannelsid">频道：</label><select style="width:140px;" name="fchannels" ondblclick="sourceEdit.appendChannels(this);sourceEdit.cal()" id="fchannels" multiple="multiple" size="9">'+""+'</select></td>';
		html += '<td height="90" width="100"><a title="点击全选" onclick="sourceEdit.selectAllFchannels();sourceEdit.cal()" href="javascript:void(0)"><b>&gt;&gt;</b></a>';
		html += '<br/><br/><a title="点击选择选中" onclick="sourceEdit.selectSelectedFchannels();sourceEdit.cal()" href="javascript:void(0)"><b>&nbsp;&gt;</b></a></td>';
		html += '<td rowspan="2"><select style="width:140px;" multiple="multiple" size="9" name="fchannelsid" ondblclick="sourceEdit.delChannels(this);sourceEdit.cal()" id="fchannelsid"></select></td></tr>';
		html += '<tr><td><a title="清空已选" onclick="sourceEdit.cancelSelectedFchannels();sourceEdit.cal()" href="javascript:void(0)"><b>&nbsp;&lt;</b></a>';
		html += '<br/><br/><a title="全部清空" onclick="sourceEdit.cancelFchannels();sourceEdit.cal()" href="javascript:void(0)"><b>&lt;&lt;</b></a></td></tr>';
		html += '<tr><td colspan="3"><label>已选频道:</label><span id="selectCount"></span></td></tr></tbody>';
		html += '<tbody id="timessetbody" style="display:none;"><tr><td colspan="3"><fieldset><legend style="font-size:13px;">时长设置</legend><div id="timesSet"></div></fieldset></td></tr></tbody>';
		html += '<tr><td colspan="3"><label for="fresourceid">素材：</label><input name="fsourceid" onclick="sourceEdit.showResource(1,'+treeNode.advid+');" type="button" value="浏览..."/><span id="sourcediv" style="word-break:break-all;">'+spanHtml+'</span></td></tr>';
		html += '<tr><td colspan="3" width="330" height="150" id="playHtml" style="font-size:13px;text-align:center;">'+playHtmlStr+'</td></tr>';
		html += '</table>';
		html += '<input id="id" name="id" value="'+s.id+'" type="hidden" /></form>';
		
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 650,
			height : 510,
			title : '新增广告',
			body : '<div id="txt_source_div" style="padding:0px;overflow:auto;overflow-x:hidden;margin:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					sourceEdit.ajaxSubmit("form1","insert",kdialog,opt,treeNode,vsid);
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
		sourceEdit.showHide(s,opt,treeNode);
		this.cal();
	},
	cal : function(){
		var count = $("#fchannelsid").find("option").length;
		$("#selectCount").html(count);
	},
	checkChannelGroupIsUsing : function(obj){
		//获取树选中节点
		var cgs = $(".cgroups").text();
		for(var i=0,len=cgs.length;i<len;i++){
			if(cgs.charAt(i) == obj.value){
				$.jBox.tip("该分组已编辑，不能在添加编辑","info");
				obj.checked =false;
				return;
			}
		}
		this.changeFchannels(obj.value);
		this.cal();
	},
	/**
	 * 查询频道和时间段信息
	 * 
	 * @param cmd
	 *            参数，1：时间段，2：频道，3：时间段和频道
	 */
	editLoadTimeperoidOrChannel : function(cmd,s,treeNode){
		// 加载分公司频道信息
		if(!s) s={"id":"","ftimeperiodid":"","fadvid":treeNode.advid};
		$.post("editLoadTimeperoidOrChannel",{"fbranchid":$("#fbranchid").val(),"cmd":cmd,"fadvid":s.fadvid,"id":s.id},function(data,status){
			if(status){
				var cgroup = data.cgroups;
				if(cgroup){
					var cgroups = "";
					$.each(cgroup,function(i,g){
						cgroups += g.fname+'<input name="flevel" type="radio" attr="'+g.fisprovincecompany+'" onclick="sourceEdit.checkChannelGroupIsUsing(this);" cgroupsid="'+g.id+'" value="'+g.fname+'">';
					});
					$("#cgroups").html(cgroups);
				}
				var html = "";
				var channel = data.channel;
				if(channel){
					$.each(channel,function(i,a){
						html += "<option class='"+a.id+"' _level='"+a.flevel+"' value='"+a.id+"'>"+a.fname+"</option>";;
					});
				}
				$("#fchannels").html(html);
				var userChannel = "";
				var useChannel = data.userChannels;
				if(useChannel){
					$.each(useChannel,function(i,a){
						userChannel += "<option class='"+a.id+"' _level='"+a.flevel+"' value='"+a.id+"'>"+a.fname+"</option>";;
					});
					$("#fchannelsid").html(userChannel);
				}
			}else{
				$.jBox.tip('数据加载失败','error');
			}
		},"json");
	},
	hide : function(){
		$("#channelbody").css("display","none");
		$("#timebody").css("display","none");
		$("#listbody").css("display","none");
		$("#timessetbody").css("display","none");
	},
	clearDiv : function(){
//		$("#ftimeperiodid").html("");
		$("#fchannels").html("");
		$("#fchannelsid").html("");
		$("#sourcediv").html("");
		$("#playHtml").html("");
		$("#timesSet").html("");
	},
	showHide : function(s,opt,treeNode){
		if(!opt){
			this.clearDiv();
		}
		this.hide();
		if(!s)$("#id").val("");
		var fpositionid = treeNode.positionid;
		if(fpositionid){
			if(fpositionid == 4){
				$("#channelbody").css("display","");
				$("#timebody").css("display","");
				$("#listbody").css("display","none");
				$("#timessetbody").css("display","none");
				// 加载频道和时间段信息
				this.editLoadTimeperoidOrChannel(3,s,treeNode);
			}else if(fpositionid >= 5 && fpositionid <= 9){
				$("#channelbody").css("display","none");
				$("#listbody").css("display","");
				if(fpositionid == 9){
					$("#timessetbody").css("display","");
					$("#timebody").css("display","none");
				}else{
					$("#timessetbody").css("display","none");
					$("#timebody").css("display","");
					// 加载时间段信息
					this.editLoadTimeperoidOrChannel(1,s,treeNode);
				}
			}else if(fpositionid == 1 ||fpositionid ==2){
				$("#channelbody").css("display","none");
				$("#timebody").css("display","none");
				$("#listbody").css("display","none");
				$("#timessetbody").css("display","none");
			}else{
				$("#channelbody").css("display","none");
				$("#listbody").css("display","none");
				$("#timebody").css("display","");
				$("#timessetbody").css("display","none");
				// 加载时间段信息
				this.editLoadTimeperoidOrChannel(1,s,treeNode);
			}
		}
	},
	
	ajaxSubmit:function(form,uri, kdialog,opt,treeNode,vsid){
		var inputtimes = $("input[name='times']"); 
		var times = [];
		var resourcesIds = [];
		$.each(inputtimes,function(i,time){
			resourcesIds.push($(this).attr("attr"));
			times.push(this.value);
		});
		var time = "";
		var id = $("#id").val();
		if(resourcesIds.length > 0 || id){
			var flevel = $("input[name='flevel']:checked");
			// 频道分组
			var fisprovincecompany = flevel.attr("attr");
			var cgroup = flevel.val();
			// 频道
			var options = $("#fchannelsid").find("option");
			var fchannelsids = [];
			$.each(options,function(i,o){
				fchannelsids.push($(this).attr("value"));
			});
			var channel = "";
			var fdefinition = $("#fdefinition").val();
			var fpositionid = treeNode.positionid;
			if(fpositionid == 9){
				time = times.join(",");
			}else if(fpositionid == 4){
				channel = fchannelsids.join(",");
			}
			var data = {"fdefinition":fdefinition,"temp":resourcesIds.join(","),"fduration":time,"fchannel":channel,"fisprovincecompany":fisprovincecompany};
			data.ftimeperiodid = treeNode.timeperiodid;
			data.fadvid = treeNode.advid;
			data.vaid = treeNode.vaid;
			data.vsid = vsid;
			data.ffontcolor = cgroup;
			data.freleaseversionid = $("#freleaseversionid").val();
			data.cgroupsid = flevel.attr("cgroupsid");

			_waiting._show();
			$("#"+form).ajaxSubmit({
				url:uri,
				data : data,
				type:'post',
				success:function(data){
					if(data==0){
						$.jBox.tip('数据未作修改','info');
					}else{
						$.jBox.tip('数据保存成功','success');
						if(opt != 2) 
							source.queryByExample(1,treeNode);
						else 
							myTask.load();
						kdialog.remove();
					}
					_waiting._hide();
				},
				error:function(msg){
					$.jBox.tip('数据保存失败','error');
					kdialog.remove();
					_waiting._hide();
				}
			});
		}else{
			$.jBox.tip('请选择素材','error');
		}
	},
	ajaxLoadById:function(opt){
		//树中当前选中节点
		var node = base.getSelectedTreeNode();
		if(node == null){
			$.jBox.tip('请选择广告位或时间段','info');
			return;
		}
		var pos = node.positionid;
		if(pos != 1 && pos != 2 && pos != 9 && node.isParent){
			$.jBox.tip('请选择时间段','info');
			return;
		}
		
		var id= null;
		var vsid = null;
		var branchid = $("#fbranchid").val();
		if(opt==1){
			var checked = $("input[name='checkbox']:checked");
			id= checked.val();
			vsid = checked.attr("vsid");
			if(!id){
				$.jBox.tip('请选择需要编辑修改的信息','info');
				return;
			}
		}
		_waiting._show();
		$.post("selectByKey",{"id":id,"fpositionid":node.positionid},function(data,status){
			_waiting._hide();
			if(status){
				sourceEdit.edit(data,opt,branchid,node,vsid);
			}else{
				$.jBox.tip('数据加载失败','error');
			}
		},"json");
	},
	deleteSource:function(){
		var vsid = $("input[name='checkbox']:checked").attr("vsid");
		if(!vsid){
			$.jBox.tip('请选择要删除的数据','error');
			return false;
		}
		_waiting._show();
		$.post("delete",{"id":vsid},function(data,status){
			_waiting._hide();
			if(status){
				$.jBox.tip("删除成功","success");
				source.queryByExample(1,base.getSelectedTreeNode());
			}else{
				$.jBox.tip('数删除载失败','error');
			}
		});
	},
	selectAllFchannels : function(){
		var opts = $("#fchannels").find("option");
		var options = '';
		$.each(opts,function(i,opt){
			options += opt.outerHTML;
		});
		opts.remove();
		$("#fchannelsid").append(options);
	},
	selectSelectedFchannels : function(){
		var opts = $("#fchannels").find("option:selected");
		var options = '';
		$.each(opts,function(i,opt){
			options += opt.outerHTML;
		});
		opts.remove();
		$("#fchannelsid").append(options);
	},
	cancelFchannels : function(){
		var opts = $("#fchannelsid").find("option");
		var options = '';
		$.each(opts,function(i,opt){
			options += opt.outerHTML;
		});
		opts.remove();
		$("#fchannels").append(options);
	},
	cancelSelectedFchannels : function(){
		var opts = $("#fchannelsid").find("option:selected");
		var options = '';
		$.each(opts,function(i,opt){
			options += opt.outerHTML;
		});
		opts.remove();
		$("#fchannels").append(options);
	},
	changeFchannels :function(lev){
		var chs = $("#fchannelsid").find("option");
		var oldOpts = '';
		$.each(chs,function(i,c){
			var o = c.outerHTML;
			oldOpts += o;
		});
		if(oldOpts)
			$("#fchannels").append(oldOpts);
		
		var opts = $("#fchannels").find("option[_level='"+lev+"']");
		var options = '';
		$.each(opts,function(i,opt){
			var o = opt.outerHTML;
			options += o;
		});
		opts.remove();
		$("#fchannelsid").html(options);
	},
	appendChannels:function(o){
		var index = o.selectedIndex;
		var option = o.options[index];
		var opt = $(option);
		var html = "<option class='"+opt.attr('class')+"' _level='"+opt.attr('_level')+"' value='"+opt.val()+"'>"+opt.text()+"</option>";
		$("#fchannelsid").append(html);
		opt.remove();
	},
	delChannels:function(o){
		var index = o.selectedIndex;
		var option = o.options[index];
		var opt = $(option);
		var html = "<option class='"+opt.attr('class')+"' _level='"+opt.attr('_level')+"' value='"+opt.val()+"'>"+opt.text()+"</option>";
		opt.remove();
		$("#fchannels").append(html);
	},
	showResource : function(page,advid){
		var html = "<div><form id='formResource'>";
		html += '<input name="fadvid" value="'+ advid+'" type="hidden">';
		html += '&nbsp;&nbsp;&nbsp;列出&nbsp;&nbsp;开始日期 :<input class="Wdate" readonly="readonly" id="fusestarttime" name="fusestarttime" onClick="WdatePicker()" style="width: 100px;" type="text">';
		html += '结束日期 :<input class="Wdate" id="fuseendtime" name="fuseendtime" readonly="readonly" onClick="WdatePicker()" style="width: 100px;" type="text">';
		html += '&nbsp;使用状态: <select name="fisusing" id="fisusing"><option value="">==请选择==</option><option value="0">未使用</option><option value="1">使用中</option></select>';
		html += '&nbsp;素材名: <input name="fname" id="fname" style="width: 110px;" type="text">';
		html += '<input class="inputButton" name="submitbutton" value="查询" onclick="sourceEdit.ajaxResource('+page+','+advid+')" type="button">';
		html += "</form></div>";
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 800,
			height : 500,
			title : '资源选择',
			body : '<div id="txt_source_div" style="padding:0px;overflow:auto;overflow-x:hidden;">'+html+'<div id="dialogData" style="height:360px;overflow:auto;overflow-x:hidden;"></div><div id="pagerTab"></div></div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '添加',
				click : function(e) {
					var select = base.selectValue("\|","divCheckbox");
					
					var spans = "";
					var timeSetHtml = "";
					var o = eval("({"+select+"})");
					var p = o.fpath;
					var suffix = p.split(".");
					if(suffix[suffix.length-1] == "m2v"){
						p += ".jpg";
					}
					spans += "<a class='sourceSpan' onclick=\"sourceEdit.preview(\'"+p+"\',\'"+o.fname+"\',"+o.fwidth+","+o.fheight+","+300+","+160+")\" attr="+o.id+">"+o.fname+"</a>&nbsp;&nbsp;&nbsp;";
					// 广告时间设置
					timeSetHtml += "<div style='float:left;width:290px;font-size:13px;'>"+o.fname+":<input name='times' value='5' style='width:30px' attr='"+o.id+"|"+o.caid+"|"+o.carid+"|"+o.fnodeid+"'/></div>";

					sourceEdit.preview(p,o.fname,o.fwidth,o.fheight,300,160);
					$("#sourcediv").html(spans);
					$("#timesSet").html(timeSetHtml);
					kdialog.remove();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
		// 加载数据
		this.ajaxResource(1);
	},
	ajaxResource:function(page){
		$("#playHtml").html("");
		/**
		 * 加载资源信息
		 */
		if(!page){page = 1;}
		var limitParam = "limitRes";
		var limit = $("#"+limitParam).val();
		if(!limit){limit = 5;}
		
		var data = $("#formResource").serialize();
		data.page = page;
		data.limit = limit;
		_waiting._show();
		$.ajax({
			url:"loadResource",
			data:data,
			type:"post",
			dataType:"json",
			success:function(data){
				sourceEdit.bindGridResource(data,limit,limitParam);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载出错',"error");
				_waiting._hide();
			}
		
		});
	},
	bindGridResource:function(data,limit,limitParam){
		// 绑定资源信息
		var html = "";
		var iw = 240, ih = 140;
		if(data.total > 0){
			$.each(data.source,function(i,s){
				var p = base.replaceEndwidth(s.fpath);
				var play = preview.playHtml(p,s.fname,s.fwidth,s.fheight,iw,ih);// 232  184
				html += '<div name="trdiv'+i+'" id="trdiv'+i+'" onclick="base.selectTd(\''+i+'\');" class="tr img_div" style="width:252px;height:200px;" title="'+s.fname+'">';
				html += '<div class="imgDiv" style="height:140px;">'+play+'</div><div class="caption" style="font-size:12px;">';
				html += '<input type="radio" name="divCheckbox" id="checkboxdiv'+i+'" value="'+base.obj2Str(s)+'"/>';
				html += '使用状态:'+(s.fisusing==0 ? "<font color=blue>未使用</font>" : "<font color=red>使用中</font><a href='javascript:void(0);' onclick='base.showResourceUsed("+s.id+")'>查看</a>");
				html += '<br/>合同有效期：'+s.fusestarttime+'至'+s.fuseendtime+'<br/>上传时间：'+s.fcreatetime+'</div></div>';
			});
			html += '<div class="clear"></div>';
		}else{
			html = "暂无您查询的记录";
		}
		$("#dialogData").html(html);
		$("#pagerTab").pager({ pagenumber: data.page, pagecount: data.totalPage, buttonClickCallback: this.ajaxResource, limitFun:"sourceEdit.ajaxResource",limit:limit,limitParam:limitParam});
	},
	preview : function(p,name,width,height,iw,ih){
		// 添加预览
		$("#playHtml").html(preview.playHtml(p,name,width,height,iw,ih));
	},
	setAdv:function(){
		var attr = $("#fcontractid").find("option:selected").attr("temp");
		var v = attr.split("|");
		$("#fadvname").val(v[0]);
		$("#fadvclassid").val(v[1]);
		var definition = v[2];
		$("#fdefinition").val(definition);
	},
	previewContract:function(id){
		_waiting._show();
		$.post("../contract/previewContract",{"id":id},function(data,status){
			_waiting._hide();
			if(status){
				var contract = data.contract;
				html = '<div style="margin-left:20px;height:100%;overflow:auto; overflow-x:hidden;"><table style="width:100%;font-size:13px;border:1px;"><label style="text-align: center;width:99%;font-size: 18px">合同基本信息</label><br/>';
				html += '<tr><td>合同编号：'+contract.fguid+'</td><td>合同名称：'+contract.ftitle+'</td><td>解析度：'+contract.fdefinition+'</td></tr>';
				html += '<tr><td>广告商：'+contract.fadvname+'</td><td>广告位：'+data.advclass.ftype+'</td><td>广告级别：'+contract.fadvlevel+'</td></tr>';
				html += '<tr><td>代理商：'+contract.fagent+'</td><td>联系人：'+contract.fcontactname+'</td><td>联系电话：'+contract.fcontacttel+'</td></tr>';
				html += '<tr><td>单价：'+contract.fprice+'</td><td>折扣：'+contract.fdiscount+'</td><td>付款方式：'+contract.fpayway+'</td></tr>';
				html += '<tr><td>起始日期：'+contract.fstarttime+'</td><td>终止日期：'+contract.fendtime+'</td></tr>';
				html += '</table><label style="text-align: center;width:100%;font-size: 18px">合同内容</label><br/>'+contract.fcontent+'</div>',
				$.jBox.open(html,"合同浏览",750,520,{"showScrolling":false,"top":"1px"});
			}else{
				$.jBox.tip('数据加载失败','error');
			}
		},"json");
	},
	loadSourceTimeperiod : function(){
		var treeNode = base.getSelectedTreeNode();
		var advid = treeNode.advid;
		var rvid = treeNode.rvid;
		if(treeNode.positionid == -3){
			advid = treeNode.leftMenuAdvid;
			rvid = treeNode.provRvid;
		}
		var vsid = $("input[name='checkbox']:checked").attr("vsid");
		if(!treeNode && treeNode.isParent && treeNode.root){
			$.jBox.tip('请选择时间段信息',"info");
			return;
		}
		if(!vsid){
			$.jBox.tip('请选择广告信息',"info");
			return;
		}

		var params = {"fadvid":advid,"rvid":rvid,"vaid":treeNode.vaid};
		
		$.ajax({
			url:"../time/loadTimeperiodByAdvid",
			data: params,
			type:"post",
			dataType:"json",
			success:function(data){
				console.info(data);
				if(data.length > 1){
					sourceEdit.bindGridSourceTimeperiod(data,treeNode,vsid);
				}else{
					$.jBox.tip("无需修改，该广告位下只有一个时间段","info");
				}
			},
			error:function(data){
				$.jBox.tip('数据加载出错',"error");
			}
		
		});
	},
	bindGridSourceTimeperiod : function(data,treeNode,vsid){
		var html = "<br/><br/><br/><label for='ftimeperiodid'>选择时间段：</label><select name='ftimeperiodid' id='ftimeperiodid'>";
		var selected = "selected='selected'";
		$.each(data,function(i,d){
			html += "<option value='"+d.id+"' "+(d.id==treeNode.timeperiodid ? selected : '')+">"+d.fstarttime+"-"+d.fendtime+"</option>";
		});
		html += "</select>";
		var kdialog=null;
		kdialog = KindEditor.dialog({
			width : 300,
			height : 200,
			title : '广告位时间修改',
			body : '<div id="txt_source_div" style="padding:0px;overflow:auto;overflow-x:hidden;margin-top:1px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '修改',click:function(e){sourceEdit.updateTimeperiod(treeNode,vsid);kdialog.remove();}},
			noBtn : {name : '关闭',click : function(e) {kdialog.remove();}}
		});
	},
	updateTimeperiod : function(treeNode,vsid){
		//
		$.ajax({
			url:"updateSourceTimeperiod",
			data:{"ftimeperiodid":$("#ftimeperiodid").val(),"id":vsid},
			type:"post",
			dataType:"json",
			success:function(data){
				$.jBox.tip('时间段修改成功',"success");
				sourceEdit.queryByExample(1,treeNode);				
			},
			error:function(data){
				$.jBox.tip('数据加载出错',"error");
			}
		
		});
	},
	copyInsertVersion : function(){
		$.jBox.confirm('您确定要复制新增广告版本吗？', '温馨提示', this.copying);
		/*var rv = $("#freleaseversionid").find("option:selected");
		var html = "<div>当前选中版本号："+rv.text()+"</div><br/>";
		html += "<div>是否要复制该版本作为新版本：<input type='checkbox' name='rvid' id='rvid' value='"+rv.val()+"'></div><br/>";
		html += "<div><b>注：未选中时表示使用使用上一版本作为最版本。</b><div>";
		var kdialog=null;
		kdialog = KindEditor.dialog({
			height : 200,
			title : '温馨提示',
			body : '<div id="txt_source_div" style="overflow:auto;overflow-x:hidden;padding:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '复制新增',click:function(e){
				var rvid = $("#rvid:checked").val();
				if(!rvid) rvid = "";
				_waiting._show();
				//复制新版本
				$.ajax({
					url:"copyInsertVersion",
					data:{"fbranchid":$("#fbranchid").val(),"fdefinition":$("#fdefinition").val(),"rvid":rvid},
					type:"post",
					dataType:"json",
					success:function(data){
						_waiting._hide();
						$.jBox.tip('版本新建成功',"success");
						source.loadBranchRelease();
						kdialog.remove();
					},
					error:function(data){
						_waiting._hide();
						$.jBox.tip('数据加载出错',"error");
						kdialog.remove();
					}
				});
			}},
			noBtn : {name : '关闭',click : function(e) {kdialog.remove();}}
		});*/
	},
	copying : function(v,k,s){
		if(v == 'ok'){
			var rvid = $("#rvid:checked").val();
			if(!rvid) rvid = "";
			_waiting._show();
			//复制新版本
			$.ajax({
				url:"copyInsertVersion",
				data:{"fbranchid":$("#fbranchid").val(),"fdefinition":$("#fdefinition").val(),"rvid":rvid},
				type:"post",
				dataType:"json",
				success:function(data){
					_waiting._hide();
					$.jBox.tip('版本新建成功',"success");
					source.loadBranchRelease();
				},
				error:function(data){
					_waiting._hide();
					$.jBox.tip('数据加载出错',"error");
				}
			});
		}
		return true;
	},
	noticeReleaseToTestNode : function(){
		var rvid = $("#freleaseversionid").val();
		console.debug(rvid);
		if(!rvid){
			$.jBox.tip("暂无版本信息","info");
			return;
		}
		_waiting._show();
		//复制新版本
		$.ajax({
			url:"noticeReleaseToTestNode",
			data:{"rvid":rvid},
			type:"post",
			dataType:"json",
			success:function(data){
				_waiting._hide();
				$.jBox.tip('操作成功，通知下一节点成功',"success");
			},
			error:function(data){
				_waiting._hide();
				$.jBox.tip('数据加载出错',"error");
			}
		});
	}
	/**
	 * 加载分公司广告位
	 */
	/*loadBranchAdv : function(branchid){
		this.hide();
		$.post("loadBranchAdv",{"fbranchid":branchid},function(data,status){
			if(status){
				var advHtml= "<option value='' positionid='' definition=''>===选择合同-广告位===</option>";
				$.each(data,function(i,adv){
					advHtml += '<option value="'+adv.id+'" positionid='+adv.fpositionid+' definition='+adv.fdefinition+'>'+adv.ftype+"|"+adv.fdefinition+'</option>';
				});
				$("#fadvclassid").html(advHtml);
			}else{
				$.jBox.tip('数据加载失败','error');
			}
		},"json");
	}*/
	
};