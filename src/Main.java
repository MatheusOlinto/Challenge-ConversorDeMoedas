import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import service.ApiService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("*************************************");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moeda :)");
            System.out.println("*************************************");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileiro");
            System.out.println("4) Real brasileiro => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Sair");
            System.out.println("*************************************");

            System.out.print("Escolha uma opção válida: ");

            int opcao = 0;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n⚠️ Opção inválida! Digite apenas números de 1 a 7.\n");
                scanner.nextLine();
                continue;
            }

            if (opcao == 7) {
                System.out.println("\nSaindo... Até logo! 👋");
                break;
            }

            String from = "";
            String to = "";

            switch (opcao) {
                case 1 -> { from = "USD"; to = "ARS"; }
                case 2 -> { from = "ARS"; to = "USD"; }
                case 3 -> { from = "USD"; to = "BRL"; }
                case 4 -> { from = "BRL"; to = "USD"; }
                case 5 -> { from = "USD"; to = "COP"; }
                case 6 -> { from = "COP"; to = "USD"; }
                default -> {
                    System.out.println("\n⚠️ Opção inválida! Escolha um número de 1 a 7.\n");
                    continue;
                }
            }

            System.out.print("Digite o valor que deseja converter: ");
            double valor;
            try {
                valor = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("\n⚠️ Valor inválido! Digite um número (ex: 100.50).\n");
                scanner.nextLine();
                continue;
            }

            try {
                double taxa = ApiService.getExchangeRate(from, to);
                double convertido = valor * taxa;
                System.out.printf("💱 %.2f [%s] corresponde a %.2f [%s]%n", valor, from, convertido, to);
            } catch (IOException | InterruptedException e) {
                System.out.println("Erro ao buscar taxa de câmbio: " + e.getMessage());
            }

            System.out.println("\nDeseja realizar outra conversão? (s/n)");
            scanner.nextLine();
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (!resposta.equals("s")) {
                continuar = false;
                System.out.println("\nEncerrando o conversor... Até a próxima! 👋");
            }
        }

        scanner.close();
    }
}