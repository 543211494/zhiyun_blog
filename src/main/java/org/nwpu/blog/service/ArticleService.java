package org.nwpu.blog.service;

import org.nwpu.blog.bean.Article;

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
     * @param searchAll 是否查询被删除的文章
     * @return 查询到的文章
     */
    public Article getArticleById(Integer articleId,boolean searchAll,boolean isDetail);

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
}
