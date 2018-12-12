# -*- coding:utf-8 -*-
import gensim
import sys


def similarity(list1, list2):
    model = gensim.models.Word2Vec.load(
        '/Users/xujin/Desktop/毕业设计/implement_code/Accommandation_AD/src/main/java/com/xujin/ad_sender/py/word2vec.modal')
    return model.n_similarity(list1, list2)


# 测试
# model = gensim.models.Word2Vec.load(
#     '/Users/xujin/Desktop/毕业设计/implement_code/Accommandation_AD/src/main/java/com/xujin/ad_sender/py/word2vec.modal')
# print(model.wv['computer'])
# print(similarity(['篮球', '电影', '排球'], ['IT服务']))
# print(eval("[计算机软件, 篮球, 电影, 排球]"))
# print(model.wv[u'学校'])
if __name__ == '__main__':
    # eval将str转换成有效的Python表达式，例如将 "['women', 'girl']" 转化为 ['women', 'girl']
    similar = similarity(eval(sys.argv[1]), eval(sys.argv[2]))
    print(similar)
