<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=11">  
    <title>文件上传功能</title> 
  </head>
  
<body>
  <form action="/student/fileUpload" method="post" enctype="multipart/form-data">  
   	文件上传：<input type="file" name="fileupload"/>  
   	 描述：<input type="text" name="descwhat"/>  
    <input type="submit" value="submit"/>  
   </form>
	
</body>
</html>
