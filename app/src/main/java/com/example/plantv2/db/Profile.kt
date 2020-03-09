package com.example.plantv2.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile (
    val name: String,
    val species: String,
    val location: String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}