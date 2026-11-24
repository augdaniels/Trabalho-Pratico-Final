public class Funcionario {

    private int idFuncionario;
    private String nome;
    private String cargo;

    public Funcionario(String nome, String cargo) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }
}