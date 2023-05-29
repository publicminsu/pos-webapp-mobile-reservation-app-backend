package com.hknusc.web.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException

class JwtAuthenticationFilter(val jwtTokenProvider: JwtTokenProvider):GenericFilterBean() {
    @Throws(IOException::class,ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        var httpRequest:HttpServletRequest=request as HttpServletRequest
        var httpResponse:HttpServletResponse=response as HttpServletResponse

        var token:String?=null
        if(httpRequest.getHeader(JwtTokenProvider.Access_Key)!=null&&
                httpRequest.getHeader(JwtTokenProvider.Access_Key).startsWith("Bearer ")){
            token=httpRequest.getHeader(JwtTokenProvider.Access_Key).split(" ")[1]
        }
        if(token!=null&&jwtTokenProvider.validateToken(token)){
            var authentication:Authentication=jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication=authentication
        }
        chain.doFilter(httpRequest,httpResponse)
    }
}