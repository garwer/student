<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>


<!DOCTYPE html>
<html lang="en">
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=11">  
    <title>在线预览文件功能</title> 
    <script src="js/jquery/jquery.min.js"></script>
	<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
	<link href="css/cropper/cropper.min.css" rel="stylesheet">
	<link href="css/sitelogo/sitelogo.css" rel="stylesheet">
	<script src="js/cropper/cropper.min.js"></script>
	<script src="js/sitelogo/sitelogo.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>

  </head>
  
<body onload="init()">
  <div style="position: absolute;top:30px;left:10px;text-align:center;width:100%;">
    	<div id="listDivId" style="width:100%;text-align:left;" >
    		<ul id="attachments" class="commentsUl">					
			</ul>
    	</div>	        	       
   </div>
<script type="text/javascript">
	function init() {
		getDoc();
	}
	
	function getDoc() {
		getFile();
	}
	
	function getFile() {
		var urlStr = "/student/checkLogin.do?method=";
		var url = urlStr + "openFile";
		$.ajax({
			type:'post',
			url:url,
			dataType:'html',
			success:function(jsonStr) {
				var fileType = "1";
				var fileUrl = "/servlet/DisplayChart?filename=D:/up/swfFile/1487755963893_1043966930.swf";  ///servlet/DisplayChart?filename=
				var url="showDoc.jsp?fileType="+fileType+"&swfPath="+fileUrl;
				window.location.href = url;
			},
			error:function(){
				alert("加载失败")
			}
		})
	}

	
</script>
</body>
</html>
