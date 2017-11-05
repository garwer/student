var urlStr = "/student/checkLogin.do?method=";

function menuBarInit(divId, tab) {
	tab = tab ? tab : 0;
	var $li1 = $('<li class="menu-bg-li">主页</li>').data('url','/student/course/mainPage.jsp');
	var $li2 = $('<li class="menu-bg-li">课题选择</li>').data('url','/student/course/coursePage.jsp');
	var $li3 = $('<li class="menu-bg-li">我的收藏</li>').data('url','/student/course/myCollect.jsp');
	var $li4 = $('<li class="menu-bg-li">积分明细</li>').data('url','/student/course/score.jsp');
	var $li5 = $('<li class="menu-bg-li">个人中心</li>').data('url','/student/course/center.jsp');
	
	
	var $li6 = $('<li class="menu-bg-li">管理员中心</li>').data('url','/student/course/manage.jsp');
	var $li7 = $('<li class="menu-bg-li">添加学生</li>').data('url','/student/course/addStudent.jsp');
	var $li8 = $('<li class="menu-bg-li">添加课题</li>').data('url','/student/course/addCourse.jsp');

	var $li9 = $('<li class="menu-bg-li">教师中心</li>').data('url','/student/course/teacher.jsp');
	var $li10 = $('<li class="menu-bg-li">我的学生</li>').data('url','/student/course/myStudent.jsp');
	var $li11 = $('<li class="menu-bg-li">课程管理</li>').data('url','/student/course/myCourse.jsp');

	var name = $('#studentName').val();
	var id = $('#studentId').val();
	var type = $('#userType').val();
	var $menuDiv = $('<div class = "top-div">'
			 +  ' <form name="frmLogin" action="/student/Logout" target="_top" style="display:none"></form>'
			 +  '	<div>'
			 +  '		<div style="font-size:23px;float:left;position:relative;padding-top:16px;margin-left:20px;color:white;font-family:Arial,Verdana,Sans-serif"><img src="images/book.png" class="cursor" onclick="openFile()" style="vertical-align:middle;"/><span style = "vertical-align:middle;margin-left:2px">阳光学院选题平台系统</span></div>'
			 +  '			<div style="padding-top:1%;padding-left:82%;"><span class="student">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span class = "cursor">'+name+'</span>，欢迎您！</span><a class = "cursor blue" onclick="document.frmLogin.submit()">点击注销</a></div>'
			 +  '		</div>'
			 +  '   </div>'
			 + '	<div class="menu-bg-bottom">'
			 + '		<ul id="menu-ul">'					
			 + '		</ul>'
			 +'		</div>'
			 +  '   </div>');
	
	if(type == 'student') {
		$menuDiv.find("#menu-ul").append($li1).append($li2).append($li3).append($li4).append($li5);
	} else if(type == 'teacher') {
		$menuDiv.find("#menu-ul").append($li9).append($li10).append($li11);
	} else if(type == 'admin'){
		$menuDiv.find("#menu-ul").append($li6).append($li7).append($li8);
	}
	//绑定事件
	$menuDiv.find('li.menu-bg-li').on('click', function() {				
		 var url = $(this).data('url');
    	 if(url) {
    		 window.location.href = url; 
    	 }	
	});
	
	$menuDiv.find('li.menu-bg-li').removeClass("menu-bg-li-over");
	$menuDiv.find('li.menu-bg-li:eq(' + tab + ')').addClass("menu-bg-li-over");
	
	$('#' + divId).append($menuDiv);
	
	return $menuDiv
};

//在线预览打开word文件
function openFile() {
	var url="doc.jsp";
	window.open(url);
}

function showDetail(id) {
	if(!id) {
		var id = $('#studentId').val();
	}
	$('#flagTitle').html("学生个人资料");
	$('#infoModal').draggable({   
	    handle: ".modal-header",   
	    cursor: 'move',   
	    refreshPositions: false  
	});
	$('#infoModal').modal('show');
	var url = urlStr + "getUserInfo&id="+id;
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		success:function(jsonStr) {
			console.log(jsonStr);
			//alert(jsonStr[0].user_name);
			$('#sId').html(jsonStr[0].user_id);
			$('#name').html(jsonStr[0].user_name);
			$('#sex').html(jsonStr[0].user_sex);
			$('#pro').html(jsonStr[0].user_profession);
			$('#birthDay').html(jsonStr[0].user_birth);
			$('#course').html(jsonStr[0].choose_course);
			if(jsonStr[0].photo_adr == null) {
				document.getElementById("myPhoto").innerHTML = '<img src = "images/none.png" style="width:120px;height:120px"/>';
			}else{
				document.getElementById("myPhoto").innerHTML = '<img src = '+jsonStr[0].photo_adr+' style="width:120px;height:120px" />';
			}
		}
	})
}
/*window.showStudentMsg = function() {
	  window.sweetAlertInitialize = function() {
		    var sweetHTML = '<div class="sweet-overlay" tabIndex="-1"></div><div class="sweet-alert" tabIndex="-1"><div class="icon error"><span class="x-mark"><span class="line left"></span><span class="line right"></span></span></div><div class="icon warning"> <span class="body"></span> <span class="dot"></span> </div> <div class="icon info"></div> <div class="icon success"> <span class="line tip"></span> <span class="line long"></span> <div class="placeholder"></div> <div class="fix"></div> </div> <div class="icon custom"></div> <h2>Title</h2><p>Text</p><button class="cancel" tabIndex="2">Cancel</button><button class="confirm" tabIndex="1">OK</button></div>',
		        sweetWrap = document.createElement('div');
		    	sweetWrap.innerHTML = sweetHTML;
		    // For readability: check sweet-alert.html
		    document.body.appendChild(sweetWrap);
		  };
}*/

function closeMsg() {
	$('#sweet-overlay').hide();
	$('#workOrderConfig').fadeOut(1000); //jquery淡入显示div
}
