package id.smkcoding.smkcodingchallenge.activity.detailmyfriend

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.smkcoding.smkcodingchallenge.R
import kotlinx.android.synthetic.main.activity_my_friend.*


class MyFriendActivity : AppCompatActivity() {


    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_friend)

        ref = FirebaseDatabase.getInstance().getReference("Teman") //path: Teman
        auth = FirebaseAuth.getInstance() //Mendapakan Instance Firebase Autentifikasi

        simpan.setOnClickListener{
            prosesSave()
        }
        update.setOnClickListener{
            prosesUpdate()
        }
    }

    private fun prosesUpdate() {
    }

    private fun prosesSave() {
        val getNama: String = nama?.getText().toString()
        val getEmail: String = email?.getText().toString()
        val getTelp: String = telp?.getText().toString()
        val getAlamat: String = alamat?.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()

        if (getNama.isEmpty() && getNama.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty()) {
            //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
            Toast.makeText(this@MyFriendActivity,"Data tidak boleh ada yang kosong",
                Toast.LENGTH_SHORT).show()
        } else {
//            val teman = MyFriendModel(getNama, getEmail, getTelp, getAlamat)
//            val temanId = ref.push().key.toString()
//            ref.child(getUserID).child(temanId).setValue(teman).addOnCompleteListener {
//                Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
//                Nama!!.setText("")
//                Email!!.setText("")
//                Telp!!.setText("")
//                Alamat!!.setText("")
//            }
        }
    }

}




