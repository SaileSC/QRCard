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
import com.qrcard.iu.fragment.modelview.MainViewModel
import com.squareup.picasso.Picasso

class DetailActivity : Fragment() {

    private val binding by lazy { DetailItemFragmentBinding.inflate(layoutInflater) }

    private val viewMain: MainViewModel by activityViewModels()

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
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.go_to_mainScreen)
        }

        binding.ivClose.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.go_to_mainScreen)
        }

        binding.btnAddItem.setOnClickListener {
            val item = arguments?.getParcelable<Item>("item")
            item?.let{
                view?.let { it1 ->
                    viewMain.setItemBuy(
                        it1.context,
                        BuyItem(
                            id = it.id,
                            nome = it.nome,
                            quantidade = 1,
                            preco = it.preco,
                            categoria = it.categoria,
                            descricao = it.descricao,
                            urlPhoto = it.urlPhoto
                        )
                    )
                }
            }
        }
    }

    private fun loadBundle() {
        val item = arguments?.getParcelable<Item>("item")

        binding.run {
            tvItemName.text = item?.nome
            tvItemDescription.text = item?.descricao
            tvItemPrice.text = "R$ " + item?.preco

            Picasso.get()
                .load(item?.urlPhoto)
                .fit()
                .transform(RoundedCornersTransformation(20))
                .into(ivItemImage)
        }
    }
}
