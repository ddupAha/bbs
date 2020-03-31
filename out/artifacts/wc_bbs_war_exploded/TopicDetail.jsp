<%@ page import="Dao.UserInfoDAO" %>
<%@ page import="Entity.UserInfo" %>
<%@ page import="Dao.SectionInfoDAO" %>
<%@ page import="Entity.SectionInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/23
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<%--{% from "common/_macros.html" import static %}--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <%--    {% include "common/_heads.html" %}--%>
    <%--    <meta name="csrf-token" content="{{ csrf_token() }}">--%>
    <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js" charset="UTF-8"></script>
    <script src="/js/jquery.min.js" charset="UTF-8"></script>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" charset="UTF-8"></script>

    <script src="/js/zlajax.js" charset="UTF-8"></script>



    <link rel="stylesheet" href="/sweetalert/sweetalert.css">

    <script src="/sweetalert/sweetalert.min.js" charset="UTF-8"></script>

    <script src="/sweetalert/zlalert.js" charset="UTF-8"></script>
    <link rel="stylesheet" href="/css/front_base.css">
    <link rel="stylesheet" href="/css/front_pdetail.css">



    <script src="/js/base.js" charset="UTF-8"></script>
    <script src="/js/front_pdetail.js" charset="UTF-8"></script>

    <%--    {% block head %}{% endblock %}--%>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">论坛</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/mainborad.jhtml?bid=-1">首页<span class="sr-only">(current)</span></a></li>
            </ul>
<%--            <form class="navbar-form navbar-left">--%>
<%--                <div class="form-group">--%>
<%--                    <input type="text" class="form-control" placeholder="请输入关键字">--%>
<%--                </div>--%>
<%--                <button type="submit" class="btn btn-default">搜索</button>--%>
<%--            </form>--%>
            <ul class="nav navbar-nav navbar-right">
<%--                {% if g.front_user %}--%>
                <span id="login-tag" data-is-login=${sessionScope.get("is_login")} style="display:none;"></span>
<%--                <li class="dropdown">--%>
<%--                    <a href="#" class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">--%>
<%--                        {{ g.front_user.username }}--%>
<%--                        <span class="caret"></span>--%>
<%--                    </a>--%>
<%--                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">--%>
<%--                        <li><a href="#">个人中心</a></li>--%>
<%--                        <li><a href="#">设置</a></li>--%>
<%--                        <li><a href="{{ url_for('front.logout') }}">注销</a></li>--%>
<%--                    </ul>--%>
<%--                </li>--%>
<%--                {% else %}--%>
    <c:if test="${type == 0}">
        <li><a href="/bbs_index.jsp">进入后台管理 </a></li>
    </c:if>
    <c:if test="${is_login==1}">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" type="button" id="dropdownMenu11" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                欢迎！${sessionScope.get("username")}
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="/person.jhtml">个人中心</a></li>
            </ul>
        </li>
        <li><a href="/logout.jhtml">注销</a></li>
    </c:if>
    <c:if test="${is_login!=1}">
        <li><a href="/login.jsp">登录</a></li>
        <li><a href="">注册</a></li>
    </c:if>
<%--                {% endif %}--%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="main-container">
    <div class="lg-container">
        <div class="post-container">
            <c:set var="id" value="${topicInfo.tuid}" scope="request"/>
            <c:set var="bid" value="${topicInfo.tsid}" scope="request"/>
            <%
                UserInfoDAO userInfoDAO = new UserInfoDAO();
                int id = (int) request.getAttribute("id");
                UserInfo userinfo = userInfoDAO.getUserInfoByID(id);
                request.setAttribute("userinfo",userinfo);

                SectionInfoDAO sectionInfoDAO = new SectionInfoDAO();
                int board_id = (int) request.getAttribute("bid");
                SectionInfo sectionInfo = sectionInfoDAO.getPSectionById(board_id);
                request.setAttribute("sectionInfo",sectionInfo);

            %>
            <h2>${topicInfo.ttopic}</h2>
            <p class="post-info-group">
                <span>发表时间：${topicInfo.tpublishtime}</span>
                <span>作者：${userinfo.uname}</span>
                <span>所属板块：${sectionInfo.sname}</span>
                <span>评论数：${topicInfo.treplycount}</span>
                <span>阅读数：${topicInfo.tclickcount}</span>
            </p>
            <article class="post-content" id="post-content" data-id="{{ post.id }}">
                ${topicInfo.tcontents}
            </article>
        </div>

        <div class="comment-group">
            <h3>评论列表</h3>
            <ul class="comment-list-group">
<%--                {% for comment in post.comments %}--%>
                <c:forEach items="${comments}" var="comment">
                    <li id="cid" data-id="${comment.id}">
                        <div class="avatar-group">
                            <img src="/image/head/${comment.face}" alt="">
                        </div>
                        <div class="comment-content">
                            <p class="author-info">
<%--                                <span style="display: none" ></span>--%>
                                <span>${comment.author}</span>
                                <span>${comment.publishtime}</span>
                            </p>
                            <p class="comment-txt">
                                    ${comment.contents}
                            </p>



                        </div>
                        <c:if test="${is_master==1}">
                        <div class="comment-btn-group">
<%--                            <button class="btn btn-primary">编辑</button>--%>
                            <button class="btn btn-warning" >删除</button>
                        </div>
                        </c:if>
                    </li>
                </c:forEach>

<%--                {% endfor %}--%>
            </ul>
        </div>
        <div class="add-comment-group">
            <h3>发表评论</h3>
<%--            <script id="editor" type="text/plain" style="height:100px;"></script>--%>
            <textarea name="post_content" id="post_content" cols="60" rows="10"

            ></textarea>
            <div class="comment-btn-group">
                <button class="btn btn-primary" id="comment-btn1" onclick="pp()">发表评论</button>
<%--                <button class="btn-primary" onclick="ggg()">1111</button>--%>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $(".btn-warning").click(function (event) {
        var self = $(this);
        var tr = self.parent().parent();
        var id = tr.attr('data-id');
        // alert(id);
        zlalert.alertConfirm({
            "msg":"您确定要删除这条评论吗？",
            'confirmCallback': function () {
                zlajax.post({
                    'url': '/delr.jhtml',
                    'data':{
                        'id': id,
                        'tid':${topicInfo.tid}
                    },
                    'success': function () {
                            zlalert.alertInfo("删除成功！");
                            setTimeout("window.location.reload()",900);
                            // window.location.reload();
                    }
                })
            }
        });
    });






    function pp() {
        var loginTag = $("#login-tag").attr("data-is-login"); //获取login-tag 判断有没有登录
        // alert(loginTag);
        if(loginTag !=1){
            window.location = '/login.jsp';return }
        var content = document.getElementById("post_content").value;
        if(content == "")
        {
            zlalert.alertInfo("评论内容为空！");
            return ;
        }
        $.ajax({
            type:'POST',
            url:'/post.jhtml?post_content=' +content ,
            success:function(){
                zlalert.alertSuccess("评论成功！");
                setTimeout("window.location.reload()",900);
            }

        })
    }
</script>
</html>
