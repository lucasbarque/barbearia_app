package br.estacio.cadastrodeclientes.model;

/**
 * Created by carlos on 15/05/17.
 */

public enum Sexo {

    F("Feminino"),
    M("Masculino");

    private String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
