<!--这里是放置js代码区域-->
layui.use(['element','form', 'layer', 'jquery'], function () {
    var element = layui.element;
    var form = layui.form
    var layer = layui.layer;
    var $ = layui.$;

    var sysusertype = $("#sysusertype").text();
    //根据登录用户的权限，判断是否展示用户管理界面
    if(sysusertype == 0){
        $("#sysuser").show();
    }
    if(sysusertype == 1){
        $("#sysuser").hide();
    }

    // 配置tab实践在下面无法获取到菜单元素
    $('.site-demo-active').on('click', function () {
        var dataid = $(this);
        //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
            //如果比零小，则直接打开新的tab项
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
        } else {
            //否则判断该tab项是否以及存在
            var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
            $.each($(".layui-tab-title li[lay-id]"), function () {
                //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                    isData = true;
                }
            })
            if (isData == false) {
                //标志为false 新增一个tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            }
        }
        //最后不管是否新增tab，最后都转到要打开的选项页面上
        active.tabChange(dataid.attr("data-id"));
    });

    var active = {
        //在这里给active绑定几项事件，后面可通过active调用这些事件
        tabAdd: function (url, id, name) {
            //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
            //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
            element.tabAdd('demo', {
                title: name,
                content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                id: id //规定好的id
            })
            FrameWH();  //计算ifram层的大小
        },
        tabChange: function (id) {
            //切换到指定Tab项
            element.tabChange('demo', id); //根据传入的id传入到指定的tab项
        },
        tabDelete: function (id) {
            element.tabDelete("demo", id);//删除
        }
    };
    function FrameWH() {
        var h = $(window).height();
        $("iframe").css("height",h+"px");
    }
    $(".changePassword").on("click",function () {
        layer.open({
            type: 1,
            area: ['500px', '360px'], //宽高
            content: $('#test'),
            cancel: function(){
                $('input[name="newpassword"]').val("")
                $('input[name="oldpassword"]').val("")
                $('input[name="surepassword"]').val("")

            }
        });
        return false
    });
    //修改密码输入框验证
    layui.use(['form','layer'], function(){
        var form = layui.form;
        var layer= layui.layer;
        var newpassword=''
        form.verify({
            oldpassword:function(value,item){
                if(value.length<5){
                    return"密码长度范围为6-20字符"
                }else if (value.length>21){
                    return"密码长度范围为6-20字符"
                }
            },
            newpassword:function(value,item){
                newpassword=value
                if(value.length<6){
                    return"密码长度范围为6-20字符"
                }else if (value.length>21){
                    return"密码长度范围为6-20字符"
                }
            },
            surepassword:function(value,item){
                if(value!==newpassword){
                    return"两次密码输入不一致"
                }
            }
        })
    });
    //修改密码
    form.on('submit(sure)', function(data){;
        //原密码
        var oldPassword = $('input[name="oldpassword"]').val();//$('#oldpassword').val();
        //新密码
        var surePassword =  $('input[name="surepassword"]').val();//$('#surepassword').val();

        var datas={
            "oldPassword" : oldPassword,
            "surePassword" :surePassword
        };
        $.ajax({
            type:'post',
            url:'/admin/updatePassword',
            dataType:'json',
            data:datas,
            success:function (Data) {
                var data = eval(Data);
                if(data.code == "0") {
                    layer.msg("登录成功");
                    setTimeout("window.location.href = \"/admin/index\"",1000);
                }else if (data.code == "80002") {
                    layer.msg("原密码输入错误");
                    // window.location.href = "/admin/login";
                }else if (data.code == "80006") {
                    layer.msg(data.message);
                    // window.location.href = "/admin/login";
                }else if (data.code == "80007") {
                    layer.msg(data.message);
                    // window.location.href = "/admin/login";
                }
            }
        });
        return false
    });

    //注销登录
    $(".logout").on("click",function () {
        $.ajax({
            type:'post',
            url:'/admin/logout',
            dataType:'json',
            success:function (Data) {
                var data = eval(Data);
                if(data.code == "0") {
                    layer.msg('注销成功');
                    setTimeout("window.location.href = \"/admin/login\"",1000);
                    // window.location.href = "/admin/login";
                }else {
                    layer.msg("注销失败");
                }
            }
        });
    });

});