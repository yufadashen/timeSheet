//返回首页
function index(){
    window.location.href = "/home/goHospitalMain"
}

function message(data){
    if ($("#toast").css('display') != 'none'){
        return;
    }
    $("#toastMsg").text(data);
    $("#toast").fadeIn(100);
    setTimeout(function () {
        $("#toast").fadeOut(100);
    }, 2000);
}
