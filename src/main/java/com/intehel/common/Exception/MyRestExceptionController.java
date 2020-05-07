package com.intehel.common.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:谷金冰
 * @description:处理所有cotroller层面的异常
 * @create:2019/11/5 0005
 */
@RestControllerAdvice(annotations = RestController.class)
public class MyRestExceptionController {
    private static final Logger logger= LoggerFactory.getLogger(MyRestExceptionController.class);

    /**
     * 处理所有的Controller层面的异常
     * */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public final Map handleAllExceptions(Exception ex, WebRequest request){
        logger.error(ex.getMessage());
        Map<String,Object> map=new HashMap<>();
        map.put("status",-1);
        map.put("msg",ex.getLocalizedMessage());

        return map;
    }
}
