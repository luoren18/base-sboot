package top.luoren.common.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luoren
 * @date 2019/9/9 10:32
 */
@Configuration
public class ShiroRedisConfig {



    @Bean("sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(ShiroRealm shiroRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean factory(SecurityManager securityManager) {
        ShiroFilterFactoryBean factory = new ShiroFilterFactoryBean();
        factory.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>(16);
        filterMap.put("jwtFilter", new JwtFilter());
        factory.setFilters(filterMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/**", "jwtFilter");
        factory.setFilterChainDefinitionMap(filterChainDefinitionMap);
        factory.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factory;
    }


    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 这个方法需要注释掉，如果不注释掉会导致权限验证方法会被调用两次
     * https://blog.csdn.net/qq_37164847/article/details/88075492
     *
     * @return
     */
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
//        proxyCreator.setProxyTargetClass(true);
//        return proxyCreator;
//    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
