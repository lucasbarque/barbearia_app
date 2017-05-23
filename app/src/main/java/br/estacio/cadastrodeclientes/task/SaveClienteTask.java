package br.estacio.cadastrodeclientes.task;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import br.estacio.cadastrodeclientes.model.Cliente;
import br.estacio.cadastrodeclientes.ws.WebRequest;


/**
 * Created by carlos on 05/11/2015.
 */
public class SaveClienteTask extends AsyncTask<String, Object, Boolean> {
    private final Activity mainActivity;
    private final Cliente cliente;
    private ProgressDialog progress;

    private static final String STATUS = "status";
    private static final String STATUS_OK = "OK";
    private boolean statusOK;

    public SaveClienteTask(Activity mainActivity, Cliente cliente) {
        this.mainActivity = mainActivity;
        this.cliente = cliente;
    }

    @Override
    protected void onPreExecute() {
        //progress = ProgressDialog.show(mainActivity, "Aguarde...", "Enviando dados!!!", true);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            WebRequest request = new WebRequest();
            String jsonResult = request.saveCliente(cliente);
            JSONObject jsonObject = new JSONObject(jsonResult);
            statusOK = jsonObject.getString(STATUS).trim().equals(STATUS_OK);
            return statusOK;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean statusOK) {
        if (!statusOK) {
            Toast.makeText(mainActivity, "Houve um erro ao salvar o cliente", Toast.LENGTH_LONG).show();
        }
        //progress.dismiss();
    }
}
