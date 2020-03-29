package com.example.plantv2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Profile::class],
    version = 2
)

abstract class ProfileDatabase : RoomDatabase(){

    abstract fun getProfileDao() : ProfileDao

    companion object {

        @Volatile private var instance : ProfileDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ProfileDatabase::class.java,
            "profiledatabase").addMigrations(Migrations.MIGRATION_1_2).build()
    }
}
