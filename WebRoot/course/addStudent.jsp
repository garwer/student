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
  <TITLE>学生管理</TITLE>
	<meta charset="UTF-8" />   
	<link rel="stylesheet" href="css/bootstrap/bootstrap-table.css">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/add.css">	
	<link href="css/cropper/cropper.min.css" rel="stylesheet">
	<link href="css/sitelogo/sitelogo.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
	<script src="js/cropper/cropper.min.js"></script>
	<script src="js/sitelogo/sitelogo.js"></script>
	<script src="js/Calendar.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap-table.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/addStudent.js" ></script> 
	<SCRIPT type="text/javascript" >
	/**
	 * Bootstrap Table Chinese translation
	 * Author: Zhixin Wen<wenzhixin2010@gmail.com>
	 */
	(function ($) {
	    'use strict';
	    $.fn.bootstrapTable.locales['zh-CN'] = {
	        formatLoadingMessage: function () {
	            return '正在努力地加载数据中，请稍候……';
	        },
	        formatRecordsPerPage: function (pageNumber) {
	            return '每页显示 ' + pageNumber + ' 条记录';
	        },
	        formatShowingRows: function (pageFrom, pageTo, totalRows) {
	            return '显示第 ' + pageFrom + ' 到第 ' + pageTo + ' 条记录，总共 ' + totalRows + ' 条记录';
	        },
	        formatSearch: function () {
	            return '搜索';
	        },
	        formatNoMatches: function () {
	            return '没有找到匹配的记录';
	        },
	        formatPaginationSwitch: function () {
	            return '隐藏/显示分页';
	        },
	        formatRefresh: function () {
	            return '刷新';
	        },
	        formatToggle: function () {
	            return '切换';
	        },
	        formatColumns: function () {
	            return '列';
	        },
	        formatExport: function () {
	            return '导出数据';
	        },
	        formatClearFilters: function () {
	            return '清空过滤';
	        }
	    };

	    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

	})(jQuery);

	var course_id = '';
	function getAllStudent() {
		$("#access_table").bootstrapTable('destroy');
		var $table = $('#access_table');
	    $table.bootstrapTable({
	    	method: "get",  //使用get请求到服务器获取数据
	        url: "/student/getCourse.do?method=findAllStudent", //获取数据的Servlet地址
	        toggle:'table',
	        cache:false,
	        dataType: "json",
	        toolbar: '#toolbar', 
	        striped: true,  //表格显示条纹
	        pagination: true, //启动分页
	        paginationPreText:'上一页',
	        paginationNextText:'下一页',
	        pageSize: 10,  //每页显示的记录数
	        pageNumber:1, //当前第几页
	        pageList: [1, 10],  //记录数可选列表
	        //showHeader:true,
	        //showFooter:true,
	        search: false,  //是否启用查询
	        //showPaginationSwitch:true,//隐藏 显示分页
	        strictSearch: true,
	        showColumns: true,                  //是否显示所有的列
	        //showRefresh: true,                  //是否显示刷新按钮
	        minimumCountColumns: 2,             //最少允许的列数
	        clickToSelect: true,  
	        showToggle: true,
	        strictSearch: true,
	        showColumns: false,  //显示下拉框勾选要显示的列
	        showExport: true, 
	        exportDataType: "basic", 
	        sidePagination: "server", //表示服务端请求
	        queryParamsType : "undefined", 
	        searchAlign:'right',
	        queryParams: function queryParams(params) {   //设置查询参数
	          var param = {  
	              pageNumber: params.pageNumber,  
	              pageSize: params.pageSize,
	              id: $('#studentId').val()
	          };  
	          return param;                 
	        },
	        columns:[
	                 {
	                	 title:'学生',
	                	 field:'user_name',
	                	 align:'center',
	                	 width:100
	                 },
	                 {
	                	 title:'性别',
	                	 field:'user_sex',
		                 align:'center',
						 width:300
	                 },
	                 {
	                	 title:'生日',
	                	 field:'user_birth',
	                	 align:'center',
	           			 width:300
	                 },
	                 {
	                	 title:'专业',
	                	 field:'user_profession',
	                	 align:'center',
	                	 width:200
	                 },
	                 {
	                	 title:'选择课题',
	                	 field:'choose_course',
	                	 align:'center',
	                	 width:200
	                 },
	                 {
	                     field: 'operation',
	                     title: '操作',
	                     align: 'center',
	                     width:100,
	                     formatter:function(value,row,index) {
	                    	 console.log(row);
	                         var d = '<a class = "cursor" onclick = "showDetail('+row.user_id+')">查看详细</a>';
	                        return d;
	                     },
	                     events: 'operateEvents'
	                 }
	                 ]
		})
	}

	function addStudent() {
		$("#avatar-modal").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#avatar-modal').modal('show');
	}
	
	function sureDelCollect() {
		var url = "/student/getCourse.do?method=cancelCollect";
		var data = {courseId:course_id};
		$.ajax({
			type:'post',
			url:url,
			dataType:'text',
			contentType:'application/json;charset=utf-8',
			data:JSON.stringify(data),
			success:function() {
				getMyCollect();
			}
		})
	}
	</SCRIPT>
