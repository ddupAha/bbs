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
                var reply_id = tr.attr('data-id');
                zlalert.alertConfirm({
                    "msg":"您确定要删除这个回复吗？",
                    'confirmCallback': function () {
                        zlajax.post({
                            'url': '/delr.jhtml',
                            'data': {
                                'id': reply_id,
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
    </script>
    <%
        ReplyInfoDAO replyInfoDAO=new ReplyInfoDAO();
        UserInfoDAO userInfoDAO=new UserInfoDAO();
    %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>所属帖子</th>
            <th>回复用户</th>
            <th>主要内容</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ReplyInfo> listReply = replyInfoDAO.getAllReplyInfo();
            request.setAttribute("listReply",listReply);
        %>
        <c:forEach items="${listReply}" var="reply">
            <tr data-id="${reply.rid}">
                <td>${reply.rid}</td>
                <td>${reply.rtopic}</td>
                <c:set var="uid" value="${reply.ruid}" scope="request"/>
                <%
                    int uid= (int) request.getAttribute("uid");
//                    int sid= (int) request.getAttribute("sid");
                    UserInfo userInfo=userInfoDAO.getUserInfoByID(uid);
                    String uname=userInfo.getUname();
                    request.setAttribute("uname",uname);
                %>
                <td>${uname}</td>
                <td>${reply.rcontents}</td>
                <td>
                    <button class="btn btn-danger btn-xs delete-board-btn">删除</button>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</rapid:override>
<%@ include file="bbs_index.jsp" %>
