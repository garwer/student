
$(function() {
	//页面头部
	menuBarInit("menuDivId", 3);
	//加载信息
	getMyScore();
});

function getData(type) {
	var id = $('#studentId').val();
	var url = "/student/getCourse.do?method=getScoreDetail&id="+ id;		
	$.ajax({
		type:'post',
		url:url,
		dataType:'text',
		success:function(jsonStr) {	
			if(type == 'chartCModel') {
				getChartC(jsonStr);
			} else if(type == 'chartRModel') {
				getChartR(jsonStr);
			}
		}
	})
}

function openChartC() {
	$("#chartCModel").draggable({   
	    handle: ".modal-header",   
	    cursor: 'move',   
	    refreshPositions: false  
	});
	$('#chartCModel').modal('show');
	getData('chartCModel');
}

function openChartR() {
	$("#chartRModel").draggable({   
	    handle: ".modal-header",   
	    cursor: 'move',   
	    refreshPositions: false  
	});
	$('#chartRModel').modal('show');
	getData('chartRModel');
}

function getChartC(jsonStr) {
	var cake = echarts.init(document.getElementById('cake'));
	jsonObj = JSON.parse(jsonStr);
	var integral = {
		comment: jsonObj.comment,
		collect: jsonObj.collect,
		study: jsonObj.study
	}
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
	        data:['评论课题','收藏课题']
	    },
	    series: [
	        {
	            name:'获取积分来源',
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
	                {value:integral.comment, name:'评论课题'},
	                {value:integral.collect, name:'收藏课题'}	        
	                ]
	        }
	    ]
	};
	

	cake.setOption(cakeOption);
}

function getChartR(jsonStr) {
	var col = echarts.init(document.getElementById('col'));
	jsonObj = JSON.parse(jsonStr);
	var data = [];
	var integral = {
		comment: jsonObj.comment,
		collect: jsonObj.collect,
		study: jsonObj.study
	}
	data.push(integral);
	var colOption = {
	        title: {
	            text: '积分明细柱形图'
	        },
	        tooltip: {},
	        legend: {
	            data:['积分']
	        },
	        xAxis: {
	            data: ['评论课题','收藏课题']
	        },
	        yAxis: {},
	        series: [{
	            name: '积分明细',
	            type: 'bar',
	            data: [integral.comment,integral.collect]
	        }]
	    };
	
	col.setOption(colOption);

}

