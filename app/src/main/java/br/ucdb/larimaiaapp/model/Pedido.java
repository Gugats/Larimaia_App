package br.ucdb.larimaiaapp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Gustavo on 07/10/2015.
 */
public class Pedido {
    private String indicacao;
    private String observacao;
    private String localEvento;
    private String origemPedido;
    private String horaEvento;
    private String endereco;
    private String cerimonial;
    private int  id;
    private String dataEvento, dataPedido;
    private Cliente idCliente;
    private TipoEvento idTipoEvento;
    public List<ItemPedido> itemPedidoCollection;

    public String getCerimonial() {
        return cerimonial;
    }

    public void setCerimonial(String cer) {
        cerimonial = cer;
    }

    public String getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

    public String getObs() {
        return observacao;
    }

    public void setObs(String obs) {
        this.observacao = obs;
    }


    public String getOrigemPedido() {
        return origemPedido;
    }

    public void setOrigemPedido(String origem) {
        this.origemPedido = origem;
    }

    public String getLocalContrato() {
        return localEvento;
    }

    public void setLocalContrato(String localContrato) {
        this.localEvento = localContrato;
    }

    public String getHora() {
        return horaEvento;
    }

    public void setHora(String hora) {
        this.horaEvento = hora;
    }

    public TipoEvento getEvento() {
        return idTipoEvento;
    }

    public void setEvento(TipoEvento evento) {
        this.idTipoEvento = evento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return idCliente;
    }

    public void setCliente(Cliente cliente) {
        this.idCliente = cliente;
    }

    public List<ItemPedido> getListaItemPedido() {
        return itemPedidoCollection;
    }

    public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
        this.itemPedidoCollection = listaItemPedido;
    }

}