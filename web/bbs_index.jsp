<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/23
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%--{% from "common/_macros.html" import static %}--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
<%--    {% include "common/_heads.html" %}--%>
<%--    <meta name="csrf-token" content="{{ csrf_token() }}">--%>
    <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--    <script src="{{ url_for('static',filename="common/zlajax.js") }}"></script>--%>
    <script src="/js/zlajax.js"></script>
<%--    <link rel="stylesheet" href="{{ url_for('static',filename="common/sweetalert/sweetalert.css") }}">--%>
    <link rel="stylesheet" href="/sweetalert/sweetalert.css">
<%--    <script src="{{ url_for('static',filename="common/sweetalert/sweetalert.min.js") }}"></script>--%>
    <script src="/sweetalert/sweetalert.min.js"></script>
<%--    <script src="{{ url_for('static',filename="common/sweetalert/zlalert.js") }}"></script>--%>
    <script src="/sweetalert/zlalert.js"></script>


<%--    <link rel="stylesheet" href="{{ static('cms/css/base.css') }}">--%>
    <link rel="stylesheet" href="/css/base.css">
<%--    <script src="{{ static('cms/js/base.js') }}"></script>--%>
    <script src="/js/base.js"></script>
    <rapid:block name="head"></rapid:block>
<%--    {% block head %}{% endblock %}--%>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">论坛管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href=""><%=session.getAttribute("b_username")%></a></li>
                <li><a href="/cms_login.jsp">注销</a></li>
            </ul>
<%--            <form class="navbar-form navbar-right">--%>
<%--                <input type="text" class="form-control" placeholder="查找...">--%>
<%--            </form>--%>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav-sidebar">
                <li class="unfold"><a href="">首页</a></li>
<%--                <li class="profile-li">--%>
<%--                    <a href="javascript:void(0);">个人中心<span></span></a>--%>
<%--                    <ul class="subnav">--%>
<%--                        <li><a href="">个人信息</a></li>--%>
<%--                        <li><a href="">修改密码</a></li>--%>
<%--                        <li><a href="">修改邮箱</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                <li class="nav-group banner-manage"><a href="">轮播图管理</a></li>--%>
                <li class="nav-group post-manage"><a href="topicMana.jsp">帖子管理</a></li>

                <li class="comments-manage"><a href="replyMana.jsp">评论管理</a></li>
                <li class="board-manage"><a href="boardMana.jsp" >板块设置</a></li>
                <li class="nav-group user-manage"><a href="userMana.jsp">用户管理</a></li>
<%--                <li class="nav-group cmsuser-manage"><a href="">CMS用户管理</a></li>--%>
<%--                <li class="cmsrole-manage"><a href="">CMS组管理</a></li>--%>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
<%--            <h1>{% block page_title %}{% endblock %}</h1>--%>
            <div class="main_content" style="margin-top: 40px">
<%--                {% block main_content %}{% endblock %}--%>
                <rapid:block name="main_content"></rapid:block>
            </div>
        </div>
    </div>
</div>
</body>
</html>
