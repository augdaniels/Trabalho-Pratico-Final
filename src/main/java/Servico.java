public class Servico {

    private int id;
    private String nome;
    private double valor;

    public Servico(int id, String lavagemSimples, double v) {
        this.id = id;
        this.nome = lavagemSimples;
        this.valor = v;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
        public String getNome() {
            return nome;
        }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
}