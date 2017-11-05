//页面初始化
$(function() {
	var id = $('#studentId').val();
	//页面头部
	menuBarInit("menuDivId", 2);
	//加载信息
	getAllMyCourse();
});

//确定添加课题
function sureAddCourse() {
	var pid = $("#teacherId").val();
	var name = $('#courseName').val();
	var content = $('#courseContent').val();
	if(name == '' || content == '') {//若课题名称和课题内容为空
		alert('请完善填写相关信息');
		return;
	}
	var url = "/student/getCourse.do?method=insertCourse";		
	var data = {
		pid:pid,
		content:content,
		name:name
	};
	console.log(data);
	$.ajax({
		type:'post',
		url:url,
		contentType:'application/json;charset=utf-8',
		data : JSON.stringify(data),
		dataType:'text',
		async:'false',
		success:function(jsonStr) {	//添加成功提示 刷新教师课题页面
			$("#titleModal").draggable({   
			    handle: ".modal-header",   
			    cursor: 'move',   
			    refreshPositions: false  
			});
			$('#titleModal').modal('show');
			getAllMyCourse();
		}	
	});
}

