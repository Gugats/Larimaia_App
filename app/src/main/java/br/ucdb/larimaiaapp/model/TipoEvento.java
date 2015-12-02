package br.ucdb.larimaiaapp.model;

import java.io.Serializable;

/**
 * Created by williamluciodonascimento on 30/09/15.
 */
public class TipoEvento implements Serializable {

    private Integer idTipoEvento;
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(Integer idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
