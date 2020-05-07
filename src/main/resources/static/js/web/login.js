

if(location.href.indexOf("#reloaded")==-1){
    location.href=location.href+"#reloaded";
    location.reload();
}
layui.use(['jquery','form','layer'], function(){
    var $ =layui.jquery;
    var form = layui.form;//表单验证
    var layer= layui.layer;
    //表单验证（页面自动关联,验证结果绑定在表单）
    form.verify({
        username:function(value,item){
            if(value.length==0){
                return"用户名不能为空"
            }
        },
        userpassword:function(value,item){
            if(value.length<6){
                return"密码必须大于六位"
            }
        }
    });
    form.on('submit(login)', function(data){
        var username = $('input[name="username"]').val();
        var password = $('input[name="userpassword"]').val();
        var datas={
            "sysUserName" : username,
            "sysUserPassword" :password
        };
        $.ajax({
            type:'post',
            url:'/admin/yanzheng',
            dataType:'json',
            data:datas,
            success:function (Data) {
                var data = eval(Data);
                if(data.code == "0") {
                    layer.msg("登录成功");
                    setTimeout("window.location.href = \"/jump/index\"",1000);
                }else if (data.code == "80001") {
                    layer.msg(data.message);
                    setTimeout("window.location.href = \"/jump/login\"",1000);
                }else if (data.code == "80002") {
                    layer.msg(data.message);
                    setTimeout("window.location.href = \"/jump/login\"",1000);
                }
            }
        });
        return false
    })
});