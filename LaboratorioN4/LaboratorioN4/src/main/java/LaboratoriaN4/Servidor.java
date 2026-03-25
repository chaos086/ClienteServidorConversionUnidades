package LaboratoriaN4;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 12345;
    private static final String SEPARADOR = ";";
    
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de conversiones iniciado en puerto " + PUERTO);
            System.out.println("Esperando conexiones...");
            
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                new Thread(() -> manejarCliente(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en servidor: " + e.getMessage());
        }
    }
    
    private static void manejarCliente(Socket cliente) {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {
            
            String solicitud;
            while ((solicitud = entrada.readLine()) != null) {
                System.out.println("Solicitud recibida: " + solicitud);
                String respuesta = procesarSolicitud(solicitud);
                System.out.println("Respuesta enviada: " + respuesta);
                salida.println(respuesta);
                
                if (solicitud.startsWith("0")) break; // Comando de desconexión
            }
            
        } catch (IOException e) {
            System.err.println("Error manejando cliente: " + e.getMessage());
        } finally {
            try {
                cliente.close();
                System.out.println("Cliente desconectado");
            } catch (IOException e) {
                System.err.println("Error cerrando cliente: " + e.getMessage());
            }
        }
    }
    
    private static String procesarSolicitud(String solicitud) {
        try {
            if (solicitud == null || solicitud.trim().isEmpty()) {
                return "ERROR;Solicitud vacía";
            }
            
            String[] partes = solicitud.split(SEPARADOR);
            if (partes.length < 2) {
                return "ERROR;Formato inválido";
            }
            
            int operacion = Integer.parseInt(partes[0]);
            
            switch (operacion) {
                case 0: // Desconexión
                    return "OK;Desconexión";
                    
                case 1: // Decimal a Binario
                    if (partes.length != 3) return "ERROR;Parámetros insuficientes";
                    int decimal1 = Integer.parseInt(partes[1]);
                    int bits = Integer.parseInt(partes[2]);
                    String binario = Integer.toBinaryString(decimal1);
                    String resultado1 = String.format("%" + bits + "s", binario).replace(' ', '0');
                    return "OK;" + resultado1;
                    
                case 2: // Binario a Decimal
                    String binario2 = partes[1];
                    int resultado2 = Integer.parseInt(binario2, 2);
                    return "OK;" + resultado2;
                    
                case 3: // Decimal a Hexadecimal
                    if (partes.length != 3) return "ERROR;Parámetros insuficientes";
                    int decimal3 = Integer.parseInt(partes[1]);
                    int ancho = Integer.parseInt(partes[2]);
                    String hex = Integer.toHexString(decimal3).toUpperCase();
                    String resultado3 = String.format("%" + ancho + "s", hex).replace(' ', '0');
                    return "OK;" + resultado3;
                    
                case 4: // Hexadecimal a Decimal
                    String hex4 = partes[1];
                    int resultado4 = Integer.parseInt(hex4, 16);
                    return "OK;" + resultado4;
                    
                case 5: // Binario a Hexadecimal
                    if (partes.length != 3) return "ERROR;Parámetros insuficientes";
                    String binario5 = partes[1];
                    int ancho5 = Integer.parseInt(partes[2]);
                    int decimal5 = Integer.parseInt(binario5, 2);
                    String hex5 = Integer.toHexString(decimal5).toUpperCase();
                    String resultado5 = String.format("%" + ancho5 + "s", hex5).replace(' ', '0');
                    return "OK;" + resultado5;
                    
                case 6: // Hexadecimal a Binario
                    String hex6 = partes[1];
                    int decimal6 = Integer.parseInt(hex6, 16);
                    String binario6 = Integer.toBinaryString(decimal6);
                    int padding = (4 - binario6.length() % 4) % 4;
                    String resultado6 = "0".repeat(padding) + binario6;
                    return "OK;" + resultado6;
                    
                default:
                    return "ERROR;Operación no válida";
            }
        } catch (NumberFormatException e) {
            return "ERROR;Formato numérico inválido";
        } catch (Exception e) {
            return "ERROR;" + e.getMessage();
        }
    }
}