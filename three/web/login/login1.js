/**
 * 初始化
 **/
$(function () {

})

/**
 * 定义函数
 **/
var loginManager = {};

loginManager.login = function () {
    $.ajax({
        url: "/LoginServlet1",
        type: "post",
        data: {
            "userName": $("#loginName").val(),
            "password": $("#loginPwd").val()
        },
        dataType: "json",
        success: function (result) {
            console.log(result);
            if (result.status == 1 && result.data != null && result.data.status == 1){
                toastr['success'](result.message);
                window.location.href = "index/views/main1.html";
                /**
                 * localStorage 是一个数据库
                 * 只能存 键值对 而且是前端的 小型数据库 数据存放在 页面中
                 */
                localStorage.setItem("userName",result.data.userName);
                localStorage.setItem("staffId",result.data.userId);
                localStorage.setItem("roleId",result.data.roleId);
            }else {
                toastr['error']("登录失败");
            }
        }
    })
}

