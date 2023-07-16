<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${cookie.loginerror.value}
   <form action="http://localhost:8080/ShoppingApp/LoginCheckDBAdmin" method="post">
		Enter uname : <input type="text" name="uname" /> 
		<br/>
		Enter pwd : <input type="password" name="pwd" />
		<br/>
		<input type="submit" value="LOGIN" />
		<input type="reset" value="CLEAR" /> <br/>
		
	</form>
</body>
</html>