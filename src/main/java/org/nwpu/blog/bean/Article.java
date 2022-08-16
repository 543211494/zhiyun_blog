package org.nwpu.blog.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 文章类
 */
@Data
public class Article {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 作者id
     */
    private Integer authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 封面缩略图URL
     */
    private String thumbnail;

    /**
     * 是否删除
     */
    private boolean isDeleted;

    public Article(Integer id, Integer authorId, String title, String content, Date updateTime, Date createTime, String summary, String thumbnail, boolean isDeleted) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.isDeleted = isDeleted;
    }

    public Article() {
    }
}
