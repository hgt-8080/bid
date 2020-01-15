package cn.bx.bid.ctrl;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @version 1.0
 * @date 2020/1/8
 * @description cn.bx.bid.ctrl
 */
public class RoleInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("time", System.nanoTime());
        if (request.getSession().getAttribute("USER_LOGIN") == null) {
            request.setAttribute("msg", "您没有权限访问");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        super.afterCompletion(request, response, handler, ex);
    }
}
