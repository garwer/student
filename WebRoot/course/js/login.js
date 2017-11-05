var urlStr = "/student/checkLogin.do?method=";
$(function() {
	$(document).ready(function() {
		//alert(1);
	})
});

function login() {
	var userId = $('#userId').val();
	var passWord = $('#passWord').val();
	var type = $('#type').val();
	var yzm = $('#yzm').val();
	var url = urlStr + "checkUser&userId=" + userId + "&passWord=" + passWord + "&type=" + type + "&yzm=" +yzm;
	$.ajax({
		type:'post',
		url:url,
		dataType:'text',
		success:function(flag) {
			if(flag == 'nullId' || flag == 'errorPwd') {	
				$('#hideMsg').show();
				$('#promptMsg').html("学生,教师或管理员账号密码有误");	
				return;
			}
			if(flag == 'errorYzm') {
				$('#hideMsg').show();
				$('#promptMsg').html("验证码错误");
				return;
			}
			if(type == 'student') {
				window.location.href = 'mainPage.jsp';
			} else if(type == 'admin'){
				window.location.href = 'manage.jsp';
			} else {
				window.location.href = 'teacher.jsp';
			}
		},
		error:function(flag) {
			$('#promptMsg').html("网络出错");
		}
	})
}

//刷新验证码操作
function reloadVerifyCode(id) {
	  var timenow = new Date().getTime();                        		
	  document.getElementById(id).src="/student/ImageServlet?d="+timenow;      
}