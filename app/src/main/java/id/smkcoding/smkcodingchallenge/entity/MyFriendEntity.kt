package id.smkcoding.smkcodingchallenge.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_friend")
class MyFriendEntity {
    @PrimaryKey  @ColumnInfo(name = "id") val id: String? = null
    @ColumnInfo(name = "email") val email: String? = null
    @ColumnInfo(name = "nama") val nama: String? = null
    @ColumnInfo(name = "kelamin") val kelamin: String? = null
    @ColumnInfo(name = "telp") val telp: String? = null
    @ColumnInfo(name = "alamat") val alamat: String? = null
}