// JavaScript Document
var msg=new Array();
msg[0]="账号可由大小写英文字母、下划线、数字组成，长度4-16个字符。";
msg[1]="<span class='tips'>此项为必填项，请输入您账号。</span>";
msg[2]="<span class='tips'>账号格式错误，请重新填写。</span>";
msg[3]="<span class='tips'>此账号已被注册，请重新填写。</span>";
msg[4]="密码区分字母大小写，长度6－20个字符。";
msg[5]="<span class='tips'>此项为必填项，请设置您的密码。</span>";
msg[6]="<span class='tips'>密码格式错误，请重新填写。</span>";
msg[7]="<span class='tips'>此项为必填项，请再次输入您的密码。</span>";
msg[8]="<span class='tips'>两次密码输入不一致，请重新填写。</span>";
msg[9]="地址是给您邮寄的地址，请认真填写。";
msg[10]="<span class='tips'>此项为必填项，请输入您的地址。</span>";
msg[11]="电话号码方便我们联系您，请认真填写。";
msg[12]="<span class='tips'>此项为必填项，请输入您的电话号码。</span>";
msg[13]="<span class='tips'>电话格式错误，请重新填写。</span>";
msg[14]="我们不会为您发送垃圾邮件，请认真填写。";
msg[15]="<span class='tips'>此项为必填项，请输入您的电子邮箱。</span>";
msg[16]="<span class='tips'>邮箱格式错误，请重新填写。</span>";
msg[17]="不区别大小写";
msg[18]="<span class='tips'>请输入图片中的四个字母。</span>";
msg[19]="<span class='tips'>验证码输入错误，请重新填写。</span>";
msg[20]="<img src='../images/write_ok.gif' />";
msg[21]="<span class='tips'>此项为必填项，请输入您的邮编。</span>";
msg[22]="<span class='tips'>邮编格式错误，请重新填写。</span>";
msg[23]="<span class='tips'>请输入您的旧密码。</span>";

