package com.qrcard.iu.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.qrcard.R
import com.qrcard.databinding.ActivityDetailItemBinding
import com.qrcard.databinding.ActivityMainBinding
import com.qrcard.domain.Item
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailItemBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        carregaDados()
        binding.ivClose.setOnClickListener {
            finish()
        }
    }

    private fun carregaDados() {
        val bundle = intent.extras
        val item = bundle?.getParcelable<Item>("item")

        binding.run {
            tvItemName.text = item?.nome
            tvItemDescription.text = item?.descricao
            tvItemPrice.text = item?.preco

            Picasso.get()
                .load(item?.urlPhoto)
                .fit()
                .into(ivItemImage)
        }
    }
}
