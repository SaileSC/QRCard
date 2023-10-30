package com.qrcard.iu.fragment.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isGone
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qrcard.R
import com.qrcard.databinding.PurchasedItemCardBinding
import com.qrcard.domain.BuyItem
import com.qrcard.domain.Item
import com.qrcard.iu.fragment.image.RoundedCornersTransformation
import com.qrcard.iu.fragment.modelview.MainViewModel
import com.squareup.picasso.Picasso

class ItemBuyAdapter(
    private val itens: List<BuyItem>,
    private val viewModel: MainViewModel,
) : RecyclerView.Adapter<ItemBuyAdapter.ViewHolder>() {

    var itemLister : (Item) -> Unit = {}
    lateinit var item : BuyItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.purchased_item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            nome.text = itens[position].nome
            preco.text = "R$ ${itens[position].preco}"

            Picasso.get()
                .load(itens[position].urlPhoto)
                .resize(220, 200)
                .transform(RoundedCornersTransformation(20))
                .into(ivPhoto)

            quantidade.text = itens[position].quantidade.toString()


            btnIncrement.setOnClickListener {
                viewModel.increment(itens[position].id.toInt())
                quantidade.text = itens[position].quantidade.toString()
            }

            btnDecrement.setOnClickListener {
                viewModel.decrement(itens[position].id.toInt())
                quantidade.text = itens[position].quantidade.toString()
            }

            btnRemove.setOnClickListener {
                viewModel.deleteBuyItem(itens[position].id.toInt())
                cardView.isGone = true
            }
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nome : TextView
        val preco : TextView
        val quantidade : TextView
        val ivPhoto : ImageView
        val btnIncrement : ImageView
        val btnDecrement : ImageView
        val btnRemove : ImageView
        val cardView : CardView

        init{
            view.apply {
                nome = findViewById(R.id.tv_item_description)
                preco = findViewById(R.id.tv_item_value)
                ivPhoto = findViewById(R.id.iv_item)
                quantidade = findViewById(R.id.tv_value_quantity)
                btnIncrement = findViewById(R.id.iv_add)
                btnDecrement = findViewById(R.id.iv_remove)
                btnRemove = findViewById(R.id.iv_delete)
                cardView = findViewById(R.id.cv_item)

            }
        }
    }
}






