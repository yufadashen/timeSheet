package com.intehel.service.admin.impl;

import com.intehel.common.util.ResultCode;
import com.intehel.service.admin.UploaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author: 石文静
 * @Description: 文件上传
 * @CreateDate: 2019/11/26  14:18
 * @UpdateDate: 2019/11/26  14:18
 * @Version: V1
 */
@Service
public class UploaderServiceImpl implements UploaderService {
    @org.springframework.beans.factory.annotation.Value("${imageDir}")
    private String imageDir;
    private static final Logger logger = LoggerFactory.getLogger(UploaderServiceImpl.class);
    /**
     * @Author 石文静
     * @Description 图片上传
     * @CreateDate 2019/11/26 14:19
     * @Version V1
    */
    @Override
    public Map<String, Object> uploadPicture(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        File targetFile = null;
        //获取文件名加后缀
        String fileName = file.getOriginalFilename();
        if (fileName != null && !"".equals(fileName)) {
            try {
                //存储路径
                String returnUrl = "/upload/imgs/";
                //文件存储位置
                String path =imageDir+returnUrl ;
                //文件后缀
                String file2 = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                //新的文件名
                fileName = System.currentTimeMillis() + "_" + new Random().nextInt(1000) + file2;
                //先判断文件是否存在
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String fileAdd = sdf.format(new Date());
                //获取文件夹路径
                File file1 = new File(path + "/" + fileAdd);
                //如果文件夹不存在则创建
                if (!file1.exists() && !file1.isDirectory()) {
                    file1.mkdirs();
                }
                //将图片存入文件夹
                targetFile = new File(file1, fileName);
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                logger.info("文件夹路径： "+file1.getAbsolutePath());
                logger.info("文件名： "+fileName);
                logger.info("图片存储路径： "+targetFile.getAbsolutePath());
                //返回存储路径
                String url ="/systemImage"+ returnUrl + fileAdd + "/" + fileName;
                map.put("path", url);
                map.put("absolutePath", targetFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(ResultCode.UPLOAD_PICTURE_ERROR.desc());
                throw new RuntimeException(ResultCode.UPLOAD_PICTURE_ERROR.desc());
            }
        }
        //writeJson(response, result);
        return map;
    }
    /**
     *@Description 富文本框上传图片
     *@Author 谷金冰
     *@Date
     *@Time
     */
    @Override
    public Map<String, Object> uploadPictureText(MultipartFile file, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        if(file != null){
            String webapp = request.getServletContext().getRealPath("/");
            try {
                String substring = file.getOriginalFilename();
                //存储路径
                String returnUrl = "/upload/imgs/";
                //文件存储位置
                String path =imageDir+returnUrl ;
                //文件后缀
                String file2 = substring.substring(substring.lastIndexOf("."), substring.length());
                //新的文件名
                substring = System.currentTimeMillis() + "_" + new Random().nextInt(1000) + file2;
                //先判断文件是否存在
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String fileAdd = sdf.format(new Date());
                //获取文件夹路径
                File file1 = new File(path + "/" + fileAdd);
                //如果文件夹不存在则创建
                if (!file1.exists() && !file1.isDirectory()) {
                    file1.mkdirs();
                }
                //将图片存入文件夹
                File targetFile = new File(file1, substring);
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                // 图片的路径+文件名称
                String fileName ="/systemImage"+ returnUrl + fileAdd + "/" + substring;
                map = new HashMap<>();
                map2 = new HashMap<>();
                map.put("code", 0);//0表示成功，1失败
                map.put("msg", "上传成功");//提示消息
                map.put("data", map2);
                map2.put("src", fileName);//图片url
                map2.put("title", substring);//图片名称，这个会显示在输入框里
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
