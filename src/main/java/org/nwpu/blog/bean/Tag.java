package org.nwpu.blog.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 标签类
 */
@Data
public class Tag {

    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签描述
     */
    private String description;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除
     */
    private boolean isDeleted;

    public Tag(Integer id, String name, String description, Date updateTime, Date createTime, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
    }

    public Tag() {

    }
}
