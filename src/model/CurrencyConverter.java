package model;

import service.ApiService;
import java.io.IOException;

public class CurrencyConverter {

    public static double converter(String from, String to, double valor)
            throws IOException, InterruptedException {

        double taxa = ApiService.getExchangeRate(from, to);
        return valor * taxa;
    }
}