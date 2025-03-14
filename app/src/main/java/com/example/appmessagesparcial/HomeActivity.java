package com.example.appmessagesparcial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    private TextView textRegister;
    private Button buttonBegin;

    @SuppressLint("MissingSuperCall")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        buttonBegin= findViewById(R.id.buttonBeginHome);
        textRegister= findViewById(R.id.textRegisterHome);

        buttonBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection(LoginActivity.class);
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirection(RegisterActivity.class);
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


    //Functions Apart from the life cycles of the activity
    private View.OnClickListener redirection(Class<?> destinationActivity){
        Intent intent = new Intent(this, destinationActivity);
        startActivity(intent);
        return null;
    }
}
