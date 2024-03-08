package com.salem.apps.presentation.ui.main.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salem.apps.databinding.ItemNewsBinding
import com.salem.apps.domain.models.ArticleModel
import com.salem.apps.presentation.extentions.loadImage

class NewsBitCoinAdapter : RecyclerView.Adapter<NewsBitCoinAdapter.ViewHolder>() {


    private var bitCoinsList = ArrayList<ArticleModel>()
    fun catchBitcoinList( list : ArrayList<ArticleModel>){
        bitCoinsList.clear()
        this.bitCoinsList = list
        notifyDataSetChanged()
    }

   inner class ViewHolder (  val binding : ItemNewsBinding ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context) , parent , false ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = bitCoinsList[position]


        holder.binding.apply {
            ivImage.loadImage(data.urlToImage ?: "" )
            tvAuthor.text = data.author
            tvSource.text = data.source?.name ?: ""
            tvDescription.text = data.description
            tvTitle.text = data.title
        }
    }
    override fun getItemCount() =  bitCoinsList.size
}