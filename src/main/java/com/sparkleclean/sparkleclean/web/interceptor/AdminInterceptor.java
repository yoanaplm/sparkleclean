package com.sparkleclean.sparkleclean.web.interceptor;

import com.sparkleclean.sparkleclean.user.model.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AdminInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        HttpSession session = request.getSession(false);

        boolean isAdmin = session != null && session.getAttribute("role") ==UserRole.ADMIN;

        if (!isAdmin) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }return isAdmin;
    }
}
