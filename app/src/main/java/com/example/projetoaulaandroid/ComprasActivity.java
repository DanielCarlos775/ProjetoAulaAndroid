package com.example.projetoaulaandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ComprasActivity  extends AppCompatActivity {

    private ArrayList<ItemCompra> listaItens;
    private ItemCompraAdapter adapter;
    private int posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        EditText etDescricao = findViewById(R.id.etDescricao);
        EditText etQuantidade = findViewById(R.id.etQuantidade);
        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        Button btnRemover = findViewById(R.id.btnRemover);
        ListView lvItens = findViewById(R.id.lvItens);

        listaItens = new ArrayList<>();
        adapter = new ItemCompraAdapter(this, listaItens);
        lvItens.setAdapter(adapter);

        lvItens.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
                ItemCompra item = listaItens.get(position);
                Toast.makeText(MainActivity.this, "selecionado: " + item.getDescricao(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}
