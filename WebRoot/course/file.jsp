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
    <title>�ļ��ϴ�����</title> 
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
			data:form,//form��
			//dataType:'json',
			processData:false,
            contentType:"multipart/form-data",//Ҫ��ΪBoolean���͵Ĳ�����Ĭ��Ϊtrue��Ĭ������£����͵����ݽ���ת��Ϊ���󣨴Ӽ����Ƕ� ���������ַ����������Ĭ����������"application/x-www-form-urlencoded"�����Ҫ����DOM ����Ϣ����������ϣ��ת������Ϣ��������Ϊfalse��
			success:function(flag) {
				alert(flag);
				alert("�ϴ��ɹ�!");
			 },
			 error:function(flag) {
				alert(2);
			 }
		});
	}
  </script>
<body>
  <form  id="uploadFile" method="post" enctype="multipart/form-data">  
   	�ļ��ϴ���<input type="file" name="fileupload"/>  
   	 ������<input type="text" name="descwhat"/>  
    <input type="button" value="submit" onclick = "openFile()"/>  
   </form>	
</body>
</html>
