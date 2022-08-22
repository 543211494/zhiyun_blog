package org.nwpu.blog.service;

import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.View;
import org.nwpu.blog.result.CategoryCollection;
import org.nwpu.blog.result.CategoryData;
import org.nwpu.blog.result.RegisterData;
import org.nwpu.blog.result.WordCloud;

import java.util.List;

public interface DataService {

    /**
     * 根据用户id查询个人词云
     * @param userId 用户id
     * @return 查询结果
     */
    public List<WordCloud> getWordCloudById(Integer userId);

    /**
     * 查询全站词云
     * @return 查询结果
     */
    public List<WordCloud> getAllWordCloud();

    /**
     * 查看个人文章分类数量
     * @param userId 用户id
     * @return 查询结果
     */
    public List<CategoryData> getCategoryById(Integer userId);

    /**
     * 查看全站文章分类数量
     * @return 查询结果
     */
    public List<CategoryData> getAllCategories();

    /**
     * 根据用户id查询用户文章分类被收藏的情况
     * @param userId 用户id
     * @return 查询结果
     */
    public List<CategoryCollection> getCategoryCollectionById(Integer userId);

    /**
     * 查看全站文章分类被收藏情况
     * @return 查询结果
     */
    public List<CategoryCollection> getCategoryCollections();

    /**
     * 查看指定用户全部文章七天的阅读量
     * @param userId 用户id
     * @return 查询结果
     */
    public List<View> getViewsById(Integer userId);

    /**
     * 查询七天内用户注册数量
     * @return 查询结果
     */
    public List<RegisterData> getRegisterData();

    /**
     * 获取最热门的五篇文章
     * @return 查询结果
     */
    public List<Article> getArticles();
}
