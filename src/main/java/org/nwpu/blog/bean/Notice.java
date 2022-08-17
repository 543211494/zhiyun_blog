package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 公告类
 */
@Data
public class Notice {

    /**
     * 公告id
     */
    private Integer id;

    /**
     * 公告发布者id
     */
    private Integer publisherId;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告优先级，1紧急公告，2普通公告
     */
    private Integer order;

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

    public Notice(Integer id, Integer publisherId, String content, Integer order, Date updateTime, Date createTime, boolean isDeleted) {
        this.id = id;
        this.publisherId = publisherId;
        this.content = content;
        this.order = order;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.isDeleted = isDeleted;
    }

    public Notice() {

    }
}
