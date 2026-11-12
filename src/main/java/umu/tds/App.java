package umu.tds;

import umu.tds.dominio.Categoria;

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
        
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
}
