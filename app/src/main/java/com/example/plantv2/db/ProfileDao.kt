package com.example.plantv2.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDao {

    @Insert
    fun addProfile(profile: Profile)

    @Query("SELECT * FROM profile")
    fun getAllProfiles() : List<Profile>
}