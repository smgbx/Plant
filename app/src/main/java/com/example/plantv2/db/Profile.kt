package com.example.plantv2.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Calendar

@Entity
data class Profile (
    val name: String,
    val species: String,
    val location: String,
    //val days: List<String>,
    //var time: String
    //val minute: Int,
    //val hour: Int
    val plantDate: Calendar = Calendar.getInstance()
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}



