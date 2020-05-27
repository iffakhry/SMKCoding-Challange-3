package id.smkcoding.smkcodingchallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.smkcoding.smkcodingchallenge.model.GithubUserModel
import id.smkcoding.smkcodingchallenge.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.github_user_item.*

/**
 * Created by Fakhry on 5/7/20.
 */
class GithubUserAdapter(private val context: Context, private val items: List<GithubUserModel>, private val listener: (GithubUserModel)-> Unit) :
RecyclerView.Adapter<GithubUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context,
            LayoutInflater.from(context).inflate(
                R.layout.github_user_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView : View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: GithubUserModel, listener: (GithubUserModel) -> Unit) {
            txtUsername.text = item.login
            txtUserRepo.text = item.reposUrl

            Glide.with(context).load(item.avatarUrl).into(imgUser)

            containerView.setOnClickListener { listener(item) }
        }

    }

}