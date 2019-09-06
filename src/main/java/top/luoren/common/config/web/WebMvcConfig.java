package top.luoren.common.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.luoren.common.config.web.interceptor.SystemInterceptor;

/**
 * @author luoren
 * @date 2019/9/6 10:19
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Cors跨域访问配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = new String[] { "/login","/*.html","/swagger-resources/**"};
        registry.addInterceptor(new SystemInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
