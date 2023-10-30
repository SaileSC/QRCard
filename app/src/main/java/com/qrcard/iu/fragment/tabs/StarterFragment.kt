package com.qrcard.iu.fragment.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.qrcard.databinding.AllFragmentBinding
import com.qrcard.domain.Item
import com.qrcard.iu.fragment.modelview.MainViewModel
import com.qrcard.iu.fragment.adapter.ItemAdapter

class StarterFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var itemAdapter: ItemAdapter

    private val binding by lazy {
        AllFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("itemList", ArrayList(viewModel.getListItens()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList(emptyList())

        viewModel.getItemListLiveData().observe(viewLifecycleOwner) { itemList ->
            val filteredList = itemList.filter { item -> item.categoria == "entrada"}
            setupList(filteredList)
        }
    }
    fun setupList(lista : List<Item>){
        val navController = viewModel.getNav()
        val itemAdapter = navController?.let { ItemAdapter(lista, it) }

        binding.rvAllItens.apply {
            adapter = itemAdapter
            isVisible = true
        }
    }


//    override fun onResume() {
//        super.onResume()
//        setupList(viewModel.getListItens())
//        binding.rvAllItens.isVisible = true
//    }

}