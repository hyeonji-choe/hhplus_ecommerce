package kr.hhplus.be.server.config;

import kr.hhplus.be.server.common.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                //.addPathPatterns("") //interceptor 적용 url
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }
}
