$(function() {
	//页面头部
	menuBarInit("menuDivId", 4);
	var id = $('#studentId').val();
	isChoose(id);
	getCenterInfo(id);
	cancelCourse();
});

function cancelCourse() {
	$('#cancelBtn').click(function() {
		$("#cancelModal").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#cancelModal').modal('show');	
	});
}

function sureCancel() {
	var id = $('#studentId').val();
	var url = "/student/getCourse.do?method=cancleCourse&id="+id;
	$.ajax({
		type:'post',
		url:url,
		dataType:'text',
		contentType:'application/json;charset=utf-8',
		success:function() {
			isChoose(id)
		}
	})
}

function isChoose(id) {
	var url = "/student/getCourse.do?method=isChoose&id="+ id;		
	$.ajax({
		type:'post',
		url:url,
		contentType:'text/html;charset=utf-8',
		dataType:'text',
		success:function(jsonStr) {
			console.log(jsonStr);
			if(jsonStr == 'null'|| jsonStr == null) {
				$('#haveChoose').hide();
				$('#noneCourseInfo').show();
			} else {
				$('#noneCourseInfo').hide();
				$('#haveChoose').show();
			}
		}
	})
}

function getCenterInfo(id) {
	var url = "/student/getCourse.do?method=getCenterInfo&id="+ id;		
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		success:function(jsonStr) {
			$('#collectNum').html(jsonStr.collectNum);
			$('#scoreNum').html(jsonStr.scoreNum);
			$('#myCourse').html(jsonStr.course);
			$('#myTeacher').html(jsonStr.teacher);
			$('#content').html(jsonStr.content);

		}
	})
}