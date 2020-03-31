<%--
  Created by IntelliJ IDEA.
  User: LJL
  Date: 2019/12/23
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,Dao.*,Entity.*"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="head">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
</rapid:override>
<rapid:override name="main_content">
    <script type="text/javascript">
        $(function () {
            $(".delete-board-btn").click(function (event) {
                var self = $(this);
                var tr = self.parent().parent();
                var user_id = tr.attr('data-id');
                zlalert.alertConfirm({
                    "msg":"您确定要删除这个用户吗？",
                    'confirmCallback': function () {
                        zlajax.post({
                            'url': '/delUser.jhtml',
                            'data': {
                                'user_id': user_id,
                            },
                            'success': function () {
                                zlalert.alertSuccess("删除成功！");
                                setTimeout("window.location.reload()",900);
                            }
                        });
                    }
                });
            });

        });



        $(function () {
            $(".edit-board-btn").click(function (event) {
                var self = $(this);
                var tr = self.parent().parent();
                var user_id = tr.attr('data-id');
                zlalert.alertOneInput({
                    'text': '请输入新的用户类别编号\n管理员编号：  2\n普通用户编号：  0',
                    'placeholder': "请输入用户类别编号",
                    'confirmCallback': function (inputValue) {
                        zlajax.post({
                            'url': '/muserType.jhtml',
                            'data': {
                                'id': user_id,
                                'type': inputValue
                            },
                            'success': function () {
                                zlalert.alertInfo("修改用户成功！！！");
                                setTimeout("window.location.reload()",900);

                            }
                        });
                    }
                })
            });

        });


    </script>
    <%
         UserInfoDAO userInfoDAO=new UserInfoDAO();
    %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>头像</th>
            <th>用户ID</th>
            <th>用户名称</th>
            <th>用户密码</th>
            <th>性别</th>
            <th>注册时间</th>
            <th>用户类型</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<UserInfo> listUser = userInfoDAO.getAllUserInfo();
            request.setAttribute("listUser",listUser);
        %>
        <c:forEach items="${listUser}" var="user">
            <tr data-name="${user.uname}" data-id="${user.uid}">
                <td><img src="/image/head/${user.uface}" alt=""></td>
                <td>${user.uid}</td>
                <td>${user.uname}</td>
                <td>${user.upassword}</td>
                <c:if test="${user.usex==false}"><td>男</td></c:if>
                <c:if test="${user.usex==true}"><td>女</td></c:if>
                <td>${user.uregtime}</td>
                <c:if test="${user.utype==2}"><td>管理员</td></c:if>
                <c:if test="${user.utype==0}"><td>普通用户</td></c:if>
                <td>
                    <button class="btn btn-primary btn-xs edit-board-btn">编辑</button>
                    <button class="btn btn-danger btn-xs delete-board-btn">删除</button>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</rapid:override>
<%@ include file="bbs_index.jsp" %>
