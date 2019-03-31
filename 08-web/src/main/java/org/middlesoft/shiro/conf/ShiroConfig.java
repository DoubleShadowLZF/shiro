package org.middlesoft.shiro.conf;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.middlesoft.shiro.filter.PermissionFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @link https://shiro.apache.org/spring.html
 */
@Configuration
public class ShiroConfig {

    /**
     * 自定义认证和授权类
     *
     * @return
     */
    @Bean("userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    /**
     * shiro核心类，
     * 管理认证和授权等核心功能
     *
     * @param realm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(AuthorizingRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /*@Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }*/

    /**
     * shiro的web过滤器Factory命名：shiroFilter
     *
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        /**
         * 记得设置核心类
         */
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new PermissionFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        /**
         * 过滤链,
         * 从上自下执行，一般讲 “/**”放在最为下边：对有所请求都生效；
         * authc：所有url都必须认证通过才可以访问；
         * anon：所有url都可以匿名访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login/auth", "anon");
        filterChainDefinitionMap.put("/login/logout", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


}
