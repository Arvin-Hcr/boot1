/* 
 * 定义URL
**/
var getAllCardTypeInfoUrl = "/GetCardTypeServlet";
var addUserUrl = "/AddUserServlet";
var getLastCardIdUrl = "/GetLastCardIdServlet";
/**
 * 初始化
**/
$(function () {
        /*
		 * 实现省市级联
		 * 1.先创建省市json对象
		 * 2.通过json数组.keys()方法取出json中所有的键
		 * 3.将所有取出的键放入第一个下拉列表中：
		 * 创建option标签，遍历数组将键值放入
		 * 4.将创建的option标签放入复选框中
		 * 5.当省级下拉列表选中改变时触发市级下拉列表变化，创建函数
		 * 6.获取第一个复选框选中的键值
		 * 7.根据键值取出当前键所有的value值
		 * 8.创建option标签，将取得的所有value值通过遍历放入option标签中
		 * 9.将创建的option标签放入市级的复选框
		 */
    //1.创建省市级联json对象
    var data={
        "河南":["郑州","新乡","驻马店","开封"],
        "海南":["三亚","海口"],
        "安徽":["合肥","芜湖","马鞍山"],
        "云南":["昆明","大理","西双版纳","玉溪"],
        "湖南":["长沙","湘潭","张家界"]
    }
    //2.将jeson对象遍历，取出所有的key值存在集合obj中
    var obj=Object.keys(data);
    //遍历集合访问所有的key值
    for (var i = 0; i < obj.length; i++) {
        //将取出的key值放入select中
        var opt=$("<option>"+ obj[i]+"</option>");
        $("#province").append(opt);
    }
    //为第一个多选框添加单击事件
    $("#province").change(function(){
        //在改变之前将第二个select中的值清空
        $("#city").html("");
        //获取省级下拉列表中选中的键值放入value中
        var value=data[$("#province").val()];
        //将获取到的value值遍历
        for (var i = 0; i < value.length; i++) {
            //创建option标签，将取到的value值放在option中
            var opt =$("<option>"+value[i]+"</option>");
            //将创建的option值放入第二个select中
            $("#city").append(opt);
        }
    })
    var userId = window.localStorage.getItem("roleId");
    var userName = window.localStorage.getItem("userName");
    $("#staffId").val(userId);
    $("#staffName").val(userName);
    userManage.initCardType();
    userManage.changedLevel();
    userManage.checkAmount();
    getLastCardId();

})
 function getLastCardId (){
     $.ajax({
         url:getLastCardIdUrl,
         type:'post',
         dataType:'json',
         success:function(result){
             $("#cardId").val(result.data);
         }
     })
 }
var userManage = {};
/**
 * 加载所有的会员类型
**/
userManage.initCardType = function () {
    $.ajax({
        url: getAllCardTypeInfoUrl,
        type: 'post',
        dataType: 'json',
        success: function (result) {
            for (var i = 0; i < result.data.length; i++) {
                var opt = $("<option data-rank='" + result.data[i].rank + "' value='" + result.data[i].id + "'>" + result.data[i].name + "</option>");
                $("#userLevel").append(opt);
            }
        }
    })
}
/**
 * 会员等级联动积分
**/
var amount = 0;
var currAmount = 0;
userManage.changedLevel = function () {
    $("#credit").val(0);
    $("#amount").val(0);
    $("#userLevel").change(function () {
        $("#credit").val($("#userLevel :selected").data("rank"));
        $("#amount").val($("#userLevel :selected").data("rank"));
        amount = $("#userLevel :selected").data("rank");
    });
}
/**
 * 对amount进行校验
 *
 * 暂时不用
// **/
userManage.checkAmount = function () {
    $("#amount").blur(function () {
        if ($("#amount").val() < amount) {
            toastr['error']("起充金额不能小于" + amount + "元");
            $("#amount").val(amount);
        } else {
            currAmount = $("#amount").val();
            $("#userLevel").val(6);
            $("#userLevel").change();
        }
    });
}


userManage.add = function () {
    //staffId就是登陆的时候的userName,因为登陆的名字是staff表中的staffId
    var roleId = window.localStorage.getItem("roleId");
    console.log(roleId);
    var bootstrapValidator=$("#addUserForm").data('bootstrapValidator');
    bootstrapValidator.validate();
    $.ajax({
        url: addUserUrl,
        type: 'post',
        data: {
            "userId": $("#cardId").val(),
            "cardId":$("#cardId").val(),
            "userName": $("#userName").val(),
            "userPhone": $("#userPhone").val(),
            "userLevel": $("#userLevel").val(),
            "userStatus": $("#userStatus").val(),
            "roleId": roleId,
            "credit": $("#credit").val(),
            "amount": $("#amount").val(),
            "idno": $("#idno").val(),
            "userSex": $("#userSex").val(),
            "area": $("#address").val(),
            "address": $("#province").val()+$("#city").val(),
            "momo": $("#momo").val(),
            "createdTime":$("#createdTime").val(),
            "birthday":$("#birthday").val(),
        },
        dataType: 'json',
        success: function (result) {
            toastr['success']("操作成功");
            location.reload(true);
        }
    })
}
$("#addUserForm").bootstrapValidator({
    feedbackIcons:{
        valid:'giyphicon glyphicon-ok',
        invalid:'glyphicon  glyphicon-remove',
        validating:'glyphicon glyphicon-refresh'
    },
    fields:{
        userName:{
            validators:{
                notEmpty:{
                    message:"会员姓名不能为空"
                }
            }
        },
        userPhone:{
            validators:{
                notEmpty:{
                    message:"会员电话不能为空"
                },
                regexp:{
                    regexp:/^1[345678]\d{9}$/,
                    message:"请输入正确的手机号"
                }
            }
        },
        userLevel:{
            validators:{
                notEmpty:{
                    message:"会员姓名不能为空"
                }
            }
        },
        userStatus:{
            validators:{
                notEmpty:{
                    message:"状态不能为空"
                }
            }
        },
        staffName:{
            validators:{
                notEmpty:{
                    message:"办卡人员不能为空"
                }
            }
        },
        credit:{
            validators:{
                notEmpty:{
                    message:"初始积分不能为空"
                }
            }
        },
        amount:{
            validators:{
                notEmpty:{
                    message:"起充金额不能为空"
                }
            }
        },
        idno:{
            validators:{
                notEmpty:{
                    message:"身份证号不能为空"
                },
                regexp:{
                    regexp:/^\d{17}[0-9|x]$/,
                    message:"请输入正确的身份证号"
                }
            }
        },
        userSex:{
            validators:{
                notEmpty:{
                    message:"性别不能为空"
                }
            }
        },
        address:{
            validators:{
                notEmpty:{
                    message:"地址不能为空"
                }
            }
        },
    }
})
