$(function() {
	var id = $('#teacher').val();
	//页面头部
	menuBarInit("menuDivId", 1);
	//加载信息
	getAllMyStudent(id);
});
