function $box(id){return document.getElementById(id);}
//var isIE = (document.all) ? true : false;
function Box(objID,W,H,inner,footerInner,zIndex,bZIndex){
	if(W==undefined){
		zIndex = 999;
	}
	if(bZIndex == undefined){
		bZIndex = 900;
	}
	var objHead=null,objBody=null,objFooter,inner,footerInner;
	if(!$box(objID)){
	//创建html结构并赋id以及class		
		var obj = document.createElement("div");		
		var objHead = document.createElement("div");
		var objBody = document.createElement("div");
		var objFooter = document.createElement("div");
		obj.id=objID;
		obj.className="lightBox";		
		objHead.id=objID+"_head";
		objBody.id=objID+"_body";
		objFooter.id=objID+"_footer";
		objHead.className="Boxheader";
		objBody.className="Boxbody";
		objFooter.className="Boxfooter";		
		obj.appendChild(objHead);
		obj.appendChild(objBody);
		obj.appendChild(objFooter);
		document.body.appendChild(obj);
		//如果参数为空时的处理
		if(W==undefined||W<200||W==""){W=200;}//最小宽度
		if(H==undefined||H<60||H==""){H=60;}
		if(inner==undefined || inner=="" ){inner="";}
		if(footerInner==undefined || footerInner=="" ){footerInner="";}//页脚没有内容时默认值
		var BodyLid = objID+"BodyL",BodyRid = objID+"BodyR",headerLid = objID+"headerL",FooterLid= objID+"FooterL",closeBtn= objID+"closeBtn",cancelBtn =objID+"cancel";		
		objBody.innerHTML='<div class="BodyL" id='+BodyLid+'>'+inner+'</div><div class="BodyR" id='+BodyRid+'></div>';
		objHead.innerHTML='<div class="headerL" id='+headerLid+'><span style="float:right;background:url(/EMSP_CMS/images/box/lightbox-close.png) no-repeat 0 center;display:inline-block;height:46px;width:16px;cursor:pointer;" id='+closeBtn+'></span></div><div class="headerR"></div>';
		objFooter.innerHTML='<div class="FooterL" id='+FooterLid+'>'+footerInner+'</div><div class="FooterR"></div>';
		//js样式设置
		$box(BodyLid).style.height=H+"px";
		$box(BodyLid).style.width=W+"px";
		$box(FooterLid).style.width=$box(headerLid).style.width=W+"px";
		$box(BodyRid).style.height=$box(BodyLid).clientHeight+"px";		
		obj.style.cssText+=";position:absolute;left:50%;top:50%;z-index:"+zIndex+";";		
		obj.style.marginLeft=-obj.scrollWidth/2+"px";
		obj.style.marginTop=-obj.scrollHeight/2+"px";	
		
		document.body.style.cssText+="height:100%;overflow:hidden;";		
		var mask=1;//是否创建遮罩层
		if(mask){
			var objMask = document.createElement("div");
			objMask.id="Mask";objMask.className="BoxMask";
			document.body.appendChild(objMask);objMask.style.cssText+=";position:absolute;z-index:"+bZIndex+";";
			objMask.style.height=document.documentElement.scrollHeight+document.documentElement.scrollTop+"px";
		}
		function Close(objID){
			document.body.removeChild($box(objID));document.body.style.cssText+="width:100%;overflow:auto;overflow-y:hidden;"; if(objMask){document.body.removeChild(objMask);}
		}
		$box(closeBtn).onclick = function(){Close(objID);};//关闭按钮
		if($box(cancelBtn)){$box(cancelBtn).onclick = function(){Close(objID);}}//取消按钮
		
		//拖动功能			
	//if(evt.keycode==27){alert("esc")};
	var w = obj.scrollWidth,h = obj.scrollHeight;
	var iWidth = document.documentElement.clientWidth; 
	var iHeight = document.documentElement.clientHeight; 	
	var moveX = 0,moveY = 0,moveTop = 0,moveLeft = 0,moveable = false;
		objHead.onmousedown = function(e) {	
		moveable = true; 	
		e = window.event?window.event:e;
		moveX = e.clientX-obj.offsetLeft;		
		moveY = e.clientY-obj.offsetTop;
		obj.style.zIndex++;
		document.onmousemove = function(e) {
				if (moveable) {
				e = window.event?window.event:e;		
				var x = e.clientX - moveX;
				var y = e.clientY - moveY;
					if ( x > 0 &&( x + w < iWidth) && y > 0 && (y + h < iHeight) ) {
						obj.style.left = x + "px";
						obj.style.top = y + "px";
						obj.style.margin = "auto";
						}
					}
				}
				document.onmouseup = function () {moveable = false;};
		}
	}else(alert("has been opened!"));
	
}//box();End
