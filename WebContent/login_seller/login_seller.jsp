<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家注册</title>
</head>
<body>
	注册信息填写:<br/>
	<form action="../loginSellerPut" method = "post"
		enctype = "multipart/form-data" >
		
		商家帐户名(邮箱)：<input name = "email"  type = "text"/><br/>
		帐户密码：<input name = "passwd0"  type = "password"/><br/>
		重复密码：<input name = "passwd1"  type = "password"/><br/>
		商家店铺名：<input name = "title"  type = "text"/><br/>
		商家法人姓名：<input name = "name"  type = "text"/><br/>
		商家法人电话：<input name = "phone" type = "text"/><br/>
		上传商家店面图片：<input name = "myfiles" type = "file"><br/>
		上传商家店面图片：<input name = "myfiles" type = "file"><br/>
		<button id = "submit" >提交</button>
	</form>
</body>
</html>