/**
 * Bolaños Ramos Caleb Salomon 
 * García Marciano Edgar
 * Hernández Oble Axel
 * Olay Silis Jose Eduardo
 * Proyecto final de Programación Orientada a Objetos
 * Proyecto Aplicador y evaluador de examenes de opcion multiple
 * Miercoles 26 de enero de 2021 
 * 2CM3 
 * Programación Orientada a Objetos
 */
public class Validaciones {
    //metodo que se usa para verificar que ningun textfield dado este vacio
    public static boolean StringsNoVacios(String... textfileds) {
        for (String contenido : textfileds) {
            if (contenido.equals("")) {
                return false;
            }
        }
        return true;
    }
}
