package com.qrcard.iu.fragment.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qrcard.R
import com.qrcard.domain.Item
import com.squareup.picasso.Picasso

class ItemAdapter(private val itens : List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var itemLister : (Item) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dados : Item

        holder.apply {
            nome.text = itens[position].nome
            preco.text = itens[position].preco

            itens[position].run{
                dados = Item(
                    id = id,
                    nome = nome,
                    preco = preco,
                    descricao = descricao,
                    urlPhoto = urlPhoto
                )
            }

            Picasso.get()
                .load(itens[position].urlPhoto)
                .resize(370, 350)
                .into(ivPhoto)

            btnCard.setOnClickListener {

                val bundle = Bundle()
                bundle.putParcelable("item", dados)

                val navController = Navigation.findNavController(holder.itemView)
                navController.navigate(R.id.go_to_detailActivity, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return itens.size
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nome : TextView
        val preco : TextView
        val ivPhoto : ImageView
        val btnCard : CardView

        init{
            view.apply {
                nome = findViewById(R.id.tv_item_description_card)
                preco = findViewById(R.id.tv_item_value_card)
                ivPhoto = findViewById(R.id.iv_item_card)
                btnCard = findViewById(R.id.cv_item_card)
            }
        }
    }
}






