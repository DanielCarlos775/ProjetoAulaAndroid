package com.example.projetoaulaandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class FrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frases);

        TextView txtFrase = findViewById(R.id.txtFrase);
        ImageView imgFrase = findViewById(R.id.imgFrase);
        Button btnFrase = findViewById(R.id.btnFrase);

        int[] imagens = {
                R.drawable.img01,
                R.drawable.img02,
                R.drawable.img03,
                R.drawable.img04,
                R.drawable.img05,
                R.drawable.img06
        };

        String[] frases = {
                "Fica esperando o universo te ajudar… ele nem sabe que você existe.",
                "Quer um milagre? Começa levantando da cama.",
                "Você diz que não tem sorte, mas a sorte fugiu porque cansou de te esperar agir.",
                "Nada muda na sua vida porque você continua sendo o mesmo.",
                "O sucesso não é difícil — difícil é largar a preguiça que te abraça todo dia.",
                "A vida não tá contra você… ela só tá testando se você merece o que diz querer."
        };

        int numero1 = new Random().nextInt(frases.length);
        txtFrase.setText(frases[numero1]);
        imgFrase.setImageResource(imagens[numero1]);

        btnFrase.setOnClickListener(v -> {
            int numero = new Random().nextInt(frases.length);
            txtFrase.setText(frases[numero]);
            imgFrase.setImageResource(imagens[numero]);
        });
    }
}
