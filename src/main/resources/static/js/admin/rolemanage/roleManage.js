/**
 * 角色管理
 */
var pageCurr;
var form;
var tableIns;
$(function() {
    layui.use('table', function () {
        var table = layui.table;
        form = layui.form;
           //表单初始赋值
        form.val('roleSearch', {

          })   //

        tableIns = table.render({
            elem: '#roleList',
            url: 'admin/getRoleList',
            method: 'post', //默认：get请求
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response: {
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type: 'numbers'}
                , {field: 'roleName', title: '角色名称', align: 'center'}
                , {field: 'description', title: '角色描述', align: 'center'}
                , {field: 'createTime', title: '创建时间', align: 'center'}
                , {field: 'roleId',  title: '操作', align: 'center', toolbar: '#barOption'}
            ]],
            done: function (res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
                //得到数据总量
                //console.log(count);
                pageCurr = curr;
                $("[data-field='roleName']").children().each(function(){
                    if($(this).text()=='admin'){
                        $("#del").hide();
                        $("#edit").hide();
                        $("#grantPermission").hide();
                    }
                });
            }
        });
        // //监听提交
        // form.on('submit(userSubmit)', function(data){
        //     formSubmit(data);
        //     return false;
        // });
        table.on('tool(roleList)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data ,//获得当前行数据（json格式的键值对）
                layEvent = obj.event, //获得 lay-event 对应的值（编辑、删除、添加）
                editList=[]; //存放获取到的那条json数据中的value值（不放key）
            $.each(data,function(name,value) {//循环遍历json数据
                editList.push(value);//将json数据中的value放入数组中（下面的子窗口显示的时候要用到）
            });
            if(layEvent === 'edit') {
                //脚本编辑弹出层
                layer.open({
                    type: 1,
                    title: "修改角色", //不显示标题栏
                    closeBtn: 2,
                    area:["500px","300px"],
                    shade: 0,
                    btn: ['保存', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: $("#roleDialog"),
                    success: function (layero, index) {
                       $("#roleName1").val(data.roleName);
                       $("#description").val(data.description);
                    },
                    yes: function (index, layero) {
                        var roleId = data.roleId;
                        var roleName =$("#roleName1").val();
                        var description =$("#description").val();
                        if (roleName == null||roleName=='') {
                            layer.msg("角色名称不能为空");
                            return;
                        }
                        if(roleName.length>10){
                            layer.msg("角色名称不能超过10个字符");
                            return;
                        }
                        if(description!=null&&description!=''){
                            if(description.length>100){
                                layer.msg("角色描述过长")
                            }
                        }
                        $.ajax({
                            url: 'admin/checkRoleExists',
                            data: {roleName: roleName,
                                    roleId:roleId},
                            dataType:'json',
                            success: function (data) {
                                if (data.code == "0") {
                                    var data = {
                                        roleName: roleName,
                                        description: description,
                                        roleId: roleId
                                    };
                                    $.ajax({
                                        url: 'admin/roleAdd',
                                        data: data,
                                        dataType:'json',
                                        success: function (data) {
                                            if(data.code=='0'){
                                                layer.close(index);
                                                layer.msg("修改角色成功");
                                                load(data);
                                            }else if(data.code=="-1"){
                                                layer.msg("修改角色失败")
                                            }else if(data.code=='403'){
                                                setTimeout("window.location.href = \"/403\"",1000);
                                            }

                                        }
                                    });

                                } else {
                                    layer.msg("角色名重复")
                                }
                            }
                        });

                    },
                    btn2: function (index, layero) {
                    }
                });
            }
            if(layEvent=== 'del'){
                layer.open({
                    type: 0,
                    title: '删除角色',
                    shadeClose: true,
                    shade: 0,
                    maxmin: true,
                    btnAlign: 'c',
                    area: ['300px', '200px'],
                    content:'您确定删除吗？',
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        var roleId=data.roleId;
                        $.ajax({
                            url: 'admin/roleDelete',
                            data:{roleId:roleId},
                            dataType:'json',
                            success: function(data) {
                                if(data.code=='0'){
                                    layer.close(index);
                                    layer.msg("删除角色成功")
                                    load(data);
                                }
                                if(data.code=="403"){
                                    setTimeout("window.location.href = \"/403\"",1000);
                                }
                            }
                        });

                    },
                    btn2: function (index, layero) {
                    }
                });
            }
            if(layEvent === 'detail'){
                var checkedData;
                layer.open({
                    type: 1,
                    title: "分配权限", //不显示标题栏
                    closeBtn: 2,
                    area:["500px","400px"],
                    shade: 0,
                    btn: ['保存', '取消'],
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: $("#menuTreeDialog"),
                    success: function (layero, index) {
                         roleId=data.roleId;
                        $.ajax({
                            type:"post",
                            url: 'admin/getPermission',
                            data: {
                                roleId:roleId
                            },
                            dataType:'json',
                            success: function (data) {
                                var array=[];
                                array=data.info.treeNode;
                                layui.use(['tree', 'util'], function() {
                                    var tree = layui.tree
                                        , layer = layui.layer
                                        , util = layui.util
                                    tree.render({
                                        elem: '#menuTree'
                                        ,data: array
                                        ,showCheckbox: true  //是否显示复选框
                                        ,id: 'test'
                                        ,click: function(obj){
                                        }
                                    });
                                    var list=data.info.list;
                                   tree.setChecked('test',list);
                                });

                            }
                        });


                    },
                    yes: function (index, layero) {
                        var tree = layui.tree;
                        var checkData = tree.getChecked('test');
                        var ids = getIds(checkData);
                        //递归获取所有树id
                        $.ajax({
                                type:"post",
                                url: 'admin/toAuthorize',
                                traditional:true, //默认false
                                data: {
                                    roleId:roleId,
                                    permissionIdList:ids
                                },
                                dataType:'json',
                            success: function (data) {
                                     console.log(data);
                                    if(data.code=="0"){
                                        layer.msg("分配权限成功")
                                        layer.close(index);
                                    }else if(data.code=="-1"){
                                        layer.msg("分配权限失败")
                                    }else if(data.code=='403'){
                                        setTimeout("window.location.href = \"/403\"",1000);
                                    }


                            }
                        });
                    },
                    btn2: function (index, layero) {
                    }
                });
            }
        });

        //搜索框
        layui.use(['form', 'laydate'], function () {
            var form = layui.form, layer = layui.layer
                , laydate = layui.laydate;

            var date = new Date();
            //日期
            laydate.render({
                elem: '#startTime'
                , value: new Date(date - 30 * 24 * 3600 * 1000)
            });
            laydate.render({
                elem: '#endTime'
                , value: date
            });
            //监听搜索框
            form.on('submit(searchSubmit)', function (data) {
                //重新加载table
                load(data);
                return false;
            });
        });
    });
});

