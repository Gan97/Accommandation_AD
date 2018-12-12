$(function () {
    /**
     * 初始化广告列表
     */
    initADList();

    /**
     * 提交图片
     */
    $("#ssi-upload").ssi_uploader({url: '/dsp/upload', maxFileSize: 1, allowed: ['jpg', 'gif', 'txt', 'png', 'pdf']});
    /**
     * 提交新的广告信息
     */
    $("#submit").click(function () {
        addADInfo();
    });
});

/**
 * 添加新的广告信息
 * @param ADInfo
 */
function addADInfo() {
    var ADInfo = {};
    var ADTitle = $("#ad_title").val().trim();
    var SelectCrowd = $("#peopleSelect").find("option:selected").text().trim();
    var SelectGender = $("#sexSelect").find("option:selected").text().trim();
    var pictureName = $("table.ssi-imgToUploadTable tbody tr td").last().text().trim();
    //获取AD类别
    //---------------------------–-----–--–-–--–-–----------
    var ADClasses = {};
    var ADClassesList = $("#ClassesOption").find("input");
    var ADClassesSelected = [];
    ADClassesList.each(function () {
        if ($(this).is(":checked")) {
            ADClassesSelected.push(this.nextSibling.nodeValue);
        }
    });
    ADClasses["ADClassesSelected"] = ADClassesSelected;
    //---------------------------–-----–--–-–--–-–----------
    var ADDescribe = $("#comment").val().trim();
    var RTBPrice = $("#rtbPrice").val().trim();
    //格式化请求数据
    ADInfo["ADID"] = null;
    ADInfo["ADTitle"] = ADTitle;
    ADInfo["SelectCrowd"] = SelectCrowd;
    ADInfo["SelectGender"] = SelectGender;
    // console.log("pictureName：" + pictureName);
    ADInfo["UploadPicture"] = pictureName;
    ADInfo["ADClasses"] = JSON.stringify(ADClasses);
    ADInfo["ADDescribe"] = ADDescribe;
    ADInfo["RTBPrice"] = RTBPrice;
    // console.log(ADInfo);
    $.ajax({
        method: "post",
        url: "/dsp/addADInfo",
        contentType: "application/json",
        data: JSON.stringify(ADInfo),
        success: function (e) {
            if (e.code == 200) {
                alert(e.message);
                window.location.reload(true);
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
 * 初始化广告列表
 */
function initADList() {
    $.getJSON("/dsp/InitADInformation", function (data) {
        var tbody = $("#ad_list");
        var num = 0;
        for (var i in data) {
            num++;
            tbody.append(
                '<tr>' +
                '<td align="center">' + num + '</td>' +
                '<td align="center" style="width:200px">' + data[i]["adtitle"] + '</td>' +
                '<td align="center" style="width:200px">' + JSON.parse(data[i]["adclasses"])["ADClassesSelected"] + '</td>' +
                '<td align="center">' + data[i]["addescribe"] + '</td>' +
                '<td align="center" style="width:200px">' + data[i]["rtbprice"] + '</td>' +
                '<td style="width:200px">' +
                '<div class="operation text-center">' +
                '<!--编辑广告部分内容-->' +
                '   <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#edit_' + i + '">编辑 </button>' +
                '<div class="modal fade text-left" id="edit_' + i + '">' +
                '<!--在modal-dialog后面设置模态框大小 modal-lg modal-sm-->' +
                '<div class="modal-dialog modal-lg">' +
                '<div class="modal-content">' +
                '<!--模态框头部-->' +
                '<div class="modal-header">' +
                '<h4 class="modal-title">Edit AD Information</h4>' +
                '</div>' +
                '<!--模态框主体-->' +
                '<div class="modal-body" style="width: 100%">' +
                '<div class="register_form">' +
                '<div class="form-group">' +
                '<label class="control-label">AD Title</label>' +
                '<input id="ad_title_' + data[i]["adid"] + '" required="required" type="text" class="form-control"' +
                'placeholder="' + data[i]["adtitle"] + '"/>' +
                '</div>' +
                '<div class="form-group">' +
                '<label class="control-label">AD Classes</label>' +
                '<div id="editClassesOption_' + data[i]["adid"] + '">' +
                //wait init
                '</div>' +
                '</div>' +
                '<div class="form-group">' +
                '<label class="control-label">AD Describe</label>' +
                '<textarea id="comment_' + data[i]["adid"] + '" type="text" class="form-control" rows="5">' + data[i]["addescribe"] + '</textarea>' +
                '</div>' +
                '<div class="form-group">' +
                '<label class="control-label">RTB Price</label>' +
                '<input id="rtbPrice_' + data[i]["adid"] + '" class="form-control input-lg" type="text" name="cat_add"' +
                'value=""' +
                'placeholder="' + data[i]["rtbprice"] + '"/>' +
                '</div>' +
                '<input type="submit" value="complete"' +
                'class="float-right bg-success text-white" id="' + data[i]["adid"] + '" onclick="updateADInfo(this)"/>' +
                '<!--<button type="button" class="btn btn-primary" id="register">Register</button>-->' +
                '</div>' +
                '</div>' +
                '<!--模态框底部-->' +
                '<div class="modal-footer">' +
                '<button type="button" class="btn btn-secondary" data-dismiss="modal">' +
                '关闭' +
                '</button>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<button type="button" class="btn btn-sm btn-danger" id="delete_' + data[i]["adid"] + '" onclick="deleteADInfo(this)">删除</button>' +
                '</div>' +
                '</td>' +
                '</tr>'
            );
            editClassesOption("#editClassesOption_" + data[i]["adid"]);

        }
    });
    $.getJSON("/profession/initprofession", function (data) {
        var ClassesOption = $("#ClassesOption");
        for (var i in data) {
            var content =
                '<div class="form-check form-check-inline">' +
                '<label class="form-check-label">' +
                '<input type="checkbox" class="form-check-input" value="' + data[i].professionId + '"/>' + data[i].professionName +
                '</label>' +
                '</div>';
            ClassesOption.append(content);
        }
    });
}

/**
 * 初始化编辑的profession选择框
 * @param editnum
 */
function editClassesOption(editnum) {
    var ClassesOption = $(editnum);
    $.getJSON("/profession/initprofession", function (data) {
        for (var i in data) {
            var content =
                '<div class="form-check form-check-inline">' +
                '<label class="form-check-label">' +
                '<input type="checkbox" class="form-check-input" value="' + data[i].professionId + '"/>' + data[i].professionName +
                '</label>' +
                '</div>';
            ClassesOption.append(content);
        }
    });
}

/**
 * 更新广告信息
 * @param obj
 */
function updateADInfo(obj) {
    //$(obj).prop('tagName')打印当前标签name
    var i = $(obj).attr("id");
    var ADInfo = {};
    var ADTitle = $("#ad_title_" + i).val().trim();
    //获取AD类别
    //---------------------------–-----–--–-–--–-–----------
    var ADClasses = {};
    var ADClassesList = $("#editClassesOption_" + i).find("input");
    var ADClassesSelected = [];
    ADClassesList.each(function () {
        if ($(this).is(":checked")) {
            ADClassesSelected.push(this.nextSibling.nodeValue);
        }
    });
    ADClasses["ADClassesSelected"] = ADClassesSelected;
    //---------------------------–-----–--–-–--–-–----------
    var ADDescribe = $("#comment_" + i).val().trim();
    var RTBPrice = $("#rtbPrice_" + i).val().trim();
    //格式化请求数据
    ADInfo["ADID"] = i;
    ADInfo["ADTitle"] = ADTitle;
    ADInfo["SelectCrowd"] = null;
    ADInfo["SelectGender"] = null;
    ADInfo["UploadPicture"] = null;
    ADInfo["ADClasses"] = JSON.stringify(ADClasses);
    ADInfo["ADDescribe"] = ADDescribe;
    ADInfo["RTBPrice"] = RTBPrice;
    $.ajax({
        method: "post",
        url: "/dsp/updateADInfo",
        contentType: "application/json",
        data: JSON.stringify(ADInfo),
        success: function (e) {
            if (e.code == 200) {
                alert(e.message);
                window.location.reload(true);
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
 * 删除广告信息
 * @param obj
 */
function deleteADInfo(obj) {
    // var ADID = $(obj).parent().parent().siblings().first().text();
    var ADID = $(obj).attr("id").split('_')[1];
    // alert(ADID);
    $(obj).parent().parent().parent().remove();
    $.ajax({
        method: "post",
        url: "/dsp/deleteADInfo",
        data: {
            "ADID": ADID
        },
        success: function (e) {
            if (e.code == 200) {
                alert(e.message);
                window.location.reload(true);
            } else {
                alert(e.message);
            }
        },
        error: function () {
            alert("Error!");
        }
    });
}
