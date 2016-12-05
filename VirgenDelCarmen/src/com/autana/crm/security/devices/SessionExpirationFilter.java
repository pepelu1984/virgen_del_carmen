package com.autana.crm.security.devices;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.util.Assert;

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


public class SessionExpirationFilter implements Filter, InitializingBean {
    //~ Instance fields ================================================================================================

    private String expiredUrl;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.hasText(expiredUrl, "ExpiredUrl required");
    }

    /**
     * Does nothing. We use IoC container lifecycle services instead.
     */
    public void destroy() {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        Assert.isInstanceOf(HttpServletRequest.class, request, "Can only process HttpServletRequest");
        Assert.isInstanceOf(HttpServletResponse.class, response, "Can only process HttpServletResponse");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession(false);

        if (session == null &&
            httpRequest.getRequestedSessionId() != null &&
            !httpRequest.isRequestedSessionIdValid())
        {    
            String targetUrl = httpRequest.getContextPath() + expiredUrl;
            httpResponse.sendRedirect(httpResponse.encodeRedirectURL(targetUrl));
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * Does nothing. We use IoC container lifecycle services instead.
     *
     * @param arg0 ignored
     *
     * @throws ServletException ignored
     */
    public void init(FilterConfig arg0) throws ServletException {}

    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }
}