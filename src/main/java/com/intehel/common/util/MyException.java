package com.intehel.common.util;

/**
 * @author admin
 * 或者继承RuntimeException（运行时异常）
 */
public class MyException extends Exception {

    private static final long serialVersionUID = 1L;


    /**
     * 提供无参数的构造方法
     */
    public MyException() {
    }

    /**
     * / 提供一个有参数的构造方法，可自动生成
     * @param message 错误类型
     */
    public MyException(String message) {
        // 把参数传递给Throwable的带String参数的构造方法
        super(message);
    }
    /** 提供一个有参数的构造方法，可自动生成*/
    public MyException(ResultCode type) {
        // 把参数传递给Throwable的带String参数的构造方法
        super(Result.error(type.desc(),type.code()));
    }
/**提供一个有参数的构造方法，可自动生成*/
    public MyException(ResultCode type,String msg) {
        // 把参数传递给Throwable的带String参数的构造方法
        super(Result.error(type.desc(),type.code(),msg));
    }

}
