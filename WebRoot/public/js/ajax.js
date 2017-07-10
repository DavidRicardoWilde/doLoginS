function AjaxDO(){

    this.HttpRequest = null;

    this.openMethod = null; //HTTP请求的方法，为Get、Post 或者Head

    this.openURL = null; //是目标URL。基于安全考虑，这个URL 只能是同网域的，否则会提示“没有权限”的错误。

    this.openAsync = null; //是指定在等待服务器返回信息的时间内是否继续执行下面的代码。如果为False，则不会继续执行，直到服务器返回信息。默认为True。

    this.ProcessRequestFunction = function(_HttpRequest) {return;} //处理返回信息的函数入口

    this.ProcessRequestParam = null; //处理访问信息时的附加参数

    this.LoadingImg = null; //正在载入的图片，一般为.gif动画

    //初始化HttpRequest
    this.InitHttpRequest = function(){
        var http;

        // try {
        // http = new ActiveXObject("Msxml2.XMLHTTP");
        // } catch(e) {
        // try {
        // http = new ActiveXObject("Microsoft.XMLHTTP");
        // } catch(e) {
        // http = false;
        // }
        // }

        try {
            if(window.ActiveXObject){
                for(var i=5; i; i--){
                    try{
                        if(i==2){
                            http = new ActiveXObject("Microsoft.XMLHTTP");
                        }else{
                            http = new ActiveXObject( "Msxml2.XMLHTTP." + i + ".0" );
                        }
                        break;
                    }catch(e){
                        //alert(i);
                        http = false;
                    }
                }
            }else if(window.XMLHttpRequest){
                http = new XMLHttpRequest();
                if(http.overrideMimeType){
                    http.overrideMimeType("text/xml");
                }
            }
        }catch(e){
            http = false;
        }

        if(!http){
            Alert("不能创建XMLHttpRequest对象实例");
            return http;
        }

        this.HttpRequest = http;
        return http;
    }

    //检测 this.HttpRequest
    this.checkHttpRequest = function(){
        if(!this.HttpRequest){
            return this.InitHttpRequest();
        }
        return this.HttpRequest;
    }

    //修改MIME类别
    //http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); //如果要传文件或者Post 内容给服务器
    //http.setRequestHeader("Content-Type","text/xml");
    //http.setRequestHeader("Content-Type","gb2312");
    this.setRequestHeader = function(mime){
        if(!this.checkHttpRequest()){
            return false;
        }
        try{
            this.HttpRequest.setRequestHeader("Content-Type", mime);
            return true;
        }catch(e){
            if(e instanceof Error){
                Alert("修改MIME类别错误");
                return false;
            }
        }
    }

    //设置状态改变的事件触发器
    this.setOnReadyStateChange = function(funHandle, Param){
        if(!this.checkHttpRequest()){
            return false;
        }
        this.ProcessRequestFunction = funHandle;
        this.ProcessRequestParam = Param;
        return true;
    }

    this.setLoadingImg = function(ImgID){
        this.LoadingImg = ImgID;
    }

    //建立HTTP连接
    //open("method","URL"[,asyncFlag[,"userName"[, "password"]]])
    this.Open = function(method, url, async, username, psw){
        if(!this.checkHttpRequest()){
            return false;
        }
        this.openMethod = method;
        this.openURL = url;
        this.openAsync = async;
        if((this.openMethod==null) || ((this.openMethod.toUpperCase()!="GET")&&(this.openMethod.toUpperCase()!="POST")&&(this.openMethod.toUpperCase()!="HEAD"))){
            Alert("请指定HTTP请求的方法，为Get、Post 或者Head");
            return false;
        }
        if((this.openURL==null)||(this.openURL=="")){
            Alert("请指定目标URL");
            return false;
        }
        try{
            this.HttpRequest.open(this.openMethod, this.openURL, this.openAsync, username, psw);
        }catch(e){
            if(e instanceof Error){
                Alert("无法建立HTTP连接");
                return false;
            }
        }

        if(this.openMethod.toUpperCase()=="POST"){
            if(!this.setRequestHeader("application/x-www-form-urlencoded")){
                Alert("修改MIME类别失败");
                return false;
            }
        }

        if(this.openAsync){ //异步模式，程序继续执行
            if(this.ProcessRequestFunction==null){
                Alert("请指定处理返回信息的函数");
                return false;
            }
            var _http_request_ajax = this.HttpRequest;
            var _this_ajax = this;
            this.HttpRequest.onreadystatechange = function(){
                if(_http_request_ajax.readyState==4) {
                    if(_http_request_ajax.status==200) {
                        _this_ajax.ProcessRequestFunction(_http_request_ajax, _this_ajax.ProcessRequestParam, _this_ajax.LoadingImg);
                    }else{
                        //Alert("您所请求的页面有异常。");
                        return false;
                    }
                }
            }
        }

        if(this.LoadingImg!=null){
            funShow(this.LoadingImg);
        }

        return true;
    }

    //向服务器发出HTTP请求
    //格式：name=value&anothername=othervalue&so=on
    this.Send = function(idata){
        if(!this.checkHttpRequest()){
            return false;
        }
        var data = null;
        if(this.openMethod.toUpperCase()=="POST"){
            data = funEscapeAll(idata);
        }
        try{
            this.HttpRequest.send(data);
            return true;
        }catch(e){
            if(e instanceof Error){
                Alert("向服务器发出HTTP请求失败");
                return false;
            }
        }
    }

    //处理服务器返回的信息
    this.getResponseText = function(type){
        if(!this.checkHttpRequest()){
            return false;
        }
        if(this.HttpRequest.readyState==4) {
            if(this.HttpRequest.status==200) {
                if((type!=null) && (type.toUpperCase()=="XML")){
                    return this.HttpRequest.responseXML;
                }
                return this.HttpRequest.responseText;
            }else{
                //Alert("您所请求的页面有异常。");
                return false;
            }
        }
    }

    //停止当前请求
    this.abort = function(){
        if(!this.checkHttpRequest()){
            return false;
        }

        if(this.LoadingImg!=null){
            funHide(this.LoadingImg);
        }

        if(this.HttpRequest.readyState>0 && this.HttpRequest.readyState<4){
            this.HttpRequest.abort();
        }
    }

}

