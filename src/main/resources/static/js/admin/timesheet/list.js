/**
 * Created by MECHREVO on 2019/11/20.
 */
var form;
var tableIns;
layui.use('table', function () {
    var table = layui.table;
    form = layui.form;
    //表单初始赋值
    form.val('articleSearch', {});
    tableIns = table.render({
        elem: '#timeSheetList'
        , page: true //开启分页
        , url: '/timeSheet/selectList'
        , height: 'full-250'
        , method: 'get'
        , request: {
            pageName: 'pageNo', //页码的参数名称，默认：pageNum
            limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
        }
        , response: {
            statusName: 'code', //数据状态的字段名称，默认：code
            statusCode: 0, //成功的状态码，默认：0
            countName: 'totals', //数据总数的字段名称，默认：count
            dataName: 'list' //数据列表的字段名称，默认：data
        }
        , cols: [[
            {type: 'numbers', width: '3%'}
            , {field: 'sheetDate', width: '8%', title: '时间'}
            , {field: 'sysUserId', width: '5%', title: '用户编号'}
            , {field: 'sysUserRealName', width: '6%', title: '用户名'}
            , {field: 'projectId', width: '5%', title: '项目编号'}
            , {field: 'projectCode', width: '8%', title: '项目名称'}
            , {field: 'taskCode', width: '30%', title: '工作内容'}
            , {field: 'workTime', width: '8%', title: '工时(小时)'}
            , {field: 'overTime', width: '8%', title: '超时时间(小时)'}
            , {field: 'remark', width: '16%', title: '备注'}
            , {field: 'barDemo', width: '8%', title: '操作', toolbar: '#barDemo'}
        ]],
        done: function (res, curr, count) {

            console.log(res)

        }
    });


    //监听行工具事件
    table.on('tool(timeSheetList)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        /*   var data = obj.data //获得当前行数据
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
           }*/

        console("监听行工具事件"+obj);
        var data = obj.data,//获得当前行数据（json格式的键值对）
            layEvent = obj.event, //获得 lay-event 对应的值（编辑、删除、添加）
            editList = []; //存放获取到的那条json数据中的value值（不放key）
        $.each(data, function (name, value) {//循环遍历json数据
            editList.push(value);//将json数据中的value放入数组中（下面的子窗口显示的时候要用到）
        });

    });

    //搜索框
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, layer = layui.layer, laydate = layui.laydate;
        var date = new Date();
        //监听搜索框
        form.on('submit(searchSubmit)', function (data) {
            console.log("监听搜索框")
            //重新加载table
            load(data);
            return false;
        });
        //导出数据
        form.on('submit(excelSubmit)', function (data) {
            excelOutput();
            return false;

        });

    });

});


//导出数据
function excelOutput() {

    var dates = document.getElementById("dates").value;
    var sysUserRealName =  document.getElementById("sysUserRealName").value;
    var sheetDateStart = dates.split(" && ")[0];
    var sheetDateEnd = dates.split(" && ")[1];
    window.location.href =
        "/export/exportOutpatientRecord"
        + "?sysUserRealName=" + isempt(sysUserRealName)
        + "&sheetDateStart=" + isempt(sheetDateStart)
        + "&sheetDateEnd=" + isempt(sheetDateEnd);
}

function isempt(stringpanduan) {
    if ("" == stringpanduan || undefined == stringpanduan) {
        return "";
    } else {
        return stringpanduan;
    }
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

function refund() {
    //重新加载table
    tableIns.reload({
        page: {
            pageNo: 1 //从当前页码开始
            , pageSize: 10
        }
    });
}

function load(obj) {
    console.log("重新加载" + obj);
    if (obj.field.payStatus == "请选择") {
        obj.field.payStatus = null;
    }
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            pageNo: 1 //从当前页码开始
            , pageSize: 10
        }
    });
}