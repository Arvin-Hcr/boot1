

/**
 * 初始化
 **/
$(function () {

    $("#roleuser").text(localStorage.getItem("userName"));
    transferCard.initSheng();
})

var transferCard = {};

transferCard.initSheng=function(){

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
        $("#sheng").append(opt);
    }
    //为第一个多选框添加单击事件
    $("#sheng").change(function(){
        //在改变之前将第二个select中的值清空
        $("#shi").html("");
        //获取省级下拉列表中选中的键值放入value中
        var value=data[$("#sheng").val()];
        //将获取到的value值遍历
        for (var i = 0; i < value.length; i++) {
            //创建option标签，将取到的value值放在option中
            var opt =$("<option>"+value[i]+"</option>");
            //将创建的option值放入第二个select中
            $("#shi").append(opt);
        }
    })
}




/**
 * 加载并卡1用户
 **/
var userCard=0;
transferCard.seachUserList = function () {
    $("#userList").bootstrapTable('refresh');
    userCard=1;
    $("#myModal").modal('show');
    transferCard.initList();

}

/**
 * 加载并卡2用户
 */
transferCard.seachUserList1 = function () {
    userCard=2;
    $("#myModal").modal('show');
    transferCard.initList();

}


/**
 * 加载table
 **/
transferCard.initList = function () {
    $("#userList").bootstrapTable({
        url: "/GetAllUserServlet", //请求路径
        method: 'post', //请求方式(*)
        contentType: 'application/x-www-form-urlencoded', //使用from表单方式提交(*)
        toolbar: '#toolbar', //工具按钮的容器
        striped: true, //是否启用隔行变色
        cache: false, //使用是否缓存 默认为true,所以一般情况下需要设置一下为false (*)
        pagination: true, //是否显示分页(*)
        sortable: false, //使用启用排序
        sortOrder: 'desc', //排序方式
        queryParams: transferCard.queryParams, //传递参数(*)
        queryParamsType: '',
        sidePagination: 'server', // 分页方式有两种 1.client 客户端分页  2.server分页
        pageNumber: 1, //初始化页数为第一页
        pageSize: 5, //默认每页加载行数
        pageList: [10, 25, 50, 100], //每页可选择记录数
        strictSearch: true,
        showColumns: false, // 是否显示所有的列
        showRefresh: false, // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        uniqueId: "id", // 每一行的唯一标识，一般为主键列
        showToggle: false, // 是否显示详细视图和列表视图的切换按钮
        cardView: false, // 是否显示详细视图
        detailView: false, // 是否显示父子表
        smartDisplay: false,
        onClickRow: function (e, row, element) {
            $(".success").removeClass("success");
            $(row).addClass("success");
        },
        responseHandler: function (result) {
            if (result != null) {
                return {
                    'total': result.data.count, //总条数
                    'rows': result.data.userlist //所有的数据
                };
            }
            return {
                'total': 0, //总条数
                'rows': [] //所有的数据
            }
        },
        //列表显示
        columns: [{
            field: 'id',
            title: "编号",
            visible: false
        }, {
            field: 'userId',
            title: "会员编号"
        }, {
            field: 'userName',
            title: "会员姓名"
        }, {
            field: 'cardId',
            title: "会员卡号"
        }, {
            field: 'levelName',
            title: "等级"
        }, {
            field: 'credit',
            title: "积分"
        }, {
            field: 'amount',
            title: "余额"
        }, {
            field: 'status',
            title: "状态",
            formatter: function (value) {
                switch (value) {
                    case 0 :
                        return "<span class='label label-danger'>禁用</span>";
                    case 1 :
                        return "<span class='label label-info'>启用</span>";
                }
            }
        }
        ]
    });
}

/**
 * 传递参数
 **/
transferCard.queryParams = function (params) {
    return {
        "pageNumber": params.pageNumber, //当前页数
        "pageSize": params.pageSize //每页条数
    }
}


/**
 * 双击表格加载 指定区域的数据
 **/
$("#myModal").on('dbl-click-row.bs.table', function (e, row) {
    if(userCard==1){

        $("#userCardId").text(row.cardId);
        $("#userName").text(row.userName);
        $("#cardType").text(row.levelName);
        $("#cardAmount").text(row.amount);
        $("#userCredit").text(row.credit);
        $("#inp").val(row.cardId);
        $("#myModal").modal('hide');
    }
    if(userCard==2){
        if($("#userCardId").text()==null||$("#userCardId").text().length==0){
            toastr['error']("未选中并卡人员");
            $("#myModal").modal('hide');
        }else{

            if( $("#userCardId").text()==row.cardId){
                toastr['error']("此用户以选为并卡人员,请重新选着");
            }else{
                $("#userCardId1").text(row.cardId);
                $("#userName1").text(row.userName);
                $("#cardType1").text(row.levelName);
                $("#cardAmount1").text(row.amount);
                $("#userCredit1").text(row.credit);
                $("#inp1").val(row.cardId);
                $("#myModal").modal('hide');
                transferCard.bing();
            }
        }
    }
})

