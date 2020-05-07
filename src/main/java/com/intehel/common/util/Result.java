package com.intehel.common.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : fwj
 * @Date : Created in 2018/3/7 14:28
 */
@Setter
@Getter
public class Result {

    private static final long serialVersionUID = 1L;

    private static String key="SHANGbaiwanguang";

    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 返回代号
     */
    private Integer code;

    /**
     * 成功标识
     */
    private Boolean success;

    /**
     * 返回信息.
     */
    private String message;

    /**
     * 返回参数表单
     */
    private Object info;

    public static String success() {

        Result result = getResult(true, "ok", 0, "ok");
        return JSONObject.toJSONString(result);
    }

    public static String success(Object info) {

        int flag = 101;
        if (info instanceof PageInfo) {
            PageInfo pageInfo = (PageInfo) info;
            if (pageInfo.getList() != null && pageInfo.getList().size() > 0) {
                flag = 0;
            }
        } else if (info != null) {
            flag = 0;
        }

        return JSONObject.toJSONStringWithDateFormat(getResult(true, "ok", flag, info), dateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String aes(Object info){
        int flag =101;
        String s = null;
        if(info !=null){
            try {
                s = AesEncryptUtils.aesEncrypt(info.toString(), key);
                flag=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSONObject.toJSONStringWithDateFormat(getResult(true, "ok", flag, s), dateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }
    public static String success(String msg){
        Result result = getResult(true, msg, 0, "ok");
        return JSONObject.toJSONString(result);

    }
    public static String success(String msg, Integer code) {
        Result result = getResult(true, msg, code, null);
        return JSONObject.toJSONString(result);
    }
    public static String error(String msg, Integer code) {
        Result result = getResult(true, msg, code, null);
        return JSONObject.toJSONString(result);
    }

    public static String error(String msg, Integer code,Object info) {
        Result result = getResult(true, msg, code, info);
        return JSONObject.toJSONString(result);
    }

    public static String missing(String msg) {
        Result result = getResult(true, msg, 405, null);
        return JSONObject.toJSONString(result);
    }

    public static String missing(String msg, Object info) {
        Result result = getResult(true, msg, 405, info);
        return JSONObject.toJSONString(result);
    }

    public static String missing(Object info) {
        Result result = getResult(true, "缺少参数", 405, info);
        return JSONObject.toJSONString(result);
    }

    public static String waring(String msg, Integer code) {
        Result result = getResult(true, msg, code, null);
        return JSONObject.toJSONString(result);
    }

    private static Result getResult(Boolean success, String message, Integer code, Object info) {
        Result result = new Result();
        result.setCode(code);
        result.setInfo(info);
        result.setMessage(message);
        result.setSuccess(success);
        return result;
    }
}
