$(document).ready(function(e){
    $('#userTable').bootstrapTable({
        url: '/user/listpage',                      //请求后台的URL（*）
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
                username:params.username,
                email:params.email
            };
            return temp;
        },
        columns: [
            {
                align : 'center',
                checkbox: true
            },{
                field: 'id',
                title: '用户编号'
            }, {
                field: 'username',
                title: '用户名'
            }, {
                field: 'email',
                title: '邮件'
            }, {
                field: 'sex',
                title: '性别',
                align: 'center',
                formatter:function(value,row,index){
                    var value="";
                    if(row.sex=="1"){
                        value = "男";
                    }else{
                        value = "女" ;
                    }
                    return value;
                }
            }, {
                field: 'tel',
                title: '电话'
            }, {
                field: 'createTime',
                title: '创建时间'
            }, {
                field: 'headImg',
                title: '头像',
                formatter:headImgFormatter
            }, {
                field: 'roles',
                title: '所属角色',
                formatter:rolesFormatter
            },{
                field: 'doOpt',
                title: '操作',
                formatter : optFormatter
            }]
    });

    function optFormatter(value,row, index){
        var c = '<a class="btn btn-xs btn-default" href="#!"  onclick=\'edit("' + row.id + '")\' title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>';
        var e = '<div class="btn btn-xs btn-default"  href="#!" onclick="del(\''+row.id+'\')" title="删除"  data-toggle="tooltip"><i class="mdi mdi-window-close"></i><div/> ';
        return c + e ;
    }

    function headImgFormatter(value,row, index){
        return "<img style=\"height:35px;width:35px;border-radius:50%;line-height:50px!important;\" src=\"/showimage/"+row.headImg+"\">"
        // return '<img  src="/static/images/logo-sidebar.png">';
    }

    function rolesFormatter(value,row, index){
        if(row.roles.length > 0){
            var roleStr = "";
            for(var i=0;i<row.roles.length;i++){
                roleStr+= " <span class=\"label label-warning\">"+row.roles[i].name+"</span>"
            }
            return roleStr;
        }
        return "<span class=\"label label-danger\">未分配权限</span>";


    }
})

function edit(id){
    $('#userEditModal').modal({
        show: true,
        backdrop:'static'
    });
    //重置表单
    $('#userEditForm')[0].reset();
    var editRow = $('#userTable').bootstrapTable('getRowByUniqueId',id);//行的数据
    $("#e_id").val(editRow.id);
    $("#e_username").val(editRow.username);
    // $("#e_pwd").val(editRow.password);
    $("#e_email").val(editRow.email);
    $("#e_tel").val(editRow.tel);
    var sexVal = editRow.sex;
    if(editRow.sex){
        $("input[name='sex'][value='1']").prop("checked",true);
    }else{
        $("input[name='sex'][value='0']").prop("checked",true);
    }
}

function del(id){
    //发送ajax请求删除数据
    $.get("/user/deleteUser",{"id":id},function(res){
        if(res.isSuccess){
            $.confirm({
                title: '温馨提示',
                content: '删除成功',
                type: 'green',
                buttons: {
                    omg: {
                        text: '谢谢',
                        btnClass: 'btn-green',
                    }
                }
            });
            $("#userTable").bootstrapTable('refresh')
        }
    });
}


