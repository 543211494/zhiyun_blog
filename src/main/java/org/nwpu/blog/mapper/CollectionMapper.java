package org.nwpu.blog.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.ArticleCollection;

@Mapper
public interface CollectionMapper {

    /**
     * 新增收藏
     * @param collection 要新增的收藏实例
     * @return 操作结果
     */
    public boolean insertCollection(@Param("collection") ArticleCollection collection);
}
