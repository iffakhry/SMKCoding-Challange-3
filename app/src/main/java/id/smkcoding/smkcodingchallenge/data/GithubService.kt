package id.smkcoding.smkcodingchallenge.data

import id.smkcoding.smkcodingchallenge.model.GithubUserModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Fakhry on 5/7/20.
 */
interface GithubService {

    @GET("users")
    fun getUsers(): Call<List<GithubUserModel>>

}
