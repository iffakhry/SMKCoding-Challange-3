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

class MyUpdateViewModel() : ViewModel() {

    lateinit var repository: MyFriendRepository

    fun init(context: Context) {
        val myFriendDao = MyFriendDatabase.getDatabase(context).myFriendDao()
        repository = MyFriendRepository(myFriendDao)
    }

    fun updateData(myFriend: MyFriendModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(myFriend)
    }
}