package br.estacio.cadastrodeclientes.model;

import java.io.Serializable;
import java.util.Calendar;


public class Cliente implements Serializable {

    private long id;
    private String nome;
    private Calendar dataNasc;
    private String fone;
    private String caminhoFoto;
    private Procedimento procedimento;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }
    public Calendar getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(Calendar dataNasc) {
        this.dataNasc = dataNasc;
    }
    public String getCaminhoFoto() {
        return caminhoFoto;
    }
    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
    public Procedimento getProcedimento() {
        return procedimento;
    }
    public void setProcedimento(Procedimento procedimento) {this.procedimento = procedimento;}

    @Override
    public String toString() {
        return nome + " " + procedimento.toString();
    }

}
