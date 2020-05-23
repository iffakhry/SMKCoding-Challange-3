package id.smkcoding.smkcodingchallenge2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friends.*

/**
 * A simple [Fragment] subclass.
 * Use the [MyFriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFriendsFragment : Fragment() {

    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null
    lateinit var datateman: ArrayList<MyFriendModel>
    lateinit var listTeman : ArrayList<MyFriendModel>

    private fun simulasiDataTeman() {
        listTeman = ArrayList()
        listTeman.add(MyFriendModel("Fakhry", "fakhry@smkcoding.id", "081123123123", "Malang"))
        listTeman.add(MyFriendModel("Ahmad","ahmad@smkcoding.id", "085123123123", "Malang"))
    }

    private fun tampilTeman() {
        rv_listMyFriends.layoutManager = LinearLayoutManager(activity)
        rv_listMyFriends.adapter = MyFriendAdapter(activity!!, listTeman)
        //rv_listMyFriends.adapter = MyFriendAdapter(activity!!, datateman)
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
        initView()
        fab.setOnClickListener {
            val intent = Intent (getActivity(), MyFriendActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    private fun initView() {
        //simulasiDataTeman()
        getData()
        //tampilTeman()
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference("Teman") //path: Teman
        ref.child(getUserID).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    //Inisialisasi ArrayList
                    datateman = ArrayList<MyFriendModel>()
                    for (snapshot in dataSnapshot.children) {
                        //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                        val teman: MyFriendModel? =
                            snapshot.getValue(MyFriendModel::class.java)
                        //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                        teman!!.setKey(snapshot.key)
                        datateman!!.add(teman)
                    }

                    rv_listMyFriends.layoutManager = LinearLayoutManager(activity)
                    rv_listMyFriends.adapter = MyFriendAdapter(activity!!, datateman)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(
                        "MyFriendsFragment",
                        databaseError.details + " " + databaseError.message
                    )
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
