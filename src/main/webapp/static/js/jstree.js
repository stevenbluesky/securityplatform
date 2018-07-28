var jsontree ;
$(function() {
    //读取菜单, 异步请求
    getMenu();
});

function getMenu() {
    $.ajax({
        url : 'getMenuTree',//请求的url
        dataType : 'json',//返回的数据类型: jquery把返回的值转成json数据
        type : 'post',//请求的方式
        success : function(obj) {
            //把数据中的菜单赋值
            jsontree = JSON.stringify(obj);
            var $tree = $('#treeview12').treeview({
                data:jsontree,
                enableLinks:false
            });
            $("#right").attr("src",JSON.parse(jsontree)[0].href);
            $('#treeview12').on('nodeSelected', function(event, data) {
                $("#right").attr("src",data.href);
            });
        },
        error:function (obj) {
            //alert("Menu Load Failed!");
            window.location.href="../login";
        }
    });
}