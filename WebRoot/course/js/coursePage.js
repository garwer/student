var a ='';
var course_id = "";
var treeId = getUrlParam('id');
var $studentId = '';

$(function() {
	//页面头部
	menuBarInit("menuDivId", 1);
	var graphURL = "../servlet/DisplayChart?filename=";
	//$('#aaa').html('<img src="'+graphURL+'D:\image\huang.png"/>');
	//showLabel();
	chooseCourse();
	sureChoose();
	showAllMyLabel();
	contractLabel();//收缩框
	tagSelect();
	deleteTips();
	getTipsId();
	getTipsIdAndTag(); 
	addLabelBtn();//按钮添加标签置上方
	sureCollect();//点击收藏
	comment();//点击评论
	$('.face-icon').SinaEmotion($('.text'));//加载评论表情
});

var zTree;
var demoIframe;
var setting = {
	view: {
		dblClickExpand: false,
		showLine: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},
	callback: {
		beforeClick: function(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if (treeNode.isParent) {
				zTree.expandNode(treeNode);
				return false;
			} else {
				demoIframe.attr("src",treeNode.file + ".html");
				return true;
			}
		},
		onClick: clickFuc
	}
};

/**
 * 获取URL参数
 * @param name
 * @returns
 */
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r != null) return unescape(r[2]); 
	return null; //返回参数值
}

//选择课题
function chooseCourse() {
	$('#chooseMyCourse').click(function() {
		$("#chooseCourseModal").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#chooseCourseModal').modal('show');		
	});
}

function sureChoose() {
	$('#sureChoose').click(function() {
		//先判断课题是否选择
		var url = "/student/getCourse.do?method=courseChoose&id=" +course_id;
		$.ajax({
			type:'post',
			url:url,
			dataType:'text',
			success:function(jsonStr) {
				if(jsonStr == '1') {
					$('#titleModalMsg').html('该课题已被选择,请选择其它课题');
					$("#titleModal").draggable({   
					    handle: ".modal-header",   
					    cursor: 'move',   
					    refreshPositions: false  
					});
					$('#titleModal').modal('show');	
				} else {
					//判断自己是否已选择课题
					var id = $('#studentId').val();
					var url = "/student/getCourse.do?method=isChoose&id="+ id;		
					$.ajax({
						type:'post',
						url:url,
						contentType:'text/html;charset=utf-8',
						dataType:'text',
						success:function(jsonStr) {
							//确定
							if(jsonStr == 'null') {
								var url = "/student/getCourse.do?method=sureChoose&id="+ course_id+"&userId="+id;		
								$.ajax({
									type:'post',
									url:url,
									contentType:'text/html;charset=utf-8',
									dataType:'text',
									success:function(jsonStr) {
										$('#titleModalMsg').html('选择课题成功!');
										$("#titleModal").draggable({   
										    handle: ".modal-header",   
										    cursor: 'move',   
										    refreshPositions: false  
										});
										$('#titleModal').modal('show');	
										getCourseInfo(course_id);
									}
								})
							} else {
								$('#titleModalMsg').html('你已选择过课题,请取消后再选择');
								$("#titleModal").draggable({   
								    handle: ".modal-header",   
								    cursor: 'move',   
								    refreshPositions: false  
								});
								$('#titleModal').modal('show');	
							}
						}
					})
				}
			}
		})
	});
}


//评论
function comment() {
	$('#getComment').click(function() {
		getComment();
		$("#commentModel").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#commentModel').modal('show');
	});
}


function out() {
	//插入评论
	var url = "/student/getCourse.do?method=saveComment";		
	var studentId = $('#studentId').val();
	var content = $('.text').val();
	var data = {
		courseId:course_id,
		userId:studentId,
		type:'course',
		integralType:'comment',
		commentContent:AnalyticEmotion(content)
	};
	$.ajax({
		type:'post',
		url:url,
		contentType:'application/json;charset=utf-8',
		data : JSON.stringify(data),
		dataType:'text',
		async:'false',
		success:function(jsonStr) {	//重新加载评论
			getComment();
			$('.text').val('');
		}	
	});
}

var html;
function reply(data) {
	html  = '<li>';
	html += '<div class="head-face">';
	html += '<img src="'+data.photo+'" / >';
	html += '</div>';
	html += '<div class="reply-cont">';
	html += '<p class="username">'+data.userName+'</p>';
	html += '<p class="comment-body">'+data.content+'</p>';
	html += '<p class="comment-footer">'+data.date+'';
	if(data.isSelf == 'yes') {
		html += '<span style="cursor:pointer;color:#99CC66;margin-left:15px" onclick="delComment('+data.commentId+')">删除</span></p>'
	}
	html += '</div>';
	html += '</li>';
	return html;
}

