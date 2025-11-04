package service;

import java.net.http.*;
import java.net.URI;
import java.io.IOException;

public class ApiService {
    private static final String API_KEY = "4b83af84cc60cbffd490c311";

    public static double getExchangeRate(String from, String to) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Erro ao conectar à API: código " + response.statusCode());
        }

        String json = response.body();
        return JsonParserUtil.getRateFromJson(json, to);
    }
}