package br.ucdb.larimaiaapp.model;

import java.io.Serializable;

/**
 * Created by Mar_Ju on 23/09/2015.
 */
public class Produto implements Serializable {

    private Long idProduto;
    private String descricao;
    private double valor;


    public Long getId() {
        return idProduto;
    }

    public void setId(Long id) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return descricao;
    }
}


