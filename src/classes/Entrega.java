package classes;

import java.util.ArrayList;
import java.util.List;

public class Entrega {
    protected String nomeEntregador;
    protected List<int[]> movimentos; // Lista de movimentos como pares (x, y)
    protected double distanciaPercorrida; // Total da distância percorrida
    protected int[] posicaoAtual = new int[]{1, 1};

    public Entrega(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
        this.movimentos = new ArrayList<>();
        this.distanciaPercorrida = 0.0;
        // O entregador começa na posição (1,1)
        this.movimentos.add(new int[]{1, 1});
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    public void moverNorte(int quadras) {
        int novaPosicaoY = posicaoAtual[1] - quadras;
        if (novaPosicaoY >= 0) {
            distanciaPercorrida += quadras;
            posicaoAtual[1] = novaPosicaoY;
            movimentos.add(new int[]{posicaoAtual[0], novaPosicaoY});
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void moverSul(int quadras) {
        int novaPosicaoY = posicaoAtual[1] + quadras;
        if (novaPosicaoY < 8) {
            distanciaPercorrida += quadras;
            posicaoAtual[1] = novaPosicaoY;
            movimentos.add(new int[]{posicaoAtual[0], novaPosicaoY});
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void moverLeste(int quadras) {
        int novaPosicaoX = posicaoAtual[0] + quadras;
        if (novaPosicaoX < 8) {
            distanciaPercorrida += quadras;
            posicaoAtual[0] = novaPosicaoX;
            movimentos.add(new int[]{novaPosicaoX, posicaoAtual[1]});
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void moverOeste(int quadras) {
        int novaPosicaoX = posicaoAtual[0] - quadras;
        if (novaPosicaoX >= 0) {
            distanciaPercorrida += quadras;
            posicaoAtual[0] = novaPosicaoX;
            movimentos.add(new int[]{novaPosicaoX, posicaoAtual[1]});
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void imprimirCaminho() {
        System.out.println("Caminho percorrido pelo entregador " + nomeEntregador + ":");
        for (int[] posicao : movimentos) {
            System.out.println("Posição: (" + posicao[0] + ", " + posicao[1] + ")");
        }
        System.out.println("Distância total percorrida: " + distanciaPercorrida + " quadras.");
    }

    public void visualizarMapa() {
        char[][] mapa = new char[8][8]; // Mapa 8x8 quadras
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa[i][j] = '.'; // Inicializar o mapa com pontos
            }
        }

        // Marcar a posição inicial
        int[] inicio = movimentos.get(0); // A primeira posição
        mapa[inicio[1]][inicio[0]] = 'S'; // 'S' para o ponto de partida

        // Marcar os pontos percorridos no caminho
        for (int i = 1; i < movimentos.size() - 1; i++) {
            int[] posicao = movimentos.get(i);
            mapa[posicao[1]][posicao[0]] = 'X'; // 'X' para o caminho percorrido
        }

        // Marcar a posição final
        int[] fim = movimentos.get(movimentos.size() - 1);
        mapa[fim[1]][fim[0]] = 'F'; // 'F' para o ponto final

        // Exibir o mapa
        System.out.println("Mapa da entrega:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println(); // Pular linha após cada linha do mapa
        }
    }


    // Retorna a posição atual (última posição da lista)
    public int[] getPosicaoAtual() {
        return movimentos.get(movimentos.size() - 1);
    }

    // Retorna a distância total percorrida
    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }
}
