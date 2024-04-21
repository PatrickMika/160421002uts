package com.anmp.advuts160421002.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.anmp.advuts160421002.databinding.HomeListItemBinding
import com.anmp.advuts160421002.model.Hobby

class HobbytListAdapter(val hobbyList:ArrayList<Hobby>)
    :RecyclerView.Adapter<HobbytListAdapter.HobbyViewHolder>()
{
    class HobbyViewHolder(var binding: HomeListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val binding = HomeListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HobbyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return hobbyList.size
    }

    fun updateHobbyList(newStudentList: ArrayList<Hobby>) {
        hobbyList.clear()
        hobbyList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        holder.binding.txtTitle.text = hobbyList[position].title
        holder.binding.txtUsername.text = hobbyList[position].username
        holder.binding.txtDesc.text = hobbyList[position].desc

        holder.binding.btnRead.setOnClickListener {
            val action = HomeFragmentDirections.actionDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
}

