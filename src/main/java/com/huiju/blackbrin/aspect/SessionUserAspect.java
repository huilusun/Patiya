package com.huiju.blackbrin.aspect;

import com.huiju.blackbrin.util.BlackbrinConstant;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xpchen on 17/2/3.
 */
@Aspect
@Component
public class SessionUserAspect {

    @Autowired
    HttpSessionLocal sessionLocal;


    @Before(value = "execution(* com.huiju.blackbrin.controller.BaseController+.*(..))")
    public void controllerSessionAspect() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String user = (String) request.getSession().getAttribute(BlackbrinConstant.SESSION_USER);
        sessionLocal.setUser(user);
        if (null == user) {
            MDC.put("userID", "noLogin");
        } else {
            MDC.put("userID", user);
        }

    }

    @After(value = "execution(* com.huiju.blackbrin.controller.BaseController+.*(..))")
    public void componentAspect() {
        sessionLocal.deleteUser();
        MDC.remove("userID");
    }

}
