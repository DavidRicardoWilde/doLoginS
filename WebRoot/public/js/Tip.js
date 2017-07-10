(function() {
  function MoveDiv()
  {
      this.move = function(dom, json) {
          distance = Math.sqrt(Math.pow(json.x2-json.x1 ,2)+Math.pow(json.y2-json.y1,2)),                
			    s = (json.y2-json.y1)/distance, 
			    c = (json.x2-json.x1)/distance,
			    tdelay = 0;
          var move = function(){
         		tdelay += 3;
	          if(tdelay > distance) 
	          {
	            clearInterval(st);
	          }
	          dom.style.left = json.x1 + Math.round(tdelay*c)+'px';
	          dom.style.top  = json.y1 + Math.round(tdelay*s)+'px';
          }
          st = setInterval(move,2);
      }
  }

  var Drag = function(callback, activeDom, dragDom, moveSign) {
      this.fun = callback;
      this.moveSign = moveSign;
      this.x1=0;
      this.y1=0;
      this.x2=0;
      this.y2=0;
      this.mousedownHandle = this.getMousedownHandle();
			if(this.moveSign){
      	this.mousemoveHandle = this.getMousemoveHandle();
      }
      this.mouseupHandle = this.getMouseupHandle();
      this.bind(activeDom, dragDom);
  }
  Drag.prototype = {
      bind: function(activeDom, dragDom) {
          if (!activeDom) return;
          dragDom = dragDom || activeDom;
          activeDom.style.cursor = 'move';
          this.activeDom = activeDom;
          this.dragDom = dragDom;
          return this;
      },
      start: function() {
          this.addEventListen(this.activeDom, 'mousedown', this.mousedownHandle);
      },
      getMousedownHandle: function() {
          _this = this;
          return function(e) {
          	 e = e || window.event;
          	 var srcObj = e.srcElement || e.target;
             if (srcObj.tagName.toLowerCase() != "img"){
	              _this.dx = e.clientX - _this.dragDom.offsetLeft;
	              _this.dy = e.clientY - _this.dragDom.offsetTop;
	              _this.x1 = _this.dragDom.offsetLeft;
	              _this.y1 = _this.dragDom.offsetTop;
	              _this.addEventListen(document, 'mousemove', _this.mousemoveHandle);
	              _this.addEventListen(document, 'mouseup', _this.mouseupHandle);
	              _this.agency = _this.dragDom.cloneNode(false);
	              _this.agency.style.background = 'none';
	              _this.agency.style.border = '1px dashed #ccc';
	              _this.agency.style.left = e.clientX - _this.dx + 'px';
	              _this.agency.style.top = e.clientY - _this.dy + 'px';
	              _this.agency.style.zIndex = "999999";
	              document.body.appendChild(_this.agency);
	              _this.preventDefault(e);
            }else{
                  document.getElementById("Tip_bg").parentNode.removeChild(document.getElementById("Tip_bg"));
                  document.getElementById("Tip").parentNode.removeChild(document.getElementById("Tip"));
                  if (_this.fun != undefined && _this.fun != ''){
                      eval(_this.fun)();
                  }
              }
          }
      },
      getMousemoveHandle: function() {
          _this = this;
          return function(e) {
              e = e || window.event;
              _this.setPosition(e.clientX - _this.dx, e.clientY - _this.dy);
              _this.preventDefault(e);
          }
      },
      getMouseupHandle: function() {
          _this = this;
          return function(e) {
              e = e || window.event;
              _this.x2 = _this.agency.offsetLeft;
              _this.y2 = _this.agency.offsetTop;
	              var movediv = new MoveDiv();
	              movediv.move(_this.dragDom, {
	                  x1: _this.x1,
	                  y1: _this.y1,
	                  x2: _this.x2,
	                  y2: _this.y2
	              });            	
              _this.removeEventListen(document, 'mousemove', _this.mousemoveHandle);
              _this.removeEventListen(document, 'mouseup', _this.mouseupHandle);
              document.body.removeChild(_this.agency);
          }
      },
      setPosition: function(x, y) {
          _this.agency.style.left = x + 'px';
          _this.agency.style.top = y + 'px';
      },
      addEventListen: function(dom, evtType, callback) {
          if (window.addEventListener) {
              dom.addEventListener(evtType, callback, false);
          } else {
              dom.attachEvent('on'.concat(evtType), callback);
          }
      },
      removeEventListen: function(dom, evtType, callback) {
          if (window.removeEventListener) {
              dom.removeEventListener(evtType, callback, false);
          } else {
              dom.detachEvent('on'.concat(evtType), callback);
          }
      },
      preventDefault: function(e) {
          if (e.stopPropagation) {
              e.stopPropagation();
              e.preventDefault();
          } else {
              e.cancelBubble = true;
              e.returnValue = false;
          }
      }
  }
   window.Drag = Drag;
})();
function Tip(json)
{
  var bg = document.createElement("div");
  bg.id = "Tip_bg";
  var w = document.body.clientWidth + "px";
  var h = (document.body.scrollHeight > document.documentElement.scrollHeight ? document.body.scrollHeight: document.documentElement.scrollHeight) + "px";
  bg.style.cssText = "width:" + w + "; height:" + h + "; background:#333333;filter:alpha(opacity=50);-moz-opacity:0.5;opacity:0.5;position:absolute;left:0;top:0;z-index:998";
  document.body.appendChild(bg);
  var tip = document.createElement("div");
  tip.id = "Tip";
  var w1 = (window.screen.availWidth - 300) / 2 + "px";
  var h1 = (window.screen.availHeight - 180) / 2 + "px";
  tip.style.cssText = "width:300px; height:180px; background-color:#ffffff;overflow:hidden;position:fixed;z-index:999;left:" + w1 + ";top:" + h1;
  switch (json.pos)
  {
    case 1:
      var backpos=0;
      break;
    case 2:
      var backpos=-47;  
      break;
    case 3:
      var backpos=-94; 
      break;
    case 4:
      var backpos=-141;
      break;
  }
  tip.innerHTML = '<div  align="left" id="TipTop" style="height:20px; background-color:#f7f7fa;"><span style="float:left;margin-left:4px;margin-top:2px;display:inline-block;font-family:Tahoma;font-size:12px;color:#666;"><b>友情提示</b></span><img id="Tip_im" style="float:right;margin-top:4px;margin-right:4px;cursor:pointer" src="./images/close.jpg"></div>' + 
      '<div style="padding-left:20px;padding-top:40px;"><div style=\'float:left;width:47px;height:46px;background:url("./images/pic.jpg") '+backpos+'px 0px no-repeat \'></div><div style="margin-top:10px;height:46px;font-weight:bold;font-size:16px;float:left;margin-left:2px;">' + json.str + '</div></div>';
  document.body.appendChild(tip);
  if(!document.getElementById("Tip")){
    alert(json.str);
  }
  var drag = new Drag(json.callback,document.getElementById('TipTop'), document.getElementById('Tip'),json.move);
  drag.start();
}