package com.example.appmessagesparcial

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CentralActivity : AppCompatActivity() {
    private lateinit var etConsumptionWater: EditText
    private lateinit var etTypeDiet: EditText
    private lateinit var etConsumptionFood: EditText
    private lateinit var etConsumptionHabits: EditText
    private lateinit var buttonEditProfile: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central)

        etConsumptionWater = findViewById(R.id.etConsumptionWater)
        etTypeDiet = findViewById(R.id.etTypeDiet)
        etConsumptionFood = findViewById(R.id.etConsumptionFood)
        etConsumptionHabits = findViewById(R.id.etConsumptionHabits)
        buttonEditProfile = findViewById(R.id.buttonEditProfile)

        sharedPreferences = getSharedPreferences("hybridFootprintData", MODE_PRIVATE)

        buttonEditProfile.setOnClickListener {
            if (validateFields()) {
                registerData()
                redirection(CentralActivity::class.java)
            }
        }
    }

    private fun validateFields(): Boolean {
        fun isFieldEmpty(field: EditText, errorMessage: String): Boolean {
            if (field.text.toString().trim().isEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                return true
            }
            return false
        }

        if (isFieldEmpty(etConsumptionWater, "El campo consumo de agua es requerido")) return false
        if (isFieldEmpty(etTypeDiet, "El campo tipo de dieta es requerido")) return false
        if (isFieldEmpty(etConsumptionFood, "El campo consumo de alimentos es requerido")) return false
        if (isFieldEmpty(etConsumptionHabits, "El campo habitos de consumo es requerido")) return false

        return true
    }

    private fun registerData() {
        val control= sharedPreferences.edit()
        control.putString("ConsumtionWater",etConsumptionWater.text.toString().trim())
        control.putString("TypeDiet",etTypeDiet.text.toString().trim())
        control.putString("ConsumtionFood",etConsumptionFood.text.toString().trim())
        control.putString("ConsumtionHabits",etConsumptionHabits.text.toString().trim())

        control.apply()
        Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show()
    }

    private fun redirection(destinationActivity: Class<*>) {
        val intent = Intent(this, destinationActivity)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "onStart llamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "onResume llamado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "onPause llamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "onStop llamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "onRestart llamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "onDestroy llamado")
    }


}