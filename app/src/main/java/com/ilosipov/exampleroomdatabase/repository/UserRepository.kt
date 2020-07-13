package com.ilosipov.exampleroomdatabase.repository

import androidx.lifecycle.LiveData
import com.ilosipov.exampleroomdatabase.data.UserDao
import com.ilosipov.exampleroomdatabase.model.User

/**
 * Класс UserRepository
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}