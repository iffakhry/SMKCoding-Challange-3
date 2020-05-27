package id.smkcoding.smkcodingchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import id.smkcoding.smkcodingchallenge.R
import id.smkcoding.smkcodingchallenge.entity.MyFriendEntity
import id.smkcoding.smkcodingchallenge.model.MyFriendModel
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