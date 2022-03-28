package com.example.aula05_list_view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQUISICAO_ESTADO = 1;
    private static final String ESTADO_SELECIONADO = "estado";

    private Button btnSelecionar;

    private String estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSelecionar = findViewById(R.id.btn_selecionar);

        btnSelecionar.setOnClickListener(e -> {
            Intent intent = new Intent(getBaseContext(), TelaEstadosActivity.class);
            intent.putExtra(TelaEstadosActivity.EXTRA_ESTADO, estado);
            startActivityForResult(intent, REQUISICAO_ESTADO);

        });

        if(savedInstanceState != null) {
            estado = savedInstanceState.getString(ESTADO_SELECIONADO);
            btnSelecionar.setText(estado);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUISICAO_ESTADO) {
            estado = data.getStringExtra(TelaEstadosActivity.EXTRA_RESULTADO);
            if(estado != null) {
                btnSelecionar.setText(estado);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ESTADO_SELECIONADO, estado);
    }
}