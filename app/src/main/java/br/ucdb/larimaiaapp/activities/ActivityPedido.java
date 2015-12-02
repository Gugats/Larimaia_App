package br.ucdb.larimaiaapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Cliente;
import br.ucdb.larimaiaapp.model.ItemPedido;
import br.ucdb.larimaiaapp.model.Pedido;
import br.ucdb.larimaiaapp.model.Produto;
import br.ucdb.larimaiaapp.model.TipoEvento;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by MarcosVinicius on 23/09/2015.
 */
public class ActivityPedido extends AppCompatActivity{

    Integer qtdInt;

    @Bind(R.id.tx_lc)
    EditText textLocal;

    @Bind(R.id.txt_origem)
    EditText origem;

    @Bind(R.id.tx_cerimonial)
    EditText textCerimonial;

    @Bind(R.id.txt_he)
    EditText horaEvento;

    @Bind(R.id.txt_dte)
    EditText dataEvento;

    @Bind(R.id.txt_dtped)
    EditText dataPedido;

    @Bind(R.id.tx_indicacao)
    EditText indicacao;

    @Bind(R.id.txt_obs)
    EditText obs;

    @Bind(R.id.txt_endereco)
    EditText endereco;

    @Bind(R.id.txt_qtd)
    EditText quantidade;

    @Bind(R.id.lv_cli)
    Spinner cliente;

    @Bind(R.id.spi_pro)
    Spinner produto;

    @Bind(R.id.spi_tipo_evento)
    Spinner tipoEvento;

    @Bind(R.id.listaItens)
    ListView listaViewIP;

    private static List<ItemPedido> listaIP = new ArrayList<>();
    private List<Cliente> listaClientes;
    Pedido pedido = new Pedido();

    ArrayAdapter<ItemPedido> adapter;

    @OnClick(R.id.btn_add_item_pedido)
    public void addItemPedido(){
        ItemPedido ip = new ItemPedido();

        adapter.clear();
        adapter = new ArrayAdapter<ItemPedido>(ActivityPedido.this, android.R.layout.simple_list_item_activated_1);
        listaViewIP.setAdapter(adapter);

        if(quantidade.getText()!=null) {
            String qtd = quantidade.getText().toString();
            qtdInt = Integer.parseInt(qtd);
            ip.setQuantidade(qtdInt);
        }

        Produto pro = (Produto) produto.getSelectedItem();
        ip.setIdProduto(pro);
        Double total = pro.getValor() * qtdInt;
        ip.setValorItem(total);

        listaIP.add(ip);

        adapter.addAll(listaIP);

        adapter.notifyDataSetChanged();
        //listaViewIP.invalidateViews();
    }

    @OnClick(R.id.btn_salvar_evento_pedido)
    public void salvar(){


        if(origem.getText()!=null) {
            pedido.setOrigemPedido(origem.getText().toString());
        }

        if (textLocal.getText() != null)
            pedido.setLocalContrato(textLocal.getText().toString());

        if (textCerimonial.getText() != null)
            pedido.setCerimonial(textCerimonial.getText().toString());

        if(horaEvento.getText() != null)
            pedido.setHora(horaEvento.getText().toString());

        if(dataEvento.getText() != null) {
            Date date = null;
            String dataText = new String(dataEvento.getText().toString());
            pedido.setDataEvento(dataText);
        }

        if(dataPedido.getText() != null){
            Date date = null;
            String dataText = new String(dataPedido.getText().toString());
            pedido.setDataPedido(dataText);
        }

        if(indicacao.getText()!= null)
            pedido.setIndicacao(indicacao.getText().toString());

        if(obs.getText()!=null)
            pedido.setObs(obs.getText().toString());

        if(endereco.getText()!=null){
            pedido.setEndereco(endereco.getText().toString());
        }

        pedido.setCliente((Cliente) cliente.getSelectedItem());

        pedido.setEvento((TipoEvento) tipoEvento.getSelectedItem());

        pedido.setListaItemPedido(listaIP);

        ApiWeb.getRotas().salvarPedido(pedido, new Callback<Pedido>() {
            @Override
            public void success(Pedido ped, Response response) {
                Toast.makeText(ActivityPedido.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        ButterKnife.bind(this);

        adapter = new ArrayAdapter<ItemPedido>(ActivityPedido.this, android.R.layout.simple_list_item_activated_1);

        ApiWeb.getRotas().listaClientes(new Callback<List<Cliente>>() {
            @Override
            public void success(List<Cliente> clientes, Response response) {
                ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(ActivityPedido.this, android.R.layout.simple_list_item_activated_1, clientes);
                cliente.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        ApiWeb.getRotas().listarProduto(new Callback<List<Produto>>() {
            @Override
            public void success(List<Produto> produtos, Response response) {
                ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(ActivityPedido.this, android.R.layout.simple_list_item_activated_1, produtos);
                produto.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        ApiWeb.getRotas().listaTipoEventos(new Callback<List<TipoEvento>>() {
            @Override
            public void success(List<TipoEvento> tiposEventos, Response response) {
                ArrayAdapter<TipoEvento> adapter = new ArrayAdapter<TipoEvento>(ActivityPedido.this, android.R.layout.simple_list_item_activated_1, tiposEventos);
                tipoEvento.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}