package com.huiju.blackbrin.commons;

import com.huiju.blackbrin.aspect.HttpSessionLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 基础组件类
 */
@Component
public class BaseComponent {


    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpSessionLocal httpSessionLocal;

    /**
     * 返回当前登录用户的Jid
     */
    protected String getUserJid() {
    	return httpSessionLocal.getUser();
    }


}
