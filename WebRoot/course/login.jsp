<%@page language="java" pageEncoding="UTF-8" %>
<%
    String contextURL = request.getContextPath() + "/";
%>
<!DOCTYPE html>
<head>
 	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=11">
    <script src="js/jquery/jquery.min.js"></script>
    <script src="js/select.js"></script>
    <script src="js/login.js"></script>
    <script>
    $(function() {
        $('#login #passWord').focus(function() {
            $('#owl-login').addClass('password');
        }).blur(function() {
            $('#owl-login').removeClass('password');
        });
        var dd = new DropDown( $('#dd') );
		$(document).click(function() {
			$('.wrapper-dropdown-5').removeClass('active');
		});
		//初始化背景
    });
	function changeBg(flag) {
		if(flag == "green") {
			$("body").css("background","url(images/background/green.png)");
		}
		if(flag == "sky") {
			$("body").css("background","url(images/background/sky.png)");
		}	
		if(flag == "iamge") {
			$("body").css("background","url(sky.png)");
		}
	}
    </script>
    <script type="text/javascript">
			function DropDown(el) {
				this.dd = el;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;
					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						event.stopPropagation();
					});	
				}
			}
		
		</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线选题系统</title>
<link rel="stylesheet" href="css/login.css">
<%--<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">--%>
<link rel="shortcut icon" href="images/ico/login.ico"/>
</head>
<body>
<!-- begin -->

<img src = "images/YGlogo2.png"/>
		<section class="main" style="float:right;vertical-align: middle;"><img src = "images/setSkin.png" style="height:30px;line-height:30px;float:right;cursor:pointer;vertical-align: middle;"/>
				<div class="wrapper-demo">
					<div id="dd" class="wrapper-dropdown-5" tabindex="1" style="  vertical-align: middle;">风格选择
						<ul class="dropdown">
							<li><a onclick="changeBg('green')"><img src = "images/ico/green.ico" style="left:15px;position:absolute"/><i class="icon-cog"></i>自然绿&nbsp;&nbsp;</a></li>
							<li><a onclick="changeBg('sky')"><img src = "images/ico/cloud.ico" style="left:15px;position:absolute"/><i class="icon-cog"></i>天空蓝&nbsp;&nbsp;</a></li>
							<li><a onclick="changeBg('image')"><img src = "images/ico/image.ico" style="left:15px;position:absolute"/><i class="icon-cog"></i>自由色&nbsp;&nbsp;</a></li>
						</ul>
					</div>
				</div>
			</section>
<div id="login" style="margin:0 auto;width:398px;">
    <div class="wrapper"> 
        <div class="login">
            <div  class="container offset1 loginform">
                <div id="owl-login">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>
                <div class="pad">               
                    <div class="control-group">
                        <div class="controls">
                            <label class="control-label fa fa-envelope"></label>
                            <input type="text" id = "userId" placeholder="请输入账号" tabindex="1"  class="form-control input-medium">
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="control-label fa fa-asterisk"></label>
                            <input type="passWord" id="passWord" placeholder="请输入密码"  class="form-control input-medium">
                        </div>
                    </div>                 
                    <div class="controls yzm-text">        
                        <input  type="text" id = "yzm" placeholder="验证码" tabindex="2" style="width:100px;float:left" class="form-control input-medium ">  
                        <img alt="验证码" id="resetPwd" src="/student/ImageServlet" style="float:left;text-align:center;margin-top:5px;margin-left:10px " title = "看不清，换一张" onclick="reloadVerifyCode('resetPwd')">        
                    </div>                   
                </div>
                <div style="padding:5px 5px 5px 30px">
                 <select id ="type"  class="script-select-all" style = "border-radius: 4px;padding: 3px 14px 3px 3px;"><option value="student">学生登陆</option><option value="teacher">老师登陆</option>><option value="admin">管理员登陆</option></select>   		
                </div>
                <div id="hideMsg" style="padding:0px 5px 2px 30px;display:none;">
                	<img src = "images/errorMsg.png" style="vertical-align: middle;"/><span id="promptMsg" style="vertical-align: middle;color:red"></span>
                </div>
                <div class="form-actions"><a tabindex="5" class="btn pull-left btn-link text-muted"></a><%--<a  tabindex="6" class="btn btn-link text-muted">注册</a>
                    --%><button type="submit" tabindex="4" class="btn btn-primary" onclick = "login()">登录</button>
                </div>
           
            </div>          
        </div>     
    </div>
</div>
<!-- end -->
  <div style="position:fixed;left:22%;top:90%;">Copyright@ 阳光学院 all rights reserved 地址：福州经济技术开发区（马尾）卧龙山 公安备案号 35011043033号  作者 黄景彪</div>
</body>
</html>