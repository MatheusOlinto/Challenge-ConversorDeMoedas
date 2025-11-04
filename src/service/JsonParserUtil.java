package service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {

    public static double getRateFromJson(String json, String currencyCode) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

        if (rates == null || !rates.has(currencyCode)) {
            throw new IllegalArgumentException("Moeda de destino não encontrada: " + currencyCode);
        }

        return rates.get(currencyCode).getAsDouble();
    }
}