var cookie = {
	//添加cookie
	addCookie : function(name, value, expiresDays) {
		var cookieString = name + "=" + escape(value);
		// 判断是否设置过期时间
		if (expiresDays > 0) {
			var date = new Date();
			date.setTime(date.getTime + expiresDays * 3600 * 1000 * 24);
			cookieString = cookieString + "; expires=" + date.toGMTString();
		}
		alert(cookieString);
		document.cookie = cookieString;
	},
	//获取cookie
	getCookie : function(name) {
		var strCookie = document.cookie;
		alert(strCookie);
		var arrCookie = strCookie.split("; ");
		for ( var i = 0; i < arrCookie.length; i++) {
			var arr = arrCookie[i].split("=");
			if (arr[0] == name)
				return arr[1];
		}
		return "";
	},
	//删除cookie
	deleteCookie : function(name) {
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = name + "=''; expires=" + date.toGMTString();
	}
};