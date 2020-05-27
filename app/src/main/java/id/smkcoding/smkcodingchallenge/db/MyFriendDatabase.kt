package id.smkcoding.smkcodingchallenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.smkcoding.smkcodingchallenge.dao.MyFriendDao
import id.smkcoding.smkcodingchallenge.entity.MyFriendEntity

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(MyFriendEntity::class), version = 1, exportSchema = false)
public abstract class MyFriendDatabase : RoomDatabase() {

    abstract fun wordDao(): MyFriendDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MyFriendDatabase? = null

        fun getDatabase(context: Context): MyFriendDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyFriendDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}