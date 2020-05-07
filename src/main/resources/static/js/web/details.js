var url = window.location.search;
var key;
if (url.indexOf("?") != -1) {
    var str = url.substr(1)
    strs = str.split("&");
    for (i = 0; i < strs.length; i++) {
        key=strs[i].split("=")[1]
    }
}
var datas={
    "key":key,
    "sysUserId":$("#sysUserIds").text(),
    "pageNo": 1,
    "pageSize":10
};
getReports();
function getReports() {
    $.ajax({
        url: '/timeSheet/getDetails',
        dataType: 'json',
        type: 'get',
        data: datas,
        success: function (Data) {
            if(Data.code==0){
                var str = "";
                for(var i=0;i<Data.info.length;i++){
                    str += "    <div class=\"all\">\n" +
                        "        <a href=\"javascript:;\" onclick='toDetail("+Data.info[i].id+")'>\n" +
                        "         <div class=\"top\">\n" +
                        "           <div class=\"a\">"+Data.info[i].projectId+"</div>\n" +
                        "             <div class=\"b\">"+Data.info[i].sheetDate+"</div>\n" +
                        "         </div>\n" +
                        "            <div class=\"bottom\">\n" +
                        "                <div class=\"c\"><span>任务名称：</span>"+Data.info[i].taskCode+"</div>\n" +
                        "                <div class=\"d\"></div>\n" +
                        "            </div>\n" +
                        "            <div class=\"bottom2\">\n" +
                        "                <div class=\"e\"><span>工时：</span>"+Data.info[i].workTime+"<span>小时</span></div>\n" +
                        "                <div class=\"f\"><span>超时时间：</span>"+Data.info[i].overTime+"<span>小时</span></div>\n" +
                        "            </div>\n" +
                        "        </a>\n" +
                        "    </div>";
                }
                $(".content").html(str)
            }else{
                $(".content").append(
                    "<img src=\"../../images/admin/zwsj.png\" style='width:43%;margin-top: 0.9rem;margin-left:0.5rem'>"
                    +'<div style="width: 100%;text-align: center;font-size:0.36rem;color:#666">暂无数据</div>'
                );
            }
        }
    })
}

function toDetail(info) {
    console.log(info);
    window.location.href = "/jump/updateDetails?id="+info;
}


