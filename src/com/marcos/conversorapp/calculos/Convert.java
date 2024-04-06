package com.marcos.conversorapp.calculos;

import java.util.Map;
public class Convert {
    private Map<String, Double> exchangeRates;

    public Convert(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double fromRate = exchangeRates.getOrDefault(fromCurrency, 1.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 1.0);

        // Realiza el cálculo de conversión
        return amount * (toRate / fromRate);
    }
}