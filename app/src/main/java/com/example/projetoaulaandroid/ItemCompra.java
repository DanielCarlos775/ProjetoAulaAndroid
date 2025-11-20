package com.example.projetoaulaandroid;

public class ItemCompra {

    private int sequencial;
    private String descricao;
    private int quantidade;

    public ItemCompra (int sequencial, String descricao, int quantidade) {
        this.sequencial = sequencial;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
