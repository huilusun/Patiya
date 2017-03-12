package com.huiju.blackbrin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.huiju.blackbrin.service.FilterService;
import com.huiju.blackbrin.util.BlackbrinConstant;

public class AuthFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(AuthFilter.class);
    FilterService filterService;

    private String[] excludedPageArray;//过滤掉排除验证的URL

    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        filterService = wac.getBean(FilterService.class);
        
        String excludedPageStr = filterConfig.getInitParameter("excludedPages");  
        if (StringUtils.isNotEmpty(excludedPageStr)) {     
        	excludedPageArray = excludedPageStr.split(",");     
        } 
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = ((HttpServletRequest) request);
        if (isWhiteList(req)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object user = session.getAttribute(BlackbrinConstant.SESSION_USER);
        if (user == null) {
            String jid = req.getHeader("jid");
            String token = req.getHeader("token");
            String realIp = req.getHeader("x-real-ip");
            /*
            coconut的特殊验证方式
            String realIp = req.getHeader("x-real-ip");
            String contextRoot = req.getHeader("ContextRoot");
            if("Html".equals(contextRoot) && (jid = filterService.coconutAuth(realIp, token)) != null) {
                req.getSession().setAttribute(BlackbrinConstant.SESSION_USER, jid);
            } else
            */
            if (!login(jid, token, realIp)){
                authFailed(res); //登录失败
                return;
            }
            req.getSession().setAttribute(BlackbrinConstant.SESSION_USER, jid);
        }
        chain.doFilter(request, response);
    }

    protected boolean login(String jid, String token, String realIp) throws IOException {
        if (filterService.commonAuth(jid, token)) {
            log.debug("jid {} token {} 认证失败",jid,token);
            return true;
        }
        //TODO 内测时使用的特殊登录方式，上线前必须删掉
        if ("BB5F7C8EA50440CC8E1FF39FFAFE7DD0".equals(token)){
            if(realIp != null && !StringUtils.startsWith(realIp, "192.168.1")) {  //仅内测网络或非nginx代理可以使用
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public void destroy() {
    }

    private void authFailed(HttpServletResponse res) throws IOException {
        res.setStatus(HttpStatus.SC_UNAUTHORIZED);
        res.setCharacterEncoding("utf-8");
        res.getWriter().write("auth first");
    }

    private boolean isWhiteList(HttpServletRequest request) {
        boolean isWhite = false;
        try {
            String realIp = request.getHeader("x-real-ip");
            String host = request.getHeader("host").split(":")[0];
            String token = request.getHeader("token");
            String userAgent = request.getHeader("User-Agent");
            log.debug("realIp:" + realIp + ", host:" + host + ", token:" + token + ", userAgent:" + userAgent);
            if (StringUtils.isBlank(realIp) && "127.0.0.1".equals(host) && filterService.getEspecialToken().equals(token)) {
                isWhite = true;
            }
            
            if("127.0.0.1".equals(host) || (realIp != null && !StringUtils.startsWith(realIp, "192.168.1"))) {  //仅内测网络或非nginx代理可以使用
            	 String path = request.getServletPath();
                 for (int i = 0; i < excludedPageArray.length; i++) {
                 	if(excludedPageArray[i].equals(path)){
                 		isWhite = true;
                 	}
     			}
            }
           
        } catch (Exception e) {
            log.error("校验是否白名单失败.", e);
        }
        return isWhite;
    }

}