/**
 * 用户二加载
 */


/**
 *进行转卡操作
 */
transferCard.userRechargeOk = function () {
    $.ajax({
        url:"/TransferCardServlet",
        type:"post",
        data:{
            "carId":$("#userCardId").text(),
            "getCardName":$("#getCardName").val(),
            "getCardphone":$("#getCardphone").val(),
            "sheng":$("#sheng").val(),
            "shi":$("#shi").val(),
            "idCard":$("#idCard").val()
        },
        dataType:"json",
        success:function (res) {
            if(res.status==1){
                toastr['success']("修改成功");
                $("#userCardId").text("");
                $("#userName").text("");
                $("#cardType").text("");
                $("#cardAmount").text("");
                $("#userCredit").text("");
                $("#inp").val("");
                $("#recharge")[0].reset();
                $("#shi").html("<option>请选择</option>>");
            }else{
                toastr['error'](res.message);
            }
        }
    })
}


/**
 * 合并卡信息
 */
transferCard.bing=function(){
        var fen =Number($("#userCredit1").text())+Number($("#userCredit").text());
    $("#getMoney").text(Number($("#cardAmount1").text())+Number($("#cardAmount").text()));
    $("#getFen").text(fen);
    $.ajax({
        url:"/GetVipServlet",
        type:"post",
        dataType:"json",
        success:function (res) {
            console.log(res.data);
            if(res.status==1){
               for(var i=0;i<res.data.length;i++){
                   if(fen<Number(res.data[i].rank)){
                       break;
                   }
                   $("#getDeng").text(res.data[i].name);
                   $("#getLevel").text(res.data[i].level);
               }
            }else{
                toastr['error'](res.message);
            }
        }
    })



}



/**
 * 并卡确认操作
 */
var resInp=true;

transferCard.userRechargeOk=function(){
    Modal.confirm({
        msg:"确定当前操作"
    }).on(function (e) {
        if(e){
            $.ajax({
                url:"/ParallelCarServlet",
                type:"post",
                data:{
                    userCardId1:$("#userCardId1").text(),
                    userCardId:$("#userCardId").text(),
                    getMoney: $("#getMoney").text(),
                    getLevel: $("#getLevel").text(),
                    getFen: $("#getFen").text()
                },
                dataType:"json",
                success:function(res){
                    if(res.status==1){
                        toastr['success']('并卡成功');
                        $("#box span:lt(13)").text("");
                        $("#userList").bootstrapTable('refresh');
                        $("#inp1").val("");
                        $("#inp").val("");
                    }else{
                        toastr['error']('操作失败');
                    }
                }
            })
        }
    })
}

/**
 * 搜索
 */
transferCard.search=function(){
 resInp=true;
if( $("#inp").val().length|| $("#inp").val()==0){
        resInp=false;
}
if(resInp){
    $.ajax({
        url: "/GetAllUserServlet",
        type:"post",
        data:{
            pageNumber:1,
            pageSize:5,
            cardId: $("#inp").val()
        },
        dataType:"json",
        success:function(res){
            console.log(res.data);
            if(res.status==1){
                $("#userCardId").text(res.data.userlist[0].cardId);
                $("#userName").text(res.data.userlist[0].userName);
                $("#cardType").text(res.data.userlist[0].levelName);
                $("#cardAmount").text(res.data.userlist[0].amount);
                $("#userCredit").text(res.data.userlist[0].credit);
                $("#inp").val(res.data.userlist[0].cardId);
                $("#myModal").modal('hide');

            }
        }
    })
}else{
    toastr['error']('卡号不能为空');
}
}

transferCard.search1=function(){
    var resInp=true;
    if( $("#inp").val().length==0|| $("#inp").val()==0){
        resInp=false;
    }

    if(resInp){

        $.ajax({
            url: "/GetAllUserServlet",
            type:"post",
            data:{
                pageNumber:1,
                pageSize:5,
                cardId: $("#inp").val()
            },
            dataType:"json",
            success:function(res){
                console.log(res.data);
                if(res.status==1){
                    $("#userCardId1").text(res.data.userlist[0].cardId);
                    $("#userName1").text(res.data.userlist[0].userName);
                    $("#cardType1").text(res.data.userlist[0].levelName);
                    $("#cardAmount1").text(res.data.userlist[0].amount);
                    $("#userCredit1").text(res.data.userlist[0].credit);
                    $("#inp1").val(res.data.userlist[0].cardId);
                    transferCard.bing();
                }else{
                    toastr['error']("数据有误");
                }
            }
        })
    }else{
        toastr['error']('卡号不能为空');
    }
}





