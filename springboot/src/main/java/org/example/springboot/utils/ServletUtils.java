package org.example.springboot.utils;

import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springboot.common.Constants;

public class ServletUtils {
    /**
     * 写入消息到响应对象
     *
     * @param response 响应对象
     * @param text     消息
     */
    public static void write(HttpServletResponse response, String text) {
        JakartaServletUtil.write(response, text, Constants.APPLICATION_JSON_UTF8);
    }
}
