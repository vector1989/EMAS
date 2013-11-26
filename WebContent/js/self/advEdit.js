function init(){
	$('#tab > ul').tabs({ fx: { opacity: 'toggle' } }).tabs();
	//设置属性面板值
	Adv.setPropertyPanel();
	Adv.setElementOrderBy();
	Adv.setAdvProperty();
}
//资源类型
var type = null;
//广告名称
var advName = "";
var Adv = {
		
	newAdv:function(data) {
		//设置属性面板值
		$("input[name=id]").val();
		$("#element").html("暂无信息");
		$("#eProject").html('<div class="safearea" id="advDesign" style="width: 1280px;height: 720px; opacity: 1; cursor: default;"></div>');
		this.setPropertyPanel();
	},
	setPropertyPanel : function(){
		var sd = $("#fdefinition").val();
		var width = "1920";
		var height = "1080";
		if(sd == "SD"){
			width = "720";
			height = "576";
		}
		$("input[name='fscreenwidth']").val(width);
		$("input[name='fscreenheight']").val(height);
		$("#advDesign").css("width",width);
		$("#advDesign").css("height",height);
	},
	showResource:function(page){
		/**
		 * 弹出资源列表
		 */
		var tab = '<div id="rotate"><ul><li><a href="#fragment-1" onclick="Adv.ajaxResource(,1);"><span>图片资源库</span></a></li>';
	    tab+= '<li><a href="#fragment-2"" onclick="Adv.ajaxResource(,2);"><span>视频资源库</span></a></li>';
	    tab+= '<li><a href="#fragment-3"" onclick="Adv.ajaxResource(,4);"><span>视频I帧</span></a></li>';
	    tab+= '<li><a href="#fragment-4"" onclick="Adv.ajaxResource(,3);"><span>文本资源库</span></a></li>';
	    tab+= '<li><a href="#fragment-5"" onclick="Adv.ajaxResource(,5);"><span>实时音视频</span></a></li></ul>';
	    tab+= '<div id="fragment-1"> 暂无信息</div>';
	    tab+= '<div id="fragment-2"> 暂无信息 </div>';
	    tab+= '<div id="fragment-3"> 暂无信息 </div>';
	    tab+= '<div id="fragment-4"> 暂无信息 </div>';
	    tab+= '<div id="fragment-5"> 暂无信息 </div></div>';
	    var pagerHml = '<div id="pagerTab"></div>';
	    var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 950,
			height : 540,
			title : '资源选择',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;">'+tab+pagerHml+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var select = base.selectValue("|");
					var resources = select.split("|");
					
					var divs = "";
					$.each(resources,function(i,resource){
						var rs = "{"+resource+"}";
						eval("var source="+rs);
						var divId = "div"+source.id;
						var div = $("#"+divId);
						if(!div[0]){
							divs += '<div class="move" onmousedown="Adv.mouseDrag(this.id);" onmousemove="Adv.mouseupDrag(this.id);" onfocus="Adv.setDivzIndex(this.id);" id="'+divId+'" style="z-index:'+i+';">';
							divs += '<img class="imgData" src="/EAMS/'+source.fpath+'" alt="'+source.fname+'" orderby="'+i+'" sourceType="'+source.ftype+'" jsdata="'+resource+'" id="img'+source.id+'" style="width:'+source.fwidth+'px;height:'+source.fheight+'px"></div>';
						}else{
							$.growlUI(source.fname+' 该资源已使用！');
						}
					});
					$("#advDesign").append(divs);
					kdialog.remove();
					Adv.setElementOrderBy();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
		 //$('#rotate > ul').tabs({ fx: { opacity: 'toggle' } }).tabs('rotate', 2000);
		// $('#rotate > ul').tabs({ fx: { opacity: 'toggle' } }).tabs();
		$('#rotate > ul').tabs({ event: 'click',fx: {opacity: 'toggle' },load:Adv.ajaxResource(1, 1)});
	},
	setElementOrderBy:function(){
        var imgs = $("img[class='imgData']");
        var li = "";
		var iw = 80, ih = 50;
		for(var j=0;j<imgs.length;j++){
			var iimg = $(imgs[j]);
			var width = iimg.css("width");
			width = width.replace("px","");
			var height = iimg.css("height");
			height = height.replace("px","");
			width = parseInt(width);
			height = parseInt(height);
			var zoomRate = Math.max( Math.max( width / iw, 1 ), Math.max( height / ih, 1 ));
			var w = width / zoomRate;
			var h = height / zoomRate;
			
//			var jsdata = iimg.attr('jsdata');
			var src = iimg.attr('src');
			var divId = iimg.attr('id');
			divId = divId.replace("img","div");
			//onmousemove   jsdata=\""+jsdata+"\"
			li += "<li order='"+j+"' id='li"+j+"' class='li' onclick='Adv.setDivzIndex(\""+divId+"\");'><span class='elementImg'><img src='"+src+"' width='"+w+"' height='"+h+"'/></span><a href='#' onclick='return Adv.moveLi(this,\"+\",\""+divId+"\");' order=\""+j+"\" title='上移'>∧</a><a href='#' onclick='return Adv.moveLi(this,\"-\",\""+divId+"\");' title='下移'>∨</a><a href='#' onclick='return Adv.moveLi(this,\"x\",\""+divId+"\");' title='删除'>×</a></li>";
        }
        $("#element").html("<ul>" + li+ "<ul>");
	},
	setDivzIndex : function(divId){
		var maxzIndex = this.getMaxZIndex();
		var zIndex = parseInt(maxzIndex) + 1;
		zIndex = zIndex == 1 ? 2 : zIndex;
		$("#"+divId).css("z-index",zIndex);
	},
	setAdvDesign:function(ele,pos,size){
		$("#"+ele).css(pos,size);
	},
	setAdvName:function(val){
		var eleId = this.getzIndex();
		var imgId = eleId.replace("div","img");
		var ele = $("#"+imgId);
		var jsdata = ele.attr("jsdata");
		jsdata = jsdata.replace(advName,val);
		ele.attr("jsdata",jsdata);
		ele.attr("alt",val);
		advName = "";
	},
	setFocusAdvName:function(val){
		advName = val;
	},
	setAdvDesignPosition:function(ele,position,size,isImg){
		ele = this.getzIndex();
		
		size = parseInt(size,10);
		$("#"+ele).css(position,(size+ 45)+"px");
		if(isImg){
			$("#"+ele).css(position,size+"px");
			var imgId = ele.replace("div","");
			$("#img"+imgId).css(position,size+"px");
		}
		
		var imgId = ele.replace("div","img");
		var img = $("#"+imgId);
		var data = img.attr("jsdata");
		var dataArr = data.split(",");
		var jsdata = "";
		for(var i=0;i<dataArr.length;i++){
			var property = dataArr[i];
			var kv = property.split(":");
			if(kv[0]=="'f"+position+"'"){
				kv[1] = "'"+size+"'";
			}
			jsdata += kv[0] +":"+ kv[1] + ",";
		}
		jsdata = jsdata.substring(0, jsdata.length-1);
		
		img.attr("jsdata",jsdata);
	},
	ajaxResource:function(page,t){
		if(t){
			type = t;
		}
		var fadvtype = $("#fadvtype").val();
		/**
		 * 加载资源信息
		 */
		if(!page){
			page = 1;
		}
		var limitParam = "limitRes";
		var limit = $("#"+limitParam).val();
		if(!limit){
			limit = 10;
		}
		$.ajax({
			url:"../resource/list",
			data:{"page":page,"limit":limit,"ftype":type,"fadvtype":fadvtype},
			type:"post",
			dataType:"json",
			success:function(data){
				Adv.bindGridResource(data,limit,limitParam);
			},
			error:function(data){
				$.growlUI('数据加载出错');
			}
		
		});
	},
	bindGridResource:function(data,limit,limitParam){
	//绑定资源信息
		var html = "";
		var iw = 160, ih = 120;
		if(data.total > 0){
			$.each(data.source,function(i,source){
				var zoomRate = Math.max( Math.max( source.fwidth / iw, 1 ), Math.max( source.fheight / ih, 1 ));
				var w = source.fwidth / zoomRate;
				var h = source.fheight / zoomRate;
				// onclick="Adv.setAdvDesignImg('+source.id+')"
//				alert(base.obj2Str(source));
				html += '<div name="tr'+i+'" id="tr'+i+'" onclick="base.selectTd(\''+i+'\');" class="tr img_div" style="width:180px;height:180px;" title="'+source.fname+'"><div class="imgDiv"><img src="/EAMS/'+source.fpath+'" width="'+w+'" height="'+h+'"/></div><div class="caption" style="font-size:12px;">广告位:'+source.temp+'<br/>时间：'+source.fcreatetime+'<br /><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+base.obj2Str(source)+'"/></div></div>';
			});
			html += '<div class="clear"></div>';
		}else{
			html = "暂无您查询的记录";
		}
		$("#fragment-"+type).html(html);
		$("#pagerTab").html('');
		$("#pagerTab").pager({ pagenumber: data.page, pagecount: data.totalPage, buttonClickCallback: this.ajaxResource, limitFun:"Adv.ajaxResource",limit:limit,limitParam:limitParam});
	},
	show:function(page){
		/**
		 * 显示
		 */
		if(!page){page = 1;}
		var limit = $("#limit").val();
		if(!limit){limit = 10;}
		$.ajax({
			url:"list",
			data:{"page":page,"limit":limit},
			type:"post",
			dataType:"json",
			success:function(data){
				Adv.bindGrid(data,limit);
			},
			error:function(data){
				$.growlUI('数据加载出错'); 
			}
		
		});
	},
	bindGrid:function(data,limit){
		$("#total").html('总共找到'+data.total+'条数据');
		var html = "";
		if(data.total > 0){
			$.each(data.source,function(i,source){
				
			});
		}else{
			html = "<tr><td colspan='9' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		$("#pager").pager({ pagenumber: data.page, pagecount: data.totalPage, buttonClickCallback: this.show, limitFun:"Adv.show",limit:limit});
	},
	ajax:function(url,param){
		/**
		 * ajax请求
		 */
		$.ajax({
			url:url,
			data:param,
			type:"post",
			dataType:"json",
			success:function(data){
				$.growlUI('数据操作成功'); 
			},
			error:function(data){
				$.growlUI('数据加载出错'); 
			}
		
		});
	},
	getMaxZIndex : function(){
		var maxZIndex = 0; //层最大数值
        var allTag = $("div[class='move']");
       
        //所有元素长度
        var allTipNum = allTag.length;
        for (var i = 0; i < allTipNum; i++) {
            var curZ = parseInt(allTag[i].style.zIndex);
            if (curZ > maxZIndex) {
                maxZIndex = curZ;
            }
        }
        
        return maxZIndex;
	},
	getzIndex : function() {
        var maxZIndex = this.getMaxZIndex();
        
        var allTag = $("div[class='move']");
       
        //所有元素长度
        var allTipNum = allTag.length;
        //z-index最大的对象
        var obj = null;
        for(var i = 0; i < allTipNum; i++){
        	obj = allTag[i];
        	if(obj.style.zIndex == maxZIndex){
        		break;
        	}
        }
        return obj.id;

	},
	mouseDrag:function(id){
		$("#"+id).myDrag();
		//this.mouseupDrag(id);
	},
	mouseupDrag:function(id){
		var left = $("#"+id).css("left");
		var top = $("#"+id).css("top");
		left = left.replace("px","")-45;
		top = top.replace("px","")-45;
		
		var imgId = id.replace("div","img");
		var width = $("#"+imgId).css("width");
		var height = $("#"+imgId).css("height");

		width = width.replace("px","");
		height = height.replace("px","");
		
		$("#fwidth").val(width);
		$("#fheight").val(height);
		$("#x").val(left);
		$("#y").val(top);
		$("#advname").val($("#"+imgId).attr("alt"));
		
		
		this.setProperty(imgId,width,height,left,top);
	},
	setProperty:function(id,width,height,left,top){
		var img = $("#"+id);
		var data = img.attr("jsdata");
		var dataArr = data.split(",");
		var jsdata = "";
		for(var i=0;i<dataArr.length;i++){
			var property = dataArr[i];
			var kv = property.split(":");
			switch (kv[0]) {
				case "'fwidth'":
					kv[1] = "'"+width+"'";
					break;
				case "'fheight'":
					kv[1] = "'"+height+"'";
					break;
				case "'fleft'":
					kv[1] = "'"+left+"'";
					break;
				case "'ftop'":
					kv[1] = "'"+top+"'";
					break;
			}
			jsdata += kv[0] +":"+ kv[1] + ",";
		}
		jsdata = jsdata.substring(0, jsdata.length-1);
		$("#"+id).attr("jsdata",jsdata);
		
		$("#fname").val(img.attr("alt"));
		$("#eleType").val(resourceType[img.attr("sourceType")]);
	},
	getPropertes:function(id){
		var data = $("#"+id).attr("jsdata");
		var rs = "{"+data+"}";
		eval("var source="+rs);

		return source;
	},
	moveLi:function(obj,opt,divId){
		var onthis = $(obj).parent();
		var editDiv = $("#"+divId);
		var order = parseInt(onthis.attr("order"));
		switch (opt) {
		case "+":
			var getup = onthis.prev();
			$(getup).before(onthis);
			onthis.attr("order",order-1);
			$(getup).attr("order",order);
			var div = editDiv.prev();
			$(div).before(editDiv);
			break;
		case "-":
			var getdown = onthis.next();
			$(getdown).after(onthis);
			onthis.attr("order",order+1);
			$(getdown).attr("order",order);
			var div = editDiv.next();
			$(div).after(editDiv);
			break;
		case "x":
			onthis.remove();
			$("#"+divId).remove();
			break;
		}
		
	},
	ajaxSubmit:function(){
		//编辑数据
		var imgs = $("img[class='imgData']");
		var length = imgs.length;
		var jsdata = '';
		var html = "";
		for(var i=0;i<length;i++){
			var img = $(imgs[i]);
			jsdata += img.attr("jsdata") + "。";
			if(!html){
				html = img.parent().parent().html();
			}
		}
		jsdata = jsdata.substring(0,jsdata.length-1);

//		var data = {"id":id,"fremark":remark,"tempSource":html,"fadvid":fadvid,"fserviceid":fserviceid,"fdefinition":fdefinition,"fexpand":editHtml,"temp":jsdata,"fname":fname};
		
		$("#form1").ajaxSubmit({
			url:"insert",
			type:'post',
			data:{"fexpand":html,"fremark":jsdata},
			dataType:'json',
			success:function(data){
				$.growlUI('数据保存成功');
			},
			error:function(msg){
				$.growlUI('数据保存失败'); 
			}
		});
		
		
//		this.ajax("insert", data);
	},
	preview:function(){
		var previewWindow = 0;
		var safearea = $("#safearea");
		if( previewWindow == 0 || previewWindow.closed ){
			previewWindow = window.open( '', '_blank', 'width=' + safearea.attr("width") + ',height=' + safearea.attr("height") );
		}

		previewWindow.focus();
		previewWindow.document.write( "<style>.move{position:absolute;z-index:3;background: #ccc;border: 1px solid #ccc;}.safearea {z-index: 2;background: #ccc;border: 35px solid #114a11;margin:5px;}</style>"+$("#eProject").html());
		previewWindow.document.close();
	}
	,loadData : function(id){
		$.ajax({
			url:"selectByKey",
			data:{"id":id},
			type:"post",
			dataType:"json",
			success:function(data){
				Adv.newAdv(eval("{"+data+"}"));
			},
			error:function(data){
				$.growlUI('数据加载出错'); 
			}
		});
	},
	setAdvProperty : function(){
		var option = $("#fadvbaseid").find("option:selected");
		var attr = option.attr("attr");
		var attrs = attr.split(",");
		//获取fpositionid
		var fpositionid = $("#fpositionid").val();
		for(var i = 0,length = attrs.length;i<length;i++){
			var arr = attrs[i].split(":");
			$("input[name='"+arr[0]+"']").val(arr[1]);
			if(arr[0]=='fpositionid' && fpositionid != arr[1]){
				this.changeTime(arr[1]);
			}
		}
		this.setPropertyPanel();
	},
	changeTime :function(fpositionid){
		//加载广告时间
		if(fpositionid)
			$.ajax({
				url:"../time/queryByFpositionid",
				data:{"fpositionid":fpositionid},
				type:"post",
				dataType:"json",
				success:function(data){
					var html = '';
					$.each(data,function(i,o){
						html += '<option value="'+o.id+'">'+o.fstarttime+'--'+o.fendtime+'</option>';
					});
					$("#ftimeperiodid").html(html);
				},
				error:function(data){
					$.growlUI('数据加载出错'); 
				}
			});
	}
};
function bindResize(el) {
    //初始化参数
    var els = el.style,
    //鼠标的 X 和 Y 轴坐标
    x = y = 0;
    //邪恶的食指
    $(el).mousedown(function(e) {
        //按下元素后，计算当前鼠标与对象计算后的坐标
        x = e.clientX - el.offsetWidth,
        y = e.clientY - el.offsetHeight;
        //在支持 setCapture 做些东东
        el.setCapture ? (
        //捕捉焦点
        el.setCapture(),
        //设置事件
        el.onmousemove = function(ev) {
            mouseMove(ev || event);
        },
        el.onmouseup = mouseUp) : (
        //绑定事件
        $(document).bind("mousemove", mouseMove).bind("mouseup", mouseUp));
        //防止默认事件发生
        e.preventDefault();
    });
    //移动事件
    function mouseMove(e) {
        //宇宙超级无敌运算中...
        els.width = e.clientX - x + 'px';
        els.height = e.clientY - y + 'px';
    }
    //停止事件
    function mouseUp() {
        //在支持 releaseCapture 做些东东
        el.releaseCapture ? (
        //释放焦点
        el.releaseCapture(),
        //移除事件
        el.onmousemove = el.onmouseup = null) : (
        //卸载事件
        $(document).unbind("mousemove", mouseMove).unbind("mouseup", mouseUp));
    }
}
var resourceType = ["图片","文本","实时的音视频","视频I帧","视频文件"];