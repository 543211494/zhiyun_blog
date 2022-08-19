package org.nwpu.blog.service;

import org.nwpu.blog.bean.Tag;

import java.util.List;

public interface TagService {
    /**
     * 批量无重复插入标签
     * @param tags 要插入的标签
     * @return 插入标签的id列表
     */
    public List<Integer> addTags(Tag[] tags);
}
