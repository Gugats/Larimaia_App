package br.ucdb.larimaiaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

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
 * Created by williamluciodonascimento on 30/09/15.
 */
public class ActivityTipoEvento extends AppCompatActivity {


    static boolean valida = false;

    static TipoEvento tipoEvento ;

    @Bind(R.id.et_descricao)
    EditText etDescricao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipoevento);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        TipoEvento te = (TipoEvento) intent.getSerializableExtra("TipoEvento");
        if(te!=null){
            TelaEditar(te);
        }
    }

    @OnClick(R.id.btn_salvar_tipo_evento)
    public void salvar() {
        if(valida==false) {
            TipoEvento te = new TipoEvento();
            te.setDescricao(etDescricao.getText().toString());

            ApiWeb.getRotas().salvarTipoEvento(te, new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    Toast.makeText(ActivityTipoEvento.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    etDescricao.setText("");
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ActivityTipoEvento.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            editar();
        }

    }


    @OnClick(R.id.btn_listar_tipo_evento)
    public void listar() {
        Intent irParaTelaListar = new Intent(this, ActivityConsultaTipoEvento.class);
        startActivityForResult(irParaTelaListar, 1);
    }

    public void editar(){
        TipoEvento te = tipoEvento;
        te.setDescricao(etDescricao.getText().toString());

        ApiWeb.getRotas().salvarTipoEvento(te, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityTipoEvento.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityTipoEvento.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
            }
        });
        valida=false;
    }

    public void TelaEditar(TipoEvento te){
        tipoEvento = te;
        valida = true;
        etDescricao.setText(te.getDescricao());
    }

    //Botão para voltar a tela anterior, tela de cadastros
    @OnClick(R.id.btn_voltar)
    public void voltar(){
        Intent it = new Intent(this,ActivityCadastros.class);
        startActivity(it);
    }
}

