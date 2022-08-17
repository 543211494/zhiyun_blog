package org.nwpu.blog.util;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author lzy
 * @date 2022/8/17
 * JSON序列化工具类
 */
public class JSON {

    public static String toString(Object object){
        return com.alibaba.fastjson.JSON.toJSONString(object, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }
}
