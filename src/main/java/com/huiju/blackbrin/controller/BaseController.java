/**
 *
 */
package com.huiju.blackbrin.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huiju.blackbrin.commons.BaseComponent;
import com.huiju.blackbrin.util.BlackbrinConstant;
import com.huiju.blackbrin.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author xpchen
 */
public abstract class BaseController extends BaseComponent {

    protected Logger log = LoggerFactory.getLogger(this.getClass());
    protected Gson gson;

    public BaseController() {
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        gson = builder.create();
    }

    public String sessionUser() throws Exception {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return (String) attr.getRequest().getSession(true).getAttribute(BlackbrinConstant.SESSION_USER);
    }
}
