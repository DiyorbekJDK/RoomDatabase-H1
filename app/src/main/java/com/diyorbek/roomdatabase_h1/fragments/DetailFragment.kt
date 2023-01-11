package com.diyorbek.roomdatabase_h1.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.diyorbek.roomdatabase_h1.R
import com.diyorbek.roomdatabase_h1.database.FilmEntity
import com.diyorbek.roomdatabase_h1.databinding.FragmentAddEditBinding
import com.diyorbek.roomdatabase_h1.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var film: FilmEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        film = arguments?.getParcelable("film")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        allCode()
    }

    @SuppressLint("SetTextI18n")
    private fun allCode() {
        if (film != null) {
            binding.apply {
                title.text = film?.filmName
                btnSaveEdit.text = "Close"
                filName.text = "Film name: ${film?.filmName}"
                filmAuthor.text = "Film Authors: ${film?.filmAuthors}"
                filmDesc.text = film?.filmDesc
                filmDate.text = "Film Data: ${film?.filmData}"
                btnSaveEdit.setOnClickListener {
                    findNavController().navigate(R.id.action_detailFragment_to_moviesListFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
