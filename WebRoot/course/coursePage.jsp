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
 	String photo = info.getPhoto_adr();
%>
<HTML>
 <HEAD>
  <TITLE>学生选题页面</TITLE>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
  <link rel="stylesheet" href="css/zTreeStyle.css" type="text/css">
  <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
   <link rel="stylesheet" href="css/jq22.css">
   <link rel="stylesheet" href="css/comment/main.css">
   <link rel="stylesheet" href="css/comment/sinaFaceAndEffec.css"/>
  
  <style>
  /*
	body {
	background-color: white;
	margin:0; padding:0;
	text-align: center;
	}
	*/
  </style>
<link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">  
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/coursePage.css">
<script type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.ztree.core.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/comment/main.js"></script>
<script type="text/javascript" src="js/comment/sinaFaceAndEffec.js"></script>

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/coursePage.js"></script> 

</HEAD>
<BODY>
<input type="hidden" id="studentId" value= <%=id %> />
<input type="hidden" id="studentName" value= <%=name %> />
<input type="hidden" id="userType" value= <%=type %> />
<input type="hidden" id="photoAdr" value= <%=photo %> />


<div id = "menuDivId" style="display:block"></div>
	<!-- 收藏模态框 -->
		<div class="modal fade" id="showLabel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width: 800px;height:100px">
		        <div class="modal-content">
		        <div>
		      	</div>
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal">×</button>
		                <h4 class="modal-title text-center text-info">选择标签</h4>
		            </div>	          
		            	<div class="demo" class="col-md-12 ">	
						<div class="plus-tag tagbtn clearfix" id="myTags"></div>
				<div class="plus-tag-add">
					<form id="" action="" class="login">
					<ul class="Form FancyForm">
						<li>
							<input id="labelText" name="" type="text" class="stext" maxlength="20" placeholder="自定义标签输入框"/>
						</li>
						<li>
							<button type="button" id="addLabel" class="Button RedButton Button18" style="padding:.45em .825em .45em;">添加</button>
							<a id="selLabelTitle">展开推荐标签</a>
						</li>
					</ul>
					</form>
				</div><!--plus-tag-add end-->
				<div id="mycard-plus">
					<div class="default-tag tagbtn">
						<div id="showMyLabel" class="clearfix">
						<%--
							<a value="-1" title="JAVA" href="javascript:void(0);"><span>JAVA</span><em></em></a>
						--%>
						</div>		
					</div><!--mycard-plus end-->	
				</div>
				</div>        
	         	<div class="modal-footer">
		                <button type="button" class="btn btn-primary" data-dismiss="modal" id="sureCollect">确定收藏</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		         </div>	            
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->

		<!-- 是否取消模态框(Modal) -->
		<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width: 300px;height: 200px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal">×</button>
		                <h4 class="modal-title text-center text-info">确定要取消收藏吗?</h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-success" data-dismiss="modal" id="sureCancel">确定</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div>		            
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		
		<!-- 是否删除评论模态框(Modal) -->
		<div class="modal fade" id="delCommentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width: 300px;height: 200px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal">×</button>
		                <h4 class="modal-title text-center text-info">确定要删除改评论吗?</h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-success" data-dismiss="modal" id="suredelCommentModal">确定</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div>		            
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		
		
		<!-- 是否选择课题模态框 -->
		<div class="modal fade" id="chooseCourseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:20%">
		    <div class="modal-dialog" style="width: 300px;height: 200px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal">×</button>
		                <h4 class="modal-title text-center text-info">确定要选择该课题?</h4>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-success" data-dismiss="modal" id="sureChoose">确定</button>
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div>		            
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<!-- 信息模态框 -->
		<div class="modal fade" id="titleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:20%">
		    <div class="modal-dialog" style="width: 350px;height: 200px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal">×</button>
		                <h4 class="modal-title text-center text-info" id="titleModalMsg"></h4>
		            </div>	  
		              <div class="modal-footer">
		                <button type="button" class="btn btn-success" data-dismiss="modal" id="sureChoose">确定</button>
		            </div>	          
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<!-- 评论模态框 -->
		<div class="modal fade" id="commentModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	 	     <div class="modal-dialog" style="width: 800px;height: 500px;">
	 	     
		        <div class="modal-content" style="padding-top:1%">
		        <button type="button" class="close" data-dismiss="modal" style="margin-top:5px;margin-right:5px">×</button>
		        
		 		<div id="content" style="width: 750px; height: 500px;padding-top:3%;padding-left:2%">
		 		
			<div class="wrap">
				<div class="comment">
					<div class="head-face">
						<img src=<%=photo %> >
						<p><%=name %></p>
					</div>
					<div class="content">
						<div class="cont-box">
							<textarea class="text text-p" id = 'commentContent' placeholder="请输入您要评论的内容..."></textarea>
						</div>
						<div class="tools-box">
							<div class="operator-box-btn"><span class="face-icon"  >☺</span></div>
							<div class="submit-btn"><input type="button" onClick="out()"value="提交评论" /></div>
						</div>
					</div>
				</div>
				<div id="info-show" style="overflow:auto;height:300px">
					<ul></ul>
				</div>
				<div id ="faceDiv"></div>		
			</div>
		</div>           
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	
	<div class = "col-xs-12">
	<div class = "col-xs-3" style="width">
	<TABLE border=0>
		<TR>
			<TD width="280px"  valign=baseline style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:280px;height:650px;overflow:auto; "></ul>
			</TD>	
		</TR>
	</TABLE>
	</div>

	<div class = "col-xs-9 " style="padding-left:3%">
		 <div class="no-access-log-tip" id="noneCourseInfo">
            <div class="no-access-log-div pull-left"></div>
            <div class="tip-label-div pull-left ml-xl">
                <div class="access-log-text3 tip-label">您还未查看课题</div>
                <div class="text-color-blue tip-label2 mt-sm">
                    <span class = "cursor" onclick = "window.open('/student/course/course.docx')">点击下载全部课题文档</span>
                </div>
            </div>
        </div>
		
		</div>
		
		<div id = "courseInfo"  style="display:none">
		<div class = "course-info course-color" >
			课题名称:<span id="courseName" class="titleInfo" ></span>
		</div>
		<div class = "course-info course-color" >
			导师:<span id="courseTeacher" class="titleInfo"></span>
		</div>
		<div class = "course-info course-color" >
			课题内容:<span id="courseContent" class="titleInfo"></span>
		</div>
		<div class = "course-info course-color" >
			课题情况:<span id="chooseCourse" class="titleInfo"></span>
		</div>
		<div style="text-align:center;margin-top:10%">
			 <button type="button" class="btn btn-success" id = "chooseMyCourse">选择课题</button>
			 <button type="button" class="btn btn-success"  style="margin-left:3%" id ="isCollect" onclick="showLabel();">收藏课题</button>
			 <button type="button" class="btn btn-success"  style="margin-left:3%" id = "getComment">我要评论</button>
		</div>
		</div>	
	</div>

</div>
<script>
//绑定表情
// 测试本地解析



</script>
</BODY>
</HTML>