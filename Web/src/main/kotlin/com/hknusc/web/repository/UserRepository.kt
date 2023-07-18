package com.hknusc.web.repository

import com.hknusc.web.dto.user.DeletedUserDTO
import com.hknusc.web.dto.user.UserDTO
import com.hknusc.web.dto.user.UserSaveDTO
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRepository {
    fun getUsers(): List<UserDTO>
    fun saveUser(userSaveDTO: UserSaveDTO)
    fun getUser(id: Int): UserDTO?
    fun getUserByUserEmail(email: String): UserDTO?
    fun editUser(userDTO: UserDTO)
    fun deleteUser(id: Int)
    fun getDeletedUsers(): List<DeletedUserDTO>
    fun saveDeletedUser(deletedUserDTO: DeletedUserDTO)
}