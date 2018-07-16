package com.bluestar.organization.common.util;

import java.util.UUID;

/**
 * 编码，字符串之类的工具类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/16 15:34:37
 */
public final class CodeUtils {

    /**
     * 获得实体类的 id 值,通过 UUID 生成
     * <p>
     * @return 返回随机生成的 id 值
     */
    public static String getId() {
        return UUID.randomUUID().toString();
    }

    /**
     * 判断字符串是否是空白的
     * <p></p>
     * ex:
     * null  ====>  true<p>
     * ""    ====>  true<p>
     * "   " ====>  true<p>
     * "  jfnks  fnjkdsnf"  ====>  false<p>
     *
     * @param str 要判断的字符串
     * @return 返回值上面有详细说明，true 表示字符串是空的，反之不空
     */
    public static boolean isBlank(String str) {
        return (str == null || "".equals(str.trim()));
    }
}
