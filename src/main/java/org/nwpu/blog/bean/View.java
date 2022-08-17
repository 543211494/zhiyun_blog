package org.nwpu.blog.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @author lzy
 * @date 2022/8/16
 * 文章浏览量类
 */
@Data
public class View {

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 日期,精确到天
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    /**
     * 浏览量
     */
    private Integer count;

    public View(Integer articleId, Date date, Integer count) {
        this.articleId = articleId;
        this.date = date;
        this.count = count;
    }

    public View() {

    }
}
