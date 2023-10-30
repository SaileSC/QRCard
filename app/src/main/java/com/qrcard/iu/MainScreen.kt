package com.qrcard.iu

import android.os.Bundle
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
import com.qrcard.iu.fragment.modelview.MainViewModel

class MainScreen  : Fragment() {


    private val viewMain: MainViewModel by activityViewModels()


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
    }

    private fun navigation() {
        navController = findNavController()
        viewMain.setNav(navController)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)
    }


    private fun setupActions(){
        binding.fabListBuy.setOnClickListener{
            navController.navigate(R.id.go_to_orderActivity)
        }

        binding.ivPerfil.setOnClickListener{
            navController.navigate(R.id.go_to_singInFragment)
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val activity = requireActivity()
            activity.moveTaskToBack(true)
        }
    }
}