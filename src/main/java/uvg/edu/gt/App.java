package uvg.edu.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner in = new Scanner(System.in);
        MapFab<String,String> fab = new MapFab<String,String>();
        Leo leo = new Leo();
        System.out.println("**************************************************\n"
                + "*     Seleccione una implementacion (nombre)     *\n"
                + "**************************************************\n"
                + "*   HashMap                                    *\n"
                + "*   TreeMap                                    *\n"
                + "*   LinkedHashMap                              *\n"
                + "**************************************************\n");
        String opc = in.nextLine();
        Map<String,String> maso = fab.getMap(opc);
        String direccion = "C:\\Users\\cjvil\\IdeaProjects\\cards_desc.txt";
        leo.llenarMap(direccion,maso);
        MapFab<String,Integer> fab1 = new MapFab<String,Integer>();
        Map<String,Integer> coleccion = fab1.getMap(opc);
        Map<String,String> coleccion0 = fab.getMap(opc);

        int opcion;
        do {
            System.out.println("\n****************************************************************\n"
                    + "*                                Menú                          *\n"
                    + "****************************************************************\n"
                    + "* 1. Agregar una carta a la colección                          *\n"
                    + "* 2. Mostrar el tipo de una carta dada                         *\n"
                    + "* 3. Mostrar la colleccion                                     *\n"
                    + "* 4. Mostrar la collección ordenada por tipo                   *\n"
                    + "* 5. Mostrar todas las cartas existenete                       *\n"
                    + "* 6. Mostrar todas las cartas existenetes ordenadas por tipo   *\n"
                    + "* 0. Salir                                                     *\n"
                    + "****************************************************************\n");
            System.out.print("Ingrese el número de la opción deseada: ");
            opcion = Integer.parseInt(getNumber(in));
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la carta que desea agregar: ");
                    String nombreCarta = in.nextLine();
                    agregarCarta(maso,coleccion, coleccion0, nombreCarta);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre de la carta que desea consultar: ");
                    String cartaConsultar = in.nextLine();
                    mostrarTipoCarta(maso, cartaConsultar);
                    break;
                case 3:
                    mostrarColeccion(coleccion, maso);
                    break;
                case 4:
                    mostrarColeccionOrdenadaPorTipo(coleccion, coleccion0);
                    break;
                case 5:
                    mostrarTodasCartas(maso);
                    break;
                case 6:
                    mostrarTodasCartasOrdenadasPorTipo(maso);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número válido.");
            }
        } while (opcion != 0);
        in.close();
        System.out.println("Tiempo ejecucion HashMap:" + medirTiempoEjecucion("HashMap"));
        System.out.println("Tiempo ejecucion TreeMap:" + medirTiempoEjecucion("TreeMap"));
        System.out.println("Tiempo ejecucion LinkedHashMap:" + medirTiempoEjecucion("LinkedHashMap"));




    }
    public static void agregarCarta(Map<String,String> maso,Map<String, Integer> coleccion, Map<String,String> col, String nombreCarta) {
        if (coleccion.containsKey(nombreCarta)) {
            int cantidad = coleccion.get(nombreCarta);
            coleccion.put(nombreCarta, cantidad + 1);
            System.out.println("Se ha agregado una carta de " + nombreCarta + " a la colección.");
        }else if(maso.containsKey(nombreCarta)){
            coleccion.put(nombreCarta,1);
            col.put(nombreCarta,maso.get(nombreCarta));
        }else {
            System.out.println("Error: La carta ingresada no se encuentra entre las cartas disponibles.");
        }
    }

    public static void mostrarTipoCarta(Map<String, String> maso, String cartaConsultar) {
        if (maso.containsKey(cartaConsultar)) {
            String tipo = maso.get(cartaConsultar);
            System.out.println("El tipo de la carta " + cartaConsultar + " es: " + tipo);
        } else {
            System.out.println("La carta consultada no se encuentra en el maso.");
        }
    }

    public static void mostrarColeccion(Map<String, Integer> coleccion, Map<String, String> maso) {
        System.out.println("Nombre - Tipo - Cantidad");
        for (Map.Entry<String, Integer> entry : coleccion.entrySet()) {
            String nombreCarta = entry.getKey();
            int cantidad = entry.getValue();
            String tipo = maso.get(nombreCarta);
            System.out.println(nombreCarta + " - " + tipo + " - " + cantidad);
        }
    }

    public static void mostrarColeccionOrdenadaPorTipo(Map<String, Integer> coleccion, Map<String, String> maso) {
        List<Map.Entry<String, String>> listaOrdenada = new ArrayList<>(maso.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue());

        System.out.println("Nombre - Tipo - Cantidad");
        for (Map.Entry<String, String> entry : listaOrdenada) {
            String nombreCarta = entry.getKey();
            int cantidad = coleccion.get(nombreCarta);
            String tipo = maso.get(nombreCarta);
            System.out.println(nombreCarta + " - " + tipo + " - " + cantidad);
        }
    }

    public static void mostrarTodasCartas(Map<String, String> maso) {
        System.out.println("Nombre - Tipo");
        for (Map.Entry<String, String> entry : maso.entrySet()) {
            String nombreCarta = entry.getKey();
            String tipo = entry.getValue();
            System.out.println(nombreCarta + " - " + tipo);
        }
    }

    public static void mostrarTodasCartasOrdenadasPorTipo(Map<String, String> maso) {
        List<Map.Entry<String, String>> listaOrdenada = new ArrayList<>(maso.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue());

        System.out.println("Nombre - Tipo");
        for (Map.Entry<String, String> entry : listaOrdenada) {
            String nombreCarta = entry.getKey();
            String tipo = entry.getValue();
            System.out.println(nombreCarta + " - " + tipo);
        }
    }
    public static String getNumber(Scanner keyboard) {
        String number = keyboard.nextLine(); // Obtiene el input
        boolean isNumber = false;

        while (!isNumber) { // Vuelve a pedir input hasta que este sea un número
            try {
                int nm = Integer.parseInt(number); // Verifica que el input sea un número
                isNumber = true;
            } catch (NumberFormatException nft) {
                System.out.println("ERROR. Verifique que el valor ingresado sea numérico e intente de nuevo.");
                number = keyboard.nextLine();
            }
        }

        return number;
    }

    public static long medirTiempoEjecucion(String opc){
        Scanner in = new Scanner(System.in);
        MapFab<String,String> fab = new MapFab<String,String>();
        Leo leo = new Leo();
        Map<String,String> maso = fab.getMap(opc);
        String direccion = "C:\\Users\\cjvil\\IdeaProjects\\cards_desc.txt";
        leo.llenarMap(direccion,maso);
        MapFab<String,Integer> fab1 = new MapFab<String,Integer>();
        Map<String,Integer> coleccion = fab1.getMap(opc);
        Map<String,String> coleccion0 = fab.getMap(opc);
        long tiempo = System.currentTimeMillis();
        mostrarTodasCartas(maso);
        tiempo = System.currentTimeMillis() - tiempo;
        return tiempo;
    }

}
