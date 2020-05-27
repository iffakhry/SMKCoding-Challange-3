package id.smkcoding.smkcodingchallenge.activity.main.myfriend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.smkcoding.smkcodingchallenge.db.MyFriendDatabase
import id.smkcoding.smkcodingchallenge.entity.MyFriendEntity
import id.smkcoding.smkcodingchallenge.repository.MyFriendRepository

class MyFriendsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MyFriendRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allMyFriends: LiveData<List<MyFriendEntity>>

    init {
        val myFriendDao = MyFriendDatabase.getDatabase(application).wordDao()
        repository = MyFriendRepository(myFriendDao)
        allMyFriends = repository.allMyFriend
    }

}