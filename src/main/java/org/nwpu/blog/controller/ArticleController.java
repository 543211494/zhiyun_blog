package org.nwpu.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.bean.Article;
import org.nwpu.blog.bean.Category;
import org.nwpu.blog.bean.Tag;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.service.ArticleService;
import org.nwpu.blog.service.CategoryService;
import org.nwpu.blog.service.TagService;
import org.nwpu.blog.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ArticleController {

    private static int summaryLength = 100;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    /**
     * 处理文章发布，其主要步骤为:
     * 1.将文章信息插入数据库中的表article，并获取新插入的文章的id，执行一次SQL完成
     * 2.将文章分类插入表category,若表中已经有同名分类则update更新时间,若无则插入,返回该分类的id,执行一次SQL完成
     * 3.将标签获取(数组形式),批量插入表tag,若表中已有同名标签则update更新时间,若无则insert,执行一次SQL完成
     * 4.将步骤1和步骤2中获取的文章id和分类id插入表article_category_ref,执行一次SQL完成
     * 5.将步骤1和步骤2中获取的文章id和标签id批量插入表article_tag_ref,执行一次SQL完成
     *
     * @param token 用户登录令牌
     * @param title 文章标题
     * @param content 文章内容
     * @param category 文章分类
     * @param tagList 文章标签
     * @return 操作结果
     */
    @RequestMapping(value = "/user/article/publishArticle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addArticle(@RequestParam("token")String token,@RequestParam("title")String title,
                             @RequestParam("content")String content,@RequestParam("category")String category,
                             @RequestParam("tagList")String tagList){
        Response response = new Response<Object>();
        if(title==null||title.isEmpty()||content==null||content.isEmpty()||category==null||category.isEmpty()||tagList==null||tagList.isEmpty()){
            response.setCode(400);
            response.setMessage("参数不能为空!");
        }else if(content.length()>Article.MAXLENGTH){
            response.setCode(351);
            response.setMessage("文章长度过长!");
        }else{
            /* 通过token获取用户id */
            Integer userId = Integer.parseInt(token.split(":")[0].split("-")[2]);
            /* 新增文章 */
            Article article = new Article(userId,title,content);
            article.setSummary(this.initSummary(content.toCharArray()));
            Integer articleId = articleService.addArticle(article);
            /* 插入分类 */
            Category articleCategory = new Category();
            articleCategory.setName(category);
            Integer categoryId = categoryService.addCategory(articleCategory);
            /* 插入标签 */
            Date current = new Date();
            String[] tagNames = tagList.split("\\|");
            Tag[] tags = new Tag[tagNames.length];
            for(int i = 0;i<tagNames.length;i++){
                tags[i] = new Tag();
                tags[i].setName(tagNames[i]);
                tags[i].setCreateTime(current);
                tags[i].setUpdateTime(current);
            }
            List<Integer> tagIds = tagService.addTags(tags);
            /* 更新关联表 */
            articleService.addArticleCategory(articleId,categoryId);
            articleService.updateArticleTags(articleId,tagIds);
            response.setCode(200);
            response.setMessage("发布成功,请耐心等待审核!");
        }
        return JSON.toString(response);
    }

    /**
     * 处理文章修改,其主要步骤
     * 1.修改文章内容
     * @param id 文章id
     * @param title 文章标题
     * @param content 文章内容
     * @param category 文章分类
     * @param tagList 文章标签
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/user/article/updateArticle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateArticle(@RequestParam("articleId")String id,@RequestParam("title")String title,
                                @RequestParam("content")String content,@RequestParam("category")String category,
                                @RequestParam("tagList")String tagList,@RequestParam("token")String token){
        Response response = new Response<Object>();
        if(id==null||id.isEmpty()||title==null||title.isEmpty()||content==null||content.isEmpty()||
                category==null||category.isEmpty()||tagList==null||tagList.isEmpty()){
            response.setCode(400);
            response.setMessage("参数不能为空!");
        }else{
            Integer articleId = null;
            try{
                articleId = Integer.parseInt(id);
            }catch(Exception e){
                response.setCode(400);
                response.setMessage("文章id格式错误!");
                return JSON.toString(response);
            }
            Article article = articleService.getArticleById(articleId,false,false);
            if(article==null){
                response.setCode(400);
                response.setMessage("文章不存在!");
                return JSON.toString(response);
            }else{
                /* 更新文章 */
                article.setTitle(title);
                article.setContent(content);
                article.setSummary(this.initSummary(content.toCharArray()));
                article.setUpdateTime(new Date());
                article.setVisible(false);
                articleService.updateArticle(article);
                /* 删除老标签和分类 */
                articleService.deleteArticleCategoryById(articleId);
                articleService.deleteArticleTagsById(articleId);
                /* 插入分类 */
                Category articleCategory = new Category();
                articleCategory.setName(category);
                Integer categoryId = categoryService.addCategory(articleCategory);
                /* 插入标签 */
                Date current = new Date();
                String[] tagNames = tagList.split("\\|");
                Tag[] tags = new Tag[tagNames.length];
                for(int i = 0;i<tagNames.length;i++){
                    tags[i] = new Tag();
                    tags[i].setName(tagNames[i]);
                    tags[i].setCreateTime(current);
                    tags[i].setUpdateTime(current);
                }
                List<Integer> tagIds = tagService.addTags(tags);
                /* 更新关联表 */
                articleService.addArticleCategory(articleId,categoryId);
                articleService.updateArticleTags(articleId,tagIds);
                response.setCode(200);
                response.setMessage("修改成功,请耐心等待审核!");
            }
        }
        return JSON.toString(response);
    }

    /**
     * 根据id删除文章
     * @param id 文章id
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/article/deleteArticle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteArticle(@RequestParam("articleId")String id,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer articleId = null;
        try{
            articleId = Integer.parseInt(id);
        }catch(Exception e){
            response.setCode(400);
            response.setMessage("文章id格式错误!");
            return JSON.toString(response);
        }
        articleService.deleteArticleById(articleId);
        response.setCode(200);
        response.setMessage("删除成功!");
        return JSON.toString(response);
    }

    /**
     * 审核通过文章
     * @param id 文章id
     * @param token 用户登录令牌
     * @return
     */
    @RequestMapping(value = "/admin/article/passArticle",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String passArticle(@RequestParam("articleId")String id,@RequestParam("token")String token){
        Response response = new Response<Object>();
        Integer articleId = null;
        try{
            articleId = Integer.parseInt(id);
        }catch(Exception e){
            response.setCode(400);
            response.setMessage("文章id格式错误!");
            return JSON.toString(response);
        }
        articleService.passArticleById(articleId);
        response.setCode(200);
        response.setMessage("审核成功!");
        return JSON.toString(response);
    }

    /**
     * 更新文章封面
     * @param file 文章封面图片文件
     * @param id 文章id
     * @param token 用户登录令牌
     * @param session HttpSession
     * @return 操作结果
     */
    @RequestMapping(value = "/user/article/uploadThumbnail",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadThumbnail(@RequestParam("thumbnail")MultipartFile file,@RequestParam("articleId")String id,
                                  @RequestParam("token")String token,HttpSession session){
        Response response = new Response<Object>();
        Integer userId = Integer.parseInt(token.split(":")[0].split("-")[2]);
        Integer articleId = null;
        try{
            articleId = Integer.parseInt(id);
        }catch(Exception e){
            response.setCode(400);
            response.setMessage("文章id格式错误!");
            return JSON.toString(response);
        }
        if(articleService.getArticleById(articleId,false,false)==null){
            response.setCode(400);
            response.setMessage("文章不存在!");
            return JSON.toString(response);
        }
        /* 只允许上传xxx.png或xxx.jpg格式的文件作为头像 */
        String[] fileinfo = file.getOriginalFilename().split("\\.");
        if(fileinfo[0].isEmpty()){
            response.setCode(400);
            response.setMessage("上传失败,文件为空!");
            return JSON.toString(response);
        }else if(!(fileinfo[fileinfo.length-1].equals("jpg")||fileinfo[fileinfo.length-1].equals("png"))){
            response.setCode(332);
            response.setMessage("文件格式错误，只允许上传.jpg或.png格式的文件!");
            return JSON.toString(response);
        }
        /* 获取图片资源存在的根目录 */
        ServletContext context = session.getServletContext();
        String root = context.getRealPath("imgs\\article");
        /* 判断存放头像图片的根目录是否存在，若不存在则新建文件夹 */
        File avatarRoot = new File(root);
        if(!avatarRoot.exists()){
            avatarRoot.mkdirs();
        }
        /* 保存头像图片，文件名为文章id */
        String fileName = root + File.separator + articleId + "."+fileinfo[fileinfo.length-1];
        try{
            file.transferTo(new File(fileName));
        }catch(IOException e){
            response.setCode(332);
            response.setMessage(e.getMessage());
            return JSON.toString(response);
        }
        /* 删除可能残留的老图片 */
        fileName = root + File.separator + articleId + "." + (fileinfo[fileinfo.length-1].equals("jpg")?"png":"jpg");
        File Thumbnail = new File(fileName);
        if(Thumbnail.exists()){
            Thumbnail.delete();
        }
        /* 更新文章封面图片路径 */
        articleService.updateArticleThumbnailById("/api/imgs/article/"+articleId + "." + fileinfo[fileinfo.length-1],articleId);
        response.setCode(200);
        response.setMessage("文章封面上传成功!");
        Map<String,String> data = new HashMap<String,String>();
        data.put("url","/api/imgs/article/"+articleId + "." + fileinfo[fileinfo.length-1]);
        response.setData(data);
        return JSON.toString(response);
    }


    @RequestMapping(value = "/user/article/getArticleById",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getArticleById(){
        Response response = new Response<Object>();
        return JSON.toString(response);
    }
    /**
     * 提取文章照耀
     * @param content 文章内容
     * @return 文章摘要
     */
    public String initSummary(char[] content){
        StringBuilder builder = new StringBuilder();
        /* 当前字符总数 */
        int num = 0;
        /* <的数目,模拟栈用于过滤<> */
        int count = 0;
        for (int index = 0 ;index <content.length ; index++){
            if(content[index]=='<'){
                count++;
            }else if(content[index]=='>'){
                count--;
            }else{
                if(num==ArticleController.summaryLength){
                    break;
                }else if(count==0){
                    builder.append(content[index]);
                    num++;
                }
            }
        }
        builder.append("....");
        return builder.toString();
    }
}
