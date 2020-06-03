package id.smkcoding.smkcodingchallenge

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_friend")
data class MyFriendModel(
    var nama: String,
    var email: String,
    var telp: String,
    var alamat: String,
    @PrimaryKey var key: String
){
    constructor() : this("","","","",""
    )
}
