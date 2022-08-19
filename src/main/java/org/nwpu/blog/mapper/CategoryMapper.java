package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Category;

@Mapper
public interface CategoryMapper {

    /**
     * 插入一条新分类
     * @param category 要插入的分类实例
     * @return 操作结果
     */
    public boolean insertCategory(Category category);

    /**
     * 根据分类名称获取分类
     * @param name 分类名称
     * @return 获取的分类
     */
    public Category getCategoryByName(@Param("name") String name);
}
