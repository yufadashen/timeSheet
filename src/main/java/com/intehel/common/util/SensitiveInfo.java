package com.intehel.common.util;

import java.lang.annotation.*;

/**
 * @Author: 石文静
 * @Description: 敏感信息注解标记
 * @CreateDate: 2019/12/2  14:20
 * @UpdateDate: 2019/12/2  14:20
 * @Version: V1
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveInfo {
    public SensitiveInfoUtils.SensitiveType type() ;
}