//=====================================================================================
//公共函数
//=====================================================================================
function $(_obj){
    var o;
    if (typeof(_obj)!="string")
        return _obj;
    else{
        try{
            document.all;
            try{
                o=document.all(_obj);
            }catch(e){
                return null;
            }
        }catch(ee){
            try{
                o=document.getElementById(_obj);
            }catch(e){
                return null;
            }
        }
        return o;
    }
}

function funEscapeAll(str){
    var t = "&";
    var s = str.split(t);
    if(s.length<=0) return str;
    for(var i=0; i<s.length; i++){
        s[i] = funEscapeOne(s[i]);
    }
    return s.join(t);
}

function funEscapeOne(str){
    var i = str.indexOf("=");
    if(i==-1) return str;
    var t = URLEncode(str.substr(i+1));
    return str.substring(0, i+1)+t;
}

function URLEncode(str){
    return encodeURIComponent(str);
    /*
	return escape(str).replace(/\+/g, "%2B").
	replace(/\"/g,"%22").
	replace(/\'/g, "%27").
	replace(/\//g,"%2F");
	*/
}

function funEscapeXML(content) {
    if (content == undefined)
        return "";
    if (!content.length || !content.charAt)
        content = new String(content);
    var result = "";
    var length = content.length;
    for (var i = 0; i < length; i++) {
        var ch = content.charAt(i);
        switch (ch) {
        case '&':
            result += "&";
            break;
        case '<':
            result += "<";
            break;
        case '>':
            result += ">";
            break;
        case '"':
            result += '"';
			break;
		case '\'':
			result += "'";
			break;
		default:
			result += ch;
		}
	}
	return result;
}

function funShow(_obj){
	if(typeof(_obj)=="object")
		_obj.style.visibility = "inherit";
	else
		$(_obj).style.visibility = "inherit";
}

function funHide(_obj){
	if(typeof(_obj)=="object")
		_obj.style.visibility = "hidden";
	else
		$(_obj).style.visibility = "hidden";
}

function Alert(str){
	alert(str);
	//window.status = str;
} 
