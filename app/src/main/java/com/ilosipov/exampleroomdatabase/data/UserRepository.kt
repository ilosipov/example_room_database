package com.ilosipov.exampleroomdatabase.data

import androidx.lifecycle.LiveData

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
}