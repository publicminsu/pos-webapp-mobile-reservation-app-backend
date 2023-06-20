package com.hknusc.web.jwt

import com.hknusc.web.dto.UserDTO
import com.hknusc.web.service.UserDetailService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenProvider(
    @param:Value("\${jwt.secretKey}") private val secretKey:String,
    private val userDetailService: UserDetailService
):InitializingBean {
    val logger=LoggerFactory.getLogger(JwtTokenProvider::class.java)
    lateinit var key:Key
    val accessTokenExpireTime = 30 * 60 * 1000L//30분
    val refreshTokenExpireTime = 60 * 60 * 24 * 1000L
    override fun afterPropertiesSet() {
        key= Keys.hmacShaKeyFor(secretKey.toByteArray())
    }
    fun generateRefreshToken(userDTO: UserDTO):String{
        var now=Date()
        return Jwts.builder()
            .setHeaderParam("typ","REFRESH_TOKEN")
            .setHeaderParam("alg","HS256")
            .setSubject(userDTO.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time+refreshTokenExpireTime))
            .signWith(key,SignatureAlgorithm.HS256)
            .compact()
    }
    fun generateAccessToken(userDTO: UserDTO):String{
        var now=Date()
        return Jwts.builder()
            .setHeaderParam("typ","ACCESS_TOKEN")
            .setHeaderParam("alg","HS256")
            .setSubject(userDTO.id.toString())
            .setIssuedAt(now)
            .setExpiration(Date(now.time+accessTokenExpireTime))
            .signWith(key,SignatureAlgorithm.HS256)
            .compact()
    }
    fun findUserIdByJWT(token:String):Int{
        var claims=Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(token)
            .body
        var userId=claims.subject.toInt()
        return userId
    }
    fun validateToken(token: String):Boolean{
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        }catch (e:Exception){
            logger.error(e.message)
        }
        return false
    }

    fun getAuthentication(token: String):Authentication{
        var userDetails=userDetailService.loadUserByUsername(findUserIdByJWT(token).toString())
        return UsernamePasswordAuthenticationToken(userDetails,"",userDetails.authorities)
    }
    fun resolveToken(request:HttpServletRequest):String?{
        val bearerToken: String? = request.getHeader(Access_Key);
        if(bearerToken!=null&&bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
    companion object{
        const val Access_Key="access_token"
        const val Refresh_Key="refresh_token"
    }
}