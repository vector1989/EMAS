var preview = {
	listPreview:function(p,name,width,height){
		var suffix = p.split(".");
		//列表中预览
		var zoomRate = this.zoomRate(width,height,800,600);
		var w = width / zoomRate;
		var h = height / zoomRate;
		if(suffix[suffix.length-1] == "jpg" || suffix[suffix.length-1] == "jpeg"){
			var html = this.playHtml(p,name,width,height);
			$.jBox.open(html,"广告浏览",w+5,h+70,{"top":"3%"});
		}else{
//			var url = "/EAMS/"+p;			
			this.previewVedio(p,w,h);
		}
	},
	zoomRate : function (width,height,iw,ih) {
		if(!iw)iw = 400;
		if(!ih)ih = 350;
		return Math.max( Math.max( width / iw, 1 ), Math.max( height / ih, 1 ));
	},
	playHtml : function(url,name,width,height,iw,ih){
		var href = window.location.href;
		href = "/" + href.split("/")[3] + "/";
		if(url.indexOf(href) == -1){
			url = href + url;
		}
		var suffix = url.split(".");
		var html = "";
		if(suffix[suffix.length-1] == "jpg" || suffix[suffix.length-1] == "jpeg"){
			var zoomRate = this.zoomRate(width,height,iw,ih);
			var w = width / zoomRate > 0 ? width / zoomRate : iw;
			var h = height / zoomRate > 0 ? height / zoomRate : ih;
			html = "<img src='"+url+"' width='"+w+"' height='"+h+"' />";
		}else{
			var zoomRate = this.zoomRate(width,height,800,600);
			var w = width / zoomRate > 0 ? width / zoomRate : 800;
			var h = height / zoomRate > 0 ? height / zoomRate : 600;

			html = "<br/><br/><br/><a href='javascript:void(0);' onclick=preview.previewVedio('"+url+"',"+w+","+h+")>点击预览</a><br/>";
			if(name)
				html += "素材名称：" + name;
		}
		return html;
	},
	previewVedio : function (url, width, height){
		var iTop = (window.screen.availHeight-width)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth-height)/2; //获得窗口的水平位置;
		var previewWindow = window.open( '', '_blank', 'width=' + width + ',height=' + height +',top='+iTop+',left='+iLeft+',z-look:true,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
		previewWindow.focus();
		previewWindow.document.write( mediaPlayer(url, width-30, height-30));
		previewWindow.document.close();
	},
	previewContract:function(id){
		_waiting._show();
		$.post("../contract/previewContract",{"id":id},function(data,status){
			if(status){
				var contract = data.contract;
				html = '<div style="margin:0px 20px;height:100%;overflow:auto; overflow-x:hidden;"><table style="width:100%;font-size:13px;border:1px;"><label style="text-align: center;width:99%;font-size: 18px">合同基本信息</label><br/>';
				html += '<tr><td>合同编号：'+contract.fguid+'</td><td>合同名称：'+contract.ftitle+'</td><td>广告商：'+contract.fadvname+'</td></tr>';//<td>解析度：'+contract.fdefinition+'</td>
				html += '<tr><td>代理商：'+contract.fagent+'</td><td>联系人：'+contract.fcontactname+'</td><td>联系电话：'+contract.fcontacttel+'</td></tr>';
				html += '<tr><td>单价：'+contract.fprice+'</td><td>折扣：'+contract.fdiscount+'</td><td>付款方式：'+contract.fpayway+'</td></tr>';
				html += '<tr><td>广告级别：'+contract.fadvlevel+'</td><td>起始日期：'+contract.fstarttime+'</td><td>终止日期：'+contract.fendtime+'</td></tr>';
				html += '</table><label style="text-align: center;width:100%;font-size: 18px">合同内容</label><br/>'+contract.fcontent+'</div>',
				$.jBox.open(html,"合同浏览",950,520,{"showScrolling":false,"top":"2px"});
			}else{
				$.jBox.tips('数据加载失败','error');
			}
			_waiting._hide();
		},"json");
	}
};