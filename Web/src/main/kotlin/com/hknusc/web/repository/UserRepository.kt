package com.hknusc.web.repository
import com.hknusc.web.dto.UserDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRepository {
    fun getUsers():List<UserDTO>
    fun save(userDTO: UserDTO)
    fun getUser(id:Int):UserDTO
}