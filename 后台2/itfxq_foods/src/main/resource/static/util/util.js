
let utils = {
    //弹出提示
    $msg:function(msg,color) {

            $.confirm({
                title: '温馨提示',
                content:msg,
                type: color,
                buttons: {
                    omg: {
                        text: '谢谢',
                        btnClass: 'btn-'+color,
                    }
                }
            });

        }
}
