let cos = null; // 腾讯云对象存储地址
$(function (){
    $.get("/index/init",function (r) {
        cos = r.data.cos;
        console.log(r);
    })
})