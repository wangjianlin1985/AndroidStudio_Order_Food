$(document).ready(function(e){
    $('#foodTable').bootstrapTable({
        url: '/food/listpage',                      //请求后台的URL（*）
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
                foodName:params.foodName
            };
            return temp;
        },
        columns: [
            {
                align : 'center',
                checkbox: true
            },{
                field: 'id',
                title: '食物编号'
            }, {
                field: 'foodName',
                title: '食物名称'
            }, {
                field: 'taste',
                title: '口味'
            }, {
                field: 'price',
                title: '价格',
                align: 'center'
            }, {
                field: 'foodType',
                title: '食物类型',
                formatter:foodTypeFormatter
            }, {
                field: 'createTime',
                title: '创建时间'
            }, {
                field: 'foodPic',
                title: '食物图片',
                formatter:foodPicFormatter
            },{
                field: 'doOpt',
                title: '操作',
                formatter : optFormatter
            }]
    });

    function foodTypeFormatter(value,row,index){
        var str = "";
        switch (row.foodType) {
            case 1: str = '热菜'; break;
            case 2: str = '凉菜'; break;
            case 3: str = '甜品'; break;
            case 4: str = '干锅'; break;
            case 5: str = '汤'; break;
            case 6: str = '饮料'; break;
            case 7: str = '米饭'; break;
        }
        return str;
    }

    function optFormatter(value,row, index){
        var c = '<a class="btn btn-xs btn-default" href="#!"  onclick=\'edit("' + row.id + '")\' title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>';
        var e = '<div class="btn btn-xs btn-default"  href="#!" onclick="del(\''+row.id+'\')" title="删除"  data-toggle="tooltip"><i class="mdi mdi-window-close"></i><div/> ';
        return c + e ;
    }

    function foodPicFormatter(value,row, index){
        return "<img style=\"height:55px;width:55px;line-height:50px!important;\" src=\"/food/showimg/"+row.foodPic+"\">"
    }



})


