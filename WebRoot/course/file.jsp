<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<html>
  <head>
    <meta charset="gbk">
    <meta http-equiv="X-UA-Compatible" content="IE=11">  
    <title>文件上传功能</title> 
  </head>
  <script type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
  <script type="text/javascript">
	  function openFile() {
		var form = new FormData(document.getElementById("uploadFile"));
		form.append("username","ljw")
		var urlStr = "/student/uploadFile.do?method=";
		var url = urlStr + "uploadFile&namea=1";
		$.ajax({
			type:'post',
			url:url,
			//data:form,
			data:form,//form表单
			//dataType:'json',
			processData:false,
            contentType:"multipart/form-data",//要求为Boolean类型的参数，默认为true。默认情况下，发送的数据将被转换为对象（从技术角度 来讲并非字符串）以配合默认内容类型"application/x-www-form-urlencoded"。如果要发送DOM 树信息或者其他不希望转换的信息，请设置为false。
			success:function(flag) {
				alert(flag);
				alert("上传成功!");
			 },
			 error:function(flag) {
				alert(2);
			 }
		});
	}
  </script>
<body>
  <form  id="uploadFile" method="post" enctype="multipart/form-data">  
   	文件上传：<input type="file" name="fileupload"/>  
   	 描述：<input type="text" name="descwhat"/>  
    <input type="button" value="submit" onclick = "openFile()"/>  
   </form>	
</body>
</html>
