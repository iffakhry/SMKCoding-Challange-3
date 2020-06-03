package id.smkcoding.smkcodingchallenge

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import id.smkcoding.smkcodingchallenge.viewmodel.MyFriendFragmentViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friends.*

/**
 * A simple [Fragment] subclass.
 * Use the [MyFriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFriendsFragment : Fragment(), MyFriendAdapter.dataListener {

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    var dataTeman: MutableList<MyFriendModel> = ArrayList()
    private val viewModel by viewModels<MyFriendFragmentViewModel>()
    private var adapter: MyFriendAdapter? = null

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
        init()
        getData()
        viewModel.init(requireContext());
        viewModel.allMyFriends.observe(viewLifecycleOwner, Observer { myFriends ->
            // Update the cached copy of the words in the adapter.
            myFriends?.let { adapter?.setData(it) }
        })
        fab.setOnClickListener {
            val intent = Intent (getActivity(), MyFriendActivity::class.java)
            getActivity()?.startActivity(intent)
        }
    }

    private fun init(){
        rv_listMyFriends.layoutManager = LinearLayoutManager(context)
        adapter = MyFriendAdapter(requireContext(), dataTeman)
        rv_listMyFriends.adapter = adapter
        adapter?.listener = this
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
                dataTeman = ArrayList()
                for (snapshot in dataSnapshot.children) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    val teman = snapshot.getValue(MyFriendModel::class.java)
                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    teman?.key = (snapshot.key!!)
                    dataTeman.add(teman!!)
                }
                viewModel.insertAll(dataTeman)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    override fun onDeleteData(data: MyFriendModel, position: Int) {
        /*
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter pada RecyclerView melalui interface.
         * kemudian akan menghapus data berdasarkan primary key dari data tersebut
         * Jika berhasil, maka akan memunculkan Toast
         */
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        if (ref != null) {
            ref.child(getUserID)
                .child("Teman")
                .child(data?.key!!.toString())
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                    viewModel.delete(data)
                }
        } else {
            Toast.makeText(context, data!!.key!!.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
