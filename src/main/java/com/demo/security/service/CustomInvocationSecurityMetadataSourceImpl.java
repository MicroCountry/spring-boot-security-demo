package com.demo.security.service;

/**
 * Created by wangguomin on 17-9-26.
 */

import com.alibaba.fastjson.JSON;
import com.demo.model.PubAuthorities;
import com.demo.model.PubRoles;
import com.demo.service.AuthorityService;
import com.demo.service.ResourceService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service("customInvocationSecurityMetadataSource")
public class CustomInvocationSecurityMetadataSourceImpl implements
        FilterInvocationSecurityMetadataSource {

    @Resource
    private ResourceService resourceService;

    @Resource
    private AuthorityService authorityService;

    private PathMatcher urlMatcher = new AntPathMatcher();

    private HashMap<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     *
     * 自定义方法，这个类放入到Spring容器后，
     * 指定init为初始化方法，从数据库中读取资源
     * TODO(这里用一句话描述这个方法的作用).
     */
    @PostConstruct
    public void init() {
        System.out.println("do load resource");
        loadResourceDefine();
    }

    /**
     *
     * TODO(程序启动的时候就加载所有资源信息).
     */
    private void loadResourceDefine() {

        // 在Web服务器启动时，提取系统中的所有权限。

        List<PubAuthorities> query = authorityService.getAllAuthoritys();

        /**
             * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
             * sparta
             */
        resourceMap = new HashMap<>();

        for (PubAuthorities auth : query) {
            String authName = auth.getAuthorityName();
            ConfigAttribute ca = new SecurityConfig(auth.getAuthorityName());

            List<String> resources = resourceService.getResourcesByAuthName(authName);


            for (String str : resources) {
                //String authName = auth2.getAuthorityName();
                String url = str;

                /**//*
                     * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                     * sparta
                     */
                if (resourceMap.containsKey(url)) {

                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(ca);
                    resourceMap.put(url, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<>();
                    atts.add(ca);
                    resourceMap.put(url, atts);
                }

            }

        }

    }

    /**
     * TODO(自定义方法，将List<Role>集合转换为框架需要的Collection<ConfigAttribute>集合).
     * @param roles 角色集合
     * @return list 封装好的Collection集合
     */
    private Collection<ConfigAttribute> listToCollection(List<PubRoles> roles) {
        List<ConfigAttribute> list = new ArrayList<>();

        for (PubRoles role : roles) {
            list.add(new SecurityConfig(role.getRoleName()));

        }
        return list;
    }

    /**
     * <p>Title: getAllConfigAttributes</p>
     * <p>Description: </p>
     * @return
     * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * <p>Title: getAttributes</p>
     * <p>Description: </p>
     * @return
     * @throws IllegalArgumentException
     * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        //object 是一个URL ,为用户请求URL
        String url = ((FilterInvocation)object).getRequestUrl();
        if("/".equals(url)){
            System.out.println(" attributes is nulll");
            return null;
        }
        int firstQuestionMarkIndex = url.indexOf(".");
        //判断请求是否带有参数 如果有参数就去掉后面的后缀和参数(/index.do  --> /index)
        if(firstQuestionMarkIndex != -1){
            url = url.substring(0,firstQuestionMarkIndex);
        }
        System.out.println("url sub ..."+url);

        Iterator<String> ite = resourceMap.keySet().iterator();
        //取到请求的URL后与上面取出来的资源做比较
        System.out.println(JSON.toJSONString(ite));
        while (ite.hasNext()) {
            String resURL = ite.next();
            if(urlMatcher.match(resURL,url)){
                System.out.println("return :"+JSON.toJSONString(resourceMap.get(resURL)));
                return resourceMap.get(resURL);
            }
        }
        return null;
    }


    /**
     * <p>Title: supports</p>
     * <p>Description: </p>
     * @param arg0
     * @return
     * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return true;
    }
}
