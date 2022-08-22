package org.nwpu.blog.controller;

import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.Score;
import org.nwpu.blog.bean.User;
import org.nwpu.blog.bean.View;
import org.nwpu.blog.result.RegisterData;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.ArticleService;
import org.nwpu.blog.service.DataService;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据查询控制类
 * @author lzy
 * @date 2022/8/22
 */
@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private ArticleService articleService;

    /**
     * 查看个人词云
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/statistics/wordCloud",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserWordCloud(@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer userId = User.getIdByToken(token);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("wordCloud",dataService.getWordCloudById(userId));
        response.setData(data);
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }

    /**
     * 查看全站词云
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/statistics/allWordCloud",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllWordCloud(@RequestParam("token")String token){
        Response response = new Response<Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("wordCloud",dataService.getAllWordCloud());
        response.setData(data);
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }

    /**
     * 查看个人文章分类数量
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/statistics/articleCategoryNum",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserCategoryData(@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer userId = User.getIdByToken(token);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("articleCategoryNum",dataService.getCategoryById(userId));
        response.setData(data);
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }

    /**
     * 查看全站文章分类分布
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/statistics/articleCategoryNum",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllCategoryData(@RequestParam("token")String token){
        Response response = new Response<Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("articleCategoryNum",dataService.getAllCategories());
        response.setData(data);
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }

    /**
     * 查看个人文章分类被收藏的情况
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/statistics/categoryCollectionNum",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserCategoryCollection(@RequestParam("token")String token){
        Response response = new Response<Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        Integer userId = User.getIdByToken(token);
        data.put("categoryCollectionNum",dataService.getCategoryCollectionById(userId));
        response.setData(data);
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }

    /**
     * 查看全站文章分类被收藏的情况
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/statistics/categoryCollectionNum",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllCollections(@RequestParam("token")String token){
        Response response = new Response<Object>();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("categoryCollectionNum",dataService.getCategoryCollections());
        response.setData(data);
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }

    /**
     * 查看指定用户七天内的浏览量
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/statistics/articleViewCountGraph",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String articleViewCountGraph(@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer userId = User.getIdByToken(token);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("categoryCollectionNum",dataService.getViewsById(userId));
        response.setData(data);
        response.setCode(200);
        response.setMessage("查询成功!");
        return JSON.toString(response);
    }

    /**
     * 查询最近七天用户登录量
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/statistics/userCountGraph",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userCountGraph(@RequestParam("token")String token){
        Response response = new Response<Object>();
        List<RegisterData> userCountList = dataService.getRegisterData();
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("userCountList",userCountList);
        response.setCode(200);
        response.setMessage("查询成功!");
        response.setData(data);
        return JSON.toString(response);
    }

    /**
     * 查询最热门的五篇文章
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/statistics/articleRank",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getArticleRank(@RequestParam("token")String token){
        Response response = new Response<Object>();
        List<Article> articleList = dataService.getArticles();
        this.setArticlesViewAndScore(articleList);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("articleList",articleList);
        response.setCode(200);
        response.setMessage("查询成功!");
        response.setData(data);
        return JSON.toString(response);
    }

    /**
     * 查询并设置文章的阅读量和平均评分
     * @param articleList
     */
    public void setArticlesViewAndScore(List<Article> articleList){
        /* 查询文章平均评分和阅读量 */
        if(articleList!=null&&articleList.size()!=0){
            List<Score> scoreList = articleService.listScoresByArticle(articleList);
            List<View> viewList = articleService.listViewsByArticleId(articleList);
            if(scoreList!=null){
                for(int i = 0;i<articleList.size();i++){
                    for(int j = 0;j<scoreList.size();j++){
                        if(articleList.get(i).getId().equals(scoreList.get(j).getArticleId())){
                            articleList.get(i).setScore(scoreList.get(j).getScore().doubleValue()/scoreList.get(j).getCount().doubleValue());
                            break;
                        }
                    }
                }
            }
            if(viewList!=null){
                for(int i = 0;i<articleList.size();i++){
                    for(int j = 0;j<viewList.size();j++){
                        if(articleList.get(i).getId().equals(viewList.get(j).getArticleId())){
                            articleList.get(i).setView(viewList.get(j).getCount());
                            break;
                        }
                    }
                }
            }
        }
    }
}
