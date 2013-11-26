$(document).ready(function() {
	navTag("资源库&gt;素材管理");
	Resource.show();
});

var Resource = {};

function $$(id) {
	return document.getElementById(id);
}

/**
 * 显示
 */
Resource.show=function(page){
	$("#AllCheck").attr("checked",false);
	if(!page){
		page = $("li[class='page-number pgCurrent']").text();
	}
	var limit = base.getGlobalLimit();
	_waiting._show();
	$("#queryForm").ajaxSubmit({
		url:"list",
		type:'post',
		data:{"page":page,"limit":limit},
		dataType:'json',
		success:function(data){
			Resource.bindGrid(data,limit);
			_waiting._hide();
		},
		error:function(msg){
			$.jBox.tip('数据加载失败','error'); 
			_waiting._hide();
		}
	});
};
Resource.bindGrid = function(data,limit){
	var html = "";
	var iw = 160, ih = 120;
	if(data.total > 0){
		var basePath = _waiting.getRootPath() + "/";
		$.each(data.source,function(i,source){
			if(source.ftype==0){
				$("#navText").html("资源库&gt;图片资源");
				var zoomRate = Math.max( Math.max( source.fwidth / iw, 1 ), Math.max( source.fheight / ih, 1 ));
				var w = source.fwidth / zoomRate;
				var h = source.fheight / zoomRate;
				var cname = source.contractName;
				cname = cname.length > 9 ? cname.substring(0,9):cname;
				html += '<div class="img_div" id="tr'+i+'" onclick="check.divCk('+i+',\'img_div\')" title="合同名称：'+source.contractName+'"><div class="imgDiv"><img src="'+basePath+source.fpath+'" width="'+w+'" height="'+h+'"/></div>';
				html += '<div class="caption"><a href="download?filePath='+source.fpath+'&fileName='+source.fname+'">下载</a>';
				html += '<input type="radio" name="checkbox" id="checkbox'+i+'" value="'+source.id+'" isSource="0" onchange="check.divCk('+i+',\'img_div\')">广告位:'+source.temp + "|" + source.fdefinition;
				html +='<br/>合同名称：'+cname+'</br>时间：'+source.fcreatetime+'</div></div>';
			}else{
				if(i==0){
					html = '<table border="0" rules="none" cellpadding="0" cellspacing="0"><tr>';
					html += '<th class="th"></th><th class="th">资源类型</th><th class="th">资源名称</th><th class="th">合同名称</th>';//<input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox">
					html += '<th class="th">广告位</th><th class="th">用户</th><th class="th">分公司</th><th class="th">上传时间</th><th class="th">操作</th></tr><tbody id="dataGrid">';
				}
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'" onclick="check.divCk('+i+',\'tr\')"><td><input type="radio" isSource="0" name="checkbox" id="checkbox'+i+'" value="'+source.id+'" onchange="check.divCk('+i+',\'tr\')"></td>';//<input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+source.id+'"onclick="check.cbCk('+i+')">
				html += '<td>'+(source.ftype==0?"图片":(source.ftype==3)?"I帧" : (source.ftype==11) ? "数据资源" :"视频资源")+'</td>';
				html += '<td>'+source.fname+'</td>';
				html += '<td>'+source.contractName+'</td>';
				html += '<td>'+source.temp + "|" + source.fdefinition+'</td>';
				html += '<td>'+source.userName+'</td>';
				html += '<td>'+source.branch+'</td>';
				html += '<td>'+source.fcreatetime+'</td>';
				html += "<td>";
				if(source.ftype==11){
					$("#navText").html("资源库&gt;数据资源");
					if(source.fisoriginalresource == 0){
						html += '<span style="color:red;">已编辑</span>|';
					}
					html += '<a href="javascript:void(0);" onclick="upload.modify(\''+source.id+'\',\''+source.contractAdvId+'\',\''+source.fdefinition+'\',\''+source.fbranchid+'\',\''+source.fpositionid+'\',\''+source.fadvclassid+'\',\''+source.carId+'\');">修改</a>|';
				}else if(source.ftype==10){
					$("#navText").html("资源库&gt;视频资源");
				}else{
					$("#navText").html("资源库&gt;I帧资源");
				}
				html += '<a href="download?filePath='+source.fpath+'&fileName='+source.fname+'">下载</a>|';
				html += '<a href="javascript:void(0);" onclick="preview.listPreview(\''+source.fpath+'\',\''+source.fname+'\',\''+source.fwidth+'\',\''+source.fheight+'\')">浏览</a></td></tr>';
				if(data.source.length==(i+1)){
					html += '</tbody></table>';
				}
			}
		});
		html += '<div class="clear"></div>';
	}else{
		html = "暂无您查询的记录";
	}
	$("#dataGrid").html(html);
	$("#pager").pager({ pagenumber: data.page, pagecount: data.totalPage, buttonClickCallback: this.show, limitFun:"Resource.show",limit:limit,count:data.total});
};
/**
 * 加载分公司广告位
 */
Resource.loadAdv = function(fbranchid){
	$.post("loadAdv",{"fbranchid":fbranchid,"ftype":$("#ftype").val()},function(data,status){
		if(status){
			var advHtml = "<option value=''>==请选择==</option>";
			$.each(data,function(i,a){
				advHtml += "<option value="+a.id+">"+a.ftype+"|"+a.fdefinition+"</option>";
			});
			$("#fadvclassid").html(advHtml);
		}else{
			$.jBox.tip("数据加载失败","error");
		}
	},"json");
};
/***
 * 预览资源信息
 * @param url
 * @param width
 * @param height
 */
function play(url,width,height){
	var suffix = url.split(".");
	var html = "";
	var iw = 400, ih = 350;
	var zoomRate = Math.max( Math.max( width / iw, 1 ), Math.max( height / ih, 1 ));
	var w = width / zoomRate;
	var h = height / zoomRate;
	if(suffix[suffix.length-1] == "jpg" || suffix[suffix.length-1] == "jpeg"){
		html = "<img src='"+url+"' width='"+w+"' height='"+h+"' />";
	}else
		html = mediaPlayer(url, w, h);//height:100%;
	html = '<div id="txt_div" style="padding:0px;overflow:auto;overflow-x:hidden;margin:2px;">'+html+'</div>';
	$.jBox.open(html,"广告浏览",w + 10,h + 100);
}