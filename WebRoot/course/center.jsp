<%@page language="java" pageEncoding="utf-8" %>
<%@ page import="course.entity.UserInfo"%>
<%
    String contextURL = request.getContextPath() + "/";
 	UserInfo info = (UserInfo)session.getAttribute("userInfo");
 	String id = info.getUser_id();
 	String name = info.getUser_name();
 	String type = info.getUser_type();
%>
<!DOCTYPE html>
<html lang="zh-CN">
 <HEAD>
  <TITLE>学生选题页面</TITLE>
	<meta charset="UTF-8" />   
	<link rel="stylesheet" href="css/bootstrap/bootstrap-table.css">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/center.css">
	<script type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap-table.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/echarts.common.min.js"></script> 
	<script type="text/javascript" src="js/center.js" ></script> 
</HEAD>
<BODY>
<input type="hidden" id="studentId" value= <%=id %> />
<input type="hidden" id="studentName" value= <%=name %> />
<input type="hidden" id="userType" value= <%=type %> />
<div id = "menuDivId" style="display:block"></div>
<div class="col-xs-12">
  <div class="script-left-div col-xs-3 ">						      		
     		<!-- bootstrap分区 -->
     		<div class="col-xs-12 p-t-15">
     			<div class="col-xs-3"> 
     				<div class="releaseNum imageBar1"></div>  
     			</div>	   				
     			<div class="textSpan col-xs-3">
   					<span id = "collectNum" class="releaseNumText3">0</span><br/>
   					<a class="textCss">收藏数</a>	      					
     			</div>
     			<div class="col-xs-3"> 
     				<div class="releaseNum imageBar5"></div>  
     			</div>	   				
     			<div  class="textSpan col-xs-3">
   					<span id = "scoreNum" class="releaseNumText5" >0</span><br/>
   					<a class="textCss">总积分</a>	      					
     			</div>
     		</div>		
     		
     
   </div>
   
   <div class="script-right-div col-xs-9">
   		<div id="haveChoose" style="display:none">
   		<div id="cancelBtn" class="btn btn-default"><span class="cursor">取消课题</span></div>
   		     <div class="modal-body" >
             <div style="margin-top:20px">
		                <legend>
		                	 <h4 class="modal-title text-info m-b-30 p-t-15" >我的课题:<span id="myCourse"></span></h4>
		                </legend>  
		                 <legend>
		                	 <h4 class="modal-title text-info m-b-30 p-t-15" >我的导师:<span id="myTeacher"></span></h4>
		                </legend>  
		                <legend>
		                	 <h4 class="modal-title text-info m-b-30 p-t-15" >课题内容:<span id="content"></span></h4>
		                </legend>  
		         <table id ="access_table" class=" table" >	
					<thead>
					</thead>	
					<tbody>				
					</tbody>
				</table>  		
          	</div> 
		   </div>
		   </div>
		  <div class="no-access-log-tip" id="noneCourseInfo">
            <div class="no-access-log-div pull-left"></div>
            <div class="tip-label-div pull-left ml-xl">
                <div class="access-log-text3 tip-label">您还未选择您的课题</div>
                <div class="text-color-blue tip-label2 mt-sm">
                    <span class = "cursor" onclick = "window.open('/student/course/coursePage.jsp')">点击选择课题</span>
                </div>
            </div>
        </div>
   </div>
</div>

	<!-- 是否取消模态框(Modal) -->
	<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:20%">
	    <div class="modal-dialog" style="width: 300px;height: 200px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal">×</button>
	                <h4 class="modal-title text-center text-info">确定要取消该课题吗?</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-success" data-dismiss="modal" onclick = "sureCancel()">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>		            
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</BODY>
</HTML>