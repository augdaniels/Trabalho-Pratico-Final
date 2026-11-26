public class Funcionario {
    private int proximoId =1;
    private int idFuncionario;
    private String nome;
    private String cargo;

    public Funcionario( String nome, String cargo) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.idFuncionario = proximoId++;
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.nome = nome;
    }
    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("O cargo não pode ser vazio");
        }
        this.cargo = cargo;
    }
}