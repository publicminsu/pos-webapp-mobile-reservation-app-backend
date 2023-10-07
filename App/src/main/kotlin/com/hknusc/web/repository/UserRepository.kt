package com.hknusc.web.repository

import com.hknusc.web.dto.auth.PasswordDBEditDTO
import com.hknusc.web.dto.user.*
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserRepository {
    fun saveUser(userDBSaveDTO: UserDBSaveDTO)
    fun getUser(id: Int): UserDTO?
    fun getUserByIdList(userGetByIdListDTO: UserGetByIdListDTO): List<UserDTO>
    fun getUserByUserEmail(email: String): UserDTO?
    fun editUser(userDBEditDTO: UserDBEditDTO)
    fun deleteUser(id: Int)
    fun saveDeletedUser(deletedUserSaveDTO: DeletedUserSaveDTO)
    fun editPassword(passwordDBEditDTO: PasswordDBEditDTO)
    fun confirmEmail(id: Int): Int
}