var uploadFoodPic = "";
var editFoodId = "";
function edit(id){
    $('#foodEditModal').modal({
        show: true,
        backdrop:'static'
    });
    //重置表单
    $('#foodEditForm')[0].reset();
    var editRow = $('#foodTable').bootstrapTable('getRowByUniqueId',id);//行的数据
    $("#e_id").val(editRow.id);
    $("#e_foodName").val(editRow.foodName);
    $("#e_taste").val(editRow.taste);
    $("#e_price").val(editRow.price);
    $("#e_foodType").val(editRow.foodType);
    uploadFoodPic = editRow.foodPic;
    editFoodId = editRow.id;

    $("#e-file-pic").fileinput('destroy')
    //上传
    $('#e-file-pic').fileinput({
        //初始化上传文件框
        language: "zh",//配置语言
        showUpload : false, //显示整体上传的按钮
        showRemove: false,//显示移除图标
        uploadAsync: true,//默认异步上传
        uploadClass: "btn btn-primary",//设置上传按钮样式
        showCaption: true,//是否显示标题
        dropZoneEnabled: true,//是否显示拖拽区域
        uploadUrl: '/food/uploadFoodPic',//这个是配置上传调取的后台地址，本项目是SSM搭建的
        maxFileSize : 9999,//文件大小限制
        maxFileCount: 9999,//允许最大上传数，可以多个，
        enctype: 'multipart/form-data',
        allowedFileExtensions : ["jpg", "png","gif","docx","zip","xlsx","txt"],/*上传文件格式限制*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        showBrowse: true,
        browseOnZoneClick: true,
        initialPreviewAsData:true,
        // 在预览窗口中为新选择的文件缩略图设置文件操作的对象配置
        fileActionSettings: {
            showRemove: false, // 显示删除按钮
            showUpload: false,  // 显示上传按钮
            showDownload: false,// 显示下载按钮
            showZoom: false,  // 显示预览按钮
            showDrag: false,  // 显示拖拽

        },
        initialPreview: [
            '/food/showimg/'+uploadFoodPic
        ],
        initialCaption: uploadFoodPic,
        uploadExtraData: function(previewId, index) {   //额外参数的关键点
         //{ id: foodId }
         return { id: editFoodId };
        }
    });

}

function del(id){
    //发送ajax请求删除数据
    $.get("/food/deleteFood",{"id":id},function(res){
        if(res.isSuccess){
            utils.$msg('删除成功','orange');
            $("#foodTable").bootstrapTable('refresh')
        }
    });
}

$(document).ready(function(e) {
    var foodId ;
    $("button,a").on('click',function(){
        //获取到 a标签里面配置 data-method
        var methodName = $(this).data('method');
        if(methodName){
            doMethod[methodName]();
        }
    });
    var doMethod = {

        delBatch:function(){
            var delRows= $("#foodTable").bootstrapTable("getSelections");
            if(delRows.length<=0){
                $.confirm({
                    title: '温馨提示',
                    content: '请选中一行进行删除',
                    type: 'grey',
                    buttons: {
                        omg: {
                            text: '谢谢',
                            btnClass: 'btn-grey',
                        }
                    }
                });
            }else {
                $.confirm({
                    title: "温馨提示！",
                    content: "确定要删除它们吗？",
                    type: 'green',
                    buttons: {
                        omg: {
                            text: '确定',
                            btnClass: 'btn-green',
                            action:function(){
                                var ids = "";
                                for(var i=0;i<delRows.length;i++){
                                    var row = delRows[i];
                                    ids=ids+row.id+",";
                                }
                                ids+="0";
                                $.ajax({
                                    type: "post",
                                    url: "/food/deleteBatchfood",
                                    data: {"ids":ids},
                                    success: function (data) {
                                        if (data.isSuccess) {
                                            $("#foodTable").bootstrapTable("refresh");
                                        }
                                    }
                                });
                            }
                        },
                        close: {
                            text: '取消'
                        }
                    }
                });
            }
        },
        add:function(){
            $('#foodAddModal').modal({
                show: true,
                backdrop:'static'
            });


            //重置表单
            $('#foodAddForm')[0].reset();


        },
        search:function(){
            var foodName = $("#q_foodName").val();
            var queryparam = {
                silent:true,
                query:{
                    foodName:foodName
                }
            };
            $('#foodTable').bootstrapTable('refresh',queryparam);

        },
        save:function(){
            //提交表单
            var formParamObj =  $("#foodAddForm").serializeObject();

                //验证通过
                $.ajax({
                    url: "/food/addFood",
                    async: false,
                    type: "POST",
                    data: formParamObj,
                    success: function (data) {
                        foodId = data;
                        //不上传图片时，不触发bootstrap 上传插件的初始化方法。仅将表单里面的（除图片以外的）内容提交，
                        if ($("#file-pic").val() != "") {
                            $('#file-pic').fileinput('upload'); //触发插件开始上传。
                        }
                        if (data.isSuccess) {
                            $('#foodAddModal').modal('hide');
                            $('#foodTable').bootstrapTable('refresh');
                        } else if ("403" == data) {
                            alert("你无权访问");
                            $('#foodAddModal').modal('hide');
                        }

                    }
                });


        },
        editSave:function(){
            //提交表单
            var formParamObj =  $("#foodEditForm").serializeObject();

            //验证通过
            $.ajax({
                url: "/food/editSaveFood",
                async: false,
                type: "POST",
                data: formParamObj,
                success: function (data) {
                    console.log('------')
                    if(data.isSuccess) {
                        if ($("#e-file-pic").val() != "") {
                            $('#e-file-pic').fileinput('upload'); //触发插件开始上传。
                        }else{
                            $('#foodEditModal').modal('hide');
                            $('#foodTable').bootstrapTable('refresh');
                        }

                    }else{
                        utils.$msg(data.msg,'red');
                    }


                }
            });


        }

    }



    //上传--新增图片
    $('#file-pic').fileinput({
        //初始化上传文件框
        language: "zh",//配置语言
        showUpload : false, //显示整体上传的按钮
        showRemove : true,//显示整体删除的按钮
        uploadAsync: true,//默认异步上传
        uploadLabel: "上传",//设置整体上传按钮的汉字
        removeLabel: "移除",//设置整体删除按钮的汉字
        uploadClass: "btn btn-primary",//设置上传按钮样式
        showCaption: true,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        uploadUrl: '/food/uploadFoodPic',//这个是配置上传调取的后台地址，本项目是SSM搭建的
        maxFileSize : 9999,//文件大小限制
        maxFileCount: 9999,//允许最大上传数，可以多个，
        enctype: 'multipart/form-data',
        allowedFileExtensions : ["jpg", "png","gif","docx","zip","xlsx","txt"],/*上传文件格式限制*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        showBrowse: true,
        browseOnZoneClick: true,
        slugCallback : function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        },
        uploadExtraData: function(previewId, index) {   //额外参数的关键点
            //{ id: foodId }
            return { id: foodId };
        }
    });

    $('#file-pic').on("fileuploaded", function(event, data, previewId, index) {
        var response = data.response;
        console.log(response);
        if(response.isSuccess){
            $.confirm({
                title: '温馨提示',
                content: '保存成功',
                type: 'green',
                buttons: {
                    omg: {
                        text: '谢谢',
                        btnClass: 'btn-green',
                    }
                }
            });
            $('#foodAddModal').modal('hide');
            $('#foodTable').bootstrapTable('refresh');
        }else{
            $.confirm({
                title: '温馨提示',
                content: '操作失败',
                type: 'red',
                buttons: {
                    omg: {
                        text: '重试',
                        btnClass: 'btn-red',
                    }
                }
            });
        }
    });

    //修改图片
    $('#e-file-pic').on("fileuploaded", function(event, data, previewId, index) {
        var response = data.response;
        console.log(response);
        if(response.isSuccess){
            utils.$msg('修改成功','orange')
            $('#foodEditModal').modal('hide');
            $('#foodTable').bootstrapTable('refresh');

        }else{
            utils.$msg('修改失败','red')
        }

    });





})