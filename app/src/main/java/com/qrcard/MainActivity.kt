package com.qrcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qrcard.databinding.MainScreenActivityBinding


class MainActivity : AppCompatActivity()  {

    private val binding by lazy { MainScreenActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}