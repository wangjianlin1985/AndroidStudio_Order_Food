//设置页面的选中状态
var url = window.location.href;
var idVal = url.split("=")[1]
var menuIdStr = "#id_"+idVal;
$("li").removeClass('active');
$(menuIdStr).parent().addClass('active');
$(menuIdStr).parent().parent().parent().addClass('nav-item nav-item-has-subnav open')


