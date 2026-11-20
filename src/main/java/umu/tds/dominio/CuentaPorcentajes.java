package umu.tds.dominio;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CuentaPorcentajes extends CuentaCompartida {

    private final Map<Persona, Double> porcentajes;
    final double DIV_100 = 100.0;
    public CuentaPorcentajes(String nombre, Map<Persona, Double> porcentajes) {
        // TODO 1: Invoca a super(...) pasando 'nombre' y la lista de claves de 'porcentajes'
        // Pista: valida antes en un método auxiliar estático o ten cuidado porque super() debe ser la primera línea.
        // Truco limpio: super(nombre, validarYExtraerPersonas(porcentajes));
        super(nombre, validarYExtraerPersonas(porcentajes));
        this.porcentajes = Map.copyOf(porcentajes);
    }

    private static List<Persona> validarYExtraerPersonas(Map<Persona, Double> porcentajes) {
        Objects.requireNonNull(porcentajes, "El mapa de porcentajes no puede ser nulo");
        
        // TODO 2: Comprueba que ningún valor en porcentajes.values() sea null o <= 0.0 (usa un Stream con anyMatch)
        if (porcentajes.values().stream().anyMatch(v -> (v==null || v <= 0.0))) {
            throw new IllegalArgumentException("Todos los porcentajes deben ser mayores que 0");
        }

        // TODO 3: Calcula la suma de todos los valores usando Stream (.values().stream().mapToDouble(Double::doubleValue).sum())
        // y comprueba que Math.abs(suma - 100.0) <= 1e-6. Si no, lanza IllegalArgumentException.
        double suma = porcentajes.values().stream().mapToDouble(Double::doubleValue).sum();
        		
        if (Math.abs(suma - 100.0) > 1e-6) {
            throw new IllegalArgumentException("La suma de los porcentajes debe ser exactamente 100%");
        }

        return List.copyOf(porcentajes.keySet());
    }

    @Override
    protected void repartirDeuda(Gasto gasto) {
        // TODO 4: Recorre getPersonas() usando .forEach(...)
        // Para cada persona 'p', obtén su porcentaje del mapa 'porcentajes.get(p)',
        // calcula cuánto le toca pagar: gasto.getCuantia() * (porcentaje / 100.0)
        // y réstaselo llamando a p.actualizarSaldo(-parteAsumida)
        getPersonas().forEach(p -> { double porcentaje = porcentajes.get(p);
        							double parte = gasto.getCuantia()*(porcentaje/DIV_100);
        							p.actualizarSaldo(-parte);
        });
    }

    public Map<Persona, Double> getPorcentajes() {
        return porcentajes; // Ya es inmutable gracias a Map.copyOf()
    }
}
