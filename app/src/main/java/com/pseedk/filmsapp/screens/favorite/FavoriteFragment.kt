package com.pseedk.filmsapp.screens.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.pseedk.filmsapp.MAIN
import com.pseedk.filmsapp.R
import com.pseedk.filmsapp.databinding.FragmentFavoriteBinding
import com.pseedk.filmsapp.databinding.FragmentMainBinding
import com.pseedk.filmsapp.models.MovieItemModel
import com.pseedk.filmsapp.screens.main.MainAdapter
import com.pseedk.filmsapp.screens.main.MainFragmentViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavoriteAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)
        recyclerView = binding.rvFavorite
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(viewLifecycleOwner, { list ->
            adapter.setList(list.asReversed())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun clickMovie(model: MovieItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }

}