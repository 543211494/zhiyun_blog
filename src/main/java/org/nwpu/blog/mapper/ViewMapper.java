package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.View;

@Mapper
public interface ViewMapper {

    /**
     * 增加文章阅读量
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean addArticleView(@Param("articleId") Integer articleId);

    /**
     * 查询一篇文章的总阅读量
     * @param articleId 文章id
     * @return 总阅读量
     */
    public Integer searchTotalViewByArticleId(@Param("articleId") Integer articleId);
}
