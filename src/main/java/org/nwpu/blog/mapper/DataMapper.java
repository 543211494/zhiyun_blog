package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.View;
import org.nwpu.blog.result.CategoryCollection;
import org.nwpu.blog.result.CategoryData;
import org.nwpu.blog.result.RegisterData;
import org.nwpu.blog.result.WordCloud;

import java.util.List;

@Mapper
public interface DataMapper {

    /**
     * 查询个人词云
     * @return 查询结果
     */
    public List<WordCloud> getWordCloudById(@Param("userId") Integer userId);

    /**
     * 获取全站词云
     * @return
     */
    public List<WordCloud> getAllWordCloud();

    /**
     * 查看个人分类数量图
     * @param userId 用户id
     * @return 查询结果
     */
    public List<CategoryData> getCategoryDataById(@Param("userId") Integer userId);

    /**
     * 查看全站分类数量图
     * @return 查询结果
     */
    public List<CategoryData> getAllCategories();

    /**
     * 查看一个用户文章被收藏的情况
     * @return 查询结果
     */
    public List<CategoryCollection> getCategoryCollectionById(@Param("userId") Integer userId);

    /**
     * 查看全站文章收藏情况
     * @return 查询结果
     */
    public List<CategoryCollection> getCategoryCollections();

    /**
     * 查看指定用户全部文章七天的阅读量
     * @param userId 用户id
     * @return 查询结果
     */
    public List<View> getViewsById(@Param("userId") Integer userId);

    /**
     * 查询七天内用户注册数量
     * @return 查询结果
     */
    public List<RegisterData> getRegisterData();

    /**
     * 获取阅读量最高的五篇文章
     * @return 文章列表
     */
    public List<Article> getArticles();
}
