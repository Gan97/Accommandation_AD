$(function () {
    init();
    $("#register").click(function () {
        var UserName = $("#UserName").val().trim();
        var PassWord = $("#pwd").val().trim();
        //获取选中的文本
        // var Profession = $("#Profession").find("option:selected").text().trim();
        // 获取选中的value
        var Profession = $("#Profession").val().trim();
        var Sex = $("#Sex").val().trim();
        var Age = $("#Age").val().trim();
        var Hobby = $("#Hobby").val().trim();
        //获取选择中爱好项
        //————————————————————————————————————————————————————
        var HobbyOptionList = $("#HobbyOption").find("input");
        var HobbyJson = {};
        var HobbySelected = [];
        HobbySelected.push(Hobby);
        HobbyOptionList.each(function () {
            if ($(this).is(":checked")) {
                HobbySelected.push(this.nextSibling.nodeValue);
            }
        });
        HobbyJson["HobbySelected"] = HobbySelected;
        //————————————————————————————————————————————————————
        //进行ajax请求
        Register(UserName, PassWord, Profession, Sex, Age, HobbyJson);
    });
});

/**
 * 注册请求
 * @param UserName
 * @param PassWord
 * @param Profession
 * @param Sex
 * @param Age
 * @param HobbyJson
 * @constructor
 */
function Register(UserName, PassWord, Profession, Sex, Age, HobbyJson) {
    $.ajax({
        method: 'POST',
        url: '/Register/register',
        data: {
            'UserName': UserName,
            'PassWord': PassWord,
            'Profession': Profession,
            'Sex': Sex,
            'Age': Age,
            'Hobby': JSON.stringify(HobbyJson)
        },
        success: function (e) {
            if (e.code == 200) {
                window.location.href = "/login";
                alert(e.message);
            } else {
                alert(e.message);
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}

/**
 * 初始化注册页面的选择项
 */
function init() {
    initSelectProfession();
    initSelectHobby();
}

/**
 * 初始化职业选择项
 */
function initSelectProfession() {
    $.getJSON("/profession/initprofession", function (data) {
        var ProfessionList = $("#Profession");
        for (var i in data) {
            ProfessionList.append(new Option(data[i].professionName, data[i].professionId));
        }
    });
}

/**
 * 初始化爱好选择项
 */
function initSelectHobby() {
    $.getJSON("/hobby/inithobby", function (data) {
        var HobbyOption = $("#HobbyOption");
        for (var i in data) {
            HobbyOption.append(
                '<div class="form-check form-check-inline">' +
                '<label class="form-check-label">' +
                '<input type="checkbox" class="form-check-input" value="' + data[i].hobbyID + '"/>' + data[i].hobbyName +
                '</label>' +
                '</div>'
            );
        }
    });
}