<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/23
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登录-论坛CMS管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/cms/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form class="form-signin" method="post" action="/blogin.jhtml">
        <h2 class="form-signin-heading">请登录</h2>
<%--        <input type="hidden" name="csrf_token" value="{{ csrf_token() }}">--%>
        <label for="bname" class="sr-only">用户名：</label>
        <input type="text" id="bname" class="form-control" placeholder="用户名" name="bname" required autofocus>
        <label for="password" class="sr-only">密码：</label>
        <input type="password" id="password" class="form-control" placeholder="密码" name="password" required>
        <div class="checkbox">
<%--            <label>--%>
<%--                <input type="checkbox" value="1" name="remember">记住我--%>
<%--            </label>--%>
        </div>
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="立即登录">
<%--        <button class="btn btn-lg btn-primary btn-block" type="submit">立即登录</button>--%>
    </form>
<%--    {% if message %}--%>
<%--    <p style="text-align: center;" class="text-danger">{{ message }}</p>--%>
<%--    {% endif %}--%>
</div> <!-- /container -->
</body>
</html>
