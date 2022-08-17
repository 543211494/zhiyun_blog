package org.nwpu.blog.exception;

import lombok.extern.slf4j.Slf4j;
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
        log.error(e.getMessage());
        e.printStackTrace();
        return "{\"status\":310}";
    }
}
