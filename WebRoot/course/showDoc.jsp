<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=11"> 
<script type="text/javascript" src="js/swfView/swfobject.js"></script>
<script type="text/javascript" src="js/swfView/jquery.js"></script>
<script type="text/javascript" src="js/swfView/flexpaper_flash.js"></script>
<script type="text/javascript" src="js/swfView/flexpaper_flash_debug.js"></script>
<style type="text/css" media="screen"> 
	html, body	{ height:100%; } 
	body { margin:0; padding:0; overflow:auto; }  
</style> 

<title>�ĵ�����Ԥ��</title>
</head>
<body onload="init()">
   <div style="text-align:center;width:100%; height:100%;">
    	<div id="viewerPlaceHolder" style="width:auto; height:100%;" ></div>	        	       
   </div>
    <script type="text/javascript">
    	var swfPath="";
		var fileType="";
		
    	function init(){    		  
            var hostport="http://"+document.location.host;
    		//var arrayUrl=getURLSearch();
   		    //fileType= decodeURIComponent(arrayUrl.fileType);	 
			swfPath = "student/servlet/DisplayChart?filename=D:/up/swfFile/1487755963893_1043966930.swf";
		    //swfPath= decodeURIComponent(arrayUrl.swfPath);
		    swfPath=hostport+"/"+swfPath;
		    alert(swfPath);
		    initView();
    	}
    	//��ʼ��swfչʾ
    	function initView(){
    		var fp = new FlexPaperViewer(	
   				 'js/swfView/FlexPaperViewer',
   				 'viewerPlaceHolder', { config : {
   				 SwfFile : escape(swfPath),//��Ҫʹ��Flexpaper�򿪵��ĵ�
   				 Scale : 1.5, 						//��ʼ�����ű���������ֵӦ���Ǵ����������
   				 ZoomTransition : 'easeOut',		//Flexpaper��������ʽ����ʹ�ú�Tweenerһ������ʽ��Ĭ�ϲ���ֵΪeaseOut.������ѡֵ����: easenone, easeout, linear, easeoutquad
   				 ZoomTime : 0.5,					//��һ�����ű�����Ϊ����һ�����ű�����Ҫ���ѵ�ʱ�䣬�ò���ֵӦ��Ϊ0�����
   				 ZoomInterval : 0.2,				//���ű���֮������Ĭ��ֵΪ0.1����ֵΪ������
   				 FitPageOnLoad : false,				//��ʼ����ʱ������Ӧҳ�棬��ʹ�ù������ϵ���Ӧҳ�水ťͬ����Ч��
   				 FitWidthOnLoad : false,			//��ʼ����ʱ������Ӧҳ���ȣ��빤�����ϵ���Ӧ��Ȱ�ťͬ����Ч��
   				 FullScreenAsMaxWindow : false,		//������Ϊtrue��ʱ�򣬵���ȫ����ť���һ��flexpaper��󻯵��´��ڶ�����ȫ����������flash��������Ϊ��ȫ����ֹȫ������ʹ��flexpaper��Ϊ������flash��������ʱ������Ϊtrue�Ǹ�����ѡ��
   				 ProgressiveLoading : false,		//������Ϊtrue��ʱ��չʾ�ĵ�ʱ��������������ĵ��������𲽼��أ�������Ҫ���ĵ�ת��Ϊ9���ϵ�flash�汾��ʹ��pdf2swf��ʱ��ʹ��-T 9 ��ǩ����
   				 MinZoomSize : 0.2,					//�����������ű���
   				 MaxZoomSize : 5,					//��С�����ű���
   				 SearchMatchAll : true,				//����Ϊtrue��ʱ�򣬵����������з��������ĵط�������ʾ
   				 InitViewMode : 'Portrait',			//��������ģʽ��"Portrait" or "TwoPage" or "SinglePage"
   				 
   				 ViewModeToolsVisible : true,		//���������Ƿ���ʾ��ʽѡ���
   				 ZoomToolsVisible : true,			//���������Ƿ���ʾ���Ź���
   				 NavToolsVisible : true,			//���������Ƿ���ʾ��������
   				 CursorToolsVisible : true,			//���������Ƿ���ʾ��깤��
   				 SearchToolsVisible : true,			//���������Ƿ���ʾ����			
   				 localeChain: 'zh_CN'
   				 }});
    	}    	
    
	 </script>            
</body>
</html>