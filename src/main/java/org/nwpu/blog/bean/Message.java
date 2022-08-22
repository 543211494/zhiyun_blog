package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lzy
 * @date 2022/8/16
 * 留言类
 */
@Data
public class Message {

    /**
     * 留言id
     */
    private Integer id;

    /**
     * 父留言id
     */
    private Integer pid;

    /**
     * 留言作者id
     */
    private Integer authorId;

    /**
     * 留言内容
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

    /**
     * 子评论
     */
    private List<Message> child;

    public Message(Integer id, Integer pid, Integer authorId, String content, Date createTime, boolean isDeleted) {
        this.id = id;
        this.pid = pid;
        this.authorId = authorId;
        this.content = content;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
    }

    public Message(Integer authorId, String content) {
        Date date = new Date();
        this.authorId = authorId;
        this.content = content;
        this.createTime = date;
        this.isDeleted = false;
    }

    public Message(Integer pid, Integer authorId, String content) {
        Date date = new Date();
        this.pid = pid;
        this.authorId = authorId;
        this.content = content;
        this.createTime = date;
        this.isDeleted = false;
    }

    public Message() {

    }
}
