package id.zamzam.myappgithubuser.ui

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.zamzam.myappgithubuser.data.response.UserItem
import id.zamzam.myappgithubuser.databinding.ActivityMainBinding
import id.zamzam.myappgithubuser.ui.details.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModels: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object: UserAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: UserItem) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        viewModels = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            btnSearch.setOnClickListener{
                setUserData()
            }

            if (savedInstanceState==null)
                setUserData("aufazamzamramadhani")
            rvQuery.setOnKeyListener { view, a, event ->
                if (event.action == KeyEvent.ACTION_DOWN && a == KeyEvent.KEYCODE_ENTER){
                    setUserData()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModels.getUsers().observe(this, {
            if (it!=null){
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    private fun setUserData(){
        binding.apply {
            val query = rvQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModels.setSearchUsers(query)
        }
    }

    private fun setUserData(q: String){
        binding.apply {
            showLoading(true)
            viewModels.setSearchUsers(q)
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}