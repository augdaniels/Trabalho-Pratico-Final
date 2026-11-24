import java.util.ArrayList;
import java.util.List;

public class Servico {

    private int id;
    private String nome;
    private double valor;
    private static final List<Servico> servicos = new ArrayList<>();

    public Servico(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
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

    public void setValor(double valor) {
        this.valor = valor;
    }
    public static void cadastrar(Servico servico) {
        servicos.add(servico);
    }

    public static Servico consultar(int id) {
        for (Servico servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }

    public static void atualizar(int id, String nome, double valor) {
        Servico servico = consultar(id);

        if (servico != null) {
            servico.setNome(nome);
            servico.setValor(valor);
        }
    }

    public static void excluir(int id) {
        servicos.removeIf(servico -> servico.getId() == id);
    }
}