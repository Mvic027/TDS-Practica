package umu.tds.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Gasto {
    private final UUID id;
    private String concepto;
    private double cuantia;
    private LocalDate fecha;
    private Categoria categoria;
    private Persona pagador;

    // Constructor 1: Para gastos personales nuevos
    public Gasto(String concepto, double cuantia, LocalDate fecha, Categoria categoria) {
        this(UUID.randomUUID(), concepto, cuantia, fecha, categoria, null);
    }

    // Constructor 2: Para gastos compartidos nuevos (obliga a que haya un pagador)
    public Gasto(String concepto, double cuantia, LocalDate fecha, Categoria categoria, Persona pagador) {
        this(UUID.randomUUID(), concepto, cuantia, fecha, categoria, 
             Objects.requireNonNull(pagador, "Un gasto compartido debe tener un pagador asignado"));
    }

    // Constructor 3 (Maestro): Para recuperar de la base de datos o recibir delegaciones
    public Gasto(UUID id, String concepto, double cuantia, LocalDate fecha, Categoria categoria, Persona pagador) {
        this.id = Objects.requireNonNull(id, "El ID del gasto no puede ser nulo");
        this.concepto = validarConcepto(concepto);
        this.cuantia = validarCuantia(cuantia);
        this.fecha = validarFecha(fecha);
        this.categoria = validarCategoria(categoria);
        this.pagador = pagador; // Se permite null porque puede venir de un gasto personal
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

    public Optional<Persona> getPagador() {
        return Optional.ofNullable(pagador);
    }

    // Actualización atómica para gastos personales (la de tu compañero)
    public void actualizar(String concepto, double cuantia, LocalDate fecha, Categoria categoria) {
        actualizar(concepto, cuantia, fecha, categoria, this.pagador);
    }

    // Sobrecarga de actualización por si se edita un gasto compartido y cambia también quién lo pagó
    public void actualizar(String concepto, double cuantia, LocalDate fecha, Categoria categoria, Persona pagador) {
        String conceptoValido = validarConcepto(concepto);
        double cuantiaValida = validarCuantia(cuantia);
        LocalDate fechaValida = validarFecha(fecha);
        Categoria categoriaValida = validarCategoria(categoria);

        this.concepto = conceptoValido;
        this.cuantia = cuantiaValida;
        this.fecha = fechaValida;
        this.categoria = categoriaValida;
        this.pagador = pagador;
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
        String nombrePagador = getPagador().map(Persona::getNombre).orElse("Personal");
        return "Gasto [id=" + id + ", concepto=" + concepto + ", cuantia=" + cuantia
                + ", fecha=" + fecha + ", categoria=" + categoria + ", pagador=" + nombrePagador + "]";
    }
}