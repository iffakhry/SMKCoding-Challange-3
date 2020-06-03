package id.smkcoding.smkcodingchallenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.smkcoding.smkcodingchallenge.MyFriendModel
import id.smkcoding.smkcodingchallenge.dao.MyFriendDao

@Database(entities = arrayOf(MyFriendModel::class), version = 1, exportSchema = false)
public abstract class MyFriendDatabase : RoomDatabase() {

    abstract fun myFriendDao(): MyFriendDao

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
                    "my_friend_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}