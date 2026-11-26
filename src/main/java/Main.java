import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        // Um único Scanner para o programa inteiro
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cliente ===");
        String nomeCliente = lerTexto(scanner, "Nome: ");
        String telefone = lerTexto(scanner, "Telefone: ");
        Cliente cliente = new Cliente(nomeCliente, telefone);

        System.out.println();
        System.out.println("=== Veículo ===");
        String placa = lerTexto(scanner, "Placa: ");
        String modelo = lerTexto(scanner, "Modelo: ");
        Veiculo veiculo = new Veiculo(placa, modelo, cliente);

        System.out.println();
        System.out.println("=== Funcionário ===");
        String nomeFuncionario = lerTexto(scanner, "Nome: ");
        String cargo = lerTexto(scanner, "Cargo: ");
        Funcionario funcionario = new Funcionario(nomeFuncionario, cargo);

        System.out.println();
        System.out.println("=== Agendamento ===");
        LocalDateTime dataHora = lerDataHora(scanner, "Data e hora (dd/MM/yyyy HH:mm): ");
        Agendamento agendamento = new Agendamento(dataHora, cliente, veiculo, funcionario);

        System.out.println();
        System.out.println("=== Serviços ===");
        do {
            String nomeServico = lerTexto(scanner, "Nome do serviço: ");
            BigDecimal valor = new BigDecimal("65.00");
            Servico servico = new Servico(nomeServico, valor);
            Servico.cadastrar(servico);
            agendamento.adicionarServico(servico);
        } while (lerSimNao(scanner, "Adicionar outro serviço? (s/n): "));

        System.out.println();
        System.out.println("=== Agendamento #" + agendamento.getIdAgendamento() + " ===");
        System.out.println("Cliente: " + cliente.getNome() + " | " + cliente.getTelefone());
        System.out.println("Veículo: " + veiculo.getModelo() + " (" + veiculo.getPlaca() + ")");
        System.out.println("Funcionário: " + funcionario.getNome() + " | " + funcionario.getCargo());
        System.out.println("Data: " + agendamento.getDataHora().format(FORMATO));
        System.out.println("Serviços:");
        for (Servico servico : agendamento.getServicos()) {
            System.out.println("  - " + servico.getNome() + ": " + dinheiro(servico.getValor()));
        }
        System.out.println("Total: " + dinheiro(agendamento.calcularTotal()));
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

    // Mostra o valor sempre com 2 casas
    private static String dinheiro(BigDecimal valor) {
        return "R$ " + valor.setScale(2, RoundingMode.HALF_UP);
    }

    // Repete a pergunta até o usuário digitar algo (não aceita vazio)
    private static String lerTexto(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("O campo não pode ficar vazio.");
        }
    }

    // Repete até a data e a hora estarem no formato dd/MM/yyyy HH:mm
    private static LocalDateTime lerDataHora(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(entrada, FORMATO);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy HH:mm (ex.: 01/12/2026 09:00).");
            }
        }
    }

    // Repete até o usuário digitar s ou n
    private static boolean lerSimNao(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().toLowerCase();
            if (entrada.equals("s")) {
                return true;
            }
            if (entrada.equals("n")) {
                return false;
            }
            System.out.println("Digite s para sim ou n para não.");
        }
    }
}