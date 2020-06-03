<<<<<<< HEAD:app/src/main/java/id/smkcoding/smkcodingchallenge/activity/main/github/GithubFragment.kt
package id.smkcoding.smkcodingchallenge.activity.main.github
=======
package id.smkcoding.smkcodingchallenge
>>>>>>> room:app/src/main/java/id/smkcoding/smkcodingchallenge/GithubFragment.kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
<<<<<<< HEAD:app/src/main/java/id/smkcoding/smkcodingchallenge/activity/main/github/GithubFragment.kt
import id.smkcoding.smkcodingchallenge.model.GithubUserModel
import id.smkcoding.smkcodingchallenge.R
import id.smkcoding.smkcodingchallenge.adapter.GithubUserAdapter
=======
>>>>>>> room:app/src/main/java/id/smkcoding/smkcodingchallenge/GithubFragment.kt
import id.smkcoding.smkcodingchallenge.data.GithubService
import id.smkcoding.smkcodingchallenge.data.apiRequest
import id.smkcoding.smkcodingchallenge.data.httpClient
import id.smkcoding.smkcodingchallenge.util.dismissLoading
import id.smkcoding.smkcodingchallenge.util.showLoading
import id.smkcoding.smkcodingchallenge.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_github.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [CardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GithubFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_github, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetGithubUser()
    }

    private fun callApiGetGithubUser() {
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<GithubService>(httpClient)

        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<GithubUserModel>> {

            override fun onFailure(call: Call<List<GithubUserModel>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)

            }

            override fun onResponse(call: Call<List<GithubUserModel>>, response: Response<List<GithubUserModel>>) {
                dismissLoading(swipeRefreshLayout)

                when {
                    response.isSuccessful ->

                        when {
                            response.body()?.size != 0 ->

                                tampilGithubUser(response.body()!!)

                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }

                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }

            }
        })
    }

    private fun tampilGithubUser(githubUsers: List<GithubUserModel>) {
        listGithubUser.layoutManager = LinearLayoutManager(context)
        listGithubUser.adapter =
            GithubUserAdapter(
                context!!,
                githubUsers
            ) {

                val githubUser = it
                tampilToast(context!!, githubUser.login)

            }
    }


    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}