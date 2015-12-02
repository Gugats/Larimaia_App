package br.ucdb.larimaiaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Produto;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityProduto extends AppCompatActivity {

    static boolean valida = false;
    static Produto pro ;
    @Bind(R.id.et_descricao)
    EditText etDescricao;
    @Bind(R.id.et_valor)
    EditText etValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Produto pro = (Produto) intent.getSerializableExtra("Produto");
        if(pro!=null){
            TelaEditar(pro);
        }
    }

    @OnClick(R.id.btn_salvar_produto)
    public void salvar() {
        if(valida==false) {
            Produto p = new Produto();
            p.setDescricao(etDescricao.getText().toString());
            p.setValor(Double.parseDouble(etValor.getText().toString()));

            ApiWeb.getRotas().salvarProduto(p, new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    Toast.makeText(ActivityProduto.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    etDescricao.setText("");
                    etValor.setText("");
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ActivityProduto.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            editar();
        }

    }


    @OnClick(R.id.btn_listar_produto)
    public void listar() {
        Intent irParaTelaListar = new Intent(this, ActivityConsultaProduto.class);
        startActivityForResult(irParaTelaListar,1 );
    }

    public void editar(){
        Produto p = pro;
        p.setDescricao(etDescricao.getText().toString());
        p.setValor(Double.parseDouble(etValor.getText().toString()));

        ApiWeb.getRotas().salvarProduto(p, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityProduto.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityProduto.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
            }
        });
        valida=false;
    }

    public void TelaEditar(Produto p){
        pro = p;
        valida = true;
        etDescricao.setText(p.getDescricao());
        etValor.setText(String.valueOf(p.getValor()));
    }

    //Botão para voltar a tela anterior, tela de cadastros
    @OnClick(R.id.btn_voltar)
    public void voltar(){
        Intent it = new Intent(this,ActivityCadastros.class);
        startActivity(it);
    }
}

