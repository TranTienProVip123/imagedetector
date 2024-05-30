$(function () {
    $('#btn_save').click(function (e) {
        //Prevent default submission of form
        e.preventDefault();
        $.post({
            url: _ctx + 'user-profile/save',
            data: $('#frm_user_profile').serialize(),
            success: function (res) {
                if (res.validated) {
                    //take your successful action here; you may want to redirect to another page
                    alert("Registration Successful");
                } else {
                    alert("Error");
                }
            }
        })
    });
});
