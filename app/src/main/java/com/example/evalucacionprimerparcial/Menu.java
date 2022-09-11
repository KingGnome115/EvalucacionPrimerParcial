package com.example.evalucacionprimerparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    private ImageButton btnAcercaDe;
    private ImageButton btnTelevision;
    private ImageButton btnGaleria;
    private ImageButton btnCamara;
    private ImageButton btnLinterna;
    private ImageButton btnPestanias;
    private ImageButton btnSalida;

    private boolean encendia = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnAcercaDe = findViewById(R.id.btnMenuAcercaDe);
        btnTelevision = findViewById(R.id.btnMenuTelevision);
        btnGaleria = findViewById(R.id.btnMenuGeleria);
        btnCamara = findViewById(R.id.btnMenuCamara);
        btnLinterna = findViewById(R.id.btnMenuLinterna);
        btnPestanias = findViewById(R.id.btnMenuPestanias);
        btnSalida = findViewById(R.id.btnMenuSalida);

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        final String[] cameraId = {null};

        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCreditos();
            }
        });

        btnTelevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Television.class);
                startActivity(intent);
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Galeria.class);
                startActivity(intent);
            }
        });

        btnLinterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!encendia)
                {
                    try
                    {
                        cameraId[0] = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId[0], true);
                        encendia = true;

                    }catch (CameraAccessException e){
                        e.printStackTrace();
                    }
                }else{
                    try
                    {
                        cameraId[0] = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId[0], false);
                        encendia = false;
                    }catch (CameraAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, CamaraP.class);
                startActivity(intent);
            }
        });

        btnPestanias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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