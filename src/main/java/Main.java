import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Um único Scanner para o programa inteiro
        Scanner scanner = new Scanner(System.in);

        // Dados fixos da apresentação
        Cliente cliente = new Cliente("Maria Souza", "(31) 99999-0000");
        Veiculo veiculo = new Veiculo("ABC1D23", "Gol", cliente);
        Funcionario funcionario = new Funcionario("João Lima", "Lavador");

        Servico lavagem = new Servico("Lavagem completa", new BigDecimal("40.00"));
        Servico cera = new Servico("Cera", new BigDecimal("25.00"));
        Servico.cadastrar(lavagem);
        Servico.cadastrar(cera);

        Agendamento agendamento = new Agendamento(
                LocalDateTime.of(2026, 12, 1, 9, 0), cliente, veiculo, funcionario);
        agendamento.adicionarServico(lavagem);
        agendamento.adicionarServico(cera);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("=== Agendamento #" + agendamento.getIdAgendamento() + " ===");
        System.out.println("Cliente: " + cliente.getNome() + " | " + cliente.getTelefone());
        System.out.println("Veículo: " + veiculo.getModelo() + " (" + veiculo.getPlaca() + ")");
        System.out.println("Funcionário: " + funcionario.getNome() + " | " + funcionario.getCargo());
        System.out.println("Data: " + agendamento.getDataHora().format(formato));
        System.out.println("Serviços:");
        for (Servico servico : agendamento.getServicos()) {
            System.out.println("  - " + servico.getNome() + ": R$ " + servico.getValor());
        }
        System.out.println("Total: R$ " + agendamento.calcularTotal());
        System.out.println("Status: " + agendamento.getStatus());

        System.out.println();
        System.out.println("=== Pagamento ===");
        Pagamento pagamento = new Pagamento(agendamento);
        pagamento.registrarPagamento(scanner);

        agendamento.concluir();

        System.out.println();
        System.out.println("=== Resumo final ===");
        System.out.println(pagamento);
        System.out.println("Status do agendamento: " + agendamento.getStatus());

        scanner.close();
    }
}
