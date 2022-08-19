package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
     * 是否通过审核
     */
    private boolean isVisible;

    /**
     * 文章分类
     */
    private String category;

    /**
     * 文章标签
     */
    private List<Tag> tags;

    /**
     * 是否删除
     */
    private boolean isDeleted;

    /**
     * 文章默认封面图片URL
     */
    private static String defaultThumbnail = "/api/imgs/article/default.png";

    /**
     * 文章最大长度
     */
    public final static int MAXLENGTH = 30000;

    public Article(Integer id, Integer authorId, String title, String content,
                   Date updateTime, Date createTime, String summary, String thumbnail,
                   boolean isVisible, boolean isDeleted) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.summary = summary;
        this.thumbnail = thumbnail;
        this.isVisible = isVisible;
        this.isDeleted = isDeleted;
    }

    public Article(Integer authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        Date date = new Date();
        this.updateTime = date;
        this.createTime = date;
        this.thumbnail = Article.defaultThumbnail;
        this.isVisible = false;
        this.isDeleted = false;
    }

    public Article() {
        Date date = new Date();
        this.updateTime = date;
        this.createTime = date;
        this.thumbnail = Article.defaultThumbnail;
        this.isVisible = false;
        this.isDeleted = false;
    }
}
