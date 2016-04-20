<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
</head>
<body>
	添加的商品信息：<br/>
	<form action="../addProduct"method = "post"
		enctype = "multipart/form-data">
		商品名称：<input name ="productName" type ="text" /> <br/>
		商品价格：<input name ="productPrice" typr ="text"/> <br/>
		商品图片1：<input name = "productFiles" type ="file"/> <br/>
		商品图片2：<input name = "productFiles" type ="file"/> <br/>
		商品图片3：<input name = "productFiles" type ="file"/> <br/>
		
	</form>
</body>
</html>