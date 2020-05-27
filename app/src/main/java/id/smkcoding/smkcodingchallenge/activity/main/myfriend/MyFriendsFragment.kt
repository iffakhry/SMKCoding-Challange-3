package id.smkcoding.smkcodingchallenge2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_github.*
import kotlinx.android.synthetic.main.fragment_my_friends.*

/**
 * A simple [Fragment] subclass.
 * Use the [MyFriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFriendsFragment : Fragment() {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var dataTeman: ArrayList<MyFriendModel>
    lateinit var listTeman : ArrayList<MyFriendModel>
    lateinit var rvView: RecyclerView

    private fun simulasiDataTeman() {
        listTeman = ArrayList()
        listTeman.add(MyFriendModel("Fakhry", "fakhry@smkcoding.id", "081123123123", "Malang"))
        listTeman.add(MyFriendModel("Ahmad","ahmad@smkcoding.id", "085123123123", "Malang"))
    }

    private fun tampilTeman() {
        rv_listMyFriends.layoutManager = LinearLayoutManager(activity)
        rv_listMyFriends.adapter = MyFriendAdapter(activity!!, listTeman)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        fab.setOnClickListener {
            val intent = Intent (getActivity(), MyFriendActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(getContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Teman").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(getContext(), "Database Error yaa...", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //Inisialisasi ArrayList
                dataTeman = java.util.ArrayList<MyFriendModel>()
                for (snapshot in dataSnapshot.children) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    val teman = snapshot.getValue(MyFriendModel::class.java)
                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    teman?.setKey(snapshot.key)
                    dataTeman.add(teman!!)
                }

                //Memasang Adapter pada RecyclerView
                rv_listMyFriends.layoutManager = LinearLayoutManager(context)
                rv_listMyFriends.adapter = MyFriendAdapter(context!!, dataTeman)

                Toast.makeText(getContext(), "Data Berhasil Dimuat", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
