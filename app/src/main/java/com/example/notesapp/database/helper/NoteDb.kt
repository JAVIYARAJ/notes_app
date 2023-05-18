package com.example.notesapp.database.helper

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notesapp.database.convetors.NoteTypeConvertors
import com.example.notesapp.database.dao.NoteDao
import com.example.notesapp.database.entity.Note

@TypeConverters(NoteTypeConvertors::class)
@Database(entities = [Note::class], version = 2)
abstract class NoteDb : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDb? = null;
        private val migration_1_to_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Notes add column noteStatus Integer default(0)")
            }
        }

        fun getInstance(context: Context): NoteDb {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, NoteDb::class.java, "note-db").addMigrations(
                            migration_1_to_2).build()
                }
            }
            return INSTANCE!!
        }
    }
}