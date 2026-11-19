package umu.tds.dominio;

import java.util.Objects;

public class Persona {
    private final String nombre;
    private double saldo;

    public Persona(String nombre) {
        if(nombre == null || nombre.isBlank()) {
        	throw new IllegalArgumentException("El nombre introducido para la persona no es válido");
        }
        this.nombre = nombre.trim();
        this.saldo = 0.0; // Inicialmente a 0 según el enunciado
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    // Patrón Experto: Ella misma actualiza su saldo cuando paga o le toca asumir parte de un gasto
    public void actualizarSaldo(double cantidad) {
        // TODO 2: Suma 'cantidad' (puede ser positiva si pagó, o negativa si le toca pagar su parte)
    	this.saldo += cantidad; 
    }

    
 // TODO 3: Implementa equals y hashCode basándote en 'nombre' (ignorando mayúsculas/minúsculas, igual que en Categoria.java)
	@Override
	public int hashCode() {
		return Objects.hash(nombre.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return nombre.equalsIgnoreCase(other.nombre);
	}

    
    
}
