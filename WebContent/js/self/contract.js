$(document).ready(function(){
	navTag("合同管理&gt;合同列表");
	con.load();
});
var con={
	load : function(page){
		$("#AllCheck").attr("checked",false);
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		var data = {"page":page,"limit":limit};
		_waiting._show();
		$("#queryForm").ajaxSubmit({
			url:"queryContract",
			type:"post",
			data:data,
			dataType:"json",
			success:function(data){
				con.bindGrid(data,limit);
				$("#sumPrice").val(data.sumPrice+"元");
				_waiting._hide();
				if(""!=data.emailStr){
					con.sendMail(data.emailStr);
				}
			},
			error:function(data){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	},
	bindGrid : function(V,limit){
		var D = eval(V);
		var html = '';
		if(D.total > 0){
			$.each(D.source,function(i,obj){
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.fguid+'</td><td>'+obj.ftitle+'</td>';
				html += '<td>'+obj.fadvname+'</td><td>'+obj.fagent+'</td><td>'+obj.fstarttime+'|'+obj.fendtime+'</td><td>'+obj.fcreateuser+'</td>';
				html += '<td>'+obj.fcontactname+'|'+obj.fcontacttel+'</td><td>'+obj.fprice+'</td>';
				html += '<td>'+obj.fdiscount+'</td><td>'+obj.fpayway+'</td><td>'+con.ffreezed(obj.ffreezed)+'</td>';
				html += '</tr>';
			});
		}else{
			html = "<tr><td colspan='14' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"con.load",limit:limit,count:D.total});
	},
	sendMail : function(emailStr){
		_waiting._show();
		$.ajax({
			url: "sendMail",
			data :{"emailStr":emailStr},
			type:"post",
			dataType:"json",
			success:function(data){
				$.jBox.tip(data+'条合同已到期，请查收邮件！');
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('邮件发送失败！','error'); 
				_waiting._hide();
			}
		});
	},
	upres : function(conadv){
		if(conadv.fedited == "1"){
			return "<font color='green'>已编辑</font>";
		}else if(conadv.fresourceid){
			return "<font color='blue'>已添加</font>";
		}else if(conadv.foriginalresourceid){
			return "<font color='yellow'>原始素材</font>";
		}else{
			return "<font color='red'>未添加</font>";
		}
	},
	play:function(fpath,fwidth,fheight,iw,ih){
		var html = con.playHtml(fpath,fwidth,fheight,iw,ih);
		var K = null;
		K = KindEditor.dialog({title : '素材浏览',body : html, shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {K.remove();}},
			noBtn : {name : '关闭',click : function(e) {K.remove();}}
		});
	},
	convert : function(val){
		if("0"==val){
			return "<font color='blue'>未审核</font>";
		} else if("1"==val){
			return "<font color='green'>审核通过</font>";
		}else if("2"==val){
			return "<font color='red'>审核未通过</font>";
		}
	},
	ffreezed : function(val){
		if("0"==val){
			return "<font color='green'>未过期</font>";
		}
		if("1"==val){
			return "<font color='blue'>已延期</font>";
		}
		if("2"==val){
			return "<font color='red'>已过期</font>";
		}
	},
	edit : function(opt){
		$.post("hasAdv",function(data,status){
			if(status){
				if(data > 0){
					if(!opt){
						window.location.href=$("#modify").attr("src");
						return;
					}else{
						var id = base.selectFirst();
						if(!id){
							$.jBox.tip("请选择要修改的数据","error");
							return false;
						}else{
							$.ajax({
								url:"previewContract",
								type:"post",
								data:{"id":id},
								dataType:"json",
								success:function(data){
									if(data.user.fusergroupid==1||data.user.id==data.contract.fcreateuserid){
										var modify = $("#modify");
										var src = modify.attr("src");
										window.location.href=src+"?id="+id;
									}else{
										$.jBox.tip('您无权修改该合同！',"error");
									}
								},
								error:function(data){
									$.jBox.tip('数据操作失败',"error"); 
									_waiting._hide();
								}
							});
						}
					}
				}else{
					$.jBox.tip("该公司暂无广告位，请检查或联系管理员","info",{"timeout":"3000"});
				}
			}else{
				$.jBox.tip("数据加载出错","error");
			}
		});
		
	},
	option : function(url){
		var id = base.selectValue();
		if(!id){
			$.jBox.tip("请选择要操作的数据","error");
			return false;
		}
		_waiting._show();
		$.ajax({
			url:url,
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				$.jBox.tip(data+'条数据操作成功',"success");
				con.load();
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据操作失败',"error"); 
				_waiting._hide();
			}
		});
	},
	ajaxSubmit:function(form,uri){
		var limit = $("#limit").val();
		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:uri,
			type:'post',
			data:{"limit":limit},
			dataType:'json',
			success:function(data){
				con.bindGrid(data,limit);
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据保存失败',"error"); 
				_waiting._hide();
			}
		});
	},
	ajaxLoadById:function(opt){
		var id= base.selectFirst();
		_waiting._show();
		$.ajax({
			url:"selectById",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				con.edit(data,opt);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败',"error"); 
				_waiting._hide();
			}
		});
	},
	getPath : function(){
		var curWwwPath=window.document.location.href;
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    var localHostPath=curWwwPath.substring(0,pos);
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+2);
	    return localHostPath+projectName;
	},
	download:function(){
		var id= base.selectFirst();
		if(!id){
			$.jBox.tip("请选择要下载的数据","error");
			return false;
		}
		_waiting._show();
		$.ajax({
			url:"download",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				var downloadPath = con.getPath()+data.contract.ffilepath+data.contract.ffiletitle;
				if(null==data.msg){
					window.location.href=downloadPath;
				}else{
					$.jBox.tip(data.msg,"error");
				}
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败',"error"); 
				_waiting._hide();
			}
		});
	},
	deleteContract : function(){
		var id = base.selectValue();
		if(!id){
			$.jBox.tip("请选择要删除的数据","error");
			return false;
		}else if(confirm("确认删除？")){
			_waiting._show();
			$.ajax({
				url:"delete",
				type:"post",
				data:{"id":id},
				dataType:"json",
				success:function(data){
					if(null!=data.eids){
						var html = "合同索引为";
						$.each(data.eids,function(i,id){
							html += id+",";
						});
						$.jBox.tip(html+"的合同通过审核，不可删除！","error");
					}else{
						$.jBox.tip('数据删除成功',"success");
					}
					con.load();
					_waiting._hide();
				},
				error:function(data){
					$.jBox.tip('数据删除失败',"error"); 
					_waiting._hide();
				}
			});
		}
	},
	uploadPic : function(value){
		var regExp= /^.*\.gif|\.jpg|\.jpeg|\.png|\.GIF|\.JPG|\.JPEG|\.PNG$/;
		var id= base.selectFirst();
		if(!regExp.test(value)){
			$.jBox.tip("请选择正确格式图片！",'error'); 
		}
		if(!id){
			$.jBox.tip("请选择要关联的合同！",'error'); 
		}else{
			_waiting._show();
			$("#addPicForm").ajaxSubmit({
          		url:"uploadPic",
          		data:{"id":id},
          		type:"post",
          		dataType:"json",
          		success:function(data){
					$.jBox.tip(data.msg,'error'); 
    				_waiting._hide();
          		}
          	});
		}
	},
	preview:function(){
		var id= base.selectFirst();
		if(!id){
			$.jBox.tip("请选择要预览的数据","error");
			return false;
		}
		_waiting._show();
		$.ajax({
			url:"preview",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				var previewPath = con.getPath()+data.contract.ffilepath+data.contract.fpictitle;
				if(null==data.msg){
					kdialog = KindEditor.dialog({
						width : 600,
						height : 350,
						title : '合同图片预览',
						body : '<div id="txt_source_div" align="center" style="padding:0px;height:100%;overflow:auto;margin-top:20px;margin-left:0px;"><img style="width:100%" src="'+previewPath+'"/></div>',
						shadowMode : true,
						closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
						yesBtn : {name : '取消',click : function(e) {kdialog.remove();}}
					});
				}else{
					$.jBox.tip(data.msg,'error'); 
				}
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	},
	previewContract:function(){
		var id= base.selectFirst();
		if(!id){
			$.jBox.tip("请选择要预览的数据","info");
			return false;
		}
		_waiting._show();
		$.ajax({
			url:"previewContract",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				html = '<div style="padding:10px 20px;height:100%;overflow:auto;font-size:13px;">';
				html += '<table style="width:100%;font-size:13px;" cellspacing="0" cellpadding="0" border="1" rules="both"><label style="text-align: center;width:100%;font-size: 20px">合同基本信息</label><br/>';
				html += '<tr><td width="80">合同编号：</td><td>'+data.contract.fguid+'</td><td width="80">合同名称：</td><td>'+data.contract.ftitle+'</td></tr>';
				html += '<tr><td>广告商：</td><td>'+data.contract.fadvname+'</td><td>广告级别：</td><td>'+data.contract.fadvlevel+'</td></tr>';
				html += '<tr><td>起始日期：</td><td>'+data.contract.fstarttime+'</td><td>终止日期：</td><td>'+data.contract.fendtime+'</td></tr>';
				html += '<tr><td>代理商：</td><td>'+data.contract.fagent+'</td><td>联系人：</td><td>'+data.contract.fcontactname+'</td></tr>';
				html += '<tr><td>联系电话：</td><td>'+data.contract.fcontacttel+'</td><td>单价：</td><td>'+data.contract.fprice+'</td></tr>';
				html += '<tr><td>折扣：</td><td>'+data.contract.fdiscount+'</td><td>付款方式：</td><td>'+data.contract.fpayway+'</td></tr>';
				html += '<tr><td>广告位：</td><td colspan="3">';
				$.each(data.advclass,function(i,a){
					html += "[" + a.ftype + "|"+a.fdefinition+"]" ;
				});
				html += '</td></tr></table><label style="text-align: center;width:100%;font-size: 20px">合同内容</label><br/>'+data.contract.fcontent+'</div>',
				kdialog = KindEditor.dialog({
					width : 750,
					height : 500,
					title : '合同预览',
					body : html,
					shadowMode : true,
					closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
					yesBtn : {name : '取消',click : function(e) {kdialog.remove();}}
				});
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	},
	uploadRes:function(){
		var id= base.selectFirst();
		if(!id){
			$.jBox.tip('请选择要上传的数据','error');
			return false;
		}
		_waiting._show();
		$.post("previewContract",{"id":id,"temp":"1"},function(data,status){
			if(status){
				con.editRes(data);
				_waiting._hide();
			}else{
				$.jBox.tip('数据加载失败','error');
			}
		},"json");
	},	
	editRes:function(data){
		//分公司
		var branch = data.branch;
		var contract = data.contract;
		var html = '<form id="form1" enctype="multipart/form-data">';
		html += '<input name="fdefinition" value="" type="hidden">';
		html += '<input name="fpositionid" value="" type="hidden">';
		html += '<input name="fbranchid" value="'+contract.fbranchid+'" type="hidden">';
		html += '<table>';
		html += '<tr><td><label for="fcontrackid">合同名称：</label></td><td><input type="hidden" name="fcontrackid" required="required" id="fcontrackid" value="'+contract.id+'"/>'+contract.ftitle+'</td>';
		html += '<tr><td><label for="fpositionid">广告位：</label></td>';
		html += '<td><select id="fadvlocid" name="cid" style="width:155px" onchange="con.setAdvClassConfig()">';
		$.each(data.advclass,function(i,conadv){
			html += '<option value="'+conadv.id+'" fadvid="'+conadv.fadvid+'">'+conadv.ftype+'|'+conadv.fdefinition+'</option>';
		});
		html += '</select></td></tr>';
		var display = "none";
		if(branch.fisspecialchannel == 1)	display = "";
		html += '<tbody id="tbody" style="display:'+display+';"><tr><td><label for="ftype">素材特殊通道：</label></td><td><input id="ftype" name="ftype" value="11" required="required" type="checkbox" placeholder="请选择是否为特殊通道"/></td></tr></tbody>';
		html += '<tr><td><label for="ftype">素材限制条件：</label></td><td><div id="tips" style="font-size:13px;color:red;"></div></td></tr>';
		html += '<tr><td><label for="resource">素材：</label></td><td><input name="file" required="required" id="file" type="file" multiple="true" style="width:160px;" accept="video/x-mpeg2"/></td></tr>';
		html += '</table></form>';

		kdialog = KindEditor.dialog({
			width : 530,
			height : 260,
			title : '新增素材',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var checkForm = base.formIsNull();
					if(checkForm){
						con.uploadSubmit("form1");
						kdialog.remove();
					}
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
		this.setAdvClassConfig();
	},
	uploadSubmit:function(form,uri){
		var fadvid = $("#fadvlocid").find("option:selected").attr("fadvid");
		
		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:"../resource/insert",
			type:'post',
			data:{"fadvclassid":fadvid},
			dataType:'json',
			success:function(data){
				_waiting._hide();
				var badSuffix = data.badSuffix;
				var badSize = data.badSize;
				var badWH = data.badWH;
				if(badSuffix || badSize || badWH){
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
					var body = '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+bad+'</div>';
					
					$.jBox.open(body,"温馨提示");
					
				}else{
					$.jBox.tip("数据保存成功","success",{"timeout":1000});
				}
				con.load();
			},
			error:function(msg){
				_waiting._hide();
				$.jBox.tip("数据保存失败","error",{"timeout":1000});
			}
		});
	},
	setAdvClassConfig : function(){
		var fadvid = $("#fadvlocid").find("option:selected").attr("fadvid");
		$.post("loadAdvConfig",{"id":fadvid},function(dd,status){
			if(status){
				var html = "宽*高：" + dd.fwidth + "*" + dd.fheight + "px;文件大小:<=" + dd.fsize + "kb;文件格式："+dd.fformat;
				if(dd.ftime)
					html += ";时长：" + dd.ftime;
				$("input[name='fpositionid']").val(dd.fpositionid);
				$("input[name='fdefinition']").val(dd.fdefinition);
				$("#tips").html(html);
			}else{
				$.jBox.tip("数据加载失败","error",{"timeout":1000});
			}
		},"json");
	},
	previewResource : function(){
		var id= base.selectFirst();
		if(!id){
			$.jBox.tip('请选择合同数据','info');
			return false;
		}
		$.post("loadPreviewResource",{"id":id},function(data,status){
			if(status){
				var html = "";
				var href = window.location.href;
				href = "/" + href.split("/")[3] + "/";
				$.each(data,function(i,s){
					if(s.fpath){
						var play = preview.playHtml(href+s.fpath,"",s.fwidth,s.fheight,240,150);//232 184
						html += '<div name="trdiv'+i+'" id="trdiv'+i+'" class="tr img_div" style="width:252px;height:200px;" title="素材预览">';
						html += '<div class="imgDiv" style="height:160px;">'+play+'</div><div class="caption" style="font-size:12px;">';
						html += '广告位:'+s.ftype+"|"+s.fdefinition+'<br/>状态：'+con.upres(s)+'</div></div>';
					}
				});
				if(html){
					html += '<div class="clear"></div>';
					$.jBox.open(html,"素材浏览",800,500,{"top":"2%",buttons:{"提交":true}});
				}else{
					$.jBox.tip('该合同暂无素材信息','error');
				}
			}else{
				$.jBox.tip('素材预览失败','error');
			}
		},"json");
	},
	/**
	 * 加载合同的广告位和素材
	 */
	loadAdvAndResourceByContract : function(opt){
		var id= base.selectFirst();
		if(!id){
			$.jBox.tip('请选择合同数据','info');
			return false;
		}
		$.post("loadAdvAndResourceByContract",{"id":id},function(data,status){
			if(status){
				var html = '<table style="width:100%;font-size:13px;" border="0" rules="none" cellpadding="0" cellspacing="0">';
				html += '<tr><th>广告位</th><th>素材</th><th>状态</th><th>素材使用状态</th><th>素材有效期</th><th>操作</th><th>流程</th></tr>';
				$.each(data,function(i,c){
					html += "<tr><td>"+c.ftype+"|"+c.fdefinition+"</td><td>"+(c.fname != undefined ? c.fname: '')+"</td>";
					var usetime = "";
					var nodestatus="";
					if(c.fresourceid || c.foriginalresourceid){
						if(c.fusestarttime || c.fuseendtime){
							usetime = c.fusestarttime + "至" + c.fuseendtime;
						}
						nodestatus = '<a href="../nodeStatus/queryContractAdvResourceStatus?type=0&id='+c.carId+'&bid='+$("#fbranchid").val()+'" target="_black";">流程</a>';
					}
					html += "<td>"+con.upres(c)+"</td><td>"+con.isusing(c.fisusing)+"</td><td align='right'>"+usetime+"</td>";
					html += "<td align='right'>"+con.previewResourceHtml(c)+"</td><td>"+nodestatus+"</td></tr>";
				});
				html += "</table>";
				if(!opt){
					var body = '<div class="table" id="txt_source_div_1" style="padding:5px;overflow:auto;overflow-x:hidden;">'+html+'</div>';
					$.jBox(body,{"title":"合同广告位","width":920,"height":440,"top":"15%"});
				}else{
					$("#txt_source_div_1").html(html);
				}
			}else{
				$.jBox.tip('广告位加载失败','error');
			}
		},"json");
	},
	isusing : function(s){
		var str = "";
		switch (s) {
		case '0':
			str = "<font color='green'>未使用</font>";
			break;
		case '1':
			str = "<font color='blue'>使用中</font>";
			break;
		case '2':
			str = "<font color='yellow'>已使用</font>";
			break;
		}
		return str;
	},
	setResourceValid : function(starttime,endtime,carid){
		var html = "<form id='form1'>";
		html += "<input name='id' value='"+carid+"' type='hidden'>";
		html += '<label for="fusestarttime">开始日期：</label><input id="fusestarttime" name="fusestarttime" value="'+starttime+'" required="required" type="text" onClick="WdatePicker()" class="Wdate" placeholder="请输入开始日期"/>';
		html += '<br/><br/><label for="fuseendtime">结束日期：</label><input id="fuseendtime" name="fuseendtime" value="'+endtime+'" required="required" type="text" onClick="WdatePicker()" class="Wdate" placeholder="请输入结束日期"/>';
		html += "</form>";
		var K = null;
		K = KindEditor.dialog({
			width : 450,
			height : 200,
			title : '广告素材有效期',
			body : '<div class="table" id="txt_source_div" style="padding:5px; padding-top:30px;height:100%;overflow:auto;overflow-x:hidden;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {K.remove();}},
			yesBtn : {name : '提交',click : function(e){
				if(base.formIsNull()){
					var d1 = $("#fusestarttime").val();
					var d2 = $("#fuseendtime").val();
		   			if(d1 > d2){
		   				$("#fusestarttime").after("<font color='color' name='tips'>[起始日期不能大于终止日期]</font>");
		   				$("#fuseendtime").after("<font color='color' name='tips'>[起始日期不能大于终止日期]</font>");
		   				return;
		   			}
					$.post("modifyResourceValid",$("#form1").serialize(),function(data,status){
						if(status){
							if(data != 1){
								$.jBox.tip(data,'error');
							}else{
								con.loadAdvAndResourceByContract(1);
								K.remove();
							}
						}else{
							$.jBox.tip('广告素材有效期修改失败','error');
						}
					});
				}
			}},
			noBtn : {name : '关闭',click : function(e) {K.remove();}}
		});
	},
	toDate : function(str){
		var sd=str.split("-");
		return new Date(sd[0],sd[1],sd[2]);
	},
	previewResourceHtml : function(c){
		var ph = "";
		if(c.fresourceid || c.foriginalresourceid){
			ph = "<a href='javascript:void(0)' onclick=preview.listPreview('"+c.fpath+"','"+c.fname+"',"+c.fwidth+","+c.fheight+",500,330)>预览</a>";
			ph += "&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick=con.setResourceValid(\""+c.fusestarttime+"\",\""+c.fuseendtime+"\","+c.carId+")>修改</a>";
		}
		return ph;
	}
};