$(document).ready(function(e) {
    var userId ;
    $("button,a").on('click',function(){
        //获取到 a标签里面配置 data-method
        var methodName = $(this).data('method');
        if(methodName){
            doMethod[methodName]();
        }
    });
    var doMethod = {
        //条件用户角色弹框
        addUserRole:function(){
            var addRoleRows= $("#userTable").bootstrapTable("getSelections");
            if(addRoleRows.length<=0){
                $.confirm({
                    title: '温馨提示',
                    content: '请选中一行进行操作',
                    type: 'orange',
                    buttons: {
                        omg: {
                            text: '谢谢',
                            btnClass: 'btn-orange',
                        }
                    }
                });
                return;
            }
            //编辑id
            var userId = addRoleRows[0].id;
            var username = addRoleRows[0].username;
            $("#addRole_userid").val(userId);
            $("#addRole_username").val(username);

            //清空所有的
            $("input[name='roles[]']").prop("checked",false);
            //设置权限
            addRoleRows[0]["roles"].forEach((el,index)=>{
                var rid = el.id;
                $("#rid_"+rid).prop("checked","checked");
            });

            //添加用户角色
            $('#addUserRoleModal').modal({
                show: true,
                backdrop:'static'
            });
        },
        addUserRoleSave(){
            //保存用户的角色
            var addRoleParamObj =  $("#addUserRoleForm").serializeObject();
            console.log(addRoleParamObj);
            /**
             * {
             * id: "18"
                   roles:[1,2]
                   }
             */
            var paramObj =  {"userId":addRoleParamObj.id,"roleIds":addRoleParamObj.roles};
            $.ajax({
                type: "post",
                url: "/user/addUserRole",
                dataType : "json",
                data: JSON.stringify(paramObj),
                contentType:'application/json;charset=utf-8',
                success: function (data) {
                    if (data.isSuccess) {
                        $.confirm({
                            title: '温馨提示',
                            content: '保存成功',
                            type: 'green',
                            buttons: {
                                omg: {
                                    text: '谢谢',
                                    btnClass: 'btn-green',
                                    action:function(){
                                        $('#addUserRoleModal').modal('hide');
                                        $('#userTable').bootstrapTable('refresh');
                                    }
                                }
                            }
                        });
                    }
                }
            });
        },
        delBatch:function(){
            var delRows= $("#userTable").bootstrapTable("getSelections");
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
                                    url: "/user/deleteBatchUser",
                                    data: {"ids":ids},
                                    success: function (data) {
                                        if (data.isSuccess) {
                                            $("#userTable").bootstrapTable("refresh");
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
            $('#userAddModal').modal({
                show: true,
                backdrop:'static'
            });

        },
        search:function(){

            var username = $("#q_username").val();
            var email = $("#q_email").val();
            var queryparam = {
                silent:true,
                query:{
                    username:username,
                    email:email
                }
            };
            $('#userTable').bootstrapTable('refresh',queryparam);

        },
        save:function(){
            //提交表单
            var bootstrapValidator = $('#userAddForm').data('bootstrapValidator');
            var formParamObj =  $("#userAddForm").serializeObject();
            console.log(formParamObj);
            bootstrapValidator.validate();

            if (bootstrapValidator.isValid()) {
                //验证通过
                $.ajax({
                    url: "/user/addUser",
                    async: false,
                    type: "POST",
                    data: formParamObj,
                    success: function (data) {
                        userId = data;
                        //不上传图片时，不触发bootstrap 上传插件的初始化方法。仅将表单里面的（除图片以外的）内容提交，
                        if ($("#file-pic").val() != "") {
                            $('#file-pic').fileinput('upload'); //触发插件开始上传。
                        }
                        if (data.isSuccess) {
                            alert("init ok");
                            $('#userAddModal').modal('hide');
                            $('#userTable').bootstrapTable('refresh');
                        } else if ("403" == data) {
                            alert("你无权访问");
                            $('#userAddModal').modal('hide');
                        }

                    }
                });

            }

        },
        editSave:function(){
            //提交表单
            var bootstrapValidator = $('#userEditForm').data('bootstrapValidator');
            var formParamObj =  $("#userEditForm").serializeObject();
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                //验证通过
                $.ajax({
                    url: "/user/editSaveUser",
                    async: false,
                    type: "POST",
                    data: formParamObj,
                    success: function (data) {
                        userId = data;
                        if (data.isSuccess) {
                            $.confirm({
                                title: '温馨提示',
                                content: '修改成功',
                                type: 'green',
                                buttons: {
                                    omg: {
                                        text: '谢谢',
                                        btnClass: 'btn-green',
                                    }
                                }
                            });
                            $('#userEditModal').modal('hide');
                            $('#userTable').bootstrapTable('refresh');
                        } else if ("403" == data) {
                            alert("你无权访问");
                            $('#userEditModal').modal('hide');
                        }

                    }
                });

            }

        }

    }

    //上传
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
        uploadUrl: '/file/uploadFile',//这个是配置上传调取的后台地址，本项目是SSM搭建的
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
            //{ id: userId }
            return { id: userId };
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
            $('#userAddModal').modal('hide');
            $('#userTable').bootstrapTable('refresh');
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

    //验证规则
    $('#userAddForm,#userEditForm').bootstrapValidator({
        live: 'enabled',//字段值有变化就触发验证 disabled,submitted 当点击提交时验证并展示错误信息
        message: '信息不合法',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名必须填写,不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '长度必须是6到30个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名必须有字母数字下划线组成'
                    }
                }
            },
            tel: {
                message: '电话不合法',
                validators: {
                    notEmpty: {
                        message: '电话号码,不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '长度必须是11位'
                    },
                    regexp: {
                        regexp: /^1\d{10}$/,
                        message: '手机号格式错误'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能空'
                    },
                    emailAddress: {
                        message: '输入邮箱不合格'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码不一样哟111....'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能和用户名相同'
                    }
                }
            }
        }
    });
})