package com.example.appmessagesparcial

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {


        private lateinit var etName: EditText
        private lateinit var etLastName: EditText
        private lateinit var etEmail: EditText
        private lateinit var etPhone: EditText
        private lateinit var buttonEdit: Button
        private lateinit var buttonChangePhoto: Button
        private lateinit var buttonCalculator: Button
        private lateinit var textGoBack: TextView

        private lateinit var sharedPreferences: SharedPreferences

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_profile)

            etName = findViewById(R.id.etNameProfile)
            etLastName = findViewById(R.id.etLastNameProfile)
            etEmail = findViewById(R.id.etEmailProfile)
            etPhone = findViewById(R.id.etPhoneProfile)
            buttonEdit = findViewById(R.id.buttonEditProfile)
            buttonChangePhoto = findViewById(R.id.buttonChangePhoto)
            buttonCalculator = findViewById(R.id.buttonActivityCentral)
            textGoBack = findViewById(R.id.textGoBackProfile)

            sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

            loadData()

            buttonEdit.setOnClickListener {
                if (validateFields()) {
                    editData()
                    redirection(ProfileActivity::class.java)
                }
            }

            buttonChangePhoto.setOnClickListener {
                Toast.makeText(this, "Esta funcionalidad todavia no esta implementada", Toast.LENGTH_SHORT).show()
            }

            textGoBack.setOnClickListener {
                redirection(LoginActivity::class.java)
            }

            buttonCalculator.setOnClickListener {
                redirection(CentralActivity::class.java)
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

            if (isFieldEmpty(etName, "El campo nombre es requerido")) return false
            if (isFieldEmpty(etLastName, "El campo apellido es requerido")) return false
            if (isFieldEmpty(etEmail, "El campo email es requerido")) return false
            if (isFieldEmpty(etPhone, "El campo teléfono es requerido")) return false

            if ( etPhone.text.toString().trim().length < 10) {
                Toast.makeText(this, "El número de teléfono debe tener al menos 10 dígitos", Toast.LENGTH_SHORT).show()
                return false
            }

            if (!etEmail.text.toString().trim().matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
                Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show()
                return false
            }


            return true
        }

        private fun editData() {
            val control = sharedPreferences.edit()
            control.putString("name", etName.text.toString().trim())
            control.putString("lastName", etLastName.text.toString().trim())

            try {
                control.putLong("phone", etPhone.text.toString().trim().toLong())
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "El número de teléfono no es válido", Toast.LENGTH_SHORT).show()
                return
            }

            control.putString("email", etEmail.text.toString().trim())
            control.apply()
            Toast.makeText(this, "Se editaron los datos correctamente", Toast.LENGTH_SHORT).show()
        }

        private fun loadData() {
            etName.setText(sharedPreferences.getString("name", ""))
            etLastName.setText(sharedPreferences.getString("lastName", ""))
            etPhone.setText(sharedPreferences.getLong("phone", 0).toString())
            etEmail.setText(sharedPreferences.getString("email", ""))
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