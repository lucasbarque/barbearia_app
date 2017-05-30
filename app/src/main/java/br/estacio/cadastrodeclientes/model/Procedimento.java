package br.estacio.cadastrodeclientes.model;

public enum Procedimento {

    CB("Cabelo"),
    BA("Barba"),
    BI("Bigode");

    private String procedimento;


    Procedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    @Override
    public String toString() {
        return procedimento;
    }


}
