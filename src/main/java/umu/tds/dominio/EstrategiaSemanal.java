package umu.tds.dominio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EstrategiaSemanal implements EstrategiaAlerta {

	@Override
	public double calcularGastoEnPeriodo(List<Gasto> gastos, Optional<Categoria> categoria, LocalDate fechaReferencia) {
		Objects.requireNonNull(gastos, "La lista de gastos no puede ser nula");
		Objects.requireNonNull(categoria, "La categoría opcional no puede ser nula");
		Objects.requireNonNull(fechaReferencia, "La fecha de referencia no puede ser nula");
		
		LocalDate inicioSemana = fechaReferencia.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate finSemana = fechaReferencia.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		
		double resultado = gastos.stream()
				.filter(g -> categoria.isEmpty() || g.getCategoria().equals(categoria.get()))
				.filter(g -> !g.getFecha().isBefore(inicioSemana) && !g.getFecha().isAfter(finSemana))
				.mapToDouble(Gasto::getCuantia).sum();
		return resultado;
	}

	@Override
	public String getNombrePeriodo() {
		return "semanal";
	}

	@Override
	public boolean esMismoPeriodo(LocalDate f1, LocalDate f2) {
		Objects.requireNonNull(f1, "No puede haber fechas vacias");
		Objects.requireNonNull(f2, "No puede haber fechas vacias");
		
		LocalDate lunesf1 = f1.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate lunesf2 = f2.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		return lunesf1.equals(lunesf2);
	}
	
	

}
