package br.ucdb.larimaiaapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.TipoEvento;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Junio on 10/10/2015.
 */
public class ActivityConsultaTipoEvento extends AppCompatActivity {

    @Bind(R.id.lista_consulta)
    ListView lista;

    List<TipoEvento> tipoeventos;

    ArrayAdapter<TipoEvento> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lista);

        ButterKnife.bind(this);

        listar();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final TipoEvento te = (TipoEvento) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(ActivityConsultaTipoEvento.this, ActivityTipoEvento.class);
                intent.putExtra("TipoEvento", te);
                startActivity(intent);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final TipoEvento te = (TipoEvento) parent.getItemAtPosition(position);
                AlertDialog alert = new AlertDialog.Builder(ActivityConsultaTipoEvento.this)
                        .setTitle("Excluir")
                        .setMessage("Deseja realmente excluir?")
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, final int i) {
                                //startLoading();
                                //Chamando API para remover
                                ApiWeb.getRotas().excluirTipoEvento(te.getIdTipoEvento(), new Callback<Response>() {
                                    @Override
                                    public void success(Response response, Response response2) {
                                        listar();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(ActivityConsultaTipoEvento.this, "Erro ao conectar com o servidor", Toast.LENGTH_SHORT).show();
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
        Intent it = new Intent(this,ActivityTipoEvento.class);
        startActivity(it);
    }
    //Botão para ir a tela de cadastro deste obj
    @OnClick(R.id.btn_cadastrar)
    public void cadastrar(){
        Intent irParaOpcao =  new Intent(this, ActivityTipoEvento.class);
        startActivity(irParaOpcao);
    }

    public void listar(){
        ApiWeb.getRotas().listaTipoEventos(new Callback<List<TipoEvento>>() {
            @Override
            public void success(List<TipoEvento> tipoEventos, Response response) {
                //Carrengando no ListView
                adapter = new ArrayAdapter<>(ActivityConsultaTipoEvento.this, android.R.layout.simple_list_item_1, tipoEventos);
                lista.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });
    }



}
