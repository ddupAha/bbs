<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/23
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>博客登录</title>
    <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/js/zlajax.js"></script>
    <link rel="stylesheet" href="/sweetalert/sweetalert.css">
    <script src="/sweetalert/sweetalert.min.js"></script>
    <script src="/sweetalert/zlalert.js"></script>
<%--    <link rel="stylesheet" href="/css/base.css">--%>
<%--    <script src="/js/base.js"></script>--%>

    <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js"></script>
    <script src="/js/zlparam.js"></script>
    <link rel="stylesheet" href="/css/front_signbase.css">
    <script src="/js/front_signin.js"></script>
<%--    {% block head %}--%>

<%--    {% endblock %}--%>
    <%
    if(request.getAttribute("error") == null)
        request.setAttribute("error","");
    %>
</head>
<body>

<div class="outer-box">
    <div class="logo-box">
        <a href="/">
            <img src="/image/logo.png" alt="">
        </a>
    </div>


    <h2 class="page-title">论坛登录</h2>



    <div class="sign-box">
        <div><span style="color: red">${error_msg}</span></div>
        <form action="/login.jhtml" method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="uname" placeholder="用户名">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="upass" placeholder="密码">
        </div>
<%--        <div class="checkbox">--%>
<%--            <label>--%>
<%--                <input type="checkbox" name="remember" value="1">记住我--%>
<%--            </label>--%>
<%--        </div>--%>
        <div class="form-group">
            <%--            <button class="btn btn-warning btn-block" id="submit-btn">立即登录</button>--%>
            <input type="text" class="form-control" name="cap" placeholder="验证码" style="width: 195px;display: inline"  >
            <img src="/code.jhtml" alt="" style="width: 100px;height: 40px"
                 onclick="javascript:this.src='/code.jhtml?id='+new Date().getMilliseconds()"
            >
        </div>
        <div class="form-group">
<%--            <button class="btn btn-warning btn-block" id="submit-btn">立即登录</button>--%>
            <input type="submit"  value="立即登录"  class="btn btn-warning btn-block" >
        </div>

        <div class="form-group">
            <a href="/registe.jsp" class="signup-link">没有账号？立即注册</a>
<%--            <a href="#" class="resetpwd-link" style="float:right;">找回密码</a>--%>
            <a href="/front_index.jsp" class="" style="margin-left: 100px">游客浏览</a>
        </div>
        </form>

    </div>

        <span style="display:none;" id="return-to-span"><%=request.getAttribute("error")%></span>
</div>
<%--<script>--%>
<%--zlalert.alertError('用户名或密码错误！！');--%>
<%--setTimeout("window.location.reload()",900);--%>
<%--</script>--%>



</body>
</html>
