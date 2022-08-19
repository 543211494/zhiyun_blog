package org.nwpu.blog.controller;

import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.ArticleService;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章评分控制器
 * @author lzy
 * @date 2022/8/19
 */
@Controller
public class ScoreController {

    @Autowired
    private ArticleService articleService;

    /**
     * 给文章评分
     * @param id 文章id
     * @param number 文章分数
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/article/scoreArticle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String processScore(@RequestParam("articleId")String id,@RequestParam("score")String number,
                               @RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer articleId = null;
        Integer score = null;
        try{
            articleId = Integer.parseInt(id);
            score = Integer.parseInt(number);
        }catch (Exception e){
            response.setCode(400);
            response.setMessage("数据格式不正确!");
            return JSON.toString(response);
        }
        if(articleService.getArticleById(articleId,true,true,false)==null){
            response.setCode(352);
            response.setMessage("文章不存在!");
            return JSON.toString(response);
        }else if(0<=score.intValue()&&score.intValue()<=5){
            Integer userId = Integer.parseInt(token.split(":")[0].split("-")[2]);
            articleService.scoreArticle(userId,articleId,score);
            response.setCode(200);
            response.setMessage("评分成功!");
            return JSON.toString(response);
        }else{
            response.setCode(354);
            response.setMessage("文章得分数值异常!");
            return JSON.toString(response);
        }
    }
}