var arr=[]
function getIds(json){
    for(var i = 0; i < json.length; i ++){
        arr.push(json[i].id);
        if(json[i].children != null && json[i].children != undefined){
            if(json[i].children.length > 0){
                getIds(json[i].children);
            }
        }
    }
    return arr;
}
function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function addRole() {
    $("#roleName1").val("");
    $("#description").val("");
    layer.open({
        type: 1,
        title: "新增角色", //不显示标题栏
        closeBtn: 2,
        area:["500px","300px"],
        shade: 0,
        btn: ['保存', '取消'],
        btnAlign: 'c',
        moveType: 1, //拖拽模式，0或者1
        content: $("#roleDialog"),
        yes: function (index, layero) {
            var roleName=$("#roleName1").val();
            var description=$("#description").val();
            if (roleName == null||roleName=='') {
                layer.msg("角色名称不能为空");
                return;
            }
            if(roleName.length>10){
                layer.msg("角色名称不能超过10个字符");
                return;
            }
            if(description!=null&&description!=''){
                if(description.length>100){
                    layer.msg("角色描述过长")
                }
            }
            $.ajax({
                url: 'admin/checkRoleExists',
                data:{roleName:roleName},
                dataType:'json',
                success: function(data) {
                    console.log(data.code);
                    if(data.code=="0"){
                        var data={roleName:roleName,
                            description:description
                        };
                        $.ajax({
                            url: 'admin/roleAdd',
                            data: data,
                            dataType:'json',
                            success: function(data) {
                                if(data.code=='0'){
                                    layer.close(index);
                                    layer.msg("新增角色成功")
                                    load(data);
                                }else if(data.code=="-1"){
                                    layer.msg("新增角色失败")
                                }else if(data.code=="403"){
                                    setTimeout("window.location.href = \"/403\"",1000);
                                }


                            }
                        });

                    }else {
                        layer.msg("角色名重复")
                    }
                }
            });

        },
        btn2: function (index, layero) {
        }
    });
}



