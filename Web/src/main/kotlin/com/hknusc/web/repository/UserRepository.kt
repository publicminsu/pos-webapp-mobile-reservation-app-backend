package com.hknusc.web.repository

import com.hknusc.web.dto.user.*
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRepository {
    fun getUsers(): List<UserDTO>
    fun saveUser(userSaveDTO: UserSaveDTO)
    fun getUser(id: Int): UserDTO?
    fun getUserByIdList(userGetByIdListDTO: UserGetByIdListDTO): List<UserDTO>
    fun getUserByUserEmail(email: String): UserDTO?
    fun editUser(userDBEditDTO: UserDBEditDTO)
    fun deleteUser(id: Int)
    fun getDeletedUsers(): List<DeletedUserDTO>
    fun saveDeletedUser(deletedUserDTO: DeletedUserDTO)
}