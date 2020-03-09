package com.example.plantv2.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDao {

    @Insert
    suspend fun addProfile(profile: Profile)

    @Query("SELECT * FROM profile ORDER BY id DESC")
    suspend fun getAllProfiles() : List<Profile>
}