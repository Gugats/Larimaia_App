package br.ucdb.larimaiaapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.ucdb.larimaiaapp.model.Cliente;
import br.ucdb.larimaiaapp.model.ItemPedido;
import br.ucdb.larimaiaapp.model.Pedido;
import br.ucdb.larimaiaapp.model.Produto;
import br.ucdb.larimaiaapp.model.TipoEvento;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Junio on 23/09/2015.
 */

public class ApiWeb {

    //SEMPRE ALTERAR NO CAMINHO O IP, COLOCAR O DA MÁQUINA AONDE ESTÁ O SERVIDOR WEB
    public static final String BASE_URL = "http://192.168.0.10:8080/larimaiawebapi/ws";


    public static Rotas rotasApi;

    public static Rotas getRotas() {
        if (rotasApi == null) {

            //Serializador Client  Json
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            //Inicializa o Rest Client
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setConverter(new GsonConverter(gson))
                    .setEndpoint(String.format("%s", BASE_URL))
                    .build();

            //Objeto rest
            rotasApi = restAdapter.create(Rotas.class);
        }

        return rotasApi;
    }

    public interface Rotas{

        //Produto
        @POST("/produto/salvar")
        public void salvarProduto(@Body Produto produto, Callback<Response> callback);
        @GET("/produto/editar/{id}")
        public void editarProduto(@Path("id") long id, Callback<Produto> callback);
        @DELETE("/produto/excluir/{id}")
        public void excluirProduto(@Path("id") long id, Callback<Response> callback);
        @GET("/produto/listar")
        public void listarProduto(Callback<List<Produto>> callback);

        //Cliente
        @POST("/cliente/salvar")
        public void salvarCliente(@Body Cliente cliente, Callback<Response> callback);
        @GET("/cliente/editar/{id}")
        public void editarCliente(@Path("id") long id, Callback<Cliente> callback);
        @DELETE("/cliente/excluir/{id}")
        public void excluirCliente(@Path("id") long id, Callback<Response> callback);
        @GET("/cliente/lista")
        public void listaClientes(Callback<List<Cliente>> callback);


        //TipoEvento
        @POST("/tipoevento/salvar")
        public void salvarTipoEvento(@Body TipoEvento tipoevento, Callback<Response> callback);
        @GET("/tipoevento/editar/{id}")
        public void editarTipoEvento(@Path("id") long id, Callback<TipoEvento> callback);
        @DELETE("/tipoevento/excluir/{id}")
        public void excluirTipoEvento(@Path("id") long id, Callback<Response> callback);
         @GET("/tipoevento/listar")
         public void listaTipoEventos(Callback<List<TipoEvento>> callback);


        //Pedido
        @POST("/pedido/salvar")
        public void salvarPedido(@Body Pedido pedido, Callback<Pedido> callback);
        @GET("/pedido/editar/{id}")
        public void editarPedido(@Path("id") long id, Callback<Pedido> callback);
        @DELETE("/pedido/excluir/{id}")
        public void excluirPedido(@Path("id") long id, Callback<Response> callback);

        //ItemPedido
        @POST("/itempedido/salvar")
        public void salvarItemPedido(@Body ItemPedido itemPedido, Callback<Response> callback);
        @GET("/itempedido/editar/{id}")
        public void editarItemPedido(@Path("id") long id, Callback<Pedido> callback);
        @DELETE("/itempedido/excluir/{id}")
        public void excluirItemPedido(@Path("id") long id, Callback<Response> callback);

    }
}
