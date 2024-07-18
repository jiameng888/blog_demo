package com.blog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final String LOGIN_URL = "/api/auth/login";
    private static final String POST_URL = "/api/posts";
    private static final CharSequence POST_URL1 = "/api/posts/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String method = request.getMethod();
        if (request.getRequestURI().equals(LOGIN_URL)) {
            return true;
        }
        if (method.equalsIgnoreCase("get") && request.getRequestURI().equalsIgnoreCase(POST_URL)) {
            return true;
        }
        if (method.equalsIgnoreCase("get") && request.getRequestURI().contains(POST_URL1)) {
            return true;
        }
        Object loginUserToken = request.getSession().getAttribute("loginUserToken");
        if (loginUserToken == null) {
            response.getWriter().write("{ \"code\":1003 , \"msg\":\"还未登录\", \"data\":null }");
            return false;
        }
        return true;
    }
}
