<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>echat图表样例</title>
	<script type="text/javascript" src="js/echarts.common.min.js"></script> 
  </head>
  
  <body>
    <div id="cake" style="width: 600px;height:400px;"></div>
    <div id="col" style="width: 600px;height:400px;"></div>
    <script>
    var cake = echarts.init(document.getElementById('cake'));
    var col = echarts.init(document.getElementById('col'));

	cakeOption = {
	    title: {
              text: '积分明细环形图'
        },
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'right',
	        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
	    },
	    series: [
	        {
	            name:'访问来源',
	            type:'pie',
	            radius: ['50%', '70%'],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {value:335, name:'直接访问'},
	                {value:310, name:'邮件营销'},
	                {value:234, name:'联盟广告'},
	                {value:135, name:'视频广告'},
	                {value:1548, name:'搜索引擎'}	          
	                ]
	        }
	    ]
	};
	
	
    var colOption = {
            title: {
                text: '积分明细柱形图'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["直接访问","邮件营销","联盟广告","视频广告","搜索引擎"]
            },
            yAxis: {},
            series: [{
                name: '积分明细',
                type: 'bar',
                data: [1548, 310, 234, 135,22]
            }]
        };

	cake.setOption(cakeOption);
	col.setOption(colOption);

	</script>
  </body>
</html>
