package com.eyyuperdogan.personregister.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name="numberPhone")
    var numberPhone:String,
) :java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    var id=0
}