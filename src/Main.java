import classes.Entrega;
import classes.EntregaAPe;
import classes.EntregaCarro;
import classes.EntregaDrone;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        Entrega entrega = null;

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar nova entrega");
            System.out.println("2. Mover entrega");
            System.out.println("3. Visualizar mapa");
            System.out.println("4. Exibir caminho e distância");
            System.out.println("5. Finalizar entrega");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    entrega = cadastrarEntrega(scanner);
                    break;
                case 2:
                    if (entrega != null) {
                        moverEntrega(scanner, entrega);
                    } else {
                        System.out.println("Nenhuma entrega cadastrada!");
                    }
                    break;
                case 3:
                    if (entrega != null) {
                        entrega.visualizarMapa();
                    } else {
                        System.out.println("Nenhuma entrega cadastrada!");
                    }
                    break;
                case 4:
                    if (entrega != null) {
                        entrega.imprimirCaminho();
                    } else {
                        System.out.println("Nenhuma entrega cadastrada!");
                    }
                    break;
                case 5:
                    if (entrega != null) {
                        System.out.println("Entrega finalizada.");
                        entrega.imprimirCaminho();
                        return; // Finaliza o programa após a entrega
                    } else {
                        System.out.println("Nenhuma entrega cadastrada!");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static Entrega cadastrarEntrega(Scanner scanner) {
        System.out.println("\n--- Cadastrar Entrega ---");
        System.out.print("Informe o nome do entregador: ");
        String nomeEntregador = scanner.nextLine();

        System.out.println("Escolha o tipo de entrega:");
        System.out.println("1. Entrega por Carro");
        System.out.println("2. Entrega por Drone");
        System.out.println("3. Entrega a Pé");
        System.out.print("Opção: ");
        int tipoEntrega = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        switch (tipoEntrega) {
            case 1:
                System.out.print("Informe o consumo médio do carro (litros por quadra): ");
                double consumoMedio = scanner.nextDouble();
                return new EntregaCarro(nomeEntregador, consumoMedio);
            case 2:
                return new EntregaDrone(nomeEntregador);
            case 3:
                return new EntregaAPe(nomeEntregador);
            default:
                System.out.println("Tipo de entrega inválido!");
                return null;
        }
    }

    private static void moverEntrega(Scanner scanner, Entrega entrega) {
        System.out.println("\n--- Movimentar Entrega ---");
        System.out.println("1. Mover ao Norte");
        System.out.println("2. Mover ao Sul");
        System.out.println("3. Mover ao Leste");
        System.out.println("4. Mover ao Oeste");

        // Opção especial para drone
        if (entrega instanceof EntregaDrone) {
            System.out.println("5. Mover diretamente (apenas para drones)");
        }

        System.out.print("Escolha uma direção: ");
        int direcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        switch (direcao) {
            case 1:
                System.out.print("Quantas quadras mover ao norte? ");
                int norte = scanner.nextInt();
                entrega.moverNorte(norte);
                break;
            case 2:
                System.out.print("Quantas quadras mover ao sul? ");
                int sul = scanner.nextInt();
                entrega.moverSul(sul);
                break;
            case 3:
                System.out.print("Quantas quadras mover ao leste? ");
                int leste = scanner.nextInt();
                entrega.moverLeste(leste);
                break;
            case 4:
                System.out.print("Quantas quadras mover ao oeste? ");
                int oeste = scanner.nextInt();
                entrega.moverOeste(oeste);
                break;
            case 5:
                if (entrega instanceof EntregaDrone) {
                    System.out.print("Informe o destino X: ");
                    int x = scanner.nextInt();
                    System.out.print("Informe o destino Y: ");
                    int y = scanner.nextInt();
                    ((EntregaDrone) entrega).moverDireto(x, y);
                } else {
                    System.out.println("Opção inválida para este tipo de entrega!");
                }
                break;
            default:
                System.out.println("Direção inválida!");
        }
    }
}
