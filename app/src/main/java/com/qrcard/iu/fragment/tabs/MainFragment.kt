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

class MainFragment : Fragment() {
    private val viewMain: MainViewModel by activityViewModels()

    private lateinit var itemAdapter: ItemAdapter

    private lateinit var filterList : List<Item>

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
        outState.putParcelableArrayList("itemList", ArrayList(viewMain.getListItens()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList(emptyList())

        viewMain.getItemListLiveData().observe(viewLifecycleOwner) { itemList ->
            filterList = itemList.filter { item -> item.categoria == "principal"}
            setupList(filterList)
        }

        viewMain.searchString.observe(viewLifecycleOwner) {
            setupList(filterList, it)
        }
    }
    fun setupList(lista : List<Item>){
        val navController = viewMain.getNav()
        val itemAdapter = navController?.let { ItemAdapter(lista, it) }

        binding.rvAllItens.apply {
            adapter = itemAdapter
            isVisible = true
        }
    }

    private fun setupList(lista: List<Item>, filtro: String) {
        val navController = viewMain.getNav()

        val filteredList = lista.filter { item ->
            item.nome.contains(filtro, ignoreCase = true)
        }

        val itemAdapter = navController?.let { ItemAdapter(filteredList, it) }

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