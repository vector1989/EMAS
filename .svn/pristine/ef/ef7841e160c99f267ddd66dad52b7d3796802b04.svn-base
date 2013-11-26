var upload={
	
	edit1 : function(D){
		var kdialog = null;
		var option = "<option value=''>请选择合同.....</option>";
		$.each(D,function(i,con){
			option += "<option class='"+con.fadvlocid+"' advName='"+con.temp+"' fdefinition='"+con.fdefinition+"' fbranchid='"+con.fbranchid+"' fpositionid='"+con.fpositionid+"' advid='"+con.fadvlocid+"' value='"+con.id+"'>"+con.fadvname+"---"+con.ftitle+"</option>";
		});

		var html = '<form id="form1" enctype="multipart/form-data">';
		html += '<table>';
		html += '<tr><td><label for="fcontrackid">选择合同：</label></td><td><select name="fcontrackid" required="required" id="fcontrackid" onchange="upload.setAdvClass(this.value)">'+option+'</select></td>';
		html += '<tr><td colspan="2"><input name="fadvclassid" id="fadvid" type="hidden" value=""/></td></tr>';
		html += '<tr><td><label for="fpositionid">广告位：</label></td><td><input name="fadvName" id="fadvname" type="text" disabled="disabled"/></td></tr>';
		html += '<tr><td colspan="2"> </td></tr>';
		html += '<tr><td><label for="fdefinition">解析度：</label></td><td><input name="fdefinition" id="fdefinition" type="text" readonly="readonly"/></td></tr>';//<select name="fdefinition" required="required" id="fdefinition"><option value="HD">高清&nbsp;</option><option value="SD">标清&nbsp;</option></select>
		html += '<tbody id="tbody" style="display:none;">';
		html += '<tr><td colspan="2"> </td></tr>';
		html += '<tr><td><label for="ftype">素材特殊通道：</label></td><td><input id="ftype" name="ftype" value="11" required="required" type="checkbox" placeholder="请选择是否为特殊通道"/></td></tr>';//'+checkbox+'
		html += '</tbody>';
		html += '<tr><td colspan="2"> </td></tr>';//multiple="true" 
		html += '<tr><td><label for="resource">素材：</label></td><td><input name="file" disabled="disabled" required="required" id="file" type="file" style="width:160px;" accept="video/x-mpeg2"/><div id="tips" style="font-size:13px;color:red;"></div></td></tr>';
		html += '</table></form>';
		kdialog = KindEditor.dialog({
			width : 500,
			height : 300,
			title : '新增素材',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var checkForm = base.formIsNull();
					if(checkForm){
						upload.ajaxSubmit("form1");
						kdialog.remove();
					}
				}
			}
		});
	},
	edit : function(D){
		var kdialog = null;
		var contracts = "<option value=''>请选择合同.....</option>";
		$.each(D.cadvs,function(i,ca){
			contracts += "<option class='"+ca.id+"' adv='"+ca.fadvid+"|"+ca.fpositionid+"|"+ca.fdefinition+"|"+ca.id+"|' value='"+ca.fcontractid+"'>["+ca.fcontractName+"]-["+ca.ftype+"]-["+ca.fdefinition+"]</option>";
		});
		var branchsHtml = "";
		var fbranchid = $("#fbranchid").val();
		if(fbranchid){
			var selected = "selected='selected'";
			$.each(D.branchs,function(i,b){
				branchsHtml += '<option value="'+b.id+'" '+(b.id==fbranchid?selected:"")+'>'+b.fname+'</option>';
			});
		}
		var html = '<form id="form1" enctype="multipart/form-data">';
		html += '<table>';
		if(branchsHtml){
			html += '<tr><td><label for="fbranchid">分公司：</label></td><td><select name="fbranchid" required="required" id="branchid" onchange="upload.ajaxLoadData(1)">'+branchsHtml+'</select></td></tr>';
		}
		html += '<tr><td><label for="fcontractid">选择合同：</label></td><td><select name="fcontractid" required="required" id="contractid" onchange="upload.setAdvClass(this.value)">'+contracts+'</select></td></tr>';
		html += '<tbody id="tbody" style="display:none;">';
		html += '<tr><td><label for="ftype">素材特殊通道：</label></td><td><input id="ftype" name="ftype" value="11" required="required" type="checkbox" placeholder="请选择是否为特殊通道"/></td></tr>';//'+checkbox+'
		html += '</tbody>';
		html += '<tr><td><label for="ftype">素材限制条件：</label></td><td><div id="tips" style="font-size:13px;color:red;"></div></td></tr>';
		html += '<tr><td><label for="resource">素材：</label></td><td><input name="file" disabled="disabled" required="required" id="file" type="file" multiple="true" style="width:160px;" accept="video/x-mpeg2"/></td></tr>';
		html += '</table></form>';
		kdialog = KindEditor.dialog({
			width : 550,
			height : 250,
			title : '新增素材',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var checkForm = base.formIsNull();
					if(checkForm){
						upload.ajaxSubmit("form1");
						kdialog.remove();
					}
				}
			}
		});
	},
	ajaxSubmit:function(form,uri){
		var advValue = $("#adv").val();
		if(!advValue)
			advValue = $("#fcontrackid").find("option:selected").attr("adv");
		var adv = advValue.split("\|");
		var fadvid = adv[0];
		var fpositionid = adv[1];//广告位
		var fdefinition = adv[2];//解析度
		var cid = adv[3];//合同广告位关联索引
		var carId = adv[4];
		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:"insert",
			type:'post',
			data:{"fadvclassid":fadvid,"fpositionid":fpositionid,"fdefinition":fdefinition,"cid":cid,"carId":carId},
			dataType:'json',
			success:function(data){
				_waiting._hide();
				var badSuffix = data.badSuffix;
				var badSize = data.badSize;
				var badWH = data.badWH;
				var badDuration = data.badDuration;
				if(badSuffix || badSize || badWH || badDuration){
					var bad = "";
					if(badSuffix){
						bad += "<div style='word-wrap:break-word;'>以下资源素材格式不正确：<br/>" + badSuffix + "<br/></div>";
					}
					if(badSize){
						bad += "<div style='word-wrap:break-word;'>以下资源素材文件大小超过最大资源文件大小:<br/>"+badSize + "</br></div>";
					}
					if(badWH){
						bad += "<div style='word-wrap:break-word;'>以下资源素材文件尺寸不对：<br/>"+badWH + "<br/></div>";
					}
					if(badDuration){
						bad += "<div style='word-wrap:break-word;'>以下资源素材文件时长不对：<br/>"+badDuration + "<br/></div>";
					}
					var body = '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+bad+'</div>';
					
					$.jBox.open(body,"温馨提示");
					
				}else{
					$.jBox.tip("数据保存成功","success",{"timeout":1000});
				}
				Resource.show();
			},
			error:function(msg){
				_waiting._hide();
				$.jBox.tip("数据保存失败","error",{"timeout":1000});
			}
		});
	},
	/**
	 * 加载合同广告位
	 */
	ajaxLoadData : function(opt){
		$.post("loadContractAdv",{"fbranchid":$("#branchid").val()},function(data,status){
			if(status){
				if(!opt){
					if(data.cadvs.length > 0)
						upload.edit(data);
					else
						$.jBox.tip("该分公司暂无合同","info",{"timeout":2000});
				}else{
					var contracts = "<option value=''>请选择合同.....</option>";
					$.each(data.cadvs,function(i,ca){
						contracts += "<option class='"+ca.id+"' adv='"+ca.fadvid+"|"+ca.fpositionid+"|"+ca.fdefinition+"|"+ca.id+"|"+ca.carId+"' value='"+ca.fcontractid+"'>["+ca.fcontractName+"]-["+ca.ftype+"]-["+ca.fdefinition+"]</option>";
					});
					$("#tips").html("");
					$("#contractid").html(contracts);
				}
			}else{
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
		},"json");
	},
	setAdvClass : function(val){
		if(val){
			var adv = $("#fcontrackid").find("option:selected").attr("adv").split("\|");
			var fadvid = adv[0];
			var fpositionid = adv[1];//广告位
			if(fpositionid == -3){
				fpositionid = 3;
			}
			var fdefinition = adv[2];//广告位
			var fbranchid = $("#fbranchid").val();
			$("#tips").html("");
			//广告位配置信息
			if(fadvid)
				this.setAdvClassConfig(fdefinition,fbranchid,fpositionid);

			$("#file").removeAttr("disabled");
		}
		else{
			$("#file").attr("disabled","disabled");
		}
	},
	setAdvClassConfig : function(fdefinition,fbranchid,fpositionid){
		var d = {"fdefinition":fdefinition,"fpositionid":fpositionid,"fbranchid":fbranchid};
		$.post("../advClassConfig/querybyObject",d,function(data,status){
			var dd = data.config;
			var branch = data.branch;
			var display = "none";
			if(status){
				var html = "宽*高：" + dd.fwidth + "*" + dd.fheight + "px;文件大小:<=" + dd.fsize + "kb;文件格式："+dd.fformat;
				if(dd.ftime)
					html += ";时长：" + dd.ftime;
				$("#tips").html(html);
				if(branch.fisspecialchannel == 1){					
					display = "";
				}
			}else{
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
			$("#tbody").css("display",display);	
		},"json");
	},
	modify : function(rid,cid,fdefinition,fbranchid,fpositionid,fadvid,carId,opt){
		var kdialog = null;
		var html = '<form id="form1" enctype="multipart/form-data">';
		html += '<input id="adv" type="hidden" value="'+fadvid+'|'+fpositionid+'|'+fdefinition+'|'+cid+'|'+carId+'"/>';
		html += '<input name="fbranchid" type="hidden" value="'+fbranchid+'">';
		html += '<table>';
		html += '<tr><td><label for="ftype">素材限制条件：</label></td><td><div id="tips" style="font-size:13px;color:red;"></div></td></tr>';
		html += '<tr><td colspan="2">&nbsp;<input name="id" type="hidden" value="'+rid+'"/></td></tr>';
		html += '<tr><td><label for="resource">素材：</label></td><td><input name="file" required="required" id="file" type="file" multiple="true" style="width:160px;"/></td></tr>';
		html += '</table></form>';
		kdialog = KindEditor.dialog({
			width : 550,
			height : 200,
			title : '修改素材',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var checkForm = base.formIsNull();
					if(checkForm){
						upload.ajaxSubmit("form1");
						kdialog.remove();
					}
				}
			}
		});
		this.setAdvClassConfig(fdefinition,fbranchid,fpositionid);
	}/*,
	loadBranchContractAdv : function(val){
		$.post("loadContractAdv",{"fbranchid":val},function(data,status){
			if(status){
				var contracts = "<option value=''>请选择合同.....</option>";
				$.each(data.cadvs,function(i,ca){
					contracts += "<option class='"+ca.id+"' adv='"+ca.fadvid+"|"+ca.fpositionid+"|"+ca.fdefinition+"|"+ca.id+"|' value='"+ca.fcontractid+"'>["+ca.fcontractName+"]-["+ca.ftype+"]-["+ca.fdefinition+"]</option>";
				});
				$("#fcontrackid").html(contracts);
			}else{
				$.jBox.tip("数据加载失败","error",{"top":"5px"});
			}
		},"json");
	}*/
};