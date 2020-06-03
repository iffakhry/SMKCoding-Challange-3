package id.smkcoding.smkcodingchallenge.repo

import androidx.lifecycle.LiveData
import id.smkcoding.smkcodingchallenge.MyFriendModel
import id.smkcoding.smkcodingchallenge.dao.MyFriendDao

class MyFriendRepository(private val myFriendDao: MyFriendDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allMyFriend: LiveData<List<MyFriendModel>> = myFriendDao.getAllMyFriend()

    suspend fun insert(myFriend: MyFriendModel) {
        myFriendDao.insert(myFriend)
    }

    suspend fun insertAll(myFriends: List<MyFriendModel>) {
        myFriendDao.insertAll(myFriends)
    }

    suspend fun deleteAll() {
        myFriendDao.deleteAll()
    }

    suspend fun update(myFriend: MyFriendModel) {
        myFriendDao.update(myFriend)
    }

    suspend fun delete(myFriend: MyFriendModel) {
        myFriendDao.delete(myFriend)
    }
}