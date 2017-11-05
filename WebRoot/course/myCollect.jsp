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
	<script type="text/javascript" src="js/jquery/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap-table.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/myCollect.js" ></script> 
	<SCRIPT type="text/javascript" >

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
	function getMyCollect() {
		$("#access_table").bootstrapTable('destroy');
		var $table = $('#access_table');
	    $table.bootstrapTable({
	    	method: "get",  //使用get请求到服务器获取数据
	        url: "/student/getCourse.do?method=getMyCollect", //获取数据的Servlet地址
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
	        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
	        //设置为limit可以获取limit, offset, search, sort, order
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
	                	 title:'收藏人',
	                	 field:'user_name',
	                	 align:'center',
	                	 width:100
	                 },
	                 {
	                	 title:'收藏课题',
	                	 field:'name',
		                 align:'center',
						 width:300
	                 },
	                 {
	                	 title:'收藏标签',
	                	 field:'label',
	                	 align:'center',
	           			 width:300
	                 },
	                 {
	                	 title:'收藏时间',
	                	 field:'date',
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
	                       //  var s = '<a class = "save" >保存</a>';
	                         var d = '<a class = "delCollect" onclick = "delCollect('+row.course_id+')">取消收藏</a>';
	                        // return s+' '+d;
	                        return d;
	                     },
	                     events: 'operateEvents'

	                 }
	                 ]
		})
	}

	function delCollect(id) {
		course_id = id;
		$("#cancelModal").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#cancelModal').modal('show');
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
		                	 <h4 class="modal-title text-info m-b-30" >我的收藏</h4>
		                </legend>  
		         <table id ="access_table" class=" table" >	
					<thead>
					</thead>	
					<tbody>				
					</tbody>
				</table>  		
          	</div> 
		   </div>
		   
	<!-- 是否取消模态框(Modal) -->
	<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:20%">
	    <div class="modal-dialog" style="width: 300px;height: 200px;">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal">×</button>
	                <h4 class="modal-title text-center text-info">确定要取消收藏吗?</h4>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-success" data-dismiss="modal" onclick = "sureDelCollect()">确定</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>		            
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</BODY>
</HTML>