package org.nwpu.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.nwpu.blog.result.Response;
import org.nwpu.blog.util.JSON;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lzy
 * @date 2022/8/16
 * 全局异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionResolver {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String processError(Exception e){
        Response response = new Response<Object>();
        if(e.getMessage().equals("1")){
            response.setCode(342);
            response.setMessage("用户未登录(token字符串失效或者请求未携带token)");
            return JSON.toString(response);
        }else if(e.getMessage().equals("2")){
            response.setCode(342);
            response.setMessage("用户权限不足!");
            return JSON.toString(response);
        }else{
            response.setCode(400);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return JSON.toString(response);
    }
}
