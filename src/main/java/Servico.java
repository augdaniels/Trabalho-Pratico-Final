import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Servico {

    private static int proximoId = 1;
    private static final List<Servico> servicos = new ArrayList<>();

    private final int id;
    private String nome;
    private BigDecimal valor;

    public Servico(String nome, BigDecimal valor) {
        validarValor(valor);
        this.id = proximoId++;
        this.nome = nome;
        this.valor = valor;
    }

    private static void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor do serviço não pode ser nulo nem negativo.");
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        validarValor(valor);
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

    public static void atualizar(int id, String nome, BigDecimal valor) {
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