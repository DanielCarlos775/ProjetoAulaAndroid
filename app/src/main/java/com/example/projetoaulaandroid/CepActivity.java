package com.example.projetoaulaandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep);

        EditText txtCep = findViewById(R.id.txtCep);
        TextView txtResultadoCep = findViewById(R.id.txtResultadoCep);
        Button btnConsultaCep = findViewById(R.id.btnConsultaCep);

        btnConsultaCep.setOnClickListener(v -> {
            String cep = txtCep.getText().toString();
            if (!cep.isEmpty()) {
                buscarEndereco(cep, txtResultadoCep);
            } else {
                txtResultadoCep.setText("Informe um CEP válido");
            }
        });
    }

    private void buscarEndereco(String cep, TextView txtResultadoCep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        new Thread(() -> {
            try {
                URL apiUrl = new URL(url);
                HttpURLConnection conexao = (HttpURLConnection) apiUrl.openConnection();
                conexao.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                StringBuilder resultado = new StringBuilder();
                String linha;

                while ((linha = reader.readLine()) != null) {
                    resultado.append(linha);
                }
                reader.close();

                JSONObject json = new JSONObject(resultado.toString());
                String Rua = json.optString("logradouro", "");
                String Bairro = json.optString("bairro", "");
                String Cidade = json.optString("localidade", "");
                String UF = json.optString("uf", "");
                String EnderecoCompleto = Rua + ", " + Bairro + "\n" + Cidade + "-" + UF;

                runOnUiThread(() -> txtResultadoCep.setText(EnderecoCompleto));

            } catch (Exception e) {
                runOnUiThread(() -> txtResultadoCep.setText("Erro ao Buscar Endereço"));
            }
        }).start();
    }
}
