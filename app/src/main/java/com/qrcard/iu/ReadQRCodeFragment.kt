package com.qrcard.iu

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.qrcard.R
import com.qrcard.databinding.QrcodeReadFragmentBinding
import com.qrcard.iu.fragment.modelview.RestaurantViewModel


class ReadQRCodeFragment : Fragment() {
    private val binding by lazy { QrcodeReadFragmentBinding.inflate(layoutInflater) }
    private val RestaurantView : RestaurantViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View
    {
        binding.bvQrcodeRead.setStatusText("")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        nextFragment()
        setupListener()
    }

    private fun setupListener() {
        binding.btnReadQrcode.setOnClickListener{
            findNavController().navigate(R.id.go_to_mainScreen)
        }
    }

    private fun nextFragment() {
        RestaurantView.restaurant.observe(viewLifecycleOwner) {
            if(it.name != ""){
                findNavController().navigate(R.id.go_to_mainScreen)
            }
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST
            )
        } else {
            setupQrCodeSacanner()
        }
    }

    private fun setupQrCodeSacanner() {
        binding.bvQrcodeRead.decodeContinuous{
            if(!it.toString().contains(":")){
                Toast.makeText(context, "QRCode invalido", Toast.LENGTH_SHORT).show()
            }else{
                val result = it.toString().split(":")
                try{
                    RestaurantView.setRestaurant(context, result[0])
                }  catch (e : Exception){
                    Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bvQrcodeRead.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.bvQrcodeRead.pause()
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST = 101
    }
}