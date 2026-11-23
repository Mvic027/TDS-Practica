package umu.tds.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class CuentaCompartida {
    private final UUID id;
    private final String nombre;
    private final List<Persona> personas;
    private final List<Gasto> gastos;

    public CuentaCompartida(String nombre, List<Persona> personas) {
        // TODO 1: Valida que 'nombre' no sea null ni blank, y que 'personas' no sea null y tenga al menos 2 elementos.
    	if(nombre==null || nombre.isBlank()) {
    		throw new IllegalArgumentException("El nombre de la cuenta no puede estar vacío");
    	}
    	
    	if(personas == null || personas.size()<2) {
    		throw new IllegalArgumentException("La lista de personas debe contener al menos 2 nombres");
    	}
        // TODO 2: Comprueba que no haya personas nulas ni repetidas (pista: compara personas.size() con new HashSet<>(personas).size()).
    	
    	if (personas.stream().anyMatch(Objects::isNull)) {
    	    throw new IllegalArgumentException("La lista no puede contener personas nulas");
    	}
    	if (new HashSet<>(personas).size() != personas.size()) {
    	    throw new IllegalArgumentException("No puede haber personas repetidas en la cuenta");
    	}
    	
        this.id = UUID.randomUUID();
        this.nombre = nombre.trim();
        // TODO 3: Guarda 'personas' de forma INMUTABLE usando List.copyOf(...)
        this.personas = List.copyOf(personas);
        this.gastos = new ArrayList<>();
    }

    public void registrarGasto(Gasto gasto) {
        Objects.requireNonNull(gasto, "El gasto no puede ser nulo");
        // TODO 4: Verifica que gasto.getPagador() esté presente (.isPresent()) y que esa persona pertenezca a 'this.personas'.
        // Si no pertenece o no tiene pagador, lanza IllegalArgumentException.
        
        Persona pagador = gasto.getPagador()
                .orElseThrow(() -> new IllegalArgumentException("Un gasto en cuenta compartida debe tener pagador"));
        
        personas.stream()
                .filter(p -> p.equals(pagador))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("El pagador no pertenece a esta cuenta compartida"));

        // TODO 5: Añade el gasto a 'this.gastos', suma el total al pagador (pagador.actualizarSaldo(gasto.getCuantia()))
        // y llama al método abstracto repartirDeuda(gasto) para restar a cada uno su parte.
        this.gastos.add(gasto);
        pagador.actualizarSaldo(gasto.getCuantia());
        repartirDeuda(gasto);
        
    }

    // Método plantilla que implementarán CuentaEquitativa y CuentaPorcentajes
    protected abstract void repartirDeuda(Gasto gasto);

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public List<Persona> getPersonas() { return personas; } // Ya es inmutable
    public List<Gasto> getGastos() { return List.copyOf(gastos); }
}