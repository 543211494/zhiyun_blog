package org.nwpu.blog.result;

import lombok.Data;

/**
 * 词云类
 * @author lzy
 * @date 2022/8/22
 */
@Data
public class WordCloud {

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签出现次数
     */
    private Integer count = 0;

    public WordCloud(Integer tagId, String tagName, Integer count) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.count = count;
    }

    public WordCloud() {
    }
}