//删除评论
function delComment(id) {
	var url = "/student/getCourse.do?method=delComment";
	var data = {commentId:id};
	$.ajax({
		type:'post',
		url:url,
		dataType:'text',
		contentType:'application/json;charset=utf-8',
		data:JSON.stringify(data),
		success:function() {
			alert('删除成功');
			getComment();		
		}
	})
}

//加载评论
function getComment() {
	var url = "/student/getCourse.do?method=getComment&courseId="+course_id+"&type=course";		
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		success:function(jsonStr) {
			$('#info-show ul').empty();
			var length = jsonStr.length;
			for(i = 0; i < length; i++) {			
				var data = {
					id: jsonStr[i].rowId,	
					commentId: jsonStr[i].comment_id,
					userName: jsonStr[i].user_name,
					content:jsonStr[i].comment_content,
					date:jsonStr[i].date,
					course:jsonStr[i].name,
					photo:jsonStr[i].photo_adr,
					isSelf:jsonStr[i].isSelf
				};
				$('#info-show ul').append(reply(data));
			}	
		}
	})
}

function clickFuc(e, treeId, treeNode) {
	$('#noneCourseInfo').hide();
	$('#courseInfo').show();
	course_id = treeNode.id;
	getCourseInfo(course_id);
	$studentId = $('#studentId').val();
	isCollectOrNot(course_id,$studentId);
	sureCancel();
}

function defaultClick(id) {
	course_id = id;
	$('#noneCourseInfo').hide();
	$('#courseInfo').show();
	getCourseInfo(id);
	$studentId = $('#studentId').val();
	isCollectOrNot(id,$studentId);
	sureCancel();
}

function getCourseInfo(id) {
	var url = "/student/getCourse.do?method=findContentById&id="+id;		
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		async:'false',
		success:function(jsonStr) {	
			console.log(jsonStr);
			$('#courseName').html(jsonStr.name);
			$('#courseTeacher').html(jsonStr.teacher);
			$('#courseContent').html(jsonStr.content);
			if(jsonStr.isChoose == '0') {
				$('#chooseCourse').html('该课题暂未被选择');
			}else {
				$('#chooseCourse').html('课题已被选择');
			}
		}
	});
}

function showLabel() {
	var $isCollect = $('#isCollect');
	//说明为已收藏
	if($isCollect.text() == '取消收藏') {
		$("#cancelModal").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#cancelModal').modal('show');
		
	//如果为取消收藏 弹出是否取消模拟态框
	}else {
		$("#showLabel").draggable({   
		    handle: ".modal-header",   
		    cursor: 'move',   
		    refreshPositions: false  
		});
		$('#showLabel').modal('show');
	}
}

$(document).ready(function() {
	var t = $("#tree");
	var zNodes = "";
	var url = "/student/getCourse.do?method=getCourse";		
	$.ajax({
		type:'post',
		url:url,
		dataType:'json',
		async:'false',
		success:function(jsonStr) {		
			//alert(jsonStr);
			var zNodes = convert(jsonStr);
			//var zNodes = [{"id":1,"open":"true","name":"计算机专业","pid":0},{"id":11,"open":"false","name":"林宏基","pid":1},{"id":12,"open":"false","name":"陈建华","pid":1},{"id":13,"open":"false","name":"姚朝灼","pid":1},{"id":14,"open":"false","name":"曹俊","pid":1},{"id":15,"open":"false","name":"赵妍","pid":1},{"id":16,"open":"false","name":"陆林花","pid":1},{"id":17,"open":"false","name":"唐乐红","pid":1},{"id":18,"open":"false","name":"黄金土","pid":1},{"id":19,"open":"false","name":"黄勤","pid":1},{"id":111,"name":"聊天机器人语音系统设计","pid":11},{"id":112,"name":"聊天机器人服务器系统设计","pid":11},{"id":113,"name":"聊天机器人客户系统设计","pid":11},{"id":121,"name":"图像处理一些效果的设计与实现","pid":12},{"id":122,"name":"网站设计与开发","pid":12},{"id":131,"name":"基于andoid的平时成绩测定与出勤登记系统的手机实现","pid":13},{"id":132,"name":"基于andoid的酒店点菜手机系统实现","pid":13},{"id":141,"name":"基于物联网的智慧大棚","pid":14},{"id":142,"name":"手持智能导盲设备服务器端","pid":14},{"id":143,"name":"智能旅行箱客户端服务器端","pid":14},{"id":151,"name":"学生选课信息系统的设计与实现","pid":15},{"id":152,"name":"阳光学院工会网站的设计与开发","pid":15},{"id":153,"name":"基于B/S的酒店就餐管理系统的设计与应用","pid":15},{"id":154,"name":"校园二手物品交易网站的设计与实现","pid":15},{"id":161,"name":"于B/S的课堂管理系统的设计与实现","pid":16},{"id":162,"name":"基于.NET校园教学资源共享平台设计与实现","pid":16},{"id":163,"name":"基于.NET的科研学术管理系统的设计与实现","pid":16},{"id":171,"name":"基于安卓的学生小管家APP","pid":17},{"id":172,"name":"学生日常开支管理系统","pid":17},{"id":181,"name":"基于CTOC的网上拍卖管理系统","pid":18},{"id":182,"name":"在线考试系统的设计","pid":18},{"id":183,"name":"毕业设计选题管理系统","pid":18},{"id":191,"name":"基于php的办公用品售卖网","pid":19},{"id":192,"name":"基于php的校园闲置物品贩卖平台","pid":19}];
			t = $.fn.zTree.init(t, setting, zNodes);
			demoIframe = $("#testIframe");
			demoIframe.bind("load", loadReady);
			var zTree = $.fn.zTree.getZTreeObj("tree");
			if(treeId != null) {
				zTree.selectNode(zTree.getNodeByParam("id", treeId));//默认展开节点
				defaultClick(treeId);
			}
		}
	});

});

