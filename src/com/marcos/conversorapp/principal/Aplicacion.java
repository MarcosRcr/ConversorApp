package com.marcos.conversorapp.principal;

import com.google.gson.JsonObject;
import com.marcos.conversorapp.modelos.ApiConnection;
import com.marcos.conversorapp.modelos.Convertidor;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Aplicacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            boolean continuar = true;

            while (continuar) {
                System.out.println("Ingrese la moneda base deseada (ejemplo: USD):");
                String baseCurrency = scanner.nextLine();
                baseCurrency = baseCurrency.toUpperCase();

                JsonObject ratesObject = ApiConnection.getExchangeRates(baseCurrency); // Llamada a un método para obtener las tasas de cambio
                JsonObject conversionRates = ratesObject.getAsJsonObject("conversion_rates"); // Extraer el objeto conversion_rates

                // Imprimir las monedas disponibles
                System.out.println("Monedas disponibles:");
                Set<String> currencies = conversionRates.keySet();
                int count = 0;
                for (String currency : currencies) {
                    System.out.printf("%-10s", currency);
                    count++;
                    if (count % 10 == 0) {
                        System.out.println();
                    }
                }
                System.out.println("\n");

                System.out.println("Ingrese la segunda moneda (ejemplo: MXN):");
                String secondCurrency = scanner.nextLine();
                secondCurrency = secondCurrency.toUpperCase();
                System.out.println("Ingrese la cantidad de " + baseCurrency + " que desea convertir a " + secondCurrency + ":");
                double amountToConvert = scanner.nextDouble();
                scanner.nextLine(); // Consumir el carácter de nueva línea en el búfer

                Convertidor convertidor = new Convertidor(conversionRates); // Crear una instancia de Convertidor

                String convertedAmount = convertidor.convertirMoneda(baseCurrency, secondCurrency, amountToConvert);
                System.out.println(baseCurrency + " a " + secondCurrency + " = " + convertedAmount);

                System.out.println("¿Desea realizar otra conversión? (s/n)");
                String respuesta = scanner.nextLine();
                if (!respuesta.equalsIgnoreCase("s")) {
                    continuar = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener las tasas de cambio: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}