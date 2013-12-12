$(document).ready(function(){
	navTag("广告管理&gt;我的任务");
	myTask.load(1,0);
});
var fdelete = 0;
var myTask={
	load : function(page,deleted){
		if(deleted != undefined){
			fdelete = deleted;
		}
		
		$("#AllCheck").attr("checked",false);
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		var nid = $("#nid").val();
		
		_waiting._show();
		$.ajax({
			url:"myTask?"+$("#queryForm").serialize(),
			type:'post',
			data:{"nid":nid,"page":page,"limit":limit},
			dataType:'json',
			success:function(data){
				myTask.bindGrid(data,limit);
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	},
	bindGrid : function(D,limit){
		var html = '';
		var otherTotal = D.source.otherTotal;
		$("#totalOther").html("<font color='red'>"+otherTotal+"</font>");
		if(D.total > 0){
			$.each(D.source.tasks,function(i,obj){
				var id = "";
				if(obj.carid){
					id = "id="+obj.carid;
					if(i == 0){
						html += '<tr><th class="th">分公司</th><th class="th">素材名称</th><th class="th">提交时间</th><th class="th">状态</th><th class="th">操作</th></tr>';
					}					
					html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td>'+obj.branch+'</td>';
					html += '<td>'+obj.rname+'</td>';
					html += '<td>'+obj.fupdatetime+'</td><td>'+(obj.fisvalid == 1 ? "正常提交" : "<font color='red'>流程回退</font>" + "|<a href='javascript:void(0)' onclick='myTask.handleTask("+obj.id+")'>处理</a>")+'</td>';
					html += '<td><a href="../nodeStatus/queryContractAdvResourceStatus?'+id +'" target="_blank";">流程</a></td></tr>';

				}else if(obj.cid){
					id = "cid=" + obj.cid;
					if(i==0){
						html += '<tr><th class="th">分公司</th><th class="th">合同编号</th><th class="th">合同名称</th><th class="th">提交时间</th><th class="th">状态</th><th class="th">操作</th></tr>';
					}					
					html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td>'+obj.branch+'</td>';
					html += '<td>'+obj.fguid+'</td><td>'+obj.fcontractname+'</td>';
					html += '<td>'+obj.fupdatetime+'</td><td>'+(obj.fisvalid == 1 ? "正常提交" : "<font color='red'>流程回退</font>" + "|<a href='javascript:void(0)' onclick='myTask.handleTask("+obj.id+")'>处理</a>")+'</td>';
					html += '<td><a href="../nodeStatus/queryContractAdvResourceStatus?'+id +'" target="_blank";">流程</a></td></tr>';

				}else{
					id = "rvid="+obj.rvid;
					if(i == 0){
						html += '<tr><th class="th">分公司</th><th class="th">版本号</th><th class="th">解析度</th>';
						html += '<th class="th">提交时间</th><th class="th">状态</th><th class="th">操作</th></tr>';
					}
					html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td>'+obj.branch+'</td>';
					html += '<td>'+obj.fversion+'</td><td>'+obj.fdefinition+'</td>';
					html += '<td>'+obj.fupdatetime+'</td><td>'+(obj.fisvalid == 1 ? "正常提交" : "<font color='red'>流程回退</font>" + "|<a href='javascript:void(0)' onclick='myTask.handleTask("+obj.id+")'>处理</a>")+'</td>';
					html += '<td><a href="../nodeStatus/queryContractAdvResourceStatus?'+id +'" target="_blank";">流程</a></td></tr>';
				}
			});
		}else{
			html = "<tr><td colspan='7' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"myTask.load",limit:limit,count:D.total});
	},
	
	ajaxIOperateInfo : function(page){
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		if(!page)
			page = 1;
		var limit = base.getGlobalLimit();
		var nid = $("#nid").val();
		//参数
		var params = "?fnodeid="+nid+"&page="+page+"&limit="+limit+"&fdeleted="+(fdelete == 0 ? '' : fdelete);
		
		_waiting._show();
		$.ajax({
			url:"iOperateInfo"+params,
			type:'post',
			data:$("#queryForm1").serialize(),
			dataType:'json',
			success:function(data){
				myTask.iOperateInfoBindGrid(data,limit);
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	},
	iOperateInfoBindGrid : function(data,limit){
		var html = "";
		var iw = 180, ih = 120;
		if(data.total > 0){
			if(data.source.bool){
				html += '<table border="0" rules="none" cellpadding="0" cellspacing="0"><tr>';
				html += '<th class="th">合同编号</th>';
				html += '<th class="th">合同名称</th>';
				html += '<th class="th">广告商</th>';
				html += '<th class="th">代理商</th>';
				html += '<th class="th">开始时间|结束时间</th>';
				html += '<th class="th">创建人</th>';
				html += '<th class="th">联系人|电话</th>';
				html += '<th class="th">单价</th><th class="th"></th></tr>';
				$.each(data.source.list,function(i,obj){
					html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td>'+obj.fguid+'</td><td>'+obj.ftitle+'</td>';
					html += '<td>'+obj.fadvname+'</td><td>'+obj.fagent+'</td><td>'+obj.fstarttime+'|'+obj.fendtime+'</td><td>'+obj.fcreateuser+'</td>';
					html += '<td>'+obj.fcontactname+'|'+obj.fcontacttel+'</td><td>'+obj.fprice+'</td>';
					html += '<td><a href="javascript:void();" onclick="myTask.loadAdvAndResourceByContract('+obj.id+')">广告位素材信息</a></td></tr>';
				});
				html += '</table>';
			}else{
				$.each(data.source.list,function(i,s){
					var definition = s.fdefinition == "HD" ? "高清" : "标清";
					var cname = s.fname;
					//流程
					var nodestatus = '[<a href="../nodeStatus/queryContractAdvResourceStatus?id='+s.fcontractadvresourceid+'&bid='+$("#fbranchid").val()+'" target="_black";">流程</a>]';
					
					cname = cname.length > 9 ? cname.substring(0,9):cname;
					html += '<div class="img_div" id="tr'+i+'" title="素材名称：'+s.fname+'" style="width:220px;">';
					html += '<div class="imgDiv">'+preview.playHtml(s.fpath,s.fname,s.fwidth,s.fheight,iw,ih)+'</div>';
					html += '<div class="caption"><input type="checkbox" name="checkbox" id="checkbox'+i+'" onchange="check.cbCk('+i+',\'img_div\')" value="'+s.id+'" fchannelsid="'+(s.fexpand==undefined?"":s.fexpand)+'">';
					html += '状态：'+(s.fstatus == 1 ? "<font color='green'>通过</font>":"<font color='red'>未通过</font>");
					html += nodestatus;
					html +='<br/>素材名称：'+cname+'</br>广告位：'+s.ftype+'[<font color="green" >'+definition+'</font>]</div></div>';
				});
				html += '<div class="clear"></div>';
			}
		}else{
			html = "暂无您查询的记录";
		}
		$("#dataGrid_").html(html);
		
		$("#pager").pager({ pagenumber: data.page, pagecount: data.totalPage, buttonClickCallback: this.ajaxIOperateInfo, limitFun:"myTask.ajaxIOperateInfo",limit:limit,count:data.total});
	},
	/**
	 * 加载合同的广告位和素材
	 */
	loadAdvAndResourceByContract : function(id){
		$.post("../contract/loadAdvAndResourceByContract",{"id":id},function(data,status){
			if(status){
				var html = '<table style="width:100%;font-size:13px;" border="0" rules="none" cellpadding="0" cellspacing="0">';
				html += '<tr><th>广告位</th><th>素材</th><th>操作</th></tr>';
				$.each(data,function(i,c){
					html += "<tr><td>"+c.ftype+"|"+c.fdefinition+"</td><td>"+(c.fname != undefined ? c.fname: '')+"</td>";
					var nodestatus="";
					if(c.fresourceid || c.foriginalresourceid){
						nodestatus = '<a href="../nodeStatus/queryContractAdvResourceStatus?type=0&id='+c.carId+'&bid='+c.fbranchid+'" target="_black";">流程</a>';
						nodestatus += "|<a href='javascript:void(0)' onclick=preview.listPreview('"+c.fpath+"','"+c.fname+"',"+c.fwidth+","+c.fheight+",500,330)>预览</a>";
					}
					html += "<td align='right'>"+nodestatus+"</td></tr>";
				});
				html += "</table>";
				
				var body = '<div class="table" id="txt_source_div_1" style="padding:5px;overflow:auto;overflow-x:hidden;">'+html+'</div>';
				$.jBox(body,{"title":"合同广告位","width":920,"height":440,"top":"15%"});
			}else{
				$.jBox.tip('广告位加载失败','error');
			}
		},"json");
	},
	handleTask : function(id){
		$.ajax({
			url:"../nodeStatus/handleTask",
			type:'post',
			data:{"id":id},
			dataType:'json',
			success:function(data){
				$.jBox.tip("处理成功","success");
				myTask.load(1);
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('处理失败','error'); 
				_waiting._hide();
			}
		});
	},
	uploadResource : function(){
		
	}
};