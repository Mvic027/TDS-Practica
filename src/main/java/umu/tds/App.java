package umu.tds;

import java.time.LocalDate;

import umu.tds.dominio.Categoria;
import umu.tds.dominio.Gasto;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Categoria a = new Categoria("COMIDA");
        Categoria b = new Categoria("comida");
        Gasto c = new Gasto("Cena", 200, LocalDate.now(), a);
        
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.getId());
    }
}
