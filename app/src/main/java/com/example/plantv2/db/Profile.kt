package com.example.plantv2.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Profile (
    val name: String,
    val species: String,
    val location: String,
    //val days: List<String>,
    val hour: Int = 12,
    val minute: Int = 0
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}



