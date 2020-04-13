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
    val MIGRATION_2_3: Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            //create new table
            database.execSQL("CREATE TABLE 'Profile_new' ('id' INTEGER, 'name' TEXT, 'species' TEXT, 'location' TEXT, 'plantDate' INTEGER, PRIMARY KEY(id))")
            //copy data
            database.execSQL("INSERT INTO 'Profile_new' ('id', 'name', 'species', 'location', 'plantDate') SELECT 'id', 'name', 'species', 'location' FROM 'Profile'")
            //remove old table
            database.execSQL("DROP TABLE Profile")
            //change new table name to old table name
            database.execSQL("ALTER TABLE Profile_new RENAME TO Profile")
        }
    }
}