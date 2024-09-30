package com.challenge;

import com.google.gson.JsonObject;
import java.util.Scanner;

public class Challenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiClient apiClient = new ApiClient();
        boolean exit = false;

        while (!exit) {
            System.out.println("**********************************");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Convertir de USD a ARS");
            System.out.println("2. Convertir de ARS a USD");
            System.out.println("3. Convertir de BRL a USD");
            System.out.println("4. Convertir de USD a BRL");
            System.out.println("5. Convertir de EUR a USD");
            System.out.println("6. Convertir de USD a EUR");
            System.out.println("7. Salir");
            System.out.println("**********************************");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    convertirMoneda(apiClient, "USD", "ARS", scanner);
                    break;
                case 2:
                    convertirMoneda(apiClient, "ARS", "USD", scanner);
                    break;
                case 3:
                    convertirMoneda(apiClient, "BRL", "USD", scanner);
                    break;
                case 4:
                    convertirMoneda(apiClient, "USD", "BRL", scanner);
                    break;
                case 5:
                    convertirMoneda(apiClient, "EUR", "USD", scanner);
                    break;
                case 6:
                    convertirMoneda(apiClient, "USD", "EUR", scanner);
                    break;
                case 7:
                    exit = true;
                    System.out.println("¡Saliendo del programa!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }

        scanner.close();
    }

    // Metodo para realizar la conversión de monedas
    private static void convertirMoneda(ApiClient apiClient, String monedaBase, String monedaObjetivo, Scanner scanner) {
        System.out.println("Ingrese el monto a convertir de " + monedaBase + " a " + monedaObjetivo + ":");
        double monto = scanner.nextDouble();

        JsonObject rates = apiClient.getRates(monedaBase);
        if (rates != null && rates.has(monedaObjetivo)) {
            double tasa = rates.get(monedaObjetivo).getAsDouble();
            double resultado = monto * tasa;
            System.out.println("El monto convertido es: " + resultado + " " + monedaObjetivo);
        } else {
            System.out.println("No se pudo obtener la tasa de cambio.");
        }
    }
}
