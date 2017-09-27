package com.demo.security.service;

/**
 * Created by wangguomin on 17-9-26.
 */

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的CostomInvocationSecurityMetadataSourceService。
 * 该CostomInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 */
@Service("customFilterSecurityInterceptor")
public class CustomFilterSecurityInterceptorImpl extends
        AbstractSecurityInterceptor implements Filter {

    @Resource
    @Qualifier("customInvocationSecurityMetadataSource")
    private FilterInvocationSecurityMetadataSource securityMetadataSource;


    @Resource
    @Qualifier("customAccessDecisionManager")
    @Override
    public void setAccessDecisionManager(
            AccessDecisionManager accessDecisionManager) {
        // TODO Auto-generated method stub
        super.setAccessDecisionManager(accessDecisionManager);
    }
/*    @Resource
    @Qualifier("customAccessDecisionManager")
    public AccessDecisionManager accessDecisionManager;*/

/*    @Resource
    @Qualifier("authenticationManager")
    public AuthenticationManager authenticationManager;*/


    @Resource
    @Qualifier("authenticationManager")
    @Override
    public void setAuthenticationManager(AuthenticationManager newManager) {
        super.setAuthenticationManager(newManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        infoke(fi);

    }

    /**
     * @param fi
     * @throws ServletException
     * @throws IOException
     */
    private void infoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }


    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }


    @Override
    public void destroy() {

    }


   /* public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return securityMetadataSource;
    }


    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }*/
}
