package org.example.springboot.biz.utils;

import com.hankcs.hanlp.HanLP;

import java.util.List;

public class LabelUtils {
    /**
     * 根据文章内容生成文章标签
     *
     * @param content 文章内容
     * @return 标签列表
     */
    public static List<String> generateLabelList(String content) {
        return HanLP.extractKeyword(content, 8);
    }
}
