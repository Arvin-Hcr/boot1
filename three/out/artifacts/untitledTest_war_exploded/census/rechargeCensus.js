$.ajax({
    url: "/GetReChargerecordServlet",
    type: "post",
    dataType: "json",
    success: function (result) {
        if (result.data != null) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            var option = {
                xAxis: {
                    type: 'category',
                    data: result.data.m
                },
                tooltip: {  //这是鼠标移到所属领域产生的线条
                    trigger:'axis'
                },
                yAxis: {
                    type: 'value'
                },
                series: [{   //这个是显示数据时的样式  name 为标题  data 为数据  type 为统计图类型
                    name: '充值总金额',
                    data: result.data.d,
                    type: 'line'
                }]

            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

    }
})