package umu.tds.dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EstrategiaMensual implements EstrategiaAlerta {

	@Override
	public double calcularGastoEnPeriodo(List<Gasto> gastos, Optional<Categoria> categoria, LocalDate fechaReferencia) {
		Objects.requireNonNull(gastos, "La lista de gastos no puede ser nula");
		Objects.requireNonNull(categoria, "La categoría opcional no puede ser nula");
		Objects.requireNonNull(fechaReferencia, "La fecha de referencia no puede ser nula");
		double resultado = gastos.stream()
				.filter(g -> categoria.isEmpty() || g.getCategoria().equals(categoria.get()))
				.filter(g -> g.getFecha().getYear() == fechaReferencia.getYear() && g.getFecha().getMonth() == fechaReferencia.getMonth())
				.mapToDouble(Gasto::getCuantia).sum();
		return resultado;
	}

	@Override
	public String getNombrePeriodo() {
		return "mensual";
	}

	@Override
	public boolean esMismoPeriodo(LocalDate f1, LocalDate f2) {
		Objects.requireNonNull(f1, "No puede haber fechas vacias");
		Objects.requireNonNull(f2, "No puede haber fechas vacias");
		return (f1.getYear() == f2.getYear() && f1.getMonth() == f2.getMonth());
	}

}
