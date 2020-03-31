<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/23
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Entity.SectionInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.SectionInfoDAO" %>
<%@ page import="Dao.TopicInfoDAO" %>
<%@ page import="Dao.ReplyInfoDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dao.UserInfoDAO" %>
<%@ page import="Entity.UserInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/js/zlajax.js"></script>
    <link rel="stylesheet" href="/sweetalert/sweetalert.css">
    <script src="/sweetalert/sweetalert.min.js"></script>
    <script src="/sweetalert/zlalert.js"></script>


    <link rel="stylesheet" href="/css/front_base.css">
    <link rel="stylesheet" href="/css/front_index.css">
    <title>

    </title>
    <%--    {% block head %}{% endblock %}--%>
    <%
        List<SectionInfo> listParent = null;		//根版块列表
        List<SectionInfo> listChild = null;			//子版块列表
        int sParentId = 0;							//根版块编号
        int sId = 0;								//子版块编号
        int tId = 0;								//帖子编号
        SectionInfoDAO section_dao = new SectionInfoDAO();//版块DAO
        TopicInfoDAO topic_dao = new TopicInfoDAO();	//主题DAO
        ReplyInfoDAO reply_dao = new ReplyInfoDAO();	//回复DAO
    %>


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
                <%--                <span id="login-tag" data-is-login="1" style="display:none;"></span>--%>
                <%--                <li class="dropdown">--%>
                <%--                    <a href="#" class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">--%>
                <%--                        {{ g.front_user.username }}--%>
                <%--                        <span class="caret"></span>--%>
                <%--                    </a>--%>
                <%--                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">--%>
                <%--                        <li><a href="#">个人中心</a></li>--%>
                <%--                        <li><a href="#">设置</a></li>--%>
                <%--                        <li><a href="">注销</a></li>--%>
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
        <div class="post-group">
            <ul class="post-group-head">
                <%--                    {% if current_sort == 1 %}--%>
                <%--                    <li class="active" ><a href="">最新</a></li>--%>
                <%--                    {% else %}--%>
                <li ><a href="">当前位置:${sessionScope.get("location")}主题</a></li>
                <%--                    {% endif %}--%>
                <%--                    {% if current_sort == 2 %}--%>
                <%--                    <li class="active"><a href="">精华帖子</a></li>--%>
                <%--                    {% else %}--%>
                <%--                    <li><a href="">精华帖子</a></li>--%>
                <%--                    {% endif %}--%>
                <%--                    {% if current_sort == 3 %}--%>
                <%--                    <li class="active"><a href="">点赞最多</a></li>--%>
                <%--                    {% else %}--%>
                <%--                    <li><a href="">点赞最多</a></li>--%>
                <%--                    {% endif %}--%>
                <%--                    {% if current_sort == 4 %}--%>
                <%--                    <li class="active"><a href="">评论最多</a></li>--%>
                <%--                    {% else %}--%>
                <%--                    <li><a href="">评论最多</a></li>--%>
                <%--                    {% endif %}--%>
            </ul>
            <ul class="post-list-group">
                <%--                    {% for post in posts %}--%>
                <c:forEach items="${topic_list}" var="topic">
                    <li  data-id="${topic.tid}" data-name="${topic.ttopic}">
                        <div class="author-avatar-group">
                            <img src="image/logo.png" alt="">
                        </div>
                        <div class="post-info-group">
                            <p class="post-title">
                                <a href="/topicDetail.jhtml?did=${topic.tid}">帖子：${topic.ttopic}</a>
                            </p>
                            <c:set var="id" value="${topic.tuid}" scope="request"/>
                            <%
                                UserInfoDAO userInfoDAO = new UserInfoDAO();
                                int id = (int) request.getAttribute("id");
                                UserInfo userinfo = userInfoDAO.getUserInfoByID(id);
                                request.setAttribute("userinfo",userinfo);
                            %>
                            <p class="post-info" style="display: inline">
                                <span>作者:${userinfo.uname}</span>
                                <span>发表时间:${topic.tpublishtime}</span>
                                <span>评论:${topic.treplycount}条</span>
                                <span>阅读:${topic.tclickcount}次</span>

                            </p>
                            <c:if test="${userinfo.uname==sessionScope.get('username')}">
                                <div class="layui-btn-group" style="float: right">
                                    <button class="btn btn-primary">编辑</button>
                                    <button class="btn btn-danger" >删除</button>
                                </div>
                            </c:if>

                        </div>


                    </li>
                </c:forEach>
                <%--                    {% endfor %}--%>
            </ul>
            <c:if test="${pageNos>1 }">
                <a class="prev" href="/topicDetail.jhtml?pageNos=1&tid=${sessionScope.get("tid")}" >首页</a>
                <a  href="/topicDetail.jhtml?pageNos=${pageNos-1 }&tid=${sessionScope.get("tid")}">上一页</a>
            </c:if>
            <c:if test="${pageNos <=countPage }">
                <a href="/topicDetail.jhtml?pageNos=${pageNos+1 }&tid=${sessionScope.get("tid")}">下一页</a>
                <a class="next" href="/topicDetail.jhtml?pageNos=${countPage }&tid=${sessionScope.get("tid")}">末页</a>
            </c:if>

