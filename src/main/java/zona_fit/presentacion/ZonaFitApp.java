package zona_fit.presentacion;

import zona_fit.datos.ClinteDao;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);

        //Crear onjeto de clase cliente DAO
        var clienteDao = new ClinteDao();

        while (!salir){
            try {

                mostrarMenu();
                //salir = ejecutarOpciones(consola, clienteDao);


            }catch (Exception e){
                System.out.println("Erro al ejecutar opciones: "+e.getMessage());
            }
            System.out.println();
        }
    }

    private static void mostrarMenu(){
        System.out.print("""
                **Zona Fit Gym
                1. Listar Cientes
                2. Buscar clientes
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
                
                Elije una opción:\s""");
    }
}
