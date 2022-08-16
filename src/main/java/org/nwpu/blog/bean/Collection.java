package org.nwpu.blog.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 文章收藏类
 */
@Data
public class Collection {

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
    private Date createTime;

    public Collection(Integer userId, Integer articleId, Date createTime) {
        this.userId = userId;
        this.articleId = articleId;
        this.createTime = createTime;
    }

    public Collection() {

    }
}