<%--            <form action="/topicDetail.jhtml?tid=${sessionScope.get("tid")}">--%>
                <h4 >
                    当前第<span>${pageNos}</span>页&nbsp&nbsp
                    <%--                            <input type="text" value="${pageNos}" name="pageNos" size="1">页--%>
                    <%--                            <input type="submit" value="go" class="layui-btn">--%>
                    共${countPage}页
                </h4>
<%--            </form>--%>
            <%--                <div style="text-align:center;">--%>
            <%--                    {{ pagination.links }}--%>
        </div>
    </div>
</div>


<div class="sm-container">
    <div style="padding-bottom:10px;">
        <a href="/addPost.jsp" class="btn btn-warning btn-block">发布帖子</a>
    </div>
    <div class="list-group">
        <%--                {% if current_board %}--%>
        <%--                <a href="/" class="list-group-item">所有板块</a>--%>
        <%--                {% else %}--%>
        <%
            listParent = section_dao.getSectionById(0);
            request.setAttribute("listParent",listParent);

        %>

        <a href="/mainborad.jhtml?bid=-1"class="list-group-item active">所有板块</a>
        <%--                {% endif %}--%>
        <%--                {% for board in boards %}--%>
        <%--                {% if board.id == current_board %}--%>
        <%--                <a href="" class="list-group-item active"></a>--%>
        <%--                {% else %}--%>
        <ul>
            <li>
                <c:forEach items="${listParent}" var="list">
                    <a href="/mainborad.jhtml?bid=${list.sid.toString()}"  class="list-group-item">${list.sname}专区</a>
                </c:forEach>
            </li>
        </ul>
        <%--                <a href="" class="list-group-item">111</a>--%>
        <%--                {% endif %}--%>
        <%--                {% endfor %}--%>
    </div>
</div>
<%--        {% endblock %}--%>
</div>
</body>

<script>

    $(".btn-primary").click(function (event) {
        var self = $(this);
        var tr = self.parent().parent().parent();
        var tname = tr.attr('data-name');
        var id = tr.attr('data-id');
        // alert(tname + "  " + id);
        zlalert.alertOneInput({
            'text': '请输入新的帖子名称！',
            'placeholder': tname,
            'confirmCallback': function (inputValue) {
                zlajax.post({
                    'url': '/muserType.jhtml',
                    'data': {
                        'id': id
                    },
                    'success': function () {
                        zlalert.alertInfo("修改帖子成功！！！");
                        setTimeout("window.location.reload()",900);

                    }
                });
            }
        });
    })


    $(".btn-danger").click(function (event) {
        var self = $(this);
        var tr = self.parent().parent().parent();
        var id = tr.attr('data-id');
        // alert(id);
        zlalert.alertConfirm({
            "msg":"您确定要删除这条帖子吗？",
            'confirmCallback': function () {
                zlajax.post({
                    'url': '/deltopic.jhtml',
                    'data':{
                        'id': id
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
</script>


</html>

