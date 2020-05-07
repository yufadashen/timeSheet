/**
 * Created by MECHREVO on 2019/11/20.
 */
var form;
var tableIns;
layui.use('table', function () {
    var table = layui.table;
    tableIns = table.render({
        elem: '#sysUserList'
        , page: true //开启分页
        , url: '/admin/sysUserManagement/sysUserList'
        , height: 'full-250'
        , method: 'post'
        , request: {
            pageName: 'pageNo', //页码的参数名称，默认：pageNum
            limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
        }
        , response: {
            statusName: 'code', //数据状态的字段名称，默认：code
            statusCode: 200, //成功的状态码，默认：0
            countName: 'totals', //数据总数的字段名称，默认：count
            dataName: 'list' //数据列表的字段名称，默认：data
        }
        , cols: [[
            {type: 'numbers', width: '3%'}
            , {field: 'sysUserName', width: '19%', title: '用户名'}
            , {field: 'sysUserRealName', width: '20%', title: '真实姓名'}
            , {field: 'sysUserTel', width: '19%', title: '联系方式'}
            , {field: 'sysUserType', width: '19%', title: '用户级别'}
            , {field: 'barDemo', width: '20%', title: '操作', toolbar: '#barDemo'}
        ]],
        done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            //得到当前页码
            console.log(res)
            $("[data-field='sysUserType']").children().each(function () {
                if ($(this).text() === '0') {
                    $(this).text("超级用户");
                    $("#del").hide();
                } else if ($(this).text() === '1') {
                    $(this).text("普通用户")
                }
            });
        }
    });

    //搜索框
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, layer = layui.layer
            , laydate = layui.laydate;
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            //重新加载table
            load(data);
            return false;
        });

        form.on('submit(addSysUser)', function () {
            //新增用户
            onManual();
            return false;
        });
    });

    //监听行工具事件
    table.on('tool(sysUserList)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        var sysUserId = JSON.stringify(data.sysUserId);
        if (layEvent === 'del') {
            layer.confirm('确定要删除吗', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
                delSysuser(sysUserId);
            });
        } else if (layEvent === 'edit') {
            getSysuserById(sysUserId);
        }
    });

});

function load(obj) {
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            pageNo: 1 //从当前页码开始
            , pageSize: 10
        }
    });
}

//根据用户id，删除用户信息
function delSysuser(sysUserId) {
    $.ajax({
        type: 'post',
        url: '/admin/sysUserManagement/delSysUserById',
        dataType: 'json',
        data: {"sysUserId": sysUserId},
        success: function (Data) {
            var data = eval(Data);
            if (data.code == 0) {
                layer.msg("删除成功");
                setTimeout("window.location.href = \"/admin/sysUserManagement/sysusermanageView\"", 1000);
            } else {
                layer.msg(data.message);
                setTimeout("window.location.href = \"/admin/sysUserManagement/sysusermanageView\"", 1000);
            }
        }
    });
}

//根据用户id，查询对应的用户信息，展示到弹出框中，此时不展示密码
function getSysuserById(sysUserId) {
    //查询对应信息
    $.ajax({
        type: 'post',
        url: '/admin/sysUserManagement/getSysUserById',
        dataType: 'json',
        data: {"sysUserId": sysUserId},
        success: function (Data) {
            var data = eval(Data);
            if (data.code === 0) {
                getSysuserInfo(data);
            } else {
                layer.msg(data.message);
                setTimeout("window.location.href = \"/admin/sysUserManagement/sysusermanageView\"", 1000);
            }
        }
    });
}

