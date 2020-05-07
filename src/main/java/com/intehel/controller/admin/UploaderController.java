package com.intehel.controller.admin;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.intehel.common.util.Result;
import com.intehel.common.util.ResultCode;
import com.intehel.service.admin.UploaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 石文静
 * @Description: 图片上传工具类
 * @CreateDate: 2019/11/26  11:05
 * @UpdateDate: 2019/11/26  11:05
 * @Version: V1
 */
@Controller
@RequestMapping("/upload")
public class UploaderController {
    private static final Logger logger = LoggerFactory.getLogger(UploaderController.class);
    @Autowired
    private UploaderService uploaderService;

    /**
     * @Author 石文静
     * @Description 跳转到上传图片页面
     * @CreateDate 2019/11/26 17:47
     * @Version V1
    */
    @RequestMapping("/toUpload")
    public String toUpload(){
        return "upload/uploadImg";
    }

    /**
     * @Author 石文静
     * @Description 图片上传
     * @CreateDate 2019/11/26 11:42
     * @Version V1
    */
    @ResponseBody
    @RequestMapping("/uploadPicture")
    public Object uploadPicture(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> map = uploaderService.uploadPicture(file, request, response);
            return Result.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(ResultCode.UPLOAD_PICTURE_ERROR.desc());
            throw new RuntimeException(ResultCode.UPLOAD_PICTURE_ERROR.desc());
        }
    }

}
