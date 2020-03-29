package com.example.plantv2.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Profile ADD COLUMN hour INT NOT NULL DEFAULT''")
            database.execSQL("ALTER TABLE Profile ADD COLUMN minute INT NOT NULL DEFAULT''")
        }
    }
}