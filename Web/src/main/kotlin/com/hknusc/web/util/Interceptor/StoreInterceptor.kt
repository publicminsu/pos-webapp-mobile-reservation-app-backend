package com.hknusc.web.util.Interceptor

import com.hknusc.web.util.exception.CustomException
import com.hknusc.web.util.exception.ErrorCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class StoreInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userStoreId: Int = request.getAttribute("userStoreId").toString().toInt()
        if (userStoreId == 0)
            throw CustomException(ErrorCode.STORE_NOT_OPEN)
        return true
    }
}
