
$(function () {
    $(".edit-fuser-btn").click(function () {
        var self = $(this);
        var tr = self.parent().parent();
        var post_id = tr.attr("data-id");
        var highlight = parseInt(tr.attr("data-highlight"));
        var dialog = $("#fuser-dialog");
        dialog.modal("show");
    })
});


$(function () {
    $(".black-fuser-btn").click(function (event) {
        var self = $(this);
        var tr = self.parent().parent();
        var fuser_id = tr.attr('data-id');
        zlalert.alertConfirm({
            "msg":"您确定要删除这个用户吗？",
            'confirmCallback': function () {
                zlajax.post({
                    'url': '/cms/dfuser/',
                    'data':{
                        'fuser_id': fuser_id
                    },
                    'success': function (data) {
                        if(data['code'] == 200){
                            window.location.reload();
                        }else{
                            zlalert.alertInfo(data['message']);
                        }
                    }
                })
            }
        });
    });
});