package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.Score;
import org.nwpu.blog.bean.View;
import org.nwpu.blog.mapper.ArticleMapper;
import org.nwpu.blog.mapper.ViewMapper;
import org.nwpu.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ViewMapper viewMapper;

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
    public Article getArticleById(Integer articleId,boolean searchDelete,boolean searchPass,boolean isDetail) {
        return articleMapper.searchArticleById(articleId,searchDelete,searchPass,isDetail);
    }

    @Override
    public boolean deleteArticleById(Integer articleId) {
        try{
            articleMapper.deleteArticleById(articleId);
            articleMapper.deleteTagByArticleId(articleId);
            articleMapper.deleteCategoryByArticleId(articleId);
            articleMapper.deleteCollectionByArticleId(articleId);
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

    @Override
    public boolean addArticleView(Integer articleId) {
        try{
            viewMapper.addArticleView(articleId);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Integer searchArticleViewById(Integer articleId) {
        Integer count = null;
        try{
            count = viewMapper.searchTotalViewByArticleId(articleId);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return count==null?0:count;
    }

    @Override
    public Integer listArticlesByAuthorId(Integer authorId, Integer currentPage, Integer pageSize,List<Article> result) {
        int start = (currentPage.intValue()-1)*pageSize;
        int pageNum;
        List<Article> articles = articleMapper.listArticlesByAuthorId(authorId);
        pageNum = (articles.size()/pageSize)+(articles.size()%pageSize==0?0:1);
        if(start>=articles.size()){
            return pageNum;
        }else{
            int end = start+pageSize;
            for(int i = start;i<end&&i<articles.size();i++){
                result.add(articles.get(i));
            }
            return pageNum;
        }
    }

    @Override
    public List<Score> listScoresByArticle(List<Article> articles) {
        return articleMapper.listScoresByArticleId(articles);
    }

    @Override
    public Double searchAvgScoreByArticleId(Integer articleId) {
        Score score = articleMapper.searchScoreByArticleId(articleId);
        if(score.getArticleId()==null){
            return null;
        }else{
            return score.getScore().doubleValue()/score.getCount().doubleValue();
        }
    }

    @Override
    public Integer listCollectionsByUserId(Integer userId, Integer currentPage, Integer pageSize, List<Article> result) {
        int start = (currentPage.intValue()-1)*pageSize;
        int pageNum;
        List<Article> articles = articleMapper.listCollectionsByUserId(userId);
        pageNum = (articles.size()/pageSize)+(articles.size()%pageSize==0?0:1);
        if(start>=articles.size()){
            return pageNum;
        }else{
            int end = start+pageSize;
            for(int i = start;i<end&&i<articles.size();i++){
                result.add(articles.get(i));
            }
            return pageNum;
        }
    }

    @Override
    public List<View> listViewsByArticleId(List<Article> articles) {
        return articleMapper.listViewsByArticleId(articles);
    }

    @Override
    public Integer searchArticleNumByAuthorId(Integer authorId) {
        return articleMapper.countArticleByAuthorId(authorId);
    }

    @Override
    public Integer searchViewsByAuthorId(Integer authorId) {
        return articleMapper.countViewsByAuthorId(authorId);
    }

    @Override
    public Integer listArticlesByCategory(String category, Integer currentPage, Integer pageSize, List<Article> result, boolean isPassed) {
        int start = (currentPage.intValue()-1)*pageSize;
        int pageNum;
        List<Article> articles = articleMapper.listArticlesByCategory(category,isPassed);
        pageNum = (articles.size()/pageSize)+(articles.size()%pageSize==0?0:1);
        if(start>=articles.size()){
            return pageNum;
        }else{
            int end = start+pageSize;
            for(int i = start;i<end&&i<articles.size();i++){
                result.add(articles.get(i));
            }
            return pageNum;
        }
    }

    @Override
    public Integer listArticlesByTag(String tag, Integer currentPage, Integer pageSize, List<Article> result, boolean isPassed) {
        int start = (currentPage.intValue()-1)*pageSize;
        int pageNum;
        List<Article> articles = articleMapper.listArticlesByTag(tag, isPassed);
        pageNum = (articles.size()/pageSize)+(articles.size()%pageSize==0?0:1);
        if(start>=articles.size()){
            return pageNum;
        }else{
            int end = start+pageSize;
            for(int i = start;i<end&&i<articles.size();i++){
                result.add(articles.get(i));
            }
            return pageNum;
        }
    }

    @Override
    public Integer listArticlesByTitle(String title, Integer currentPage, Integer pageSize, List<Article> result, boolean isPassed) {
        int start = (currentPage.intValue()-1)*pageSize;
        int pageNum;
        List<Article> articles = articleMapper.listArticleByTitle(title,isPassed);
        pageNum = (articles.size()/pageSize)+(articles.size()%pageSize==0?0:1);
        if(start>=articles.size()){
            return pageNum;
        }else{
            int end = start+pageSize;
            for(int i = start;i<end&&i<articles.size();i++){
                result.add(articles.get(i));
            }
            return pageNum;
        }
    }
}
