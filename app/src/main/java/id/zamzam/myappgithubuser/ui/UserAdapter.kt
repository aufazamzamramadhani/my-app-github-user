package id.zamzam.myappgithubuser.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.zamzam.myappgithubuser.data.response.UserItem
import id.zamzam.myappgithubuser.databinding.UserLayoutBinding

class   UserAdapter : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private val list = ArrayList<UserItem>()
    private var onItemClickCallback: OnItemClickCallBack? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallBack{
        fun onItemClicked(data: UserItem)
    }

    inner class MyViewHolder(val binding: UserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserItem){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .centerCrop()
                    .into(ccUser)
                tvUsername.text = user.login

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val view = UserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder((view))
    }

    fun setList(users: ArrayList<UserItem>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}