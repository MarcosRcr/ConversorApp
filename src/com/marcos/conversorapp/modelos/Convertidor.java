// En el archivo Convertidor.java dentro del paquete modelos

package com.marcos.conversorapp.modelos;

import com.google.gson.JsonObject;

public class Convertidor {
    private JsonObject tasasDeCambio;

    public Convertidor(JsonObject tasasDeCambio) {
        this.tasasDeCambio = tasasDeCambio;
    }
    public String convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) {
        monedaOrigen = monedaOrigen.toUpperCase();
        monedaDestino = monedaDestino.toUpperCase();

        if (tasasDeCambio.has(monedaOrigen) && tasasDeCambio.has(monedaDestino)) {
            double tasaOrigenADestino = tasasDeCambio.get(monedaDestino).getAsDouble();
            double tasaDestinoAOrigen = tasasDeCambio.get(monedaOrigen).getAsDouble();
            double cantidadConvertida = (cantidad * tasaOrigenADestino) / tasaDestinoAOrigen;
            return String.format("%.2f", cantidadConvertida);
        } else {
            return "Moneda no encontrada en las tasas de cambio.";
        }
    }
}
