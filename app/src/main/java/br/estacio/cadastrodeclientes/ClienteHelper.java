package br.estacio.cadastrodeclientes;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import br.estacio.cadastrodeclientes.model.Cliente;

/**
 * Created by carlos on 27/03/17.
 */

public class ClienteHelper {

    private ClienteActivity activity;

    private EditText edtNome, edtMail, edtFone, edtCEP, edtEndereco,
    edtNumero, edtCidade;
    private RadioGroup rgSexo;
    private Button btnSalvarCliente;

    private Cliente cliente;

    public ClienteHelper(ClienteActivity activity) {
        this.activity = activity;
        edtNome = (EditText) activity.findViewById(R.id.edtNome);
        edtMail = (EditText) activity.findViewById(R.id.edtMail);
        edtFone = (EditText) activity.findViewById(R.id.edtFone);
        edtCEP = (EditText) activity.findViewById(R.id.edtCEP);
        edtEndereco = (EditText) activity.findViewById(R.id.edtEndereco);
        edtNumero = (EditText) activity.findViewById(R.id.edtNumero);
        edtCidade = (EditText) activity.findViewById(R.id.edtCidade);
        rgSexo = (RadioGroup) activity.findViewById(R.id.rgSexo);
        btnSalvarCliente = (Button) activity.findViewById(R.id.btnSalvarCliente);

        cliente = (Cliente) activity.getIntent().getSerializableExtra("clienteSelecionado");
        if (cliente != null) {
            carregaDadosParaTela(cliente);
        }
    }

    public Cliente carregaDadosDaTela() {
        cliente = new Cliente();
        cliente.setNome(edtNome.getText().toString());
        cliente.setEmail(edtMail.getText().toString());
        cliente.setFone(edtFone.getText().toString());
        cliente.setCEP(edtCEP.getText().toString());
        cliente.setEndereco(edtEndereco.getText().toString());
        cliente.setNumero(edtNumero.getText().toString());
        cliente.setCidade(edtCidade.getText().toString());
        cliente.setSexo(rgSexo.getCheckedRadioButtonId() == R.id.feminino ? 0 : 1);
        return cliente;
    }

    public void carregaDadosParaTela(Cliente cliente) {
        this.cliente = cliente;
        edtNome.setText(cliente.getNome());
        edtMail.setText(cliente.getEmail());
        edtFone.setText(cliente.getFone());
        edtCEP.setText(cliente.getCEP());
        edtEndereco.setText(cliente.getEndereco());
        edtNumero.setText(cliente.getNumero());
        edtCidade.setText(cliente.getCidade());
        rgSexo.check(cliente.getSexo());
    }
}
