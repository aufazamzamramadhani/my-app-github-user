package id.zamzam.myappgithubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.zamzam.myappgithubuser.data.response.UserItem
import id.zamzam.myappgithubuser.data.response.UsersResponse
import id.zamzam.myappgithubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    val listUser = MutableLiveData<ArrayList<UserItem>>()

    fun setSearchUsers(q: String = "aufazamzamramadhani") {
//        ApiConfig.apiInstance
        val client = ApiConfig.getApiService()
            .getUsers(q)
        client.enqueue(object : Callback<UsersResponse>{
//            .enqueue(object : Callback<UsersResponse>{

                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                        Log.d("GET DATA", "${response.body()}")
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getUsers(): LiveData<ArrayList<UserItem>> {
        return listUser
    }
}