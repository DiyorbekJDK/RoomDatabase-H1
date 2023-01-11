package com.diyorbek.roomdatabase_h1.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diyorbek.roomdatabase_h1.R
import com.diyorbek.roomdatabase_h1.adapter.FilmAdapter
import com.diyorbek.roomdatabase_h1.database.FilmDatabase
import com.diyorbek.roomdatabase_h1.databinding.FragmentMoviesListBinding
import com.google.android.material.snackbar.Snackbar


class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val filmAdapter by lazy { FilmAdapter() }
    private val filmDatabase by lazy { FilmDatabase(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMoviesListBinding.bind(view)
        allCode()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun allCode() {
        binding.rv.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        filmAdapter.setList(filmDatabase.dao.getAllFilms().toMutableList())
        binding.addFilm.setOnClickListener {
            findNavController().navigate(R.id.action_moviesListFragment_to_addEditFragment)
        }
        filmAdapter.onClick = {
            val bundle = bundleOf("film" to it)
            findNavController().navigate(R.id.action_moviesListFragment_to_detailFragment, bundle)
        }
        filmAdapter.onEditClick = {
            val bundle = bundleOf("film" to it)
            findNavController().navigate(R.id.action_moviesListFragment_to_addEditFragment, bundle)
        }
        filmAdapter.onDeleteClick = {
            filmDatabase.dao.deleteFilm(it)
            Snackbar.make(
                requireView(),
                "Film Deleted. Press Back Button to access changes",
                Snackbar.LENGTH_SHORT
            ).show()
            filmAdapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}