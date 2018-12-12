/**
 * create by XuJin
 * @param userName
 * @param password
 */
function login(userName, password) {

    $.ajax({
        method: 'POST',
        url: '/login',
        data: {
            'userName': userName,
            'password': password
        },
        success: function (e) {
            if (e.code == 200) {
                alert("Login successfully!" + " Welcome !");
                window.location.href = '/ad_index';
            }else if(e.code == 201){
                window.location.href = '/ad_dsp_index';
            }else {
                alert(e.message)
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}

$(function () {
    $("#btnSubmit").click(function () {
        var userName = $("#userName").val().trim();
        var password = $("#userPassword").val().trim();
        login(userName, password);
    });

    $("#btnRegister").click(function () {
        //两种跳转方式均可
        // $(location).attr("href", "ad_register.html")
        window.location.href = '/ad_register';
    });
});
