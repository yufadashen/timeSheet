package com.intehel.common.Exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:
 * @description:404
 * @create:2019/11/5 0005
 */
@Controller
public class MainsiteErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value=ERROR_PATH)
    public String handleError(){
        return "/exception/index";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
