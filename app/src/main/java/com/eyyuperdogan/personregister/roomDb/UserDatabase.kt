package com.eyyuperdogan.personregister.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eyyuperdogan.personregister.model.User


@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao():UserDao
}