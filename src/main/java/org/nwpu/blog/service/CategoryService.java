package org.nwpu.blog.service;

import org.nwpu.blog.bean.Category;

public interface CategoryService {

    /**
     * 新增一个分类，若分类已存在则不insert
     * @param category 要新增的分类
     * @return insert成功则返回新增分类的id，insert失败则返回已经存在的分类id
     */
    public Integer addCategory(Category category);
}
