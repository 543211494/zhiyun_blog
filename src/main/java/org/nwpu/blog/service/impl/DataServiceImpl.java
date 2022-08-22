package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.View;
import org.nwpu.blog.result.CategoryCollection;
import org.nwpu.blog.result.CategoryData;
import org.nwpu.blog.result.RegisterData;
import org.nwpu.blog.result.WordCloud;
import org.nwpu.blog.mapper.DataMapper;
import org.nwpu.blog.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<WordCloud> getWordCloudById(Integer userId) {
        List<WordCloud> result = null;
        try{
            result = dataMapper.getWordCloudById(userId);
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<WordCloud> getAllWordCloud() {
        List<WordCloud> result = null;
        try{
            result = dataMapper.getAllWordCloud();
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<CategoryData> getCategoryById(Integer userId) {
        List<CategoryData> result = null;
        try{
            result = dataMapper.getCategoryDataById(userId);
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<CategoryData> getAllCategories() {
        List<CategoryData> result = null;
        try{
            result = dataMapper.getAllCategories();
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<CategoryCollection> getCategoryCollectionById(Integer userId) {
        List<CategoryCollection> result = null;
        try{
            result = dataMapper.getCategoryCollectionById(userId);
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<CategoryCollection> getCategoryCollections() {
        List<CategoryCollection> result = null;
        try{
            result = dataMapper.getCategoryCollections();
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<View> getViewsById(Integer userId) {
        List<View> result = null;
        try{
            result = dataMapper.getViewsById(userId);
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<RegisterData> getRegisterData() {
        List<RegisterData> result = null;
        try{
            result = dataMapper.getRegisterData();
            System.out.println(result.size());
        }catch(Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<Article> getArticles() {
        List<Article> result = null;
        try{
            result = dataMapper.getArticles();
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
