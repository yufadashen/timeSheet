
package com.intehel.common.util;

import java.util.UUID;

/**
 * <p>
 * Description: UUID生成器
 * </p>
 * @author 席林达
 */
public final class UUIDGenerator {

    private UUIDGenerator() {
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUuid() {
        String uuidString = UUID.randomUUID().toString();
        return uuidString;
    }

    /**
     * 获得一个32位UUID
     * @return String UUID
     */
    public static String getUuid32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    private static String tempFinanceId = "";
  

}
