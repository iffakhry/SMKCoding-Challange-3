package id.smkcoding.smkcodingchallenge.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.smkcoding.smkcodingchallenge.entity.MyFriendEntity

@Dao
interface MyFriendDao {

    @Query("SELECT * from my_friend ORDER BY email ASC")
    fun getAllMyFriend(): LiveData<List<MyFriendEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: MyFriendEntity)

    @Query("DELETE FROM my_friend")
    suspend fun deleteAll()
}