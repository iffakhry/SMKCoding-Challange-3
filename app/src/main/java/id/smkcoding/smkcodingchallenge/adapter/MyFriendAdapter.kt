package id.smkcoding.smkcodingchallenge2

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.smkcoding.smkcodingchallenge.R
import id.smkcoding.smkcodingchallenge.model.MyFriendModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.my_friends_item.*

/**
 * Created by Fakhry on 5/7/20.
 */

class MyFriendAdapter(
    private val context: Context,
    private val list: ArrayList<MyFriendModel>
)
    : RecyclerView.Adapter<MyFriendAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.my_friends_item, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))

        //Mengambil Nilai/Value yenag terdapat pada RecyclerView berdasarkan Posisi Tertentu
        val Nama: String? = list.get(position).nama
        val Email: String? = list.get(position).email
        val Telp: String? = list.get(position).telp
        val Alamat: String? = list.get(position).alamat

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.txtFriendName.setText("Nama: $Nama")
        holder.txtFriendEmail.setText("Email: $Email")
        holder.txtFriendTelp.setText("Telp: $Telp")
        holder.txtFriendAlamat.setText("Alamat: $Alamat")

        //Deklarasi objek dari Interfece
        val listener: dataListener? = null
        lateinit var ref : DatabaseReference
        lateinit var auth: FirebaseAuth

        holder.ListItem?.setOnLongClickListener(OnLongClickListener { view ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {
                        /*Berpindah Activity pada halaman layout updateData dan mengambil data
                         pada listMahasiswa, berdasarkan posisinya untuk dikirim pada activity selanjutnya
                         */
                        val bundle = Bundle()
                        bundle.putString("dataNama", list.get(position).nama)
                        bundle.putString("dataEmail", list.get(position).email)
                        bundle.putString("dataTelp", list.get(position).telp)
                        bundle.putString("dataAlamat", list.get(position).alamat)
                        bundle.putString("getPrimaryKey", list.get(position).id)
                        val intent = Intent(view.context, MyUpdateActivity::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                        //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                        //listener?.onDeleteData(list.get(position), position) // gak jadi pakai listener >> gagal
                        auth = FirebaseAuth.getInstance()
                        ref = FirebaseDatabase.getInstance().getReference()
                        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                        if (ref != null) {
                            ref.child(getUserID)
                                .child("Teman")
                                .child(list.get(position)?.id.toString())
                                .removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, list.get(position)?.id.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            alert.create()
            alert.show()
            true
        })
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        var ListItem: LinearLayout? = null
        fun bindItem(item: MyFriendModel) {
            txtFriendName.text = item.nama
            txtFriendEmail.text = item.email
            txtFriendTelp.text = item.telp
            txtFriendAlamat.text = item.alamat
            ListItem = itemView.findViewById<LinearLayout>(R.id.list_item)

        }
    }

    //Membuat Interfece
    interface dataListener {
        fun onDeleteData(data: MyFriendModel, position: Int)
    }
}