package id.zamzam.myappgithubuser.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.zamzam.myappgithubuser.data.response.DetailUsers
import id.zamzam.myappgithubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {

    val user = MutableLiveData<DetailUsers>()

    fun setDetailUser(username: String){
//        ApiConfig.apiInstance
        //        ApiConfig.apiInstance
        val client = ApiConfig.getApiService()
            .getDetailUser(username)
        client.enqueue(object : Callback<DetailUsers>{
//            .enqueue(object : Callback<DetailUsers> {

                override fun onResponse(call: Call<DetailUsers>, response: Response<DetailUsers>) {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUsers>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getDetailUser(): LiveData<DetailUsers> {
        return user
    }
}