//选择起止日期
function choiceDate(type) {
    weui.datePicker({
        start: 2018,
        end: date.getFullYear()+"-" + (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + "-" + (date.getDate()<10 ? '0'+date.getDate() : date.getDate()),
        defaultValue: [new Date().getFullYear(), new Date().getMonth()+1, new Date().getDate()],
        onChange: function(result){
        },
        onConfirm: function (result) {
            if (result[1] < 10){
                result[1] = '0'+result[1];
            }
            if (result[2] < 10){
                result[2] = '0'+result[2];
            }
            if(type == 0){
                $('#startDate').text(result.join('-'));
            }
            if(type == 1){
                $('#endDate').text(result.join('-'));
            }
            var startTime=$("#startDate").text()
            var endTime=$("#endDate").text()
            var difference=DateDiff(startTime, endTime)
            if(difference>0){
                weui.dialog({
                    // title: '错误提示',
                    content: '请选择合法日期范围',
                    className: 'custom-classname',
                    buttons: [{
                        label: '确定',
                        type: 'primary',
                        onClick: function () {
                            $('#startDate').text(date1.getFullYear()+"-" + (date1.getMonth()+1 < 10 ? '0'+(date1.getMonth()+1) : date1.getMonth()+1) + "-" + (date1.getDate()<10 ? '0'+date1.getDate() : date1.getDate()));
                            $('#endDate').text(date.getFullYear()+"-" + (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + "-" + (date.getDate()<10 ? '0'+date.getDate() : date.getDate()));
                            var datas={
                                "beginDay":$("#startDate").text(),
                                "endDay":$("#endDate").text()
                            }
                            $.ajax({
                                url: '/reportQuery/getReports',
                                dataType: 'json',
                                type: 'get',
                                data: datas,
                                success: function (Data) {
                                    if(Data.code==0){
                                        var str = "";
                                        for(var i=0;i<Data.info.length;i++){
                                            str += "    <div class=\"all\">\n" +
                                                "        <div class=\"top\">\n" +
                                                "            <div class=\"a\">"+Data.info[i].applyDept+"</div>\n" +
                                                "            <div class=\"b\">"+Data.info[i].outReportDate+"</div>\n" +
                                                "        </div>\n" +
                                                "\n" +
                                                "        <div class=\"bottom\">\n" +
                                                "            <div class=\"c\">"+Data.info[i].outReportType+"</div>\n" +
                                                "            <div class=\"d\"><a href=\"javascript:;\" onclick='toDetail("+Data.info[i].inpatientNo+","+Data.info[i].outReportClass+")'>></a></div>" +
                                                "        </div>\n" +
                                                "    </div>";
                                        }
                                        $(".content").html(str)
                                    }else{
                                        $(".content").css("height","100%")
                                        $(".content").html("")
                                        $('.content').addClass('myimage')
                                    }

                                }
                            })

                        }
                    }]
                });
            }else if(difference<-90){
                weui.dialog({
                    // title: '错误提示',
                    content: '只能查看90天内的缴费记录',
                    className: 'custom-classname',
                    buttons: [{
                        label: '确定',
                        type: 'primary',
                        onClick: function () {
                            $('#startDate').text(date1.getFullYear()+"-" + (date1.getMonth()+1 < 10 ? '0'+(date1.getMonth()+1) : date1.getMonth()+1) + "-" + (date1.getDate()<10 ? '0'+date1.getDate() : date1.getDate()));
                            $('#endDate').text(date.getFullYear()+"-" + (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + "-" + (date.getDate()<10 ? '0'+date.getDate() : date.getDate()));
                            var datas={
                                "beginDay":$("#startDate").text(),
                                "endDay":$("#endDate").text()
                            }
                            $.ajax({
                                url: '/reportQuery/getReports',
                                dataType: 'json',
                                type: 'get',
                                data: datas,
                                success: function (Data) {
                                    if(Data.code==0){
                                        var str = "";
                                        for(var i=0;i<Data.info.length;i++){
                                            str += "    <div class=\"all\">\n" +
                                                "        <div class=\"top\">\n" +
                                                "            <div class=\"a\">"+Data.info[i].applyDept+"</div>\n" +
                                                "            <div class=\"b\">"+Data.info[i].outReportDate+"</div>\n" +
                                                "        </div>\n" +
                                                "\n" +
                                                "        <div class=\"bottom\">\n" +
                                                "            <div class=\"c\">"+Data.info[i].outReportType+"</div>\n" +
                                                "           <div class=\"d\"><a href=\"javascript:;\" onclick='toDetail("+Data.info[i].inpatientNo+","+Data.info[i].outReportClass+")'>></a></div>" +
                                                "        </div>\n" +
                                                "    </div>";
                                        }
                                        $(".content").html(str)
                                    }else{
                                        $(".content").css("height","100%")
                                        $(".content").html("")
                                        $('.content').addClass('myimage')
                                    }

                                }
                            })

                        }
                    }]
                });
            }else if(difference>-90 && (difference<0||difference==0)){
                var datas={
                    "beginDay":$("#startDate").text(),
                    "endDay":$("#endDate").text()
                }
                $.ajax({
                    url: '/reportQuery/getReports',
                    dataType: 'json',
                    type: 'get',
                    data: datas,
                    success: function (Data) {
                        if(Data.code==0){
                            var str = "";
                            for(var i=0;i<Data.info.length;i++){
                                str += "    <div class=\"all\">\n" +
                                    "        <div class=\"top\">\n" +
                                    "            <div class=\"a\">"+Data.info[i].applyDept+"</div>\n" +
                                    "            <div class=\"b\">"+Data.info[i].outReportDate+"</div>\n" +
                                    "        </div>\n" +
                                    "\n" +
                                    "        <div class=\"bottom\">\n" +
                                    "            <div class=\"c\">"+Data.info[i].outReportType+"</div>\n" +
                                    "           <div class=\"d\"><a href=\"javascript:;\" onclick='toDetail("+Data.info[i].inpatientNo+","+Data.info[i].outReportClass+")'>></a></div>" +
                                    "        </div>\n" +
                                    "    </div>";
                            }
                            $(".content").html(str)
                        }else{
                            $(".content").css("height","100%")
                            $(".content").html("")
                            $('.content').addClass('myimage')
                        }

                    }
                })
            }


}
    });
}
function DateDiff(sDate1, sDate2){    //sDate1和sDate2的格式是2019-03-11
    var  aDate,  oDate1,  oDate2,  iDays
    aDate  =  sDate1.split("-")
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式
    aDate  =  sDate2.split("-")
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])
    iDays  =  parseInt((oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数
    return  iDays
}




