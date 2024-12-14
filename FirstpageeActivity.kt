package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityFirstpageeBinding

class FirstpageeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityFirstpageeBinding
    private val countries = arrayOf("China", "Nepal", "Korea", "Japan", "Thailand")
    private val cities = arrayOf("Kathamndu", "Jeju", "Tokyo", "Phuket", "Beijing")
    private var selectedCountry: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstpageeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Country Spinner Setup
        val countryAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, countries
        )
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.countrySpinner.adapter = countryAdapter
        binding.countrySpinner.onItemSelectedListener = this

        // AutoCompleteTextView for Cities
        val cityAdapter = ArrayAdapter(
            this, android.R.layout.simple_dropdown_item_1line, cities
        )
        binding.countryAutoComplete.setAdapter(cityAdapter)
        binding.countryAutoComplete.threshold = 1

        // Submit Button Click Listener
        binding.submitButton.setOnClickListener {
            val name = binding.nameBox.text.toString()
            val email = binding.emailBox.text.toString()
            val password = binding.passwordBox.text.toString()
            val city = binding.countryAutoComplete.text.toString()

            // Input Validations
            if (name.isEmpty()) {
                binding.nameBox.error = "Name cannot be empty"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.emailBox.error = "Email cannot be empty"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.passwordBox.error = "Password cannot be empty"
                return@setOnClickListener
            }
            if (city.isEmpty()) {
                binding.countryAutoComplete.error = "Please select a city"
                return@setOnClickListener
            }

            val selectedId = binding.genderRadioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val gender = findViewById<RadioButton>(selectedId).text.toString()

            if (!binding.agreementCheckbox.isChecked) {
                Toast.makeText(this, "Please click on I agree", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Passing Data to OutputActivity
            val intent = Intent(this, SecondpageActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("gender", gender)
            intent.putExtra("country", selectedCountry)
            intent.putExtra("city", city)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedCountry = countries[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedCountry = ""
    }
}