package br.com.douglasmotta.naivagtioncomponentappmirror.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.dao.UserDao

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null


        fun getDatabase(context : Context) : AppDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database.db").build()
                return INSTANCE as AppDatabase
            }

        }
    }
}