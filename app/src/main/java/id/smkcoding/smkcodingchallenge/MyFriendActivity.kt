package id.smkcoding.smkcodingchallenge

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.smkcoding.smkcodingchallenge.viewmodel.MyFriendViewModel
import kotlinx.android.synthetic.main.activity_my_friend.*


class MyFriendActivity : AppCompatActivity() {

    private var Nama: EditText? = null
    private var Email: EditText? = null
    private var Telp: EditText? = null
    private var Alamat: EditText? = null
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null

    private val viewModel by viewModels<MyFriendViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_friend)
        Nama = findViewById<EditText>(R.id.nama)
        Email = findViewById<EditText>(R.id.email)
        Telp = findViewById<EditText>(R.id.telp)
        Alamat = findViewById<EditText>(R.id.alamat)

        viewModel.init(this)
        ref = FirebaseDatabase.getInstance().getReference()
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
        val getNama: String = Nama?.getText().toString()
        val getEmail: String = Email?.getText().toString()
        val getTelp: String = Telp?.getText().toString()
        val getAlamat: String = Alamat?.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()

        if (getNama.isEmpty() && getNama.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty()) {
            //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
            Toast.makeText(this@MyFriendActivity,"Data tidak boleh ada yang kosong",
                Toast.LENGTH_SHORT).show()
        } else {
            val teman = MyFriendModel(getNama, getEmail, getTelp, getAlamat, "")
            //val temanId = ref.push().key.toString()
            ref.child(getUserID).child("Teman").push().setValue(teman).addOnCompleteListener {
                Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                Nama!!.setText("")
                Email!!.setText("")
                Telp!!.setText("")
                Alamat!!.setText("")
                teman.key = ref.key.toString()
                viewModel.addData(teman)
            }
            finish()
        }
    }

}




