package org.nwpu.blog.service;

import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.Score;
import org.nwpu.blog.bean.View;

import java.util.List;

public interface ArticleService {

    /**
     * 新增一篇文章
     * @param article 要新增的文章
     * @return 新增文章的主键
     */
    public Integer addArticle(Article article);

    /**
     * 更新文章分类关联表
     * @param articleId 要插入的文章id
     * @param categoryId 要插入的分类id
     * @return 操作结果
     */
    public boolean addArticleCategory(Integer articleId,Integer categoryId);

    /**
     * 更新文章标签关联表
     * @param articleId 要插入的文章id
     * @param tagIds 要插入的tagId列表
     * @return 操作结果
     */
    public boolean updateArticleTags(Integer articleId,List<Integer> tagIds);

    /**
     * 更新文章
     * @param article 要更新的文章实例
     * @return 操作结果
     */
    public boolean updateArticle(Article article);

    /**
     * 根据文章id删除一个文章所有的分类(关联表)
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean deleteArticleCategoryById(Integer articleId);

    /**
     * 根据文章id删除一个文章所有的标签(关联表)
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean deleteArticleTagsById(Integer articleId);

    /**
     * 根据文章id获取文章
     * @param articleId 文章id
     * @param searchDelete true查询未被删除的文章,false查询全部(删除和未删除)文章
     * @param searchPass true查询通过审核的文章，false查询全部(审核通过和未通过)的文章
     * @param isDetail 是否查询文章的分类和标签
     * @return 查询到的文章
     */
    public Article getArticleById(Integer articleId,boolean searchDelete,boolean searchPass,boolean isDetail);

    /**
     * 根据文章id删除文章
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean deleteArticleById(Integer articleId);

    /**
     * 根据文章id审核通过文章
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean passArticleById(Integer articleId);

    /**
     * 给文章打分
     * @param userId 打分用户的id
     * @param articleId 文章id
     * @param score 分数
     * @return 操作结果
     */
    public boolean scoreArticle(Integer userId,Integer articleId,Integer score);

    /**
     * 根据文章id修改文章封面图片路径
     * @param thumbnail 要修改的路径值
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean updateArticleThumbnailById(String thumbnail,Integer articleId);

    /**
     * 根据文章id新增阅读量，若已存在则update,否则insert
     * @param articleId 文章id
     * @return 操作结果
     */
    public boolean addArticleView(Integer articleId);

    /**
     * 查询一篇文章的总阅读量
     * @param articleId
     * @return 文章总阅读量
     */
    public Integer searchArticleViewById(Integer articleId);

    /**
     * 按页查询指定用户发布的文章
     * @param authorId 作者id
     * @param currentPage 当前页码
     * @param pageSize 每一页的大小
     * @param result 存放查询结果的列表
     * @return 总页数
     */
    public Integer listArticlesByAuthorId(Integer authorId,Integer currentPage,Integer pageSize,List<Article> result);

    /**
     * 查询一组文章的评分
     * @param articles 要查询评分的文章
     * @return 查询结果
     */
    public List<Score> listScoresByArticle(List<Article> articles);

    /**
     * 查询一篇文章的平均得分
     * @param articleId 文章id
     * @return
     */
    public Double searchAvgScoreByArticleId(Integer articleId);

    /**
     * 按页查询指定用户的收藏
     * @param userId 用户id
     * @param currentPage 当前页码
     * @param pageSize 每页的大小
     * @param result 存在查询结果的列表
     * @return 总页数
     */
    public Integer listCollectionsByUserId(Integer userId,Integer currentPage,Integer pageSize,List<Article> result);

    /**
     * 根据文章id查询文章阅读量
     * @param articles 文章id列表
     * @return 查询结果
     */
    public List<View> listViewsByArticleId(List<Article> articles);

    /**
     * 根据用户id查询用户发布的总文章数
     * @param authorId
     * @return
     */
    public Integer searchArticleNumByAuthorId(Integer authorId);

    /**
     * 查询一个作者文章的总阅读量
     * @param authorId 用户id
     * @return 文章总阅读量
     */
    public Integer searchViewsByAuthorId(Integer authorId);

    /**
     * 根据分类查询文章
     * @param category 分类名称，若为null则查询全部
     * @param currentPage 当前页码
     * @param pageSize 一页的大小
     * @param result 查询结果
     * @param isPassed true表示查询通过审核的，false表示查询未通过审核的
     * @return 总页数
     */
    public Integer listArticlesByCategory(String category,Integer currentPage,Integer pageSize,List<Article> result,boolean isPassed);

    /**
     * 根据标签查询文章
     * @param tag 标签名称，若为null则查询全部
     * @param currentPage 当前页码
     * @param pageSize 一页的大小
     * @param result 查询结果
     * @param isPassed true表示查询通过审核的，false表示查询全部
     * @return 总页数
     */
    public Integer listArticlesByTag(String tag,Integer currentPage,Integer pageSize,List<Article> result,boolean isPassed);

    /**
     * 根据文章标题查询文章
     * @param title 标签名称，若为null则查询全部
     * @param currentPage 当前页码
     * @param pageSize 一页的大小
     * @param result 查询结果
     * @param isPassed true表示查询通过审核的，false表示查询全部
     * @return 总页数
     */
    public Integer listArticlesByTitle(String title,Integer currentPage,Integer pageSize,List<Article> result,boolean isPassed);
}
