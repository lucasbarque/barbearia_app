package br.estacio.cadastrodeclientes;


import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.estacio.cadastrodeclientes.dao.ClienteDAO;
import br.estacio.cadastrodeclientes.model.Cliente;
import br.estacio.cadastrodeclientes.model.Procedimento;

public class ClienteHelper {

    private ClienteActivity activity;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    private EditText edtNome, edtDataNasc, edtFone;
    private Button btnSalvarCliente, btnFoto;
    private ImageView foto;
    private Spinner spinnerProcedimento;
    private List<Procedimento> procedimento;
    private ArrayAdapter<Procedimento> adapter;
    private Procedimento procedimentoSelecionado;
    int hora, minuto, horaFinal, minutoFinal, diaFinal, mesFinal, anoFinal;
    private Cliente cliente;

    public ClienteHelper(final ClienteActivity activity) {
        this.activity = activity;
        procedimento = Arrays.asList(
                Procedimento.values()
        );
        adapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, procedimento);
        edtNome = (EditText) activity.findViewById(R.id.edtNome);
        edtFone = (EditText) activity.findViewById(R.id.edtFone);
        edtDataNasc = (EditText) activity.findViewById(R.id.edtDataNasc);
        edtDataNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });
        spinnerProcedimento = (Spinner) activity.findViewById(R.id.spinnerProcedimento);
        spinnerProcedimento.setAdapter(adapter);
        spinnerProcedimento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                procedimentoSelecionado = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        cliente.setFone(edtFone.getText().toString());
        cliente.setCaminhoFoto((String) foto.getTag());
        cliente.setDataNasc(getDate());
        cliente.setProcedimento(procedimentoSelecionado);
        return cliente;
    }

    public void carregaDadosParaTela(Cliente cliente) {
        this.cliente = cliente;
        edtNome.setText(cliente.getNome());
        edtFone.setText(cliente.getFone());
        setImage(cliente.getCaminhoFoto());
        setDate(cliente.getDataNasc());
        spinnerProcedimento.setSelection(procedimento.indexOf(cliente.getProcedimento()));
    }

    public boolean validate() {
        boolean valid = true;
        if (edtNome.getText().toString().trim().isEmpty()) {
            edtNome.setError("Campo nome é obrigatório!");
            valid = false;
        }
        if (edtDataNasc.getText().toString().trim().isEmpty()) {
            edtDataNasc.setError("Campo data de nascimento é obrigatório!");
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




    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);

        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, activity, hora, minuto, true);
        timePickerDialog.show();
    }

    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        horaFinal = i;
        minutoFinal = i1;
    }

    private void setDate(Calendar calendar) {
        try {
            edtDataNasc.setText(dateFormat.format(calendar.getTime()));
        }
        catch (Exception e) {
            edtDataNasc.setText(dateFormat.format(new Date()));
        }
    }

    private Calendar getDate() {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateFormat.parse(edtDataNasc.getText().toString()));
        }
        catch (Exception e) {
            c.setTime(new Date());
        }
        return c;
    }

    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable("calendar", cliente.getDataNasc());
        fragment.setArguments(args);
        fragment.show(activity.getFragmentManager(), "");
    }

}
