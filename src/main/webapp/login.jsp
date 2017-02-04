<%-- 
    Document   : login
    Created on : Dec 7, 2016, 3:10:21 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Login Form</title>
  
  
  
      <link rel="stylesheet" href="css/style2.css">

  
</head>

<body>
  <body>
	<div class="login">
		<div class="login-screen">
			<div class="app-title">
				<h1>Login</h1>
			</div>

                    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
			<div class="login-form">
				<div class="control-group">
				<input type="text" class="login-field" value="" placeholder="username" name="login-name">
				<label class="login-field-icon fui-user" for="login-name"></label>
				</div>

				<div class="control-group">
                                    <input type="password" class="login-field" value="" placeholder="password" name="login-pass">
				<label class="login-field-icon fui-lock" for="login-pass"></label>
				</div>
<!--
				<a class="btn btn-primary btn-large btn-block" href="#">login</a>-->
                                  <input  class="btn btn-primary btn-large btn-block" type="submit" name="button1" value="Login" />
				<a class="login-link" href="#">Lost your password?</a>
			</div>
                    </form>
		</div>
	</div>
</body>
  
  
</body>
</html>