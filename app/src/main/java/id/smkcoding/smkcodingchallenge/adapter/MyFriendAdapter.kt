package id.smkcoding.smkcodingchallenge.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import id.smkcoding.smkcodingchallenge.R
import id.smkcoding.smkcodingchallenge.entity.MyFriendEntity
import id.smkcoding.smkcodingchallenge.model.MyFriendModel
import id.smkcoding.smkcodingchallenge2.MyUpdateActivity
import id.smkcoding.smkcodingchallenge2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.my_friends_item.*

/**
 * Created by Fakhry on 5/7/20.
 */
class MyFriendAdapter(
    private val context: Context,
    private var list: List<MyFriendEntity>
)
    : RecyclerView.Adapter<MyFriendAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.my_friends_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))
    }

    fun setData(list: List<MyFriendEntity>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: MyFriendEntity) {
            txtFriendName.text = item.nama
            txtFriendEmail.text = item.email
            txtFriendTelp.text = item.telp
            txtFriendAlamat.text = item.alamat
        }
    }
}

/**
 * Created by Fakhry on 5/7/20.
 */

class MyFriendAdapter(
    private val context: Context, private val list: ArrayList<MyFriendModel>
)
    : RecyclerView.Adapter<MyFriendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context)
            .inflate(R.layout.my_friends_item, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))

        //Mengambil Nilai/Value yenag terdapat pada RecyclerView berdasarkan Posisi Tertentu
        val Nama: String? = list.get(position).getNama()
        val Email: String? = list.get(position).getEmail()
        val Telp: String? = list.get(position).getTelp()
        val Alamat: String? = list.get(position).getAlamat()


        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)

        //Memasukan Nilai/Value kedalam View (TextView: NIM, Nama, Jurusan)
        holder.txtFriendName.setText("Nama: $Nama")
        holder.txtFriendEmail.setText("Email: $Email")
        holder.txtFriendTelp.setText("Telp: $Telp")
        holder.txtFriendAlamat.setText("Alamat: $Alamat")


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
                        bundle.putString("dataNama", list.get(position).getNama())
                        bundle.putString("dataEmail", list.get(position).getEmail())
                        bundle.putString("dataTelp", list.get(position).getTelp())
                        bundle.putString("dataAlamat", list.get(position).getAlamat())
                        bundle.putString("getPrimaryKey", list.get(position).getKey())
                        val intent = Intent(
                            view.context,
                            MyUpdateActivity::class.java
                        )
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                    }
                }
            }
            alert.create()
            alert.show()
            true
        })
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        var ListItem: LinearLayout? = null
        fun bindItem(item: MyFriendModel) {
            txtFriendName.text = item.getNama()
            txtFriendEmail.text = item.getEmail()
            txtFriendTelp.text = item.getTelp()
            txtFriendAlamat.text = item.getAlamat()
            ListItem = itemView.findViewById<LinearLayout>(
                R.id.list_item
            )

        }
    }
}