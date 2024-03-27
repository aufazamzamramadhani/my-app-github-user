package id.zamzam.myappgithubuser.data.retrofit

import id.zamzam.myappgithubuser.data.response.DetailUsers
import id.zamzam.myappgithubuser.data.response.UserItem
import id.zamzam.myappgithubuser.data.response.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    token 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

    //    @GET("search/users")
    @GET("search/users?q")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") username: String
    ): Call<UsersResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUsers>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UserItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<UserItem>>

}