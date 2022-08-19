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
     * @return
     */
    public Integer searchArticleViewById(Integer articleId);
}
