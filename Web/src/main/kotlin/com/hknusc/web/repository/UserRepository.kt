package com.hknusc.web.repository

import com.hknusc.web.dto.DeletedUserDTO
import com.hknusc.web.dto.UserDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRepository {
    fun getUsers(): List<UserDTO>
    fun saveUser(userDTO: UserDTO)
    fun getUser(id: Int): UserDTO?
    fun getUserByUserEmail(email:String):UserDTO?
    fun editUser(userDTO: UserDTO)
    fun deleteUser(id: Int)
    fun getDeletedUsers(): List<DeletedUserDTO>
    fun saveDeletedUser(deletedUserDTO: DeletedUserDTO)
}