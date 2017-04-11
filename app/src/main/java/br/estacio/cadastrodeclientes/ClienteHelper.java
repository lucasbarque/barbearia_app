package br.estacio.cadastrodeclientes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import br.estacio.cadastrodeclientes.dao.ClienteDAO;
import br.estacio.cadastrodeclientes.model.Cliente;

/**
 * Created by carlos on 27/03/17.
 */

public class ClienteHelper {

    private ClienteActivity activity;

    private EditText edtNome, edtMail, edtFone, edtCEP, edtEndereco,
    edtNumero, edtCidade;
    private RadioGroup rgSexo;
    private Button btnSalvarCliente, btnFoto;
    private ImageView foto;

    private Cliente cliente;

    public ClienteHelper(final ClienteActivity activity) {
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
        btnSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente = carregaDadosDaTela();
                if (validate()) {
                    ClienteDAO dao = new ClienteDAO(activity);
                    if (cliente.getId() == 0) {
                        dao.insert(cliente);
                    } else {
                        dao.update(cliente);
                    }
                    dao.close();
                    activity.finish();
                }
            }
        });
        foto = (ImageView) activity.findViewById(R.id.foto);
        btnFoto = (Button) activity.findViewById(R.id.formFotoButton);

        cliente = (Cliente) activity.getIntent().getSerializableExtra("clienteSelecionado");
        if (cliente != null) {
            carregaDadosParaTela(cliente);
        }
        else {
            cliente = new Cliente();
        }
    }

    public Cliente carregaDadosDaTela() {
        cliente.setNome(edtNome.getText().toString());
        cliente.setEmail(edtMail.getText().toString());
        cliente.setFone(edtFone.getText().toString());
        cliente.setCEP(edtCEP.getText().toString());
        cliente.setEndereco(edtEndereco.getText().toString());
        cliente.setNumero(edtNumero.getText().toString());
        cliente.setCidade(edtCidade.getText().toString());
        cliente.setSexo(rgSexo.getCheckedRadioButtonId() == R.id.feminino ? 0 : 1);
        cliente.setCaminhoFoto((String) foto.getTag());
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
        rgSexo.check(cliente.getSexo() == 0 ? R.id.feminino : R.id.masculino);
        setImage(cliente.getCaminhoFoto());
    }

    public boolean validate() {
        boolean valid = true;
        if (edtNome.getText().toString().trim().isEmpty()) {
            edtNome.setError("Campo nome é obrigatório!");
            valid = false;
        }
        if (edtMail.getText().toString().trim().isEmpty()) {
            edtMail.setError("Campo e-mail é obrigatório!");
            valid = false;
        }
        return valid;
    }

    public Button getBtnFoto() {
        return btnFoto;
    }

    public void setImage(String localArquivoFoto) {
        if (localArquivoFoto != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
            Bitmap imagemFotoReduzida = Bitmap
                    .createScaledBitmap(imagemFoto, imagemFoto.getWidth(),
                            300, true);
            foto.setImageBitmap(imagemFotoReduzida);
            foto.setTag(localArquivoFoto);
            foto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

}
