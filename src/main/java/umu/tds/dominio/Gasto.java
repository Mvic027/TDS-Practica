package umu.tds.dominio;

import java.time.LocalDate;

public class Gasto {
    private String id;
    private String concepto;
    private double cuantia;
    private LocalDate fecha;
    private Categoria categoria;

    public Gasto(String id, String concepto, double cuantia, LocalDate fecha, Categoria categoria) {
    	this.id=id;
        this.concepto = concepto;
        this.cuantia = cuantia;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getCuantia() {
        return cuantia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Gasto [concepto=" + concepto + ", cuantia=" + cuantia + ", fecha=" + fecha + ", categoria=" + categoria + "]";
    }
}