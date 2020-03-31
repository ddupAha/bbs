
// $(function () {
//     var ue = UE.getEditor("editor",{
//         'serverUrl': '/ueditor/upload/',
//         "toolbars": [
//             [
//                 'undo', //撤销
//                 'redo', //重做
//                 'bold', //加粗
//                 'italic', //斜体
//                 'source', //源代码
//                 'blockquote', //引用
//                 'selectall', //全选
//                 'insertcode', //代码语言
//                 'fontfamily', //字体
//                 'fontsize', //字号
//                 'simpleupload', //单图上传
//                 'emotion' //表情
//             ]
//         ]
//     });
//     window.ue = ue;
// });



$(function () {
    $("#comment-btn").click(function (event) {
        event.preventDefault();
        var loginTag = $("#login-tag").attr("data-is-login"); //获取login-tag 判断有没有登录
        // alert(loginTag);
        if(loginTag !=1){
            window.location = '/login.jsp';
        }else{
            var content = document.getElementById("post_content").val;
            if(content == ""){
            zlalert.alertInfoToast('评论内容不能为空！');
            return;
        }
            // var post_id = $("#post-content").attr("data-id");
            $.ajax({
                type:'POST',
                url: '/post.jhtml',
                data:{
                    'content': content,
                },
                success: function (data) {
                        zlalert.alertSuccessToast('评论成功！');
                        setTimeout("window.location.reload()",900);
                    }
            });
        }
    });
});