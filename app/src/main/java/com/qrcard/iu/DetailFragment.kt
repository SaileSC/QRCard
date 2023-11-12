package com.qrcard.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.DetailItemFragmentBinding
import com.qrcard.domain.BuyItem
import com.qrcard.domain.Item
import com.qrcard.iu.fragment.image.RoundedCornersTransformation
import com.qrcard.iu.fragment.modelview.BuyItensViewModel
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private val binding by lazy { DetailItemFragmentBinding.inflate(layoutInflater) }

    private val buyItensView:  BuyItensViewModel by activityViewModels()


    private lateinit var item : Item

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadBundle()
        setupActions()
    }


    fun setupActions(){
        val navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigateUp()
        }

        binding.ivClose.setOnClickListener {
            navController.navigateUp()
        }

        binding.btnAddItem.setOnClickListener {
            item.let{
                view?.let { it1 ->
                    buyItensView.setItemBuy(
                        it1.context,
                        BuyItem(
                            id = it.id,
                            nome = it.nome,
                            quantidade = 1,
                            preco = it.preco,
                            categoria = it.categoria,
                            descricao = it.descricao,
                            urlPhoto = it.urlPhoto,
                            isFavorite = it.isFavorite

                        )
                    )
                }
            }
            navController.navigateUp()
        }


        binding.apply {
            ivFavorite.setOnClickListener{
                item.isFavorite = !item.isFavorite
                if (item.isFavorite) {
                    ivFavorite.setImageResource(R.drawable.ic_favorite_true)

                } else {
                    ivFavorite.setImageResource(R.drawable.ic_favorite)

                }
            }
        }
    }

    private fun loadBundle() {
        item = arguments?.getParcelable<Item>("item")!!
        binding.run {
            tvItemName.text = item.nome
            tvItemDescription.text = item.descricao
            tvItemPrice.text = "R$ " + item?.preco

            if (item.isFavorite) {
                ivFavorite.setImageResource(R.drawable.ic_favorite_true)
            }

            Picasso.get()
                .load(item.urlPhoto)
                .fit()
                .transform(RoundedCornersTransformation(20))
                .into(ivItemImage)


        }
    }
}
