<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script language="javascript" type="text/javascript">
	function emailCheck(obj, labelName) {
		var objName = eval("document.all." + obj);
		var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		if (!pattern.test(objName.value)) {
			alert("请输入正确的邮箱地址。");
			objName.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<form action="/blog/register/confirm" method="post">
		<table>
			<tr>
				<td>用户名:</td>
				<td><input name="userName" type="text"></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input name="password" type="password"></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input name="confirmPassword" type="password"></td>
				<td>${pwd_err}</td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input name="email" id="email"  onblur="return emailCheck('email', 'email')"  type="text"></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"></td>
				<td><input type="reset" value="重置"></td>
			</tr>
		</table>
	</form>
</body>
</html>