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
    <div style="margin-left: 360px;margin-top: 100px">
        <div>
            <lable>头像：</lable>
            <img src="/image/head/${person.uface}" alt="" id="myimg" img_path="${person.uface}">
            <select name="chose_face" id="chose_face" onchange="getImg()">
                <c:forEach var="i" begin="1" end="15">
                    <option value="${i}.gif">${i}.gif</option>
                </c:forEach>
            </select>
        </div>
        <div style="margin-top: 10px">
            <lable>用户名：</lable>
            <input type="text" value="${person.uname}" name="name" id="name">
        </div>
        <div style="margin-top: 10px">
            <lable>性别：</lable>
            <select name="gender" id="gender">
                <c:if test="${person.usex==true}">
                    <option value="女">女</option>
                    <option value="男">男</option>
                </c:if>
                <c:if test="${person.usex==false}">
                    <option value="男">男</option>
                    <option value="女">女</option>
                </c:if>
            </select>
        </div>
<%--        <input type="submit" class="btn btn-primary" value="更新个人信息" style="margin-top: 10px;width: 300px;border-radius: 10px">--%>
        <button class="btn btn-primary"  style="margin-top: 10px;width: 300px;border-radius: 10px">更新个人信息</button>
    </div>
<%--    <table style="margin-left: 360px" class="table table-hover">--%>
<%--        <tr class="active">--%>
<%--            <td>头像</td>--%>
<%--            <td>用户名</td>--%>
<%--            <td>性别</td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><img src="/image/head/${person.uface}" alt=""></td>--%>
<%--            <td>${person.uname}</td>--%>
<%--            <c:if test="${person.usex==true}">--%>
<%--                <td>女</td>--%>
<%--            </c:if>--%>
<%--            <c:if test="${person.usex==false}">--%>
<%--                <td>男</td>--%>
<%--            </c:if>--%>
<%--        </tr>--%>
<%--    </table>--%>
</div>


<div class="sm-container">

</div>
</body>
<script>
    function getImg() {
        var obj = document.getElementById("chose_face"); //定位id
        var index = obj.selectedIndex; // 选中索引
        var value = obj.options[index].value;
        $('#myimg').attr('src',"/image/head/"+value);
        $('#myimg').attr('img_path',value);
    }

    $(".btn-primary").click(function (event) {
        // alert(id);
        var name = document.getElementById("name").value;
        var face = document.getElementById("myimg").getAttribute("img_path");
        var sex = document.getElementById("gender").value;
        // alert("name: "+name +"  face:"+face+"  sex:"+sex);
        zlalert.alertConfirm({
            "msg":"您确定要更新信息吗？",
            'confirmCallback': function () {
                zlajax.post({
                    'url': '/updateperson.jhtml',
                    'data':{
                        'name': name,
                        'face': face,
                        'sex': sex,
                    },
                    'success': function () {
                        zlalert.alertSuccess("更新信息成功");
                        setTimeout("window.location.reload()",600);
                        // window.location.reload();
                    }
                })
            }
        });
    });


</script>
</html>



