$userjs={
		login : function() {
		  username=	$("#username").val();
		  password= $("#password").val();
			if(username==""){
				alert("用户名不能为空")
			}else if(password==""){
				alert("密码不能为空");
			}else{
				data = {"userName":username,"password":password};
				$.ajax({  
			        url : "/IntegratedManagement/user/login.do",  
			        type : "POST", 
			        datatype:"json", 
			        data :JSON.stringify( data),
			        contentType: "application/json; charset=utf-8", 
			        success : function(res) {  
			  
			        	if(res.code==10000){
			        		location.href="./user/userindex.html";
			        	}else if(res.code==10001){
			        		alert('用户不存在');
			        	}else if(res.code==10002){
			        		alert('密码错误');
			        	}else if (res.code==10003){
			        		alert('该账户已被冻结，请联系管理员');
			        	}
			        },  
			        error : function(res) {  
			            alert("网络故障");  
			        }  
			    }); 
			}
		  
		  
		},

changepassword:function(){
    var oldpassword= $("#oldpassword").val();
    var password1 = $("#password1").val();
    var password2 = $("#password2").val();
    if(oldpassword!=""&&password1!=""&&password2!=""){
        if(password1!=password2){
            alert("新密码两次输入不一致！");
        }
        else{
            var	data = {"password":oldpassword,"newPassword":password1};
            $.ajax({
                url : "/IntegratedManagement/user/resetPword.do",
                type : "POST",
                datatype:"json",
                data :JSON.stringify( data),
                contentType: "application/json; charset=utf-8",
                success : function(res) {

                    if(res.code==10000){
                        alert("密码修改成功");
                        location.href="./userindex.html";
                    }else if(res.code==10201){
                        alert('旧密码错误');
                    }
                },
                error : function(res) {
                    alert("网络故障");
                }
            });

        }
    }else{
        alert("密码输入不能为空");
    }

}
		
}