<%--
  Created by IntelliJ IDEA.
  User: MSII
  Date: 2019/12/24
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"></meta>
    <title>博客注册</title>
    <script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"></link>>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/js/zlajax.js"></script>
    <link rel="stylesheet" href="/sweetalert/sweetalert.css"></link>
    <script src="/sweetalert/sweetalert.min.js"></script>
    <script src="/sweetalert/zlalert.js"></script>
    <%--    <link rel="stylesheet" href="/css/base.css">--%>
    <%--    <script src="/js/base.js"></script>--%>

    <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js"></script>
    <script src="/js/zlparam.js"></script>
    <link rel="stylesheet" href="/css/front_signbase.css"></link>
    <script src="/js/front_signin.js"></script>
    <%--    {% block head %}--%>

    <%--    {% endblock %}--%>
    <%
        if(request.getAttribute("error") == null)
            request.setAttribute("error","");
    %>
</head>
<body>

<div class="outer-box">
    <div class="logo-box">
        <a href="/">
            <img src="/image/logo.png" alt=""></img>
        </a>
    </div>


    <h2 class="page-title">论坛注册</h2>



    <div class="sign-box">
<%--        <form >--%>
            <span style="color: red" id="return-to-span"><%=request.getAttribute("error")%></span>
            <div class="" style="width: 1000px;margin-left: -50px">
                <label for="" class="layui-form-label">
                    <span class="x-red">*</span>选择头像：</label>
<%--                <div class="layui-input-inline">--%>
<%--                    <img src="/image/logo.png" alt="" id="new_user_image" style="width: 200px;margin-left: 30px;">--%>
<%--                    <input type="file" value="选择头像" multiple="multiple" id="file-selector"style="margin-top: 10px;margin-left: 70px" accept="image/jpeg,image/png,image/gif">--%>
<%--                </div>--%>
                <img src="image/head/1.gif"/><input type="radio" name="head" value="1.gif" checked="checked">
                <img src="image/head/2.gif"/><input type="radio" name="head" value="2.gif">
                <img src="image/head/3.gif"/><input type="radio" name="head" value="3.gif">
                <img src="image/head/4.gif"/><input type="radio" name="head" value="4.gif">
                <img src="image/head/5.gif"/><input type="radio" name="head" value="5.gif">
                <BR/>
                <img src="image/head/6.gif"/><input type="radio" name="head" value="6.gif">
                <img src="image/head/7.gif"/><input type="radio" name="head" value="7.gif">
                <img src="image/head/8.gif"/><input type="radio" name="head" value="8.gif">
                <img src="image/head/9.gif"/><input type="radio" name="head" value="9.gif">
                <img src="image/head/10.gif"/><input type="radio" name="head" value="10.gif">
                <BR/>
                <img src="image/head/11.gif"/><input type="radio" name="head" value="11.gif">
                <img src="image/head/12.gif"/><input type="radio" name="head" value="12.gif">
                <img src="image/head/13.gif"/><input type="radio" name="head" value="13.gif">
                <img src="image/head/14.gif"/><input type="radio" name="head" value="14.gif">
                <img src="image/head/15.gif"/><input type="radio" name="head" value="15.gif">

            </div>
            <div class="form-group" style="margin-top: 20px">
                <input type="text" class="form-control" name="uname" id="uname" placeholder="用户名" >
                <span style="color: red" id="erro_name"></span>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="upass" id="upass" placeholder="密码">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="confirm_password" id="confirm_password" placeholder="重复密码">
            </div>
            <div class="checkbox">
                <label style="margin-left: 60px">
                    性别：
                    <input type="radio" name="gender" value="0" checked="checked">男
                    <input type="radio" name="gender" value="1" >女
                </label>
            </div>
            <div class="form-group">
                <%--            <button class="btn btn-warning btn-block" id="submit-btn">立即登录<tton>--%>
<%--                <input type="submit"  value="立即注册"  class="btn btn-warning btn-block" >--%>
                <button class="btn btn-warning btn-block" onclick="send()">立即注册</button>
            </div>
            <div class="form-group">
                <a href="/login.jsp" class="signup-link">返回登录</a>
                <%--<a href="#" class="resetpwd-link" style="float:right;">找回密码</a>--%>
            </div>

    </div>


</div>

</body>

<script type="text/javascript">

    $('#uname').blur(function () {
        var name = document.getElementById("uname").value;
        var pwd = document.getElementById("upass").value;
        var r_pwd = document.getElementById("confirm_password").value;

        $.ajax({
            url:'/regite.jhtml?uname='+name+'&upass='+pwd+'$confirm_password='+r_pwd,
            type:'POST',
            success:function (data) {
                if(data!=false)
                {
                    $('#erro_name').html(data);
                }
            }
        })
    })


    function send() {
        // alert("11111");
        var name = document.getElementById("uname").value;
        var pwd = document.getElementById("upass").value;
        var r_pwd = document.getElementById("confirm_password").value;
        // var gender = document.getElementsByName("gender");
        var gender = $('input[name="gender"]:checked').val();
        var face = $('input[name="head"]:checked').val();

        if(name=="")
        {
            zlalert.alertError("请输入用户名");return ;
        }
        if(pwd=="")
        {
            zlalert.alertError("请输入密码");return ;
        }
        $.ajax({
            type:'POST',
            url: "/regite.jhtml?uname="+name+"&upass="+pwd+"&confirm_password="+r_pwd+"&gender="+gender+'&face='+face,
            success:function(){
                zlalert.alertSuccess("注册用户成功！！！");
                // setTimeout("window.location.reload()",900);
            }

        });
    }
</script>


<script>
    $('#file-selector').change(function(){
        var $file = $(this);
        var fileObj = $file[0];
        var windowURL = window.URL || window.webkitURL;
        var dataURL;
        var $img = $("#new_user_image");
        if (fileObj && fileObj.files && fileObj.files[0]) {
            dataURL = windowURL.createObjectURL(fileObj.files[0]);
            $img.attr('src', dataURL);
        } else {
            dataURL = $file.val();
            var imgObj = document.getElementById("preview");
            imgObj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
            imgObj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = dataURL;
        }
    });



</script>
</html>
