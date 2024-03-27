package id.zamzam.myappgithubuser.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.zamzam.myappgithubuser.data.response.UserItem
import id.zamzam.myappgithubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersModelViews: ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<UserItem>>()

    fun setListFollowers(username: String) {
        val client = ApiConfig.getApiService()
            .getFollowers(username)
        client.enqueue(object : Callback<ArrayList<UserItem>>{

                override fun onResponse(
                    call: Call<ArrayList<UserItem>>,
                    response: Response<ArrayList<UserItem>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UserItem>>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }

    fun getListFollowers(): LiveData<ArrayList<UserItem>> {
        return listFollowers
    }
}