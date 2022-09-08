package com.example.evalucacionprimerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String USUARIO="";
    private final String PASSWORD = "";

    private Button btnAceptar;
    private Button btnSalir;

    private EditText usuarioM;
    private EditText passwordM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioM = findViewById(R.id.textUsuarioMain);
        passwordM = findViewById(R.id.textPasswordMain);

        btnAceptar = findViewById(R.id.btnMainAceptar);
        btnSalir = findViewById(R.id.btnMainSalir);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usu = usuarioM.getText().toString().trim();
                String pas = passwordM.getText().toString();
                if(usu.compareTo(USUARIO)==0)
                {
                    if(pas.compareTo(PASSWORD)==0)
                    {
                        Intent intent = new Intent(MainActivity.this, Menu.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"Constraseña Incorrecta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Usuario no registrado",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hasta pronto", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        });

    }
}