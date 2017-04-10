package br.estacio.cadastrodeclientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.estacio.cadastrodeclientes.adapter.ClienteAdapater;
import br.estacio.cadastrodeclientes.dao.ClienteDAO;
import br.estacio.cadastrodeclientes.model.Cliente;

public class MainActivity extends AppCompatActivity {

    private ListView listaCliente;
    private ClienteAdapater adapter;
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCliente = (ListView) findViewById(R.id.listaCliente);
        listaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente clienteSelecionado = adapter.getItem(position);

                Intent cadCliente = new Intent(MainActivity.this, ClienteActivity.class);
                cadCliente.putExtra("clienteSelecionado", clienteSelecionado);
                startActivity(cadCliente);
            }
        });

        Button btnNovoAluno = (Button) findViewById(R.id.btnNovoAluno);
        btnNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ClienteDAO dao = new ClienteDAO(this);
        clientes = dao.list();
        dao.close();

        adapter = new ClienteAdapater(this, clientes);
        listaCliente.setAdapter(adapter);
    }
}
