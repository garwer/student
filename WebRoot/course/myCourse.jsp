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
  <TITLE>课程管理</TITLE>
	<meta charset="UTF-8" />   
	<link rel="stylesheet" href="css/bootstrap/bootstrap-table.css">
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/add.css">
	<script type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap-table.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/myCourse.js" ></script> 
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
	function getAllMyCourse() {
		$("#access_table").bootstrapTable('destroy');
		var $table = $('#access_table');
	    $table.bootstrapTable({
	    	method: "get",  //使用get请求到服务器获取数据
	        url: "/student/getCourse.do?method=findAllMyCourse", //获取数据的Servlet地址
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
	              id: $('#teacherId').val()
	          };  
	          return param;                 
	        },
	        columns:[
	                 {
	                	 title:'导师',
	                	 field:'teacher',
	                	 align:'center',
	                	 width:100
	                 },
	                 {
	                	 title:'课题',
	                	 field:'name',
		                 align:'center',
						 width:300
	                 },
	                 {
	                	 title:'创建日期',
	                	 field:'date',
	                	 align:'center',
	           			 width:150
	                 },
	                 {
	                	 title:'课题内容',
	                	 field:'content',
	                	 align:'center',
	           			 width:400
	                 }
	               
	                 ]
		})
	}

	//教师添加课题
	function addCourse() {
		$('#courseName').val('');
		$('#courseContent').val('');
		$('#selTeacher').html($('#teacherName').val());
		$('#moadlTitle').html('添加课题');
		$("#addModal").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#addModal').modal('show');
	}
	
	

	</SCRIPT>
</HEAD>
<BODY>
<input type="hidden" id="teacherId" value= <%=id %> />
<input type="hidden" id="studentId" value= <%=id %> />

<input type="hidden" id="studentName" value= <%=name %> />
<input type="hidden" id="teacherName" value= <%=name %> />

<input type="hidden" id="userType" value= <%=type %> />
<div id = "menuDivId" style="display:block"></div>
         <div class="modal-body" >
             <div style="margin-top:20px">
		                <legend>
		                	 <h4 class="modal-title text-info" >我的课题管理</h4>
		                </legend>  
		                 <div id="addBtn" class="btn btn-primary" onclick="addCourse()"><span class="cursor">添加课题</span></div>		                
		         <table id ="access_table" class=" table" >	
					<thead>
					</thead>	
					<tbody>				
					</tbody>
				</table>  		
          	</div> 
		   </div>
		   
	<!-- 模态框(Modal) -->
		<div class="modal fade sourceModal" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:20%">
		    <div class="modal-dialog" style="width: 500px;">  
		        <div class="source-content">        	
		         <div class="modal-header p0">  
		            <div class = "source-title">
			    		<div class="f-l" id = "moadlTitle"></div>
			    		<div class="close-image" data-dismiss="modal" ></div>
		    		</div>
	
		            </div>
		            <div id = "" style="padding:20px">
		                   <table id="addSource-content-table" class="addSource-content-table" cellspacing="0" cellpadding="0">
		                   		<tr>
									<td width="20%">导师：</td>
									<td width="80%" style="text-align:left">
										<span id="selTeacher"></span>
									</td>						
								</tr>
								
								<tr>
									<td width="20%">课题名称：</td>
									<td width="80%" ><input type="text" id = "courseName" /></td>						
								</tr>				
								<tr>
									<td width="20%" style="vertical-align: text-top;">课题内容：</td>
									<td width="80%"><textarea rows="4"  width="90%" id = "courseContent"></textarea></td>						
								</tr>					
				                 </div>
			      		 </table>         
	            		<div id = "promptMsg">	
						</div>
		            </div>
		            <div class="modal-footer">
				      		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			                <button type="button" id = "addOrEditDataSource" class="btn btn-primary" onclick="sureAddCourse()" data-dismiss="modal">添加</button>        
		            </div>		            
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<!-- 信息模态框 -->
		<div class="modal fade" id="titleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:0%">
		    <div class="modal-dialog" style="width: 350px;height: 200px;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal">×</button>
		                <h4 class="modal-title text-center text-info" id="titleModalMsg">添加成功</h4>
		            </div>	  
		              <div class="modal-footer">
		                <button type="button" class="btn btn-primary" data-dismiss="modal" id="sureChoose">确定</button>
		            </div>	          
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
</BODY>
</HTML>