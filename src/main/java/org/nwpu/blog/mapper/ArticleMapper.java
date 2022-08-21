package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.Score;
import org.nwpu.blog.bean.View;

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
     * @param searchDelete true查询被删除的文章,false查询全部(删除和未删除)文章
     * @param searchPass true查询通过审核的文章，false查询全部(审核通过和未通过)的文章
     * @param isDetail 是否查询详细信息,即是否包括tags和category
     * @return 查询结果
     */
    public Article searchArticleById(@Param("articleId") Integer articleId,@Param("searchDelete") boolean searchDelete,@Param("searchPass")boolean searchPass,@Param("isDetail") boolean isDetail);

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

    /**
     * 按页查询一个用户发布的所有文章
     * @param authorId 作者id
     * @return 查询结果
     */
    public List<Article> listArticlesByAuthorId(@Param("authorId") Integer authorId);

    /**
     * 根据文章id查询评分
     * @param articles 要查询评分的文章列表
     * @return
     */
    public List<Score> listScoresByArticleId(@Param("articles") List<Article> articles);

    /**
     * 根据文章id查询指定文章的平均得分
     * @param articleId 文章id
     * @return 查询结果
     */
    public Score searchScoreByArticleId(@Param("articleId")Integer articleId);

    /**
     * 查询一个用户的全部收藏文章
     * @param userId 用户id
     * @return 查询结果
     */
    public List<Article> listCollectionsByUserId(@Param("userId") Integer userId);

    /**
     * 根据文章id查询文章阅读量
     * @param articles 文章id列表
     * @return
     */
    public List<View> listViewsByArticleId(@Param("articles") List<Article> articles);

    /**
     * 查询一个用户发布文章的数目
     * @param authorId 用户id
     * @return 文章数目
     */
    public Integer countArticleByAuthorId(@Param("authorId")Integer authorId);

    /**
     * 查询一个作者文章的总阅读量
     * @param authorId 用户id
     * @return 文章总阅读量
     */
    public Integer countViewsByAuthorId(@Param("authorId")Integer authorId);

    /**
     * 根据分类查询文章
     * @param category 分类名称，若为null查询全部
     * @param isPassed true表示查询通过审核的，false表示查询未通过审核的
     * @return 文章列表
     */
    public List<Article> listArticlesByCategory(@Param("category") String category,@Param("isPassed") boolean isPassed);

    /**
     * 根据标签名称查询文章
     * @param tag 标签名称，若为null查询全部
     * @param isPassed true表示查询通过审核的，false表示查询全部
     * @return 文章列表
     */
    public List<Article> listArticlesByTag(@Param("tag")String tag,@Param("isPassed")boolean isPassed);

    /**
     * 根据文章标题查询文章
     * @param title 文章标题，若为null查询全部
     * @param isPassed true表示查询通过审核的，false表示查询全部
     * @return 文章列表
     */
    public List<Article> listArticleByTitle(@Param("title")String title,@Param("isPassed")boolean isPassed);
}
