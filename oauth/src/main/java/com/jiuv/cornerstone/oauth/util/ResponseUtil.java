package com.jiuv.cornerstone.oauth.util;

import cn.hutool.json.JSONUtil;
import com.jiuv.cornerstone.base.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: ResponseUtil
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/8 9:40
 * @version: 1.0
 */
@Slf4j
public class ResponseUtil {

    public static void responseJsonWriter(HttpServletResponse response, Result<Object> result) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = null;
        try {

            printWriter = response.getWriter();
            printWriter.print(JSONUtil.toJsonStr(result));
        } catch (IOException e) {
            log.error("responseJsonWriter异常", e);
        } finally {
            printWriter.flush();
            printWriter.close();
        }
    }
}
