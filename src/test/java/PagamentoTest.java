
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {
    private final PrintStream saidaOriginal = System.out;
    private ByteArrayOutputStream saida;

    @BeforeEach
    void capturarSaida() {
        saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void restaurarSaida() {
        System.setOut(saidaOriginal);
    }

    private String textoImpresso() {
        return saida.toString(StandardCharsets.UTF_8);
    }

    private static Agendamento agendamentoComTotal(String total) {
        BigDecimal valor = new BigDecimal(total);
        return new Agendamento(null, null, null, null) {
            @Override
            public BigDecimal calcularTotal() {
                return valor;
            }
        };
    }

    private static Pagamento pagamentoValido() {
        Agendamento agendamento = new Agendamento(null, null, null, null);
        agendamento.adicionarServico(new Servico("Lavagem", new BigDecimal("40.00")));
        return new Pagamento(agendamento);
    }

    // Simula o usuário digitando no console
    private static Scanner digitando(String texto) {
        return new Scanner(texto);
    }

    @Test
    void construtor_agendamentoNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Pagamento(null));
    }


    @Test
    void novoPagamento_comecaPendenteSemTipoComValorDoAgendamento() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        assertEquals(StatusPagamento.PENDENTE, pagamento.getStatusPagamento());
        assertNull(pagamento.getTipoPagamento());
        assertEquals(0, new BigDecimal("40.00").compareTo(pagamento.getValor()));
    }

    @Test
    void construtor_totalZero_lancaIllegalArgumentException() {
        Agendamento semServicos = new Agendamento(null, null, null, null);

        assertThrows(IllegalArgumentException.class, () -> new Pagamento(semServicos));
    }

    @Test
    void construtor_agendamentoCancelado_lancaIllegalArgumentException() {
        Agendamento agendamento = new Agendamento(null, null, null, null);
        agendamento.cancelar();

        assertThrows(IllegalArgumentException.class, () -> new Pagamento(agendamento));
    }

    @Test
    void setValor_nulo_lancaIllegalArgumentException() {
        Pagamento pagamento = pagamentoValido();

        assertThrows(IllegalArgumentException.class, () -> pagamento.setValor(null));
    }

    @Test
    void setValor_zero_lancaIllegalArgumentException() {
        Pagamento pagamento = pagamentoValido();

        assertThrows(IllegalArgumentException.class, () -> pagamento.setValor(BigDecimal.ZERO));
    }

    @Test
    void setValor_negativo_lancaIllegalArgumentException() {
        Pagamento pagamento = pagamentoValido();

        assertThrows(IllegalArgumentException.class, () -> pagamento.setValor(new BigDecimal("-1")));
    }

    @Test
    void ids_saoSequenciais() {
        Pagamento primeiro = new Pagamento(agendamentoComTotal("10.00"));
        Pagamento segundo = new Pagamento(agendamentoComTotal("10.00"));

        assertEquals(primeiro.getIdPagamento() + 1, segundo.getIdPagamento());
    }

    @Test
    void registrar_comPix_marcaPagoEGuardaOTipo() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        pagamento.registrarPagamento(digitando("3\n"));

        assertEquals(StatusPagamento.PAGO, pagamento.getStatusPagamento());
        assertEquals(TipoPagamento.PIX, pagamento.getTipoPagamento());
    }

    @Test
    void registrar_opcaoInvalidaDepoisValida_repeteOMenu() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        pagamento.registrarPagamento(digitando("9\nabc\n1\n"));

        assertEquals(TipoPagamento.DEBITO, pagamento.getTipoPagamento());
        assertTrue(textoImpresso().contains("Tente novamente"));
    }

    @Test
    void registrar_dinheiroComTroco_mostraOTroco() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        pagamento.registrarPagamento(digitando("4\n50\n"));

        assertEquals(StatusPagamento.PAGO, pagamento.getStatusPagamento());
        assertTrue(textoImpresso().contains("Troco: R$ 10.00"));
    }

    @Test
    void registrar_dinheiroInsuficiente_pedeDeNovoSemTroco() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        pagamento.registrarPagamento(digitando("4\n10\n40\n"));

        assertEquals(StatusPagamento.PAGO, pagamento.getStatusPagamento());
        assertTrue(textoImpresso().contains("insuficiente"));
        assertTrue(!textoImpresso().contains("Troco"));
    }

    @Test
    void registrar_dinheiroComVirgula_aceitaECalculaOTroco() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        pagamento.registrarPagamento(digitando("4\n50,50\n"));

        assertTrue(textoImpresso().contains("Troco: R$ 10.50"));
    }

    @Test
    void registrar_agendamentoCanceladoDepois_lancaIllegalStateException() {
        Agendamento agendamento = new Agendamento(null, null, null, null);
        agendamento.adicionarServico(new Servico("Lavagem", new BigDecimal("40.00")));
        Pagamento pagamento = new Pagamento(agendamento);
        agendamento.cancelar();

        assertThrows(IllegalStateException.class,
                () -> pagamento.registrarPagamento(new Scanner("3\n")));
    }

    @Test
    void registrar_duasVezes_lancaIllegalStateException() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));
        pagamento.registrarPagamento(digitando("3\n"));

        assertThrows(IllegalStateException.class,
                () -> pagamento.registrarPagamento(digitando("3\n")));
    }

    @Test
    void cancelar_pendente_marcaCancelado() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        pagamento.cancelarPagamento();

        assertEquals(StatusPagamento.CANCELADO, pagamento.getStatusPagamento());
    }

    @Test
    void cancelar_pago_lancaIllegalStateException() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));
        pagamento.registrarPagamento(digitando("3\n"));

        assertThrows(IllegalStateException.class, pagamento::cancelarPagamento);
    }

    @Test
    void registrar_pagamentoCancelado_lancaIllegalStateException() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));
        pagamento.cancelarPagamento();

        assertThrows(IllegalStateException.class,
                () -> pagamento.registrarPagamento(digitando("3\n")));
    }

    @Test
    void setValor_valorValido_atualiza() {
        Pagamento pagamento = new Pagamento(agendamentoComTotal("40.00"));

        pagamento.setValor(new BigDecimal("25.00"));

        assertEquals(new BigDecimal("25.00"), pagamento.getValor());
    }
}
