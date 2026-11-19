public class Veiculo {
    private int idVeiculo;
    private String placa;
    private String modelo;
    private Cliente cliente;
    private static int proximoId = 1;

    public Veiculo(String placa, String modelo, Cliente cliente) {
        this.idVeiculo = proximoId++;
        this.placa = placa;
        this.modelo = modelo;
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }
}
