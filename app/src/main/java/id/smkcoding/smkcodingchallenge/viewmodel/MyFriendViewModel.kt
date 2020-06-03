package id.smkcoding.smkcodingchallenge.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.smkcoding.smkcodingchallenge.MyFriendModel
import id.smkcoding.smkcodingchallenge.db.MyFriendDatabase
import id.smkcoding.smkcodingchallenge.repo.MyFriendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyFriendViewModel() : ViewModel() {

    lateinit var repository: MyFriendRepository

    fun init(context: Context) {
        val myFriendDao = MyFriendDatabase.getDatabase(context).myFriendDao()
        repository = MyFriendRepository(myFriendDao)
    }

    fun addData(myFriend: MyFriendModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(myFriend)
    }

    fun deleteData(myFriend: MyFriendModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(myFriend)
    }
}