var date = new Date();
var month = (date.getMonth()+1)>9?(date.getMonth()+1):"0"+(date.getMonth()+1);
var day = date.getDate()>9?date.getDate():"0"+date.getDate();
var date2 = date.getFullYear()+"-"+month+"-"+day;
console.log(date2);
layui.use('laydate', function(){
    var laydate = layui.laydate;
    laydate.render({
        elem: '#date2'
        ,position: 'static'
        ,value: date2
        ,showBottom: false
        ,change: function(value, date){ //监听日期被切换
            // lay('#testView').html(value);
            date2=value;
        }
    });
});

function  new_task() {
    console.log(date2);
 window.location.href="/jump/addRecording?date2="+date2;
}
function  see_details() {

}
function  view_details_month() {
    console.log(date2);
    window.location.href="/jump/detailsMonth?date2="+date2;
}