package com.hknusc.web.repository
import com.hknusc.web.dto.UserDTO
import com.hknusc.web.entity.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRepository {
    fun getUsers():List<User>
    fun save(userDTO: UserDTO)
}