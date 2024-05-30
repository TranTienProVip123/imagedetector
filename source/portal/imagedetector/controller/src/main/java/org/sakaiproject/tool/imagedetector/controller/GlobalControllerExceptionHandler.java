package org.sakaiproject.tool.imagedetector.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
