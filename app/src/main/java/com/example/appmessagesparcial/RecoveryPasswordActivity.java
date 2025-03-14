package com.example.appmessagesparcial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appmessagesflash.LoginActivity;import java.util.regex.Pattern;

public class RecoveryPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button buttonRecoveryPassword;
    private TextView textGoBack;
    private SharedPreferences sharedPreferences;


    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recovery_password);

        etEmail= findViewById(R.id.etEmailRecoveryPassword);
        buttonRecoveryPassword= findViewById(R.id.buttonRecoveryPassword);
        textGoBack= findViewById(R.id.textGoBackRecoveryPassword);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);


        buttonRecoveryPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()){
                    if (etEmail.getText().toString().trim().equals(sharedPreferences.getString("email", ""))){
                        Toast.makeText(RecoveryPasswordActivity.this,"Se envio el correo exitosamente", Toast.LENGTH_SHORT).show();
                        redirection(LoginActivity.class);
                    } else {
                        Toast.makeText(RecoveryPasswordActivity.this,"El correo digitado no esta registrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        textGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection(LoginActivity.class);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart llamado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume llamado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause llamado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop llamado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Lifecycle", "onRestart llamado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy llamado");
    }

    private boolean validateFields(){
        if (etEmail.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"El campo correo electrónico es requerido", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matcher(etEmail.getText().toString().trim()).matches()) {
            Toast.makeText(this, "El correo electrónico no es válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    //Functions Apart from the life cycles of the activity
    private View.OnClickListener redirection(Class<?> destinationActivity){
        Intent intent = new Intent(this, destinationActivity);
        startActivity(intent);
        return null;
    }
}
