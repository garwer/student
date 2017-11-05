<%@page language="java" pageEncoding="UTF-8" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
Object userName = session.getAttribute("userName");
if(userName == null) {
	System.out.println("当前session为空");
}
%>  
<!DOCTYPE html>
<head>
</head>
	<body>
		<h1>哈哈哈</h1>
	</body>
</html>