//将查询结果展示到弹窗
function getSysuserInfo(data) {
    //用户名无法修改
    $("#sysUserNames").attr("readonly", true);
    $("#redSpan").hide();
    $("#redSpan1").hide();
    $("#sysUserPassword").attr("lay-verify", "sysUserPassword");
    //用户名置灰
    $("#sysUserNames").attr("style", "background-color: #e4e4e4");
    //将查询出的结果放入弹出框中
    $("#sysUserIds").val(data.info.sysUserId);
    $("#sysUserNames").val(data.info.sysUserName);
    $("#sysUserPassword").val(data.info.sysUserPassword);
    var pwd = $("#sysUserPassword").val();
    $("#sysUserRealNames").val(data.info.sysUserRealName);
    $("#sysUserTels").val(data.info.sysUserTel);
    //弹出框
    var layer = layui.layer;
    layer.open({
        title: '修改用户信息'
        , content: $("#insertSearch")
        , type: 1
        , offset: "160px"
        , area: ['40%', '45%']
        , btn: ['保存', '取消']
        , btnAlign: 'c'
        , success: function (layero, index) {
        }
        , yes: function (index, layero) {
            //表单验证
            //如果密码相同，或不修改，则不再验证密码
            if ($("#sysUserPassword").val() != pwd) {
                if ($("#sysUserPassword").val().length < 6) {
                    layer.msg("密码不小于六位");
                    return;
                }
                if ($("#sysUserPassword").val().length > 20) {
                    layer.msg("密码不的超过20位");
                    return;
                }
            }
            if ($("#sysUserRealNames").val().length > 32) {
                layer.msg("真实姓名不得超过32位");
                return;
            }
            if ($("#sysUserTels").val().length != 0) {
                if ($("#sysUserTels").val().length != 11) {
                    layer.msg("手机号码必须11位");
                    return;
                }
                if (!(/^1[34578]\d{9}$/.test($("#sysUserTels").val()))) {
                    layer.msg("手机号码格式有误，请重填");
                    return;
                }
            }
            var datas = {
                sysUserId: $("#sysUserIds").val(),
                sysUserName: $("#sysUserNames").val(),
                sysUserPassword: $("#sysUserPassword").val(),
                sysUserRealName: $("#sysUserRealNames").val(),
                sysUserTel: $("#sysUserTels").val()
            };
            $.ajax({
                type: 'post',
                url: '/admin/sysUserManagement/updateSysUserById',
                dataType: 'json',
                data: datas,
                success: function (Data) {
                    var data = eval(Data);
                    if (data.code === 0) {
                        layer.msg("修改成功");
                        setTimeout("window.location.href = \"/admin/sysUserManagement/sysusermanageView\"", 1000);
                    } else {
                        layer.msg(data.message);
                        setTimeout("window.location.href = \"/admin/sysUserManagement/sysusermanageView\"", 1000);
                    }
                }
            });
            layui.use('form', function () {
                var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                form.render();
            })

        }
        , btn2: function (index, layero) {
            layer.close(index);
        }
    });
}

//新增用户按钮
function onManual() {
    //清除表单缓存
    clearFrom();
    $("#redSpan").show();
    $("#redSpan1").show();
    //弹出框
    var layer = layui.layer;
    layer.open({
        title: '新增用户'
        , content: $("#insertSearch")
        , type: 1
        , offset: "130px"
        , btn: ['保存', '取消']
        , area: ['40%', '45%']
        , btnAlign: 'c'
        , success: function (layero, index) {
        }
        , yes: function (index, layero) {
            //表单验证
            if ($("#sysUserNames").val().length == 0) {
                layer.msg("用户名不能为空");
                return;
            } else if ($("#sysUserNames").val().length > 10) {
                layer.msg("用户名不得超过10位");
                return;
            } else if ($("#sysUserPassword").val().length < 6) {
                layer.msg("密码不小于六位");
                return;
            } else if ($("#sysUserPassword").val().length > 20) {
                layer.msg("密码不的超过20位");
                return;
            } else if ($("#sysUserRealNames").val().length > 32) {
                layer.msg("真实姓名不得超过32位");
                return;
            } else if ($("#sysUserTels").val().length != 0) {
                if ($("#sysUserTels").val().length != 11) {
                    layer.msg("手机号码必须11位");
                    return;
                }else if (!(/^1[345678]\d{9}$/.test($("#sysUserTels").val()))) {
                    layer.msg("手机号码格式有误，请重填");
                    return;
                }else {
                    addsysUser();
                    layui.use('form', function () {
                        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                        form.render();
                    })
                }
            } else {
                addsysUser();
                layui.use('form', function () {
                    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                    form.render();
                })
            }
        }
        , btn2: function (index, layero) {
            layer.close(index);
        }
    });
}

//添加用户
function addsysUser() {
    var datas = {
        sysUserName: $("#sysUserNames").val(),
        sysUserPassword: $("#sysUserPassword").val(),
        sysUserRealName: $("#sysUserRealNames").val(),
        sysUserTel: $("#sysUserTels").val()
    };
    $.ajax({
        type: 'post',
        url: '/admin/sysUserManagement/addSysUser',
        dataType: 'json',
        data: datas,
        success: function (Data) {
            var data = eval(Data);
            if (data.code == 0) {
                layer.msg("添加成功");
                setTimeout("window.location.href = \"/admin/sysUserManagement/sysusermanageView\"", 1000);
            } else {
                layer.msg(data.message);
            }
        }
    });
}

//清除表单缓存
function clearFrom() {
    $("#sysUserNames").attr("style", "");
    $("#sysUserNames").attr("readonly", false);
    $("#sysUserPassword").attr("lay-verify", "sysUserPassword");
    $("#sysUserIds").val("");
    $("#sysUserNames").val("");
    $("#sysUserRealNames").val("");
    $("#sysUserPassword").val("");
    $("#sysUserTels").val("");
}