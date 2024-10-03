package classes;

public class EntregaCarro extends Entrega{
    private double consumoMedio; // Consumo médio em litros por quilômetro

    public EntregaCarro(String nomeEntregador, double consumoMedio) {
        super(nomeEntregador);
        this.consumoMedio = consumoMedio;
    }

    // Método para calcular o consumo total de gasolina com base na distância percorrida
    public double calcularConsumoTotal() {
        return getDistanciaPercorrida() * consumoMedio;
    }

    @Override
    public void imprimirCaminho() {
        super.imprimirCaminho();
        System.out.println("Consumo total de gasolina: " + calcularConsumoTotal() + " litros.");
    }


}
