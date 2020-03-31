
$(function () {
    $("#save-banner-btn").click(function (event) {
        event.preventDefault();
        var self = $(this);
        var dialog = $("#banner-dialog");
        var nameInput = $("input[name='name']");
        var imageInput = $("input[name='image_url']");
        var linkInput = $("input[name='link_url']");
        var priorityInput = $("input[name='priority']");


        var name = nameInput.val();
        var image_url = imageInput.val();
        var link_url = linkInput.val();
        var priority = priorityInput.val();
        var submitType = self.attr('data-type'); //获取data-type
        var bannerId = self.attr("data-id");

        if(!name || !image_url || !link_url || !priority){
            zlalert.alertInfoToast('请输入完整的轮播图数据！');
            return;
        }

         var url = '';
        if(submitType == 'update'){
            url = '/cms/ubanner/';
        }else{
            url = '/cms/abanner/';
        }

        zlajax.post({
            "url": url,
            'data':{
                'name':name,
                'image_url': image_url,
                'link_url': link_url,
                'priority':priority,
                'banner_id': bannerId
            },
            'success': function (data) {
                dialog.modal("hide");
                if(data['code'] == 200){
                    // 重新加载这个页面  更新数据在下方
                    window.location.reload();
                }else{
                    zlalert.alertInfo(data['message']);
                }
            },
            'fail': function () {
                zlalert.alertNetworkError();
            }
        });
    });
});

$(function () {
    $(".edit-banner-btn").click(function (event) {
        var self = $(this);
        var dialog = $("#banner-dialog");
        dialog.modal("show");

        var tr = self.parent().parent();
        var name = tr.attr("data-name");
        var image_url = tr.attr("data-image");
        var link_url = tr.attr("data-link");
        var priority = tr.attr("data-priority");

        var nameInput = dialog.find("input[name='name']");  //也可以直接获取属性 但这样在对话框里查看 会比较快
        var imageInput = dialog.find("input[name='image_url']");
        var linkInput = dialog.find("input[name='link_url']");
        var priorityInput = dialog.find("input[name='priority']");
        var saveBtn = dialog.find("#save-banner-btn");

        nameInput.val(name);
        imageInput.val(image_url);
        linkInput.val(link_url);
        priorityInput.val(priority);
        saveBtn.attr("data-type",'update'); //绑定一个data-type属性
        saveBtn.attr('data-id',tr.attr('data-id'));//绑定一个data-id属性
    });
});

$(function () {
    $(".delete-banner-btn").click(function (event) {
        var self = $(this);
        var tr = self.parent().parent();
        var banner_id = tr.attr('data-id');
        zlalert.alertConfirm({
            "msg":"您确定要删除这个轮播图吗？",
            'confirmCallback': function () {
                zlajax.post({
                    'url': '/cms/dbanner/',
                    'data':{
                        'banner_id': banner_id
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

$(function () {
    zlqiniu.setUp({
        'domain': 'http://pv8e9g6zw.bkt.clouddn.com',
        'browse_btn': 'upload-btn', // 按钮的ID
        'uptoken_url': '/c/uptoken/', // URL
        'success': function (up,file,info) {
            var imageInput = $("input[name='image_url']");
            imageInput.val(file.name);  //设置到对话框中
        }
    });
});