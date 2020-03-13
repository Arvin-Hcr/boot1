/**
 *会员增长趋势统计
 */
var months;
var datas;
$(function () {
    $.ajax({
        url: "/GetNearYearUserServlet",
        type: "post",
        dataType: "json",
        success: function (result) {
            if (result.data != null) {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main3'));
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '近一年会员增长趋势图'
                    },
                    xAxis: {
                        type: 'category',
                        data: result.data.m
                    },
                    tooltip: {
                        trigger:'axis'
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        name:'会员增长',
                        stack: '会员注册数',
                        data: result.data.d,
                        type: 'line'
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        }
    })

    getNewsManner.view();
    getNewsManner.show1();
    getNewsManner.show2();
})



/*

 */

var getNewsManner={};


getNewsManner.show2=function(){
    $.ajax({
        url:"/GetRechargeAmountServlet",
        type:"post",
        dataType:"json",
        success:function (res) {

            if(res.status==1){
                var myChart = echarts.init(document.getElementById('main5'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '近一年每个月会员充值的总数额'
                    },
                    tooltip: {},
                    legend: {
                        data:['总金额']
                    },
                    xAxis: {
                        data:res.data.m
                    },
                    yAxis: {},
                    series: [{
                        name: '销量',
                        type: 'bar',
                        data:res.data.d
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        }
    })


}



getNewsManner.show1=function(){
    $.ajax({
        url:"/GetVipOServlet",
        type:"post",
        dataType:"json",
        success:function(res){
            console.log(res);

        var myChart = echarts.init(document.getElementById('main4'));

    option = {
        title : {
            text: '近一年来各个vip所占的比例',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            right: 'right',
            data: res.data.name
        },
        series : [
            {
                name: '各个vip所占的比例',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:res.data.d,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
        }
})
}



getNewsManner.view=function () {
    $.ajax({
        url:"/GetNewsServlet",
        type: 'post',
        data: {
            "pageNumber":"1",
            "pageSize":"6",
            "searchStatus":1
        },
        dataType: 'json',
        success: function (result) {
            var row=result.data.list;
            for (var i = 0; i <row.length ; i++) {
                var tr=$("<tr></tr>");
                var td1=$("<td id='td1' onclick='getNewsManner.content("+JSON.stringify(row[i])+")'>"+"<a href='#'>"+row[i].title+"</a>"+" <img src='news.gif' />"+"</td>");
                var td2=$("<td id='td2'>"+row[i].createdTime+"</td>")
                tr.append(td1);
                tr.append(td2);
                $("#tab").append(tr);
            }

        }
    })
}
getNewsManner.content=function (row) {
    $("#id").val(row.id);
    $("#newsTitle").val(row.title);
    $("#newsContent").val(row.content);
    $("#myModal").modal('show');
}