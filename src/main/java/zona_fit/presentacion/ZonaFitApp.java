package zona_fit.presentacion;

import zona_fit.datos.ClinteDao;
import zona_fit.datos.IClienteDao;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);

        //Crear onjeto de clase cliente DAO
        IClienteDao clienteDao = new ClinteDao();

        while (!salir){
            try {

                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola,opcion, clienteDao);


            }catch (Exception e){
                System.out.println("Erro al ejecutar opciones: "+e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                **Zona Fit Gym
                1. Listar Cientes
                2. Buscar clientes
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
                
                Elije una opción:\s""");

        return Integer.parseInt(consola.nextLine());

    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion,
                                            IClienteDao clienteDao){
        var salir = false;
        switch (opcion){
            case 1 -> {
                //1. Listar Clientes
                System.out.println("---Listado Clientes---");
                var clientes =clienteDao.listarClientes();
                clientes.forEach(System.out::println);
            }

            case 2 -> {
                System.out.print("Introduce el ID de cliente a buscar: ");
                var id = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(id);
                var encontrado = clienteDao.buscarClientePorId(cliente);

                if (encontrado){
                    System.out.println("Cliente encontrado: "+cliente);
                }else {
                    System.out.println("Cliente no encontrado "+cliente);
                }
            }

            case 3 -> {
                System.out.println("-----Agregar cliente-----");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var nuevoCliente = new Cliente(nombre,apellido,membresia);
                var nuevo = clienteDao.agregarCliente(nuevoCliente);

                if (nuevo){
                    System.out.println("Cliente agregado: "+nuevoCliente);
                }else {
                    System.out.println("No se logro agregar el cliente: "+nuevoCliente);
                }
            }

            case 4 ->{
                System.out.println("----Modificar Cliente----");
                System.out.print("Id cliente a modificar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());

                var cliente = new Cliente(idCliente,nombre,apellido,membresia);
                var modificado = clienteDao.modificarCliente(cliente);

                if (modificado){
                    System.out.println("Cliente modificado: "+cliente);
                }else {
                    System.out.println("No se modificó el cliente: "+cliente);
                }
            }

            case 5 ->{
                System.out.println("!!!!! ELIMINAR CLIENTE !!!!!");
                System.out.print("Id del ciente a eliminar");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDao.eliminarCliente(cliente);

                if (eliminado){
                    System.out.println("Cliente eliminado: "+cliente);
                }else {
                    System.out.println("Cliente NO se eliminó: "+cliente);
                }
            }

            case 6 ->{
                System.out.println("Hasta pronto!!!");
                salir = true;
            }

            default -> System.out.println("Opción no válida");
        }

        return salir;

    }
}
