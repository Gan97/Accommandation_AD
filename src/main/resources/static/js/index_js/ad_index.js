/**
 * 回车事件调用搜索
 * @param ev
 * @constructor
 */
function Search(ev) {
    if (ev.keyCode === 13) {
        ev.preventDefault();
        var search = $('#main_search');
        var search_word = search.val();
        search.val('');
        get_search(search_word);
    }
}

/**
 * 获取搜索结果
 * @param keyword
 */
function get_search(keyword) {
    $('#content_list').empty();
    recommend_AD(keyword);
    $.ajax({
        type: 'POST',
        url: '/index/get_search',
        data: {
            keyword: keyword,
            PrePageNum: 2
        },
        success: function (data) {
            for (var i in data) {
                var ss = $.parseJSON(data[i]);
                for (var k in ss) {
                    addLink(k, ss[k]);
                }
            }
        }
    });
}

/**
 * 将搜索结果展示成链接形式
 * @param name
 * @param link
 */
function addLink(name, link) {
    $('#content_list').append('<li><span onclick="click_span(this)" id=' + link + '>' + name + '</span></li>');
}

/**
 * 点击搜索结果链接，iframe跳转页面
 * @param obj
 */
function click_span(obj) {
    var link = obj.id;
    console.log(link);
    $('#view_box').attr("src", link);
}

/**
 * 获取推荐广告
 */
function recommend_AD(keyword) {
    console.log('----------------');
    $.ajax({
        type: 'GET',
        url: '/index/getRecommendAD',
        data: {
            'keyword': keyword
        },
        success: function (data) {
            $("#ad_img").attr("src", data['picture']);
            $("#ad_describe p").text(data['describe']);
        }
    });

}

/**
 * 页面加载完成后运行函数
 */
$(function () {
    recommend_AD("");
});
