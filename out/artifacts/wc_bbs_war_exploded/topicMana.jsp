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
                var topic_id = tr.attr('data-id');
                zlalert.alertConfirm({
                    "msg":"您确定要删除这个帖子吗？",
                    'confirmCallback': function () {
                        zlajax.post({
                            'url': '/deltopic.jhtml',
                            'data': {
                                'id': topic_id,
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
        TopicInfoDAO topicInfoDAO=new TopicInfoDAO();
        UserInfoDAO userInfoDAO=new UserInfoDAO();
        SectionInfoDAO sectionInfoDAO=new SectionInfoDAO();
    %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>帖子ID</th>
            <th>帖子名称</th>
            <th>发帖用户</th>
            <th>所属板块</th>
            <th>评论数</th>
            <th>阅读数</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<TopicInfo> listTopic = topicInfoDAO.getAllTopicInfo();
            request.setAttribute("listTopic",listTopic);
        %>
        <c:forEach items="${listTopic}" var="topic">
            <tr data-name="${topic.ttopic}" data-id="${topic.tid}">
                <td>${topic.tid}</td>
                <td>${topic.ttopic}</td>
                <c:set var="uid" value="${topic.tuid}" scope="request"/>
                <c:set var="sid" value="${topic.tsid}" scope="request"/>
                <%
                    int uid= (int) request.getAttribute("uid");
                    int sid= (int) request.getAttribute("sid");
                    UserInfo userInfo=userInfoDAO.getUserInfoByID(uid);
                    String uname=userInfo.getUname();
                    SectionInfo sectionInfo=sectionInfoDAO.getPSectionById(sid);
                    String sname=sectionInfo.getSname();
                    request.setAttribute("uname",uname);
                    request.setAttribute("sname",sname);
                %>
                <td>${uname}</td>
                <td>${sname}</td>
                <td>${topic.treplycount}</td>
                <td>${topic.tclickcount}</td>
                <td>
                    <button class="btn btn-danger btn-xs delete-board-btn">删除</button>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</rapid:override>
<%@ include file="bbs_index.jsp" %>
