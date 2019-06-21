package com.chen.trademark.conf;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Administrator
 */
@Aspect
public class PageAspect {

    /**
     * 切点入口 Controller包下面所有类的所有方法
     */
    private final String pointcut = "execution(* com.chen.trademark.controller..*.*(..))";

    /**
     * 切点
     */
    @Pointcut(value = pointcut)
    public void log() {
    }
}
