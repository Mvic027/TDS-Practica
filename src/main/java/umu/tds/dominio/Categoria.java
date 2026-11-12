package umu.tds.dominio;

import java.util.Objects;

public class Categoria {

	private final String nombre ;
	

	public Categoria(String nombre) {
		
		if(nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre introducido para la categoria es invalido");
		}
		this.nombre=nombre.trim();
	}
		
	@Override
	public int hashCode() {
		return Objects.hash(nombre.toLowerCase());
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Categoria other = (Categoria) obj;
	    return nombre.equalsIgnoreCase(other.nombre);
	}

	@Override
	public String toString() {
		return nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
