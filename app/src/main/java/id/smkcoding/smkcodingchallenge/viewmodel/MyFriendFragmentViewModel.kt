package id.smkcoding.smkcodingchallenge.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.smkcoding.smkcodingchallenge.MyFriendModel
import id.smkcoding.smkcodingchallenge.db.MyFriendDatabase
import id.smkcoding.smkcodingchallenge.repo.MyFriendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyFriendFragmentViewModel() : ViewModel() {

    lateinit var repository: MyFriendRepository

    lateinit var allMyFriends: LiveData<List<MyFriendModel>>

    fun init(context: Context) {
        val myFriendDao = MyFriendDatabase.getDatabase(context).myFriendDao()
        repository = MyFriendRepository(myFriendDao)
        allMyFriends = repository.allMyFriend
    }

    fun delete(myFriend: MyFriendModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(myFriend)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(myFriends: List<MyFriendModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(myFriends)
    }
}