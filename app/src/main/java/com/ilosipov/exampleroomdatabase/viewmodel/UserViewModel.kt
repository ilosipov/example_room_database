package com.ilosipov.exampleroomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ilosipov.exampleroomdatabase.data.UserDatabase
import com.ilosipov.exampleroomdatabase.repository.UserRepository
import com.ilosipov.exampleroomdatabase.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Класс UserViewModel
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : UserRepository
    val readAllData : LiveData<List<User>>

    init {
        val userDao = UserDatabase.getDatabase(
            application
        ).userDao()
        repository =
            UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
}