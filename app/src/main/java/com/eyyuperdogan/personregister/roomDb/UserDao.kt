package com.eyyuperdogan.personregister.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eyyuperdogan.personregister.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface UserDao {
    @Query("SELECT*FROM user")
    fun getall():Flowable<List<User>>
    @Insert
    fun insert(user:User):Completable
    @Delete
    fun delete(user: User):Completable
    @Update
    fun update(user: User):Completable
}