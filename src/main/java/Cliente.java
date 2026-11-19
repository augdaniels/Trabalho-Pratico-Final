public class Cliente {

    private String nome;
    private String telefone;
    private static int proximoId = 1;
    private int idCliente;
    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.idCliente = proximoId++;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}