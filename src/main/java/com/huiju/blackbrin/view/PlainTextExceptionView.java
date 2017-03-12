package com.huiju.blackbrin.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by xpchen on 17/1/18.
 */
@Component
public class PlainTextExceptionView implements View {
    public static final String ERROR_CODE = "httpCode";
    public static final String ERROR_INFO = "errorInfo";

    @Override
    public String getContentType() {
        return "text/plain";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int code = (Integer)model.get(ERROR_CODE);
        String info = (String)model.get(ERROR_INFO);
        response.setStatus(code);
        response.setHeader(	"Content-Type", "text/html; charset=utf-8");
        response.getWriter().print(info);
    }
}
