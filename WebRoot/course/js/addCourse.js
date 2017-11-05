
$(function() {
	var id = $('#studentId').val();
	//页面头部
	menuBarInit("menuDivId", 2);
	//加载信息
	getAllCourse();
	getAllTeacher(id);
});


function getAllTeacher(id) {
	var url = "/student/getCourse.do?method=getAllTeacher&id="+ id;		
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		async:false,
		success:function(jsonStr) {
			console.log(jsonStr);
			getAllTeacherSel(jsonStr);
		}
	})
}

function getAllTeacherSel(jsonStr) {
	var $selectObj = $("#selTeacher");
	//第一行设置为空
	//$selectObj.append('<option value="">请选择</option>');
	for(var i = 0; i < jsonStr.length; i++) {
		$selectObj.append('<option value="' + jsonStr[i].id + '">' + jsonStr[i].NAME + '</option>');
	}
}

function sureAddCourse() {
	var pid = $("#selTeacher").val();
	var name = $('#courseName').val();
	var content = $('#courseContent').val();
	if(name == '' || content == '') {
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
		success:function(jsonStr) {	
			$("#titleModal").draggable({   
			    handle: ".modal-header",   
			    cursor: 'move',   
			    refreshPositions: false  
			});
			$('#titleModal').modal('show');
			getAllCourse();
		}	
	});
}