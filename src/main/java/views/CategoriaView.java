package views;

import java.util.Scanner;
import controllers.CategoriaController;

public class CategoriaView {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        leerMenu();
    }

    private static void leerMenu() {

        // Bandera para controlar la petición de la acción a realizar hasta que la
        // ingresada sea valida
        Boolean flag = true;
        Opciones opcion = null;

        while (flag) {
            System.out.print("Ingrese la acción que desea realizar: C(Crear), R(Leer), U(Actualizar), D(Eliminar):");

            // Si la opción ingresada no es valida, se seguirá mostrando el menú
            String input = keyboard.nextLine().toUpperCase();
            if (Opciones.findByValue(input) != null) {
                flag = false;
                opcion = Opciones.valueOf(input);
            }
        }

        opcionSeleccionada(opcion);
    }

    private static void opcionSeleccionada(Opciones opcion) {
        switch (opcion) {
            case C:
                System.out.print(crearCategoria());
                break;
            case R:
                System.out.print(getCategoria());
                break;
            case U:
                System.out.print(updateCategoria());
                break;
            case D:
                System.out.print(deleteCategoria());
                break;
        }
    }

    private static String crearCategoria() {
        System.out.print("Ingrese los datos de la categoría: \n");

        System.out.print("Nombre:");
        String nombre = keyboard.nextLine();

        return new CategoriaController().createCategoria(nombre);
    }

    private static String getCategoria() {
        System.out.print("Ingrese el ID de la categoría: \n");
        int id = Integer.parseInt(keyboard.nextLine());

        return new CategoriaController().getCategoria(id);
    }

    private static String updateCategoria() {
        System.out.print("Ingrese los datos de la categoría: \n");

        System.out.print("Id:");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.print("Nombre:");
        String nombre = keyboard.nextLine();

        return new CategoriaController().updateCategoria(id, nombre);
    }

    private static String deleteCategoria() {
        System.out.print("Ingrese el ID de la categoría: \n");
        int id = Integer.parseInt(keyboard.nextLine());

        return new CategoriaController().deleteCategoria(id);
    }

    private enum Opciones {
        C, // Create
        R, // Read
        U, // Update
        D; // Delete

        public static Opciones findByValue(String opcion) {
            for (Opciones o : values()) {
                if (o.name().equalsIgnoreCase(opcion)) {
                    return o;
                }
            }
            return null;
        }
    }
}

