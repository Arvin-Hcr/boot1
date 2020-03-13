
var addGoodsUrl = "/AddGoodsServlet";
var initGoodsUnitUrl ="/GetAllUnitServlet";
var initGoodsCategoryUrl ="/GetGoodsCategoryServlet";
var getLastGoodsIdUrl="/GetLastGoodsIdServlet";

$(function () {
    goodsList.initUnitGoods();

    goodsList.getLastGoodsId();
})

var goodsList = {};

goodsList.getLastGoodsId= function(){
    $.ajax({
        url:getLastGoodsIdUrl,
        type:'post',
        data: {
            "goodsId":$("#goodsId").val()
        },
        dataType:'json',
        success:function (result) {
            $("#goodsId").val(result);
        }
    })
}
/**
 * 提交增加信息
 */
goodsList.add=function () {
    var bootstrapValidator = $("#AddGoodsForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()){
        $.ajax({
            url:addGoodsUrl,
            type:'post',
            data:{
                "goodsId":$("#goodsId").val(),
                "name":$("#name").val(),
                "code":$("#code").val(),
               /* "type":$("#type").val(),
                "unitName":$("#unitName").val(),*/
                "price":$("#price").val(),
                "categoryName":$("#categoryName").val(),
            },
            dataType:'json',
            success:function (result) {

                if(result.status==1){
                    alert("增加成功");
                    //toastr['success']("增加成功");
                    location.reload(true);
                }else {

                    toastr['error']("增加失败");
                }
            }
        })
    }
}

/**
 * 模态框单位下拉框请求
 */
goodsList.initUnitGoods = function () {
    $.ajax({
        url: initGoodsUnitUrl,
        type: 'get',
        dataType: 'json',
        success: function (result) {
            if (result.status == 1) {
                var res = result.data;
                for (var i = 0; i < res.length; i++) {
                    var opt = $("<option value='" + res[i].id + "'>" + res[i].name + "</option>");
                    $("#unitName").append(opt);
                }
            }
        }
    })
}
window.onload=function(){
    goodsList.initCategory();
};

goodsList.initCategory=function(){
    $.ajax({
        url:"/GetGoodsCategoryServlet",
        type:"post",
        dataType:"json",
        success:function(res){
            if(res.status==1){
                for (var i = 0; i < res.data.length; i++) {
                    console.log(res.data[i]);
                    $("#categoryName").append("<option value="+res.data[i].id+">"+res.data[i].name+"</option>>")

                }
            }
        }
    })
}


/**
 * 商品验证
*/
$("#AddGoodsForm").bootstrapValidator({
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
    },
    fields: {

        code:{
            validators:{
                notEmpty:{
                    message:"器材数量不能为空"
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:"请输入正确的器材数量"
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: "器材功能不能为空"
                }
            }
        },
       /* unitId: {
            validators: {
                notEmpty: {
                    message: "单位不能为空"
                }
            }
        },*/
       /* type: {
            validators: {
                notEmpty: {
                    message: "器材类型不能为空"
                }
            }
        },*/
        price:{
            validators:{
                notEmpty:{
                    message:"器材使用价格/时不能为空"
                },
                regexp:{
                    regexp:/^[0-9]+$/,
                    message:"请输入正确的器材使用价位"
                }
            }
        },
        categoryId: {
            validators: {
                notEmpty: {
                    message: "器材名称不能为空"
                }
            }
        }
    }
});



