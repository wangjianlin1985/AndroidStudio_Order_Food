$(document).ready(function(e){
    $('#orderTable').bootstrapTable({
        url: '/order/listpage',                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        //toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        iconsPrefix: 'mdi', // 自定义表格右上角的图标，这个必须要定义，因为不是官方模板，icon图标样式名字不同会导致图标无法显示
        icons:  {
            refresh: 'mdi-refresh',              // 刷新图标
            columns: 'mdi-format-list-bulleted', // 列图标
            toggleOff: 'mdi-toggle-switch-off',  // 切换光
            toggleOn: 'mdi-toggle-switch',       // 切换开
            detailOpen: 'mdi-plus',              // 详情开
            detailClose: 'mdi-minus',            // 详情光
            fullscreen: 'mdi-fullscreen'         // 全屏图标
        },
        search: false,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        singleSelect: false, //开启单选,想要获取被选中的行数据必须要有该参数
        queryParams : function(params) {//上传服务器的参数
            var temp = {
                offset :params.offset + 0,// SQL语句起始索引
                page : params.limit,  // 每页显示数量
                ordernum:params.ordernum
            };
            return temp;
        },
        columns: [
            {
                align : 'center',
                checkbox: true
            },{
                field: 'ordernum',
                title: '订单编号'
            }, {
                field: 'isPay',
                title: '是否支付',
                formatter:function(value,row,index){
                    if(row.isPay==1){
                        return "已支付"
                    }else{
                        return "未支付"
                    }
                }
            }, {
                field: 'price',
                title: '订单价格'
            }, {
                field: 'address',
                title: '配送地址',
                align: 'center'
            }, {
                field: 'username',
                title: '下单用户'
            }, {
                field: 'createTime',
                title: '创建时间'
            }, {
                field: 'tel',
                title: '联系电话'
            }]
    });




})





$(document).ready(function(e) {

    $("button,a").on('click',function(){
        //获取到 a标签里面配置 data-method
        var methodName = $(this).data('method');
        if(methodName){
            doMethod[methodName]();
        }
    });
    var doMethod = {

        search:function(){
            var ordernum = $("#q_ordernum").val();
            var queryparam = {
                silent:true,
                query:{
                    ordernum:ordernum
                }
            };
            $('#orderTable').bootstrapTable('refresh',queryparam);

        }

    }





})