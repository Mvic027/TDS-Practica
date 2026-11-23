package umu.tds.dominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstrategiaAlerta {
	double calcularGastoEnPeriodo(List<Gasto> gastos, Optional<Categoria> categoria, LocalDate fechaReferencia);
	String getNombrePeriodo();
	boolean esMismoPeriodo(LocalDate f1, LocalDate f2);
}
