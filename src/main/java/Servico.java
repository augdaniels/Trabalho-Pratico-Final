public class Servico {

    private int id;
    private String nome;

    public Servico(int id, String lavagemSimples, double v) {
        this.id = id;
        this.nome = lavagemSimples;
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
}