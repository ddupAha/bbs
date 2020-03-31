<%@ page import="Entity.SectionInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="Dao.SectionInfoDAO" %>
<%@ page import="Dao.TopicInfoDAO" %>
<%@ page import="Dao.ReplyInfoDAO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/23
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
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
    <%
        List<SectionInfo> listParent = null;		//根版块列表
        List<SectionInfo> listChild = null;			//子版块列表
        int sParentId = 0;							//根版块编号
        int sId = 0;								//子版块编号
        int tId = 0;								//帖子编号
        SectionInfoDAO section_dao = new SectionInfoDAO();//版块DAO
        TopicInfoDAO topic_dao = new TopicInfoDAO();	//主题DAO
        ReplyInfoDAO reply_dao = new ReplyInfoDAO();	//回复DAO

        if(request.getAttribute("boad_list")==null)
        {
            SectionInfoDAO sectionInfoDAO = new SectionInfoDAO();
            ArrayList<SectionInfo> list = (ArrayList<SectionInfo>) sectionInfoDAO.getAll();
            request.setAttribute("boad_list",list);
            int pageNos;
            if (request.getParameter("pageNos") == null
                    || Integer.parseInt(request.getParameter("pageNos")) < 1) {
                pageNos = 1;
            } else {
                pageNos = Integer.parseInt(request.getParameter("pageNos"));
            }
            session.setAttribute("pageNos", pageNos);
            // 定义总页数并存到session中
            int countPage = list.size()/5+1;
            if(list.size() % 5 == 0)
                countPage = list.size() / 5;
            session.setAttribute("countPage", countPage);
            session.setAttribute("location","所有板块");

        }


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
    <c:if test="${type == 2}">
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

                    <li ><a href="">当前位置:${sessionScope.get("location")}</a></li>
                </ul>
                <ul class="post-list-group">
<%--                    {% for post in posts %}--%>
                <c:forEach items="${boad_list}" var="borad" begin="${(pageNos-1)*5 }" end="${pageNos*5-1}">
                    <li>
                        <div class="author-avatar-group">
                            <img src="image/logo.png" alt="">
                        </div>
                        <div class="post-info-group">
                            <p class="post-title">
                                <a href="/topic.jhtml?tid=${borad.sid.toString()}">主题：${borad.sname}</a>
                            </p>
                        </div>
                    </li>
                </c:forEach>
                </ul>
                <div>
                    <c:if test="${pageNos>1 }">
                        <a class="prev" href="/mainborad.jhtml?pageNos=1&bid=${sessionScope.get("bid")}" >首页</a>
                        <a  href="/mainborad.jhtml?pageNos=${pageNos-1 }&bid=${sessionScope.get("bid")}">上一页</a>
                    </c:if>
                    <c:if test="${pageNos <=countPage }">
                        <a href="/mainborad.jhtml?pageNos=${pageNos+1 }&bid=${sessionScope.get("bid")}">下一页</a>
                        <a class="next" href="/mainborad.jhtml?pageNos=${countPage }&bid=${sessionScope.get("bid")}">末页</a>
                    </c:if>

                    <form action="/mainborad.jhtml?bid=${sessionScope.get("bid")}">
                        <h4 >
                            当前第<span>${pageNos}</span>页&nbsp&nbsp
                            共${countPage}页
                        </h4>
                    </form>

                </div>


                </div>
            </div>
        </div>
        <div class="sm-container">
            <div style="padding-bottom:10px;">
                <a href="/addPost.jsp" class="btn btn-warning btn-block">发布帖子</a>
            </div>
            <div class="list-group">
                <%
                    listParent = section_dao.getSectionById(0);
                    request.setAttribute("listParent",listParent);

                %>

                <a href="/mainborad.jhtml?bid=-1" class="list-group-item active">所有板块</a>
                <ul>
                    <li>
                        <c:forEach items="${listParent}" var="list">
                            <a href="/mainborad.jhtml?bid=${list.sid.toString()}"  class="list-group-item">${list.sname}专区</a>
                        </c:forEach>
                    </li>
                </ul>
            </div>
        </div>
</body>
</html>
