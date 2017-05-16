package br.estacio.cadastrodeclientes.model;

/**
 * Created by carlos on 15/05/17.
 */

public enum EstadoCivil {

    S("Solteiro"),
    C("Casado"),
    D("Divercidado"),
    V("Viúvo"),
    U("União Estável");

    private String estadoCivil;


    EstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return estadoCivil;
    }


}
