package com.example.nasaapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nasaapi.api.ArtworkItem
import com.example.nasaapi.databinding.ListItemGalleryBinding

class PhotoViewHolder(
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artworkItem: ArtworkItem, onPhotoClicked: (photoURL: String?, photoTITLE: String?, explanation: String?) -> Unit) {
        binding.itemImageView.load(artworkItem.imageUrl) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
        }
        binding.root.setOnClickListener {
            onPhotoClicked(artworkItem.largeImageUrl, artworkItem.title, artworkItem.detailText)
        }
    }
}

class PhotoListAdapter(
    private val galleryItems: List<ArtworkItem>,
    private val onPhotoClicked: (photoURL: String?, photoTITLE: String?, explanation: String?) -> Unit
) : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item, onPhotoClicked)
    }

    override fun getItemCount() = galleryItems.size
}