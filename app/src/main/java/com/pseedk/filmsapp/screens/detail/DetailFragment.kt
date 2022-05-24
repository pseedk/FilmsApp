package com.pseedk.filmsapp.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.pseedk.filmsapp.MAIN
import com.pseedk.filmsapp.R
import com.pseedk.filmsapp.SaveShared
import com.pseedk.filmsapp.databinding.FragmentDetailBinding
import com.pseedk.filmsapp.models.MovieItemModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var currentMovie: MovieItemModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )
        currentMovie = arguments?.getSerializable("movie") as MovieItemModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val valueBool = SaveShared.getFavorite(MAIN, currentMovie.id.toString())
        val viewModel = ViewModelProvider(this).get(DetailFragmentViewModel::class.java)

        if (isFavorite != valueBool) {
            binding.ivDetailFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.ivDetailFavorite.setImageResource(R.drawable.ic_favorite_border)
        }

        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${currentMovie.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.ivDetail)
        binding.tvTitle.text = currentMovie.title
        binding.tvDate.text = currentMovie.release_date
        binding.tvDescription.text = currentMovie.overview

        binding.ivDetailFavorite.setOnClickListener {
            isFavorite = if (isFavorite == valueBool) {
                binding.ivDetailFavorite.setImageResource(R.drawable.ic_favorite)
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), true)
                viewModel.insert(currentMovie){}
                true
            } else {
                binding.ivDetailFavorite.setImageResource(R.drawable.ic_favorite_border)
                viewModel.delete(currentMovie){}
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), false)
                false
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}