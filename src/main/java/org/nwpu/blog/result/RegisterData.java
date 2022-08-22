package org.nwpu.blog.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 用户注册工具类
 * @author lzy
 * @date 2022/8/22
 */
@Data
public class RegisterData {

    /**
     * 注册日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date date;

    /**
     * 注册数量
     */
    private Integer count;

    public RegisterData(Date date, Integer count) {
        this.date = date;
        this.count = count;
    }

    public RegisterData() {

    }
}
