package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 文章评论类
 */
@Data
public class Comment {

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 父评论id
     */
    private Integer pid;

    /**
     * 被评论文章的id
     */
    private Integer articleId;

    /**
     * 评论作者id
     */
    private Integer authorId;

    /**
     * 父评论作者id
     */
    private Integer userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 是否删除
     */
    private boolean isDeleted;

    public Comment(Integer id, Integer pid, Integer articleId, Integer authorId, Integer userId, String content, Date createTime, boolean isDeleted) {
        this.id = id;
        this.pid = pid;
        this.articleId = articleId;
        this.authorId = authorId;
        this.userId = userId;
        this.content = content;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
    }

    public Comment() {

    }
}
