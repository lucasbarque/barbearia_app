package br.estacio.cadastrodeclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.estacio.cadastrodeclientes.adapter.ClienteAdapater;
import br.estacio.cadastrodeclientes.model.Cliente;

public class MainActivity extends AppCompatActivity {

    private ListView listaCliente;
    private ClienteAdapater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCliente = (ListView) findViewById(R.id.listaCliente);

        Cliente c1 = new Cliente();
        c1.setNome("Carlos");
        c1.setFone("123");
        Cliente c2 = new Cliente();
        c2.setNome("Marcelo");
        c2.setFone("32323");
        Cliente c3 = new Cliente();
        c3.setNome("Vinicius");
        c3.setFone("656465");
        Cliente c4 = new Cliente();
        c4.setNome("Caio");
        c4.setFone("3333");
        List<Cliente> clientes = new ArrayList();
        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        clientes.add(c4);


        adapter = new ClienteAdapater(this, clientes);
        listaCliente.setAdapter(adapter);
        listaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente clienteSelecionado = adapter.getItem(position);

                Intent cadCliente = new Intent(MainActivity.this, ClienteActivity.class);
                cadCliente.putExtra("clienteSelecionado", clienteSelecionado);
                startActivity(cadCliente);
            }
        });


    }
}
