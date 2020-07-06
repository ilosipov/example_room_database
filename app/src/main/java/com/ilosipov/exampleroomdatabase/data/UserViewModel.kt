package com.ilosipov.exampleroomdatabase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}