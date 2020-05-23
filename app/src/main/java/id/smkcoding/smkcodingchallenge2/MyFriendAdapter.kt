package id.smkcoding.smkcodingchallenge2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.my_friends_item.*

/**
 * Created by Fakhry on 5/7/20.
 */
class MyFriendAdapter(
    private val context: FragmentActivity?,
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
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: MyFriendModel) {
            txtFriendName.text = item.getNama()
            txtFriendEmail.text = item.getEmail()
            txtFriendTelp.text = item.getTelp()
            txtFriendAlamat.text = item.getAlamat()
        }
    }
}