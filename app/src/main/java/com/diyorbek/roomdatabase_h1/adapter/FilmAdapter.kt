package com.diyorbek.roomdatabase_h1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diyorbek.roomdatabase_h1.database.FilmEntity
import com.diyorbek.roomdatabase_h1.databinding.FilmLayoutBinding
import com.diyorbek.roomdatabase_h1.databinding.ItemLayoutBinding

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmVIewHolder>() {
    lateinit var onEditClick: (FilmEntity) -> Unit
    lateinit var onDeleteClick: (FilmEntity) -> Unit
    lateinit var onClick: (FilmEntity) -> Unit
    private var filmList = mutableListOf<FilmEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmVIewHolder {
        return FilmVIewHolder(
            FilmLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmVIewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    override fun getItemCount(): Int = filmList.size

    inner class FilmVIewHolder(private val binding: FilmLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filmEntity: FilmEntity) {
            binding.apply {
                textName.text = filmEntity.filmName
                textAuthor.text = filmEntity.filmAuthors
                textDate.text = filmEntity.filmData
                btnEdit.setOnClickListener {
                    onEditClick(filmEntity)
                }
                btnDelete.setOnClickListener {
                    onDeleteClick(filmEntity)
                }
                itemView.setOnClickListener {
                    onClick(filmEntity)
                }
            }
        }
    }

    fun setList(filmEntity: MutableList<FilmEntity>) {
        this.filmList = filmEntity
        notifyDataSetChanged()
    }
}