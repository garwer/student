<%@page language="java" pageEncoding="utf-8" %>
<%@ page import="course.entity.UserInfo"%>
<%
    String contextURL = request.getContextPath() + "/";
 	//Object userInfo = session.getAttribute("userInfo");
 	UserInfo info = (UserInfo)session.getAttribute("userInfo");
 	//获取保存的session中的学生id和学生姓名
 	String id = info.getUser_id();
 	String name = info.getUser_name();
 	String type = info.getUser_type();
%>
<HTML>
 <HEAD>
  <TITLE>学生选题页面</TITLE>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
  <link rel="stylesheet" href="css/zTreeStyle.css" type="text/css">
  <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
  <style>
  </style>
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">  
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/mainPage.css">
<script type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/teacher.js"></script> 
<SCRIPT type="text/javascript" >

</SCRIPT>
</HEAD>
<BODY>
<input type="hidden" id="teacherId" value= <%=id %> />
<input type="hidden" id="studentName" value= <%=name %> />
<input type="hidden" id="userType" value= <%=type %> />
<div id = "menuDivId" style="display:block"></div>
	<div class="col-xs-12 p-v-3">
       			<div class="col-xs-3 p-h-5 temp-div-line">
       				<div class="per-center"><!--  个人中心 start-->
       					<div class="per-center-div">
	       					<div class="per-center-title">教师中心</div>
		       				<div id="eventDivId" class="event-flow-div"></div>
		       				<hr class="per-line"/>
		       				<div>
		       					<div class="col-xs-6">
		       						<div  class="script-btn"  title="查看个人资料">
		       						<div class="script script-img5">&nbsp;&nbsp;&nbsp;&nbsp;</div>
		       						<div class="release-script" id="myStudent">我的学生</div>
		       						</div>
		       					</div>
		       					<div class="col-xs-6">
		       						<div class="script-btn">
		       						<div class="script script-img1">&nbsp;&nbsp;&nbsp;&nbsp;</div>
		       						<div class="release-script" id = "myCourse">课程管理</div>
		       						</div>
		       					</div>
		       					
		       				</div>
	       				</div>
       				</div>       	
       				</div>
       			<!--  个人中心 end-->	
       				
       			<div class="col-xs-6 p-h-5 temp-div-line">
       				<div class = "table-area">
	       				<div class = "">
	       					<div>
	       						<div class="col-xs-9 table-title-div1">
	       							最新课题
	       						</div>
	       						<div id = "moreScript"  class="script-btn col-xs-2 table-title-div2">
	       							
	       						</div>
	       							<div class="col-xs-1">
	       						</div>
	       					</div>
	       				</div>
	       				<table id ="new_course" class=" table  table-new-course">
							<thead>
								<tr>
									<th width="45%">
										<span class="set-line-height">课题名</span>
									</th>
									<th width="30%">
										<span class="set-line-height">发布时间</span>
									</th>
									<th width="25%">
										<span class="set-line-height">发布导师</span>
									</th>	
		
								</tr>
							</thead>	
							<tbody>
								
							</tbody>
						</table>
       			   </div>
       		 	</div>
       			<div class="col-xs-3 p-h-5">
       				<div class="spots-list "><!-- 热点脚本排行开始 -->
	       				<div class="spots-list-div"><!--标题-->
	       					<div class="spots-list-div1">
	       						热点课题排行
	       					</div>
	       					<div class="spots-list-div2">
	       						收藏次数
	       					</div>
	       				</div>
	       				<div class="spots-list-content">
		       				<table id ="hot_course" class="table  table-access-course">
							</table>      			
	       				</div><!-- 内容 -->
       				</div> <!-- 热点排行结束 -->
       				<div class="score-list p-v-3"><!-- 积分排行开始 -->
	       				<div class="score-list-div"><!-- 标题 -->
	       					<div class="spots-list-div1">学生积分排行</div>
	       					<div class="spots-list-div2">积分总数</div>
	       				</div>
       				<div class="score-list-content"> 
       					<table id = "score_course" class = "table table-access-course">
       					</table>  					
       				</div><!-- 内容 -->
       				</div><!-- 积分排行结束 -->
       			</div>
       		</div>

</BODY>
</HTML>