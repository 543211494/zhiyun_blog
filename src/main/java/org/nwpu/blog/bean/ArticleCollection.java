package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 文章收藏类
 */
@Data
public class ArticleCollection {

    /**
     * 收藏所有者id
     */
    private Integer userId;

    /**
     * 被收藏的文章id
     */
    private Integer articleId;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public ArticleCollection(Integer userId, Integer articleId, Date createTime) {
        this.userId = userId;
        this.articleId = articleId;
        this.createTime = createTime;
    }

    public ArticleCollection() {

    }
}
