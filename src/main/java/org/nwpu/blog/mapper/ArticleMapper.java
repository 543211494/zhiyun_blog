package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 新增文章
     * @param article 要新增的文章
     * @return 操作结果
     */
    public boolean insertArticle(Article article);

    /**
     * 更新一个文章的分类(insert关联表)
     * @param articleId 文章id
     * @param categoryId 分类id
     * @return 操作结果
     */
    public boolean updateArticleCategory(@Param("articleId") Integer articleId,@Param("categoryId") Integer categoryId);

    /**
     * 更新一个文章的标签(insert关联表)
     * @param articleId 文章id
     * @param tagIds 标签id列表
     * @return 操作结果
     */
    public boolean updateArticleTags(@Param("articleId")Integer articleId,@Param("tagIds") List<Integer> tagIds);

    /**
     * 更新文章
     * @param article 文章实例
     * @return 操作结果
     */
    public boolean updateArticle(@Param("article") Article article);

    /**
     * 根据文章id删除一个文章的所有分类(关联表)
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean deleteArticleCategoryById(@Param("articleId") Integer articleId);

    /**
     * 根据文章id删除一个文章的所有标签(关联表)
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean deleteArticleTagsById(@Param("articleId") Integer articleId);

    /**
     * 根据文章id查询文章
     * @param articleId 文章id
     * @param searchAll 是否查询全部文章(包括被删除的)
     * @param isDetail 是否查询详细信息,即是否包括tags和category
     * @return 查询结果
     */
    public Article searchArticleById(@Param("articleId") Integer articleId,@Param("searchAll") boolean searchAll,@Param("isDetail") boolean isDetail);

    /**
     * 根据文章id删除文章
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean deleteArticleById(@Param("articleId") Integer articleId);

    /**
     * 根据文章id审核通过文章
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean updateArticleVisibleById(@Param("articleId") Integer articleId);

    /**
     * 给文章打分
     * @param userId 评分用户id
     * @param articleId 文章id
     * @param score 分数
     * @return 操作结果
     */
    public boolean scoreArticle(@Param("userId") Integer userId,@Param("articleId") Integer articleId,@Param("score") Integer score);

    /**
     * 更新文章封面路径
     * @param thumbnail 文章封面路径
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean updateThumbnail(@Param("thumbnail")String thumbnail,@Param("articleId")Integer articleId);
}
