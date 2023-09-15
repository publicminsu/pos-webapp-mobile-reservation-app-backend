package com.hknusc.web.service

import com.hknusc.web.repository.UserRepository
import com.hknusc.web.util.type.Role
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val user = userRepository.getUser(id.toInt())!!

        val role: String = if (user.isVerified) {
            Role.USER.toString()
        } else {
            Role.UNAUTHENTICATED.toString()
        }

        return User.builder().username(user.email).password(user.password).roles(role).build()
    }
}
