package br.com.beviti.combus.resumo;

import com.google.firebase.database.Exclude;

public class Resumo {
    private String tipo;
    private double km;
    private double litros;
    private double total;

    public Resumo() {
    }

    public void salvar(){

    }
    @Exclude
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
