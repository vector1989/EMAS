/*
* jQuery pager plugin
* Version 1.0 (12/22/2008)
* @requires jQuery v1.2.6 or later
* Download by http://www.codefans.net
* Example at: http://jonpauldavies.github.com/JQuery/Pager/PagerDemo.html
*
* Copyright (c) 2008-2009 Jon Paul Davies
* Dual licensed under the MIT and GPL licenses:
* http://www.opensource.org/licenses/mit-license.php
* http://www.gnu.org/licenses/gpl.html
* 
* Read the related blog post and contact the author at http://www.j-dee.com/2008/12/22/jquery-pager-plugin/
*
* This version is far from perfect and doesn't manage it's own state, therefore contributions are more than welcome!
*
* Usage: .pager({ pagenumber: 1, pagecount: 15, buttonClickCallback: PagerClickTest });
*
* Where pagenumber is the visible page number
*       pagecount is the total number of pages to display
*       buttonClickCallback is the method to fire when a pager button is clicked.
*
* buttonClickCallback signiture is PagerClickTest = function(pageclickednumber) 
* Where pageclickednumber is the number of the page clicked in the control.
*
* The included Pager.CSS file is a dependancy but can obviously tweaked to your wishes
* Tested in IE6 IE7 Firefox & Safari. Any browser strangeness, please report.
*/
(function($) {

    $.fn.pager = function(options) {

        $.extend({}, $.fn.pager.defaults, options);

        return this.each(function() {

        // empty out the destination element and then render out the pager with the supplied options
            $(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount), options.buttonClickCallback,options.limitFun,options.limit,options.limitParam,options.count));
            
            // specify correct cursor activity
            $('.pages span').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto"; });
        });
    };

    // render and return the pager with the supplied options
    function renderpager(pagenumber, pagecount, buttonClickCallback,limitFun,limit,limitParam,count) {
        
    	$(window.parent.parent.frames["topFrame"].document).find("#global_limit").val(limit);
    	
    	if(!limitParam){
        	limitParam = "limit";
        }
    	// setup $pager to hold render
        var $pager = $('<div class="pages"></div>');
        // add in the previous and next buttons
        $pager.append(renderButton('首页', pagenumber, pagecount, buttonClickCallback)).append(renderButton('上一页', pagenumber, pagecount, buttonClickCallback));
        
        // pager currently only handles 10 viewable pages ( could be easily parameterized, maybe in next version ) so handle edge cases
        var startPoint = 1;
        var endPoint = 9;

        if (pagenumber > 4) {
            startPoint = pagenumber - 4;
            endPoint = pagenumber + 4;
        }

        if (endPoint > pagecount) {
            startPoint = pagecount - 8;
            endPoint = pagecount;
        }

        if (startPoint < 1) {
            startPoint = 1;
        }

        // loop thru visible pages and render buttons
        for (var page = startPoint; page <= endPoint; page++) {

            var currentButton = $('<span class="page-number">' + (page) + '</span>');

            page == pagenumber ? currentButton.addClass('pgCurrent') : currentButton.click(function() { buttonClickCallback(this.firstChild.data); });
            currentButton.appendTo($pager);
        }

        // render in the next and last buttons before returning the whole rendered control back.
        $pager.append(renderButton('下一页', pagenumber, pagecount, buttonClickCallback)).append(renderButton('末页', pagenumber, pagecount, buttonClickCallback));
        
        var disable = 'disabled="disabled"';
        var border = 'border: 1px solid #eee;color: #eee;';
        var opt = selectLimitPage(limit);
        $pager.append("<span style='border:0;padding:0;'><select id='"+limitParam+"' "+(pagecount > 0 ? "" : disable)+" style='width:50px;height:26px; "+(pagecount > 0 ? "" : border)+"' onchange='"+limitFun+"()'>" + opt + "</select></span>");
        if(count)
        	$pager.append("&nbsp;<span style='border:1px solid #ccc;padding:5px;'>总计：" + count +"条</span>");

        //每页条数限制
        return $pager;
    }

    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button
    function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback) {

        var $Button = $('<span class="pgNext">' + buttonLabel + '</span>');

        var destPage = 1;

        // work out destination page for required button type
        switch (buttonLabel) {
            case "首页":
                destPage = 1;
                break;
            case "上一页":
                destPage = pagenumber - 1;
                break;
            case "下一页":
                destPage = pagenumber + 1;
                break;
            case "末页":
                destPage = pagecount;
                break;
        }

        // disable and 'grey' out buttons if not needed.
        if (buttonLabel == "首页" || buttonLabel == "上一页") {
            pagenumber <= 1 ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });
        } else {
            pagenumber >= pagecount ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });
        }

        return $Button;
    }
    
    function selectLimitPage(limit){
    	var options = "";
        var select = "selected='selected'";
        if(!limit) limit = 10;
    	for(var i=0;i<10;i++){
    		var v = i* 5;
    	    options += "<option value='"+v+"'  "+((limit==v) ?select:'')+">"+(v == 0 ? '*' : v)+"</option>";
    	}
    		
    	return options;
    }
    
    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version
    $.fn.pager.defaults = {pagenumber: 1,pagecount: 1};

})(jQuery);





