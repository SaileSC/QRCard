package com.qrcard.iu

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.qrcard.R


import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.qrcard.databinding.MainScreenFragmentBinding
import com.qrcard.iu.fragment.modelview.ActionsViewModel
import com.qrcard.iu.fragment.modelview.BuyItensViewModel
import com.qrcard.iu.fragment.modelview.UserViewModel

class MainScreen  : Fragment() {


    private val actionsView : ActionsViewModel by activityViewModels()
    private val userView : UserViewModel by activityViewModels()
    private val buyItensView : BuyItensViewModel by activityViewModels()


    private val binding by lazy { MainScreenFragmentBinding.inflate(layoutInflater) }
    private lateinit var navController : NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation()
        setupActions()
        searchString()
    }

    override fun onResume() {
        super.onResume()

        binding.etSearchItem.text = Editable.Factory.getInstance().newEditable(actionsView.searchString.value ?: "")
    }

    private fun navigation() {
        navController = findNavController()
        actionsView.setNav(navController)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)
    }


    private fun setupActions(){
        binding.fabListBuy.setOnClickListener{
            if(!buyItensView.order && !buyItensView.getBuyItemList().isEmpty()){
                navController.navigate(R.id.go_to_orderActivity)
            }else if(buyItensView.order){
                navController.navigate(R.id.go_to_progressOrderFragment)
            }else{
                Toast.makeText(context, "Adicione itens ao carrinho", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivPerfil.setOnClickListener{

            if(userView.getUser().name == ""){
                navController.navigate(R.id.go_to_singInFragment)
            }else{
                navController.navigate(R.id.go_to_perfilFragment)
            }

        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val activity = requireActivity()
            activity.moveTaskToBack(true)
        }
    }

    fun searchString(){
        binding.apply {
            etSearchItem.addTextChangedListener(object  : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //Chamado antes de alterar o texto, é obrigatorio.
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val text = s.toString()
                    actionsView.searchString.value = text
                }

                override fun afterTextChanged(s: Editable?) {
                    //Chamado depois de alterar o texto, é obrigatorio.
                }

            })
        }
    }
}