package com.example.projetoaulaandroid;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ComprasActivity extends AppCompatActivity {

    private EditText etDescricao;
    private EditText etQuantidade;
    private Button btnAdicionar;
    private Button btnAtualizar;
    private Button btnRemover;
    private ListView lvItens;
    private ArrayList<ItemCompra> listaItens;
    private ItemCompraAdapter adapter;
    private int posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        etDescricao = findViewById(R.id.etDescricao);
        etQuantidade = findViewById(R.id.etQuantidade);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnRemover = findViewById(R.id.btnRemover);
        lvItens = findViewById(R.id.lvItens);

        listaItens = new ArrayList<>();
        adapter = new ItemCompraAdapter(this, listaItens);
        lvItens.setAdapter(adapter);

        lvItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
                ItemCompra item = listaItens.get(position);
                etDescricao.setText(item.getDescricao());
                etQuantidade.setText(String.valueOf(item.getQuantidade()));
                Toast.makeText(ComprasActivity.this,
                        "Selecionado: " + item.getDescricao(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarItem();
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarItemSelecionado();
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerItemSelecionado();
            }
        });
    }

    private void adicionarItem() {
        String descricao = etDescricao.getText().toString().trim();
        String quantidadeStr = etQuantidade.getText().toString().trim();

        if (descricao.isEmpty()) {
            etDescricao.setError("Informe a Descrição");
            etDescricao.requestFocus();
            return;
        }

        if (quantidadeStr.isEmpty()) {
            etQuantidade.setError("Informe a Quantidade");
            etQuantidade.requestFocus();
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            etQuantidade.setError("Quantidade Inválida");
            etQuantidade.requestFocus();
            return;
        }

        if (quantidade <= 0) {
            etQuantidade.setError("Quantidade deve ser maior que zero");
            etQuantidade.requestFocus();
            return;
        }

        int sequencial = listaItens.size() + 1;

        ItemCompra novoItem = new ItemCompra(sequencial, descricao, quantidade);
        listaItens.add(novoItem);

        adapter.notifyDataSetChanged();

        etDescricao.setText("");
        etQuantidade.setText("");
        etDescricao.requestFocus();

        posicaoSelecionada = -1;
    }

    private void atualizarItemSelecionado() {
        if (posicaoSelecionada == -1) {
            Toast.makeText(this,
                    "Selecione um itema da lista para atualizar",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (posicaoSelecionada < 0 || posicaoSelecionada > listaItens.size()) {
            Toast.makeText(this,
                    "Posição inválida. Tente selecionar novamente.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String novaDescricao = etDescricao.getText().toString().trim();
        String novaQuantidadeStr = etQuantidade.getText().toString().trim();

        if (novaQuantidadeStr.isEmpty()) {
            etQuantidade.setError("Informe a quantidade");
            etQuantidade.requestFocus();
            return;
        }

        int novaQuantidade;
        try {
            novaQuantidade = Integer.parseInt(novaQuantidadeStr);
        } catch (NumberFormatException e) {
            etQuantidade.setError("Quantidade Inválida");
            etQuantidade.requestFocus();
            return;
        }

        if (novaQuantidade <= 0) {
            etQuantidade.setError("Quantidade deve ser maior que zero");
            etQuantidade.requestFocus();
            return;
        }

        ItemCompra item = listaItens.get(posicaoSelecionada);

        if (novaDescricao.isEmpty()) {
            etDescricao.setError("Informe a Descrição");
            etDescricao.requestFocus();
            return;
        }

        item.setDescricao(novaDescricao);
        item.setQuantidade(novaQuantidade);

        adapter.notifyDataSetChanged();

        Toast.makeText(this,
                "Item atualizado com sucesso",
                Toast.LENGTH_SHORT).show();

        etDescricao.setText("");
        etQuantidade.setText("");
        etDescricao.requestFocus();
        posicaoSelecionada = -1;
    }

    private void removerItemSelecionado() {
        if (posicaoSelecionada == -1) {
            Toast.makeText(this,
                    "Selecione um item da lista para remover",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (posicaoSelecionada < 0 || posicaoSelecionada > listaItens.size()) {
            Toast.makeText(this,
                    "Posição inválida. Tente selecionar novamente.",
                    Toast.LENGTH_SHORT).show();
            posicaoSelecionada = -1;
            return;
        }

        listaItens.remove(posicaoSelecionada);

        for (int i = 0; i < listaItens.size(); i++) {
            listaItens.get(i).setSequencial(i + 1);
        }

        adapter.notifyDataSetChanged();

        posicaoSelecionada = -1;
    }
}
