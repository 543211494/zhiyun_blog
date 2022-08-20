package org.nwpu.blog.controller;

import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.ArticleCollection;
import org.nwpu.blog.bean.User;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.ArticleService;
import org.nwpu.blog.service.CollectionService;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 收藏管理器类
 * @author lzy
 * @date 2022/8/19
 */
@Controller
public class CollectionController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CollectionService collectionService;

    /**
     * 新增文章收藏
     * @param id 文章id
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/article/collectArticle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addCollection(@RequestParam("articleId")String id,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer articleId  = null;
        try{
            articleId = Integer.parseInt(id);
        }catch(Exception e){
            response.setCode(400);
            response.setMessage("文章id格式错误!");
            return JSON.toString(response);
        }
        /* 通过token获取用户id */
        Integer userId = User.getIdByToken(token);
        if(articleService.getArticleById(articleId,true,true,false)==null){
            response.setCode(352);
            response.setMessage("文章不存在!");
            return JSON.toString(response);
        }
        ArticleCollection collection = new ArticleCollection(userId,articleId);
        collectionService.addCollection(collection);
        response.setCode(200);
        response.setMessage("收藏成功!");
        return JSON.toString(response);
    }
}
