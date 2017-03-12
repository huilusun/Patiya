package com.huiju.blackbrin.util;

import java.util.UUID;

/**
 * 类说明：UUID 的相关工具类
 */
public class UUIDUtils {

    /**
     * 创建一个 UUID
     *
     * @return 去除"-"的32位 UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
