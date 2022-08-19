package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 分类类
 */
@Data
public class Category {


    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

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
     * 是否删除
     */
    private boolean isDeleted;

    public Category(Integer id, String name, String description, Date updateTime, Date createTime, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
    }

    public Category() {
        Date date = new Date();
        this.updateTime = date;
        this.createTime = date;
        this.isDeleted = false;
    }
}
