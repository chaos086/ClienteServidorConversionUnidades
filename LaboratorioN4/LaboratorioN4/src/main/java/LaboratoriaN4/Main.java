package LaboratoriaN4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    decimalABinario(scanner);
                    break;
                case 2:
                    binarioADecimal(scanner);
                    break;
                case 3:
                    decimalAHexadecimal(scanner);
                    break;
                case 4:
                    hexadecimalADecimal(scanner);
                    break;
                case 5:
                    binarioAHexadecimal(scanner);
                    break;
                case 6:
                    hexadecimalABinario(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== CONVERSOR DE NÚMEROS ===");
        System.out.println("1. Decimal a Binario");
        System.out.println("2. Binario a Decimal");
        System.out.println("3. Decimal a Hexadecimal");
        System.out.println("4. Hexadecimal a Decimal");
        System.out.println("5. Binario a Hexadecimal");
        System.out.println("6. Hexadecimal a Binario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static void decimalABinario(Scanner scanner) {
        System.out.print("Ingrese número decimal: ");
        int decimal = scanner.nextInt();
        System.out.print("Ingrese longitud en bits: ");
        int bits = scanner.nextInt();
        
        String binario = Integer.toBinaryString(decimal);
        String resultado = String.format("%" + bits + "s", binario).replace(' ', '0');
        
        System.out.println("Resultado: " + resultado);
    }
    
    private static void binarioADecimal(Scanner scanner) {
        System.out.print("Ingrese número binario: ");
        String binario = scanner.nextLine();
        
        int decimal = Integer.parseInt(binario, 2);
        
        System.out.println("Resultado: " + decimal);
    }
    
    private static void decimalAHexadecimal(Scanner scanner) {
        System.out.print("Ingrese número decimal: ");
        int decimal = scanner.nextInt();
        System.out.print("Ingrese ancho en dígitos: ");
        int ancho = scanner.nextInt();
        
        String hexadecimal = Integer.toHexString(decimal).toUpperCase();
        String resultado = String.format("%" + ancho + "s", hexadecimal).replace(' ', '0');
        
        System.out.println("Resultado: " + resultado);
    }
    
    private static void hexadecimalADecimal(Scanner scanner) {
        System.out.print("Ingrese número hexadecimal: ");
        String hexadecimal = scanner.nextLine();
        
        int decimal = Integer.parseInt(hexadecimal, 16);
        
        System.out.println("Resultado: " + decimal);
    }
    
    private static void binarioAHexadecimal(Scanner scanner) {
        System.out.print("Ingrese número binario: ");
        String binario = scanner.nextLine();
        System.out.print("Ingrese ancho en dígitos: ");
        int ancho = scanner.nextInt();
        
        int decimal = Integer.parseInt(binario, 2);
        String hexadecimal = Integer.toHexString(decimal).toUpperCase();
        String resultado = String.format("%" + ancho + "s", hexadecimal).replace(' ', '0');
        
        System.out.println("Resultado: " + resultado);
    }
    
    private static void hexadecimalABinario(Scanner scanner) {
        System.out.print("Ingrese número hexadecimal: ");
        String hexadecimal = scanner.nextLine();
        
        int decimal = Integer.parseInt(hexadecimal, 16);
        String binario = Integer.toBinaryString(decimal);
        
        // Asegurar que sea múltiplo de 4 bits para representación hexadecimal
        int padding = (4 - binario.length() % 4) % 4;
        String resultado = "0".repeat(padding) + binario;
        
        System.out.println("Resultado: " + resultado);
    }
}