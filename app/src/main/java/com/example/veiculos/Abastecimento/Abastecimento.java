package com.example.veiculos.Abastecimento;

import java.io.Serializable;
import java.util.Date;

public class Abastecimento implements Serializable {

    private int id;
    private String tipo;
    private String data;
    private Double valor;
    private Double litros;
    private String posto;
    private Double km;
    private Double total;


    public Abastecimento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
        this.total = valor * (this.litros != null ? this.litros: 0.00);
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
        this.total = litros * (this.valor != null ? this.valor: 0.0);
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Double getTotal() {
        return total;
    }



}
