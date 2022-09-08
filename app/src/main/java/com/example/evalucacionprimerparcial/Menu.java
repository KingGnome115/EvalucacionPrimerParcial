package com.example.evalucacionprimerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    private ImageButton btnAcercaDe;
    private ImageButton btnTelevision;
    private ImageButton btnGaleria;
    private ImageButton btnLinterna;
    private ImageButton btnPestanias;
    private ImageButton btnSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnAcercaDe = findViewById(R.id.btnMenuAcercaDe);
        btnTelevision = findViewById(R.id.btnMenuTelevision);
        btnGaleria = findViewById(R.id.btnMenuGeleria);
        btnLinterna = findViewById(R.id.btnMenuLinterna);
        btnPestanias = findViewById(R.id.btnMenuPestanias);
        btnSalida = findViewById(R.id.btnMenuSalida);

        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCreditos();
            }
        });

    }

    private void getCreditos() {
        new AlertDialog.Builder(this)
                .setTitle("Acerca de")
                .setMessage(""+
                        "Kevin Jesús Escutia Ceja\n"
                        + "Profesora: Rocio Elizabeth Alba\n"
                        + "Movil 2022B\n"
                        + "vrs 1\n"
                        + "Evaluacion 1mer Parcial")
                .setPositiveButton("Aceptar",null)
                .show();
    }
}