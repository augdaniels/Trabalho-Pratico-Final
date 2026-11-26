import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Pagamento {
    private static int proximoId = 1;
    private final int idPagamento;
    private BigDecimal valor;
    private TipoPagamento tipoPagamento;
    private StatusPagamento statusPagamento;
    private LocalDateTime dataPagamento;
    private Agendamento agendamento;

    public Pagamento(Agendamento agendamento) {
        if (agendamento == null) {
            throw new IllegalArgumentException("O agendamento não pode ser nulo.");
        }
        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new IllegalArgumentException("Não é possível pagar um agendamento cancelado.");
        }
        BigDecimal total = agendamento.calcularTotal();
        if (total == null || total.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        this.idPagamento = proximoId++;
        this.agendamento = agendamento;
        this.valor = total;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public void registrarPagamento(Scanner scanner) {
        if (statusPagamento != StatusPagamento.PENDENTE) {
            throw new IllegalStateException(
                    "Só é possível registrar um pagamento PENDENTE. Status atual: " + statusPagamento);
        }
        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException(
                    "Não é possível registrar o pagamento de um agendamento cancelado.");
        }

        tipoPagamento = selecionarTipo(scanner);
        System.out.println("Valor a cobrar: " + formatar(valor));

        if (tipoPagamento == TipoPagamento.DINHEIRO) {
            receberDinheiro(scanner);
        }

        statusPagamento = StatusPagamento.PAGO;
        dataPagamento = LocalDateTime.now();
        System.out.println("Pagamento realizado com sucesso! " + this);
    }

    public void cancelarPagamento() {
        if (statusPagamento != StatusPagamento.PENDENTE) {
            throw new IllegalStateException(
                    "Só é possível cancelar um pagamento PENDENTE. Status atual: " + statusPagamento);
        }
        statusPagamento = StatusPagamento.CANCELADO;
    }

    public static TipoPagamento selecionarTipo(Scanner scanner) {
        TipoPagamento[] tipos = TipoPagamento.values();
        while (true) {
            System.out.println("Forma de pagamento:");
            for (int i = 0; i < tipos.length; i++) {
                System.out.println((i + 1) + " - " + tipos[i]);
            }
            System.out.print("Escolha uma opção: ");
            String entrada = scanner.nextLine().trim();
            try {
                int opcao = Integer.parseInt(entrada);
                if (opcao >= 1 && opcao <= tipos.length) {
                    return tipos[opcao - 1];
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Opção inválida. Tente novamente.");
        }
    }


    private void receberDinheiro(Scanner scanner) {
        BigDecimal recebido = lerValor(scanner);
        while (recebido.compareTo(valor) < 0) {
            System.out.println("Valor insuficiente. O total é " + formatar(valor) + ".");
            recebido = lerValor(scanner);
        }
        BigDecimal troco = recebido.subtract(valor);
        if (troco.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Troco: " + formatar(troco));
        }
    }


    private static BigDecimal lerValor(Scanner scanner) {
        while (true) {
            System.out.print("Valor recebido: R$ ");
            String entrada = scanner.nextLine().trim().replace(',', '.');
            try {
                BigDecimal numero = new BigDecimal(entrada);
                if (numero.compareTo(BigDecimal.ZERO) > 0) {
                    return numero;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Valor inválido. Digite um número maior que zero (ex.: 50,00).");
        }
    }




    //Getters e setters

    public int getIdPagamento() {
        return idPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
    private static String formatar(BigDecimal valor) {
        return "R$ " + valor.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Pagamento #" + idPagamento
                + " | " + formatar(valor)
                + " | " + tipoPagamento
                + " | " + statusPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
}
