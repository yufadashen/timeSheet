package com.intehel.service.admin;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: 石文静
 * @Description: 图片上传
 * @CreateDate: 2019/11/26  14:17
 * @UpdateDate: 2019/11/26  14:17
 * @Version: V1
 */
public interface UploaderService {

    /**
     * @Author 石文静
     * @Description 图片上传
     * @CreateDate 2019/11/26 14:19
     * @Version V1
     */
    Map<String, Object> uploadPicture(MultipartFile file, HttpServletRequest request, HttpServletResponse response);



    /**
     * @Author 谷金冰
     * @Description layui富文本框图片上传
     */
    Map<String, Object> uploadPictureText(MultipartFile file, HttpServletRequest request);
}
