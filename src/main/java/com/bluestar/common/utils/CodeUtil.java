package com.bluestar.common.utils;

import org.springframework.util.DigestUtils;

/**
 * 编码工具类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018-07-14 17:58
 */
public class CodeUtil {

    /**
     * 获得 MD5 编码后的字符串
     *
     * @param str 要加密的字符串
     * @return 返回加密后的字符串
     */
    private static String getMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