function loadReady() {
	var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
	htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
	maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
	h = demoIframe.height() >= maxH ? minH:maxH ;
	if (h < 530) h = 530;
	demoIframe.height(h);
}

//前后台的格式转换
function convert(dataList) {
$.each(dataList,function(i,d){
	this.id = this.id;
	this.name = this.name;
	this.pId = this.pid;
	this.open = this.open;
	this.isParent = this.isParent
});
return dataList;
}

//推荐标签
function contractLabel() {
	var str = ['展开推荐标签', '收起推荐标签']
	$('#selLabelTitle').click(function(){
		//alert('推荐');
		var $this = $(this),
			$con = $('#mycard-plus');
		if($this.hasClass('plus')){
			$this.removeClass('plus').text(str[0]);
			$con.hide();
		}else{
			$this.addClass('plus').text(str[1]);
			$con.show();
		}
	});
}

//选择框
function tagSelect() {
	$('#showMyLabel a').click(function() {	
		var $this = $(this),
		name = $this.attr('title');
		id = $this.attr('value');
		setTips(name, id);
	});
}

/*function searchAjax(name, id, isAdd) {
		setSelectTips();
};*/

// 更新高亮显示
function setSelectTips() {
	var arrName = getTips();
	if(arrName.length){
		$('#myTags').show();
	}else{
		$('#myTags').hide();
	}
	$('.default-tag a').removeClass('selected');
	$.each(arrName, function(index,name){
		$('.default-tag a').each(function(){
			var $this = $(this);
			if($this.attr('title') == name){
				$this.addClass('selected');
				return false;
			}
		})
	});
}

//选择增加标签
function addLabelBtn() {
	//var $b = $('.plus-tag-add button'),$i = $('.plus-tag-add input');
	var $b = $('#addLabel');
	var $i = $('#labelText');
/*	$i.keyup(function(e){
		if(e.keyCode == 13) {
			$b.click();
		}
	});*/
	//点击添加不能选择空标签
	$b.click(function(){
		if($.trim($i.val()) == '') {
			alert("请输入自定义标签");
			return;
		}
		var name = $i.val();
		if(name != '') setTips(name,-1);
		$i.val('');
		$i.select();
		a = $('#myTags');
	});
};



function setTips(c,d) {
	if(hasTips($.trim(c))) {
		alert('该标签已选择过');
		return false
	}
	var b=d?'value="'+d+'"':"";
	a = $(".plus-tag");
	c = $.trim(c);
	a.append($("<a "+b+' title="'+c+'" href="javascript:void(0);" ><span>'+c+"</span><em></em></a>"));
	//选择置灰
	setSelectTips();
	return true
};



//删除已经选择的标签项
function deleteTips() {
	 $a= $(".plus-tag");
	 $(document).on("click","#myTags a em",function() {
		 var $obj = $(this).parents('a')
		 var $title = $obj.attr('title');
		 var $value = $obj.attr('value');
		 delTips($title,$value);
		 setSelectTips();
	 })
}

function delTips(b,d){
	a = $('#myTags');
	if(!hasTips(b)) {
		return false;
	}
	$("a",a).each(function() {
		var d= $(this);
		if(d.attr("title")== b){
			d.remove();
			return false
		}
	});

	return true
};

//获取选取标签的title值
function getTips(){
	var b= [];
	$("a",a).each(function(){
		b.push($(this).attr("title"))
	});
	return b
};

//获取所有标签的值
function getTipsId() {
	var a=$("#myTags");
	var b=[];
	$("a",a).each(function(){
		b.push($(this).attr("title"))
	});
	return b
};

