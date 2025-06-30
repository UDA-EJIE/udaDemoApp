package com.ejie.aa79b.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author aresua.
 *
 */
public class NoraInterceptor extends HandlerInterceptorAdapter {

    @Override()
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (StringUtils.contains(request.getQueryString(), "nora_response_cookie_param")) {
            ModelAndView mav = new ModelAndView("noraResponse");
            throw new ModelAndViewDefiningException(mav);
        }

        return super.preHandle(request, response, handler);
    }

    @Override()
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
