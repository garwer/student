/**
 * @author 夏の寒风
 * @time 2012-12-14
 */

//自定义hashtable
function Hashtable() {
    this._hash = new Object();
    this.put = function(key, value) {
        if (typeof (key) != "undefined") {
            if (this.containsKey(key) == false) {
                this._hash[key] = typeof (value) == "undefined" ? null : value;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    this.remove = function(key) { delete this._hash[key]; }
    this.size = function() { var i = 0; for (var k in this._hash) { i++; } return i; }
    this.get = function(key) { return this._hash[key]; }
    this.containsKey = function(key) { return typeof (this._hash[key]) != "undefined"; }
    this.clear = function() { for (var k in this._hash) { delete this._hash[k]; } }
}


var emotions = new Array();
var categorys = new Array();// 分组
var uSinaEmotionsHt = new Hashtable();

// 初始化缓存，页面仅仅加载一次就可以了
$(function() {
			//	console.log(data.slice(0,5));
			//	console.log(JSON.stringify(data.slice(0,5)));
			//	console.log(bb); /face/huang.png
		//	var arr = '[{"phrase":"[坏笑]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_thumb.png","value":"[坏笑]","picid":""},{"phrase":"[舔屏]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_thumb.png","value":"[舔屏]","picid":""},{"phrase":"[污]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_thumb.png","value":"[污]","picid":""},{"phrase":"[微笑]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/huanglianwx_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/huanglianwx_thumb.gif","value":"[微笑]","picid":""},{"phrase":"[嘻嘻]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0b/tootha_org.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0b/tootha_thumb.gif","value":"[嘻嘻]","picid":""},{"phrase":"[哈哈]","type":"face","url":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/laugh.gif","hot":false,"common":true,"category":"","icon":"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/6a/laugh.gif","value":"[哈哈]","picid":""}]';	
			var data=[
				{
					"phrase": "[坏笑]",
					"type": "face",
					"url": "/face/huaixiao.png",
					"hot": false,
					"common": true,
					"category": "",
					"icon": "/face/huaixiao.png",
					"value": "[坏笑]",
					"picid": ""
				},
				{
					"phrase": "[舔屏]",
					"type": "face",
					"url": "/face/tianping.png",
					"hot": false,
					"common": true,
					"category": "",
					"icon": "/face/tianping.png",
					"value": "[舔屏]",
					"picid": ""
				},
				{
					"phrase": "[污]",
					"type": "face",
					"url": "/face/wulian.png",
					"hot": false,
					"common": true,
					"category": "",
					"icon": "/face/wulian.png",
					"value": "[污]",
					"picid": ""
				},
				{
					"phrase": "[微笑]",
					"type": "face",
					"url": "/face/weixiao.gif",
					"hot": false,
					"common": true,
					"category": "",
					"icon": "/face/weixiao.gif",
					"value": "[微笑]",
					"picid": ""
				},
				{
					"phrase": "[嘻嘻]",
					"type": "face",
					"url": "/face/xixi.gif",
					"hot": false,
					"common": true,
					"category": "",
					"icon": "/face/xixi.gif",
					"value": "[嘻嘻]",
					"picid": ""
				},
				{
					"phrase": "[哈哈]",
					"type": "face",
					"url": "/face/laugh.gif",
					"hot": false,
					"common": true,
					"category": "",
					"icon": "/face/laugh.gif",
					"value": "[哈哈]",
					"picid": ""
				},
					{
					"phrase": "[爱心]",
					"type": "face",
					"url": "/face/love.gif",
					"hot": false,
					"common": true,
					"category": "",
					"icon": "/face/love.gif",
					"value": "[爱心]",
					"picid": ""
				}
			];		
			for (var i in data) {		
				if (data[i].category == '') {
						data[i].category = '默认';
					}
					if (emotions[data[i].category] == undefined) {
						emotions[data[i].category] = new Array();
						categorys.push(data[i].category);
					}
					emotions[data[i].category].push( {
						name : data[i].phrase,
						icon : data[i].icon
					});
					uSinaEmotionsHt.put(data[i].phrase, data[i].icon);
				}
			});

//替换
function AnalyticEmotion(s) {
	if(typeof (s) != "undefined") {
		var sArr = s.match(/\[.*?\]/g);
		if(null!=sArr && '' != sArr){
			for(var i = 0; i < sArr.length; i++){
				if(uSinaEmotionsHt.containsKey(sArr[i])) {
					var reStr = "<img src=\"" + uSinaEmotionsHt.get(sArr[i]) + "\" height=\"22\" width=\"22\" />";
					s = s.replace(sArr[i], reStr);
				}
			}
		}
		
	}
	return s;
}

function GetAbsoluteLocationEx(element) {
	if ( arguments.length != 1 || element == null ) {
		return null;
	}
	var elmt = element;
	var offsetTop = elmt.offsetTop;
	var offsetLeft = elmt.offsetLeft;
	var offsetWidth = elmt.offsetWidth;
	var offsetHeight = elmt.offsetHeight;
	while( elmt = elmt.offsetParent ) {
		if ( elmt.style.position == 'absolute' || elmt.style.position == 'relative' || ( elmt.style.overflow != 'visible' && elmt.style.overflow != '' ) ) {
			break;
		}
		offsetTop += elmt.offsetTop;
		offsetLeft += elmt.offsetLeft;
	}    
	return { absoluteTop: offsetTop, absoluteLeft: offsetLeft, offsetWidth: offsetWidth, offsetHeight: offsetHeight};
}

(function($){
	$.fn.SinaEmotion = function(target){
		console.log(target);
		var cat_current;
		var cat_page;
		$(this).click(function(event){
			event.stopPropagation();
			var eTop = target.offset().top + target.height();
			var eLeft = target.offset().left;
			if($('#emotions .categorys')[0]){
				$('#emotions').css({top: eTop, left: '50%'});
				$('#emotions').toggle();
				return;
			}
			$('#faceDiv').append('<div id="emotions"></div>');
			$('#emotions').css({top: eTop, left: '10%'});
			$('#emotions').html('<div>正在加载，请稍候...</div>');
			$('#emotions').click(function(event){
				event.stopPropagation();
			});
			$('#emotions').html('<div style="float:right"></div><div class="categorys"></div><div class="container"></div>');
			$('#emotions #prev').click(function(){
				showCategorys(cat_page - 1);
			});
			$('#emotions #next').click(function(){
				showCategorys(cat_page + 1);
			});

			showCategorys();
			showEmotions();
			
		});
		$('body').click(function(){
			$('#emotions').remove();
		});
		$.fn.insertText = function(text){
			this.each(function() {
				if(this.tagName !== 'INPUT' && this.tagName !== 'TEXTAREA') {return;}
				if (document.selection) {
					this.focus();
					var cr = document.selection.createRange();
					cr.text = text;
					cr.collapse();
					cr.select();
				}else if (this.selectionStart || this.selectionStart == '0') {
					var 
					start = this.selectionStart,
					end = this.selectionEnd;
					this.value = this.value.substring(0, start)+ text+ this.value.substring(end, this.value.length);
					this.selectionStart = this.selectionEnd = start+text.length;
				}else {
					this.value += text;
				}
			});        
			return this;
		}
		function showCategorys(){
			var page = arguments[0]?arguments[0]:0;
			if(page < 0 || page >= categorys.length / 5){
				return;
			}
			$('#emotions .categorys').html('');
			cat_page = page;
			for(var i = page * 5; i < (page + 1) * 5 && i < categorys.length; ++i){
				$('#emotions .categorys').append($('<a>' + categorys[i] + '</a>'));
			}
			$('#emotions .categorys a').click(function(){
				showEmotions($(this).text());
			});
			$('#emotions .categorys a').each(function(){
				if($(this).text() == cat_current){
					$(this).addClass('current');
				}
			});
		}
		function showEmotions(){
			var category = arguments[0]?arguments[0]:'默认';
			var page = arguments[1]?arguments[1] - 1:0;
			$('#emotions .container').html('');
			cat_current = category;
			for(var i = 0;  i < emotions[category].length; ++i){
				$('#emotions .container').append($('<a title="' + emotions[category][i].name + '"><img src="' + emotions[category][i].icon + '" alt="' + emotions[category][i].name + '" width="22" height="22" /></a>'));
			}
			$('#emotions .container a').click(function(){
				target.insertText($(this).attr('title'));
				$('#emotions').remove();
			});
			 
			$('#emotions .categorys a.current').removeClass('current');
			$('#emotions .categorys a').each(function(){
				if($(this).text() == category){
					$(this).addClass('current');
				}
			});
		}
	}
})(jQuery);
