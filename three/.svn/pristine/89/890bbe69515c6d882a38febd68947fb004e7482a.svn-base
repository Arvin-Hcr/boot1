/**
 * @author 冯世杰
 * @date 2019/12/28
 * 定义函数
 **/
$(function () {
    pass.login();
});

/**
 * @author 冯世杰
 * @date 2019/12/28
 * 定义函数
 **/
var pass = {};

pass.login = function () {
    $.ajax({
        url: "/ChangPasswordServlet",
        type: "post",
        data: {
            "userName": $("#loginName").val(),
            "password": $("#loginPwd").val()
        },
        dataType: "json",
        success: function (result) {
            if (result.status > 0){
                toastr['success']("修改成功");
                window.localStorage.clear;
                // window.history.go(-1);
                window.parent.location.href="login.html";
            }else {
                toastr['error'](result.message);
            }
        }
    })
}
