package LaboratoriaN4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String HOST = "192.168.56.101";
    private static final int PUERTO = 12345;
    private static final String SEPARADOR = ";";
    
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Conectado al servidor de conversiones");
            int opcion;
            
            do {
                mostrarMenu();
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                if (opcion >= 1 && opcion <= 6) {
                    String solicitud = construirSolicitud(opcion, scanner);
                    salida.println(solicitud);
                    
                    String respuesta = entrada.readLine();
                    procesarRespuesta(respuesta);
                } else if (opcion == 0) {
                    salida.println("0" + SEPARADOR + "desconectar");
                    String respuesta = entrada.readLine();
                    System.out.println("Desconectando...");
                } else {
                    System.out.println("Opción inválida");
                }
            } while (opcion != 0);
            
        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== CLIENTE CONVERSOR ===");
        System.out.println("1. Decimal a Binario");
        System.out.println("2. Binario a Decimal");
        System.out.println("3. Decimal a Hexadecimal");
        System.out.println("4. Hexadecimal a Decimal");
        System.out.println("5. Binario a Hexadecimal");
        System.out.println("6. Hexadecimal a Binario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static String construirSolicitud(int opcion, Scanner scanner) {
        StringBuilder solicitud = new StringBuilder();
        solicitud.append(opcion).append(SEPARADOR);
        
        switch (opcion) {
            case 1: // Decimal a Binario
                System.out.print("Número decimal: ");
                solicitud.append(scanner.nextLine()).append(SEPARADOR);
                System.out.print("Longitud en bits: ");
                solicitud.append(scanner.nextLine());
                break;
                
            case 2: // Binario a Decimal
                System.out.print("Número binario: ");
                solicitud.append(scanner.nextLine());
                break;
                
            case 3: // Decimal a Hexadecimal
                System.out.print("Número decimal: ");
                solicitud.append(scanner.nextLine()).append(SEPARADOR);
                System.out.print("Ancho en dígitos: ");
                solicitud.append(scanner.nextLine());
                break;
                
            case 4: // Hexadecimal a Decimal
                System.out.print("Número hexadecimal: ");
                solicitud.append(scanner.nextLine());
                break;
                
            case 5: // Binario a Hexadecimal
                System.out.print("Número binario: ");
                solicitud.append(scanner.nextLine()).append(SEPARADOR);
                System.out.print("Ancho en dígitos: ");
                solicitud.append(scanner.nextLine());
                break;
                
            case 6: // Hexadecimal a Binario
                System.out.print("Número hexadecimal: ");
                solicitud.append(scanner.nextLine());
                break;
        }
        
        return solicitud.toString();
    }
    
    private static void procesarRespuesta(String respuesta) {
        if (respuesta == null) {
            System.out.println("Error: No se recibió respuesta del servidor");
            return;
        }
        
        String[] partes = respuesta.split(SEPARADOR, 2);
        if (partes.length != 2) {
            System.out.println("Error: Formato de respuesta inválido");
            return;
        }
        
        String estado = partes[0];
        String mensaje = partes[1];
        
        if ("OK".equals(estado)) {
            System.out.println("Resultado: " + mensaje);
        } else {
            System.out.println("Error del servidor: " + mensaje);
        }
    }
}