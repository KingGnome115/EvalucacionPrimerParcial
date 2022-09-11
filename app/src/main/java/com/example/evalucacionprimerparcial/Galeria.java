package com.example.evalucacionprimerparcial;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class Galeria extends AppCompatActivity {

    private ImageSwitcher imageIs;
    private com.google.android.material.button.MaterialButton btnAntes;
    private com.google.android.material.button.MaterialButton btnSig;
    private com.google.android.material.button.MaterialButton btnPick;

    private ArrayList<Uri> imagenUri;
    private int position = 0;
    private ActivityResultLauncher<Intent> intentLaucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        imageIs = findViewById(R.id.ISGaleriaImagen);
        btnAntes = findViewById(R.id.btnAntesGaleria);
        btnSig = findViewById(R.id.btnSiguienteGaleria);
        btnPick = findViewById(R.id.btnBuscarGaleria);
        imagenUri = new ArrayList<Uri>();
        setUpImageSwitcher();
        activityResult();

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageIntent();
            }
        });
        btnAntes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position > 0)
                {
                    position--;
                }else{
                    position = imagenUri.size() -1;
                }
                imageIs.setImageURI(imagenUri.get(position));
            }
        });
        btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position < imagenUri.size() -1)
                {
                    position++;
                }else{
                    position = 0;
                }
                imageIs.setImageURI(imagenUri.get(position));
            }
        });
    }

    private void pickImageIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intentLaucher.launch(Intent.createChooser(intent, "Selecciona las imagenes"));
    }

    private void activityResult(){
        intentLaucher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()== Activity.RESULT_OK)
                    {
                        Uri imaUri;
                        if(result.getData().getClipData() != null)
                        {
                            int count = result.getData().getClipData().getItemCount();
                            for(int i = 0; i < count; i++)
                            {
                                imaUri = result.getData().getClipData().getItemAt(i).getUri();
                                imagenUri.add(imaUri);
                            }
                            if(count < 16 && count > 10)
                            {
                                imageIs.setImageURI(imagenUri.get(0));
                                position = 0;
                            }else{
                                if(count > 15)
                                    Toast.makeText(Galeria.this, "Maximo 15 imagenes", Toast.LENGTH_SHORT).show();
                                if(count < 10)
                                    Toast.makeText(Galeria.this, "Minimo 10 imagenes", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Galeria.this, "Minimo 10 imagenes", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setUpImageSwitcher(){
        imageIs.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(getApplicationContext());
            }
        });
    }
}