package com.hknusc.web.config

import com.hknusc.web.util.Interceptor.JWTInterceptor
import com.hknusc.web.util.Interceptor.StoreInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val jwtInterceptor: JWTInterceptor, private val storeInterceptor: StoreInterceptor) :
    WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtInterceptor)
        registry.addInterceptor(storeInterceptor)
            .excludePathPatterns("/auth/**", "/check/**", "/users/**", "/stores/open", "/stores", "/notifications")
    }
}
