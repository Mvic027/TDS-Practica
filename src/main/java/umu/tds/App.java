package umu.tds;

import java.time.LocalDate;

import umu.tds.dominio.Categoria;
import umu.tds.dominio.Gasto;
import umu.tds.repositorio.RepositorioGastos;
import umu.tds.repositorio.RepositorioGastosMemoria;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Categoria a = new Categoria("COMIDA");
        Categoria comida = new Categoria("Comida");
        Gasto cena = new Gasto("Cena", 20, LocalDate.now(), comida);

        
        System.out.println(a.hashCode());
        System.out.println(comida.hashCode());
        System.out.println(cena.getId());
        
        RepositorioGastos repo = new RepositorioGastosMemoria();

        System.out.println("Añadir: " + repo.anadirGasto(cena));          // true
        System.out.println("Añadir otra vez: " + repo.anadirGasto(cena)); // false
        System.out.println("Buscar: " + repo.buscarGasto(cena.getId()));  // el gasto

        cena.setCuantia(35);
        System.out.println("Modificar: " + repo.modificarGasto(cena));    // true
        System.out.println("Listar: " + repo.listarGastos());             // la cena con 35
        

        System.out.println("Borrar: " + repo.borrarGasto(cena.getId()));  // true
        System.out.println("Listar: " + repo.listarGastos()); 
    }
}
