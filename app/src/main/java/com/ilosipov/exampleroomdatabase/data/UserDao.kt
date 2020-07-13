package com.ilosipov.exampleroomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ilosipov.exampleroomdatabase.model.User

/**
 * Интерфейс UserDao
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>
}