</HEAD>
<BODY>
<input type="hidden" id="studentId" value= <%=id %> />
<input type="hidden" id="studentName" value= <%=name %> />
<input type="hidden" id="userType" value= <%=type %> />
<div id = "menuDivId" style="display:block"></div>
         <div class="modal-body" >
             <div style="margin-top:20px">
		                <legend>
		                	 <h4 class="modal-title text-info m-b-30" >学生管理</h4>
		                </legend>  
		                <div id="addStudent" class="btn btn-primary" onclick="addStudent()"><span class="cursor" >添加学生</span></div>		                	                
		         <table id ="access_table" class=" table" >	
					<thead>
					</thead>	
					<tbody>				
					</tbody>
				</table>  		
          	</div> 
		   </div>
		   
		   
	<!-- 遮罩层 -->
	<div tbIndex="-1" class="sweet-overlay" id="sweet-overlay" style="opacity:1.04"></div>
	<!-- 个人信息模态框 -->
	<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog" style="width:478px">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal">×</button>
	                <h4 class="modal-title text-center text-info" id="flagTitle"></h4>
	            <div class="modal-footer">
	            </div>	
	              <table id="orderInfo-content-table" class="orderInfo-content-table" cellspacing="0" cellpadding="0">
					<tr>
						<td width="30%">姓名：</td>
						<td width="60%" id = "name"></td>						
					</tr>
					
					<tr>
						<td width="30%">性别：</td>
						<td width="40%" id = "sex"></td>	
						<td rowspan="3">
						<td width="30%" rowspan="3"><div id='myPhoto'></div></td>	
						<td rowspan="3" id="aaa">		
						
					</tr>
					<tr>
						<td>学号：</td>
						<td id = "sId"></td>						
					</tr>
					
					<tr>
						<td>生日：</td>
						<td id = "birthDay"></td>						
					</tr>
					
					<tr>
						<td>专业：</td>
						<td id = "pro"></td>						
					</tr>
					
					<tr>
						<td>所选课题：</td>
						<td id = "course"></td>						
					</tr>
	      		 </table>	
	        	</div>  
	            <div class="modal-footer">
	                <button type="button" class="btn btn-success" data-dismiss="modal">确定</button>
	            </div>		            
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->		   
		   
	<!-- 是否添加学生模态框(Modal) -->
 <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form class="avatar-form" name="avatar-form" action="/student/getCourse.do?method=insertStudent" enctype="multipart/form-data" method="post">
				<div class="modal-header">
					<button class="close" data-dismiss="modal" type="button">&times;</button>
					<h4 class="modal-title" id="avatar-modal-label">添加学生</h4>
				</div>
				<div class="modal-body" style="padding-top:0px">
					<div class="avatar-body">
						<div class="avatar-upload">
							<input class="avatar-src" name="avatar_src" type="hidden">
							<input class="avatar-data" id="avatar_data" name="avatar_data" type="hidden">
						
							</div>
							<div class="row avatar-btns">
							<div class="col-md-3">
								学生信息
							</div>
							<div class="col-md-6">
							<label for="avatarInput">头像上传(请上传小于5M的图片)</label>
							<input class="avatar-input" id="avatarInput" name="avatar_file" type="file" >
							</div>
							<div class="col-md-3">
								<div class="btn-group">
									<button class="btn" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees"><i class="fa fa-undo"></i> 向左旋转</button>
								</div>
								<div class="btn-group">
									<button class="btn" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees"><i class="fa fa-repeat"></i> 向右旋转</button>
								</div>
							</div>
							
						</div>
							
						<div class="row">
							<div class="col-md-3">
							<table id="addSource-content-table" class="addSource-content-table" cellspacing="0" cellpadding="0">
		                   		<tr>
									<td width="40%">学号：</td>
									<td width="60%" ><input type="text" id = "user_id" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"  placeholder="请输入学号"/></td>						
								</tr>	
								<tr>
									<td width="40%">密码：</td>
									<td width="60%" ><input type="text" id = "user_pwd"  onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"  placeholder="请输入密码"/></td>						
								</tr>	
		                   		<tr>
									<td width="40%">姓名：</td>
									<td width="60%" ><input type="text" id = "user_name" placeholder="请输入姓名"/></td>						
								</tr>	
		                   		<tr>
									<td width="40%">性别：</td>
									<td width="60%" style="text-align:left">
										<select id="user_sex"><option value="man">男</option><option value="women">女</option></select>
									</td>						
								</tr>
								
								<tr>
									<td width="40%">出生日期：</td>
									<td width="60%" > <input  type="text" id="user_date" size="10" maxlength="10" onClick="new Calendar().show(this);" readonly="readonly" /></td>						
								</tr>									
				                 </div>
			      		 </table> 
							</div>
							<div class="col-md-6">
								<div class="avatar-wrapper p0" ></div>
							</div>
							<div class="col-md-3">
								<div class="avatar-preview preview-lg"></div>
								<div class="avatar-preview preview-md"></div>
								<div class="avatar-preview preview-sm"></div>
								<%--<div class="avatar-preview preview-md"></div>
								<div class="avatar-preview preview-sm"></div>
							--%></div>
						</div>
						<div class="row avatar-btns">
							<div class="col-md-9">
							</div>
							<div class="col-md-3">
								<button class="btn btn-primary btn-block avatar-save" type="submit"><i class="fa fa-save"></i>确认添加</button>
							</div>
						</div>
					</div>
				</div>
  		</form>
  	</div>
  </div>
</div>

</BODY>
</HTML>