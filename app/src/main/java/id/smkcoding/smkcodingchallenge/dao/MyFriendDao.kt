package id.smkcoding.smkcodingchallenge.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.smkcoding.smkcodingchallenge.MyFriendModel

@Dao
interface MyFriendDao {

    @Query("SELECT * from my_friend")
    fun getAllMyFriend(): LiveData<List<MyFriendModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFriend: MyFriendModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(myFriends: List<MyFriendModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myFriend: MyFriendModel)

    @Delete()
    suspend fun delete(myFriend: MyFriendModel)

    @Query("DELETE FROM my_friend")
    suspend fun deleteAll()
}