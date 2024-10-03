package classes;

public class EntregaDrone extends Entrega{
    public EntregaDrone(String nomeEntregador) {
        super(nomeEntregador);
    }

    public void moverDireto(int x, int y) {
        int[] posAtual = {super.movimentos.get(super.movimentos.size() - 1)[0], super.movimentos.get(super.movimentos.size() - 1)[1]};
        double distancia = Math.sqrt(Math.pow(x - posAtual[0], 2) + Math.pow(y - posAtual[1], 2));

        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            super.movimentos.add(new int[]{x, y});
            super.distanciaPercorrida += distancia;
        } else {
            System.out.println("Movimento inválido: Fora dos limites do mapa!");
        }
    }

    @Override
    public void imprimirCaminho() {
        super.imprimirCaminho();
        System.out.println("Entrega realizada por drone.");
    }
}
