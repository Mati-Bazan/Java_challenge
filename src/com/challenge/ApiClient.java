package com.challenge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiClient {
    // Cambiamos la URL base para que sea dinámica
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/43f1e1266fb20a05de631fb4/latest/";

    public JsonObject getRates(String baseCurrency) {
        JsonObject conversionRates = null;
        try {
            // Crear la URL con la moneda base
            URL url = new URL(API_URL + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta de la API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();

            // Parsear el JSON usando Gson
            JsonObject jsonResponse = JsonParser.parseString(content.toString()).getAsJsonObject();

            // Verificamos si la respuesta fue exitosa
            if (jsonResponse.get("result").getAsString().equals("success")) {
                conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
            } else {
                System.out.println("Error en la API: " + jsonResponse.get("result").getAsString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conversionRates;
    }
}

