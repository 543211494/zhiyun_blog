package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.Article;
import org.nwpu.blog.mapper.ArticleMapper;
import org.nwpu.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Integer addArticle(Article article) {
        try{
            articleMapper.insertArticle(article);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return article.getId();
    }

    @Override
    public boolean addArticleCategory(Integer articleId, Integer categoryId) {
        try{
            articleMapper.updateArticleCategory(articleId,categoryId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateArticleTags(Integer articleId, List<Integer> tagIds) {
        try{
            articleMapper.updateArticleTags(articleId,tagIds);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateArticle(Article article) {
        try{
            articleMapper.updateArticle(article);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteArticleCategoryById(Integer articleId) {
        try{
            articleMapper.deleteArticleCategoryById(articleId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteArticleTagsById(Integer articleId) {
        try{
            articleMapper.deleteArticleTagsById(articleId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Article getArticleById(Integer articleId,boolean searchAll,boolean isDetail) {
        return articleMapper.searchArticleById(articleId,searchAll,isDetail);
    }

    @Override
    public boolean deleteArticleById(Integer articleId) {
        try{
            articleMapper.deleteArticleById(articleId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean passArticleById(Integer articleId) {
        try{
            articleMapper.updateArticleVisibleById(articleId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean scoreArticle(Integer userId, Integer articleId, Integer score) {
        try{
            articleMapper.scoreArticle(userId,articleId,score);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateArticleThumbnailById(String thumbnail, Integer articleId) {
        try{
            articleMapper.updateThumbnail(thumbnail,articleId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
