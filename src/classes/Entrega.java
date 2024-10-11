package classes;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Entrega {
    protected String nomeEntregador;
    protected List<int[]> movimentos; // Lista de movimentos como pares (x, y)
    protected double distanciaPercorrida; // Total da distância percorrida
    protected int[] posicaoAtual = new int[]{0, 0}; // Começa na posição (0, 0)
    protected String simbolo; // Alterado para String para suportar emojis

    public Entrega(String nomeEntregador, String simbolo) { // Alterado para String
        this.nomeEntregador = nomeEntregador;
        this.movimentos = new ArrayList<>();
        this.distanciaPercorrida = 0.0;
        // O entregador começa na posição (0,0)
        this.movimentos.add(new int[]{0, 0});
        this.simbolo = simbolo;
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    // Função auxiliar para mover e marcar todas as quadras intermediárias
    protected void marcarMovimento(int xInicial, int yInicial, int xFinal, int yFinal) {
        int x = xInicial;
        int y = yInicial;

        while (x != xFinal || y != yFinal) {
            if (x < xFinal) x++;
            else if (x > xFinal) x--;

            if (y < yFinal) y++;
            else if (y > yFinal) y--;

            movimentos.add(new int[]{x, y});
        }
    }

    public void moverNorte(int quadras) {
        int novaPosicaoY = posicaoAtual[1] - quadras;
        if (novaPosicaoY >= 0) {
            distanciaPercorrida += quadras;
            marcarMovimento(posicaoAtual[0], posicaoAtual[1], posicaoAtual[0], novaPosicaoY);
            posicaoAtual[1] = novaPosicaoY;
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void moverSul(int quadras) {
        int novaPosicaoY = posicaoAtual[1] + quadras;
        if (novaPosicaoY < 8) {
            distanciaPercorrida += quadras;
            marcarMovimento(posicaoAtual[0], posicaoAtual[1], posicaoAtual[0], novaPosicaoY);
            posicaoAtual[1] = novaPosicaoY;
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void moverLeste(int quadras) {
        int novaPosicaoX = posicaoAtual[0] + quadras;
        if (novaPosicaoX < 8) {
            distanciaPercorrida += quadras;
            marcarMovimento(posicaoAtual[0], posicaoAtual[1], novaPosicaoX, posicaoAtual[1]);
            posicaoAtual[0] = novaPosicaoX;
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void moverOeste(int quadras) {
        int novaPosicaoX = posicaoAtual[0] - quadras;
        if (novaPosicaoX >= 0) {
            distanciaPercorrida += quadras;
            marcarMovimento(posicaoAtual[0], posicaoAtual[1], novaPosicaoX, posicaoAtual[1]);
            posicaoAtual[0] = novaPosicaoX;
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    public void imprimirCaminho() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Caminho percorrido pelo entregador " + nomeEntregador + ":");
        for (int[] posicao : movimentos) {
            System.out.println("Posição: (" + posicao[0] + ", " + posicao[1] + ")");
        }
        System.out.println("Distância total percorrida: " + df.format(distanciaPercorrida) + " quadras.");
    }

    public void visualizarMapa() {
        String[][] mapa = new String[8][8]; // Alterado para String
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mapa[i][j] = " . "; // Inicializar o mapa com pontos
            }
        }

        // Marcar todas as posições do caminho com 'X'
        for (int[] posicao : movimentos) {
            mapa[posicao[1]][posicao[0]] = " X ";
        }

        // Marcar apenas o ponto final com o símbolo correspondente
        mapa[movimentos.get(movimentos.size() - 1)[1]][movimentos.get(movimentos.size() - 1)[0]] = simbolo;

        // Exibir o mapa
        System.out.println("Mapa da entrega:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
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