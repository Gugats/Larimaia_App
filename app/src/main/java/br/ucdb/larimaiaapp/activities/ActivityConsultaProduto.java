package br.ucdb.larimaiaapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;
import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Produto;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by MarceloPC on 30/09/2015.
 */
public class ActivityConsultaProduto extends AppCompatActivity {

    @Bind(R.id.lista_consulta)
    ListView lista;

    List<Produto> produtos;

    ArrayAdapter<Produto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lista);

        ButterKnife.bind(this);

        listar();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Produto pro = (Produto) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(ActivityConsultaProduto.this, ActivityProduto.class);
                intent.putExtra("Produto", pro);
                startActivity(intent);

                //Chamando nova activity passando um Objeto no Bundle para ser editado no form
                //startActivity(new Intent(ActivityConsultaCliente.this, ActivityCliente.class).putExtra(ActivityCliente.EDIT_KEY_COURSE, adapterView.getItem(position)));
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Produto pro = (Produto) parent.getItemAtPosition(position);
                AlertDialog alert = new AlertDialog.Builder(ActivityConsultaProduto.this)
                        .setTitle("Excluir")
                        .setMessage("Deseja realmente excluir?")
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, final int i) {
                                //startLoading();
                                //Chamando API para remover
                                ApiWeb.getRotas().excluirProduto(pro.getId(), new Callback<Response>() {
                                    @Override
                                    public void success(Response response, Response response2) {
                                        listar();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(ActivityConsultaProduto.this, "Erro ao conectar com o servidor", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).show();
                return true;
            }

        });
    }

    //Botão para voltar a tela anterior, tela de cadastros
    @OnClick(R.id.btn_lista_voltar)
    public void voltar(){
        Intent it = new Intent(this,ActivityCadastros.class);
        startActivity(it);
    }
    //Botão para ir a tela de cadastro deste obj
    @OnClick(R.id.btn_cadastrar)
    public void cadastrar(){
        Intent irParaOpcao =  new Intent(this, ActivityProduto.class);
        startActivity(irParaOpcao);
    }

    public void listar(){
        ApiWeb.getRotas().listarProduto(new Callback<List<Produto>>() {
            @Override
            public void success(List<Produto> produtos, Response response) {
                //Carrengando no ListView
                adapter = new ArrayAdapter<>(ActivityConsultaProduto.this, android.R.layout.simple_list_item_1, produtos);
                lista.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });
    }



}
