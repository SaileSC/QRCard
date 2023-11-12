package com.qrcard.iu

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    private val ActionsView : ActionsViewModel by activityViewModels()
    private val UserView : UserViewModel by activityViewModels()
    private val BuyItensView : BuyItensViewModel by activityViewModels()


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

        binding.etSearchItem.text = Editable.Factory.getInstance().newEditable(ActionsView.searchString.value ?: "")
    }

    private fun navigation() {
        navController = findNavController()
        ActionsView.setNav(navController)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)
    }


    private fun setupActions(){
        binding.fabListBuy.setOnClickListener{
            if(!BuyItensView.order){
                navController.navigate(R.id.go_to_orderActivity)
            }else{
                navController.navigate(R.id.go_to_progressOrderFragment)
            }
        }

        binding.ivPerfil.setOnClickListener{

            if(UserView.getUser().nome == ""){
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
                    ActionsView.searchString.value = text
                }

                override fun afterTextChanged(s: Editable?) {
                    //Chamado depois de alterar o texto, é obrigatorio.
                }

            })
        }
    }
}