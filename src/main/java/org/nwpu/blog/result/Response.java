package org.nwpu.blog.result;

import lombok.Data;

/**
 * @author lzy
 * @date 2022/8/16
 * @param <T> 返回的数据对象
 * 消息回复实体类
 */
@Data
public class Response<T> {

    /**
     * 消息码
     */
    private Integer code;

    /**
     * 消息信息
     */
    private String message;

    /**
     * 消息数据体
     */
    private T data;
}
