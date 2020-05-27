package id.smkcoding.smkcodingchallenge.activity.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.GoogleBuilder
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import id.smkcoding.smkcodingchallenge.R
import id.smkcoding.smkcodingchallenge.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), OnFailureListener {

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progress.visibility = View.GONE
        login.setOnClickListener({
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder() //Memilih Provider atau Method masuk yang akan kita gunakan
                    .setAvailableProviders(listOf(GoogleBuilder().build()))
                    .setIsSmartLockEnabled(false)
                    .build(),
                RC_SIGN_IN)
            progress.visibility = View.VISIBLE
        })
        auth = FirebaseAuth.getInstance() //Mendapakan Instance Firebase Autentifikasi

        if (auth!!.currentUser == null) {
        } else {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN adalah kode permintaan yang Anda berikan ke startActivityForResult, saat memulai masuknya arus.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            //Berhasil masuk
            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            } else {
                progress.visibility = View.GONE
                Toast.makeText(this, "Login Dibatalkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onFailure(exception: Exception) {
//        val errorCode = (exception as StorageException).errorCode
        val errorMessage = exception.message
        // test the errorCode and errorMessage, and handle accordingly
    }
}

