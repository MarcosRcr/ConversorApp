package com.marcos.conversorapp.modelos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiConnection {
    private static final String API_KEY = "215d5b6a64731ceef54797db"; // Tu clave de API
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static JsonObject getExchangeRates(String baseCurrency) throws IOException {
        String apiUrl = API_BASE_URL + API_KEY + "/latest/" + baseCurrency;
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Check if the response code is successful (200)
        if (conn.getResponseCode() == 200) {
            // Read the response body
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder responseBody = new StringBuilder();
            while (scanner.hasNextLine()) {
                responseBody.append(scanner.nextLine());
            }
            scanner.close();
            conn.disconnect();

            // Convertir la cadena de respuesta a un JsonObject
            JsonParser parser = new JsonParser();
            return parser.parse(responseBody.toString()).getAsJsonObject();
        } else {
            throw new IOException("Failed to fetch exchange rates. Response code: " + conn.getResponseCode());
        }
    }
}
