//用于进行图片上传，返回地址
function setImg(obj){
    var f=$(obj).val();
    if(f == null || f ==undefined || f == ''){
        return false;
    }
    if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)){
        alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
        $(obj).val('');
        return false;
    }
    var data = new FormData();
    $.each($(obj)[0].files,function(i,file){
        data.append('file', file);
    });
    $.ajax({
        type: "POST",
        url: "/upload/uploadPicture",
        data: data,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        dataType:"json",
        success: function(json) {
            var ret = eval(json);
            if(ret.code==0){
                console.log(ret.info.relativePath);
                $("#photoUrl").val(ret.info.path);//将地址存储好
                $("#photourlShow").attr("src",ret.info.path);//显示图片
            }else{
                alert(ret.message);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败，请检查网络后重试");
        }
    });

}
