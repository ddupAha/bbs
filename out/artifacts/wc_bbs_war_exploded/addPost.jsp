<%@ page import="Dao.SectionInfoDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.SectionInfo" %><%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/23
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<html>
<head>
    <title>Title</title>
    <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/js/zlajax.js"></script>
    <link rel="stylesheet" href="/sweetalert/sweetalert.css">
    <script src="/sweetalert/sweetalert.min.js"></script>
    <script src="/sweetalert/zlalert.js"></script>

    <link rel="stylesheet" href="/css/front_base.css">



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
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="请输入关键字">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
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
                <c:if test="${is_login==1}">
                    <li><a href="#">${sessionScope.get("username")}</a></li>
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
<%--    <form>--%>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">标题</span>
                <input type="text" class="form-control" name="title" id="title">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">板块</span>
                <select id="board_id" name="board_id" class="form-control">
                <%
                    SectionInfoDAO sectionInfoDAO = new SectionInfoDAO();
                    List<SectionInfo> list = sectionInfoDAO.getChildBoards();
                    for (int i = 0; i < list.size(); i++) {
                %>
                    <option value="<%=list.get(i).getSname()%>"><%=list.get(i).getSname()%></option>
                    <%}%>
                </select>
            </div>
        </div>
        <span id="login-tag" data-is-login=<%=session.getAttribute("is_login")%> style="display:none;"></span>
        <div class="form-group">
            <textarea name="add_content" id="add_content" cols="135" rows="30"></textarea>
        </div>
        <div class="form-group">
            <button class="btn btn-danger"  onclick="pp()">发布帖子</button>
        </div>
<%--    </form>--%>
</div>
</body>
<script type="text/javascript">
    function pp() {
        var loginTag = $("#login-tag").attr("data-is-login"); //获取login-tag 判断有没有登录
        // alert(loginTag);
        if(loginTag !=1){
            window.location = '/login.jsp';
            return ;}
        var content = document.getElementById("add_content").value;
        var title = document.getElementById("title").value;
        if(content == "" )
        {
            zlalert.alertInfo("帖子内容为空！");
            return ;
        }
        if(title == "" )
        {
            zlalert.alertInfo("请输入标题！");
            return ;
        }
        var myselect=document.getElementById("board_id");
        var index=myselect.selectedIndex ;
        var s = myselect.options[index].value;
        var url = '/ap.jhtml?add_content=' +content+"&bo_name="+s+"&title="+title+"&h=你吗死了";
        // alert(url);
        $.ajax({
            type:'POST',
            url: url,
            success:function(){
                zlalert.alertSuccess("发布帖子成功！！！");
                // setTimeout("window.location.reload()",900);
            }

        })
    }


</script>


</html>