function getTipsIdAndTag(){
	var b=[];
	$("a",a).each(function(){
		b.push($(this).attr("value")+"##"+$(this).attr("title"))
	});
	return b
}

function hasTips(b) {
	var a = $('#myTags');
	var d= $("a",a);
	var c = false;
	d.each(function() {
		if($(this).attr("title")==b) {
			c= true;
			return false
		}
	});
	return c
};

function isCollectOrNot(courseId,studentId) {
	var $isCollect = $('#isCollect');
	var url = "/student/getCourse.do?method=isCollect&courseId="+courseId+"&userId="+studentId;		
	$.ajax({
		type:'post',
		url:url,
		dataType:'text',
		async:false,
		success:function(isCollect) {
			if(isCollect == 'true') {
				$isCollect.html('取消收藏')
			}else if(isCollect == 'false') {
				$isCollect.html('收藏课题')
			}
		}
	})
}

function sureCancel() {
	$('#sureCancel').unbind('click').click(function() {
		var url = "/student/getCourse.do?method=cancelCollect";
		var data = {courseId:course_id};
		$.ajax({
			type:'post',
			url:url,
			dataType:'text',
			contentType:'application/json;charset=utf-8',
			data:JSON.stringify(data),
			success:function() {
				$('#isCollect').html('收藏课题');
			}
		})
	})
}

//删除已经选择的标签项
function deleteTips() {
	 $a= $(".plus-tag");
	 //点x  #myTags a em
	 $(document).on("click","#myTags a em",function() {
		 var $obj = $(this).parents('a');
		 var $title = $obj.attr('title');
		 var $value = $obj.attr('value');
		 delTips($title,$value);
		 setSelectTips();
	 })
}

function delTips(b,d){
	a = $('#myTags');
	if(!hasTips(b)) {
		return false;
	}
	$("a",a).each(function() {
		var d= $(this);
		if(d.attr("title")== b){
			d.remove();
			return false
		}
	});

	return true
};

//获取选取标签的title值
function getTips(){
	var b= [];
	$("a",a).each(function(){
		b.push($(this).attr("title"))
	});
	return b
};

//获取所有标签的值
function getTipsId() {
	var a= $("#myTags");
	var b=[];
	$("a",a).each(function(){
		b.push($(this).attr("title"))
	});
	return b
};

function getTipsIdAndTag(){
	var b=[];
	$("a",a).each(function(){
		b.push($(this).attr("value")+"##"+$(this).attr("title"))
	});
	return b
}

function hasTips(b) {
	var a = $('#myTags');
	var d= $("a",a);
	var c = false;
	d.each(function() {
		if($(this).attr("title")==b) {
			c= true;
			return false
		}
	});
	return c
};


//判断该用户该标签是否存在如果不存在则添加 

//动态显示推荐标签
function showAllMyLabel() {
	var $myLabel = $("#showMyLabel"); 

	var url = "/student/getCourse.do?method=getMyLabel";
	var id = $('#studentId').val();
	var data = {
		id:id
	}
	$.ajax({
		type:'post',
		url:url,
		contentType:'application/json;charset=utf-8',
		dataType:'json',
		data:JSON.stringify(data),
		//同步处理,等数据加载完
		async: false,
		success:function(jsonStr) {
			var length = jsonStr.length;
			for(var i = 0; i < length; i++) {	
				$myLabel.append($("<a class='aaa' title='"+jsonStr[i].label_name+"'  value='"+ jsonStr[i].label_name +"' href='javascript:void(0);' ><span>"+ jsonStr[i].label_name + "</span><em></em></a>"));
			}		
		}
	})
}



//确定收藏 传入参数service_id
function sureCollect() {
	$('#sureCollect').click(function() {
		var labelArray = getTipsId();
		var titleArray = [];
		var labelName;
		var labelLength = labelArray.length;
		for(var i = 0; i < labelLength; i++) {
			labelName = labelArray[i];
			titleArray.push(labelName);
		}
		var labelObj = {
			studentId:$studentId,
			courseId:course_id,
			labelMsg:titleArray
		};
		console.log(labelObj);
		var url = "/student/getCourse.do?method=sureCollect";	
		$.ajax({
			url:url,
			contentType:'application/json;charset=utf-8',
			data: JSON.stringify(labelObj),
			type:'post',
			dataType:'text',
			async:'false',
			success:function() {
				$("#sureTitle").draggable({   
				    handle: ".modal-header",   
				    cursor: 'move',   
				    refreshPositions: false  
				});
				$('#sureTitle').modal('show');
				$('#isCollect').html('取消收藏');
			},
			error:function() {
				alert('异常');
			}
		})
	})
}