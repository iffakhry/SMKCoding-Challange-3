package id.smkcoding.smkcodingchallenge.repository

import androidx.lifecycle.LiveData
import id.smkcoding.smkcodingchallenge.dao.MyFriendDao
import id.smkcoding.smkcodingchallenge.entity.MyFriendEntity

class MyFriendRepository(private val myFriendDao: MyFriendDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allMyFriend: LiveData<List<MyFriendEntity>> = myFriendDao.getAllMyFriend()

    suspend fun insert(myFriend: MyFriendEntity) {
        myFriendDao.insert(myFriend)
    }
}
