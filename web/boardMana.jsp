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
                var board_id = tr.attr('data-id');
                zlalert.alertConfirm({
                    "msg":"您确定要删除这个版块吗？",
                    'confirmCallback': function () {
                        $.ajax({
                            url: '/delBoard.jhtml?id='+board_id,
                            success: function () {
                                zlalert.alertSuccess("删除成功！");
                                setTimeout("window.location.reload()",900);
                            }
                        })
                    }
                });
            });
            $(".edit-board-btn").click(function () {
                var self = $(this);
                var tr = self.parent().parent();
                var name = tr.attr('data-name');
                var board_id = tr.attr("data-id");

                zlalert.alertOneInput({
                    'text': '请输入新的板块名称！',
                    'placeholder': name,
                    'confirmCallback': function (inputValue) {
                        zlajax.post({
                            'url': '/updateBoard.jhtml',
                            'data': {
                                'board_id': board_id,
                                'name': inputValue
                            },
                            'success': function () {
                                zlalert.alertSuccess("修改成功！");
                                setTimeout("window.location.reload()",900);
                            }
                        });
                    }
                });
            });

        });
    </script>
    <%
        SectionInfoDAO sectionDao=new SectionInfoDAO();
    %>
    <div class="top-box" >
        <button class="btn btn-warning" style="float:right;" id="add-board-btn" onclick="xadmin.open('添加新版块','/addBoard.jsp',800,600)">添加新板块</button>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>板块ID</th>
            <th>板块名称</th>
            <th>帖子数量</th>
            <th>父板块ID</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<SectionInfo> listPSection = sectionDao.getPSection();
            request.setAttribute("listPSection",listPSection);
        %>
        <c:forEach items="${listPSection}" var="Psection">
            <tr data-name="${Psection.sname}" data-id="${Psection.sid}" class="danger">
                <td>${Psection.sid}</td>
                <td>${Psection.sname}</td>
                <td>${Psection.stopiccount}</td>
                <td>${Psection.sparentid}</td>
                <td>
                    <button class="btn btn-default btn-xs edit-board-btn">编辑</button>
                    <button class="btn btn-danger btn-xs delete-board-btn">删除</button>
                </td>
            </tr>
            <c:set var="pid" value="${Psection.sid}" scope="request"/>
            <%
                int id= (int) request.getAttribute("pid");
                List<SectionInfo> listSection = sectionDao.getSectionById(id);
                request.setAttribute("listSection",listSection);
            %>
            <c:forEach items="${listSection}" var="section">
                <tr data-name="${section.sname}" data-id="${section.sid}">
                    <td>${section.sid}</td>
                    <td>${section.sname}</td>
                    <td>${section.stopiccount}</td>
                    <td>${section.sparentid}</td>
                    <td>
                        <button class="btn btn-default btn-xs edit-board-btn">编辑</button>
                        <button class="btn btn-danger btn-xs delete-board-btn">删除</button>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>

        </tbody>
    </table>

</rapid:override>
<%@ include file="bbs_index.jsp" %>
