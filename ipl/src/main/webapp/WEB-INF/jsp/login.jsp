<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link href="css/login-box.css" rel="stylesheet" type="text/css" />
  </head>	
  <body>
		<div>	
			<div id="login-box" style="display: block;">
			   <form:form method="POST" commandName="loginForm" action="login.htm">	
					<div id="login-box-name">User Name</div>
					<div id="login-box-field">
						<form:input path="userName" id="txtUserName" class="form-login rounded-box" title="UserName" value="" size="30" maxlength="2048" />
					</div>
					<div id="login-box-name">Password</div>
					<div id="login-box-field">
						<form:input path="password" id="txtPassword"  type="password" class="form-login rounded-box" title="Password" value="" size="30" maxlength="2048" />	
					</div>
					<br/>
					<div id="login-box-name" style="color: red;width: 175px;"><form:errors path="userName"/></div>
					<input id="btn-submit"class="rounded-box" style="margin-left: 90px;color:white;font-weight:bold;height: 28px;" type="submit" onclick="" name="signin" value="  Sign In  "/>
				</form:form>
			</div>
		</div>
  </body>
</html>