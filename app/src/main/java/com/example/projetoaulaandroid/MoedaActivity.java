package com.example.projetoaulaandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MoedaActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moeda);

        TextView txtResultadoMoeda = findViewById(R.id.txtResultadoMoeda);
        EditText txtValor = findViewById(R.id.txtValor);
        Spinner spMoeda = findViewById(R.id.spMoeda);
        Button btnConverterMoeda = findViewById(R.id.btnConverterMoeda);

        final double taxaDolar = 0.20;
        final double taxaEuro = 0.18;

        //Criar Lista de Opções
        String[] moedas = {"Dólar", "Euro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                moedas);

        //Define o Layout do Spinner(Dropdown)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Aplicar os valores ao Spinner
        spMoeda.setAdapter(adapter);

        btnConverterMoeda.setOnClickListener(v -> {
            double valor = Double.parseDouble(txtValor.getText().toString());
            double resultado = 0;
            String moeda = spMoeda.getSelectedItem().toString();

            if (moeda.equals("Dólar")) {
                resultado = valor * taxaDolar;
                if (resultado >= 2) {
                    txtResultadoMoeda.setText(
                            String.format("Valor Convertido: %.2f %ses", resultado, moeda)
                    );
                }
            } else if (moeda.equals("Euro")) {
                resultado = valor * taxaEuro;
                if (resultado >= 2) {
                    txtResultadoMoeda.setText(
                            String.format("Valor Convertido: %.2f %ss", resultado, moeda)
                    );
                }
            }
            if (resultado < 2) {
                txtResultadoMoeda.setText(
                        String.format("Valor Convertido: %.2f %s", resultado, moeda)
                );
            }
        });
    }
}
