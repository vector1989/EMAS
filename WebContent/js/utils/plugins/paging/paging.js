/**
 * 手动分页
 */
KindEditor.plugin('paging', function(K) {
        var editor = this, name = 'paging';
        // 点击图标时执行
        editor.clickToolbar(name, function() {
        	editor.insertHtml('<hr custompaging="true"/>');
        });
});