package com.ilosipov.exampleroomdatabase.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс User
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 05.07.2020
 * @version $Id$
 */

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val firstName : String,
    val lastName : String,
    val age : Int
)