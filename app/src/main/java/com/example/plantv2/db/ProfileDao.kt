package com.example.plantv2.db

import androidx.room.*

@Dao
interface ProfileDao {

    @Insert
    suspend fun addProfile(profile: Profile)

    @Query("SELECT * FROM profile ORDER BY id DESC")
    suspend fun getAllProfiles() : List<Profile>

    @Update
    suspend fun updateProfile(profile: Profile)

    @Delete
    suspend fun deleteProfile(profile: Profile)
}