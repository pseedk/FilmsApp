package com.pseedk.filmsapp.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pseedk.filmsapp.MAIN
import com.pseedk.filmsapp.MainActivity
import com.pseedk.filmsapp.R
import com.pseedk.filmsapp.databinding.ItemLayoutBinding
import com.pseedk.filmsapp.models.MovieItemModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var listMovies = emptyList<MovieItemModel>()

    class MyViewHolder(val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.tvItemTitle.text = listMovies[position].title
        holder.itemBinding.tvItemDate.text = listMovies[position].release_date

        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${listMovies[position].poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemBinding.ivItemImg)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieItemModel>) {
        listMovies = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}