package com.example.projetoaulaandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItemCompraAdapter extends ArrayAdapter<ItemCompra> {

    public ItemCompraAdapter(@NonNull Context context, @NonNull List<ItemCompra> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View linhaView = convertView;

        if (linhaView == null) {
            linhaView = LayoutInflater.from(getContext()).inflate(R.layout.item_lista_compra, parent, false);
        }

        ItemCompra item = getItem(position);

        TextView tvSequencial = linhaView.findViewById(R.id.tvSequencial);
        TextView tvDescricao = linhaView.findViewById(R.id.tvDescricao);
        TextView tvQuantidade = linhaView.findViewById(R.id.tvQuantidade);

        if (item != null) {
            tvSequencial.setText(String.valueOf(item.getSequencial()));
            tvDescricao.setText(item.getDescricao());
            tvQuantidade.setText("x" + item.getQuantidade());
        }

        return linhaView;
    }

}
