package com.mlds.projects.htmlformatting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mlds.projects.htmlformatting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val html = getString(R.string.introduction_profile)

        binding.tvOutput.text = html.styleText(
            start = "<b>",
            end = "</b>"
        )

    }
}