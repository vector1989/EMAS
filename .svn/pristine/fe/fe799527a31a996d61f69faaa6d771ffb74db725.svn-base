/**
 * 导航条
 * @basePath 基本路径
 * @navText 导航文本
 * @id 绑定标签
 * @imgPath 图片路径
 */
var navTag = function(navText,id,imgPath){
	var location = (window.location+'').split('/'); 
	var basePath = location[0]+'//'+location[2]+'/'+location[3];
	if(!id) id = "nav_logo";
	if(!imgPath) imgPath = "images";
	var html = '<table width="100%" height="30" border="0" cellspacing="0" cellpadding="0">';
		html += '<tr>';
		html += '<td width="7" style="background-image: url(\''+basePath+"/"+imgPath+'/tab_03.gif\');"></td>';
		html += '<td width="20" style="background-image: url(\''+basePath+"/"+imgPath+'/tab_05.gif\');">';
		html += '<div align="center"><img src="'+basePath+"/"+imgPath+'/hi.gif" width="16" height="16" /></div>';
		html += '</td>';
		html += '<td style="background-image: url(\''+basePath+"/"+imgPath+'/tab_05.gif\');"><span>你当前的位置</span>：<span id="navText">'+navText+'</span></td>';
		html += '<td width="12" style="background-image: url(\''+basePath+"/"+imgPath+'/tab_07.gif\');"></td>';
		html += '</tr></table>';
	$("#"+id).html(html);
	
	pageTag(basePath,imgPath);
};

var pageTag = function(basePath,imgPath){
	if(!basePath){
		var location = (window.location+'').split('/'); 
		basePath = location[0]+'//'+location[2]+'/'+location[3]; 
	}
	if(!imgPath) imgPath = "images";
	basePath += "/"+imgPath;
	var html = '<table width="100%" height="0" border="0" cellspacing="0" cellpadding="0">';
		html += '<tr>';
		html += '<td width="7" style="background-image: url(\''+basePath+'/tab_18.gif\');"></td>';
		html += '<td style="background-image: url(\''+basePath+'/tab_19.gif\');"><span id="pager"></span></td>';
		html += '<td width="12" style="background-image: url(\''+basePath+'/tab_20.gif\');"></td>';
		html += '</tr></table>';
	$("#pager_bar").html(html);
};