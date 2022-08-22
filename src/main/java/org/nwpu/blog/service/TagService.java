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

    /**
     * 按页查询标签
     * @param currentPage 当前页码
     * @param pageSize 一页的大小
     * @param result 存在查询结果的列表
     * @return 总页数
     */
    public Integer getTags(Integer currentPage,Integer pageSize,List<Tag> result);
}
