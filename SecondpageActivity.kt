package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.CountryAdapter
import com.example.myapplication.databinding.ActivitySecondpageBinding

class SecondpageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondpageBinding

    // Data for RecyclerView
    private val imageList = arrayListOf(
        R.drawable.apricot, R.drawable.avocado, R.drawable.blackberry,R.drawable.cherry,R.drawable.lychee,
        R.drawable.mangoo, R.drawable.peach, R.drawable.plum
    )
    private val titleList = arrayListOf("Apricots", "Avocado", "Blackberry","Cherry","Lychee","Mangoo","Peach","Plum")
    private val descList = arrayListOf(
        "These are Apricots", "These are Avocados", "These are Blackberries", "These are Cherries",
        "These are Lychee", "These are Mangoes", "These are Peaches", "These are Plums",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve Data from Intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val gender = intent.getStringExtra("gender")
        val country = intent.getStringExtra("country")
        val city = intent.getStringExtra("city")

        // Display Received Data
        binding.nameDisplay.text = name
        binding.emailDisplay.text = email
        binding.genderDisplay.text = gender
        binding.countryDisplay.text = country
        binding.cityDisplay.text = city

        // RecyclerView Setup
        val adapter = CountryAdapter(imageList, titleList, descList)
        binding.recycler.layoutManager = GridLayoutManager(this, 2)
        binding.recycler.adapter = adapter
    }
}