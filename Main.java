//librerias
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//la interfaz esta en el archivo Telefono.java


public class Main {
    public static void main(String[] args) {
        List<EDispositivo> dispositivos = new ArrayList<>();

        cargarDispsitivosCSV(dispositivos);

        //Nueva intancia de scanner  
        Scanner scanner = new Scanner(System.in);
        
        //ciclo para el menu 
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Listar dispositivos");
            System.out.println("2. Encender dispositivo");
            System.out.println("3. Apagar dispositivo");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    listarDispositivos(dispositivos);
                    break;
                case 2:
                    encenderDispositivo(dispositivos, scanner);
                    break;
                case 3:
                    apagarDispositivo(dispositivos, scanner);
                    break;
                case 4:
                    guardarDispositivosCSV(dispositivos);
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    //funcion para imprimir todos los dispositivos del csv(Funcion nueva no incluida en el Analisis y diseño)
    private static void listarDispositivos(List<EDispositivo> dispositivos) {
        for (EDispositivo dispositivo : dispositivos) {
            String estado = dispositivo.Encendido() ? "Encendido" : "Apagado";
            String info = dispositivo instanceof Telefono ?
                "Teléfono: Modelo " + ((Telefono) dispositivo).getModelo() :
                "Computadora: Marca " + ((Computadora) dispositivo).getMarca();
            System.out.println(info + " - " + estado);
        }
    }

    //enciende un dispositvo en el arraylist(Funcion nueva no incluida en el Analisis y diseño)
    private static void encenderDispositivo(List<EDispositivo> dispositivos, Scanner scanner) {
        listarDispositivos(dispositivos);
        System.out.println("Ingrese el número del dispositivo que desea encender(El indice 0 es valido):");
        int seleccion = scanner.nextInt();
        if (seleccion >= 0 && seleccion < dispositivos.size()) {
            dispositivos.get(seleccion).encender();
            System.out.println("Dispositivo encendido.");
            listarDispositivos(dispositivos);
        } else {
            System.out.println("Número de dispositivo no válido.");
        }
    }

    //funcion para apagar un dipositivo en el arraylist(Funcion nueva no incluida en el Analisis y diseño)
    private static void apagarDispositivo(List<EDispositivo> dispositivos, Scanner scanner) {
        listarDispositivos(dispositivos);
        System.out.println("Ingrese el número del dispositivo que desea apagar(El indice 0 es valido):");
        int seleccion = scanner.nextInt();
        if (seleccion >= 0 && seleccion < dispositivos.size()) {
            dispositivos.get(seleccion).apagar();
            System.out.println("Dispositivo apagado.");
            listarDispositivos(dispositivos);
        } else {
            System.out.println("Número de dispositivo no válido.");
        }
    }

    //funcion para tomar los datos del csv y ponerlos en un arraylist para que sean manipulados 
    private static void cargarDispsitivosCSV(List<EDispositivo> dispositivos) {
        try (BufferedReader br = new BufferedReader(new FileReader("dispositivos.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String tipo = parts[0].trim();
                    String modeloMarca = parts[1].trim();
                    boolean encendido = Boolean.parseBoolean(parts[2].trim());

                    if (tipo.equals("Telefono")) {
                        dispositivos.add(new Telefono(modeloMarca, encendido));
                    } else if (tipo.equals("Computadora")) {
                        dispositivos.add(new Computadora(modeloMarca, encendido));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //funcion para guardar los cambios realizados en el csv
    private static void guardarDispositivosCSV(List<EDispositivo> dispositivos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dispositivos.csv"))) {
            for (EDispositivo dispositivo : dispositivos) {
                String tipo = dispositivo instanceof Telefono ? "Telefono" : "Computadora";
                String modeloMarca = dispositivo instanceof Telefono ?
                    ((Telefono) dispositivo).getModelo() :
                    ((Computadora) dispositivo).getMarca();
                boolean encendido = dispositivo.Encendido();
                bw.write(tipo + "," + modeloMarca + "," + encendido);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
