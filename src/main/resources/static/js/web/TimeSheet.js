var url = window.location.search;
var key;
if (url.indexOf("?") != -1) {
    var str = url.substr(1)
    strs = str.split("&");
    for (i = 0; i < strs.length; i++) {
        key=strs[i].split("=")[1]
    }
}
console.log("key="+key);
$(".showDatePicker span").text(key);
var taskId;
var createTime;
var projectId;
var taskCode;









// 项目编号列表选择器
$('.showPicker').on('click', function () {
    weui.picker([{
        label: '新人培训',
        value: 1000
    },{
        label: '项目1',
        value: 1001
    }, {
        label: '项目2',
        value: 1002
    }, {
        label: '项目3',
        value: 1003
    },{
        label: '项目4 (disabled)',
        disabled: true,
        value: 1004
    }, {
        label: '其他',
        value: 1005
    }], {
        onChange: function (result) {
            console.log(result);
        },
        onConfirm: function (result) {
            var a=result[0].label;
            var b=result[0].value;
            projectId=b;
            $(".showPicker span").text(b);
            $("#projectCode").val(a);
        },
        title: '单列选择器'
    });
});


// 任务编号列表选择器
$('.showPicker2').on('click', function () {
    weui.picker([{
        label: '101-EC递交',
        value: 101
    },{
        label: '101-EDC录入',
        value: 101
    },{
        label: '101-SAE报告',
        value: 101
    },{
        label: '101-中心问题总结',
        value: 101
    },{
        label: '101-样品处理',
        value: 101
    },{
        label: '101-病人招募',
        value: 101
    },{
        label: '101-病人文件存档',
        value: 101
    },{
        label: '101-病人访视管理',
        value: 101
    },{
        label: '101-研究药品管理',
        value: 101
    },{
        label: '102-新人学习',
        value: 102
    },{
        label: '103-会议',
        value: 103
    },{
        label: '201',
        value: 201
    }, {
        label: '202',
        value: 202
    }, {
        label: '301',
        value: 301
    },{
        label: '302 (disabled)',
        disabled: true,
        value: 302
    }, {
        label: '303',
        value: 303
    },{
        label: '501-请假',
        value: 501
    },{
        label: '502-休假',
        value: 502
    },{
        label: '502-病假',
        value: 502
    }], {
        onChange: function (result) {
            console.log(result);
        },
        onConfirm: function (result) {
            taskCode = result[0].label;
            taskId = result[0].value;
            $(".showPicker2 span").text(taskCode);
        },
        title: '单列选择器'
    });
});


function  submitInformation() {
    var remark = document.getElementById("remark").value;
    if (remark=="请点击填写") {
        remark="";
    }
    var datas={
        sheetDate:key,
        sysUserId:$("#sysUserIds").text(),
        projectId: projectId,
        projectCode: document.getElementById("projectCode").value,
        taskId:taskId,
        taskCode:taskCode,
        workTime:document.getElementById("workTime").value,
        overTime:document.getElementById("overTime").value,
        remark:remark
    };
    $.ajax({
        type:'get',
        dataType:'json',
        data:datas,
        url:'/timeSheet/add',
        success:function (json) {
            if(json.code==0){
               alert("添加成功")
                window.location.href="/jump/index";
            }else{
                alert("添加失败")
            }
        }
    })

}






/*

/!**日期选择器*!/
$('.showDatePicker').click(function(event) {
    var _this = this;
    weui.datePicker({
        start: "2019-01-01",
        end: 2030,
        defaultValue: [new Date().getFullYear(), new Date().getMonth()+1, new Date().getDate()],
        onConfirm: function(result){
            // 二级调用：时间
            // $('.ma_expect_date_picker .weui-picker').on('animationend webkitAnimationEnd', function() {
            //     show_expect_time_picker(_this, result);
            // });
            var date = result[0].value+"-" + result[1].value+"-" + result[2].value;
            createTime = date + ' ' + new Date().getHours()+":"+new Date().getMinutes()+":00";
            $(".showDatePicker span").text(date);
        },
        id: 'ma_expect_date',
        className: 'ma_expect_date_picker'
    });
});
// -----------------------二级调用：时间
var hours = [],
    minites = [],
    symbol = [{ label: ':', value: 0 }];
function show_expect_time_picker(_this, date) {
    var date = date[0].value+"-" + date[1].value+"-" + date[2].value;
    if (!hours.length) {
        for (var i = 0; i< 24; i++) {
            var hours_item = {};
            hours_item.label = ('' + i).length === 1 ? '0' + i : '' + i;
            hours_item.value = i;
            hours.push(hours_item);
        }
    }
    if (!minites.length) {
        for (var j= 0; j < 60; j++) {
            var minites_item = {};
            minites_item.label = ('' + j).length === 1 ? '0' + j : '' + j;
            minites_item.value = j;
            minites.push(minites_item);
        }
    }

    weui.picker(hours, symbol, minites, {
        defaultValue: [new Date().getHours()+1, 0, 0],
        onConfirm: function(result) {
            var time = result[0].value+':' + (result[2].value<10?"0"+result[2].value:result[2].value)+":00";
            var expect_date = date + ' ' + time;
            // $(_this).find('.weui-cell__ft').text(expect_date);

            createTime=expect_date;
            $(".showDatePicker span").text(expect_date);
        },
        id: 'ma_expect_time'
    });
}
*/