/*正则表达式*/
 var regname = /^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){3,15}$/;
 var regphone = /(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/;
 var regemail = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
 var regzip = /[1-9]\d{5}(?!\d)/;

$(document).ready(function () {
	$(".kuang").focus(
		function(){
			$(this).css({"background-color": "#F1FFDE" });
		}
	);
	$("#yzm").focus(
		function(){
			$(this).css({"background-color": "#F1FFDE" });
		}
	);
	$("#butadd").click(
		function(){
			if(ckname()&&ckpwd()&&ckrpwd()&&ckaddress()&&ckphone()&&ckemail()&&ckzip()&&ckyzm())
			{
			return true;}
			return false;
		}
	);
	$("#name").focus(	
		function(){
		if($.trim(name)=="")
			$("#dvname").html(msg[0]);
		}
	);
	$("#name").blur(	
		function(){
			ckname();
		}
	);
	$("#pwd").focus(	
		function(){
			$("#dvpwd").html(msg[4]);
		}
	);
	$("#pwd").blur(	
		function(){
			ckpwd();
		}
	);
	$("#rpwd").focus(	
		function(){
			$("#dvrpwd").html("");
		}
	);
	$("#rpwd").blur(	
		function(){
			ckrpwd();
		}
	);
	$("#address").focus(	
		function(){
			$("#dvaddress").html(msg[9]);
		}
	);
	$("#address").blur(	
		function(){
			ckaddress();
		}
	);
	$("#phone").focus(	
		function(){
			$("#dvphone").html(msg[11]);
		}
	);
	$("#phone").blur(	
		function(){
			ckphone();
		}
	);
	$("#email").focus(	
		function(){
			$("#dvemail").html(msg[14]);
		}
	);
	$("#email").blur(	
		function(){
			ckemail();
		}
	);
	$("#yzm").focus(	
		function(){
			$("#dvyzm").html(msg[17]);
		}
	);
	$("#yzm").blur(	
		function(){
			ckyzm();
		}
	);
	$("#zip").focus(	
		function(){
			$("#dvzip").html(msg[21]);
		}
	);
	$("#zip").blur(	
		function(){
			ckzip();
		}
	);
});

//验证用户名
function ckname(){
	var name = $("#name").val();
	if($.trim(name)==""){
		$("#dvname").html(msg[1]);
		gbbg("#name");
		return false;
	}else if (!regname.test(name)) {
        $("#dvname").html(msg[2]);
        gbbg("#name");
        return false;
    }else{
    var flag=true;
    	$.ajax({
        	type: "POST",
            url: $("#ajaxurl").val()+name,
            async: false,
            error: function(){
           		alert("服务器繁忙，请稍后重试");
            },
            success: function(msg){
	            if ('ok' == msg) {
				    flag = false;
	            }
            }
         });
        if(!flag){
        	gbbg("#name");
			$("#dvname").html(msg[3]);
        	return false;
        }
    }
gbbgok("#name");
$("#dvname").html(msg[20]);
return true;
}
//验证密码
function ckpwd(){
	var pwd = $("#pwd").val();
	if(pwd==""){
		$("#dvpwd").html(msg[5]);
		gbbg("#pwd");
		return false;
	}else if (pwd.length<6||pwd.length>20) {
        $("#dvpwd").html(msg[6]);
        gbbg("#pwd");
        return false;
    }
    $("#dvpwd").html(msg[20]);
    gbbgok("#pwd");
    return true;
}
//验证密码是否一致
function ckrpwd(){
	var pwd = $("#pwd").val();
	var rpwd = $("#rpwd").val();
	if(rpwd==""){
		$("#dvrpwd").html(msg[7]);
		gbbg("#rpwd");
		return false;
	}else if(rpwd!=pwd){
		$("#dvrpwd").html(msg[8]);
		 gbbg("#rpwd");
        return false;
    }
    $("#dvrpwd").html(msg[20]);
    gbbgok("#rpwd");
    return true;
}
//验证地址
function ckaddress(){
	var address = $("#address").val();
	if($.trim(address)==""){
		$("#dvaddress").html(msg[10]);
		 gbbg("#address");
        return false;
    }
    $("#dvaddress").html(msg[20]);
    gbbgok("#address");
    return true;
}
//验证电话
function ckphone(){
	var phone = $("#phone").val();
	if($.trim(phone)==""){
		$("#dvphone").html(msg[12]);
		gbbg("#phone");
        return false;
    }else if (!regphone.test(phone)) {
        $("#dvphone").html(msg[13]);
        gbbg("#phone");
        return false;
    }
      $("#dvphone").html(msg[20]);
      gbbgok("#phone");
      return true;
}
//验证邮箱
function ckemail(){
	var email = $("#email").val();
	if($.trim(email)==""){
		$("#dvemail").html(msg[15]);
		gbbg("#email");
        return false;
    }else if (!regemail.test(email)) {
        $("#dvemail").html(msg[16]);
        gbbg("#email");
        return false;
    }
      $("#dvemail").html(msg[20]);
      gbbgok("#email");
      return true;
}
//验证邮编
function ckzip(){
	var zip = $("#zip").val();
	if($.trim(zip)==""){
		$("#dvzip").html(msg[21]);
		gbbg("#zip");
        return false;
    }else if (!regzip.test(zip)) {
        $("#dvzip").html(msg[22]);
        gbbg("#zip");
        return false;
    }
      $("#dvzip").html(msg[20]);
      gbbgok("#zip");
      return true;
}
//验证验证码
function ckyzm(){
	var yzm = $("#yzm").val();
	if($.trim(yzm)==""||$.trim(yzm).length!=4){
		$("#dvyzm").html(msg[18]);
		gbbg("#yzm");
        return false;
    }
      $("#dvyzm").html(msg[20]);
      gbbgok("#yzm");
      return true;
}
//错误背景色
function gbbg(id){
	$(id).css({"background-color": "#FCB879" });
}
//正确背景色
function gbbgok(id){
	$(id).css({"background-color": "#ffffff" });
}