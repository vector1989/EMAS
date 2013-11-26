// <summary>//绑定视频播放 
// 绑定视频播放 
// </summary> 
// <param name="width">播放器宽度</param> 
// <param name="height">播放器高度</param> 
// <param name="link">播放文件地址</param> 
// <returns></returns> 
function mediaPlayer(url,width, height,ShowStatusBar,ShowControls) {
	var href = window.location.href;
	href = "/" + href.split("/")[3] + "/";
	if(url.indexOf(href) == -1){
		url = href + url;
	}
	if(ShowStatusBar != 0) ShowStatusBar = 1;
	if(ShowControls != 0) ShowControls = 1;
	var str = '<object width="'+width+'" height="'+height+'" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">';
    	str += '<param name="url" value="'+url+'"/>';
    	
    	str += '<embed width="'+width+'" height="'+height+'" type="application/x-mplayer2" ShowControls="'+ShowControls+'" ShowDisplay="0" ShowStatusBar="'+ShowStatusBar+'" src="'+url+'" loop="true" quality="high" standby="Loading..."></embed></object>';
	return str;
//	return pv_m(url,width,height);
}

function playHtml(url,width,height,iw,ih){
//	var url = "/EAMS/"+p;
	var suffix = url.split(".");
	var html = "";
	if(iw) iw = 400;
	if(ih) ih = 140;
	var zoomRate = Math.max( Math.max( width / iw, 1 ), Math.max( height / ih, 1 ));
	var w = width / zoomRate;
	var h = height / zoomRate;
	if(suffix[suffix.length-1] == "jpg" || suffix[suffix.length-1] == "jpeg"){
		html = "<img src='"+url+"' width='"+w+"' height='"+h+"' />";
	}else
		html = mediaPlayer(url, w, h);//height:100%;
	html = '<div id="txt_div" style="padding:0px;overflow:auto;overflow-x:hidden;margin:2px; height:'+(h + 5)+'">'+html+'</div>';
	
	return html;
}