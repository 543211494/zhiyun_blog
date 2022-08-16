package org.nwpu.blog.bean;

import lombok.Data;

import java.util.Date;

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
    private Date createTime;

    /**
     * 是否删除
     */
    private boolean isDeleted;

    public Message(Integer id, Integer pid, Integer authorId, String content, Date createTime, boolean isDeleted) {
        this.id = id;
        this.pid = pid;
        this.authorId = authorId;
        this.content = content;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
    }

    public Message() {

    }
}