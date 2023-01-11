package com.diyorbek.roomdatabase_h1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.diyorbek.roomdatabase_h1.R
import com.diyorbek.roomdatabase_h1.database.FilmDatabase
import com.diyorbek.roomdatabase_h1.database.FilmEntity
import com.diyorbek.roomdatabase_h1.databinding.FragmentAddEditBinding
import com.diyorbek.roomdatabase_h1.databinding.FragmentMoviesListBinding
import com.google.android.material.snackbar.Snackbar

class AddEditFragment : Fragment(R.layout.fragment_add_edit) {
    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!
    private var film: FilmEntity? = null
    private val filmDatabase by lazy { FilmDatabase(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        film = arguments?.getParcelable("film")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddEditBinding.bind(view)

        allCode()
    }

    private fun allCode() {
        if (film != null) {
            binding.apply {
                titleText.text = "Edit film"
                btnSaveEdit.text = "Commit changes"
                name.setText(film?.filmName)
                authors.setText(film?.filmAuthors)
                desc.setText(film?.filmDesc)
                data.setText(film?.filmData)
                btnSaveEdit.setOnClickListener {
                    filmDatabase.dao.updateFilm(
                        FilmEntity(
                            film?.id!!,
                            binding.name.text.toString().trim(),
                            binding.authors.text.toString().trim(),
                            binding.desc.text.toString().trim(),
                            binding.data.text.toString().trim()
                        )
                    )
                    Snackbar.make(
                        requireView(),
                        "Film has been edited successfully",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_addEditFragment_to_moviesListFragment)
                }
            }
        } else {
            binding.apply {
                titleText.text = "Add Film"
                btnSaveEdit.text = "Save"
                btnSaveEdit.setOnClickListener {
                    filmDatabase.dao.saveFilm(
                        FilmEntity(
                            filmName = binding.name.text.toString().trim(),
                            filmAuthors = binding.authors.text.toString().trim(),
                            filmDesc = binding.desc.text.toString().trim(),
                            filmData = binding.data.text.toString().trim()
                        )
                    )
                    Snackbar.make(requireView(), "Film saved", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addEditFragment_to_moviesListFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}