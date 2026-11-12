package umu.tds.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Gasto {
    private final UUID id;
    private String concepto;
    private double cuantia;
    private LocalDate fecha;
    private Categoria categoria;

    public Gasto(String concepto, double cuantia, LocalDate fecha, Categoria categoria) {
        this.id = UUID.randomUUID(); //genera un aleatorio ej(c0941202-b24f-4733-87ca-bd998f58ecf1)
        this.concepto = validarConcepto(concepto);
        this.cuantia = validarCuantia(cuantia);
        this.fecha = validarFecha(fecha);
        this.categoria = validarCategoria(categoria);
    }


    public UUID getId() {
        return id;
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

    public void setConcepto(String concepto) {
        this.concepto = validarConcepto(concepto);
    }

    public void setCuantia(double cuantia) {
        this.cuantia = validarCuantia(cuantia);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = validarFecha(fecha);
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = validarCategoria(categoria);
    }


    private static String validarConcepto(String concepto) {
        if (concepto == null || concepto.isBlank())
            throw new IllegalArgumentException("El concepto no puede estar vacío");
        return concepto.trim();
    }

    private static double validarCuantia(double cuantia) {
        if (cuantia <= 0)
            throw new IllegalArgumentException("La cuantía debe ser mayor que 0");
        return cuantia;
    }

    private static LocalDate validarFecha(LocalDate fecha) {
        return Objects.requireNonNull(fecha, "La fecha no puede ser nula");
    }

    private static Categoria validarCategoria(Categoria categoria) {
        return Objects.requireNonNull(categoria, "La categoría no puede ser nula");
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Gasto other = (Gasto) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Gasto [" + id + ", " + concepto + ", " + cuantia
                + ", " + fecha + ", " + categoria + "]";
    }
}