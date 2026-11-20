package umu.tds.dominio;

import java.util.List;

public class CuentaEquitativa extends CuentaCompartida {

    public CuentaEquitativa(String nombre, List<Persona> personas) {
        super(nombre,personas);
    }

    @Override
    protected void repartirDeuda(Gasto gasto) {
        // TODO 2: Calcula cuánto le corresponde asumir a cada participante dividiendo gasto.getCuantia() entre el número de personas
        double partePorPersona = gasto.getCuantia()/getPersonas().size();

        // TODO 3: Recorre getPersonas() usando .forEach(...) y réstales 'partePorPersona' mediante actualizarSaldo(...)
        getPersonas().forEach(p -> p.actualizarSaldo(-partePorPersona));
    }
}
