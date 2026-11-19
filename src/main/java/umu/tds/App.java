package umu.tds;

import java.time.LocalDate;
import java.util.UUID;

import umu.tds.controlador.GestorGastos;
import umu.tds.dominio.Categoria;
import umu.tds.dominio.Gasto;
import umu.tds.repositorio.RepositorioGastos;
import umu.tds.repositorio.RepositorioGastosMemoria;

public class App {
    public static void main(String[] args) {
    	//Categoria
        Categoria a = new Categoria("COMIDA");
        Categoria comida = new Categoria("Comida");
        System.out.println(a.hashCode());
        System.out.println(comida.hashCode());

        //Repositorio
        System.out.println();
        Gasto cena = new Gasto("Cena", 20, LocalDate.now(), comida);
        RepositorioGastos repo = RepositorioGastosMemoria.getInstancia();

        System.out.println("Añadir: " + repo.anadirGasto(cena));          // true
        System.out.println("Añadir otra vez: " + repo.anadirGasto(cena)); // false
        System.out.println("Buscar: " + repo.buscarGasto(cena.getId()));  // el gasto

        cena.actualizar(cena.getConcepto(), 35, cena.getFecha(), cena.getCategoria());
        System.out.println("Modificar: " + repo.modificarGasto(cena));    // true
        System.out.println("Listar: " + repo.listarGastos());             // la cena con 35

        System.out.println("Borrar: " + repo.borrarGasto(cena.getId()));  // true
        System.out.println("Listar: " + repo.listarGastos());             // []

        //Controlador
        System.out.println();
        GestorGastos gestor = GestorGastos.getUnicaInstancia();
        Categoria ocio = new Categoria("Ocio");

        System.out.println("Añadir: " + gestor.anadirGasto("Cine", 8, LocalDate.now(), ocio));        // true
        System.out.println("Añadir: " + gestor.anadirGasto("Desayuno", 4.5, LocalDate.now(), comida)); // true
        System.out.println("Listar: " + gestor.obtenerGastos());                                     // los 2 gastos

        // Cogemos el id del primer gasto de la lista
        UUID id = gestor.obtenerGastos().get(0).getId();

        System.out.println("Modificar: " + gestor.modificarGasto(id, "Concierto", 30, LocalDate.now(), ocio)); // true
        System.out.println("Listar: " + gestor.obtenerGastos());    // uno modificado

        // Modificación inválida: no debe cambiar NADA (todo o nada)
        try {
            gestor.modificarGasto(id, "Cambio que no debe aplicarse", -5, LocalDate.now(), comida);
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        System.out.println("Tras error: " + gestor.obtenerGastos());  // igual que antes

        System.out.println("Modificar id inexistente: " + gestor.modificarGasto(UUID.randomUUID(), "X", 1, LocalDate.now(), ocio));  // false

        System.out.println("Borrar: " + gestor.borrarGasto(id));    // true
        System.out.println("Listar: " + gestor.obtenerGastos());   // queda 1 gasto

        // Singleton: siempre es el mismo objeto
        System.out.println("Mismo gestor: " + (gestor == GestorGastos.getUnicaInstancia()));  // true
    }
}