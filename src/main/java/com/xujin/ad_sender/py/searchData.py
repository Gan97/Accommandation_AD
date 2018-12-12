# -*- coding:utf-8 -*-
from urllib import request
from urllib.parse import quote
from lxml import etree
import ssl
import sys

# url_basic = 'https://www.baidu.com/s?wd=%s&pn=%s'
_context = ssl._create_unverified_context()


def get_html(url, search_item, page_num):
    # 这里的quote解决含中文字符的url
    req = request.Request(url % (quote(search_item), (page_num - 1) * 10))
    req.add_header('User-Agent',
                   'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.162 '
                   'Safari/537.36')
    response = request.urlopen(req, context=_context)
    # 解决windows乱码问题
    # html = response.read().decode('utf-8').encode('GBK', 'ignore').decode('GBK')
    html = response.read().decode('utf-8')
    with open('./search_page_%s.html' % page_num, 'w') as f:
        f.write(html)


def down_search(keyword, PrePageNum):
    for i in range(1, PrePageNum + 1):
        get_html('https://www.baidu.com/s?wd=%s&pn=%s', keyword, i)


def return_search_list(keyword, PrePageNum):
    down_search(keyword, PrePageNum)
    with open('./search_page_1.html') as f1, open('./search_page_2.html') as f2:
        html1 = f1.read()
        html2 = f2.read()
    html = html1 + html2
    page = etree.HTML(html)
    result_list = page.xpath('//div[contains(@class,"c-container")]')
    item_list = []
    for i in result_list:
        result = etree.HTML(etree.tostring(i))
        data = result.xpath('//h3[contains(@class,"t")]/a[1]')
        key = data[0].xpath('string(.)').strip()
        value = data[0].xpath('@href')
        item_list.append({key: value[0]})
    return item_list


if __name__ == '__main__':
    search_list = return_search_list(sys.argv[1], int(sys.argv[2]))
    print(search_list)
