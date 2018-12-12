<%--
  Created by IntelliJ IDEA.
  User: csx14
  Date: 2018/12/11
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户登陆</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        function register(){
            alert ("正在登陆");
            document.getElementById("formId").action="register.sc";
        }
    </script>
</head>

<body>

<div>
    <form action="login.sc" id="formId" name="myForm">
        <div>账  号：<input type="text" name="account" /></div>
        <div>密  码：<input type="password" name="password" /></div>
        <br>
        <div>
            <input type="submit" name="login" value="登录" />
            <button onclick="register()">注册</button>
        </div>
    </form>
        <p style="color:red">${sessionScope.loginMessage}</p>
        <p style="color:red">${sessionScope.registerMessage}</p>
    <%
        session.removeAttribute("loginMessage");
        session.removeAttribute("registerMessage");
    %>
</div>
</body>
</html>