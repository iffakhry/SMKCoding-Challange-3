package id.smkcoding.smkcodingchallenge2

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_my_friend.*


class MyFriendActivity : AppCompatActivity() {

    private var Nama: EditText? = null
    private var Email: EditText? = null
    private var Telp: EditText? = null
    private var Alamat: EditText? = null
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_friend)
        Nama = findViewById<EditText>(R.id.nama)
        Email = findViewById<EditText>(R.id.email)
        Telp = findViewById<EditText>(R.id.telp)
        Alamat = findViewById<EditText>(R.id.alamat)

        ref = FirebaseDatabase.getInstance().getReference("Teman")

        simpan.setOnClickListener{
            savedata()
        }
        update.setOnClickListener{
            prosesUpdate()
        }

    }

    private fun prosesUpdate() {
        Toast.makeText(
            this@MyFriendActivity,
            "Ini update dulu",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun savedata() {
        val getNama: String = Nama?.getText().toString()
        val getEmail: String = Email?.getText().toString()
        val getTelp: String = Telp?.getText().toString()
        val getAlamat: String = Alamat?.getText().toString()

        val teman = MyFriendModel(getNama, getEmail, getTelp, getAlamat)
        val temanId = ref.push().key.toString()

        ref.child(temanId).setValue(teman).addOnCompleteListener {
            Toast.makeText(this, "Successs",Toast.LENGTH_SHORT).show()
            Nama!!.setText("")
            Email!!.setText("")
            Telp!!.setText("")
            Alamat!!.setText("")
        }
    }

}




